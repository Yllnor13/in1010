
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
        traakketpaa = true;
        if(tall == 0){
            tall++;
            veiut += this.toString() + "-->"; //legge til dette i utveien
        }
        forrigeRute = forirute;
        for(Rute nabo : naboer){ //sjekker gjennom hver eneste nabo i denne ruten
            if(nabo != forirute && nabo.traakketpaa == false){//slik at den ikke gaar tilbake naar den skal finne utveien
                nabo.gaa(this, veiut); //gaa til neste nabo
            }
        }
    }
}