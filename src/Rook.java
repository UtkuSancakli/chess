public class Rook extends Piece{


    public Rook(boolean isWhite) {
        super(isWhite);
        if(isWhite){
            setSymbol("♜");
        }
        else{
            setSymbol("♖");
        }
    }


}
