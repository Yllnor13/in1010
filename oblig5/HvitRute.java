
public class HvitRute extends Rute{

    public HvitRute(int k, int r){
        super(k,r);
    }

    @Override
    public char tilTegn(){
        return '.';
    }

    @Override
    public boolean sjekkUtvei(){ //viser seg selv som aapning uten aa bruke instanceof
        return false;
    }

    @Override
    public boolean erHvit(){
        return true;
    }
}