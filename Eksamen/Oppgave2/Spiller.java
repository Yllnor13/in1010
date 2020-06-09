import java.util.Random;
import java.util.Scanner;

public class Spiller {
    Sted lokasjon;
    Brukergrensesnitt grensesnitt;
    int formue;
    Skattekiste ryggsekk = new Skattekiste(12);
    String spoersmal = "\nHva oensker du aa gjoere? (skriv tallet) \n";
    String[] alternativer = {"0. Legge fra en gjenstand.", "1. Ta ut en gjenstand.", "2. Gaa videre.", "3. Vis statusifo.", "4. Sjekk hva som er inni kista\n"};
    
    public Spiller(Sted start, Brukergrensesnitt spiller){
        lokasjon = start;
        grensesnitt = spiller;
    }

    public String sluttsum(){
        String status = "Spillet er over, du tjente " + formue + " kroner.";
        return status;
    }

    public int nyTrekk(){
        int valg = 3;
        valg = grensesnitt.beOmKommando(spoersmal, alternativer);
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
                        System.out.println("\ndu har ikke noe paa deg\n");
                    }
                    else if(lokasjon.hentRikdom().hentGjenstander().size() == lokasjon.hentRikdom().hentMaksantall()){
                        System.out.println("\nkista er full\n");
                    }
                    else{
                        System.out.println("hva vil du legge fra deg?");
                        int tall = 0;
                        for (Gjenstand g : ryggsekk.hentGjenstander()){
                            System.out.println("\n" + tall + ". " + g.navn + ", som er verdt: " + g.verdi + "?\n");
                            tall++;
                        }
                        int nyvalgtall = tilftall.nextInt(ryggsekk.hentGjenstander().size());
                        String navnpaavare = ryggsekk.hentGjenstander().get(nyvalgtall).navn;
                        int solgt = lokasjon.hentRikdom().leggTil(ryggsekk.hentGjenstander().get(nyvalgtall));
                        System.out.println("\nDu solgte din " + navnpaavare + " for " + solgt + "\n");
                        formue += solgt;
                        
                    }
                }

            }
            else if(valg == 1){
                if(lokasjon.hentRikdom().hentGjenstander().size() == 0){
                    grensesnitt.giStatus("\nkista er tom!\n");
                }
                else if(ryggsekk.hentGjenstander().size() == ryggsekk.hentMaksantall()){
                    grensesnitt.giStatus("\nsekken din er full\n");
                }
                else{
                    int nygjentall = lokasjon.hentRikdom().taUt();
                    ryggsekk.hentGjenstander().add(lokasjon.hentRikdom().hentGjenstander().get(nygjentall));
                    System.out.println("\nDu fikk " + lokasjon.hentRikdom().hentGjenstander().get(nygjentall).navn + " fra kisten! Den er verdt " + lokasjon.hentRikdom().hentGjenstander().get(nygjentall).verdi + "\n");
                    lokasjon.hentRikdom().hentGjenstander().remove(nygjentall);
                }
            }
            else if(valg == 2){
                System.out.println("\nDu velger aa dra videre\n");
                lokasjon = lokasjon.gaaVidere();
                System.out.println(lokasjon.posisjon);
            }
            else if(valg == 3){

                String stats = lokasjon.toString() + "\n Du har " + formue + " kroner paa deg";
                if (ryggsekk.hentGjenstander().size() > 0){
                    stats +=  "og dette er det du har med deg...";
                }
                int tall = 0;
                for (Gjenstand g : ryggsekk.hentGjenstander()){
                    stats +="\n" + tall + ". " + g.navn + ", som er verdt: " + g.verdi + "?";
                    tall++;
                }
                grensesnitt.giStatus(stats);
            }
            else if (valg == 4){
                String forstatus = "her er hva som er oppi kista";
                int tall = 0;
                for (Gjenstand g : lokasjon.hentRikdom().hentGjenstander()){
                    forstatus +="\n" + tall + ". " + g.navn + ", som er verdt: " + g.verdi + "?";
                    tall++;
                }
                grensesnitt.giStatus(forstatus);
            }
            else{
                System.out.println("\nsvaret du skrev inn ble ikke godkjent\n");
            }
    return valg;
    }
}