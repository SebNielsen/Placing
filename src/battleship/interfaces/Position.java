/**
 * The Position class represents a two-dimensional position on the board and has
 * an x-coordinate and a y-coordinate. It has a constructor that takes the x and
 * y coordinates as parameters which you can use to create new Position objects.
 *
 * @author Tobias Grundtvig
 */
package battleship.interfaces;

public class Position {

    public final int x;
    public final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
    
    
    
}
