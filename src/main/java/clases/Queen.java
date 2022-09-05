package clases;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Queen extends JLabel {

    private int tileSize;
    private boolean isHover = false;

    private int id;

    private int clicks = 0;
    private Image queen_image;
    private int alpha;
    public Queen(int selectedTileSize, Image image, int i){
        super(new ImageIcon(image));
        tileSize = selectedTileSize;
        queen_image = image;
        this.setPreferredSize(new Dimension(tileSize,tileSize));
        id = i;

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(isHover) {
            g.setColor(new Color(255,255,255,alpha));
            g.fillRect(0,0,tileSize,tileSize);
            Cursor cursor = Cursor.getDefaultCursor();
            //change cursor appearance to HAND_CURSOR when the mouse pointed on images
            cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
            setCursor(cursor);
        }
    }
    public boolean getHover(){
        return isHover;
    }
    public void setHover(boolean bool){
        isHover = bool;
    }
    public int getAlpha(){
        return alpha;
    }
    public void setAlpha(int newAlpha){
        alpha = newAlpha;
    }
    public void addClick(){
        clicks++;
    }
    public void removeClick(){
        clicks--;
    }
    public void setClicks(int clicksAmount){
        clicks = clicksAmount;
    }
    public int getClicks(){
        return clicks;
    }
    public int getID(){
        return id;
    }
}
