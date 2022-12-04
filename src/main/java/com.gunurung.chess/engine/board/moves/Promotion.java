package com.gunurung.chess.engine.board.moves;

import com.gunurung.chess.engine.board.Board;
import com.gunurung.chess.engine.board.Location;
import com.gunurung.chess.engine.piece.*;

public class Promotion implements Movement{
    PromotionType type;
    Location from;
    Location to;
    public Promotion(Location from, Location to, PromotionType type) {
        this.from = from;
        this.to = to;
        this.type = type;
    }

    public String toString(){//Y(a~h)(1~8)(I~L)
        return token().toString();
    }

    @Override
    public void move(Board board) {
        Piece pawn = (Piece) board.getPiece(from);
        boolean isWhite = pawn.isWhite();
        Piece promoted;
        switch (type){
            case QUEEN -> promoted = new Queen(isWhite);
            case BISHOP ->  promoted = new Bishop(isWhite);
            case ROOK -> promoted = new Rook(isWhite);
            case KNIGHT -> promoted = new Knight(isWhite);
            default -> promoted = pawn;
        }
        promoted.move();
        board.setPiece(from,null);
        board.setPiece(to,promoted);
    }

    @Override
    public Location getTo(Board board) {
        return to;
    }

    @Override
    public Location getFrom() {
        return from;
    }

    @Override
    public Location getSymbolLocation(Board board) {
        return to;
    }

    @Override
    public StringBuilder token() {
        return new StringBuilder('Y').append(from.toString()).append(type.getCode());
    }

    public enum PromotionType{
        QUEEN('I'),KNIGHT('J'),ROOK('K'),BISHOP('L');
        private char c;
        private PromotionType(char c){
            this.c = c;
        }
        public char getCode(){
            return c;
        }
    }
}
