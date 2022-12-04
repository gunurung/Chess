package com.gunurung.chess.engine.piece;

import com.gunurung.chess.engine.board.Board;
import com.gunurung.chess.engine.board.moves.Movement;

import java.util.List;

public abstract class Piece {
    final boolean isWhite;
    boolean haveMoved = false;
    public Piece(boolean isWhite){
        this.isWhite = isWhite;
    }
    public boolean isWhite(){
        return isWhite;
    }
    public boolean haveMoved(){
        return haveMoved;
    }
    public void move(){
        haveMoved = true;
    }
    public abstract List<Movement> getAvailableMovement(Board board);
}
