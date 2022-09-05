package clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class ChessBoard extends JPanel {

    private int N;
    private int tileSize;

    private Tile[][] tileMatrix;
    private Queen[] queenArray;
    private Boolean[] availableQueen;
    private Boolean[][] blockedTiles;

    private Integer[] solution;

    private Tile lastTile = null;
    private int placedQueens = 0;

    private Queens queenGenerator;
    private GridLayout defaultLayout;

    public ChessBoard(int boardSize, int selectedTileSize){
        N = boardSize;
        tileSize = selectedTileSize;
        defaultLayout = new GridLayout(N,N);
        final int screenWidth = N * tileSize;
        final int screenHeight = N * tileSize;

        tileMatrix = new Tile[N][N];
        blockedTiles = new Boolean[N][N];
        availableQueen = new Boolean[N];
        solution = new Integer[N];

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setOpaque(true);
        this.isDoubleBuffered();
        this.setLayout(defaultLayout);

        queenGenerator = new Queens(N, tileSize);
        queenArray = queenGenerator.getQueenArray();

        for(int i=0;i<N;i++){
            solution[i] = null;
            availableQueen[i] = true;
            for(int j=0;j<N;j++){
                blockedTiles[i][j] = false;
            }
        }
    }
    public Tile getTile(int x, int y){
        return tileMatrix[x][y];
    }
    public void setTileColor(int x, int y, Color newColor){
        tileMatrix[x][y].setBackground(newColor);
    }
    public Queen getNextQueen(){
        if(placedQueens<N){
            for(int i=0;i<N;i++){
                if (availableQueen[i] == true){
                    availableQueen[i] = false;
                    placedQueens++;
                    return queenArray[i];
                }
            }
        }

        return null;
    }
    public int getQueenAmount(){
        return placedQueens;
    }
    public void removeQueen(int i){
        placedQueens--;
        availableQueen[i] = true;
    }
    public void setLastTile(Tile last){
        lastTile = last;
    }
    public Tile getLastTile(){
        return lastTile;
    }
    public void repaintBlockedTiles(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if (blockedTiles[i][j])
                    paintTiles(i,j,true);
            }
        }
    }
    public void paintTiles(int x, int y,boolean isBlocking){
        Color newColor;
        Color currentColor;


        if(isBlocking){
            blockedTiles[x][y] = true;
            solution[x] = y;
        }
        else{
            blockedTiles[x][y] = false;
            solution[x] = null;
        }



        for(int j=0;j<N;j++){
            for(int i=0;i<N;i++){

                if(i == x || j == y || i+j == x+y || i-j == x-y){

                    if( isBlocking && !tileMatrix[i][j].isBlocked() )
                        tileMatrix[i][j].block();

                    else if(!isBlocking && tileMatrix[i][j].isBlocked())
                        tileMatrix[i][j].unBlock();

                }
            }
        }
    }
    public void paintChessboard(SuperTile whiteTile, SuperTile blackTile){
        boolean white = true;
        for(int y=0;y<N;y++){
            for(int x=0;x<N;x++){
                if(white){
                    tileMatrix[x][y] = whiteTile.getNewTile(x,y,this);
                }
                else{
                    tileMatrix[x][y] = blackTile.getNewTile(x,y,this);
                }
                tileMatrix[x][y].setQueenImage(queenGenerator.queenImage);
                this.add(tileMatrix[x][y]);
                white = !white;
            }
            if(N % 2 == 0) {
                white = !white;
            }
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    public ChessboardLayerUI getChessboardLayer(){
        ChessboardLayerUI cbl = new ChessboardLayerUI(this);
        return cbl;
    }
    public int getTileSize(){
        return tileSize;
    }
    public int getN(){
        return N;
    }
    public Image getImage(){
        return queenGenerator.getImage();
    }
    //public int calculateNumberOfSolutions(){


    public void setSolution(String stringSolution) {
        String[] stringArray = stringSolution.split(" ", N + 1);
        for (int i = 0; i < N; i++) {
            solution[i] = Integer.parseInt(stringArray[i]);
        }
    }
    public boolean isSolved(){
        return placedQueens == N;
    }
    public String getSolution(){
        if(!isSolved()) return "The puzzle is not solved";
        String currentSolution = "";
        for(int i=0;i<N;i++){
            if(i != 0){
                currentSolution = currentSolution.concat(" ");
            }
            currentSolution = currentSolution.concat(solution[i].toString());
        }
        return currentSolution;
    }
}
