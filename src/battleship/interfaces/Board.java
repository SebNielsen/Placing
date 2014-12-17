/**
 * @author Tobias Grundtvig
 */
package battleship.interfaces;

public interface Board {

    /**
     * Returns X dimension of the board The positions on the board are
     * zero-indexed, so the x coordinates goes from 0 to sizeX() – 1
     *
     * @return x dimension of board
     */
    public int sizeX();

    /**
     * Returns Y dimension of the board The positions on the board are
     * zero-indexed, so the y coordinates goes from 0 to sizeY() – 1
     *
     * @return y dimension of board
     */
    public int sizeY();

    /**
     * The placeShip method is used to place the individual ships on the board.
     * The POS parameter is an instance of the Position class and represents the
     * starting position of the ship. The SHIP parameter is the ship to be
     * placed. Each ship may only be placed once. The VERTICAL parameter decides
     * the direction of the ship. If VERTICAL is FALSE the ship will be placed
     * horizontally which means that it is placed from the given position and
     * increasing along the x- axis up to the size of the ship. If VERTICAL is
     * TRUE, the ship will be placed vertically, which means that it is placed
     * from the given position and increasing along the y-axis up to the size of
     * the ship.
     *
     * @param pos starting pos of ship
     * @param ship the ship to be placed
     * @param vertical direction of ship
     */
    public void placeShip(Position pos, Ship ship, boolean vertical);
}
