public class Bil2{ //lager classen
  private String regNummer; //lager variabel
  public Bil2(String nummer){ //konstruktør med string nummer som parameter
    regNummer = nummer; //gjør det slik at regnummer er det samme som nummer
  }
  public void skrivut(){ //metode for å skrive ut nummeret
    System.out.println(regNummer); //skriver ut nummeret
  }
}
