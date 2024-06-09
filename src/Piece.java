import java.util.Objects;

public class Piece {

    private boolean isWhite;
    private String symbol;
    private boolean firstTime;

    public Piece(boolean iw) {
        isWhite = iw;
        firstTime = true;
    }

    public boolean getIsWhite() {
        return isWhite;
    }
    public void setIsWhite(boolean white) {
        isWhite = white;
    }

    public String getSymbol() {
        return this.symbol;
    }
    public void setSymbol(String s) {
        this.symbol = s;
    }


    public boolean checkCanPieceMove(int row, int col, int newCol, int newRow) {

        if(Objects.equals(this.getSymbol(), "♟") || Objects.equals(this.getSymbol(), "♙")) {
            if(isWhite){
                if(this.firstTime){
                    if(((newRow == 2+row) || (newRow == 1+row)) && (col == newCol)){
                        return true;
                    }
                }
                else{
                    if((newRow == 1 + row) && ((newCol == col - 1) || (newCol == col + 1))){
                        return true;
                    }
                }
                return false;
            }
            else{
                if(this.firstTime){
                    if(((newRow == row-2) || (newRow == row-1)) && (col == newCol)){
                        return true;
                    }
                }
                else{
                    if((newRow == row-1) && (col == newCol)){
                        return true;
                    }
                }
                return false;
            }
        }
        else if(Objects.equals(this.getSymbol(), "♜") || Objects.equals(this.getSymbol(), "♖")) {
            if(row == newRow || col == newCol){
                return true;
            }
            return false;
        }
        else if(Objects.equals(this.getSymbol(), "♞") || Objects.equals(this.getSymbol(), "♘")) {
            if(((newRow == row - 1) || (newRow == row + 1)) && ((newCol == col-2) || (newCol == col+2))){
                return true;
            }
            else if(((newCol == col - 1) || (newCol == col + 1)) && ((newRow == row-2) || (newRow == row+2))) {
                return true;
            }
            else{
                return false;
            }
        }
        else if(Objects.equals(this.getSymbol(), "♝") || Objects.equals(this.getSymbol(), "♗")) {
            int diffRow = row - newRow;
            int diffCol = col - newCol;

            if(diffRow < 0){
                diffRow = diffRow * -1;
            }
            if(diffCol < 0){
                diffCol = diffCol * -1;
            }

            return diffRow == diffCol;
        }
        else if(Objects.equals(this.getSymbol(), "♛") || Objects.equals(this.getSymbol(), "♕")) {

            //rook check
            if(row != newRow && col != newCol){
                return false;
            }

            //bishop check
            int diffRow = row - newRow;
            int diffCol = col - newCol;

            if(diffRow < 0){
                diffRow = diffRow * -1;
            }
            if(diffCol < 0){
                diffCol = diffCol * -1;
            }
            if(diffRow != diffCol){
                return false;
            }
            return true;
        }
        else if(Objects.equals(this.getSymbol(), "♚") || Objects.equals(this.getSymbol(), "♔")) {
            if((newRow > row + 1) || (newRow < row - 1) || (newCol > col + 1 || newCol < col - 1)){
                return false;
            }
            else{
                return true;
            }
        }
        else {
            System.out.println("Shouldn't be reached");
            return false;
        }

    }


}
