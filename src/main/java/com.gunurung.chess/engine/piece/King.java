package com.gunurung.chess.engine.piece;

import com.gunurung.chess.engine.board.Board;
import com.gunurung.chess.engine.board.Location;
import com.gunurung.chess.engine.board.moves.Castling;
import com.gunurung.chess.engine.board.moves.Move;
import com.gunurung.chess.engine.board.moves.Movement;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public List<Movement> getAvailableMovement(Board board) {
        List<Movement> moves = new ArrayList<>();
        if(board.isGameEnded()) return moves;
        if(board.isWhiteTurn() == isWhite()){
            Location from = board.getLocation(this);
            if(!haveMoved()){
                //kingSide
                if(board.getPiece(from.offset(1,0)) == null && board.getPiece(from.offset(2,0)) == null){
                    Piece rook = board.getPiece(from.offset(3,0));
                    if(rook instanceof Rook && !rook.haveMoved()) moves.add(new Castling(isWhite(), true));
                }//queenSide
                else if(board.getPiece(from.offset(-1,0)) == null && board.getPiece(from.offset(-2,0)) == null&& board.getPiece(from.offset(-3,0)) == null){
                    Piece rook = board.getPiece(from.offset(-4,0));
                    if(rook instanceof Rook && !rook.haveMoved()) moves.add(new Castling(isWhite(), false));
                }
            }

            for(int f = -1; f <= 1; f++)
                for(int r = -1; r <= 1; r++){
                    if(f==0&&r==0) continue;
                    if(from.getFile() + f < 1 || from.getFile() + f > 8 || from.getRank() + r < 1 ||  from.getRank() + r >8) continue;
                    Location offset =from.offset(f,r);
                    Piece pic = board.getPiece(offset);
                    if(pic == null || pic.isWhite() != isWhite())
                        moves.add(new Move(from, offset));
                }
        }
        return moves;
    }
}
