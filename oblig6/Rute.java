import java.util.concurrent.*;

abstract public class Rute{
    protected int kolonnepos;//hvilken kolonne den er i
    protected int radpos; // hvilken rad den er i
    //hint1
    protected Rute over; //naboer fra hintet
    protected Rute under;
    protected Rute venstre;
    protected Rute hoeyre;
    protected Labyrint laby;
    protected boolean traakketpaa = false;
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

    public abstract void gaa(String vei); //hver type rute har sin egen mate aa gaa paa

    public String toString(){ //for aa skrive utvei
        return "(" + kolonnepos + ", " + radpos +")";
    }

    public void finnUtvei(){ //finner utvei
        String vei = "";
        //this.gaa(null, vei); trenger ikke denne lenger
        traakketpaa = false;
        CountDownLatch l = null;
        Runnable run = null;
        int ledignabo = 0;
        for(int i = 0; i < naboer.length; i++){
            if(naboer[i] != null && naboer[i].tilTegn() == '.'){
                ledignabo++;
            }
        }

        if(ledignabo > 1){
            l = new CountDownLatch(ledignabo-1);

            for(int i = 0; i < naboer.length; i++){
                if(naboer[i] != null && naboer[i].tilTegn() == '.'){
                    if(traakketpaa = false){
                        naboer[i].gaa(vei);
                        traakketpaa = true;
                    }
                    else{
                        run = new Gaaa(naboer[i], vei , l);
                        Thread traad = new Thread(run);
                        traad.start();
                    }
                }
            }
        }
    

        try{
            l.await();
        } catch(InterruptedException e) {}

        if(ledignabo == 1){
            for(int i = 0; i < naboer.length; i++){
                if(naboer[i] != null && naboer[i].tilTegn() == '.'){
                    naboer[i].gaa(vei);
                }
            }
        }
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