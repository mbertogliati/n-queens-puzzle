package clases;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Queens extends JLabel {

    private int tileSize ;
    private int N;
    private Queen[] queenArray;

    Image queenImage;

    public Queens(int boardSize, int selectedTileSize){
        tileSize = selectedTileSize;
        N = boardSize;
        queenArray = new Queen[N];

        BufferedImage imagen = null;
        try{
            imagen = ImageIO.read(new File("src\\main\\resources\\img\\white_queen.png"));
        }
        catch (IOException excp){
            excp.printStackTrace();
        }
        assert imagen != null;

        queenImage = imagen.getScaledInstance(tileSize,tileSize,Image.SCALE_SMOOTH);

        createQueenArray();
    }
    private Queen getNewQueen(int i){

        Queen queen = new Queen(tileSize, queenImage, i);

        return queen;

    }
    private void createQueenArray(){
        for(int i=0;i<N;i++){
            queenArray[i] = getNewQueen(i);
        }
    }
    public Queen getQueen(int i){
        return queenArray[i];
    }
    public Queen[] getQueenArray(){
        return queenArray;
    }
    public Image getImage(){
        return queenImage;
    }

}
