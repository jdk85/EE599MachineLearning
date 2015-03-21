/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_mapping;

/**
 *
 * @author atd43
 */
@Deprecated
public class LocList {
    
    Node first;
    
    public LocList(){
        first = null;
    }
    
    //insert the location where it should go
    public void add(Location location){
        
        //check for empty
        if (first == null){
            first = new Node(location, null);
            return;
        } //end if
                
        Node node = first;
        Node prev = null;
        
        while(node != null){
            if (node.location.equals(location)){
                prev.next = new Node(new Location(location.loc, location.prob+node.location.prob), node.next);
                return;
            } //end if
            
            prev = node;
            node = node.next;
        } //end while
        
        node.next = new Node(location, null);
        first = sort();
    } //end public void add
    
    private void remove(Location location){
        Node node = first;
        Node prev = null;
        
        while(node != null){
            if (node.location.equals(location)){
                if (prev == null){
                    first = node.next;
                } else {
                    prev.next = node.next;
                    node.next = null;
                } //end else
                return;
            } //end if
            
            prev = node;
            node = node.next;
        } //end while
    } //end private void remove
    
    private Node sort(){
        Node split = null;
        Node last = null;
        
        while(first != null){
            Node node = first;
            Node max = first;
            
            while(node != null){
                if (node.location.compareTo(max.location) > 0){
                    max = node;
                }
                
                last = max;
                if (split == null) split = max;
                remove(max.location);
            } //end while
        } //end while
        
        return split;
    } //end private Node
    
    class Node{
        Location location;
        Node next;
        
        public Node(Location location, Node next){
            this.location = location;
            this.next = next;
        } //end public Node
        
        public String toString(){
            return location.toString();
        } //end public String
    } //end class Node
    
    public String toString(){
        String print = "";
        Node node = first;
        while(node != null){
            print += node.toString() + "\n";
            node = node.next;
        } //end while
        
        return print;
    } //end public String
}//end public class LocList
