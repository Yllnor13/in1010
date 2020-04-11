import java.util.Scanner; //rester fra da jeg skulle prove å scanne en fil
import java.io.File;

public class Hovedprogram{ //ny klasse
    File ff = new File("Hovedprogram.txt");
    public static void main(String[] args){ //main kode som skal kjøres
        Dataklynge abel = new Dataklynge(12); //new klynge som heter abel med 12 nodes per rack


        for(int i = 0; i < 650; i++){ //for løkke som skal legge til 650 noder i dataklyngen
            Node nynod = new Node(64, 1); //hver node skal ha 64 gb i minne og 1 prosess
            abel.settinnnodeledig(nynod); //legger til nodene i ledige racks
        }

        for (int i = 0; i < 16; i++) { //for løkke som skal legge til 16 noder
            Node nynod1 = new Node(1024, 2); //lager nye noder som har 1024 gb i minne og 2 prosesser
            abel.settinnnodeledig(nynod1); //setter inn node inn i racken
        }


        System.out.println("Antall noder med 32GB eller mer: " + abel.nokminne(32)); //printer ut noder med 32 gb eller mer
        System.out.println("Antall noder med 64GB eller mer: " + abel.nokminne(64)); //printer ut noder med 64 gb eller mer
        System.out.println("Antall noder med 128GB eller mer: " + abel.nokminne(128)); //printer ut noder med 128 gb eller mer
    
        System.out.println("Antall prosessorer: " + abel.antallprostotal()); //printer ut antall prosesser
        System.out.println("Antall rack: " + abel.antracks()); //printer ut antall racks
    }
}