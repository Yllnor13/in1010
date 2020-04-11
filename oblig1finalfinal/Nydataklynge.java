import java.util.Scanner; //importerer scanner

public class Dataklynge{ //starter klassen
    private int noderperrack = 0;
    public ArrayList <Rack> rackListe;
    
    public Dataklynge(File ff){ //konstruktooren med hvor mange noder skal vaere i hver rack
        Scanner scann = null;
        try {
            scann = new Scanner(ff);
        } catch (FileNotFoundException e){
            System.out.println("fant ikke fila");
        }
        noderperrack = ;
        rackListe = new ArrayList<Rack>();//lager ny rack
    }

    public void settinnNyRack(Rack Racke){//setter inn ny rack i klyngen
        rackListe.add(Racke);
    }

    public void settinnnodeledig(Node node){ //setter inn node der det er ledig
        if (rackListe.size()==0){ //ser om det ikke er racks i lista
            Rack ny = new Rack(); //lager ny rack
            settinnNyRack(ny); //bruker sett inn metoden til å legge til racken som nettopp ble laget
            ny.setinn(node); //setter inn noden
        }
        else{//ellers
            Rack rac = rackListe.get(rackListe.size()-1); //ser på den siste racken i lista
            
            if (rac.getAntNoder() < noderperrack){ //om den har mer plass til flere noder
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
        for(int i = 0; i < rackListe.size(); i++){ //for each lokke som går gjennom hver node
            Rack ny = rackListe.get(i);
            kravminne = kravminne + ny.racknokminne(krav); //legger til mengden av noder med nok minne i variabelen
        }
        return kravminne;
    }

    public int antracks(){ //viser antall racks
        return rackListe.size(); //henter bare ut størrelsen av racklisten
    }
}