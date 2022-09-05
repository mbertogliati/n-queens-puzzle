package clases;
import com.sun.tools.javac.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.Writer;
import java.text.AttributedCharacterIterator;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MouseInputAdapter;
import javax.swing.plaf.LayerUI;

public class MainPanel extends JPanel{
    private int N;
    private int tileSize;
    private ChessBoard chessBoard;

    public MainPanel(int boardSize, int selectedTileSize){
        N = boardSize;
        tileSize = selectedTileSize;
        setLayout(new BorderLayout());

        JLayer chessBoardLayer = createChessboard();
        JPanel topPanel = createTopPanel();

        add(chessBoardLayer, BorderLayout.LINE_END);

        Button boton = new Button("Print Solution");
        boton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                System.out.println(chessBoard.getSolution());

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        add(boton, BorderLayout.CENTER);
        add(topPanel, BorderLayout.PAGE_START);

    }
    private JLayer createChessboard(){
        chessBoard = new ChessBoard(N, tileSize);


        SuperTile blackTile = new SuperTile(tileSize,Color.decode("#EEEED2"));
        SuperTile whiteTile = new SuperTile(tileSize,Color.decode("#769656"));

        chessBoard.paintChessboard(blackTile,whiteTile);
        LayerUI<JComponent> layerUI = chessBoard.getChessboardLayer();
        JLayer<JComponent> mainBoardLayer = new JLayer<JComponent>(chessBoard, layerUI);

        return mainBoardLayer;

    }
    private JPanel createTopPanel(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(tileSize*N,150));
        JTextField solutionField = new JTextField();
        solutionField.setPreferredSize(new Dimension(100,30));

        solutionField.setText("Holiii");
        solutionField.setEditable(true);


        GroupLayout layout = new GroupLayout(panel);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(solutionField)
        );

        return panel;

    }
}
