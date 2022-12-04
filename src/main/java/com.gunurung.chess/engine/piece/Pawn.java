package com.gunurung.chess.engine.piece;

import com.gunurung.chess.engine.board.Board;
import com.gunurung.chess.engine.board.Location;
import com.gunurung.chess.engine.board.moves.Enpassant;
import com.gunurung.chess.engine.board.moves.Move;
import com.gunurung.chess.engine.board.moves.Movement;
import com.gunurung.chess.engine.board.moves.Promotion;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(boolean isWhite) {
        super(isWhite);
    }
    @Override
    public List<Movement> getAvailableMovement(Board board) {
        List<Movement> moves = new ArrayList<>();
        if(board.isGameEnded()) return moves;
        if (board.isWhiteTurn() == isWhite()) {
            Location from = board.getLocation(this);
            Location to_1 = from.offset(0,isWhite() ? 1 : -1);
            Piece pic_1 = board.getPiece(to_1);

            if(to_1.getRank() <= 8 && to_1.getRank() >= 1){
                //promotion
                if(pic_1 == null && ((isWhite() && from.getRank()==7) || (!isWhite()&&from.getRank()==2))){
                    Location promotion_to = isWhite?from.offset(0,1):from.offset(0,-1);
                    moves.add(new Promotion(from, promotion_to, Promotion.PromotionType.QUEEN));
                    moves.add(new Promotion(from,promotion_to, Promotion.PromotionType.BISHOP));
                    moves.add(new Promotion(from, promotion_to,Promotion.PromotionType.ROOK));
                    moves.add(new Promotion(from,promotion_to, Promotion.PromotionType.KNIGHT));
                }
                //일반적인 폰의 1칸 움직임
                else if(pic_1 == null) {
                    moves.add(new Move(from,to_1));
                    if(!haveMoved()){
                        Location to_2 = from.offset(0,isWhite() ? 2 : -2);
                        if(board.getPiece(to_2) == null)
                        moves.add(new Move(from,to_2));
                    }

                }
                //움직이지 않은 폰의 2칸움직임

                //앙파상 (조건 : 내 폰이 직전에 2칸 전진한 상대 폰 바로 옆에 있을 경우)
                if(from.getRank() == (isWhite() ? 5 : 4) && board.getLogger().getLastMove() instanceof Move){ //상대 폰이 나랑 같은 열인지, 상대 직전 움직임이 Move 류 였는지
                    Move lastMove = (Move) board.getLogger().getLastMove();
                    if(Math.abs(lastMove.getTo().getFile() - from.getFile()) == 1 //상대 폰이 내 바로 옆칸인지
                    && lastMove.getTo().getRank() == (isWhite() ? 5 : 4) && lastMove.getFrom().getRank() == (isWhite() ? 7 : 2) //상대 말이 2칸 전진 했는지
                    && board.getPiece(lastMove.getTo()) instanceof  Pawn) { //2칸 전진한 그 말이 폰이었는지
                        moves.add(new Enpassant(from,lastMove.getTo()));
                    }
                }

                //kill
                Piece kill_1 = from.getFile() > 1 ? board.getPiece(from.offset(-1,isWhite() ? 1 : -1)) : null;
                Piece kill_2 = from.getFile() < 8 ? board.getPiece(from.offset(1,isWhite() ? 1 : -1)) : null;
                if(kill_1 != null && kill_1.isWhite() != isWhite()){
                    Location killLoc = from.offset(-1,isWhite() ? 1 : -1);
                    if((isWhite() && from.getRank()==7)||(!isWhite&&from.getRank()==2)) {
                        moves.add(new Promotion(from, killLoc, Promotion.PromotionType.QUEEN));
                        moves.add(new Promotion(from,killLoc, Promotion.PromotionType.BISHOP));
                        moves.add(new Promotion(from, killLoc,Promotion.PromotionType.ROOK));
                        moves.add(new Promotion(from,killLoc, Promotion.PromotionType.KNIGHT));
                    }
                    else
                        moves.add(new Move(from,killLoc));
                }

                if(kill_2 != null && kill_2.isWhite() != isWhite())
                    moves.add(new Move(from,from.offset(1,isWhite() ? 1 : -1)));
            }
        }
        return moves;
    }
}
