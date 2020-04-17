
public class SortRute extends Rute{
    public SortRute(int k, int r){
        super(k,r);
    }

    @Override
    public char tilTegn(){
        return '#';
    }

    @Override
    public boolean sjekkUtvei(){ //viser seg selv som aapning uten aa bruke instanceof
        return false;
    }
}