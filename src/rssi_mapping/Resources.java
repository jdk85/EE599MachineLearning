package rssi_mapping;



import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.*;
/**
 *
 * @author atd43
 */
public class Resources {
    
    String room_lookup_filename;
    int lookup_header_lines = 1;
            
    Hashtable<Integer, Room> lookup_by_color;
    Hashtable<Point, Room> lookup_by_loc;
    Hashtable<String, Room> lookup_by_name;
    
    public Resources(String filename) throws IOException{
        room_lookup_filename = filename;
        
        lookup_by_color = new Hashtable();
        lookup_by_loc = new Hashtable();
        lookup_by_name = new Hashtable();
        
        load_hash();
    }

    private void load_hash() throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(room_lookup_filename));
        
        //remove header
        for (int i = 0; i < lookup_header_lines; i++) reader.readLine();
        
        //read in the data
        String line;
        while((line = reader.readLine()) != null){
            String[] linex = line.split(",");
            int color = Integer.parseInt(linex[0]);
            String name = linex[1];
            Point loc = new Point(Integer.parseInt(linex[2]), Integer.parseInt(linex[3]));
            
            Room room = new Room(name, loc, color);
            
            lookup_by_name.put(name, room);
            lookup_by_loc.put(loc, room);
            lookup_by_color.put(color, room);
        }
        reader.close();
    }

    private class Room {
        String name;
        Point loc;
        int color;

        public Room(String name, Point loc, int color) {
            this.name = name;
            this.loc = loc;
            this.color = color;
        }
    }
    
    public String getRoom(int color_code){
        return lookup_by_color.get(color_code).name;
    }
    
    public String getRoom(int x, int y){
        return lookup_by_loc.get(new Point(x,y)).name;
    }
    
    public int getx(int color_code){
        return lookup_by_color.get(color_code).loc.x;
    }
    
    public int gety(int color_code){
        return lookup_by_color.get(color_code).loc.y;
    }
    
    public int getx(String name){
        return lookup_by_name.get(name).loc.x;
    }
    
    public int gety(String name){
        return lookup_by_name.get(name).loc.y;
    }
    
    public int getColor(String name){
        return lookup_by_name.get(name).color;
    }
    
    public int getColor(int x, int y){
        return lookup_by_loc.get(new Point(x,y)).color;
    }
}
