import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Terreng {
    Lenkeliste<Sted> steder = new Lenkeliste<Sted>();

    public Terreng(File fil) throws FileNotFoundException{
        Scanner lesfil = null;

        try{
            lesfil = new Scanner(fil);
        }
        catch(FileNotFoundException e){
            System.out.println("fil ikke funnet lol");
        }
        while(lesfil.hasNextLine()){
            String sted = lesfil.nextLine();
            Sted nysted = new Sted(sted);
            steder.leggTil(nysted);
        }

        Scanner lesfil2 = null;
        File nyfil = new File("gjenstander.txt");
        

        int pos = 0;
        for(int i = 0; i<(steder.stoerrelse()-1); i++){
            steder.hent(i).utgang = steder.hent(i+1);
        }
    }

    public Sted hentStart(){
        return steder.hent(0);
    }
}