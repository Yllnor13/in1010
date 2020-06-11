import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException; //importer som jeg trenger for at  alt skal funke

public class Skattekiste {
    public ArrayList<Gjenstand> gjenstander = new ArrayList<Gjenstand>(); //bruker Lenkeliste paa andre steder, men matte bruke arraylist her for aa unnga aeen error
    int maksantall; //hvor mye kista kan ha som maksimun
    int naa = maksantall; //hvor mye kinsta har til et hvert oyeblikk


    public Skattekiste(int plass){ //man velger hvor mye plass kista har som parameter
        maksantall = plass;
    }

    public int leggTil(Gjenstand gjen){ //her legger man til gjenstand og faar tilbake hvor mye greia var verdt
        naa++; //oeker hvor mye kista har naa
        gjenstander.add(gjen);
        Random tilf = new Random(); //kista velger et tilfeldig tall
        int tilftall = tilf.nextInt(5);
        if(tilftall == 0){ //baasert paa hva tallet blir saa far man en en annen verdig av det objektet egentlig er verdt
            int betaling = (gjen.verdi/5) * 4;
            return betaling;
        }
        else if(tilftall == 1){
            int betaling = gjen.verdi;
            return betaling;
        }
        else if(tilftall == 2){
            int betaling = (gjen.verdi/5) * 6;
            return betaling;
        }
        else if(tilftall == 3){
            int betaling = (gjen.verdi/5) * 3;
            return betaling;
        }
        else if(tilftall == 4){
            int betaling = (gjen.verdi/5) * 7;
            return betaling;
        }
        return 0; //bare slik at noe blir returnert, dette kommer aldri til aa skje
    }

    public int taUt(){
        if(naa == 0){ //sjekker om kista er tom
            System.out.println("det er ingenting i kista");
            return naa;
        }
        else{ //noe tilfeldig blir tatt ut
            Random tilf = new Random();
            int tilftall = tilf.nextInt(gjenstander.size()-1); //maa ta -1 fordi jeg endte opp med index errorer noenganger
            gjenstander.remove(tilftall);
            naa--; //senker hvor mye som er i kista naa
            return tilftall; //returnerer tallet man trenger
        }
    }

    public ArrayList<Gjenstand> hentGjenstander(){ //henter lista
        return gjenstander;
    }

    public int hentMaksantall(){ //henter maksantallet
        return maksantall;
    }
}