public class Drikke implements Drikkbar{
    int plass = 0;
    int dlIFlaska = 0;

    public void drikkGlass(){
        dlIFlaska -= 2;
        if(dlIFlaska < 2){
            dlIFlaska = 0;
        }
    }

    public void fyllOpp(){
        dlIFlaska += plass;
    }

    public Drikke(int rom){
        plass = rom;
        dlIFlaska = rom;
    }

    public String toString(){
        return "saa mye igjen av drikke: " + dlIFlaska;
    }
}