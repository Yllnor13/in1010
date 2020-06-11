public class Gjenstand {
    int verdi; //verdien til gjenstant
    String navn; //navnet til gjenstant

    public Gjenstand(int penger, String gjenstandnavn){ //konstruktoer
        verdi = penger;
        navn = gjenstandnavn;
    }

    public int hentVerdi(){ //returnerer verdien
        return verdi;
    }

    public String hentNavn(){ //returnerer navnet
        return navn;
    }
}