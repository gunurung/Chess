package com.gunurung.chess.engine.piece;

import com.gunurung.chess.engine.board.Board;
import com.gunurung.chess.engine.board.Location;
import com.gunurung.chess.engine.board.moves.Move;
import com.gunurung.chess.engine.board.moves.Movement;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public List<Movement> getAvailableMovement(Board board) {
        List<Movement> moves = new ArrayList<>();
        if(board.isGameEnded()) return moves;
        if(board.isWhiteTurn() == isWhite()){
            Location from = board.getLocation(this);
            //비숍의 기본 움직임
            for(int i = 1; i < 8; i++){
                if(from.getRank() + i > 8 || from.getFile() + i > 8) break;
                Location offset = from.offset(i,i);
                Piece pic = board.getPiece(offset);
                if(pic == null) moves.add(new Move(from,offset));
                else if(pic.isWhite() != isWhite()){
                    moves.add(new Move(from,offset));
                    break;
                }else break;
            }
            for(int i = 1; i < 8; i++){
                if(from.getFile() + i > 8 || from.getRank() - i < 1) break;
                Location offset = from.offset(i,-i);
                Piece pic = board.getPiece(offset);
                if(pic == null) moves.add(new Move(from,offset));
                else if(pic.isWhite() != isWhite()){
                    moves.add(new Move(from,offset));
                    break;
                }else break;
            }
            for(int i = 1; i < 8; i++){
                if(from.getRank() - i < 1 || from.getFile() - i < 1) break;
                Location offset = from.offset(-i,-i);
                Piece pic = board.getPiece(offset);
                if(pic == null) moves.add(new Move(from,offset));
                else if(pic.isWhite() != isWhite()){
                    moves.add(new Move(from,offset));
                    break;
                }else break;
            }
            for(int i = 1; i < 8; i++){
                if(from.getFile() - i < 1 || from.getRank() + i > 8) break;
                Location offset = from.offset(-i,i);
                Piece pic = board.getPiece(offset);
                if(pic == null) moves.add(new Move(from,offset));
                else if(pic.isWhite() != isWhite()){
                    moves.add(new Move(from,offset));
                    break;
                }else break;
            }
            //룩의 기본 움직임
            for(int i = 1; i < 8; i++){
                if(from.getRank() + i > 8) break;
                Location offset = from.offset(0,i);
                Piece pic = board.getPiece(offset);
                if(pic == null) moves.add(new Move(from,offset));
                else if(pic.isWhite() != isWhite()){
                    moves.add(new Move(from,offset));
                    break;
                }else break;
            }
            for(int i = 1; i < 8; i++){
                if(from.getRank() - i < 1) break;
                Location offset = from.offset(0,-i);
                Piece pic = board.getPiece(offset);
                if(pic == null) moves.add(new Move(from,offset));
                else if(pic.isWhite() != isWhite()){
                    moves.add(new Move(from,offset));
                    break;
                }else break;
            }
            for(int i = 1; i < 8; i++){
                if(from.getFile() + i > 8) break;
                Location offset = from.offset(i,0);
                Piece pic = board.getPiece(offset);
                if(pic == null) moves.add(new Move(from,offset));
                else if(pic.isWhite() != isWhite()){
                    moves.add(new Move(from,offset));
                    break;
                }else break;
            }
            for(int i = 1; i < 8; i++){
                if(from.getFile() - i < 1) break;
                Location offset = from.offset(-i,0);
                Piece pic = board.getPiece(offset);
                if(pic == null) moves.add(new Move(from,offset));
                else if(pic.isWhite() != isWhite()){
                    moves.add(new Move(from,offset));
                    break;
                }else break;
            }
        }
        return moves;
    }
}
