package battleship.interfaces;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tobias
 */
public interface FireStrategy {
    
    public Position getFireCoordinates(Fleet enemyShips);
    
    public void hitFeedback(boolean hit, Fleet enemyShips);
    
    public void endMatch(int round, int points, int enemyPoints);

    public void endRound(int round, int points, int enemyPoints);
}
