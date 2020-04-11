import java.util.ArrayList; //importerer arraylist
import java.util.Scanner; //importerer scanner
import java.io.File;
import java.io.FileNotFoundException;

public class Dataklynge{ //starter klassen
    private ArrayList<Rack> rackListe = new ArrayList<Rack>();
    private int NPR = 0;
    boolean sjekk = true;

    public Dataklynge(String f) { //konstruktooren med hvor mange noder skal vaere i hver rack
        try {
          File ff = new File(f); //finner fil med samme navn som stringen
          Scanner scann = new Scanner(ff); //lager scann objekt av filen
            NPR = Integer.parseInt(scann.nextLine( )); //finner noderperrack av aa se paa neste(foerste) linje
            while(scann.hasNext()){ //mens det fortsatt er noe etter siste int som ble sett
                int antnode = scann.nextInt(); //gjor neste int om til hvor mange noder som skal vaere der
                int antminner = scann.nextInt(); //gjoer neste int om til hvor mye minne nodene skal ha
                int antpros = scann.nextInt(); //gjoer neste int om til hvor mange prosesser nodene skal ha

                for (int i = 0; i < antnode; i++){ //fot sjekk som skal sette nodene der hvor det er ledig plass i racken
                    Node noden = new Node(antminner, antpros); //lager ny node
                    settinnnodeledig(noden); //setter noden inn i racket der det er ledig
                }
            }
        }
        catch (FileNotFoundException ex){ //exception kode hvis filen ikke blir funnet
            sjekk = false;
            System.out.println("fant ikke fila");
        }
    }

    public boolean sjekkomtrue(){ //sjekker om exception koden kjoerte og stopper hovedprogram fra aa kjoere printkoden
        return sjekk;
    }

    public void settinnNyRack(Rack Racke){//setter inn ny rack i klyngen
        rackListe.add(Racke);
    }

    public void settinnnodeledig(Node node){ //setter inn node der det er ledig
        if (rackListe.size()==0){ //ser om det ikke er racks i lista
            Rack ny = new Rack(); //lager ny rack
            settinnNyRack(ny); //bruker sett inn metoden til aa legge til racken som nettopp ble laget
            ny.setinn(node); //setter inn noden
        }
        else{//ellers
            Rack rac = rackListe.get(rackListe.size()-1); //ser paa den siste racken i lista

            if (rac.getAntNoder() < NPR){ //om den har mer plass til flere noder
                rac.setinn(node); //sett inn noden
            }
            else{ //ellers, om det ikke er plass
                Rack ny = new Rack(); //lag ny rack
                settinnNyRack(ny); //setter inn ny rack
                ny.setinn(node); //setter inn noden
            }
        }
    }
    public int antallprostotal(){ //regner ut antall prosesser
        int antpro = 0;
        for(int i = 0; i < rackListe.size(); i++){ //for each lokke
            Rack ny = rackListe.get(i);//ser hver node i racklista
            antpro = antpro + ny.getAntPros();//legger til prosessene i en node i en variabel
        }
        return antpro;
    }
    public int nokminne(int krav){//ser om det er nok minne med hvor mye minne trengs som parameter for racket
        int kravminne = 0; //
        for(int i = 0; i < rackListe.size(); i++){ //for each lokke som gaar gjennom hver node
            Rack ny = rackListe.get(i);
            kravminne = kravminne + ny.racknokminne(krav); //legger til mengden av noder med nok minne i variabelen
        }
        return kravminne;
    }

    public int antracks(){ //viser antall racks
        return rackListe.size(); //henter bare ut stoerrelsen av racklisten
    }
}
