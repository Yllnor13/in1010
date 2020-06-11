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

        
        VeivalgTerreng testter = new VeivalgTerreng();
        VeivalgSpiller rob = new VeivalgSpiller(robert, testter.hentStart());
        rob.nyTrekk();
        System.out.println("sjekk1");

        VeivalgSpiller mann = new VeivalgSpiller(man, testter.hentStart());
        Sted nysted = testter.hentStart();
        mann.nyTrekk();
        System.out.println("sjekk1");
    }
}