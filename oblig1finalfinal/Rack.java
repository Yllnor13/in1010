import java.util.ArrayList; //importerte arraylist

public class Rack{ //starter klassen
  
  int antnode = 0;
  int antpro = 0;
  
  private ArrayList <Node> nodeListe;

  public Rack(){//konstruktooren
    nodeListe = new ArrayList<Node>(); //gjoor nodeliste om til en arraylist som bare tar imot noder
  }

  public void setinn(Node node) { //metode for aa sette inn noder
    nodeListe.add(node);
    antpro = antpro + node.hentpros(); //istedenfor aa kalkulere senere hvor mange prossesser som er i en rack, saa regner den det ut her
    antnode++;
  }

  public int getAntNoder() {// viser oss hvor mange noder det er i racken gjennom aa returnere stoerrelsen paa lista
    return nodeListe.size();
  }

  public int getAntPros() { //henter antpro variabelen som har ookt etter at man har brukt setinn metoden
    return antpro;
  }

  public int racknokminne(int mestminne) { //ser om racken har nok minne til enda en node med hvor mye minne krevd som parameter
    int MAXMINNE = mestminne;
    int nodermednokminne = 0;

    for(int i = 0;i < nodeListe.size();i++){//legger til sammen verdien av alle minnene til hver node
      Node noden = nodeListe.get(i);

      if (noden.paakrevdminne(MAXMINNE) == true){ //ser om mengden minne til sammen er stoorre enn parameteren
        nodermednokminne ++; //om den har mer, saa ser man at noder med nok minne ooker
      }
    }
    return nodermednokminne;
  }
}
