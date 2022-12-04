package com.gunurung.chess.engine.logger;

import com.gunurung.chess.engine.board.moves.Movement;

import java.util.ArrayList;
import java.util.List;

public class ChessLogger {
    List<Movement> moves = new ArrayList<>();
    public Movement getLastMove(){
        return moves.get(moves.size()-1);
    }
    public void moved(Movement mov){
        moves.add(mov);
    }
}
