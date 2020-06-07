import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
    public static void main(String args[]){
        start();
    }

    private static void start(){
        Robot robert = new Robot();

        Scanner nyscan = new Scanner(System.in);
        Terminal man = new Terminal(nyscan);

        
        Terreng testter = new Terreng();
        Spiller rob = new Spiller(testter.hentStart(), robert);
        rob.nyTrekk();

        Spiller mann = new Spiller(testter.hentStart(), man);
        mann.nyTrekk();
    }
}