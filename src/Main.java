import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //gecerken alma + rok gelicek
        Scanner sc = new Scanner(System.in);
        Table table = new Table();
        table.printTable();

        String intake = sc.nextLine();

        String fmr1s = intake.charAt(0)+"";
        int fmc1 = intake.charAt(1)-'0';
        String fmr2s = intake.charAt(3)+"";
        int fmc2 = intake.charAt(4)-'0';

        int fmr1 = sToInt(fmr1s);
        int fmr2 = sToInt(fmr2s);

        fmc1 = fmc1-1;
        fmc2 = fmc2-1;

        table.move(fmr1, fmc1, fmr2, fmc2);

        while(!table.isMate(fmr1, fmc1)){
            table.printTable();
            table.move(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
        }
        if(table.getWhiteToPlay()){
            System.out.println("Black player has won!");
        }
        else{
            System.out.println("White player has lost!");
        }

    }
    public static int sToInt(String c){
        /*
        Scanner sc2 = new Scanner(System.in);
        while(c != "a" && c != "b" && c != "c" && c != "d" && c != "e" && c != "f" && c != "g" && c != "h"){
            System.out.println("Wrong row input. Try again");
            c = sc2.nextLine();
        }
        sc2.close();
        */

        if(Objects.equals(c, "a")){
            return 0;
        }
        else if(Objects.equals(c, "b")){
            return 1;
        }
        else if(Objects.equals(c, "c")){
            return 2;
        }
        else if(Objects.equals(c, "d")){
            return 3;
        }
        else if(Objects.equals(c, "e")){
            return 4;
        }
        else if(Objects.equals(c, "f")){
            return 5;
        }
        else if(Objects.equals(c, "g")){
            return 6;
        }
        else if(Objects.equals(c, "h")){
            return 7;
        }
        else{
            System.out.println("sToInt patladi");
            return 99;
        }

    }
}