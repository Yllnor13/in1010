public class Stabel<T> extends Lenkeliste<T>{
    public void leggPaa(T x){
        leggTil(x); //man skulle sette inn elementet til slutt, som skjer naar man bruker leggtil(x);
    }

    public T taAv(){
        return fjern(stoerrelse() -1); //bruker stoerrelse -1, siden lenkelisten starter paa posisjon 0
    }
}