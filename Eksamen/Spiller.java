import java.util.Random;
import java.util.Scanner;

public class Spiller {
    int trekk = 6;
    Sted lokasjon;
    Brukergrensesnitt grensesnitt;
    int formue;
    Skattekiste ryggsekk = new Skattekiste(12);
    String spoersmal = "\nHva oensker du aa gjoere? (skriv tallet) \n";
    String[] alternativer = {"0. Legge fra en gjenstand.", "1. Ta ut en gjenstand.", "2. Gaa videre.", "3. Vis statusifo.\n"};
    
    public Spiller(Sted start, Brukergrensesnitt spiller){
        lokasjon = start;
        grensesnitt = spiller;
    }

    public void nyTrekk(){
        while(trekk > 0){
            System.out.print(trekk + " trekk som er igjen \n");
            System.out.println(lokasjon.posisjon);
            int valg = grensesnitt.beOmKommando(spoersmal, alternativer);
            
            
            if (valg == 0){

                if(grensesnitt.getClass() == Terminal.class){
                    Scanner nyvalg = new Scanner(System.in);
                    if(ryggsekk.hentGjenstander().size() == 0){
                        System.out.println("du har ikke noe paa deg");
                    }
                    else if(lokasjon.hentRikdom().hentGjenstander().size() == lokasjon.hentRikdom().hentMaksantall()){
                        System.out.println("kista er full \n");
                    }
                    else{
                        System.out.println("hva vil du legge fra deg?\n");
                        int tall = 0;
                        for (Gjenstand g : ryggsekk.hentGjenstander()){
                            System.out.println(tall + ". " + g.navn + ", som er verdt: " + g.verdi + "?");
                            tall++;
                        }
                        int nyvalgtall = nyvalg.nextInt();
                        String navnpaavare = ryggsekk.hentGjenstander().get(nyvalgtall).navn;
                        int solgt = lokasjon.hentRikdom().leggTil(ryggsekk.hentGjenstander().get(nyvalgtall));
                        ryggsekk.hentGjenstander().remove(nyvalgtall);
                        System.out.println("Du solgte din " + navnpaavare + " for " + solgt);
                        formue += solgt;
                        
                    }
                }
                
                else{
                    Random tilftall = new Random();
                    if(ryggsekk.hentGjenstander().size() == 0){
                        System.out.println("du har ikke noe paa deg");
                    }
                    else if(lokasjon.hentRikdom().hentGjenstander().size() == lokasjon.hentRikdom().hentMaksantall()){
                        System.out.println("kista er full");
                    }
                    else{
                        System.out.println("hva vil du legge fra deg?");
                        int tall = 0;
                        for (Gjenstand g : ryggsekk.hentGjenstander()){
                            System.out.println(tall + ". " + g.navn + ", som er verdt: " + g.verdi + "?");
                            tall++;
                        }
                        int nyvalgtall = tilftall.nextInt(ryggsekk.hentGjenstander().size());
                        String navnpaavare = ryggsekk.hentGjenstander().get(nyvalgtall).navn;
                        int solgt = lokasjon.hentRikdom().leggTil(ryggsekk.hentGjenstander().get(nyvalgtall));
                        System.out.println("Du solgte din " + navnpaavare + " for " + solgt);
                        formue += solgt;
                        
                    }
                }

            }
            else if(valg == 1){
                if(lokasjon.hentRikdom().hentGjenstander().size() == 0){
                    grensesnitt.giStatus("kista er tom!");
                }
                else if(ryggsekk.hentGjenstander().size() == ryggsekk.hentMaksantall()){
                    grensesnitt.giStatus("sekken din er full");
                }
                else{
                    int nygjentall = lokasjon.hentRikdom().taUt();
                    Gjenstand nygjen = lokasjon.hentRikdom().hentGjenstander().get(nygjentall);
                    ryggsekk.hentGjenstander().add(nygjen);
                    lokasjon.hentRikdom().hentGjenstander().remove(nygjentall);
                    System.out.println("Du fikk " + nygjen.navn + " fra kisten! Den er verdt " + nygjen.verdi);
                    trekk--;
                }
            }
            else if(valg == 2){
                System.out.println("Du velger aa dra videre");
                lokasjon = lokasjon.gaaVidere();
                System.out.println(lokasjon.posisjon);
            }
            else if(valg == 3){
                grensesnitt.giStatus(lokasjon.posisjon);
            }
            else{
                System.out.println("svaret du skrev inn ble ikke godkjent");
            }
        }
    }
}