public class Bishop extends Piece {

    public Bishop(boolean isWhite) {
        super(isWhite);
        if(isWhite){
            setSymbol("♝");
        }
        else{
            setSymbol("♗");
        }
    }
}
