package com.goldthumb.chess;

import javax.swing.*;

public class Chess {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess");
        frame.setSize(600, 600);
//        frame.setLocation(0,300);
        ChessPanel panel = new ChessPanel();
        frame.add(panel);
        frame.setVisible(true);
    }
}
