/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_mapping;
import java.util.ArrayList;
import java.util.Map;
/**
 *
 * @author jdk85
 */
public class IntersectsToRooms {
    
    //Resources object created in RSSI_Mapping
    Resources resources;
    //HashMap containing each room object referenced by room id
    Map<Integer,Room> rooms;
    //Multiplier for pixel to meters
    double scale;
    
    /**
     * Default constructor, must be passed Resource object with room info
     * 
     * @param resources
     * @param scale 
     */
    public IntersectsToRooms(Resources resources, double scale){
        this.resources = resources;
        rooms = resources.getRooms();
        this.scale = scale;
    }
    
    /**
     * This method returns an array of the probabilities for each point 
     * and for each room based on distances from the point to each room center
     * @param intersections
     * @return 
     */
    public ArrayList<RoomProbsPerPoint> getRoomProbabilities(ArrayList<IntersectionPoint> intersections){
       //An arraylist that contains ALL of the room probabilities for each intersection
        ArrayList<RoomProbsPerPoint> roomProbsPerPoint = new ArrayList<RoomProbsPerPoint>();
        
        //For each intersection that was passed to this function
        for(IntersectionPoint ip : intersections){
            //Create a new object for each intersection
            RoomProbsPerPoint rppp = new RoomProbsPerPoint(ip);     
            
            //For each room in the rooms array
            for(Map.Entry<Integer,Room> entry : rooms.entrySet()){
                //Create a room prob object that contains a room id and the
                //distance between the current intersection and the room center
                //in both pixels and meters
                RoomProbs roomProbs = new RoomProbs();
                //Distance formula in pixels
                double pixel_distance = Math.sqrt((ip.x - entry.getValue().loc.x)^2 + (ip.y - entry.getValue().loc.y)^2);
                //Set the pixel distance to the RoomProbs object
                roomProbs.setDistanceP(pixel_distance);
                //Set the meter distance to the RoomProbs object
                roomProbs.setDistanceM(pixel_distance * scale);
                //Set the room ID value in the RoomProbs object
                roomProbs.setRoomID(entry.getKey());
                //Set the confidence that this room is the true room
                roomProbs.setConfidence(ip.confidence / pixel_distance);
                //Add the object to the RoomProbsPerPoint
                rppp.addRoomProbs(roomProbs);                
            }
        }
        
        //Returns the final result - an array list containing RoomProbsPerPoint
        //for each intersection point
        return roomProbsPerPoint;
        
    }
    
}
