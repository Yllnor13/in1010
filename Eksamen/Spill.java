import java.io.FileNotFoundException;
import java.util.Scanner;

public class Spill {
    static int runde = 4;
    public static void main(String args[]){
        start();
    }

    private static void start(){

        Scanner nyscan = new Scanner(System.in);
        Terminal man = new Terminal(nyscan);

        
        Terreng terreng = new Terreng();
        Spiller mann = new Spiller(terreng.hentStart(), man);

        Scanner brukerinput = Scanner(System.in);
        String navn = brukerinput.nextLine();
        while(runde > 0){
            System.out.println("RUNDE: " + runde);
            mann.nyTrekk();
            runde--;
        }
    }
}