public class TestResept{
    public static void main(String[] args){
        Narkotisk N = new Narkotisk("narko", 20.2, 14.0, 2);
        Vanedannede Vd = new Vanedannede("vaned", 10.0, 2.3, 3);
        Legemiddel V = new Vanlig ("van", 15.0, 5.5);



        System.out.println("navn: " + N.hentNavn());
        System.out.println("pris: " + N.hentPris());
        N.settNyPris(30.3);
        System.out.println("pris: " + N.hentPris());
        System.out.println("id: " + N.hentId());
        System.out.println("virkestoff: " + N.hentVirkestoff());
        System.out.println("narkotisk styrke: " + N.hentNarkotiskStyrke());


        System.out.println("navn: " + Vd.hentNavn());
        System.out.println("pris: " + Vd.hentPris());
        System.out.println("id: " + Vd.hentId());
        System.out.println("virkestoff: " + Vd.hentVirkestoff());
        System.out.println("vanedannede styrke: " + Vd.hentVanedannedeStyrke());


        System.out.println("navn: " + V.hentNavn());
        System.out.println("pris: " + V.hentPris());
        System.out.println("id: " + V.hentId());
        System.out.println("virkestoff: " + V.hentVirkestoff());



        Lege lg = new Lege();
        Lege lg2 = new Lege();
        Lege lg3 = new Lege();

        Bla_resepter blaa = new Bla_resepter(V, lg, 14, 3);
        Militaerresepter mili = new Militaerresepter(Vd, lg2, 3, 8);
        Presepter p = new Presepter(N, lg3, 9);

        System.out.println("pris på blaa: " + blaa.prosAaBetale() + " kr");
        System.out.println("type resept: " + blaa.hentFarge());
        System.out.println("legemiddel til blaa: " + blaa.hentLegemiddel());
        System.out.println("hvor mange reiter: " + blaa.hentReit());
        blaa.bruk();
        System.out.println("hvor mange reiter naa: " + blaa.hentReit());
        System.out.println("pasientens ID: " + blaa.hentPasientId());
        System.out.println("ID: " + blaa.hentId());

        System.out.println("pris på mili: " + mili.prosAaBetale() + " kr");
        System.out.println("type resept: " + mili.hentFarge());
        System.out.println("legemiddel til mili: " + mili.hentLegemiddel());
        System.out.println("hvor mange reiter: " + mili.hentReit());
        mili.bruk();
        System.out.println("hvor mange reiter naa: " + mili.hentReit());
        System.out.println("pasientens ID: " + mili.hentPasientId());
        System.out.println("ID: " + mili.hentId());

        System.out.println("pris på p: " + p.prosAaBetale() + " kr");
        System.out.println("type resept: " + p.hentFarge());
        System.out.println("legemiddel til p: " + p.hentLegemiddel());
        System.out.println("hvor mange reiter: " + p.hentReit());
        p.bruk();
        System.out.println("hvor mange reiter naa: " + p.hentReit());
        System.out.println("pasientens ID: " + p.hentPasientId());
        System.out.println("ID: " + p.hentId());
    }
}