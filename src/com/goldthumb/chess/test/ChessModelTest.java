package com.goldthumb.chess.test;


import com.goldthumb.chess.ChessModel;
import org.junit.Test;

public class ChessModelTest {
    @Test
    public void testToString() {
        ChessModel chessModel = new ChessModel();
        System.out.println(chessModel);
    }
}