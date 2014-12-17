/**
 * @author Tobias Grundtvig
 */
package battleship.interfaces;

public interface BattleshipsPlayer {

    /**
     * For each round, phase one is started by calling the method placeShips on
     * each player. The method placeShips has two parameters fleet and board.
     *
     * @param fleet players fleet
     * @param board tournament board
     */
    public void placeShips(Fleet fleet, Board board);

    /**
     * Info about enemy's shooting position
     *
     * @param pos enemy shot pos
     */
    public void incoming(Position pos);

    /**
     * The method getFireCoordinates will be called each time a player must fire
     * a shot. The parameter to the method is a fleet containing the enemy’s
     * remaining ships and the method must return the coordinate that the shot
     * should be fired at.
     *
     * @param enemyShips enemy fleet
     * @return Position coordinate to fire at
     */
    public Position getFireCoordinates(Fleet enemyShips);

    /**
     * After each shot, the method hitFeedBack is called to tell the shooter if
     * the shot was a hit or a miss. This call also has the enemy fleet as a
     * parameter, so it is possible to see if any ships were sunk.
     *
     * @param hit ?
     * @param enemyShips enemy fleet
     */
    public void hitFeedBack(boolean hit, Fleet enemyShips);

    /**
     * Gives information about how many rounds this match consist of. Normally
     * 1000.
     *
     * @param rounds number of rounds in a game
     */
    public void startMatch(int rounds);

    /**
     * Called when the match is over. It gives you information about how many
     * rounds you won, lost and played to a draw.
     *
     * @param won rounds won
     * @param lost rounds lost
     * @param draw rounds draw
     */
    public void endMatch(int won, int lost, int draw);

    /**
     * Called at the beginning of each (of the 1000) rounds with the number of
     * the current round.
     *
     * @param round current round number
     */
    public void startRound(int round);

    /**
     * Called at the end of the round with the current round number, and both
     * your own and your enemy’s points. In this way you can see if you won or
     * lost. And if you lost you can change strategy.
     *
     * @param round current round number
     * @param points player points
     * @param enemyPoints enemy points
     */
    public void endRound(int round, int points, int enemyPoints);

}
