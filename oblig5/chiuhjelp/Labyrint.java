import java.util.*;
import java.io.*;

public class Labyrint{
    private static Rute[][] ruteListe;
    private static int kolonne;
    private static int rad;
    private static Liste<String> ut;

    private Labyrint(Rute[][] ruteListe, int rad, int kolonne){ //konstruktoer 
        this.ruteListe = ruteListe;
        this.kolonne = kolonne;
        this.rad = rad;
    }

    public static Labyrint lesFraFil(File fil) throws FileNotFoundException{ //metode for aa lese fra en fil
        Scanner scanner = null;
        scanner = new Scanner(fil);  //scanner for aa lese innholdet til filen

        String foorstelinje = scanner.nextLine(); //den foerste linjen er for info om stoerrelsen til labyrinten
        String[] infoString = foorstelinje.split(" "); //spliter foerstelinje saann at vi faar to verdier og setter dem i en array
        rad = Integer.parseInt(infoString[0]); //den foerste verdien er raden
        kolonne = Integer.parseInt(infoString[1]); //den andre verdien er kolonne
        
        Rute[][] brett = new Rute[rad][kolonne]; //lager 2D brett av array

        int r = 0;
        while(scanner.hasNextLine()){
            int k = 0;
            Rute[] raden = new Rute[kolonne]; //en array for en rad, med stoerrelsen til kolonne 

            String innlesing = scanner.nextLine(); //lesing av data
            String[] deling = innlesing.split(""); //splitter dem, saann at hver tegn blir en verdi
            for (String s : deling) { // for loekke som gaar gjennom hver verdi i en linje
                Rute ru = null; 
                if(s.equals("#")){ //hvis tegnet er # saa lages det en ny sort rute
                    ru = new SortRute(k, r);  
                }else if(s.equals(".")){ //hvis tegnet er . saa lages det en aapning eller hvit rute.
                    if(r == 0 || r == rad-1 || k == 0 || k == kolonne-1){ //hvis en av disse paastandene stemmer saa blir det laget en aapning
                        ru = new Aapning(k, r); //paastandene spoer da om tegnet er ytterst i labyrinten
                    }
                    else{ //hvis ikke if setningen stemmer, saa lager den bare en en ny hvit rute
                        ru = new HvitRute(k, r);
                    }
                }else{ //Denne kjoerer hvis det er noe tegn som ikke er gyldig
                    System.out.println("Det er feil fil format");
                    System.exit(1);
                }
                raden[k] = ru; //raden[k] lagrer alle rutene som i den kolonnen
                k++; //hopper over til neste kolonne
            }
            brett[r] = raden; //brettet sine rader for da verdiene til raden arrayen
            r++; //hopper over til neste rad
        }

        //for aa finne nabo ruter
        for (int i = 0; i < rad; i++) {
            for (int j = 0; j < kolonne; j++) {
                Rute nord = null;
                Rute oost = null;
                Rute syd = null;
                Rute vest = null;
                Rute valgt = null;
                valgt = brett[i][j];

                if(!(i == 0)){ //hvis det ikke er den oeverste rad saa kjoerer denne
                    nord = brett[i-1][j];
                }
                if(!(i == rad-1)){ //hvis det ikke er den nederste rad saa kjoerer denne
                    syd = brett[i+1][j];
                }
                if(!(j == 0)){ //hvis det ikke er den kolonnen helt til venstre saa kjoerer denne
                    vest = brett[i][j-1];
                }
                if(!(j == kolonne-1)){ //hvis det ikke er den kolonnen helt til hoeyre saa kjoerer denne
                    oost = brett[i][j+1];
                }
                valgt.settNabo(nord, syd, vest, oost); //tar da alt dataen den faar og setter den inn som nabo
            }
        }
        return new Labyrint(brett, rad, kolonne); //lager da dem nye labyrinten
    }

    public Liste<String> finnUtveiFra(int kol, int rad){ //metode for aa finne ut veien fra en gitt posisjon
        Rute startpos = ruteListe[rad][kol]; //start posisjoene
        ut = startpos.finnUtvei(); //utveien

        return ut; //returnere da utveien
    }

    public String toString(){ //metode for aa skrive ut labyrinten
      String text = "";
      for (Rute[] rutes : ruteListe) {
          for (Rute r : rutes) {
              text += " " + r.tilTegn();
          }
          text += "\n";
      }
      return text;  
    }
}