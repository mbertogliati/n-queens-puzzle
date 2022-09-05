package clases;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class SuperTile {
    private int tileSize;
    private Color color;
    GridBagLayout layout = new GridBagLayout();
    public SuperTile(int size_of_tile, Color selected_color){
        tileSize = size_of_tile;
        color = selected_color;
    }
    public Tile getNewTile(int i, int j,ChessBoard caller){
        Tile tile = new Tile(tileSize,color,i,j,caller);



        return tile;
    }
    public Color getColor(){
        return color;
    }
    public void setColor(Color newColor){
        color = newColor;
    }
    public int getTileSize(){
        return tileSize;
    }
}