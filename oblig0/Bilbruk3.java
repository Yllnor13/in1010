public class Bilbruk3{ //ny klasse
  public static void main(String[] args){ //main kode som skal kjøres
    Bil3 Person0bil = new Bil3("jo1234"); //lager ny bil
    Person Person0 = new Person(Person0bil); //registrerer den bilen med noen
    Person0.skrivut(); //skriver ut bilnummeret
  }
}
