/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.opponentStatistic;

import battleship.interfaces.Position;

/**
 *
 * @author sebastiannielsen
 */
public class PositionAndFrequency implements Comparable<PositionAndFrequency>{
    private Position position;
    private int frequency;
    
    public PositionAndFrequency(Position pos, int freq){
        this.position = pos;
        this.frequency = freq;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "PositionFrequency" + position + "" + frequency;
    }

    @Override
    public int compareTo(PositionAndFrequency pf) {
           PositionAndFrequency posFre = pf;
           return (pf.getFrequency() - frequency);

    }

    
    
    
    
}
