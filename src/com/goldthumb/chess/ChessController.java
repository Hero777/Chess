package com.goldthumb.chess;

import javax.swing.*;
import java.awt.*;

public class ChessController implements ChessDelegate {
    private ChessModel chessModel = new ChessModel();
    private ChessView centerPanel;

    public ChessController() {
        chessModel.reset();

        JFrame frame = new JFrame("Chess");
        frame.setSize(600, 600);
//        frame.setLocation(0,300);
        frame.setLayout(new BorderLayout());

        centerPanel = new ChessView(this);

        frame.add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton resetBtn = new JButton("Reset");
        southPanel.add(resetBtn);
        southPanel.add(new JButton("Delete Me"));
        frame.add(southPanel, BorderLayout.PAGE_END);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new ChessController();

    }

    @Override
    public ChessPiece pieceAt(int col, int row) {

        return chessModel.pieceAt(col, row);
    }

    @Override
    public void movePiece(int fromCol, int fromRow, int toCol, int toRow) {
        chessModel.movePiece(fromCol, fromRow, toCol, toRow);
        centerPanel.repaint();
    }
}
