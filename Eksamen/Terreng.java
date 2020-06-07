import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Terreng {
    Lenkeliste<Sted> steder = new Lenkeliste<Sted>();
    Lenkeliste<Gjenstand> gjenstander = new Lenkeliste<Gjenstand>();

    public Terreng(){
        lesSteder();
        lesGjenstander();
        fyllOppSteder();
    }

    private void fyllOppSteder(){
        Random tall = new Random();
        for(Sted s : steder){
            int gjenstandikista = 0;
            int kisteStor = tall.nextInt(13-5)+5;
            Skattekiste nyKiste = new Skattekiste(kisteStor);
            while(gjenstandikista != kisteStor){
                gjenstandikista++;
                int tilfGjenstand = tall.nextInt(40);
                Gjenstand nyGjen = gjenstander.hent(tilfGjenstand);
                nyKiste.leggTil(nyGjen);
            }
            s.leggTilSkatt(nyKiste);
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

    private void lesSteder(){
        File fil = new File("steder.txt");
        Scanner lesfil = null;

        try{
            lesfil = new Scanner(fil);
        }
        catch(FileNotFoundException e){
            System.out.println("fant ikke steder.txt");
        }

        while(lesfil.hasNextLine()){
            String sted = lesfil.nextLine();
            Sted nysted = new Sted(sted);
            steder.leggTil(nysted);
        }

        //test for lesfil
        /*
        for(Sted s : steder){
            System.out.println(s.posisjon);
        }
        */
    }

    private void lesGjenstander(){//lesfil for gjenstander
        File fil = new File("gjenstander.txt");
        Scanner lesfil = null;
        
        try{
            lesfil = new Scanner(fil);
        }
        catch(FileNotFoundException e1){
            System.out.println("fant ikke gjenstander.txt");
        }

        while(lesfil.hasNextLine()){
            String info = lesfil.nextLine();
            String[] infoer = info.split(" ");
            String navn = infoer[0];
            int tall = Integer.parseInt(infoer[1]);
            Gjenstand nyGjenstand = new Gjenstand(tall, navn);
            gjenstander.leggTil(nyGjenstand);
        }

        int pos = 0;
        for(int i = 0; i<(steder.stoerrelse()-1); i++){
            steder.hent(i).utgang = steder.hent(i+1);
        }

        //test for gjenstander
        /*
        for(Gjenstand g : gjenstander){
            System.out.println(g.navn + " " + g.verdi);
        }
        */
    }

    public Sted hentStart(){
        return steder.hent(0);
    }
}