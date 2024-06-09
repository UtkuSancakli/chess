public class Queen extends Piece {

    public Queen(boolean isWhite) {
        super(isWhite);
        if(isWhite){
            setSymbol("♛");
        }
        else{
            setSymbol("♕");
        }
    }
}
