public class testLegemiddel{
    public static void main(String[] args){
        Narkotisk N = new Narkotisk("narko", 20.2, 14.0, 2);

        System.out.println("navn: " + N.hentNavn());
        System.out.println("pris: " + N.hentPris());

        N.settNyPris(30.3);

        System.out.println("pris: " + N.hentPris());
        System.out.println("id: " + N.hentId());
        System.out.println("virkestoff: " + N.hentVirkestoff());
        System.out.println("narkotisk styrke: " + N.hentNarkotiskStyrke());

        Vanedannede Vd = new Vanedannede("vaned", 10.0, 2.3, 3);

        System.out.println("navn: " + Vd.hentNavn());
        System.out.println("pris: " + Vd.hentPris());
        System.out.println("id: " + Vd.hentId());
        System.out.println("virkestoff: " + Vd.hentVirkestoff());
        System.out.println("vanedannede styrke: " + Vd.hentVanedannedeStyrke());

        Legemiddel V = new Vanlig ("van", 15.0, 5.5);

        System.out.println("navn: " + V.hentNavn());
        System.out.println("pris: " + V.hentPris());
        System.out.println("id: " + V.hentId());
        System.out.println("virkestoff: " + V.hentVirkestoff());
    }
}