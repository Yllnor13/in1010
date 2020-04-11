public class SortertLenkeliste<T extends Comparable <T>> extends Lenkeliste<T>{
    @Override
    public void leggTil(T x){ //ny metode som overskriver
        if(stoerrelse() == 0){ //sjekker om storrelsen er 0
            super.leggTil(x); //isaafall, saa er det bare aa bruke den vanlige leggtil metoden
        }
        else if(stoerrelse() > 1){ //dersom om det er mer enn 1 (hadde denne paa 0 foer, men da funka ikke alt)
            for(int i=0; i < stoerrelse(); i++){ //for lokke som gaar gjennom alt
                if(hent(i).compareTo(x) > 0){ //om dataen paa der hvor for loopen er na er storre enn verdien x
                  super.leggTil(i,x);  //legg til dataen i den posisjonen, og dytt det som var det foer bak
                  return; //ferdig her og stopper
                }
            }
            super.leggTil(x); //hvis x er den stoerste verdien, saa gar den til slutt
        }
        else if (stoerrelse() == 1){ //om det bare er en node
            if(hent(0).compareTo(x) < 0){  //om x er storre
              super.leggTil(x); //sett den til slutt
            }else{ //hvis ikke, 
              super.leggTil(0,x); //sett den forst
            }
        }
    }

    @Override
    public T fjern(){ //kunne ikke hente over metoden, fikk overflow, men alt dette er fra lenkeliste, og er forklart der
        int pos = (stoerrelse() - 1);
        if(pos > 0 && pos < stoerrelse()){
            Node hjelpepeker = start;
            for (int i = 0;i < (pos-1); i++){
                hjelpepeker = hjelpepeker.neste;
            }
            Node n = hjelpepeker.neste;
            hjelpepeker.neste = n.neste;
            return n.data;
        }
        else if(pos == 0){
            if(stoerrelse() > 0 && start != null){
                T fjern = null;
                if(start != null){
                    fjern = start.data;
                    start = start.neste;
                }
                return fjern;
            }
            else{
                throw new UgyldigListeIndeks(-1);
            }
        }
        else{
            throw new UgyldigListeIndeks(pos);
        }
    }

    @Override
    public void sett(int pos, T x) throws UnsupportedOperationException{ //faar error om man bruker dette
        throw new UnsupportedOperationException();
    }

    @Override
    public void leggTil(int pos, T x) throws UnsupportedOperationException{ //faar error om man bruker dette
        throw new UnsupportedOperationException();
    }
}