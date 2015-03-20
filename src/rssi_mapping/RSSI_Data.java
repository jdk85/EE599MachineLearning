/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_mapping;

import static java.lang.Math.abs;
import java.util.ArrayList;

/**
 *
 * @author atd43
 */
public class RSSI_Data {
    
    //All the required data that needs to be stored is here
    
    private final int min_size = 15; //prevents bad data from small sampling
    
    private int num_nodes;
    private ArrayList<Integer>[][] raw_links;
    
    private double[][] selected_rssi;
    private double[][] variance;
    
    private double[][] calc_distances;
    
    //Initiate all the objects
    public RSSI_Data(int nodes){
        num_nodes = nodes;
        
        selected_rssi = new double[num_nodes][num_nodes];
        variance = new double[num_nodes][num_nodes];
        calc_distances = new double[num_nodes][num_nodes];
        
        raw_links = new ArrayList[num_nodes][num_nodes];
        for (int rx = 0; rx < num_nodes; rx++)
            for (int tx = 0; tx < num_nodes; tx++)
                raw_links[rx][tx] = new ArrayList();
        
        //System.out.println("yup");
    }
    
    //add each data point into the structure
    public void add(int rx, int tx, int rssi){
        raw_links[rx][tx].add(rssi);
    }
    
    //run the stats and find distances after the structure is filled
    public void run_stats(){
        mirror_data();
        
        for (int rx = 0; rx < num_nodes; rx++){
            for (int tx = rx; tx < num_nodes; tx++){
                if (raw_links[rx][tx].size() < min_size){
                    raw_links[rx][tx].clear();
                    variance[rx][tx] = 0;
                    selected_rssi[rx][tx] = 0;
                    
                    calc_distances[rx][tx] = 0;
                }else{
                    int[] raw = new int[raw_links[rx][tx].size()];
                    
                    for (int i = 0; i < raw.length; i++)
                        raw[i] = raw_links[rx][tx].get(i);
                    
                    selected_rssi[rx][tx] = select_rssi(raw);
                    selected_rssi[tx][rx] = selected_rssi[rx][tx];
                    variance[rx][tx] = run_var(raw, selected_rssi[rx][tx]);
                    variance[tx][rx] = variance[rx][tx];
                    
                    calc_distances[rx][tx] = calc_dist(selected_rssi[rx][tx], variance[rx][tx]);
                    calc_distances[tx][rx] = calc_distances[rx][tx];
                }
                
                //System.out.println(rx+"x"+tx+": "+selected_rssi[rx][tx]);
            }
        }
    }
    
    //this is our distance estimation function
    private double calc_dist(double rssi, double variance){
        // parameters fit to model
        double d_0 = 1.75;
        double var_factor = 3.08;
        double PL_0 = -23.23;
        double n = 8.15;
        
        double numerator = rssi + variance*var_factor + PL_0;
        double denominator = -10*n;
        double exponent = numerator/denominator;
        
        
        return d_0 * Math.pow(10, exponent);
    }
    
    //reflect rx and tx values to get only 1 rssi
    //and distance value for each rx tx pair
    private void mirror_data(){
        for (int rx = 0; rx < num_nodes; rx++){
            for (int tx = rx + 1; tx < num_nodes; tx++){
                ArrayList<Integer> t = raw_links[rx][tx];
                raw_links[rx][tx].addAll(raw_links[tx][rx]);
                raw_links[tx][rx].addAll(t);
            }
        }
    }
    
    //get the variance from the chosen rssi value
    private double run_var(int[] data, double selected){
        double sum_dist = 0;
        
        for (int i = 0; i < data.length; i++)
            sum_dist += abs(selected - data[i]);
        
        return sum_dist/(data.length + 1);
    }
    
    //select the rssi value to use
    //currently uses the maximum value based on Log Normal Shadowing theory
    private double select_rssi(int[] data){
        double max = Double.NEGATIVE_INFINITY;
        
        for (int i = 0; i < data.length; i++)
            if (data[i] > max)
                max = data[i];
        
        return max;
    }
    
    /**
     * get methods listed below
     * no set methods to keep data safe
     * methods are overloaded with 2 versions
     * one version to get individual values
     * second version to get all values
    **/
    public double get_rssi(int rx, int tx){
        return selected_rssi[rx][tx];
    }
    
    public double[][] get_rssi(){
        return selected_rssi;
    }
    
    public double get_var(int rx, int tx){
        return variance[rx][tx];
    }
    
    public double[][] get_var(){
        return variance;
    }
    
    public double get_dist(int rx, int tx){
        return calc_distances[rx][tx];
    }
    
    public double[][] get_dist(){
        return calc_distances;
    }
    
    @Override
    public String toString(){
        String print = "\t";
        for (int tx = 0; tx < num_nodes; tx++){
            print += tx +"\t";
        }
        
        for (int rx = 0; rx < num_nodes; rx++){
            print += "\n"+rx+"\t";
            for (int tx = 0; tx < num_nodes; tx++){
                print += calc_distances[rx][tx] +"\t";
            }
        }
        return print;
    }
}
