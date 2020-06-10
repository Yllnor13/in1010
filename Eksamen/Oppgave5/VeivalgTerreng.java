import java.util.Random;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class VeivalgTerreng extends Terreng{
    Lenkeliste<VeivalgSted> stedsliste = new Lenkeliste<VeivalgSted>(); //liste med utveier
    

    public VeivalgTerreng(){ //konstruktoer
        this.lesSteder();
        super.lesGjenstander();
        this.fyllOppSteder();
    }

    @Override
    public VeivalgSted hentStart(){
        Random tilf = new Random();
        int tilfstart = tilf.nextInt(stedsliste.stoerrelse()-1);
        return stedsliste.hent(tilfstart); //velger tilfeldig sted fra lista og gjoer det om til startsted
    }

    @Override
    public void fyllOppSteder(){ //metode for aa fylle opp steder
        Random tall = new Random(); //lager random for a velge tilfeldige tall senere
        for(VeivalgSted s : stedsliste){ //for loekke som gaar gjennom hvert sted
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

    @Override
    public void lesSteder(){ //gjoer det slik at veivalg versjoner blir laget istedenfor vanlig versjoner
        stedsliste = new Lenkeliste<VeivalgSted>();
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
            //System.out.println(sted);
            VeivalgSted nysted = new VeivalgSted(sted); //lager sted med linja som den er i
            //System.out.println(nysted);
            if(stedsliste != null){
                stedsliste.leggTil(nysted); //legger til sted i lista
            }
            else{
                System.out.println("det finnes ikke?");
            }
        }

        int pos = 0;
        for(int i = 0; i<(stedsliste.stoerrelse()-2); i++){ //for loekke som gaar fra 0 til nesten stoerrelsen til steder
            Random tilf = new Random();
            int tall1 = tilf.nextInt(40); //tilfeldig tall for aa velge tilfeldig sted for utgang
            while(stedsliste.hent(i) == stedsliste.hent(tall1)){ //i tilfelle utgang1 er det samme som stedet er i
                tall1 = tilf.nextInt(40); //lager ny tilfeldig tall til det ikke er det lenger
            }
            int tall2 = tilf.nextInt(40); //tilfeldig tall for aa velge tilfeldig sted for utgang
            while(stedsliste.hent(i) == stedsliste.hent(tall2)){ //i tilfelle utgang2 er det samme som stedet man er i
                tall2 = tilf.nextInt(40); //lager ny tilfeldig tall til det ikke er det lenger
            }
            stedsliste.hent(i).leggTilUtgang(stedsliste.hent(i+1),stedsliste.hent(tall2),stedsliste.hent(tall1)); //gjoer utvei til sted om til neste sted i lista
        }
        stedsliste.hent(stedsliste.stoerrelse()-2).leggTilUtgang(stedsliste.hent(2)); //gjor det slik at siste sted sin utvei er foerste sted

        //test for lesfil
        /*
        for(Sted s : steder){
            System.out.println(s.posisjon);
        }
        */
    }
}