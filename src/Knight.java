public class Knight extends Piece {

    public Knight(boolean isWhite) {
        super(isWhite);
        if(isWhite){
            setSymbol("♞");
        }
        else{
            setSymbol("♘");
        }
    }
}