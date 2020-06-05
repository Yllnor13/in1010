import java.util.Random;

public class Robot implements Brukergrensesnitt{
    int trekk = 0;
    String lokasjon;

    public Robot(int trekker){
        trekk = trekker;
    }

    public void giStatus(String status){
        System.out.println(status);
    }

    public int beOmKommando(String spoersmal, String[] alternativer){
        System.out.println(spoersmal);
        for(String s : alternativer){
            System.out.println(s);
        }
        Random rand = new Random();
        int valg = rand.nextInt(alternativer.length);
        trekk++;
        return valg;
    }
}