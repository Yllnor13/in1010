
public class HvitRute extends Rute{

    public HvitRute(int k, int r, Labyrint l){ //konstruktoer som blir send til super
        super(k,r,l);
    }

    @Override
    public char tilTegn(){
        return '.';
    }

    @Override
    public void gaa(String veiut){ //polimorfi
        traakketpaa = true; //sier at denne er trakkettpaa
        if(tall == 0){
            tall++; //er her bare slik at posisjonen ikke blir printet to ganger
            veiut += this.toString() + "-->"; //legge til dette i utveien
        }
        for(Rute nabo : naboer){ //sjekker gjennom hver eneste nabo i denne ruten
            if(nabo.traakketpaa == false){//slik at den ikke gaar tilbake naar den skal finne utveien
                nabo.gaa(veiut); //gaa til neste nabo
            }
        }
    }
}