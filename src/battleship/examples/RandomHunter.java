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
import battleship.opponentStatistic.Statistic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Tobias
 */
public class RandomHunter implements BattleshipsPlayer
{
    private final static Random rnd = new Random();
    private int sizeX;
    private int sizeY;
    private int round;
    private List<Position> boardPosList;
    private List<Integer> positionsFiredAt;
    private List<Position> opponentMostFrequentlyShotPosition;
    private Statistic statistic;
    
    public int shotsFired = 0;
    

    public RandomHunter()
    {
        boardPosList  = new ArrayList();
        positionsFiredAt = new ArrayList();
        opponentMostFrequentlyShotPosition = new ArrayList();

        statistic = new Statistic();
        loadPos();
    }
    

    @Override
    public void placeShips(Fleet fleet, Board board)
    {
        sizeX = board.sizeX();
        sizeY = board.sizeY();
        //run through and place all ships in fleet
        for(int i = 0; i < fleet.getNumberOfShips(); ++i)
        {
            Ship s = fleet.getShip(i);
            //flip ship randomly
            boolean vertical = rnd.nextBoolean();
            Position pos;
            //create random position on board within confinements of board
            if(vertical)
            {
                int x = rnd.nextInt(sizeX);
                int y = rnd.nextInt(sizeY-(s.size()-1));
                pos = new Position(x, y);
            }
            else
            {
                int x = rnd.nextInt(sizeX-(s.size()-1));
                int y = rnd.nextInt(sizeY);
                pos = new Position(x, y);
            }
            //place ship on board
            board.placeShip(pos, s, vertical);
        }
    }

    @Override
    public void incoming(Position pos)
    {
      statistic.summarizeOpponentShotFrequency(pos);
    }

    @Override
    public Position getFireCoordinates(Fleet enemyShips)
    {
        
        int positionToShootAt = firedAtPostitionBefore();
        return boardPosList.get(positionToShootAt);
        
        
    }
        
    /**
     * Checks if a shot has been fired at the given position
     * 
     * If it hasn't fired at the position, it returns an int, that points at
     * the position to fire at, in the postions list
     * 
     * @return 
     */
    private int firedAtPostitionBefore() {    
        int pos = rnd.nextInt(boardPosList.size());
    
        if (positionsFiredAt.contains(pos)) {
            firedAtPostitionBefore();
        }    
        shotsFired ++;
        return pos;
    }
    
    
    /**
     * Loading all possible board-positions into array
     */
    private void loadPos() {
        //@TODO lav dem dynamiske
        int sizeX = 10;
        int sizeY = 10;
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                Position pos = new Position(j,i);
                boardPosList.add(pos);
            }
        }
    }
    
    @Override
    public void hitFeedBack(boolean hit, Fleet enemyShips)
    {
        //Do nothing
    }    

    @Override
    public void startMatch(int rounds)
    {
        //Do nothing
    }

    @Override
    public void startRound(int round)
    {
       if(round > 0){
        opponentMostFrequentlyShotPosition = statistic.getMostFrequentlyPositions();
        }
    }

    @Override
    public void endRound(int round, int points, int enemyPoints)
    {
//        System.out.println(opponentMostFrequentlyShotPosition);
    }

    @Override
    public void endMatch(int won, int lost, int draw)
    {
       
    }

    private List LinkedList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Position> getPosList() {
        return boardPosList;
    }

    public void setPosList(List<Position> posList) {
        this.boardPosList = posList;
    }
    
    
    
    
    
    
}
