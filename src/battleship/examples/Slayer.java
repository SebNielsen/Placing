/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.examples;

import battleship.interfaces.BattleshipsPlayer;
import battleship.interfaces.Fleet;
import battleship.interfaces.Position;
import battleship.interfaces.Board;
import battleship.interfaces.FireStrategy;
import battleship.interfaces.Ship;
import battleship.opponentStatistic.Statistic;
import battleship.strategy.ProbDensityStrategy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Tobias
 */
public class Slayer implements BattleshipsPlayer {

    private final static Random rnd = new Random();
    private int sizeX;
    private int sizeY;
    private int round;
    private FireStrategy strategy;
    private List<Position> opponentMostFrequentlyShootingPosition;
    private Map<String, Position> possiblePlacingPositions;
    private List<String> usedPositions; 
    private Statistic statistic;

    int shotsFired = 0;

    public Slayer() {
        opponentMostFrequentlyShootingPosition = new ArrayList();
        possiblePlacingPositions = new HashMap();
        usedPositions = new ArrayList();
        statistic = new Statistic();
        
    }

    @Override
    public void placeShips(Fleet fleet, Board board) {
        sizeX = board.sizeX();
        sizeY = board.sizeY();
        strategy = new ProbDensityStrategy(sizeX, sizeY);

        updatePossiblePlacingPositions();
        usedPositions.clear();

        //run through and place all ships in fleet
        for (int i = 0; i < fleet.getNumberOfShips(); i++) {
            Ship s = fleet.getShip(i);

            Position pos = null;
            boolean possibleToPlaceShip = false;
            boolean vertical = false;

            while (possibleToPlaceShip == false) {
                
                //choose random position on board
                Object[] positions = possiblePlacingPositions.values().toArray();
                pos = (Position) positions[rnd.nextInt(positions.length)];
                
                //flip ship randomly
                vertical = rnd.nextBoolean();
                boolean validPlacement = false;

                int xValue = pos.x;
                //check for placement in vertical direction
                if (vertical) {
                    for (int j = 0; j < s.size(); j++) {
                        int tempPosY = pos.y + j;
                        String tempPos = "" + pos.x + "," + tempPosY;

                        if ((!usedPositions.contains(tempPos)) && possiblePlacingPositions.containsKey(tempPos) && tempPosY <= sizeY) {
                            validPlacement = true;
                        } else {
                            validPlacement = false;
                            break;
                        }
                    }
                    // returns that the position is valid
                    if (validPlacement == true) {
                        possibleToPlaceShip = true;
                        for (int l = 0; l < s.size(); l++) {
                            String usedPosistion = "" + pos.x + "," + (pos.y + l);
                            usedPositions.add(usedPosistion);
                        }

                        for (String usedPos : usedPositions) {
                            possiblePlacingPositions.remove(usedPos);
                        }
                    }
                    //check for placement in horisontal direction
                } else {
                    for (int k = 0; k < s.size(); k++) {
                        int tempPosX = pos.x + k;
                        String tempPos = "" + tempPosX + "," + pos.y;
                        if ((!usedPositions.contains(tempPos)) && possiblePlacingPositions.containsKey(tempPos) && tempPosX <= sizeX) {
                            validPlacement = true;
                        } else {
                            validPlacement = false;
                            break;
                        }
                    }
                    if (validPlacement == true) {
                        possibleToPlaceShip = true;
                        for (int l = 0; l < s.size(); l++) {
                            String usedPosition = "" + (pos.x + l) + "," + pos.y;
                            usedPositions.add(usedPosition);
                            possiblePlacingPositions.remove(usedPosition);
                        }
                        
                        
                    }

                }
            }
            //place ship on board
            System.out.println("round" + round);
            System.out.println("pos: " + pos);
            System.out.println("ship " +s.size());
            System.out.println("vertical " + vertical);
            System.out.println("");
            board.placeShip(pos, s, vertical);
        }
    }

    @Override
    public void incoming(Position pos) {
        
         statistic.summarizeOpponentShotFrequency(pos);
       
    }

    public Position getFireCoordinates(Fleet enemyShips)
    {
        //systematically shoot from left to right
        //one row at a time
       int nextX = 0;
       int nextY = 0;
        Position shot = new Position(nextX, nextY);
        ++nextX;
        if(nextX >= sizeX)
        {
            nextX = 0; 
            ++nextY;
            if(nextY >= sizeY)
            {
                nextY = 0;
            }
        }
        return shot;
    }

    @Override
    public void hitFeedBack(boolean hit, Fleet enemyShips)
    {
        //Do nothing
    }

    @Override
    public void startMatch(int rounds) {
        //Do nothing
    }

    @Override
    public void startRound(int round) {
        this.round = round;
        opponentMostFrequentlyShootingPosition = statistic.getMostFrequentlyPositions();
    }

    @Override
    public void endRound(int round, int points, int enemyPoints) {
        //Do nothing
    }

    @Override
    public void endMatch(int won, int lost, int draw) {
   
    }
    
    
    private void updatePossiblePlacingPositions(){

        possiblePlacingPositions.clear();
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                Position position = new Position(j, i);
                possiblePlacingPositions.put(position.toString(), position);
            }
        }
        if (round > 1) {
            for(Position pos : opponentMostFrequentlyShootingPosition){
                if(possiblePlacingPositions.containsKey(pos.toString())){
                    possiblePlacingPositions.remove(pos.toString());
                }
            }
           
        }

    }

    
    public int getSizeX() {
        return sizeX;
    }
    
}
