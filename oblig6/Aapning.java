
public class Aapning extends HvitRute{
    String vei;
    
    public Aapning(int k, int r, Labyrint l){ //konstruktoer som blir send til super
        super(k,r,l);
    }

    @Override
    public void gaa(String veiut){
        //System.out.println("FANT UTVEI"); //brukt for debug
        vei = veiut; //lagrer veien
        vei += this.toString(); //lagrer denne posisjonen
        laby.leggTilUtvei(vei); //legger den til i utveier lista i labyrint
    }
}