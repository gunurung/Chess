package com.gunurung.chess.gui;

import com.gunurung.chess.engine.board.Board;
import com.gunurung.chess.engine.board.moves.Movement;
import com.gunurung.chess.engine.player.Player;

public class GUIPlayer implements Player {
    private final boolean white;
    ChessGameFrame window;
    public GUIPlayer(boolean white, ChessGameFrame window){
        this.white = white;
        this.window = window;
    }
    private Movement decide = null;
    public synchronized boolean decide(Movement movement){
        if(decide == null){
            decide = movement;
            notifyAll();
            return true;
        }else return false;
    }
    @Override
    public synchronized Movement getMove(Board board) {

        try {
            while(decide == null) {
                wait();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Movement temp = decide;
        decide = null;
        return temp;
    }

    @Override
    public boolean isWhite() {
        return white;
    }
}
