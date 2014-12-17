/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.strategy;

import battleship.examples.Slayer;
import battleship.interfaces.FireStrategy;
import battleship.interfaces.Fleet;
import battleship.interfaces.Position;
import battleship.interfaces.Ship;
import static java.lang.Integer.parseInt;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Tobias
 */
public class ProbDensityStrategy implements FireStrategy {

    private Map<String, Integer> matrix;
    private int shotsFired = 0;
    
    private final static Random rnd = new Random();
    private int sizeX;
    private int sizeY;

    public ProbDensityStrategy(int boardSizeX, int boardSizeY) {
        matrix = new HashMap();
        this.sizeX = boardSizeX;
        this.sizeY = boardSizeY;
        
    }

    @Override
    public Position getFireCoordinates(Fleet enemyShips) {
        for (int i = 0; i < enemyShips.getNumberOfShips(); ++i) {
            Ship enemyShip = enemyShips.getShip(i);
            int shipLength = enemyShip.size(); //ex. 5
            //System.out.println("currentShipLength is: " + shipLength);
            //vertical prob
            //System.out.println("Running in vertical mode");
            for (int y = 0; y < sizeY; y++) {
                for (int x = 0; x < sizeX; x++) {
                    for (int L = 0; L < shipLength; L++) {
                        int maxVPos = sizeY - shipLength;
                        if (y <= maxVPos) {
                            String posKey = x + "," + (y + L); //ex. 0,0 -> 0,1 -> 0,2 -> 0,3 -> 0,4
                            addToMatrix(posKey);
                        } else {
                            //do nothing
                        }
                    }
                }
            }
            //horizontal prob
            //System.out.println("Running in horizontal mode");
            for (int x = 0; x < sizeX; x++) {
                for (int y = 0; y < sizeY; y++) {
                    for (int L = 0; L < shipLength; L++) {
                        int maxHPos = sizeX - shipLength;
                        if (x <= maxHPos) {
                            String posKey = (x + L) + "," + y; //ex. 0,0 -> 1,0 -> 2,0 -> 3,0 -> 4,0
                            addToMatrix(posKey);
                        } else {
                            //do nothing
                        }
                    }
                }
            }
        }

        Map.Entry<String, Integer> maxEntry = null;

        for (Map.Entry<String, Integer> entry : matrix.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
//                System.out.println(maxEntry);
            }
        }

        int x = parseInt(maxEntry.getKey().substring(0, 1));
        int y = parseInt(maxEntry.getKey().substring(2, 3));
//        System.out.println("Pos X is: " + x);
//        System.out.println("Pos X is: " + y);
        shotsFired++;
        //System.out.println("Shots fired: " + shotsFired);
        //System.out.println("Firing at pos: " + x + "," + y);
        matrix.clear();
        return new Position(x, y);
    }

    @Override
    public void hitFeedback(boolean hit, Fleet enemyShips) {

    }

    @Override
    public void endMatch(int round, int points, int enemyPoints) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void endRound(int round, int points, int enemyPoints) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void addToMatrix(String input) {
        int newWeight = 0;
        if (matrix.containsKey(input)) {
            newWeight = matrix.get(input) + 1;
            matrix.remove(input);
            matrix.put(input, newWeight);
        } else {
            newWeight = 1;
            matrix.put(input, newWeight);
        }
        //System.out.println("Added: " + input);
        //System.out.println(newWeight + " times");
    }

}
