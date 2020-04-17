public class Aapning extends HvitRute{ //Subclass av HvitRute
    public Aapning(int rad, int kol){//konstruktoer
        super(rad, kol);
    }

    @Override
    public boolean Aapninger() {//metode for aa se om det er en aapning eller ikke
        return true;
    }
}