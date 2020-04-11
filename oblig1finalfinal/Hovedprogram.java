import java.util.Scanner; //rester fra da jeg skulle prove aa scanne en fil
import java.io.File;
import java.io.FileNotFoundException;

public class Hovedprogram{ //ny klasse
    public static void main(String[] args) throws FileNotFoundException{ //main kode som skal kjores
        Dataklynge abel = new Dataklynge("Hovedprogram.txt"); //new klynge som heter abel med 12 nodes per rack

        /*for(int i = 0; i < 650; i++){ //for lokke som skal legge til 650 noder i dataklyngen
            Node nynod = new Node(64, 1); //hver node skal ha 64 gb i minne og 1 prosess
            abel.settinnnodeledig(nynod); //legger til nodene i ledige racks
        }

        for (int i = 0; i < 16; i++) { //for lokke som skal legge til 16 noder
            Node nynod1 = new Node(1024, 2); //lager nye noder som har 1024 gb i minne og 2 prosesser
            abel.settinnnodeledig(nynod1); //setter inn node inn i racken
        }*/
        if(abel.sjekkomtrue()){
            System.out.println("Antall noder med 32GB eller mer: " + abel.nokminne(32)); //printer ut noder med 32 gb eller mer
            System.out.println("Antall noder med 64GB eller mer: " + abel.nokminne(64)); //printer ut noder med 64 gb eller mer
            System.out.println("Antall noder med 128GB eller mer: " + abel.nokminne(128)); //printer ut noder med 128 gb eller mer

            System.out.println("Antall prosessorer: " + abel.antallprostotal()); //printer ut antall prosesser
            System.out.println("Antall rack: " + abel.antracks()); //printer ut antall racks
        }
    }
}
