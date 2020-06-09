public class Gjenstand {
    int verdi;
    String navn;

    public Gjenstand(int penger, String gjenstandnavn){
        verdi = penger;
        navn = gjenstandnavn;
    }

    public int hentVerdi(){
        return verdi;
    }

    public String hentNavn(){
        return navn;
    }
}