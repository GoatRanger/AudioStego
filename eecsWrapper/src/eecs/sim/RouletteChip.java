/*
 * RouletteChip.java
 *
 * Created on July 9, 2002, 10:18 PM
 */
package eecs.sim;

/**
 * A marker designed to be used with the roulette table.  A small
 * "Vegas" chip, patterened after the EECS Bicentennial Crest.
 *
 * @author Karl A. Gossett
 * @version 0.1
 */
public class RouletteChip extends jago.element.VisibleElement
{
    /**
     * Creates new RouletteChip
     */
    public RouletteChip()
    {
        setAppearance("images/eecschip.gif");
    }

    /**
     * Establishes the location of the chip.
     *
     * @param x The horizontal location
     * @param y The vertical location
     */
    public void setLocation(double x, double y)
    {
        if (added)
        {
            location.setLocation(x, y);
        }
    }
}
