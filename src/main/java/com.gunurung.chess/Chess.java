package com.gunurung.chess;

import com.gunurung.chess.engine.board.Board;
import com.gunurung.chess.engine.board.moves.Movement;
import com.gunurung.chess.engine.player.Player;

public class Chess {
    Board board;
    Player white;
    Player black;
    public Chess(Player white, Player black){
        this(new Board(),white,black);
    }
    public Chess(Board board, Player white, Player black){
        this.board = board;
        this.white = white;
        this.black = black;
    }
    public void doChess(){
        new Thread(()->{
            while (!isEnd()) {
                Movement movement = board.isWhiteTurn() ? white.getMove(board) : black.getMove(board);
                board.doMove(movement);
            }
        }).start();
    }
    public boolean isEnd(){
        return board.isGameEnded();
    }
    public Player getWhite() {
        return white;
    }

    public Player getBlack() {
        return black;
    }

    public Board getBoard() {
        return board;
    }
}
