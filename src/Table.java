import java.util.Objects;
import java.util.Scanner;

public class Table {

    private int vertical;
    private int horizontal;
    private final Piece [][] table;
    private boolean whiteToPlay;

    Pawn wp0; Pawn wp1; Pawn wp2; Pawn wp3; Pawn wp4; Pawn wp5; Pawn wp6; Pawn wp7;
    Pawn bp0; Pawn bp1; Pawn bp2; Pawn bp3; Pawn bp4; Pawn bp5; Pawn bp6; Pawn bp7;
    Rook wr1; Rook wr2; Rook br1; Rook br2;
    Knight wk1; Knight wk2; Knight bk1; Knight bk2;
    Bishop wb1; Bishop wb2; Bishop bb1; Bishop bb2;
    Queen bq; Queen wq; King wk; King bk;


    public Table(){
        whiteToPlay = true;
        int spaceCounter = 0;
        vertical = 0;
        horizontal = 0;
        table = new Piece[8][8];

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                table[i][j] = new Space(false);
                spaceCounter++;
            }
        }

        wp0 = new Pawn(true);
        wp1 = new Pawn(true);
        wp2 = new Pawn(true);
        wp3 = new Pawn(true);
        wp4 = new Pawn(true);
        wp5 = new Pawn(true);
        wp6 = new Pawn(true);
        wp7 = new Pawn(true);

        bp0 = new Pawn(false);
        bp1 = new Pawn(false);
        bp2 = new Pawn(false);
        bp3 = new Pawn(false);
        bp4 = new Pawn(false);
        bp5 = new Pawn(false);
        bp6 = new Pawn(false);
        bp7 = new Pawn(false);

        wr1 = new Rook(true);
        wr2 = new Rook(true);
        br1 = new Rook(false);
        br2 = new Rook(false);

        wk1 = new Knight(true);
        wk2 = new Knight(true);
        bk1 = new Knight(false);
        bk2 = new Knight(false);

        wb1 = new Bishop(true);
        wb2 = new Bishop(true);
        bb1 = new Bishop(false);
        bb2 = new Bishop(false);

        wq = new Queen(true);
        bq = new Queen(false);

        wk = new King(true);
        bk = new King(false);

        //whites
        table[0][0] = wr1;
        table[0][1] = wk1;
        table[0][2] = wb1;
        table[0][3] = wq;
        table[0][4] = wk;
        table[0][5] = wb2;
        table[0][6] = wk2;
        table[0][7] = wr2;

        table[1][0] = wp0;
        table[1][1] = wp1;
        table[1][2] = wp2;
        table[1][3] = wp3;
        table[1][4] = wp4;
        table[1][5] = wp5;
        table[1][6] = wp6;
        table[1][7] = wp7;

        //blacks
        table[7][0] = br1;
        table[7][1] = bk1;
        table[7][2] = bb1;
        table[7][3] = bq;
        table[7][4] = bk;
        table[7][5] = bb2;
        table[7][6] = bk2;
        table[7][7] = br2;

        table[6][0] = bp0;
        table[6][1] = bp1;
        table[6][2] = bp2;
        table[6][3] = bp3;
        table[6][4] = bp4;
        table[6][5] = bp5;
        table[6][6] = bp6;
        table[6][7] = bp7;
    }

    public void printTable(){
        for(int i = 0; i < 8; i++){
            System.out.println();

            System.out.print(" ");

            for(int j = 0; j < 8; j++){
                if(j==0){
                    System.out.print("|");
                }
                System.out.print(" ");
                System.out.print(table[i][j].getSymbol());
                System.out.print("|");
            }
            System.out.println();

        }
    }

    public int isCheckedAt(int kingRow, int kingCol) {

        int total = 0;
        String kingSymbol;

        if(whiteToPlay){
            kingSymbol = "♚";
        }
        else{
            kingSymbol = "♔";
        }

        //knight check
        String knightSymbol;
        if(whiteToPlay){
            knightSymbol = "♘";
        }
        else{
            knightSymbol = "♞";
        }
        if(kingRow-2 >= 0 && kingCol-1>=0 && table[kingRow-2][kingCol-1].getSymbol().equals(knightSymbol)){
            total++;
        }
        if(kingRow-2 >= 0 && kingCol+1<=7 && table[kingRow-2][kingCol+1].getSymbol().equals(knightSymbol)){
            total++;
        }
        if(kingRow-1 >= 0 && kingCol-2>=0 && table[kingRow-1][kingCol-2].getSymbol().equals(knightSymbol)){
            total++;
        }
        if(kingRow-1 >= 0 && kingCol+2<=7 && table[kingRow-1][kingCol+2].getSymbol().equals(knightSymbol)){
            total++;
        }
        if(kingRow+1 <= 7 && kingCol-2>=0 && table[kingRow+1][kingCol-2].getSymbol().equals(knightSymbol)){
            total++;
        }
        if(kingRow+1 <= 7 && kingCol+2<=7 && table[kingRow+1][kingCol+2].getSymbol().equals(knightSymbol)){
            total++;
        }
        if(kingRow+2 <= 7 && kingCol-1>=0 && table[kingRow+2][kingCol-1].getSymbol().equals(knightSymbol)){
            total++;
        }
        if(kingRow+2 <= 7 && kingCol+1<=7 && table[kingRow+2][kingCol+1].getSymbol().equals(knightSymbol)){
            total++;
        }

        //preparing for horizontal and vertical
        String[] possiblePieces = new String[2];
        if(whiteToPlay){
            possiblePieces[0] = "♖";
            possiblePieces[1] = "♕";
        }
        else{
            possiblePieces[0] = "♜";
            possiblePieces[1] = "♛";
        }

        //horizontal check, rook, queen
        int tmpKingCol = kingCol;
        while(tmpKingCol != 0){
            tmpKingCol = tmpKingCol-1;
            if((Objects.equals(table[kingRow][tmpKingCol].getSymbol(), possiblePieces[0]) || Objects.equals(table[kingRow][tmpKingCol].getSymbol(), possiblePieces[1]))){
                if(!isWayBlocked(kingRow, kingCol, kingRow, tmpKingCol)){
                    total++;
                }
            }
        }
        tmpKingCol = kingCol;
        while(tmpKingCol != 6){
            tmpKingCol = tmpKingCol+1;
            if((Objects.equals(table[kingRow][tmpKingCol].getSymbol(), possiblePieces[0]) || Objects.equals(table[kingRow][tmpKingCol].getSymbol(), possiblePieces[1]))){
                if(!isWayBlocked(kingRow, kingCol, kingRow, tmpKingCol)){
                    total++;
                }
            }
        }

        //vertical check - rook, queen
        int tmpKingRow = kingRow;
        while(tmpKingRow != 0){
            tmpKingRow = tmpKingRow-1;
            if((Objects.equals(table[tmpKingRow][kingCol].getSymbol(), possiblePieces[0]) || Objects.equals(table[tmpKingRow][kingCol].getSymbol(), possiblePieces[1]))){
                if(!isWayBlocked(kingRow, kingCol, tmpKingRow, kingCol)){
                    total++;
                }
            }
        }
        tmpKingRow = kingRow;
        while(tmpKingRow != 6){
            tmpKingRow = tmpKingRow+1;
            if((Objects.equals(table[tmpKingRow][kingCol].getSymbol(), possiblePieces[0]) || Objects.equals(table[tmpKingRow][kingCol].getSymbol(), possiblePieces[1]))){
                if(!isWayBlocked(kingRow, kingCol, tmpKingRow, kingCol)){
                    total++;
                }
            }
        }

        //cross check - bishop, pawn
        String bishopSymbol;
        if(whiteToPlay){
            if(kingRow+1 <= 7 && kingCol-1>=0 && table[kingRow+1][kingCol-1].getSymbol().equals("♙")){
                total++;
            }
            if(kingRow+1 <= 7 && kingCol+1 <=7 && table[kingRow+1][kingCol+1].getSymbol().equals("♙")){
                total++;
            }
            int tempKR = kingRow;
            int tempKC = kingCol;
            while(tempKR > 0 && tempKC > 0){
                if((table[tempKR][tempKC].getSymbol().equals("♗") || table[tempKR][tempKC].getSymbol().equals("♕")) && !isWayBlocked(tempKR, tempKC, kingRow, kingCol)){
                    total++;
                    break;
                }
                tempKR = tempKR-1;
                tempKC = tempKC-1;
            }

            tempKR = kingRow;
            tempKC = kingCol;
            while(tempKR > 0 && tempKC < 7){
                if((table[tempKR][tempKC].getSymbol().equals("♗") || table[tempKR][tempKC].getSymbol().equals("♕")) && !isWayBlocked(tempKR, tempKC, kingRow, kingCol)){
                    total++;
                    break;
                }
                tempKR = tempKR-1;
                tempKC = tempKC+1;
            }

            tempKR = kingRow;
            tempKC = kingCol;
            while(tempKR < 7 && tempKC > 0){
                if((table[tempKR][tempKC].getSymbol().equals("♗") || table[tempKR][tempKC].getSymbol().equals("♕")) && !isWayBlocked(tempKR, tempKC, kingRow, kingCol)){
                    total++;
                    break;
                }
                tempKR = tempKR+1;
                tempKC = tempKC-1;
            }

            tempKR = kingRow;
            tempKC = kingCol;
            while(tempKR < 7 && tempKC < 7){
                if((table[tempKR][tempKC].getSymbol().equals("♗") || table[tempKR][tempKC].getSymbol().equals("♕")) && !isWayBlocked(tempKR, tempKC, kingRow, kingCol)){
                    total++;
                    break;
                }
                tempKR = tempKR+1;
                tempKC = tempKC+1;
            }
        }
        else{
            //blackToPlay
            if(kingRow+1 <= 7 && kingCol-1>=0 && table[kingRow+1][kingCol-1].getSymbol().equals("♟")){
                total++;
            }
            if(kingRow+1 <= 7 && kingCol+1 <=7 && table[kingRow+1][kingCol+1].getSymbol().equals("♟")){
                total++;
            }
            int tempKR = kingRow;
            int tempKC = kingCol;
            while(tempKR >= 0 && tempKC >= 0){
                if((table[tempKR][tempKC].getSymbol().equals("♝") || table[tempKR][tempKC].getSymbol().equals("♛")) && !isWayBlocked(tempKR, tempKC, kingRow, kingCol)){
                    total++;
                    break;
                }
                tempKR = tempKR-1;
                tempKC = tempKC-1;
            }

            tempKR = kingRow;
            tempKC = kingCol;
            while(tempKR >= 0 && tempKC <= 7){
                if((table[tempKR][tempKC].getSymbol().equals("♝") || table[tempKR][tempKC].getSymbol().equals("♛")) && !isWayBlocked(tempKR, tempKC, kingRow, kingCol)){
                    total++;
                    break;
                }
                tempKR = tempKR-1;
                tempKC = tempKC+1;
            }

            tempKR = kingRow;
            tempKC = kingCol;
            while(tempKR <= 7 && tempKC >= 0){
                if((table[tempKR][tempKC].getSymbol().equals("♝") || table[tempKR][tempKC].getSymbol().equals("♛")) && !isWayBlocked(tempKR, tempKC, kingRow, kingCol)){
                    total++;
                    break;
                }
                tempKR = tempKR+1;
                tempKC = tempKC-1;
            }

            tempKR = kingRow;
            tempKC = kingCol;
            while(tempKR <= 7 && tempKC <= 7){
                if((table[tempKR][tempKC].getSymbol().equals("♝") || table[tempKR][tempKC].getSymbol().equals("♛")) && !isWayBlocked(tempKR, tempKC, kingRow, kingCol)){
                    total++;
                    break;
                }
                tempKR = tempKR+1;
                tempKC = tempKC+1;
            }
        }

        return total;
    }

    private String promote(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Promote to? 'Knight' 'Bishop' 'Rook' 'Queen' ");
        String wanted = sc.nextLine();
        switch (wanted) {
            case "Knight" -> {
                return "K";
            }
            case "Bishop" -> {
                return "B";
            }
            case "Rook" -> {
                return "R";
            }
            case "Queen" -> {
                return "Q";
            }
            /*
            case  -> {
                System.out.println("Wrong input, promoted to Queen automatically");
                return "Q";
            }
            */
        }
        System.out.println("shouldn't be reach here");
        return null;
    }

    public void move(int row, int col, int newRow, int newCol){
        int tmp = row;
        row=col;
        col = tmp;

        int tmp2 = newRow;
        newRow = newCol;
        newCol = tmp2;

        if(row<0 || row>7 || col<0 || col>7 || newRow<0 || newRow>7 || newCol<0 || newCol>7){
            System.out.println("Invalid Move, out of board move.");
        }
        Piece p = table[row][col];
        System.out.println("row: "+row+" col: "+col);
        System.out.println("Symb:" + p.getSymbol());
        if(whiteToPlay && !p.getIsWhite()){
            System.out.println("Invalid move, white to play.");
        }
        else if(!whiteToPlay && p.getIsWhite()){
            System.out.println("Invalid move, black to play.");
        }
        else if(Objects.equals(p.getSymbol(), "  ")){
            System.out.println("Invalid move, square is empty");
        }
        else if(isCheckedAt(row, col) > 0 && ((whiteToPlay && !p.getSymbol().equals("♚")) || (!whiteToPlay && p.getSymbol().equals("♔")))){
            System.out.println("Illegal move, checked.");
        }
        else if(p.getSymbol().equals("♚") && isCheckedAt(newRow, newCol) != 0){
            System.out.println("Illegal move King cannot run into a check.");
        }
        else if(!p.checkCanPieceMove(row, col, newCol, newRow) ||  !isWayBlocked(row, col, newRow, newCol)){
            if(p.getSymbol().equals("♟") && (table[newRow][newCol].getIsWhite() || table[newRow][newCol].getSymbol().equals("  "))){
                System.out.println("Illegal move, selected piece cannot be played to desired square.");
            }
            else if(p.getSymbol().equals("♙") && (!table[newRow][newCol].getIsWhite() || table[newRow][newCol].getSymbol().equals("  ")) ){
                System.out.println("Illegal move, selected piece cannot be played to desired square.");
            }
            System.out.println("Illegal move, selected piece cannot be played to desired square.");
        }
        else{
            Piece temp = table[newRow][newCol];
            table[newRow][newCol] = table[row][col];
            table[row][col] = temp;

            if((newRow == 0 && p.getSymbol().equals("♙")) || (newRow == 7 && p.getSymbol().equals("♟"))){
                switch (promote()) {
                    case "K" -> table[newRow][newCol] = new Knight(p.getIsWhite());
                    case "B" -> table[newRow][newCol] = new Bishop(p.getIsWhite());
                    case "R" -> table[newRow][newCol] = new Rook(p.getIsWhite());
                    case "Q" -> table[newRow][newCol] = new Queen(p.getIsWhite());
                }
            }
            whiteToPlay = !whiteToPlay;
        }

    }

    public boolean isWayBlocked(int row, int col, int newRow, int newCol) {
        Piece piece = table[row][col];
        if(Objects.equals(piece.getSymbol(), "♛") || Objects.equals(piece.getSymbol(), "♕") ||
                Objects.equals(piece.getSymbol(), "♝") || Objects.equals(piece.getSymbol(), "♗") ||
                Objects.equals(piece.getSymbol(), "♜") || Objects.equals(piece.getSymbol(), "♖") ||
                Objects.equals(piece.getSymbol(), "♟") || Objects.equals(piece.getSymbol(), "♙")){

            if(row > newRow && col > newCol){
                for (int i = newRow+1; i < row ; i++){
                    for(int j = newCol+1; j < col ; j++){
                        if(!Objects.equals(table[i][j].getSymbol(), "  ")){
                            return false;
                        }
                    }
                }
            }
            else if(row > newRow && col < newCol){
                for (int i = newRow+1; i < row ; i++){
                    for(int j = col+1; j < newCol ; j++){
                        if(!Objects.equals(table[i][j].getSymbol(), "  ")){
                            return false;
                        }
                    }
                }
            }
            else if(row < newRow && col > newCol){
                for (int i = row+1; i < newRow ; i++){
                    for(int j = newCol+1; j < col ; j++){
                        if(!Objects.equals(table[i][j].getSymbol(), "  ")){
                            return false;
                        }
                    }
                }
            }
            else if(row < newRow && col < newCol){
                for (int i = row+1; i < newRow ; i++){
                    for(int j = col+1; j < newCol ; j++){
                        if(!Objects.equals(table[i][j].getSymbol(), "  ")){
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        return true;
    }

    public boolean isMate(int row, int col) {
        for (int i = row-1; i <= row+1 ; i++){
            if(i<0){
                i++;
            }
            else if(i>7){
                i--;
            }
            for(int j = col-1; j <= col+1 ; j++){
                if(j<0){
                    j++;
                }
                else if(j>7){
                    j--;
                }

                //checks if there is check right now
                if(i == row && j == col && isCheckedAt(i ,j) == 0){
                    return false;
                }
                //checks if any space to run
                if((table[i][j].getSymbol().equals("  ")) && isCheckedAt(i, j) == 0){
                    return false;
                }
                //checks if there is takeable opponent piece for black and white
                if((table[i][j].getIsWhite() && !whiteToPlay) || (!table[i][j].getIsWhite() && whiteToPlay)){
                    if(isCheckedAt(i, j) == 0){
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public int getVertical(){
        return vertical;
    }
    public int getHorizontal() {
        return horizontal;
    }
    public Piece getTableElement(int horizontal, int vertical) {
        return table[horizontal][vertical];
    }
    public boolean getWhiteToPlay() {
        return whiteToPlay;
    }

    public void setVertical(int vertical){
        this.vertical = vertical;
    }
    public void setHorizontal(int horizontal){
        this.horizontal = horizontal;
    }
    public void setTableElement(int horizontal, int vertical, Piece tableElement){
        table[horizontal][vertical] = tableElement;
    }


}