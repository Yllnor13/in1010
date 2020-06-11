public interface Brukergrensesnitt {
    public void giStatus(String status); //som gir brukeren informasjon om det som skjer i spillet (foreksempel om det stedet spilleren er kommet til). 

    public int beOmKommando(String spoersmal, String[] alternativer); //som gir brukeren et valg (parameteren spoersmaal) med noen alternativer (parameteren alternativer). Metoden returnerer et heltall som angir indeksen for det valgte alternativet.

}