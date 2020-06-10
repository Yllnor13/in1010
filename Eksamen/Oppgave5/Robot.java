import java.util.Random;

public class Robot implements Brukergrensesnitt{ //implementerer grensesnittet
    String lokasjon;

    public Robot(){ //ikke noe konstruktoer, saa ikke hva jeg kunne legge til her. men den funker aa ha tomt
    }

    public void giStatus(String status){ //gistatus skal bare printe ut hva statusen er
        System.out.println(status);
    }

    public int beOmKommando(String spoersmal, String[] alternativer){ //be om kommando returnerer int
        System.out.println(spoersmal);//printer ut spoersmaalet
        for(String s : alternativer){ //for tekst i alternativer...
            System.out.println(s); //print teksten
        }
        Random rand = new Random(); //roboten velger noe tilfeldig
        int valg = rand.nextInt(3); //siden robotoen ikke har noen grunn til a se sin egen status eller vite hva som er oppi kista, saa tar den bare et av de 3 foeste valga
        return valg;
    }
}