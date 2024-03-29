
abstract public class Rute {
    protected int kolonnepos;//hvilken kolonne den er i
    protected int radpos; // hvilken rad den er i
    //hint1
    protected Rute over; //naboer fra hintet
    protected Rute under;
    protected Rute venstre;
    protected Rute hoeyre;
    //Liste<Rute> naboeruno; klarte ikke aa lage en for loekke med denne
    protected Rute[] naboer; //nabolisten
    protected boolean traakketpaa; //skulle se om den var trakket paa
    Rute forrigeRute;
    protected static Liste<String> utveiListe; //liste med utveier som skal til labyrint, slik at det ikke kan vaere en utvei uten aapning
    //raad fra en venn
    protected int tall; //blir brukt senere slik at foerste steg ikke blir lagret 2 ganger

    public Rute(int kpos, int rpos){
        this.kolonnepos = kpos;
        this.radpos = rpos;
    }
    
    private boolean seeOmTraaket(){
        return traakketpaa;
    }

    public void gaa(String utvei){
        System.out.println(this.lagrePos());
        for(Rute r : naboer){
            if(r.sjekkUtvei()){
                traakketpaa = true;
                utvei += this.lagrePos();
                return;
            }
            else if(this.erHvit() && r.erHvit() && r.seeOmTraaket() == false){
                traakketpaa = true;
                utvei += this.lagrePos() + " --> ";
                r.lagreVei(this);
                r.gaa(utvei);
            }
            else if(r.erHvit() && r.seeOmTraaket() == true){
                traakketpaa = true;
                utvei += this.lagrePos() + " --> ";
                forrigeRute.gaa(utvei);
            }
        }
        traakketpaa = false;
    }

    public void lagreVei(Rute nr){
        forrigeRute = nr;
    }

    private String lagrePos(){ //for aa skrive utvei
        return "(" + kolonnepos + ", " + radpos +")";
    }

    public void finnUtvei(){
        String vei = "";
        this.gaa(vei);
    }

    public abstract char tilTegn();
    public abstract boolean sjekkUtvei();
    
    public boolean erHvit(){
        return false;
    }

    //hint1
    public void nyNabo(Rute opp, Rute ned, Rute ven, Rute hoey){
        Rute over = opp;
        Rute under = ned;
        Rute venstre = ven;
        Rute hoeyre = hoey;
        //naboeruno.leggTil(over);
        //naboeruno.leggTil(under);
        //naboeruno.leggTil(venstre);
        //naboeruno.leggTil(hoeyre);
        naboer = new Rute[]{over, under, venstre, hoeyre};
    }
}