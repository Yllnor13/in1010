import java.util.concurrent.*;

public class HvitRute extends Rute{
    private class Gaa implements Runnable{ //den er her fordi andre klasser trenger ikke aa bruke denne
        Rute naborute; //for naboruten
        String posisjon; //for posisjonen denne hvite ruten er i
        CountDownLatch cdl; //for countdownlatchen denne ruten trenger
    
        public Gaa(Rute nr, String p, CountDownLatch ldc){ //konstruktoer
            naborute = nr;
            posisjon = p;
            cdl = ldc;
        }
    
        public void run(){
            naborute.gaa(posisjon); //kaller paa gaa metoden naar traden med denne runnablen starter
            cdl.countDown(); //cdl coutner ned.
        }
    }

    public HvitRute(int k, int r, Labyrint l){ //konstruktoer som blir send til super
        super(k,r,l);
    }

    @Override
    public char tilTegn(){
        return '.';
    }

    @Override
    public void gaa(String veiut){
        traakketpaa = true;
        if(tall == 0){ //er her fordi posisjon blir skrevet inn i stringen 2 ganger hvis jeg ikke har med dette
            tall++;
            veiut += this.toString() + "-->"; //legge til dette i utveien
        }
        /*for(Rute nabo : naboer){ //sjekker gjennom hver eneste nabo i denne ruten
            if(nabo.traakketpaa == false){//slik at den ikke Gaar tilbake naar den skal finne utveien
                nabo.gaa(this, veiut); //gaa til neste nabo
            }
        }// brukte denne foer */

        CountDownLatch l = null; //skaper en tom countdownlatch
        Runnable run = null; //skaper en tom runnable
        int ledignabo = 0; //den skal se antall ledige naboer
        for(int i = 0; i < naboer.length; i++){ //gjoer int i om til lengden av nabolista (jeg kunne egt bare ha skrevet int = 4), og gjoer iftesten under for hver nabo
            if(naboer[i] != null && naboer[i].tilTegn() == '.' && naboer[i].traakketpaa == false){ //sjekker om nabo finnes, om nabo er hvit og om nabo ikke er traakketpaa
                ledignabo++; //oeker ledignabo med 1
            }
        }

        if(ledignabo >= 1){ //hvis det er 2 ledige utveier, saa skal jeg lage en traad for den andre utveien, mens denne traaden skal fortsette selv
            l = new CountDownLatch(ledignabo-1); //ledignabo senker med 1 for hver gang denne testen gaar, til det bare er en nabo igjen
            for(int i = 0; i < naboer.length; i++){ // skal teste hver nabo
                if(naboer[i] != null && naboer[i].tilTegn() == '.' && naboer[i].traakketpaa == false){ //hvis naboen finnes, er hvit, og ikke er trakketpaa...
                    if(ledignabo > 1){ //hvis det er mer enn en ledig nabo saa skal den lage en ny traad
                        ledignabo--; //senker mengden av ledige nabo uten traad
                        run = new Gaa(naboer[i], veiut , l); //ny run med naboen, veiut stringen og latchen
                        Thread traad = new Thread(run); //lager en ny trad som skal kjoere denne runnen
                        traad.start();//starter den nye traaden
                        //System.out.println(traad + " " + this + " " + naboer[i]); //er for aa teste
                    }
                    else if(ledignabo == 1){ //hvis det bare er en ledig nabo
                        naboer[i].gaa(veiut); //sa fortsetter denne her videre
                    }
                }
            }
            try{//kjoerer denne her
                l.await(); //venter til andre threads er ferdig
            } catch(InterruptedException e) {}
        }
    }
}

/* svar paa spoersmal i oblig 6
er ikke helt sikker paa om jeg forstaer spoersmalet rikting (antar at den "gamle traaden" er main traden)
fra det jeg saa da jeg testet (med at aapning printer ut naar den blir funnet, og naar threads blir laget), saa var det opp til hvilken rekkefoelge koden saa gjennom naboene.
for meg, saa var nabolista denne rekkefoelgen (venstre, hoeyre, over, undeer)
saa hvis det var en labyrint som hadde en rett utvei som gikk rett mot venstre, sa ville programmet mitt ga sidelengs uten a lage nye threads til den fant den ene utveien
saa lage threads for utveiene som var hoeyre/over/under (i den rekkefoelgen).
det kan hende at den fant en utvei foer foerste trad ble laget (med labyrint 7, kol 1, rad 1)
den ville lage en ny trad for hver ledig traad etter den foerste ledige naboen
haaper jeg svarte riktig
*/