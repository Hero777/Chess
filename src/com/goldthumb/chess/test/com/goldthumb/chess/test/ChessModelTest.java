package com.goldthumb.chess.test;


import com.goldthumb.chess.ChessModel;

import com.goldthumb.chess.Player;
import com.goldthumb.chess.Rank;
import org.junit.Test;

import static org.junit.Assert.*;


public class ChessModelTest {

    @Test
    public void testPieceAt() {
        ChessModel chessModel = new ChessModel();
        assertNull(chessModel.pieceAt(0, 0));
        chessModel.reset();
        assertNotNull(chessModel.pieceAt(0, 0));
        assertEquals(Player.WHITE,chessModel.pieceAt(0,0).player);
        assertEquals(Rank.ROOK, chessModel.pieceAt(0,0).rank);
    }

    @Test
    public void testToString() {
        ChessModel chessModel = new ChessModel();
        assertTrue(chessModel.toString().contains("0 . . . . . . . ."));
        chessModel.reset();
        System.out.println(chessModel);
        assertTrue(chessModel.toString().contains("0 r n b q k b n r"));
    }
}