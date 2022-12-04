package com.gunurung.chess.engine.player;

import com.gunurung.chess.engine.board.Board;
import com.gunurung.chess.engine.board.moves.Movement;

public interface Player {
    public Movement getMove(Board board);
    public boolean isWhite();
}
