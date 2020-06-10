import java.util.Random;
import java.util.Scanner;

public class Spiller {
    Sted lokasjon; //for aa vite hvor spiller er
    Brukergrensesnitt grensesnitt; //for aa vite om det er robot eller menneske
    int formue; //for aa vite hvor mye spiller har
    Skattekiste ryggsekk = new Skattekiste(12); //hvor mye spiller har plass paa sekken
    String spoersmal = "\nHva oensker du aa gjoere? (skriv tallet) \n"; //spoersmalet
    String[] alternativer = {"0. Legge fra en gjenstand.", "1. Ta ut en gjenstand.", "2. Gaa videre.", "3. Vis statusifo.", "4. Sjekk hva som er inni kista\n"}; //valg
    
    public Spiller(Brukergrensesnitt spiller, Sted start){ //konstruktoer
        grensesnitt = spiller; //hvilket grensesnitt man skal bruke
        lokasjon = start;
    }

    public String sluttsum(){ //viser hvor mye spiller tjente
        String status = "Spillet er over, du tjente " + formue + " kroner.";
        return status;
    }

    public int nyTrekk(){ //nytrekk metode
        int valg = 3; //dette er slik at den ikke gaar videre ved starten
        valg = grensesnitt.beOmKommando(spoersmal, alternativer); //grensesnittet beomkommando blir brukt, og det man velger blir om til valg
            if (valg == 0){ //hvis man velger aa legge fra seg noe
                if(grensesnitt.getClass() == Terminal.class){ //sjekker om det er menneske som tok valget
                    Scanner nyvalg = new Scanner(System.in);
                    if(ryggsekk.hentGjenstander().size() == 0){ //sjekker om bruker har noe paa seg
                        System.out.println("du har ikke noe paa deg"); //sier om de ikke har noe paa seg
                        valg = 3; //slik at det ikke blir gjort et trekk
                    }
                    else if(lokasjon.hentRikdom().hentGjenstander().size() == lokasjon.hentRikdom().hentMaksantall()){ //sjekker om kista er full
                        System.out.println("kista er full \n");
                        valg = 3; //slik at det ikke blir gjort et trekk
                    }
                    else{ //om hverken kista er full og man har noe paa sekken
                        System.out.println("hva vil du legge fra deg?\n");
                        int tall = 0;
                        for (Gjenstand g : ryggsekk.hentGjenstander()){ //viser hva som er i sekken
                            System.out.println(tall + ". " + g.navn + ", som er verdt: " + g.verdi + "?");
                            tall++;
                        }
                        int nyvalgtall = nyvalg.nextInt(); //bruker velger hva det  er de vil legge fra seg
                        String navnpaavare = ryggsekk.hentGjenstander().get(nyvalgtall).navn;
                        int solgt = lokasjon.hentRikdom().leggTil(ryggsekk.hentGjenstander().get(nyvalgtall)); //selger greia
                        ryggsekk.hentGjenstander().remove(nyvalgtall); //gjerner gjenstanden fra sekken
                        System.out.println("Du solgte din " + navnpaavare + " for " + solgt); //forteller bruker hva som ble solgt
                        formue += solgt; //legger til hva som ble solgt
                    }
                }
                
                else{ //om det ikke er terminal
                    Random tilftall = new Random(); //gjoer for det meste det samme som paa terminal, men istedenfor scanner next int, saa bruker jeg random
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
                        int nyvalgtall = tilftall.nextInt(ryggsekk.hentGjenstander().size()); //velger tilfeldig greia fra kista
                        if(ryggsekk != null){
                            System.out.println("ryggsekk");
                            if(ryggsekk.hentGjenstander() != null){
                                System.out.println("gjenstander");
                                if(ryggsekk.hentGjenstander().get(nyvalgtall) != null){
                                    System.out.println("gjenstand");
                                    if(ryggsekk.hentGjenstander().get(nyvalgtall).hentNavn() != null){
                                        System.out.println("alt funker");
                                        String navnpaavare = ryggsekk.hentGjenstander().get(nyvalgtall).hentNavn();//gjoer det samme videre som om det skulel vaere fra terminal

                                        int solgt = lokasjon.hentRikdom().leggTil(ryggsekk.hentGjenstander().get(nyvalgtall)); //lagrer verdien som blir solgt
                        System.out.println("\nDu solgte din " + navnpaavare + " for " + solgt + "\n"); //sier hvor mye brukeren solgte gjenstanden for
                        formue += solgt; //ooker formye
                                    }
                                }
                            }
                        }
                        
                    }
                }

            }
            else if(valg == 1){ //om de velger aa ta noe ut av kista
                if(lokasjon.hentRikdom().hentGjenstander().size() == 0){ //sjekker om kista er tom
                    grensesnitt.giStatus("\nkista er tom!\n");
                    valg = 9; //slik at det ikke blir gjort et trekk
                }
                else if(ryggsekk.hentGjenstander().size() == ryggsekk.hentMaksantall()){ //sjekker om ryggsekken er full
                    grensesnitt.giStatus("\nsekken din er full\n");
                    valg = 9; //slik at det ikke blir gjort et trekk
                }
                else{ //dersom sekk er ikke full og kista ikke er tom
                    int nygjentall = lokasjon.hentRikdom().taUt(); //velger tilfeldig gjenstand fra kiste og returnerer indeksen
                    ryggsekk.hentGjenstander().add(lokasjon.hentRikdom().hentGjenstander().get(nygjentall)); //legger til gjenstanden med hjelp av indeks
                    System.out.println("\nDu fikk " + lokasjon.hentRikdom().hentGjenstander().get(nygjentall).navn + " fra kisten! Den er verdt " + lokasjon.hentRikdom().hentGjenstander().get(nygjentall).verdi + "\n"); //sier hva brukeren fikk
                    lokasjon.hentRikdom().hentGjenstander().remove(nygjentall); // fjerner gjenstand fra kistelista med hjelp av indeks
                }
            }

            else if(valg == 2){ //om man gaar videre
                System.out.println("\nDu velger aa dra videre\n");//sier til bruker hva de valgte aa gjoere
                lokasjon = lokasjon.gaaVidere(1); //lagrer neste sted i lokasjon
                System.out.println(lokasjon.toString()); //printer ut hvor de er naa
            }

            else if(valg == 3){ //om valg er er see status
                String stats = lokasjon.toString() + "\n Du har " + formue + " kroner paa deg"; //lagre string som blir sendt til status
                if (ryggsekk.hentGjenstander().size() > 0){ //om kista ikke er tom
                    stats +=  "og dette er det du har med deg..."; 
                }
                int tall = 0;
                for (Gjenstand g : ryggsekk.hentGjenstander()){ //vise hva han har paa seg
                    stats +="\n" + tall + ". " + g.navn + ", som er verdt: " + g.verdi + "?";
                    tall++;
                }
                grensesnitt.giStatus(stats); //send stats til gistatus
            }
            else if (valg == 4){ //om bruker vil sjekke hva som er oppi kista
                String forstatus = "her er hva som er oppi kista";
                int tall = 0;
                for (Gjenstand g : lokasjon.hentRikdom().hentGjenstander()){ //gaar gjennom kista og printer ut hva som er oppi
                    forstatus +="\n" + tall + ". " + g.navn + ", som er verdt: " + g.verdi;
                    tall++;
                }
                grensesnitt.giStatus(forstatus); //setter det paa status. var ikke sikker paa om gi status var for alle ting som skjer som skal printes eller om man kunne burke system.out.print, saa jeg bruker begge
            }
            else{ //hvis brukeren ikke  skrev noe av tallene over
                System.out.println("\nsvaret du skrev inn ble ikke godkjent\n"); 
            }
        return valg; //valget blir returnert
    }
}