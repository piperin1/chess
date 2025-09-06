package chess;

import java.util.Collection;
import java.util.ArrayList;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    private final ChessPiece.PieceType pieceType;
    private final ChessGame.TeamColor pieceTeam;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        pieceType = type;
        pieceTeam = pieceColor;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return this.pieceTeam;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return this.pieceType;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        //Store and prepare values
        PieceType type = board.getPiece(myPosition).getPieceType();
        ChessGame.TeamColor team = board.getPiece(myPosition).getTeamColor();
        Collection<ChessMove> moveList = new ArrayList<>();

        //Determine direction based on team
        int direction = 1;
        if (team == ChessGame.TeamColor.BLACK) {
            direction = -1;
        }

        //Calculate moves
        switch (type) {
            case PAWN:
                //Step Forward 1
                ChessPosition forwardOne = new ChessPosition(myPosition.getRow() + (1 * direction), myPosition.getColumn());
                if (onBoard(forwardOne) && board.getPiece(forwardOne) == null) {
                    //Add promo conditional here
                    ChessMove move = new ChessMove(myPosition,forwardOne,null);
                }
                //Step Forward 2 (Conditional)
                if ((team == ChessGame.TeamColor.WHITE && myPosition.getRow() == 2) || (team== ChessGame.TeamColor.BLACK && myPosition.getRow() == 7)) {
                    ChessPosition forwardTwo = new ChessPosition(myPosition.getRow() + (2 * direction), myPosition.getColumn());
                    if (board.getPiece(forwardTwo) == null) {
                        ChessMove move = new ChessMove(myPosition, forwardTwo, null);
                    }
                }
                //Diagonal capture
                

                break;
            case ROOK:
                //add code
                break;
            case KNIGHT:
                // add code
                break;
            case BISHOP:
                //add code
                break;
            case QUEEN:
                //add code
                break;
            case KING:
                //add code
                break;
        }

        return moveList;
    }

    /**
     * Returns true when a position is in bounds
     */
    public boolean onBoard(ChessPosition myPosition){
        return ( 1 <= myPosition.getRow() && myPosition.getRow() <= 8 && 1<= myPosition.getColumn() && myPosition.getColumn() <= 8 );
    }


}
