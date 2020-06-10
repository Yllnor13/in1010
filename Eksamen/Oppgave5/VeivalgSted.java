public class VeivalgSted extends Sted{
    Lenkeliste<VeivalgSted> utveier = new Lenkeliste<VeivalgSted>(); //liste med utganger

    public VeivalgSted(String navn){
        super(navn);
    }

    @Override
    public Sted gaaVidere(int index){
        return utveier.hent(index);
    }

    public void leggTilUtgang(VeivalgSted ut1, VeivalgSted ut2, VeivalgSted ut3){ //tar imot utgangene og legger dem til i lista
        utveier.leggTil(ut1);
        utveier.leggTil(ut2);
        utveier.leggTil(ut3);
    }
}