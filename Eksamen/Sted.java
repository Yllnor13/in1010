public class Sted {
    String posisjon;
    Skattekiste rikdom;
    public Sted utgang;

    public Sted(String sted){
        posisjon = sted;
    }

    public void leggtilskatt(Skattekiste skatt){
        rikdom = skatt;
    }

    public Skattekiste hentRikdom(){
        return rikdom;
    }

    public Sted gaaVidere(){
        return utgang;
    }
}