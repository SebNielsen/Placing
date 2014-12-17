/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.opponentStatistic;

import battleship.interfaces.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sebastiannielsen
 */
public class Statistic {
    private Map<String, Integer> incomingShotFrequency;
    private List<PositionAndFrequency> posistionAndFrequency;
    private List<Position> highestPositionFrequency; 
    
    public Statistic(){
        incomingShotFrequency = new HashMap<>();
        posistionAndFrequency = new ArrayList<>();
        highestPositionFrequency = new ArrayList<>();
    }
    
    public void summarizeOpponentShotFrequency(Position pos){
        String incomingPosition = pos.toString();
        
        if (incomingShotFrequency.containsKey(incomingPosition)) {
            int frequency = incomingShotFrequency.get(incomingPosition);
            incomingShotFrequency.replace(incomingPosition, frequency, frequency + 1);
        } 
        
        else 
        {
            incomingShotFrequency.put(incomingPosition, 1);
        }

    }
    
    public List getMostFrequentlyPositions(){
        calculateMostFrequentlyPositions();
        return highestPositionFrequency;
    }
    
    private void calculateMostFrequentlyPositions(){
        posistionAndFrequency.clear();
        for(Map.Entry<String,Integer> entry: incomingShotFrequency.entrySet()){
            String[] positions = entry.getKey().split(",");
            Position pos = new Position(Integer.parseInt(positions[0]),Integer.parseInt(positions[1]));
            PositionAndFrequency posFre = new PositionAndFrequency(pos, entry.getValue());
            posistionAndFrequency.add(posFre);
        }
        Collections.sort(posistionAndFrequency);
        highestPositionFrequency.clear();
        for(int i = 0; i < 20; i++){
            Position pos = posistionAndFrequency.get(i).getPosition();
            highestPositionFrequency.add(pos);
        }
        
        
    }
    
    
}
