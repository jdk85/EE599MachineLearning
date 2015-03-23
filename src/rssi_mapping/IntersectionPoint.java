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
public class IntersectionPoint {
    int x,y;
    double confidence;
    
    /**
     * Default Constructor - takes x,y point and confidence value
     * @param x
     * @param y
     * @param confidence
     */
    public IntersectionPoint(int x, int y, double confidence){
        setX(x);
        setY(y);
        setConfidence(confidence);
    }
    
    /**
     * Getter for x
     * @return y
     */
    public int getX(){
        return x;
    }
    
    /**
     * Getter for y
     * @return y
     */
    public int getY(){
        return y;
    }
    
    /**
     * Getter for confidence
     * @return confidence
     */
    public double getConfidence(){
        return confidence;
    }
    
    /**
     * Setter for x
     * @param x 
     */
    public void setX(int x){
        this.x = x;
    }
    
    /**
     * Setter for y
     * @param y
     */
    public void setY(int y){
        this.y = y;
    }
    
    /**
     * Setter for confidence
     * @param confidence
     */
    public void setConfidence(double confidence){
        this.confidence = confidence;
    }
}
