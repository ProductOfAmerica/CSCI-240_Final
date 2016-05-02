package CodeWorld.Graphics;

public interface Display {

    /* Add |dsp| to the list of Displayables to be shown in this CodeWorld.Graphics.Display */
    void addDisplayable(Displayable dsp);

    /* Update the display to reflect current state of Displayables.  Assume we
     * are at time |time|. */
    void redraw(int time);
}