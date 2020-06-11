import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Terreng {
    Lenkeliste<Sted> steder = new Lenkeliste<Sted>(); //liste med steder
    Lenkeliste<Gjenstand> gjenstander = new Lenkeliste<Gjenstand>(); //liste med gjenstander
    Lenkeliste<VeivalgSted> stedsliste = new Lenkeliste<VeivalgSted>();

    public Terreng(){ //kostruktoer. utfoerer metodene som trengs for aa lage steder og fylle dem opp med kister som er fyllte med gjenstander
        lesSteder();
        lesGjenstander();
        fyllOppSteder();
    }

    public void fyllOppSteder(){ //metode for aa fylle opp steder
        Random tall = new Random(); //lager random for a velge tilfeldige tall senere
        for(Sted s : steder){ //for loekke som gaar gjennom hvert sted
            int gjenstandikista = 0; //skal bli brukt til while loekke
            int kisteStor = tall.nextInt(13-5)+5; //velger tilfeldig tall paa stoerrelse til kiste
            Skattekiste nyKiste = new Skattekiste(kisteStor); //lager ny kiste med det tilfeldige tallet
            while(gjenstandikista != kisteStor){ //mens kista ikke er full
                gjenstandikista++; //ook gjenstand i kista
                int tilfGjenstand = tall.nextInt(40); //velg tilfeldig gjenstand
                Gjenstand nyGjen = gjenstander.hent(tilfGjenstand); //lag gjenstanden med aa velge et tall mellom 0-39
                nyKiste.leggTil(nyGjen); //legge til gjenstanden
            }
            s.leggTilSkatt(nyKiste); //legge til kista
        }
        //test for aa see om alt er fullt
        /*
        for(Sted s : steder){
            System.out.println(s.posisjon + " og i kista saa er det");
            for(Gjenstand g : s.rikdom.gjenstander){
                System.out.print(g.navn + ", ");
            }
            System.out.println("\n");
        }
        */
        
    }

    public void lesSteder(){ //metode for aa lese steder
        File fil = new File("steder.txt"); //lager fil ut av steder
        Scanner lesfil = null; //ny scanner for a lese fil

        try{
            lesfil = new Scanner(fil); //scanner med fil
        }
        catch(FileNotFoundException e){
            System.out.println("fant ikke steder.txt");
        }

        while(lesfil.hasNextLine()){ //leser hver linje
            String sted = lesfil.nextLine();//hver linje er eget sted
            Sted nysted = new Sted(sted); //lager sted med linja som den er i
            steder.leggTil(nysted); //legger til sted i lista
        }

        int pos = 0;
        for(int i = 0; i<(steder.stoerrelse()-2); i++){ //for loekke som gaar fra 0 til nesten stoerrelsen til steder
            steder.hent(i).leggTilUtgang(steder.hent(i+1)); //gjoer utvei til sted om til neste sted i lista
        }
        steder.hent(steder.stoerrelse()-1).leggTilUtgang(steder.hent(0)); //gjor det slik at siste sted sin utvei er foerste sted

        //test for lesfil
        /*
        for(Sted s : steder){
            System.out.println(s.posisjon);
        }
        */
    }

    public void lesGjenstander(){//lesfil for gjenstander
        File fil = new File("gjenstander.txt"); //gjoer det samme som over nesten
        Scanner lesfil = null;
        
        try{
            lesfil = new Scanner(fil);
        }
        catch(FileNotFoundException e1){
            System.out.println("fant ikke gjenstander.txt");
        }

        while(lesfil.hasNextLine()){ //hver linje har navn paa gjenstand og hvor mye den er verdt
            String info = lesfil.nextLine(); //lagrer linja som string
            String[] infoer = info.split(" "); //lager liste av linja
            String navn = infoer[0]; //navn er foerste i lista
            int tall = Integer.parseInt(infoer[1]); //verdi er andre i lista
            Gjenstand nyGjenstand = new Gjenstand(tall, navn); //lager gjenstand med navn og verdi
            gjenstander.leggTil(nyGjenstand); //legger til gjenstand i lista
        }

        //test for gjenstander
        /*
        for(Gjenstand g : gjenstander){
            System.out.println(g.navn + " " + g.verdi);
        }
        */
    }

    public Sted hentStart(){//skal returnere startsted
        Random tilf = new Random();
        int tilfstart = tilf.nextInt(steder.stoerrelse()-1);
        return steder.hent(tilfstart); //velger tilfeldig sted fra lista og gjoer det om til startsted
    }
}