package clases;

import javax.swing.*;
import javax.swing.plaf.LayerUI;
import java.awt.event.MouseEvent;
import java.awt.*;

public class ChessboardLayerUI extends LayerUI<JComponent> {
    private Image image;
    private int mouseX;
    private int mouseY;
    private int tileSize;
    private int N;
    boolean canPaint = false;
    ChessBoard chessBoard;

    public ChessboardLayerUI(ChessBoard cb){
        chessBoard = cb;
        N = chessBoard.getN();
        tileSize = chessBoard.getTileSize();
        image = chessBoard.getImage();
    }
    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        JLayer jlayer = (JLayer)c;
        jlayer.setLayerEventMask(
                AWTEvent.MOUSE_EVENT_MASK |
                        AWTEvent.MOUSE_MOTION_EVENT_MASK
        );
    }

    @Override
    public void uninstallUI(JComponent c) {
        JLayer jlayer = (JLayer)c;
        jlayer.setLayerEventMask(0);
        super.uninstallUI(c);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, .9f));

        if(canPaint && chessBoard.getQueenAmount() < N){
            g2.drawImage(image, mouseX - tileSize / 2, mouseY - tileSize / 2, tileSize, tileSize, null);
        }

        g2.dispose();
    }
    @Override
    protected void processMouseEvent(MouseEvent e, JLayer l) {
        if (e.getID() == MouseEvent.MOUSE_PRESSED && SwingUtilities.isLeftMouseButton(e)){
            //System.out.println("Pressed!!");
            canPaint = true;
        }
        if (e.getID() == MouseEvent.MOUSE_RELEASED && SwingUtilities.isLeftMouseButton(e)) {
            //System.out.println("Released!!");
            canPaint = false;
        }
        l.repaint();
    }

    @Override
    protected void processMouseMotionEvent(MouseEvent e, JLayer l) {
        Point p = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), l);
        mouseX = p.x;
        mouseY = p.y;
        l.repaint();
    }
    public void setImage(Image img){
        image = img;
    }
    public void setTileSize(int size){
        tileSize = size;
    }

}
