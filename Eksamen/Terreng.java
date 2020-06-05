import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Terreng {
    Lenkeliste<Sted> steder = new Lenkeliste<Sted>();

    public Terreng(File filnavn) throws FileNotFoundException{
        Scanner lesfil = null;

        try{
            lesfil = new Scanner(filnavn);
        }
        catch(FileNotFoundException e){
            System.out.println("fil ikke funnet lol");
        }
        while(lesfil.hasNextLine()){
            String sted = lesfil.nextLine();
            Sted nysted = new Sted(sted);
            steder.leggTil(nysted);
        }

        int pos = 0;
        for(Sted sted : steder){
            pos++;
            sted.utgang = steder.hent(pos);
        }
    }

    public Sted hentStart(){
        return steder.hent(0);
    }
}