package com.goldthumb.chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class ChessController implements ChessDelegate, ActionListener {
    private ChessModel chessModel = new ChessModel();
    private ChessView chessBoardPanel;
    private JButton resetBtn;
    private JButton serverBtn;
    private JButton clientBtn;


    public ChessController() {
        chessModel.reset();

        JFrame frame = new JFrame("Chess");
        frame.setSize(600, 600);
//        frame.setLocation(0,300);
        frame.setLayout(new BorderLayout());

        chessBoardPanel = new ChessView(this);

        frame.add(chessBoardPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        resetBtn = new JButton("Reset");
        resetBtn.addActionListener(this);
        buttonsPanel.add(resetBtn);

        serverBtn = new JButton("Listen");
        buttonsPanel.add(serverBtn);
        serverBtn.addActionListener(this);

        clientBtn = new JButton("Connect");
        buttonsPanel.add(clientBtn);
        clientBtn.addActionListener(this);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getSource());
        if (e.getSource() == resetBtn) {
            chessModel.reset();
            chessBoardPanel.repaint();
            System.out.println("reset clicked.");
        } else if (e.getSource() == serverBtn) {

            try (var listener = new ServerSocket(50000)) {
                System.out.println("server is listening to port 50000");
                while (true) {
                    try (var socket = listener.accept()) {
                        var out = new PrintWriter(socket.getOutputStream(), true);
                        out.println("from (0,1) to (0,2)");
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            System.out.println("Listen (for socket server) clicked.");
        } else if (e.getSource() == clientBtn) {
            System.out.println("Connect (for socket client) clicked.");
        }

    }
}
