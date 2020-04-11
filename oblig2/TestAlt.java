public class TestAlt{
    public static void main(String[] args){

        Vanedannede Vd = new Vanedannede("vaned", 1000.0, 2.3, 3);
        Lege lg2 = new Lege("jan");
        Militaerresepter mili = new Militaerresepter(Vd, lg2, 3, 8);

        Narkotisk N = new Narkotisk("narko", 433.00, 14.0, 2);
        Spesialist lg3 = new Spesialist("tomas", 1232);
        Presepter p = new Presepter(N, lg3, 9);

        Legemiddel V = new Vanlig ("van", 421.99, 5.5);
        Lege lg = new Lege("ole");
        Bla_resepter blaa = new Bla_resepter(V, lg, 14, 3);


        System.out.println(Vd.toString());
    
        System.out.println(mili.toString());

        System.out.println("navn: " + lg2.hentNavn());

            
        System.out.println(N.toString());
    
        System.out.println(p.toString());

        System.out.println("betale: " + p.prisAaBetale());

        System.out.println(lg3.toString());


        System.out.println(V.toString());
    
        System.out.println(blaa.toString());

        blaa.bruk();
        
        System.out.println("hvor mange reiter naa: " + blaa.hentReit());

        System.out.println(lg.toString());

    }
}