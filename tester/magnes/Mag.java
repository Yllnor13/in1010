import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
//importer av verktoey jeg trenger

public class Mag {
    private static ArrayList<Double> tall=new ArrayList<Double>(); //liste for doubles


    private static Double formel(int t){ //formel som skal bruke formelen fra boka
        Double svar = 0.0; //svar er lik null
        Double td = new Double(t); //gjoer int t om til double
        svar = ((td*td*td)-td)/12.0; //utfoerer formelen
        System.out.println(svar); //printer ut svaret
        return svar; //returnerer svaret slik at koden kan legge den til lista lenger nede
    }

    public static void main(String args[])throws FileNotFoundException{ //hovedmetode som kaster filenotfoundexception dersom filen ikke blir funnet
        Scanner lesfil = null; //ny scanner som skal lese fil, den er tom for naa
        File nori = new File("yllnori.txt"); //ny file "nori" som er av txt filen "yllnori.txt"

        try{ //sjekker om den kan finen filen
            lesfil = new Scanner(nori); //scanneren kan naa lese gjennom innholdet
        } catch (FileNotFoundException e){ //hvis ikke, saa blir det error
            System.out.println("fant ikke fil lol oops");
        }

        String tallinje = lesfil.nextLine(); //lager String objekt av foerste linje
        String[] talltext = tallinje.split(" "); //lager liste hvor alle tall blir splittet etter mellomrommet de har mellom seg
        
        /*for(String s : talltext){
            System.out.println(s);
        }*/ //dette er test kode for aa sjekke om alle tallene ble skrevet inn riktig paa lista
        
        for(String s : talltext){ //for hver string i lista
            Double nytall = Double.parseDouble(s); //gjoer stringen om til double
            tall.add(nytall); //legg den i double lista
        }

        /*Collections.sort(tall); //sorterer tall lista
        Collections.reverse(tall); //gjor lista om til motsatt rekkefoelge
        System.out.print(tall);*/ //printer ut lista (trenger egt ikke denne, var bare pent aa see lista sortert)

        ArrayList<Integer> frekvens = new ArrayList<Integer>(); // lager ny liste for hvor ofte tall kommer opp
        Set<Double> printa = new HashSet<>(); //denne skal bli brukt for aa ikke printe ut samme ting to ganger
        for(Double d : tall){ //for hver double i tall
            if (printa.add(d)){ //stopper frekvensen fra a bli printet ut mer enn en gang
                frekvens.add(Collections.frequency(tall, d)); //finner frekvensen til tallet som programmet er i
                System.out.println("tall " + d + " kommer opp " + Collections.frequency(tall, d) + " ganger"); //printer ut hvor ofte den har kommet opp
            }
        }
        System.out.println(frekvens); //printer ut lista

        ArrayList<Double> listeMedLoesninger = new ArrayList<Double>(); //ny double liste for siste kalkulering
        for(int siste : frekvens){ //for hvert tall i lista
            formel(siste); //utfoer formel paa tallet
            listeMedLoesninger.add(formel(siste)); //legg til resultatet i listeMedLoesninger
        }


        Double sisteresultat = 0.0; //deklarerer siste resultat som vi trenger her
        for(Double b : listeMedLoesninger){ //for hver double i siste double liste
            sisteresultat += b; //addere double med siste resultat
        }
        System.out.println(sisteresultat); //print resultatet
    }
}