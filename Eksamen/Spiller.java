public class Spiller {
    int trekk;
    Sted lokasjon;
    Brukergrensesnitt grensesnitt;
    int formue;
    Skattekiste ryggsekk;
    
    public Spiller(Sted start, Brukergrensesnitt spiller){
        lokasjon = start;
        grensesnitt = spiller;
    }

    public void NyttTrekk(){
        grensesnitt.beOmKommando();

    }
}