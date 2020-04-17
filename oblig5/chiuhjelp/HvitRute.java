public class HvitRute extends Rute{ //en subclass av Rute
    public HvitRute(int kol, int rad){ //kontruktoer som tar i mot kolonne og rad
        super(kol, rad);
    }

    @Override
    public char tilTegn() { //metode som gir tegnet for ruten
        return '.'; //siden den er hvit saa returnere den .
    }

    @Override
    public boolean erHvitRute() { //metode for som sier om den er hvit eller ikke
        return true; //returner true siden den er hvit
    }
}