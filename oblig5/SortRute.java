
public class SortRute extends Rute{
    public SortRute(int k, int r){ //konstruktoer som blir send til super
        super(k,r);
    }

    @Override
    public char tilTegn(){ //returnerer hva slags tegn det er naar man printer ut labyrinten
        return '#';
    }

    @Override
    public boolean sjekkUtvei(){ //viser seg selv om den er aapning uten aa bruke instanceof
        return false;
    }
}