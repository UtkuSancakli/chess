public class Pawn extends Piece{

    public Pawn(boolean isWhite) {
        super(isWhite);
        if(isWhite){
            setSymbol("♟");
        }
        else{
            setSymbol("♙");
        }
    }
}
