package com.gunurung.chess;

import com.gunurung.chess.engine.board.Board;
import com.gunurung.chess.engine.piece.Bishop;
import com.gunurung.chess.gui.ChessGameFrame;
import com.gunurung.chess.gui.GUIPlayer;
import com.gunurung.chess.gui.RandomPlayer;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        ChessGameFrame fr = new ChessGameFrame(false);
        GUIPlayer white = new GUIPlayer(true,fr);
        RandomPlayer black = new RandomPlayer(board,false);
        Chess chess = new Chess(board,white,black);
        fr.init(chess);
        chess.doChess();
    }
    public static Bishop bishop(int i){
        if(i == 1)
        return null;
        else return new Bishop(false);
    }
}