import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Labyrint {
    private static int rader;       //rader
    private static int kolonner;    //kolonner
    private static Rute[][] ruter;  //todimensjonell array
    private Liste<String> utveier; //utveier


    public String toString(){ //tostring metode, brukes til å vise fram labyrinten
        String kart = "";
        for(int i = 0; i <= rader - 1; i++){
            for(int j = 0; j <= kolonner - 1; j++){
                Rute paaKart = ruter[i][j];
                kart += paaKart.tilTegn();
            }
            kart += "\n";
        }
        return kart;
    }

    private Labyrint(int rad, int kol, Rute[][] rut){ //konstruktoer
        rader = rad;
        kolonner = kol;
        ruter = rut;
    }
    
    public void leggTilUtvei(String veiut){ //metode som skal bli brukt i aapning.gaa slik at liste kan forbli private
        utveier.leggTil(veiut); //legger den til i utveier lista i labyrint
    }

    public Liste<String> finnUtveiFra(int starK, int starR){//finnutveifra metoden, som tar imot start rad og start kolonne posisjon som parameter
        Rute startPunkt = ruter[starR][starK]; //hjelpepeker som man starter fra i posisjonen i rutekartet
        if(startPunkt.tilTegn() == '#'){ //hvis man skrev en posisjon som var sort rute saa skal man faa error melding
            System.out.println("du skrev svart rute som start posisjon, skriv paa nytt \n\naltsaa, det ble...");
            Liste<String> feilstring = new Lenkeliste<>(); //lager lenkeliste slik at den kan bli returnert
            return feilstring; //returnerer stringliste slik at metoden kan funke
        }
        else{//ellers saa skal man bruke finn utvei. skjoenner ikke hvorfor vi ikke ble bedt om aa gjoere finn utvei og finnutveifra om til samme fil om de gjoer det samme
            utveier = new Lenkeliste<String>();
            startPunkt.finnUtvei(); //lagrer utveiene til startpunkt i utveier
            return utveier; //returnerer utveier
        }
    }

    public static Labyrint lesFraFil(File tall) throws FileNotFoundException{//public lesfrafil slik at andre filer kan bruke metoden, alt annet er privat her (utenom finnutveifra). tar imot filen "tall", fordi filnavnene var nummer
        Scanner lesfil = null;//ny scanner som skal lese
        lesfil = new Scanner(tall); //gjoer variabel lesfil om til scanner(tall)
        
        String info = lesfil.nextLine(); //string med info om kartet blir om til en linje av lesfil
        String[] inforadkol = info.split(" "); //string liste som inneholder de to foerste tallene
        rader = Integer.parseInt(inforadkol[0]); //gjoer string om til int
        kolonner = Integer.parseInt(inforadkol[1]); //gjoer string om til int
        Rute[][] todimensjonell = new Rute[rader][kolonner]; //setter intene i rutekartet, slik at den vet hvor stor den skal vaere
        Labyrint nyLabyrint = new Labyrint(rader, kolonner, todimensjonell);//lag ny labyrint kolonne stoerrelse, radstoerrelsen og kartet

        int rad = 0; //deklarerer rad
        while(lesfil.hasNextLine()){ //for hver linje...
            int kol = 0; //deklarerer kolonne
            Rute[] radikart = new Rute[kolonner]; //setter en kolonne i raden i kartet

            String lest = lesfil.nextLine(); //sjekker neste linke
            String[] tegn = lest.split(""); //splitter den linja slik at hvert tegn er i en liste
            for(String t : tegn){ //for tegn i lista
                Rute nyRute = null; //lag ny rute
                if(t.equals("#")){ //hvis tegnet er # (altsaa, ruten er sort)
                    nyRute = new SortRute(kol, rad, nyLabyrint);//lag ny sort rute
                }
                else if (t.equals(".")){ //om tegnet er .
                    if(rad == 0 || rad == rader-1 || kol == 0 || kol == kolonner-1){ //hint 2, sjekker om det er aapning eller vanlig vei
                        nyRute = new Aapning(kol, rad, nyLabyrint);//lag aapning
                    }
                    else{//ellers lag hvit rute
                        nyRute = new HvitRute(kol, rad, nyLabyrint);
                    }
                }
                radikart[kol] = nyRute;//setter ruten i kolonnen, som er i raden
                kol++; //kol blir oekt med en
            }
            todimensjonell[rad] = radikart; //setter en rad med mange kolonner i kartlista
            rad++; //oeker rad med en
        } //og dette skjer til todimensjonell er fyllt med hvite/svarte ruter

        //hint 1, finnnabo, siden det kan hjelpe med navigasjon
        for(int i = 0; i < rader; i++){ //for hver rad i labyrinten
            for(int j = 0; j < kolonner; j++){ //for hver kolonne i rad
                Rute over = null; //lag naboer
                Rute under = null;
                Rute venstre = null;
                Rute hoeyre = null;
                Rute denneRuten = null; //og en hjelperute/ruten som skal ha disse naboene
                denneRuten = todimensjonell[i][j]; //ruten er i posisjonen som loekken er i

                if(i>0){ //hvis i er stoerre enn 0 / ikke er 0, altsaa, det er en nabo over den
                    over = todimensjonell[i-1][j]; //gjoer over naboen om til raden over
                }
                if(i<rader-1){ //hvis denne ruten er i raden nest nederst eller hoyere
                    under = todimensjonell[i+1][j]; //gjoer raden under den om til en nabo
                }
                if(j>0){ //hvis ruten er lenger til hoeyre en kolonna lengs til venstre
                    venstre = todimensjonell[i][j-1]; //gjor kolonna til venstre om til naboen
                }
                if(j<kolonner-1){ //hvis ruten er i en kolonne som ikke er lengst til hoyre
                    hoeyre = todimensjonell[i][j+1]; //gjor kolonna til hoeyre om til hoeyre nabo kolonnen
                }
                denneRuten.nyNabo(over, under, venstre, hoeyre); //lagre naboene i kolonnen
            }
        }
        return nyLabyrint; //returner labyrinten
    }
    
}