package com.gunurung.chess.engine.event;

import com.gunurung.chess.engine.board.Board;
import com.gunurung.chess.engine.board.Location;
import com.gunurung.chess.engine.board.moves.Movement;
import com.gunurung.chess.engine.piece.Piece;

public class TurnChangeEvent extends BoardEvent{
    public TurnChangeEvent(Board board, Piece moved, Movement movement){
        super(board);
        this.moved= moved;
        this.movement = movement;
    }
    final Piece moved;
    final Movement movement;
    public Piece getMovedPiece(){
        return moved;
    }
    public Location getFrom(){return movement.getFrom();}
    public Location getTo(){return movement.getTo(board);}
    public Location getSymbolicLocation(){return movement.getSymbolLocation(board);}
    public Movement getMovement(){return movement;}
}
