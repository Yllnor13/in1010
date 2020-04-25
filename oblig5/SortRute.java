
public class SortRute extends Rute{
    public SortRute(int k, int r, Labyrint l){ //konstruktoer som blir send til super
        super(k,r,l);
    }

    @Override
    public char tilTegn(){ //returnerer hva slags tegn det er naar man printer ut labyrinten
        return '#';
    }

    @Override
    public void gaa(Rute forirute, String veiut){
        return;//dette er en sort rute, saa ingentisk skal skje her
    }
}