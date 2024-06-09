public class King extends Piece {

    public King(boolean isWhite) {
        super(isWhite);
        if(isWhite){
            setSymbol("♚");
        }
        else{
            setSymbol("♔");
        }
    }
}