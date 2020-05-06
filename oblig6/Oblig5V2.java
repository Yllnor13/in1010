import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Oblig5V2 {
    public static void main(String[] args) {
        boolean vanlig = true; //betyr at man skal vise vanlig versjon av filen, eller ekstra greier som jeg la til
        String filnavn = null;

        if (args.length > 0) {
            filnavn = args[0];
        } else {
            System.out.println("FEIL! Riktig bruk av programmet: "
                               +"java Oblig5 <labyrintfil> (legg til bokstavene Xtra til slutt for extra statistikk og tester");
            return;
        }///Xtra metoder sjekkes
        if(args.length >1){ //hvis brukeren skrev noe mer enn filnavn og labyring
            if(args[1].equals("Xtra")){ //sjekker om de skrev xtra
                vanlig = false; //da skal man ikke bruke vanlig lenger
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
        System.out.println("Skriv inn koordinater <rad> <kolonne> ('a' for aa avslutte)"); //byttet paa kolonne og rad, fordi jeg ble forvirret av at rad ikke var foerst når jeg skulle teste
        String[] ord = inn.nextLine().split(" ");
        while (!ord[0].equals("a")) {

            try {
                int startRad = Integer.parseInt(ord[0]);
                int startKol = Integer.parseInt(ord[1]);

                
                if(vanlig == true){ //hvis brukerem ikke la til ekstra
                    Liste<String> utveier = l.finnUtveiFra(startRad, startKol); //finner utvei fra koordinatene skrevet
                    if (utveier.stoerrelse() > 0) { //om det er mer enn 0 utveier
                        for (String s : utveier) {
                            System.out.println(s); //skriv dem ut
                        }
                    } else { //ellers
                        System.out.println("Ingen utveier."); //skriv at det ikke ble funnet noen
                    }
                    System.out.println();
                }
                else if(vanlig == false){ //om brukeren la til ekstra
                    System.out.println("her er labyrinten");
                    System.out.println(l); //printer ut labyrinten slik at jeg faar sett om den ble lest inn riktig
                    Liste<String> utveier = l.finnUtveiFra(startRad, startKol); //lager en liste med utveier
                    String minstemuligevei = ""; //lager ny string
                    int lengde = Integer.MAX_VALUE; //lager ny int med stoerste mulige tall
                    if (utveier.stoerrelse() > 0) { //hvis den fant utveier (altsaa, den er stoerre enn null)
                        for (String s : utveier) { //for strenger i utveilista
                            if(s.length() < lengde){ //sjekk om strengen er mindre en lengde, helt til den finner den minste mulige veien ut
                                lengde = s.length();//lagre den korteste lengden hittil, til den finner den korteste veien ut
                                minstemuligevei = s; //saa lagrer den veien som den minste mulige veien
                            }
                        }
                        System.out.println("det ble funnet " + utveier.stoerrelse() + " veier"); //vis hvor mange veier ble funnet
                        for(String vei : utveier){ //gjennom en for loekke
                            System.out.println("\n" + vei);
                        }
                        System.out.println("\nut av alle, saa var denne den korteste\n"); //saa vis den korteste
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