/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_mapping;

import java.awt.Point;

/**
 *
 * @author atd43
 */
public class MappingFunctions {
    
    public static Point[] getIntersections(Point p1, double d1, Point p2, double d2){
      
        //get the component distances
        int dx = p2.x-p1.x;
        int dy = p2.y-p1.y;
        
        //get the absolute distance
        double dist = Math.sqrt((dx*dx)+(dy*dy));
        
        if ((dist > d1+d2) || (dist < Math.abs(d1-d2))){
            //the ranges do not intersect so we cannot return anything useful
            //or once circle is contained within the other
            return new Point[0];            
        } else if (dist == d1+d2){
            //the ranges intersect at a single point
            Point[] point = new Point[1];
            
            int x = (int)(p1.x+(p2.x-p1.x)*d1/(d1+d2));
            int y = (int)(p1.y+(p2.y-p1.y)*d1/(d1+d2));
            
            point[0] = new Point(x,y);
            return point;
        } else {
            //the ranges intersect at 2 points
            Point[] points = new Point[2];
            
            //find the distance from p1 to the line through both points
            double a = ((d1*d1) - (d2*d2) + (dist*dist))/(2*dist);
            
            //determine the point where the prev line crosses the line through p1 and p2
            int m_x = (int)(p1.x + (dx * a/dist));
            int m_y = (int)(p1.y + (dy * a/dist));
            Point m = new Point(m_x, m_y);
            
            //find the distance from point m to the intersection points
            double h = Math.sqrt((d1*d1)-(a*a));
            
            //find the offsets of the intersection points from point m
            int rx = (int)(-dy*h/dist);
            int ry = (int)(dx*h/dist);
            
            //find the intersection points
            points[0] = new Point(m.x+rx, m.y+ry);
            points[1] = new Point(m.x-rx, m.y-ry);
            
            return points;
        }
    }
    
}
