public class Person {//oppretter klassen person
    Bil3 bil;//lager instanse av bil klasse
  //metode som gir person bil
  public Person(Bil3 pers){
    this.bil = pers;
  }
  public void skrivut(){//metode som printer ut nummeret
    this.bil.hentNummer();
  }
}
