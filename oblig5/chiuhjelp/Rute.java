
abstract public class Rute {
    protected int kolonnepos;
    protected int radpos;
    //hint1
    protected Rute over;
    protected Rute under;
    protected Rute venstre;
    protected Rute hoeyre;
    //Liste<Rute> naboeruno; klarte ikke aa lage en for loekke med denne
    protected Rute[] naboer;
    protected boolean traakketpaa;
    //Rute forrigeRute;
    protected static Liste<String> utveiListe; //liste med utveier som skal til labyrint, slik at det ikke kan vaere en utvei uten aapning
    //raad fra en venn
    protected int tall;

    public Rute(int kpos, int rpos){
        this.kolonnepos = kpos;
        this.radpos = rpos;
    }
    
    private boolean seeOmTraaket(){
        return traakketpaa;
    }

    public void gaa(String utvei){
        System.out.println(this.toString());
        for(Rute r : naboer){
            if(this.sjekkUtvei()){
                utvei += this.toString();
                utveiListe.leggTil(utvei);
                return;
            }
            if(this.erHvit() && r.erHvit() && r.traakketpaa == false){
                if(tall == 0){
                    utvei += this.toString() + " --> ";
                    tall ++;
                }
                traakketpaa = true;
                r.gaa(utvei);
            }
        }
        traakketpaa = false;
        tall = 0;
    }

    public String toString(){ //for aa skrive utvei
        return "(" + kolonnepos + ", " + radpos +")";
    }

    public Liste<String> finnUtvei(){
        utveiListe = new Lenkeliste<String>(); //slik at jeg kan bruke leggtil metoden
        String vei = "";
        this.gaa(vei);
        return utveiListe;
    }

    public abstract char tilTegn();

    public boolean sjekkUtvei(){
        return false;
    }
    
    public boolean erHvit(){
        return false;
    }

    //hint1
    public void nyNabo(Rute opp, Rute ned, Rute ven, Rute hoey){
        over = opp;
        under = ned;
        venstre = ven;
        hoeyre = hoey;
        //naboeruno.leggTil(over);
        //naboeruno.leggTil(under);
        //naboeruno.leggTil(venstre);
        //naboeruno.leggTil(hoeyre);
        naboer = new Rute[]{over, under, venstre, hoeyre};
    }
}