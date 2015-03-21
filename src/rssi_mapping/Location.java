/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_mapping;

import java.util.ArrayList;

/**
 *
 * @author atd43
 */
@Deprecated
public class Location {
    
    public String loc;
    public double prob;
    
    public Location(String loc, double prob){
        this.loc = loc;
        this.prob = prob;
    }
    
    //for sorting purposes
    public int compareTo(Location other){
        return Double.compare(this.prob, other.prob);
    }
    
    @Override
    public String toString(){
        return loc + " - " + (prob*100)+"%";
    }
    
    //for searching purposes
    public boolean equals(Location other){
        return this.loc.equals(other.loc);
    }
}
