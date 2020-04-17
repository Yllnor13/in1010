
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
    //Rute forrigeRute;
    protected static Liste<String> utveiListe; //liste med utveier som skal til labyrint, slik at det ikke kan vaere en utvei uten aapning
    //raad fra en venn
    protected int tall; //blir brukt senere slik at foerste steg ikke blir lagret 2 ganger

    public Rute(int kpos, int rpos){ //konstruktoer som lagrer posisjonen til ruten i rute
        this.kolonnepos = kpos;
        this.radpos = rpos;
    }
    
    private boolean seeOmTraaket(){ //fra trakket paa
        return traakketpaa;
    }

    public void gaa(String utvei){ //gaa metoden som tar imot utvei strengen som skal bli ookt
        for(Rute r : naboer){ //for naboer i nabolista
            if(this.sjekkUtvei()){ //sjekker om deter aapning
                utvei += this.toString(); //setter dette som siste posisjon i strengen
                utveiListe.leggTil(utvei); //legger strengen i lista
                return; //returnerer
            }
            if(this.erHvit() && r.erHvit() && r.traakketpaa == false){ //hvis dette er en hvit rute, naboen er en hvit rute, og om den ikke er trakketpaa
                if(tall == 0){ //hvis tall er 0
                    utvei += this.toString() + " --> ";//legger til posisjonen i utvei stringen
                    tall ++; //oeker tall med ett
                }
                traakketpaa = true; //gjoer traakketpaa om til true
                r.gaa(utvei); //gaar til nabo
            }
        }
        traakketpaa = false; //trakketpaa blir false her etter at alt er over
        tall = 0; // tall gaar tilbake til 0
    }

    public String toString(){ //for aa skrive utvei
        return "(" + kolonnepos + ", " + radpos +")";
    }

    public Liste<String> finnUtvei(){ //finner utvei
        utveiListe = new Lenkeliste<String>(); //slik at jeg kan bruke leggtil metoden
        String vei = ""; //lager ny streng som skal bli brukt i gaa
        this.gaa(vei);
        return utveiListe; //returnerer utveilista
    }

    public abstract char tilTegn();

    public boolean sjekkUtvei(){ //viser om dette er en utvei eller ikke
        return false;
    }
    
    public boolean erHvit(){ //viser om dette er en hvit rute eller ikke
        return false;
    }

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