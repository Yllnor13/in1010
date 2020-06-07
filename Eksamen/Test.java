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

        Sted sted = new Sted("robert er her");

        try{
            Terreng testter = new Terreng();
        }
        catch(FileNotFoundException e){
            System.out.println("noe gikk galt");
        }
        Spiller rob = new Spiller(sted, robert);
        rob.nyTrekk();

        Spiller mann = new Spiller(sted, man);
        mann.nyTrekk();
    }
}