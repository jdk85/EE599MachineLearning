/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_mapping;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author atd43
 */
public class RoomLookUpMap {
    BufferedImage map;
    
    public RoomLookUpMap(String filename) throws IOException{
        map = ImageIO.read(new File(filename));
    }
    
    public int getColorCode(int x, int y){
        int[] color = new int[3];
        map.getData().getPixel(x, y, color);
        return color[0];
    }
}
