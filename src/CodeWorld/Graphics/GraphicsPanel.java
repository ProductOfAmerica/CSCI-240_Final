package CodeWorld.Graphics;

import CodeWorld.Drivers.Helpers.CWSException;
import CodeWorld.Objects.Shapes.Brick;
import CodeWorld.Objects.Shapes.Vector;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class GraphicsPanel extends JPanel implements Display {
    static final int kMaxDim = 1024;
    static final Color kBackground = new Color(200, 200, 255);
    private Displayable[][] dsps;
    private int imgX;
    private int imgY;
    private int imgWidth;
    private int imgHeight;
    private int pixPerCell;
    private int minPxPerCell;
    BufferedImage img;

    public GraphicsPanel(int xDim, int yDim) throws CWSException {
        this.dsps = new Brick[yDim][xDim];
        int minCellSize = (int)Math.floor(1024 / Math.max(xDim, yDim));
        if (minCellSize < 1) {
            throw new CWSException(String.format("Display dimension is over %d", 1024));
        }
        this.pixPerCell = 1;
        while (minCellSize > 1) {
            this.pixPerCell *= 2;
            minCellSize >>= 1;
        }
        this.minPxPerCell = this.pixPerCell;
        this.imgWidth = xDim * this.pixPerCell;
        this.imgHeight = yDim * this.pixPerCell;
        this.img = new BufferedImage(this.imgWidth, this.imgHeight, 2);
        this.buildImage();
        this.addMouseListener(new MouseAdapter(){

            @Override
            public void mousePressed(MouseEvent evt) {
                if (!evt.isControlDown()) {
                    GraphicsPanel.this.recenter(evt.getX(), evt.getY());
                } else {
                    GraphicsPanel.this.zoom(evt.getButton() == 1 ? 1 : -1);
                }
            }
        });
    }

    @Override
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.imgWidth, this.imgHeight);
    }

    public void zoom(int steps) {
        int oldPPC = this.pixPerCell;
        this.pixPerCell = steps > 0 ? this.pixPerCell << steps : this.pixPerCell >> - steps;
        this.pixPerCell = Math.max(this.minPxPerCell, this.pixPerCell);
        this.makeCenter((this.imgX + this.imgWidth / 2) * this.pixPerCell / oldPPC, (this.imgY + this.imgHeight / 2) * this.pixPerCell / oldPPC);
        this.buildImage();
        this.repaint();
    }

    public void makeCenter(int x, int y) {
        int xLimit = this.dsps[0].length * this.pixPerCell - this.imgWidth;
        int yLimit = this.dsps.length * this.pixPerCell - this.imgHeight;
        this.imgX = Math.max(0, Math.min(xLimit, x - this.imgWidth / 2));
        this.imgY = Math.max(0, Math.min(yLimit, y - this.imgHeight / 2));
    }

    public void recenter(int x, int y) {
        this.makeCenter(this.imgX + x, this.imgY + y);
        this.buildImage();
        this.repaint();
    }

    public void buildImage() {
        Graphics2D grp = this.img.createGraphics();
        grp.setColor(kBackground);
        grp.fillRect(0, 0, this.imgWidth, this.imgHeight);
        int row = this.imgY / this.pixPerCell;
        while (row <= (this.imgY + this.imgHeight - 1) / this.pixPerCell) {
            int col = this.imgX / this.pixPerCell;
            while (col <= (this.imgX + this.imgWidth - 1) / this.pixPerCell) {
                if (this.dsps[row][col] != null) {
                    grp.drawImage(this.dsps[row][col].getImage(this.pixPerCell), col * this.pixPerCell - this.imgX, row * this.pixPerCell - this.imgY, null);
                }
                ++col;
            }
            ++row;
        }
        grp.dispose();
    }

    public boolean inRange(Vector loc) {
        if (loc.getX() >= 0 && loc.getX() < this.dsps[0].length && loc.getY() >= 0 && loc.getY() < this.dsps.length) {
            return true;
        }
        return false;
    }

    public boolean inImage(Vector loc) {
        if (loc.getX() > this.imgX - this.pixPerCell && loc.getX() < this.imgX + this.imgWidth && loc.getY() > this.imgY - this.pixPerCell && loc.getY() < this.imgY + this.imgHeight) {
            return true;
        }
        return false;
    }

    public void update(Displayable dsp, Object lc) {
        Vector newLoc = dsp.getLoc();
        Graphics2D imgGrp = this.img.createGraphics();
        if (lc != null) {
            Vector oldLoc = (Vector)lc;
            Vector oldPxLoc = oldLoc.scale(this.pixPerCell);
            if (this.inRange(oldLoc)) {
                this.dsps[oldLoc.getY()][oldLoc.getX()] = null;
                if (this.inImage(oldPxLoc)) {
                    imgGrp.setColor(kBackground);
                    imgGrp.fillRect(oldPxLoc.getX() - this.imgX, oldPxLoc.getY() - this.imgY, this.pixPerCell, this.pixPerCell);
                }
            }
        }
        if (newLoc != null) {
            Vector newPxLoc = newLoc.scale(this.pixPerCell);
            if (this.inRange(newLoc)) {
                this.dsps[newLoc.getY()][newLoc.getX()] = dsp;
                if (this.inImage(newPxLoc)) {
                    imgGrp.drawImage(dsp.getImage(this.pixPerCell), newPxLoc.getX() - this.imgX, newPxLoc.getY() - this.imgY, null);
                }
            }
        }
        imgGrp.dispose();
        this.repaint();
    }

    @Override
    public void addDisplayable(Displayable dsp) {
        this.dsps[dsp.getLoc().getY()][dsp.getLoc().getX()] = dsp;
        this.update(dsp, null);
    }

    @Override
    public void redraw(int time) {
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.img, 0, 0, null);
    }
}