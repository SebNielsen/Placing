/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.examples;

import battleship.interfaces.BattleshipsPlayer;
import battleship.interfaces.Fleet;
import battleship.interfaces.Position;
import battleship.interfaces.Board;
import battleship.interfaces.Ship;
import static java.lang.Integer.parseInt;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Tobias
 */
public class ProbDensityPlayer implements BattleshipsPlayer {

    private final static Random rnd = new Random();
    private int sizeX;
    private int sizeY;


    int shotsFired = 0;

    Map <String, Integer> matrix;

    public ProbDensityPlayer() {
        matrix = new HashMap();
    }

    @Override
    public void placeShips(Fleet fleet, Board board) {
        sizeX = board.sizeX();
        sizeY = board.sizeY();
        //run through and place all ships in fleet
        for (int i = 0; i < fleet.getNumberOfShips(); ++i) {
            Ship s = fleet.getShip(i);
            //flip ship randomly
            boolean vertical = rnd.nextBoolean();
            Position pos;
            //create random position on board within confinements of board
            if (vertical) {
                int x = rnd.nextInt(sizeX);
                int y = rnd.nextInt(sizeY - (s.size() - 1));
                pos = new Position(x, y);
            } else {
                int x = rnd.nextInt(sizeX - (s.size() - 1));
                int y = rnd.nextInt(sizeY);
                pos = new Position(x, y);
            }
            //place ship on board
            board.placeShip(pos, s, vertical);
        }
    }

    @Override
    public void incoming(Position pos) {

        //Do nothing
    }

    @Override
    public Position getFireCoordinates(Fleet enemyShips) {
        for (int i = 0; i < enemyShips.getNumberOfShips(); ++i) {
            Ship enemyShip = enemyShips.getShip(i);
            int shipLength = enemyShip.size(); //ex. 5
            System.out.println("currentShipLength is: " + shipLength);
            //vertical prob
            System.out.println("Running in vertical mode");
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
             System.out.println("Running in horizontal mode");
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

        for (Map.Entry<String, Integer> entry : matrix.entrySet())
        {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;
//                System.out.println(maxEntry);
            }
        }

        int x = parseInt(maxEntry.getKey().substring(0, 1));
        int y = parseInt(maxEntry.getKey().substring(2, 3));
//        System.out.println("Pos X is: " + x);
//        System.out.println("Pos X is: " + y);
        shotsFired++;
        System.out.println("Shots fired: " + shotsFired);
        System.out.println("Firing at pos: " + x +","+y);
        matrix.clear();
        return new Position(x, y);
    }

    private void printMatrix() {
        int i = 0;
        for (Integer value : matrix.values()) {
            i++;
//            System.out.println("value " + i + ": " + value);
        }
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
        System.out.println("Added: " + input);
        System.out.println(newWeight + " times");
    }

    @Override
    public void hitFeedBack(boolean hit, Fleet enemyShips) {
        //Do nothing
    }

    @Override
    public void startMatch(int rounds) {
        //Do nothing
    }

    @Override
    public void startRound(int round) {
        //Do nothing
    }

    @Override
    public void endRound(int round, int points, int enemyPoints) {
        System.out.println("End of round!");
        matrix.clear();
    }

    @Override
    public void endMatch(int won, int lost, int draw) {
//        printMatrix();
        System.out.println("Printing now!");
        System.out.println("Shots Fired by RandomPlayer: " + shotsFired);
    }
}
