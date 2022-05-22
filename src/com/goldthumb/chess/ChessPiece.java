package com.goldthumb.chess;

public class ChessPiece {
    int col;
    int row;
    public Player player;
    public Rank rank;
    String imgName;

    public ChessPiece(int col, int row, Player player, Rank rank, String imgName) {
        this.col = col;
        this.row = row;
        this.player = player;
        this.rank = rank;
        this.imgName = imgName;
    }
}
