package rssi_mapping;


import java.awt.Point;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jdk85
 */
public class Room {
        int id;
        int R;
        int G;
        int B;
        String name;
        Point loc;
        int color;

        /**
         * Original Constructor
         * @param name
         * @param loc
         * @param color 
         */
        public Room(String name, Point loc, int color) {
            this.name = name;
            this.loc = loc;
            this.color = color;
        }
        
        /**
         * Constructor using id, RGB, name, and point location
         * @param id
         * @param R
         * @param G
         * @param B
         * @param name
         * @param loc 
         */
        public Room(int id, int R, int G, int B,String name, Point loc) {
            this.id = id;
            this.R = R;
            this.G = G;
            this.B = B;
            this.name = name;
            this.loc = loc;
            color = calculateColor(R,G,B);
        }
        
        /**
         * This method automatically calculates an overall color value 
         * based on the RGB value received
         * 
         * @param R
         * @param G
         * @param B
         * @return 
         */
        public int calculateColor(int R, int G, int B){
            //TODO: calculate color here
            
            return -1;
        }
        

        
    
}
