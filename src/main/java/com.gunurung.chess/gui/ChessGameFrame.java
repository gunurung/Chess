package com.gunurung.chess.gui;

import com.gunurung.chess.Chess;
import com.gunurung.chess.engine.board.Location;
import com.gunurung.chess.engine.board.moves.Movement;
import com.gunurung.chess.engine.board.moves.Promotion;
import com.gunurung.chess.engine.event.BoardEvent;
import com.gunurung.chess.engine.event.BoardEventListener;

import com.gunurung.chess.engine.piece.Bishop;
import com.gunurung.chess.engine.piece.King;
import com.gunurung.chess.engine.piece.Knight;
import com.gunurung.chess.engine.piece.Pawn;
import com.gunurung.chess.engine.piece.Piece;
import com.gunurung.chess.engine.piece.Queen;
import com.gunurung.chess.engine.player.Player;

import javax.swing.*;
import java.awt.*;

public class ChessGameFrame extends JFrame implements BoardEventListener {

    static ImageIcon i_white_bishop = new ImageIcon("./icons/bsw.png");
    static ImageIcon i_white_queen = new ImageIcon("./icons/qw.png");
    static ImageIcon i_white_rook = new ImageIcon("./icons/rw.png");
    static ImageIcon i_white_knight = new ImageIcon("./icons/knw.png");
    static ImageIcon i_white_pawn = new ImageIcon("./icons/pww.png");
    static ImageIcon i_white_king = new ImageIcon("./icons/kw.png");
    static ImageIcon i_black_bishop = new ImageIcon("./icons/bsb.png");
    static ImageIcon i_black_queen = new ImageIcon("./icons/qb.png");
    static ImageIcon i_black_rook = new ImageIcon("./icons/rb.png");
    static ImageIcon i_black_knight = new ImageIcon("./icons/knb.png");
    static ImageIcon i_black_pawn = new ImageIcon("./icons/pwb.png");
    static ImageIcon i_black_king = new ImageIcon("./icons/kb.png");
    private Chess chess;
    private final boolean whiteView;
    private JButton[][] buttons;
    private Location prevSel =  null;
    private Location selLoc =  null;

    public ChessGameFrame(boolean whiteView){
        this.whiteView = whiteView;

    }
    public void init(Chess chess){
        this.chess = chess;
        chess.getBoard().getBoardEventListeners().add(this);
        frameSetting();
        setButtons();
    }
    public void frameSetting(){
        buttons = new JButton[8][8];
        for(int i = 0; i < 8; i++)  for(int j = 0; j < 8; j++) {
            buttons[i][j]=new JButton();
            int finalI = i;
            int finalJ = j;
            buttons[i][j].addActionListener(event -> {
                selLoc = new Location(finalJ +1,whiteView?8- finalI : finalI +1);
                onClick();
            });
            add(buttons[i][j]);
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        setBounds(100,100,800,800);
        setLayout(new GridLayout(8,8));
        setVisible(true);
    }
    public void setButtons(){
        for(int r = 1; r <= 8; r++)
            for(int f = 1; f <= 8; f++){
                Piece pic = chess.getBoard().getPiece(new Location(f,r));
                JButton btn = getButton(f,r);
                btn.setIcon(getIconImage(pic));
                btn.setBackground(getBoardColor(f,r));
            }
        Piece sel = getSel();
        if(sel != null) {
            if(sel.isWhite() == chess.getBoard().isWhiteTurn()) {
                prevSel = selLoc;
                selLoc = null;
                getButton(prevSel).setBackground(new Color(187, 202, 68));
                for(Movement movement : sel.getAvailableMovement(chess.getBoard())) {
                    if (chess.getBoard().getPiece(movement.getSymbolLocation(chess.getBoard())) != null)
                        getButton(movement.getSymbolLocation(chess.getBoard())).setBackground(new Color(241, 140, 130));
                    else
                        getButton(movement.getSymbolLocation(chess.getBoard())).setBackground(new Color(246, 246, 129));
                }
            }
        }
    }
    private void doPromotion(Location fr, Location to, Promotion.PromotionType type){
        Player chessPlayer = chess.getBoard().isWhiteTurn() ? chess.getWhite() : chess.getBlack();
        ((GUIPlayer)chessPlayer).decide(new Promotion(fr, to, type));
    }
    public void onClick() {
        Player chessPlayer = chess.getBoard().isWhiteTurn() ? chess.getWhite() : chess.getBlack();

        if(chess.isEnd()) {
            selLoc = null;
            prevSel = null;
        } else if(!(chessPlayer instanceof GUIPlayer)||!((GUIPlayer)chessPlayer).window.equals(this)){
            selLoc = null;
            prevSel = null;
        }
        else if(prevSel != null){
            Piece sel = chess.getBoard().getPiece(prevSel);
            for(Movement movement : sel.getAvailableMovement(chess.getBoard()))
                if(movement.getSymbolLocation(chess.getBoard()).equals(selLoc)) {
                    if(movement instanceof Promotion){
                        JPopupMenu popup = new JPopupMenu("Promotion");
                        final Location finalPrevSel = prevSel;
                        final Location finalSelLoc = selLoc;
                        JMenuItem queen = new JMenuItem("퀸(Queen)");
                        queen.addActionListener(event -> doPromotion(finalPrevSel, finalSelLoc,Promotion.PromotionType.QUEEN));
                        JMenuItem knight = new JMenuItem("나이트(Knight)");
                        knight.addActionListener(event -> doPromotion(finalPrevSel, finalSelLoc,Promotion.PromotionType.KNIGHT));
                        JMenuItem bishop = new JMenuItem("비숍(Bishop)");
                        bishop.addActionListener(event -> doPromotion(finalPrevSel,finalSelLoc, Promotion.PromotionType.BISHOP));
                        JMenuItem rook = new JMenuItem("룩(Rook)");
                        rook.addActionListener(event -> doPromotion(finalPrevSel,finalSelLoc, Promotion.PromotionType.ROOK));
                        popup.add(queen);
                        popup.add(knight);
                        popup.addSeparator();
                        popup.add(bishop);
                        popup.add(rook);
                        popup.show(this,getButton(selLoc).getX(),getButton(selLoc).getY());
                    }else{
                        ((GUIPlayer)chessPlayer).decide(movement);
                    }
                }
            prevSel = null;
            selLoc = null;
        }
        setButtons();
    }
    public JButton getButton(int file, int rank){
        return buttons[whiteView ? 8-rank : rank-1][file-1];
    }
    public JButton getButton(Location loc){
        return getButton(loc.getFile(),loc.getRank());
    }
    public Color getBoardColor(int file, int rank){
        rank = rank-1;
        file = file-1;
        if(rank % 2 == file % 2) return new Color(153,123,112);
        else return new Color(243,225,224);
    }
    public Piece getSel(){
        if(selLoc != null) return chess.getBoard().getPiece(selLoc);
        return null;
    }
    public ImageIcon getIconImage(Piece pic){
        if(pic == null)
            return null;
        else if(pic.isWhite()){
            if(pic instanceof Pawn)
                return i_white_pawn;
            else if(pic instanceof King)
                return i_white_king;
            else if(pic instanceof Knight)
                return i_white_knight;
            else if(pic instanceof Queen)
                return i_white_queen;
            else if(pic instanceof Bishop)
                return i_white_bishop;
            else
                return i_white_rook;
        }else {
            if(pic instanceof Pawn)
                return i_black_pawn;
            else if(pic instanceof King)
                return i_black_king;
            else if(pic instanceof Knight)
                return i_black_knight;
            else if(pic instanceof Queen)
                return i_black_queen;
            else if(pic instanceof Bishop)
                return i_black_bishop;
            else
                return i_black_rook;
        }
    }

    @Override
    public void actionPerformed(BoardEvent event) {
        setButtons();
    }
}