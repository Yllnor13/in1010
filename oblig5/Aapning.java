
public class Aapning extends HvitRute{
    public Aapning(int k, int r, Labyrint l){ //konstruktoer som blir send til super
        super(k,r,l);
    }

    @Override
    public void gaa(Rute forirute, String veiut){
        String vei = veiut; //lagrer veien
        veiut += this.toString(); //lagrer denne posisjonen
        laby.utveier.leggTil(vei); //legger den til i utveier lista i labyrint
    }
}