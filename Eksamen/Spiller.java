import java.util.Random;
import java.util.Scanner;

public class Spiller {
    int trekk = 4;
    Sted lokasjon;
    Brukergrensesnitt grensesnitt;
    int formue;
    Skattekiste ryggsekk = new Skattekiste(12);
    String spoersmal = "hva oensker du aa gjoere? (skriv tallet) \n";
    String[] alternativer = {"0. Legge fra en gjenstand.", "1. Ta ut en gjenstand.", "2. Gaa videre."};
    
    public Spiller(Sted start, Brukergrensesnitt spiller){
        lokasjon = start;
        grensesnitt = spiller;
    }

    public void nyTrekk(){
        while(trekk != 0){
            System.out.print(trekk + " trekk som er igjen \n");
            int valg = grensesnitt.beOmKommando(spoersmal, alternativer);
            if (valg == 0){
                if(grensesnitt.getClass() == Terminal.class){
                    Scanner nyvalg = new Scanner(System.in);
                    if(ryggsekk.gjenstander.stoerrelse() == 0){
                        System.out.println("du har ikke noe paa deg");
                    }
                    else if(lokasjon.hentRikdom().gjenstander.stoerrelse() == lokasjon.hentRikdom().maksantall){
                        System.out.println("kista er full");
                    }
                    else{
                        System.out.println("hva vil du legge fra deg?");
                        int tall = 0;
                        for (Gjenstand g : ryggsekk.gjenstander){
                            System.out.println(tall + ". " + g.navn + ", som er verdt: " + g.verdi + "?");
                            tall++;
                        }
                        int nyvalgtall = nyvalg.nextInt();
                        String navnpaavare = ryggsekk.gjenstander.hent(nyvalgtall).navn;
                        int solgt = lokasjon.hentRikdom().leggTil(ryggsekk.gjenstander.hent(nyvalgtall));
                        System.out.println("Du solgte din " + navnpaavare + " for " + solgt);
                        formue += solgt;
                    }
                }else{
                    Random tilftall = new Random();
                    if(ryggsekk.gjenstander.stoerrelse() == 0){
                        System.out.println("du har ikke noe paa deg");
                    }
                    else if(lokasjon.hentRikdom().gjenstander.stoerrelse() == lokasjon.hentRikdom().maksantall){
                        System.out.println("kista er full");
                    }
                    else{
                        System.out.println("hva vil du legge fra deg?");
                        int tall = 0;
                        for (Gjenstand g : ryggsekk.gjenstander){
                            System.out.println(tall + ". " + g.navn + ", som er verdt: " + g.verdi + "?");
                            tall++;
                        }
                        int nyvalgtall = tilftall.nextInt(ryggsekk.gjenstander.stoerrelse());
                        String navnpaavare = ryggsekk.gjenstander.hent(nyvalgtall).navn;
                        int solgt = lokasjon.hentRikdom().leggTil(ryggsekk.gjenstander.hent(nyvalgtall));
                        System.out.println("Du solgte din " + navnpaavare + " for " + solgt);
                        formue += solgt;
                    }
                }
            }
            else if(valg == 1){
                int nygjentall = lokasjon.hentRikdom().taUt();
                Gjenstand nygjen = lokasjon.hentRikdom().gjenstander.hent(nygjentall);
                lokasjon.hentRikdom().gjenstander.fjern(nygjentall);
                ryggsekk.gjenstander.leggTil(nygjen);
                System.out.println("Du fikk " + nygjen.navn + " fra kisten! Den er verdt " + nygjen.verdi);

            }
            else if(valg == 2){
                lokasjon = lokasjon.gaaVidere();
            }
            else{
                System.out.println("svaret du skrev inn ble ikke godkjent");
            }
            trekk--;
        }
    }
}