
abstract public class Rute{
    protected int kolonnepos;//hvilken kolonne den er i
    protected int radpos; // hvilken rad den er i
    //hint1
    protected Rute over; //naboer fra hintet
    protected Rute under;
    protected Rute venstre;
    protected Rute hoeyre;
    protected Labyrint laby;
    protected boolean traakketpaa;
    protected Rute[] naboer; //nabolisten
    Rute forrigeRute;//brukes slik at ruten vet hvor den var
    protected int tall; //blir brukt senere slik at foerste steg ikke blir lagret 2 ganger

    public Rute(int kpos, int rpos, Labyrint lab){ //konstruktoer som lagrer posisjonen til ruten i rute
        this.kolonnepos = kpos;
        this.radpos = rpos;
        this.laby = lab;
    }
    
    private boolean seeOmTraaket(){ //fra trakket paa
        return traakketpaa;
    }

    public abstract void gaa(Rute forirute, String vei); //hver type rute har sin egen mate aa gaa paa

    public String toString(){ //for aa skrive utvei
        return "(" + kolonnepos + ", " + radpos +")";
    }

    public void finnUtvei(){ //finner utvei
        String vei = "";
        this.gaa(null, vei);
    }

    public abstract char tilTegn();

    //hint1
    public void nyNabo(Rute opp, Rute ned, Rute ven, Rute hoey){ //lagrer liste av naboer i denne metoden
        venstre = ven;
        hoeyre = hoey;
        over = opp;
        under = ned;
        //naboeruno.leggTil(over);
        //naboeruno.leggTil(under);
        //naboeruno.leggTil(venstre);
        //naboeruno.leggTil(hoeyre);
        naboer = new Rute[]{venstre, hoeyre, over, under};
    }
}