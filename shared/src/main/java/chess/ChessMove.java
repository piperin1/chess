package chess;

/**
 * Represents moving a chess piece on a chessboard
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessMove {
    private final ChessPosition pieceStart;
    private final ChessPosition pieceEnd;
    private final ChessPiece.PieceType piecePromo;

    public ChessMove(ChessPosition startPosition, ChessPosition endPosition,
                     ChessPiece.PieceType promotionPiece) {
        pieceStart = startPosition;
        pieceEnd = endPosition;
        piecePromo = promotionPiece;
    }

    /**
     * @return ChessPosition of starting location
     */
    public ChessPosition getStartPosition() {
        //throw new RuntimeException("Not implemented");
        return pieceStart;
    }

    /**
     * @return ChessPosition of ending location
     */
    public ChessPosition getEndPosition() {
        //throw new RuntimeException("Not implemented");
        return pieceEnd;
    }

    /**
     * Gets the type of piece to promote a pawn to if pawn promotion is part of this
     * chess move
     *
     * @return Type of piece to promote a pawn to, or null if no promotion
     */
    public ChessPiece.PieceType getPromotionPiece() {

        //throw new RuntimeException("Not implemented");
        return piecePromo;


    }
}
