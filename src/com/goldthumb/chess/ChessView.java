package com.goldthumb.chess;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ChessView extends JPanel implements MouseListener {

    private ChessDelegate chessDelegate;

    private double scaleFactor = 0.9;
    private int originX = -1;
    private int originY = -1;
    private int cellSide = -1;

    private Map<String, Image> keyNameValueImage = new HashMap<>();
    private int fromCol = -1;
    private int fromRow = -1;

    ChessView(ChessDelegate chessDelegate) {
        this.chessDelegate = chessDelegate;

        String[] imageNames = {
                ChessConstants.B_BISHOP,
                ChessConstants.W_BISHOP,
                ChessConstants.B_KING,
                ChessConstants.W_KING,
                ChessConstants.B_KNIGHT,
                ChessConstants.W_KNIGHT,
                ChessConstants.B_PAWN,
                ChessConstants.W_PAWN,
                ChessConstants.B_QUEEN,
                ChessConstants.W_QUEEN,
                ChessConstants.B_ROOK,
                ChessConstants.W_ROOK
        };
        try {
            for (String imgNm : imageNames) {
                Image img = loadImage(imgNm + ".png");
                keyNameValueImage.put(imgNm, img);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        addMouseListener(this);
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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        fromCol = (e.getPoint().x - originX) / cellSide;
        fromRow = (e.getPoint().y - originY) / cellSide;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int col = (e.getPoint().x - originX) / cellSide;
        int row = (e.getPoint().y - originY) / cellSide;
        System.out.println("from " + fromCol + " to " + col);
        chessDelegate.movePiece(fromCol, fromRow, col, row);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
