/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_mapping;

/**
 *
 * @author jdk85
 */
public class RoomProbs {
    
    int roomID;
    //Distance in meters
    double distanceM;
    //Distance in pixels
    double distanceP;
    //Confidence that true room is this room
    double confidence;

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public double getDistanceM() {
        return distanceM;
    }

    public void setDistanceM(double distanceM) {
        this.distanceM = distanceM;
    }

    public double getDistanceP() {
        return distanceP;
    }

    public void setDistanceP(double distanceP) {
        this.distanceP = distanceP;
    }
    
    
    
    
    
}
