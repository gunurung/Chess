package com.gunurung.chess.engine.board.moves;

import com.gunurung.chess.engine.board.Board;
import com.gunurung.chess.engine.board.Location;
import com.gunurung.chess.engine.logger.Tokenable;

public interface Movement extends Tokenable {
    public void move(Board board);
    public Location getTo(Board board);
    public Location getFrom();
    public Location getSymbolLocation(Board board);
}
