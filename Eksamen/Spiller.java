public class Spiller {
    int trekk;
    Sted lokasjon;
    Brukergrensesnitt grensesnitt;
    int formue;
    Skattekiste ryggsekk = new Skattekiste(12);
    String spoersmal = "hva oensker du aa gjoere? (skriv tallet)";
    String[] alternativer = {"0. Legge fra en gjenstand.", "1. Ta ut en gjenstand.", "2. Gaa videre."};
    
    public Spiller(Sted start, Brukergrensesnitt spiller){
        lokasjon = start;
        grensesnitt = spiller;
    }

    public void nyTrekk(){
        int valg = grensesnitt.beOmKommando(spoersmal, alternativer);
        if (valg == 0){
            if(ryggsekk.gjenstander.stoerrelse() == 0){
                System.out.println("du har ikke noe paa deg");
            }
            else{
                System.out.println("hva vil du legge fra deg?");
                for (Gjenstand g : ryggsekk.gjenstander){
                System.out.println(g.navn + ", som er verdt: " + g.verdi + "?");
            }
            }
        }
        else if(valg == 1){

        }
        else if(valg == 2){
            lokasjon = lokasjon.gaaVidere();
        }
    }
}