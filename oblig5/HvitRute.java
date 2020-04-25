
public class HvitRute extends Rute{

    public HvitRute(int k, int r, Labyrint l){ //konstruktoer som blir send til super
        super(k,r,l);
    }

    @Override
    public char tilTegn(){
        return '.';
    }

    @Override
    public void gaa(Rute forirute, String veiut){
        String vei = veiut;
        forrigeRute = forirute;
        veiut += this.toString() + "-->"; //legge til dette i utveien
        for(Rute nabo : naboer){ //sjekker gjennom hver eneste nabo i denne ruten
            if(nabo != forirute){//slik at den ikke gaar tilbake naar den skal finne utveien
                nabo.gaa(this, vei); //gaa til neste nabo
            }
        }
    }
}