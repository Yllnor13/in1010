import java.util.Scanner;

public class Terminal implements Brukergrensesnitt{
    int trekk = 0;
    Scanner leser = null;


    public Terminal(Scanner scanner){
        leser = scanner;
    }

    public void giStatus(String status){
        System.out.println(status);
    }

    public int beOmKommando(String spoersmal, String[] alternativer){
        System.out.println(spoersmal);
        int alt = 0;
        for (String s : alternativer){
            alt ++;
            System.out.println(alt + ". " + alternativer);
        }
        int valg = leser.nextInt();
        return valg;
    }
}