import java.io.FileNotFoundException;
import java.util.Scanner;

public class Spill {
    public static void main(String args[]){
        start();
    }

    private static void start(){

        Scanner nyscan = new Scanner(System.in);
        Terminal man = new Terminal(nyscan);

        
        Terreng terreng = new Terreng();
        Spiller mann = new Spiller(terreng.hentStart(), man);

        System.out.println("VELG NAVNET DITT");
        Scanner brukerinput = new Scanner(System.in);
        String navn = brukerinput.nextLine();
        mann.nyTrekk();
    }
}