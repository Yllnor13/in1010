public class Hoved{ //ny klasse
    static Drikke oel = new Ol(8);
    static Drikke vann = new Drikke(10);
    public static void main(String[] args){ //main kode som skal kjores
        vann.drikkGlass();
        oel.drikkGlass();
        System.out.println(vann.toString());
        System.out.println(oel.toString());

    }
}
