/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_mapping;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author atd43
 */
public class csvLoader {
    
    // how many lines to skip
    int rssi_header_lines = 2;
    int loc_header_lines = 1;
    
    BufferedReader rssi_reader, loc_reader;
    
    //create the loader with the proper file locations
    //if they are invalid, throw an exception to stop the program
    public csvLoader(String loc_filename)
            throws FileNotFoundException{
        loc_reader = new BufferedReader(new FileReader(loc_filename));
    }
    
    //read the RSSI data into the structure
    //if theres a problem throw it to stop the program
    public void ReadRSSI(String rssi_filename, RSSI_Data destination) throws FileNotFoundException, IOException{
        //open file to read
        rssi_reader = new BufferedReader(new FileReader(rssi_filename));
        
        //remove header
        for (int i = 0; i < rssi_header_lines; i++) rssi_reader.readLine();
        String line;
        while((line = rssi_reader.readLine()) != null){
            String[] linex = line.split(",");
            int rx = Integer.parseInt(linex[0].trim());
            int tx = Integer.parseInt(linex[1].trim());
            int rssi = Integer.parseInt(linex[2].trim());
            
            destination.add(rx, tx, rssi);
            
            //System.out.println(rx+"x"+tx+": "+rssi);
        }
        //System.out.println("read it all");
        rssi_reader.close();
        destination.run_stats();
    }
    
    public int ReadNodes() throws IOException{
        //remove header
        for (int i = 0; i < loc_header_lines; i++) loc_reader.readLine();
        
        int nodes = 0;
        String line;
        
        //mark it so we can come back and read the locations later
        loc_reader.mark(255);

        while((line = loc_reader.readLine()) != null){
            int node = Integer.parseInt(line.split(",")[0]);
            if (node >= nodes){
                nodes = node+1;
            }
        }
        
        //System.out.println(nodes);
        
        loc_reader.reset();
        
        return nodes;
    }
    
    public void ReadLocs(NodeLocations destination) throws IOException{ 
        String line;
        while((line = loc_reader.readLine()) != null){
            String[] linex = line.split(",");
            int node = Integer.parseInt(linex[0]);
            String loc = linex[1];
            destination.addKnown(node, loc);
            
            //System.out.println(node+" "+loc);
        }
        
        loc_reader.close();
    }
    
}
