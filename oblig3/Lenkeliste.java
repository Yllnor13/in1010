public class Lenkeliste<T> implements Liste<T>{
    
    Node inngang;

    class Node{ //klasse node som skal vaere i lista
        Node neste = null; //peker paa neste
        T data; //t er data
        Node(T x){ //konstruktoer
            data = x; //data er det man skriver inn i x
        }
    }

    public Node start = null; //skal vaere starten av lista
    private Node slutt = null; //skal vaere slutten av lista

    @Override
    public int stoerrelse(){ //sjekker stoerrelsen
        Node hjelpepeker = start; //hjelpepeker er paa start
        int stor = 0; //storrelsen er 0 intill videre
        while(hjelpepeker != null){ //hvis start ikke er null/ingenting (altsaa, det er en start der)
            stor++; //ook stoerrelsen med 1
            hjelpepeker = hjelpepeker.neste; //gaa til neste node
        }
        return stor; //returnerer stoerrelsen til slutt
    }

    @Override
    public void leggTil(int pos, T x){ //metode som skal legge til node i en posisjon, og dytte en annen node videre
        Node nyNode = new Node(x); //nye noden med verdien til x
        if (pos < 0 || pos > stoerrelse() ){ //om man skriver noe som er mindre enn null eller storre enn lista
            throw new UgyldigListeIndeks(pos); //saa er det ugyldig
        }
        else if(pos == 0){ //om man skriver 0
            nyNode.neste = start; //saa setter man noden foran start
            start = nyNode; //og gjor noden om til det nye start punktet
        }
        else if (pos == stoerrelse()){ //om pos er like stor som sterrelsen
            leggTil(x); //bruk leggtil metoden
        }
        else if (start == null){ //om start ikke finnes
            start = nyNode; //saa blir den nye noden om til start
        }
        else if(pos>0){ //om man skriver noe som er stoerre enn 0
            Node hjelpepeker = start; //hjelpepekeren er pa start
            Node pekpaany = null; //den som skal peke paa den nye finnes ikke enda
            for (int i = 0; i < pos; i++){ //gaar gjennom lista til den er framme til posisjonen
                pekpaany = hjelpepeker; //pek paa ny blir om til hjelpepeker
                hjelpepeker=hjelpepeker.neste; //hjelpepeker gar ett hakk til hoeyre
            }
            pekpaany.neste = nyNode; //node blir plassert etter pekpaa ny
            nyNode.neste = hjelpepeker; //og etter node setter man hjelpepeker, altsaa, nynode blir satt mellom 2 noder
        }
    }

    @Override
    public void leggTil(T x){ //metode som skal legge til en node ved slutten av lista
        Node nyNode = new Node(x);//den nye noden blir laget med x som data
        if(start == null){ //om det ikke finnes en start (lista er tom)
            start = nyNode; //sa er nyNode starten
        }
        else{ //ellers (om det er noe i lista fra for av)
            Node hjelpepeker = start; //saa er hjelpepekeren start
            while(hjelpepeker.neste != null){ //mens den har en nabo node etter seg
                hjelpepeker = hjelpepeker.neste; //sa gar den videre ett hakk
            }
            hjelpepeker.neste = nyNode; //og setter inn den nye noden ved slutten av lista
        }
    }


    @Override
    public void sett(int pos, T x){ //erstatter et element med et annet
        if(start == null || pos < 0 || pos >= stoerrelse()){ //om det ikke finnes neon noder, eller man har skrevet posisjon mindre enn null, eller noe som er stoerre enn stoerrelsen
            throw new UgyldigListeIndeks(0); //saa far man error
        }
        if(pos < stoerrelse()){ //hvis pos er mindre enn stoerrelsen
            if(stoerrelse() > 0){ //og stoerrelsen er stoerre enn null
                Node hjelpepeker = start; //hjelpepekeren er paa start
                for(int i = 0;i < pos; i++){ //gaar gjennom lista
                    hjelpepeker = hjelpepeker.neste; //og setter hjelpepeker et hakk videre
                }
                hjelpepeker.data = x; //naar man har nadd fram, sa erstatter man dataen der
            }
            else if(pos == 0){ //om posisjonen er 0
                start.data = x; //erstatt dataen ved start
            }
        }
        else if(stoerrelse() == 0){ //om sterrelsen er 0, atsa, det er ikke noe der
            throw new UgyldigListeIndeks(-1); //error melding
        }
        else{
            throw new UgyldigListeIndeks(0); //ellers, error melding
        }
    }

    @Override
    public T hent(int pos) throws UgyldigListeIndeks{ //henter ut dataen av en node
        if(pos > 0 && pos < stoerrelse()){ //om pos er mellom 0 og stoerrelsen
            Node hjelpepeker = start; //hjelpepeker er paa start
            for (int i=0;i<pos;i++){ //gaar gjennom lista
                hjelpepeker = hjelpepeker.neste; //hjelpepeker gaar ett hakk videre til den er framme til posisjonen
            }
            return hjelpepeker.data; //returnerer dataen
        }
        else if(pos == 0){ //om man skriver pos 0 (altsaa starten)
            if(stoerrelse() > 0){ //og lista har noder
                return start.data; //saa returnerer man dataen til starten
            }
        }
        throw new UgyldigListeIndeks(-1); //ellers, error
    }

    @Override
    public T fjern(){ //fjerner dataen fra starten, siden det er foerst inn foerst ut
        if(stoerrelse() > 0 && start != null){ //om stoerrelsen er stoerre enn 0, og start finnes
            T fjern = null; //ny t som er ingenting
            fjern = start.data; //fjern blir om til start dataen
            start = start.neste; //start blir flyttet til neste node
            return fjern; //returnerer det som ble tatt bort
        }
        else{ //ellers
            throw new UgyldigListeIndeks(-1); //error
        }
    }

    @Override
    public T fjern(int pos){ //metode som skal fjerne noe fra en posisjon
        if(pos > 0 && pos < stoerrelse()){ //om man skriver noe mellom 0 og stoerrelse
            Node hjelpepeker = start; //hjelpepeker starter fra begynnelsen
            for (int i = 0;i < (pos-1); i++){ // gar gjennom lista helt til noden foran den man skal fjaerne
                hjelpepeker = hjelpepeker.neste; //setter hjelpepeker ett hakk videre
            }
            Node fjernetNode = hjelpepeker.neste; //noden som skal bli fjernet er noden etter hjelpepekeren
            hjelpepeker.neste = fjernetNode.neste; //hjelpepekeren peker paa noden etter noden som skal bli fjernet, altsaa, men peker forbi den fjernede noden
            return fjernetNode.data;//returnerer noden som ble fjernet
        }
        else if(pos == 0){ //om man skriver 0
            return fjern(); //bruk fjern metoden
        }
        else{
            throw new UgyldigListeIndeks(pos); //ellers, errormelding
        }
    }
}