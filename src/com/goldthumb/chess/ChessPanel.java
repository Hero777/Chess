package com.goldthumb.chess;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ChessPanel extends JPanel {
    int originX = 50;
    int originY = 45;
    int cellSide = 60;

    Map<String, Image> keyNameValueImage = new HashMap<String, Image>();

    public ChessPanel() {
        String[] imageNames = {
                "Bishop-black",
                "Bishop-white",
                "King-black",
                "King-white",
                "Pawn-black",
                "Pawn-white",
                "Queen-black",
                "Queen-white",
                "Rook-black",
                "Rook-white"
        };
        for (String imgNm : imageNames) {
            try {
                Image img = loadImage(imgNm + ".png");
                keyNameValueImage.put(imgNm, img);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    protected void paintChildren(Graphics g) {
        super.paintChildren(g);

        Graphics2D g2 = (Graphics2D) g;

        drawBoard(g2);

        drawImage(g2, 0, 0, "Rook-black");
        drawImage(g2, 0, 1, "Pawn-black");

    }

    private void drawImage(Graphics2D g2, int col, int row, String imgName) {
        Image img = keyNameValueImage.get(imgName);
        g2.drawImage(img, originX + col * cellSide, originY + row * cellSide, cellSide , cellSide , null);
    }

    private Image loadImage(String imgFileName) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resURL = classLoader.getResource("img/" + imgFileName);
        if (resURL == null) {
            System.out.println("failed to load image");
            return null;
        } else {
            System.out.println("yeah");

            File imgFile = new File(resURL.toURI());
            return ImageIO.read(imgFile);

        }
    }

    private void drawBoard(Graphics2D g2) {
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                drawSquare(g2, 2 * i, 2 * j, true);
                drawSquare(g2, 1 + 2 * i, 1 + 2 * j, true);
            }
            for (int i = 0; i < 4; i++) {
                drawSquare(g2, 1 + 2 * i, 2 * j, false);
                drawSquare(g2, 2 * i, 1 + 2 * j, false);
            }
        }
    }

    private void drawSquare(Graphics2D g2, int col, int row, boolean isLight) {
        g2.setColor(isLight ? Color.LIGHT_GRAY : Color.DARK_GRAY);
        g2.fillRect(originX + col * cellSide, originY + row * cellSide, cellSide, cellSide);
    }

}
