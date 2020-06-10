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
    private static int trekk = 5;
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

        Scanner nyscan = new Scanner(System.in);
        Terminal man = new Terminal(nyscan); //lagrer scanner og terminal

        
        Scanner nyInput = new Scanner(System.in);
        System.out.println("Tast 1 for vanlig, tast 0 for veivalg verson");
        int veiellervanlig = nyInput.nextInt();

        if(veiellervanlig == 1){
            Terreng terreng = new Terreng();//lager terreng
            Spiller mann = new Spiller(man, terreng.hentStart()); //lager spiller

            System.out.println("VELG NAVNET DITT"); //printer at bruker skal velge navn
            Scanner brukerinput = new Scanner(System.in);
            String navn = brukerinput.nextLine(); //lar bruker skrive
            while (trekk>0){ //om det er trekk igjen
                System.out.print("\n" + trekk + " trekk som er igjen \n"); //si hvor mange trekk som er igjen
                int sjekkomtrekk = mann.nyTrekk(); //ny trekk
                if(sjekkomtrekk < 3){//sjekker hva som blir returnert, siden ikke alle trekk skal ta trekk
                    trekk--; //senk mengder med trekk
                }
            }
            status = new Text(mann.sluttsum()); //status tekst blir lagt ut her
            status.setFill(Color.WHITE); //teksten skal vaere hvit
            status.setX(10);  status.setY(10);
            status.setFont(new Font(15));

            kulisser.getChildren().add(status);
        }
        else if(veiellervanlig == 0){
            VeivalgTerreng terreng = new VeivalgTerreng();//lager terreng
            VeivalgSpiller mann = new VeivalgSpiller(man, terreng.hentStart()); //lager spiller

            System.out.println("VELG NAVNET DITT"); //printer at bruker skal velge navn
            Scanner brukerinput = new Scanner(System.in);
            String navn = brukerinput.nextLine(); //lar bruker skrive
            while (trekk>0){ //om det er trekk igjen
                System.out.print("\n" + trekk + " trekk som er igjen \n"); //si hvor mange trekk som er igjen
                int sjekkomtrekk = mann.nyTrekk(); //ny trekk
                if(sjekkomtrekk < 3){//sjekker hva som blir returnert, siden ikke alle trekk skal ta trekk
                    trekk--; //senk mengder med trekk
                }
            }
            status = new Text(mann.sluttsum()); //status tekst blir lagt ut her
            status.setFill(Color.WHITE); //teksten skal vaere hvit
            status.setX(10);  status.setY(10);
            status.setFont(new Font(15));

            kulisser.getChildren().add(status);
        }

        Button stoppknapp = new Button("avslutt"); //gjoer det samme som over men med annen event
        stoppknapp.setStyle("-fx-background-color: #222222");
        stoppknapp.setTextFill(Color.WHITE);
        stoppknapp.setLayoutX(100);  stoppknapp.setLayoutY(50);
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