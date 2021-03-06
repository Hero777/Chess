package com.goldthumb.chess;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

public class ChessModel extends JPanel implements MouseListener {
    private Set<ChessPiece> piecesBox = new HashSet<>();
    private Player playerInTurn = Player.WHITE;

    public void reset() {
        piecesBox.removeAll(piecesBox);

        for (int i = 0; i < 2; i++) {
            piecesBox.add(new ChessPiece(i * 7, 7, Player.BLACK, Rank.ROOK, ChessConstants.B_ROOK));
            piecesBox.add(new ChessPiece(i * 7, 0, Player.WHITE, Rank.ROOK, ChessConstants.W_ROOK));

            piecesBox.add(new ChessPiece(1 + i * 5, 7, Player.BLACK, Rank.KNIGHT, ChessConstants.B_KNIGHT));
            piecesBox.add(new ChessPiece(1 + i * 5, 0, Player.WHITE, Rank.KNIGHT, ChessConstants.W_KNIGHT));

            piecesBox.add(new ChessPiece(2 + i * 3, 7, Player.BLACK, Rank.BISHOP, ChessConstants.B_BISHOP));
            piecesBox.add(new ChessPiece(2 + i * 3, 0, Player.WHITE, Rank.BISHOP, ChessConstants.W_BISHOP));
        }
        for (int i = 0; i < 8; i++) {
            piecesBox.add(new ChessPiece(i, 6, Player.BLACK, Rank.PAWN, ChessConstants.B_PAWN));
            piecesBox.add(new ChessPiece(i, 1, Player.WHITE, Rank.PAWN, ChessConstants.W_PAWN));

        }
        piecesBox.add(new ChessPiece(3, 7, Player.BLACK, Rank.QUEEN, ChessConstants.B_QUEEN));
        piecesBox.add(new ChessPiece(3, 0, Player.WHITE, Rank.QUEEN, ChessConstants.W_QUEEN));
        piecesBox.add(new ChessPiece(4, 7, Player.BLACK, Rank.KING, ChessConstants.B_KING));
        piecesBox.add(new ChessPiece(4, 0, Player.WHITE, Rank.KING, ChessConstants.W_KING));

        playerInTurn = Player.WHITE;
    }

    public void movePiece(int fromCol, int fromRow, int toCol, int toRow) {
        ChessPiece candidate = pieceAt(fromCol, fromRow);
        if (candidate == null || candidate.player != playerInTurn) {
            return;
        }

        ChessPiece target = pieceAt(toCol, toRow);
        if (target != null) {
            if (target.player == candidate.player) {
                return;
            } else {
                piecesBox.remove(target);
            }
        }

        candidate.col = toCol;
        candidate.row = toRow;
        playerInTurn = playerInTurn == Player.WHITE ? Player.BLACK : Player.WHITE;

        System.out.println(piecesBox.size());
    }

    public ChessPiece pieceAt(int col, int row) {
        for (ChessPiece chessPiece : piecesBox) {
            if (chessPiece.col == col && chessPiece.row == row) {
                return chessPiece;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String desc = "";

        for (int row = 7; row >= 0; row--) {
            desc += "" + row;
            for (int col = 0; col < 8; col++) {
                ChessPiece p = pieceAt(col, row);
                if (p == null) {
                    desc += " .";
                } else {
                    desc += " ";
                    switch (p.rank) {
                        case KING:
                            desc += p.player == Player.WHITE ? "k" : "K";
                            break;
                        case QUEEN:
                            desc += p.player == Player.WHITE ? "q" : "Q";
                            break;
                        case BISHOP:
                            desc += p.player == Player.WHITE ? "b" : "B";
                            break;
                        case ROOK:
                            desc += p.player == Player.WHITE ? "r" : "R";
                            break;
                        case KNIGHT:
                            desc += p.player == Player.WHITE ? "n" : "N";
                            break;
                        case PAWN:
                            desc += p.player == Player.WHITE ? "p" : "P";
                            break;
                    }
                }
            }
            desc += "\n";
        }
        desc += "  0 1 2 3 4 5 6 7";

        return desc;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        System.out.println("from " + mouseEvent.getLocationOnScreen());
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        System.out.println("to " + mouseEvent.getLocationOnScreen());
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
