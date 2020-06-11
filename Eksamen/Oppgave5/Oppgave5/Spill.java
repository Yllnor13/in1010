import java.io.FileNotFoundException;
import java.util.Scanner; //ting som jeg trengte fra start

import javafx.application.Application;
import javafx.application.Platform; //er hovedsakelig javafx libraries
import javafx.stage.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.event.*;

public class Spill extends Application{
    Lenkeliste<Spiller> spillere = new Lenkeliste<Spiller>(); //liste med spillere til spillere
    Lenkeliste<Spiller> vinnere = new Lenkeliste<Spiller>();
    private static Pane kulisser = new Pane(); //kulisser
    private static Stage teater; //teater scene slik at jeg kan referere til den senere
    private static Text status; //den er her slik at den kan bli referert til snere
    
    public static void main(String args[]){
        launch();
    }

    @Override
    public void start(Stage teater){
        this.teater = teater;
        kulisser.setStyle("-fx-background-color: #353535"); //gjoer bakgrunnen graa

        
        Scanner nyInput = new Scanner(System.in);
        System.out.println("Tast 1 for vanlig, tast 0 for veivalg verson");
        int veiellervanlig = nyInput.nextInt();

        if(veiellervanlig == 1){
            Terreng terreng = new Terreng();//lager terreng
            Spiller mann = null;

            System.out.print("hvor mange spillere vil du lage?");
            Scanner nybrukerinput = new Scanner(System.in);
            int nyvalgtall = nybrukerinput.nextInt(); //lar bruker skrive

            int motsetning = 0;
            while(motsetning < nyvalgtall){
                System.out.print("robot (0) eller terminal (1)?");
                int nynyvalgttall = nybrukerinput.nextInt();
                if(nynyvalgttall == 1){
                    Scanner nyscan = new Scanner(System.in);
                    Terminal man = new Terminal(nyscan); //lagrer scanner og terminal
                    System.out.println("VELG NAVNET"); //printer at bruker skal velge navn
                    Scanner brukerinput = new Scanner(System.in);
                    String navn = brukerinput.nextLine(); //lar bruker skrive

                    mann = new Spiller(man, terreng.hentStart(), navn); //lager spiller
                    spillere.leggTil(mann);
                }
                else if(nynyvalgttall == 0){
                    Robot rob = new Robot();
                    System.out.println("VELG NAVNET"); //printer at bruker skal velge navn
                    Scanner brukerinput = new Scanner(System.in);
                    String navn = brukerinput.nextLine(); //lar bruker skrive

                    mann = new Spiller(rob, terreng.hentStart(), navn); //lager spiller
                    spillere.leggTil(mann);
                }

                nyvalgtall--;
            }

            for(Spiller s : spillere){
                int trekk = 5;
                while (trekk>0){ //om det er trekk igjen
                    System.out.print("\n" + trekk + " trekk som er igjen \n"); //si hvor mange trekk som er igjen
                    System.out.print(s.hentnavn() + " sin tur");
                    int sjekkomtrekk = s.nyTrekk(); //ny trekk
                    if(sjekkomtrekk < 3){//sjekker hva som blir returnert, siden ikke alle trekk skal ta trekk
                        trekk--; //senk mengder med trekk
                    }
                }
            }

            int minstetall = -1;
            int stoersteformue = 0;
            Spiller vinner = null;

            if(spillere.stoerrelse() > 0){ //om lista har noe i seg
                for(Spiller speler : spillere){ //sjekker spillere
                    if(speler.hentFormue() >= minstetall){ //sammenligner stoerrelser paa formuer
                        vinner = speler; //stoerste blir vinner
                        minstetall = speler.hentFormue(); //og man skal sammenligne neste spiller med den som er stoerst for naa
                    }
                }
            }

            if(spillere.stoerrelse() > 0){ //sjekker alle spillere
                for(Spiller speler : spillere){ //om en spiller har faatt samme resultat som vinner
                    if(speler.hentFormue() == vinner.hentFormue()){ 
                        vinnere.leggTil(speler); //legg dem til i vinner lista
                    }
                }
            }

            String vinnerString = ""; //lager ny string

            for(Spiller s : vinnere){ //sjekker alle vinnere
                vinnerString += s.sluttsum(); //legger til sluttsimmen til vinnere i stringen
            }

            status = new Text(vinnerString); //status tekst blir lagt ut her
            status.setFill(Color.WHITE); //teksten skal vaere hvit
            status.setX(10);  status.setY(10);
            status.setFont(new Font(15));

            kulisser.getChildren().add(status);
        }
        else if(veiellervanlig == 0){
            VeivalgTerreng terreng = new VeivalgTerreng();//lager terreng
            //System.out.println(terreng.hentStart());
            VeivalgSpiller mann = null;


            System.out.print("hvor mange spillere vil du lage?");
            Scanner nybrukerinput = new Scanner(System.in);
            int nyvalgtall = nybrukerinput.nextInt(); //lar bruker skrive

            int motsetning = 0;

            while(motsetning < nyvalgtall){
                System.out.print("robot (0) eller terminal (1)?");
                int nynyvalgttall = nybrukerinput.nextInt();
                if(nynyvalgttall == 1){
                    Scanner nyscan = new Scanner(System.in);
                    Terminal man = new Terminal(nyscan); //lagrer scanner og terminal
                    System.out.println("VELG NAVNET"); //printer at bruker skal velge navn
                    Scanner brukerinput = new Scanner(System.in);
                    String navn = brukerinput.nextLine(); //lar bruker skrive

                    mann = new VeivalgSpiller(man, terreng.hentStart(), navn); //lager spiller
                    spillere.leggTil(mann);
                }
                else if(nynyvalgttall == 0){
                    Robot rob = new Robot();
                    System.out.println("VELG NAVNET"); //printer at bruker skal velge navn
                    Scanner brukerinput = new Scanner(System.in);
                    String navn = brukerinput.nextLine(); //lar bruker skrive

                    mann = new VeivalgSpiller(rob, terreng.hentStart(), navn); //lager spiller
                    spillere.leggTil(mann);
                }

                nyvalgtall--;
            }

            
            for(Spiller s : spillere){
                int trekk = 5;
                while (trekk>0){ //om det er trekk igjen
                    System.out.print("\n" + trekk + " trekk som er igjen \n"); //si hvor mange trekk som er igjen
                    System.out.println(s.hentnavn() + " sin tur");
                    int sjekkomtrekk = s.nyTrekk(); //ny trekk
                    if(sjekkomtrekk < 3){//sjekker hva som blir returnert, siden ikke alle trekk skal ta trekk
                        trekk--; //senk mengder med trekk
                    }
                }
            }

            int minstetall = -1; //gjoerde det samme som over
            int stoersteformue = 0;
            Spiller vinner = null;

            if(spillere.stoerrelse() > 0){
                for(Spiller speler : spillere){
                    if(speler.hentFormue() >= minstetall){
                        vinner = speler;
                        minstetall = speler.hentFormue();
                    }
                }
            }

            if(spillere.stoerrelse() > 0){
                for(Spiller speler : spillere){
                    if(speler.hentFormue() == vinner.hentFormue()){
                        vinnere.leggTil(speler);
                    }
                }
            }

            String vinnerString = "";

            for(Spiller s : vinnere){
                vinnerString += s.sluttsum();
            }

            status = new Text(vinnerString); //status tekst blir lagt ut her
            status.setFill(Color.WHITE); //teksten skal vaere hvit
            status.setX(10);  status.setY(10);
            status.setFont(new Font(15));

            kulisser.getChildren().add(status);
        }

        Button stoppknapp = new Button("avslutt"); //gjoer det samme som over men med annen event
        stoppknapp.setStyle("-fx-background-color: #222222");
        stoppknapp.setTextFill(Color.WHITE);
        stoppknapp.setLayoutX(300);  stoppknapp.setLayoutY(50);
	    Stoppbehandler stopp = new Stoppbehandler();
        stoppknapp.setOnAction(stopp); //knappen skal avslutte greia

        kulisser.getChildren().add(stoppknapp);
        Scene scene = new Scene(kulisser); //legger til kulissene i scenen
        teater.setTitle("resultat"); //setter tittelen
        teater.setScene(scene); //setter scenen til teateret
        teater.show();
        teater.sizeToScene();
    }

    class Stoppbehandler implements EventHandler<ActionEvent> { //platform exit
        @Override
        public void handle(ActionEvent e) {
	        Platform.exit(); //nar knappen med en variabel av denne klassen trykkes, saa slar programmet seg av
        }
    }
}