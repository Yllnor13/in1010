import java.util.concurrent.*;

public class HvitRute extends Rute{

    public HvitRute(int k, int r, Labyrint l){ //konstruktoer som blir send til super
        super(k,r,l);
    }

    @Override
    public char tilTegn(){
        return '.';
    }

    @Override
    public synchronized void gaa(String veiut){
        traakketpaa = false;
        if(tall == 0){
            tall++;
            veiut += this.toString() + "-->"; //legge til dette i utveien
        }
        /*for(Rute nabo : naboer){ //sjekker gjennom hver eneste nabo i denne ruten
            if(nabo.traakketpaa == false){//slik at den ikke Gaar tilbake naar den skal finne utveien
                nabo.gaa(this, veiut); //gaa til neste nabo
            }
        }*/
        CountDownLatch l = null;
        Runnable run = null;
        int ledignabo = 0;
        for(int i = 0; i < naboer.length; i++){
            if(naboer[i] != null && naboer[i].tilTegn() == '.'){
                ledignabo++;
            }
        }

        if(ledignabo > 1){
            l = new CountDownLatch(ledignabo-1);

            for(int i = 0; i < naboer.length; i++){
                if(naboer[i] != null && naboer[i].tilTegn() == '.'){
                    if(traakketpaa = false){
                        naboer[i].gaa(veiut);
                        traakketpaa = true;
                    }
                    else{
                        run = new Gaaa(naboer[i], veiut , l);
                        Thread traad = new Thread(run);
                        traad.start();
                    }
                }
            }
        }
    

        try{
            l.await();
        } catch(InterruptedException e) {}

        if(ledignabo == 1){
            for(int i = 0; i < naboer.length; i++){
                if(naboer[i] != null && naboer[i].tilTegn() == '.'){
                    naboer[i].gaa(veiut);
                }
            }
        }
    }
}