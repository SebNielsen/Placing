/**
 * @author Tobias Grundtvig
 */
package battleship.interfaces;

public interface Fleet {

    /**
     * The method getNumberOfShips returns the number of ships in the fleet.
     *
     * @return number of ships in fleet
     */
    public int getNumberOfShips();

    /**
     * The method getShip returns the ship with a given index. The indexes goes
     * from zero to the number of ships minus one. The getShip returns an
     * instance of a class that implements the Ship interface.
     *
     * @param index ship index
     * @return ship at given index
     */
    public Ship getShip(int index);
}
