package com.gunurung.chess.engine.board.moves;

import com.gunurung.chess.engine.board.Board;
import com.gunurung.chess.engine.board.Location;
import com.gunurung.chess.engine.piece.Piece;

public class Move implements Movement{
    Location from;
    Location to;
    public Move(Location from, Location to){
        this.from = from;
        this.to = to;
    }

    public Location getTo() {
        return to;
    }

    public Location getFrom() {
        return from;
    }

    public void move(Board board){
        Piece piece = board.getPiece(from);
        board.setPiece(to, piece);
        board.setPiece(from, null);
        piece.move();
    }

    @Override
    public Location getTo(Board board) {
        return getTo();
    }


    @Override
    public Location getSymbolLocation(Board board) {
        return getTo();
    }

    @Override
    public StringBuilder token() {
        return new StringBuilder(from.toString()).append(to.toString());
    }

    public String toString(){//(a~h)(1~8)(a~h)(1~8)
        return token().toString();
    }
}
