import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Oblig5V2 {
    public static void main(String[] args) {
        boolean vanlig = true;
        String filnavn = null;

        if (args.length > 0) {
            filnavn = args[0];
        } else {
            System.out.println("FEIL! Riktig bruk av programmet: "
                               +"java Oblig5 <labyrintfil> (legg til bokstaven X til slutt for extra statistikk og tester");
            return;
        }///Xtra metoder sjekkes
        if(args.length >1){
            if(args[1].equals("X")){
                vanlig = false;
            }
        }
        File fil = new File(filnavn);
        Labyrint l = null;
        try {
            l = Labyrint.lesFraFil(fil);
        } catch (FileNotFoundException e) {
            System.out.printf("FEIL: Kunne ikke lese fra '%s'\n", filnavn);
            System.exit(1);
        }

        // les start-koordinater fra standard input
        Scanner inn = new Scanner(System.in);
        System.out.println("Skriv inn koordinater <rad> <kolonne> ('a' for aa avslutte)"); //byttet paa kolonne og rad, fordi jeg ble forvirret av at rad ikke var foerst
        String[] ord = inn.nextLine().split(" ");
        while (!ord[0].equals("a")) {

            try {
                int startRad = Integer.parseInt(ord[0]);
                int startKol = Integer.parseInt(ord[1]);

                
                if(vanlig = true){
                    Liste<String> utveier = l.finnUtveiFra(startKol, startRad);
                    if (utveier.stoerrelse() != 0) {
                        for (String s : utveier) {
                            System.out.println(s);
                        }
                    } else {
                        System.out.println("Ingen utveier.");
                    }
                    System.out.println();
                }
                else if(vanlig = false){
                    System.out.println("her er labyrinten");
                    System.out.println(l);
                    Liste<String> utveier = l.finnUtveiFra(startKol, startRad);
                    if (utveier.stoerrelse() != 0) {
                        String minstemuligevei = "";
                        int lengde = Integer.MAX_VALUE;
                        for (String s : utveier) {
                            if(s.length() < lengde){
                                lengde = s.length();
                                minstemuligevei = s;
                            }
                        }
                        System.out.println("det ble funnet " + utveier.stoerrelse() + " veier");
                        for(String vei : utveier){
                            System.out.println("\n" + vei);
                        }
                        System.out.println("ut av alle, saa var denne den korteste");
                        System.out.println(minstemuligevei);
                    } else {
                        System.out.println("Ingen utveier.");
                    }
                    System.out.println();
                }
            } catch (NumberFormatException e) {
                System.out.println("Ugyldig input!");
            }
            
            System.out.println("Skriv inn nye koordinater ('a' for aa avslutte)");
            ord = inn.nextLine().split(" ");
        }
    }
}