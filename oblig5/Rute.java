
abstract public class Rute {
    int kolonnepos;
    int radpos;
    Rute[] naboliste;

    public Rute(int kpos, int rpos){
        this.kolonnepos = kpos;
        this.radpos = rpos;
    }

    public abstract char tilTegn();

    public void leggTilNabo(){
        
    }
}