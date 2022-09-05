package clases;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

public class Tile extends JPanel{

    private boolean isHover = false;
    private boolean isDragged = false;

    private int tileSize;
    private Color color;
    private Color originalColor;

    private Image queenImage;

    private int row;
    private int column;



    private boolean isBlocked = false;

    Queen queen = null;

    ChessBoard parent;


    private int alpha = 60;
    OverlayLayout layout = new OverlayLayout(this);

    public Tile(int size_of_tile, Color selected_color, int i, int j,ChessBoard caller){
        tileSize = size_of_tile;
        color = selected_color;
        originalColor = selected_color;
        parent = caller;
        row = i;
        column = j;

        this.setPreferredSize(new Dimension(tileSize,tileSize));
        this.setBackground(color);
        this.setOpaque(true);
        this.isDoubleBuffered();
        this.setLayout(layout);

        MouseInputAdapter mouseListener = new MouseInputAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //isDragged = true;
                //mouseX = e.getX() - tileSize / 2;
                //mouseY = e.getY() - tileSize / 2;
                //repaint();

            }

            @Override
            public void mousePressed(MouseEvent e) {

                removeQueen();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isDragged = false;
                if(SwingUtilities.isLeftMouseButton(e)) {

                    Tile lastTile = parent.getLastTile();
                    if (lastTile.getQueen() == null && !lastTile.isBlocked()) {
                        lastTile.addQueen(parent.getNextQueen());
                    } else  if (!isBlocked())
                    {
                        addQueen(parent.getNextQueen());
                    }


                    revalidate();
                    repaint();

                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setHover(true);
                //System.out.println("("+row+","+column+") Entered!!");
                updateLastTile();
                //revalidate();
                repaint();

            }

            @Override
            public void mouseExited(MouseEvent e) {
                //System.out.println("("+row+","+column+") Exited!!");
                setHover(false);
                //revalidate();
                repaint();

            }
        };


        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);


    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        queen = getQueen();

        if(isHover) {
            g.setColor(new Color(255,255,255,alpha));
            if(queen != null){
                queen.setHover(true);
                queen.setAlpha(alpha);
                //queen.revalidate();
                //queen.repaint();
            }
        }
        else {
            g.setColor(color);
            if(queen != null){
                queen.setHover(false);
                //queen.revalidate();
                //queen.repaint();
            }

        }

        g.fillRect(0,0,tileSize,tileSize);

    }
    public void setColor(Color newColor){
        color = newColor;
        setBackground(color);
        revalidate();
        repaint();;
    }
    public Color getColor(){
        return color;
    }
    public boolean getHover(){
        return isHover;
    }
    public void setHover(boolean bool){
        isHover = bool;
    }
    public Queen getQueen(){
        Queen queen;
        try{
            queen = (Queen) getComponent(0);
        }
        catch(ArrayIndexOutOfBoundsException excp){
            queen = null;
        }
        return queen;
    }
    public int getAlpha(){
        return alpha;
    }
    public void setAlpha(int newAlpha){
        alpha = newAlpha;
    }
    public void addQueen(Queen newQueen){

        queen = newQueen;
        if(queen != null) {
            add(queen);
            parent.paintTiles(row,column,true);
            //revalidate();
            repaint();

        }
    }
    public void removeQueen(){
        if(queen != null){
            parent.paintTiles(row,column,false);
            parent.repaintBlockedTiles();
            parent.removeQueen(queen.getID());
            remove(queen);
            queen = null;
            revalidate();
            repaint();
        }
    }
    private void updateLastTile(){
        parent.setLastTile(this);
    }
    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }
    public void block(){
        isBlocked = true;
        int R = originalColor.getRed();
        int G = originalColor.getGreen();
        int B = originalColor.getBlue();
        float average = (G+B)/2.0f;
        int newG =  (int) Math.round(G*(average/255)*0.8);
        int newB =  (int) Math.round(B*(average/255)*0.9);

        float[] HSV;
        HSV = Color.RGBtoHSB(R,G,B, null);

        color = Color.getHSBColor(HSV[0], 0, HSV[2]*0.8f);
        setBackground(color);
        revalidate();
        repaint();
    }
    public void unBlock(){
        isBlocked = false;
        color = originalColor;
        setBackground(color);
        revalidate();
        repaint();
    }
    public boolean isBlocked(){
        return isBlocked;
    }
    public void setQueenImage(Image imagen){
        queenImage = imagen;
    }
    public Image getImage(){
        return queenImage;
    }
}
