package com.gunurung.chess.gui;

import com.gunurung.chess.engine.board.Board;
import com.gunurung.chess.engine.board.Location;
import com.gunurung.chess.engine.board.moves.Movement;
import com.gunurung.chess.engine.piece.Piece;
import com.gunurung.chess.engine.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPlayer implements Player {
    Board board;
    boolean white;
    public RandomPlayer(Board board, boolean white){
        this.board = board;
        this.white = white;
    }
    @Override
    public synchronized Movement getMove(Board board) {
        List<Movement> moves = new ArrayList<>();
        for(int x = 1; x <= 8; x++)
            for(int y = 1; y <= 8; y++) {
                Piece pic = board.getPiece(new Location(x, y));
                if (pic != null && pic.isWhite() == white)
                    moves.addAll(pic.getAvailableMovement(board));
            }
        return moves.get(new Random().nextInt(moves.size()));
    }

    @Override
    public boolean isWhite() {
        return white;
    }
}
