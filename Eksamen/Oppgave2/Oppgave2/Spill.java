import java.io.FileNotFoundException;
import java.util.Scanner; //importerer det jeg trenger

public class Spill {
    private static int trekk = 5; //hvor mange trekk et spill har
    public static void main(String args[]){ //main metode som kjoeres
        start();
    }

    private static void start(){

        Scanner nyscan = new Scanner(System.in);
        Terminal man = new Terminal(nyscan); //lageer scanner og terminal

        
        Terreng terreng = new Terreng(); //lager terreng
        Spiller mann = new Spiller(terreng.hentStart(), man); //lager spiller

        System.out.println("VELG NAVNET DITT");
        Scanner brukerinput = new Scanner(System.in);
        String navn = brukerinput.nextLine();//lar bruker velge sitt eget navn i spillet
        while (trekk>0){ //mens det er fortsatt flere trekk igjen
            System.out.print("\n" + trekk + " trekk som er igjen \n"); //print hvor mange trekk det er som er igjen
            int sjekkomtrekk = mann.nyTrekk(); //man tar et trekk, men ikke alle valg tar et trekk (mener at status er noe man burde kunne sjekke uten aa bruke trekk)
            if(sjekkomtrekk < 3){ //sjekker om de gjorde et trekk som skal ta et trekk for aa gjoere
                trekk--; //senker antall trekk med en
            }
        }
        System.out.print(mann.sluttsum()); //etter at spillet er ferdig, printes resultatet
    }
}