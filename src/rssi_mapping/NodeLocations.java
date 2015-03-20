/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_mapping;

/**
 *
 * @author atd43
 */
public class NodeLocations {
    
    private int num_nodes;
    private LocList[] locs;
    
    public NodeLocations(int nodes){
        num_nodes = nodes;
        locs = new LocList[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            locs[i] = new LocList();
    }
    
    public void addKnown(int node, String loc){
        locs[node].add(new Location(loc, 1.0));
    }
    
    public void addUnknown(int node, String loc, double prob){
        locs[node].add(new Location(loc, prob));
    }
    
    public String getKnown(){
        String str = "";
        for (int i = 0; i < num_nodes; i++){
            if (locs[i].first != null)
                if (locs[i].first.location.prob >= .999)
                    str += i + ", ";
        }
        if (str.length() >= 2) str = str.substring(0, str.length()-2);
        return str;
    }
    
    @Override
    public String toString(){
        String print = "";
        for (int i = 0; i < num_nodes; i++){
            print += "Node "+i+"\n"
                    +locs[i].toString()
                    +"\n";
        }
        return print;
    }
}
