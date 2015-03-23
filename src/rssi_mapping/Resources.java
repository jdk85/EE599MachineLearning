package rssi_mapping;



import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Hashtable;
import javax.swing.JOptionPane;
/**
 *
 * @author atd43
 */
public class Resources {
    
    String room_lookup_filename;
    int lookup_header_lines = 1;
    int number_of_columns = -1;
    
    //Create a Hashmap of all rooms where key is room id and
    //value is the Room object
    Map<Integer,Room> rooms = new HashMap<Integer,Room>();
    
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
        for (int i = 0; i < lookup_header_lines; i++){
            String[] hdr = reader.readLine().split(",");
            //Store the number of columns in the csv
            if(hdr.length > number_of_columns ){
                number_of_columns = hdr.length;
            }
            
        }
        
        //read in the data
        String line;
        while((line = reader.readLine()) != null){
            String[] linex = line.split(",");
            if(number_of_columns == 4){
                int color = Integer.parseInt(linex[0].trim());
                String name = linex[1].trim();
                Point loc = new Point(Integer.parseInt(linex[2].trim()), Integer.parseInt(linex[3].trim()));

                Room room = new Room(name, loc, color);

                lookup_by_name.put(name, room);
                lookup_by_loc.put(loc, room);
                lookup_by_color.put(color, room);
            }
            else if(number_of_columns == 7){
                int id = Integer.parseInt(linex[0].trim());
                int R = Integer.parseInt(linex[1].trim());
                int G = Integer.parseInt(linex[2].trim());
                int B = Integer.parseInt(linex[3].trim());
                String name = linex[4].trim();
                Point loc = new Point(Integer.parseInt(linex[5].trim()), Integer.parseInt(linex[6].trim()));
                
                Room room = new Room(id,R,G,B,name,loc);
                rooms.put(id, room);
                
        
            }
            else{
                JOptionPane.showMessageDialog(null, "Error: Unknown number of columns in lookup CSV", "Alert", JOptionPane.ERROR_MESSAGE);
            }
        }
        reader.close();
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
    
    /**
     * Returns a HashMap containing the room id as key and the Room object as val
     * 
     * @return rooms HashMap 
     */
    public Map getRooms(){
        return rooms;
    }
    
    public Room getRoomByID(int id){
        return rooms.get(id);
    }
}
