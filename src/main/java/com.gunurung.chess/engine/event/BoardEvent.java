package com.gunurung.chess.engine.event;

import com.gunurung.chess.engine.board.Board;

public abstract class BoardEvent {
    final Board board;
    public BoardEvent(Board board){
        this.board = board;
    }
    public Board getBoard() {
        return board;
    }
}
