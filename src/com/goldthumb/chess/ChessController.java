package com.goldthumb.chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessController implements ChessDelegate {
    private ChessModel chessModel = new ChessModel();
    private ChessView chessBoardPanel;

    public ChessController() {
        chessModel.reset();

        JFrame frame = new JFrame("Chess");
        frame.setSize(600, 600);
//        frame.setLocation(0,300);
        frame.setLayout(new BorderLayout());

        chessBoardPanel = new ChessView(this);

        frame.add(chessBoardPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton resetBtn = new JButton("Reset");
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                chessModel.reset();
                chessBoardPanel.repaint();
            }
        });

        buttonsPanel.add(resetBtn);
        buttonsPanel.add(new JButton("Delete Me"));
        frame.add(buttonsPanel, BorderLayout.PAGE_END);

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
        chessBoardPanel.repaint();
    }
}
