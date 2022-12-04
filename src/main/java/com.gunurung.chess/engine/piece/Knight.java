package com.gunurung.chess.engine.piece;

import com.gunurung.chess.engine.board.Board;
import com.gunurung.chess.engine.board.Location;
import com.gunurung.chess.engine.board.moves.Move;
import com.gunurung.chess.engine.board.moves.Movement;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public List<Movement> getAvailableMovement(Board board) {
        List<Movement> moves = new ArrayList<>();
        if(board.isGameEnded()) return moves;
        if (board.isWhiteTurn() == isWhite()) {
            Location from = board.getLocation(this);
            Location[] av = new Location[] {
            from.offset(1,2)
            ,from.offset(-1,2)
            ,from.offset(1,-2)
            ,from.offset(-1,-2)
            ,from.offset(2,1)
            ,from.offset(-2,1)
            ,from.offset(2,-1)
            ,from.offset(-2,-1)};
            for(int i = 0; i < av.length; i++){
                if(av[i].getFile() >= 1 && av[i].getFile() <= 8 && av[i].getRank() >= 1 && av[i].getRank() <= 8)
                    if(board.getPiece(av[i]) == null || board.getPiece(av[i]).isWhite() != isWhite())
                        moves.add(new Move(from, av[i]));
            }
        }
        return moves;
    }
}
