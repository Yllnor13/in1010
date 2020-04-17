public class SortRute extends Rute{ //SortRute er en subclass av Rute
    public SortRute(int kol, int rad){//konstruktoer til SortRute
        super(kol, rad);
    }

    @Override
    public char tilTegn() { //metode for aa sette tegnet for ruten
        return '#';
    }
}