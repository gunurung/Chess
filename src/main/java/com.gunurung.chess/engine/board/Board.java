package com.gunurung.chess.engine.board;

import com.gunurung.chess.engine.board.moves.Movement;
import com.gunurung.chess.engine.event.BoardEventListener;
import com.gunurung.chess.engine.event.TurnChangeEvent;
import com.gunurung.chess.engine.logger.ChessLogger;
import com.gunurung.chess.engine.piece.*;

import java.util.HashSet;
import java.util.Set;

public class Board {
    private boolean gameEnded = false;
    private boolean isWhiteTurn = true;
    private ChessLogger chessLogger = new ChessLogger();
    private Piece[][] board;

    private Set<BoardEventListener> boardEventListeners = new HashSet<>();
    public Board(){
        board = new Piece[8][8];
        init();
    }
    public Set<BoardEventListener> getBoardEventListeners(){
        return boardEventListeners;
    }
    public Board(Piece[][] board){
        this.board = board;
    }
    private void init(){
        for(int i = 0; i < 8; i++){
            board[i][1] = new Pawn(true);
            board[i][6] = new Pawn(false);
        }
        for(int color : new int[]{0,7}){
            board[0][color] = new Rook(color == 0);
            board[7][color] = new Rook(color == 0);
            board[1][color] = new Knight(color == 0);
            board[6][color] = new Knight(color == 0);
            board[2][color] = new Bishop(color == 0);
            board[5][color] = new Bishop(color == 0);
            board[3][color] = new Queen(color == 0);
            board[4][color] = new King(color == 0);
        }
    }
    public Location getLocation(Piece pic){
        for(int f = 0; f < 8; f++)
            for(int r = 0; r < 8; r++)
                if(board[f][r]==pic) return new Location(f+97, r+1);
        return null;
    }
    public Piece getPiece(Location loc){
        return board[loc.getFile()-1][loc.getRank()-1];
    }
    public void setPiece(Location loc, Piece pic){
        board[loc.getFile()-1][loc.getRank()-1] = pic;
    }
    public void doMove(Movement movement){
        if(gameEnded) return;
        movement.move(this);
        getLogger().moved(movement);
        boolean end = true;
        for(int x = 0; x < 8; x++)
            for(int y = 0; y < 8; y++){
                if(board[x][y] instanceof King && ((King)board[x][y]).isWhite() != isWhiteTurn())
                    end = false;
            }
        if(end)
            gameEnd(isWhiteTurn);
        else
            isWhiteTurn = !isWhiteTurn;
        TurnChangeEvent event = new TurnChangeEvent(this,getPiece(movement.getTo(this)),movement);
        boardEventListeners.stream().forEach(l->l.actionPerformed(event));
    }
    public boolean isGameEnded(){
        return gameEnded;
    }
    public void gameEnd(boolean whiteWin){
        gameEnded = true;
    }
    public boolean isWhiteTurn(){
        return isWhiteTurn;
    }
    public ChessLogger getLogger(){ return chessLogger; }
}
