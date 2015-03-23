/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_mapping;
import java.util.ArrayList;
/**
 *
 * @author jdk85
 */
public class RoomProbsPerPoint {
    
    IntersectionPoint point;
    ArrayList<RoomProbs> roomProbs;
    
    public RoomProbsPerPoint(IntersectionPoint point){
        //Default constructor
        this.point = point;
        roomProbs = new ArrayList<RoomProbs>();
    }
    
    public void addRoomProbs(RoomProbs rp){
        roomProbs.add(rp);
    }

    public IntersectionPoint getPoint() {
        return point;
    }

    public void setPoint(IntersectionPoint point) {
        this.point = point;
    }

    public ArrayList<RoomProbs> getRoomProbs() {
        return roomProbs;
    }

    public void setRoomProbs(ArrayList<RoomProbs> roomProbs) {
        this.roomProbs = roomProbs;
    }
    
    
}
