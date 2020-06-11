import java.util.Scanner;

public class Terminal implements Brukergrensesnitt{ //implementerer brukergrensesnitt
    Scanner leser = null;


    public Terminal(Scanner scanner){ //tar imot scanner som parameter
        leser = scanner;
    }

    public void giStatus(String status){ //printer status
        System.out.println(status);
    }

    public int beOmKommando(String spoersmal, String[] alternativer){ //tar imot valg
        System.out.println(spoersmal); //printer spoersmalet
        for (String s : alternativer){ //printer ut alternativene
            System.out.println(s);
        }
        int valg = leser.nextInt(); //brukeren maa skrive valget de vil ta
        return valg;
    }
}