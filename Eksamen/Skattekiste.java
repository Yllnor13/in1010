import java.util.Random;
import java.util.ArrayList;

public class Skattekiste {
    ArrayList<Gjenstand> gjenstander = new ArrayList<Gjenstand>();
    int maksantall = 4;

    public Skattekiste(){
        String bla = "blablabla";
    }

    public int leggTil(Gjenstand gjen){
        if (maksantall >= 4){
            System.out.println("det er ikke nok plass");
            return 0;
        }
        else{
            gjenstander.add(gjen);
            Random tilf = new Random();
            int tilftall = tilf.nextInt(3);
            if(tilftall == 0){
                int betaling = (gjen.verdi/5) * 4;
                return betaling;
            }
            else if(tilftall == 1){
                int betaling = gjen.verdi;
                return betaling;
            }
            else if(tilftall == 2){
                int betaling = (gjen.verdi/5) * 6;
                return betaling;
            }
        }
        return 0;
    }

    public void taUt(){
        Random tilf = new Random();
        int kistestoerrelse = gjenstander.size();
        int tilftall = tilf.nextInt(kistestoerrelse);
        gjenstander.remove(tilftall);
    }
}