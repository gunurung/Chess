package com.gunurung.chess.engine.board.moves;

import com.gunurung.chess.engine.board.Board;
import com.gunurung.chess.engine.board.Location;

public class Castling implements Movement {
    boolean white;
    boolean kingSide;
    public Castling(boolean white, boolean kingSide){
        this.white = white;
        this.kingSide = kingSide;
    }
    public String toString(){//(U~X)
        return token().toString();
    }

    @Override
    public void move(Board board) {
        Location kingFrom;
        Location rookFrom;
        Location kingTo;
        Location rookTo;
        int r = white ? 1 : 8;
        kingFrom=new Location('e',r);
        if(kingSide) {
            rookFrom = new Location('h',r);
            kingTo = new Location('g',r);
            rookTo = new Location('f',r);
        }
        else{
            rookFrom = new Location('a',r);
            kingTo = new Location('c',r);
            rookTo = new Location('d',r);
        }
        board.getPiece(kingFrom).move();
        board.getPiece(rookFrom).move();
        board.setPiece(kingTo, board.getPiece(kingFrom));
        board.setPiece(rookTo, board.getPiece(rookFrom));
        board.setPiece(kingFrom,null);
        board.setPiece(rookFrom, null);
    }

    @Override
    public Location getTo(Board board) {
        Location kingTo;
        int r = white ? 1 : 8;
        if(kingSide) {
            kingTo = new Location('g',r);
        }
        else{
            kingTo = new Location('c',r);
        }
        return kingTo;
    }

    @Override
    public Location getFrom() {
        Location kingFrom;
        int r = white ? 1 : 8;
        kingFrom=new Location('e',r);
        return kingFrom;
    }

    @Override
    public Location getSymbolLocation(Board board) {
        return getTo(board);
    }

    @Override
    public StringBuilder token() {
        if(white && kingSide)//white-kingSide
            return new StringBuilder('U');
        else if(white)//white-queenSide
            return new StringBuilder('V');
        else if(kingSide)//black-kingSide
            return new StringBuilder('W');
        else return new StringBuilder('X');
    }
}
