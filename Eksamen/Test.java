import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
    public static void main(String args[]){
        start();
    }

    private static void start(){
        Robot robert = new Robot();
        System.out.println("sjekk1");

        Scanner nyscan = new Scanner(System.in);
        Terminal man = new Terminal(nyscan);
        System.out.println("sjekk1");

        
        Terreng testter = new Terreng();
        Spiller rob = new Spiller(testter.hentStart(), robert);
        rob.nyTrekk();
        System.out.println("sjekk1");

        Spiller mann = new Spiller(testter.hentStart(), man);
        mann.nyTrekk();
        System.out.println("sjekk1");
    }
}