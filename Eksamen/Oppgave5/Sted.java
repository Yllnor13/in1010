public class Sted {
    String posisjon;
    Skattekiste rikdom;
    public Sted utgang;

    public Sted(String sted){
        posisjon = sted;
    }

    public void leggTilSkatt(Skattekiste skatt){
        rikdom = skatt;
    }

    public Skattekiste hentRikdom(){
        return rikdom;
    }

    public Sted gaaVidere(int index){
        return utgang;
    }

    public void leggTilUtgang(Sted nyUtgang){
        utgang = nyUtgang;
    }

    public String toString(){
        return posisjon;
    }
}