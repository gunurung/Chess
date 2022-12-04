package com.gunurung.chess.engine.board.moves;

import com.gunurung.chess.engine.board.Board;
import com.gunurung.chess.engine.board.Location;
import com.gunurung.chess.engine.piece.Piece;

public class Enpassant implements Movement{
    Location from;
    Location kill;
    public Enpassant(Location from, Location kill){
        this.from = from;
        this.kill = kill;
    }
    public String toString(){//Z(a~h)(1~8)(a~h)(1~8)
        return token().toString(); //Z(움직일기물)(앙파상 대상이될 상대기물)
    }

    @Override
    public void move(Board board) {
        board.setPiece(kill,null);
        Piece toMove = board.getPiece(from);
        Location to = new Location(kill.getFile(),toMove.isWhite()?6:3);
        toMove.move();
        board.setPiece(from,null);
        board.setPiece(to,toMove);
    }

    @Override
    public Location getTo(Board board) {
        return new Location(kill.getFile(),board.isWhiteTurn()?6:3);
    }

    @Override
    public Location getFrom() {
        return from;
    }

    @Override
    public Location getSymbolLocation(Board board) {
        return kill;
    }

    @Override
    public StringBuilder token() {
        return new StringBuilder('Z').append(from.toString()).append(kill.toString());
    }
}