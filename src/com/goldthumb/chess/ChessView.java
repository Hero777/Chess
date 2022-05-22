package com.goldthumb.chess;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ChessView extends JPanel {

    ChessDelegate chessDelegate;

    double scaleFactor = 0.9;

    int originX = -1;
    int originY = -1;
    int cellSide = -1;

    Map<String, Image> keyNameValueImage = new HashMap<>();

    public ChessView() {
        String[] imageNames = {
                "Bishop-black",
                "Bishop-white",
                "King-black",
                "King-white",
                "Knight-black",
                "Knight-white",
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

        int smaller = Math.min(getSize().width, getSize().height);
        cellSide = (int) (((double) smaller) * scaleFactor / 8);
        originX = (getSize().width - 8 * cellSide) / 2;
        originY = (getSize().height - 8 * cellSide) / 2;

        Graphics2D g2 = (Graphics2D) g;

        drawBoard(g2);
        drawPieces(g2);

    }

    private void drawPieces(Graphics2D g2) {
        for (int col = 7; col >= 0; col--) {
            for (int row = 0; row < 8; row++) {
                ChessPiece p = chessDelegate.pieceAt(col, row);
                if (p != null) {
                    drawImage(g2, col, row, p.imgName);
                }
            }
        }
//        drawImage(g2, 3, 3, "Bishop-black");
//        drawImage(g2, 4, 4, "Bishop-white");
    }

    private void drawImage(Graphics2D g2, int col, int row, String imgName) {
        Image img = keyNameValueImage.get(imgName);
        g2.drawImage(img, originX + col * cellSide, originY + row * cellSide, cellSide, cellSide, null);
    }

    private Image loadImage(String imgFileName) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resURL = classLoader.getResource("img/" + imgFileName);
        if (resURL == null) {
//            System.out.println("failed to load image");
            return null;
        } else {
//            System.out.println("yeah");

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
