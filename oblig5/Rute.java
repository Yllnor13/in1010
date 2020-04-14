
abstract public class Rute {
    int kolonnepos;
    int radpos;
    Rute[] naboliste;
    //hint1
    Rute over;
    Rute under;
    Rute venstre;
    Rute hoeyre;
    Liste<Rute> naboeruno;
    //Rute[] naboer;

    public Rute(int kpos, int rpos){
        this.kolonnepos = kpos;
        this.radpos = rpos;
    }

    public abstract char tilTegn();

    //hint1
    public void nyNabo(Rute opp, Rute ned, Rute ven, Rute hoey){
        Rute over = opp;
        Rute under = ned;
        Rute venstre = ven;
        Rute hoeyre = hoey;
        naboeruno.leggTil(over);
        naboeruno.leggTil(under);
        naboeruno.leggTil(venstre);
        naboeruno.leggTil(hoeyre);
        //naboer = new Rute[]{over, under, venstre, hoeyre};
    }
}