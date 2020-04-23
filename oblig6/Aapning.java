
public class Aapning extends HvitRute{
    public Aapning(int k, int r){
        super(k,r);
    }

    @Override
    public boolean sjekkUtvei(){ //viser seg selv som aapning uten aa bruke instanceof
        return true;
    }
}