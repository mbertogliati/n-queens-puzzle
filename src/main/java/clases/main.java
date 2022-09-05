package clases;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.plaf.LayerUI;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("N-Queens");
        //window.setSize(1024,1024);

        int N = 8;
        int tileSize = 64;

        MainPanel mainPanel = new MainPanel(N,tileSize);

        window.add(mainPanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
