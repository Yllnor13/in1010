public class Node{ //starter klassen node
  int minneigb; //variabel for minne i gb
  int antpros; //variabel for antall prosesser
  int paakrevd; //variabel for paakrevd minne
  public Node(int gb,int pros){ //konstruktooren som tar gb og pros som parametre
    this.minneigb = gb;
    this.antpros = pros;
  }
  public int hentminne(){ //returnerer minne til en node
    return(this.minneigb);
  }
  public int hentpros(){ //returnerer prosessene til en node
    return(this.antpros);
  }
  public boolean paakrevdminne(int paakrevd){ //ser om om det er nok minne igjen i noden
    if(this.minneigb>=paakrevd){ //if sjekk, returnerer sant hvis det er sant
      return true;
    }
    else{
      return false;
    }
  }
}
