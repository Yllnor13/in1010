import javafx.application.Application; //alt jeg trenger for å kunne kjoere koden under.
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

import java.io.File;
import java.util.Scanner;
import javafx.scene.paint.Color;

//jeg la til ekstra metoder i labyrint for aa hente ut rader, kolonner og 2d array listen
//extenda button til rute, slik at jeg kunne sette det inn som knapper her


public class Oblig7fx extends Application{ //applikasjons starten
    private static boolean[][] losning; //for hjelpemetoden fra skolen
    private static Labyrint l; //labyrint variabel slik at den kan bli brukt flere steder
    private static int rad; //variabel for rader
    private static int kol; //variabel for kolonner
    private static Rute[][] listearray; //variabel for 2d rutearray
    private static Liste<String> utveier; //variabel for utveier
    private static Pane kulisser = new Pane(); //kulisser
    private static GridPane ruter = new GridPane();
    private static boolean finnesutvei = false; //nar den skal vise hvor mange utveier som er igjen, saa burde den vite om det er en vei
    private static Stage teater; //teater scene slik at jeg kan referere til den senere
    Pathfinder let = new Pathfinder(); //klasse som leter etter utvei
    private static Text status; //den er her slik at den kan bli referert til snere
    private static String minstemuligevei; //minste mulige vei som skal bli referert til senere
    

    static boolean[][] losningStringTilTabell(String losningString, int bredde, int hoyde) { //hjelpemetode for aa lage gridpane
        boolean[][] losning = new boolean[hoyde][bredde];
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
        java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
        while (m.find()) {
            int x = Integer.parseInt(m.group(1));
            int y = Integer.parseInt(m.group(2));
            losning[y][x] = true;
        }
        return losning;
    }

    @Override
    public void start(Stage teater){ //teater klassen
        this.teater = teater;
        kulisser.setStyle("-fx-background-color: #353535"); //gjoer bakgrunnen graa


        Text hilsen = new Text("velkommen til labyrint pathfinderen!"); //oeverste teksti
        hilsen.setFill(Color.WHITE); //gjoer teksten hvit
        hilsen.setX(0);  hilsen.setY(16); //plasserer den i teateret
        hilsen.setFont(new Font(15)); //setter stoerrelsen paa font

        Button startKnapp = new Button("velg en labyrint"); //knappen som lar deg velge labyrint
        startKnapp.setStyle("-fx-background-color: #222222"); //gjoer bakgrunnsfargen moerkegraa
        startKnapp.setTextFill(Color.WHITE); //gjoer teksten hvit
        startKnapp.setLayoutX(0);  startKnapp.setLayoutY(50); //setter posisjonen
        Finnbehandler finn = new Finnbehandler(); //lager event
        startKnapp.setOnAction(finn); //legger eventen til knappen


        Button neste = new Button("neste vei"); //gjoer det samme som over men med annen event
        neste.setStyle("-fx-background-color: #222222");
        neste.setTextFill(Color.WHITE);
        neste.setLayoutX(170);  neste.setLayoutY(30);
	    Nestevei n = new Nestevei();
        neste.setOnAction(n);


        Button stoppknapp = new Button("avslutt"); //gjoer det samme som over men med annen event
        stoppknapp.setStyle("-fx-background-color: #222222");
        stoppknapp.setTextFill(Color.WHITE);
        stoppknapp.setLayoutX(100);  stoppknapp.setLayoutY(50);
	    Stoppbehandler stopp = new Stoppbehandler();
        stoppknapp.setOnAction(stopp);


        Button minstevei = new Button("minste vei");//gjoer det samme som over men med annen event
        minstevei.setStyle("-fx-background-color: #222222");
        minstevei.setTextFill(Color.WHITE);
        minstevei.setLayoutX(170);  minstevei.setLayoutY(60);
	    Kortesteleter kort = new Kortesteleter();
        minstevei.setOnAction(kort);


        status = new Text("her er info um utveiene"); //status tekst
        status.setFill(Color.WHITE);
        status.setX(0);  status.setY(40);
        status.setFont(new Font(15));


        kulisser.getChildren().add(hilsen); //legger til knapper og tekster
        kulisser.getChildren().add(stoppknapp);
        kulisser.getChildren().add(startKnapp);
        kulisser.getChildren().add(neste);
        kulisser.getChildren().add(minstevei);
        kulisser.getChildren().add(status);
        Scene scene = new Scene(kulisser); //legger til kulissene i scenen
        teater.setTitle("pathfinder"); //setter tittelen
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

    class Kortesteleter implements EventHandler<ActionEvent> { //skal vise den korteste veien
        @Override
        public void handle(ActionEvent e) {
            if(finnesutvei == true){ //om den fant en utvei fra foer av
                restart(); //gjoer brettet normalt igjen
                status.setText("her er korteste vei");
                losning = losningStringTilTabell(minstemuligevei, rad, kol); //gjoer minste mulige vei om til losningen med hjelpemetoden
                for(int r = 0; r < rad; r++){ //for loekke som gaar gjennom hver rute
                    for(int k = 0; k < kol; k++){
                        if(losning[r][k] == true){ //om losningen returnerer at denne ruten er i den riktige veien
                            listearray[r][k].setStyle("-fx-background-color: #FF0000"); //farg den roed

                            final Rute ru = listearray[r][k];
                            listearray[r][k].setOnMouseExited((g) -> ru.setStyle("-fx-background-color: #FF0000")); //dette er for at den fortsatt er roed etter  at man drar musa vekk
                        }
                    }
                }
            }
            else{ //om man ikke fant vei saa blir man fortalt om det
                status.setText("du fant ingen utveier");
            }
        }
    }

    class Finnbehandler implements EventHandler<ActionEvent> { //skal la brukeren finne filen om de klikker paa den
        @Override
        public void handle(ActionEvent e) {
            File file = new FileChooser().showOpenDialog(teater); //aapner opp file explorer
            try{
                l = Labyrint.lesFraFil(file); //faar l til a lage labyrint med lesfrafil metoden
            } catch(Exception e1) {}
            kol = l.hentKolonner(); //lagrer hvor mange kolonner det er
            rad = l.hentRader(); //lagrer hvor mange rader det er
            listearray = l.hentRuter(); //henter lista

            restart(); //gjoer labyrinten i teateret om til hvordan den skal se ut til vanlig
            
            ruter.getChildren().clear(); //fjerner rutene
            ruter.setMinWidth(0);  ruter.setMaxWidth(900); //minimum og maksimum stoerrelse paa vinduet som den kan forandre paa
            ruter.setMinHeight(0);  ruter.setMaxHeight(900);
            ruter.setGridLinesVisible(true); //la lunjene vaere synlige
            ruter.setLayoutX(0);  ruter.setLayoutY(80); //bestemmer hvor den skal bli vist
            restart(); //gjoer labyrinten i teateret om til hvordan den skal se ut til vanlig
            for(int r = 0; r < rad; r++){ //for loekke
                for(int k = 0; k < kol; k++){
                    ruter.add(listearray[r][k], k, r); //lagrer listearray rutene i ruter
                }
            }
            kulisser.getChildren().add(ruter); //legger til ruter i kulisser
            teater.sizeToScene(); //gjoer teateret like stort som labyrinten
        }
    }

    class Pathfinder implements EventHandler<ActionEvent> { //metoden som skal finne utveien
        @Override
        public void handle(ActionEvent e) {

            restart();

            //bruker metoden jeg hadde i Oblig5V3
            utveier = l.finnUtveiFra(((Rute)e.getSource()).kolPos(),((Rute)e.getSource()).radPos()); //lager en liste med utveier
            if (utveier.stoerrelse() >= 1) { //hvis den fant utveier (altsaa, den er stoerre enn null)
                int lengde = Integer.MAX_VALUE; //lager ny int med stoerste mulige tall
                for (String s : utveier) { //for strenger i utveilista
                    if(s.length() < lengde){ //sjekk om strengen er mindre en lengde, helt til den finner den minste mulige veien ut
                        lengde = s.length();//lagre den korteste lengden hittil, til den finner den korteste veien ut
                        minstemuligevei = s; //saa lagrer den veien som den minste mulige veien
                    }
                }
            }

            if(utveier.stoerrelse() >= 1){ //sjekker om det er utveier fra der man trakk
                finnesutvei = true; //gjoer finnes utvei om til true
                String dennevei = utveier.fjern(); //gjoer denne veien om til den foreste i lista
                if(utveier.stoerrelse() == 1){ //om det er bare en vei igjen
                    status.setText(utveier.stoerrelse() + " utvei igjen."); //si at det bare er en vei igjen
                }
                else if(utveier.stoerrelse() > 1){ //om det er fler
                    status.setText(utveier.stoerrelse() + " utveier igjen."); //si hvor mange veier det er igjen
                }
                losning = losningStringTilTabell(dennevei, rad, kol); //bruker hjelpemetoden til a lage vei
                for(int r = 0; r < rad; r++){ //for loekke som gaar gjennom hele labyrinten
                    for(int k = 0; k < kol; k++){
                        if(losning[r][k] == true){
                            listearray[r][k].setStyle("-fx-background-color: #FF0000"); //gjoer fargen roed

                            final Rute ru = listearray[r][k];
                            listearray[r][k].setOnMouseExited((g) -> ru.setStyle("-fx-background-color: #FF0000")); //lar fargen bli roed selv om musa gaar av ruten
                        }
                    }
                }
            }
            else{ //forteller brukeren om de ikke fant en utvei
                status.setText("fant ingen utvei");
            }
        }
    }

    public void restart(){ //metode for aa sette fargene tilbake til normalr i labyrinten
        for(int r = 0; r < rad; r++){ //for loekke som gaar gjennom hele labyrinten
            for(int k = 0; k < kol; k++){
                if(listearray[r][k].tilTegn() == '#'){ //hvis det er en svart rute
                    listearray[r][k].setStyle("-fx-background-color: #111111"); //sette fargen til dette
                }
                else{ //ellers, om det er en hvit rute
                    listearray[r][k].setOnAction(let); //registrerer let til alle knappene, slik at hver knapp kan finne veien ut
                    listearray[r][k].setStyle("-fx-background-color: #454545"); //gjoer fargen om til litt lysere graa
                    final Rute ru = listearray[r][k];
                    listearray[r][k].setOnMouseEntered((g) -> ru.setStyle("-fx-background-color: #9FAEEB")); //gjoer fargen lyseblaa om man setter musa over ruten
                    listearray[r][k].setOnMouseExited((g) -> ru.setStyle("-fx-background-color: #454545")); //sett fargen tilbake til normal igjen
                }
            }
        }
    }

    class Nestevei implements EventHandler<ActionEvent> { //skal vise neste vei i lista
        @Override
        public void handle(ActionEvent e) {
	        if(finnesutvei == true && utveier.stoerrelse() >= 1){ //sjekker om man har funnet vei fra foer av, og om det mer i lista
                restart(); //setter labyrintens farger tilbake til normalt
                if(utveier.stoerrelse() > 1){ //sjekker hvor mange utveier det er igjen for aa vise annen tekst
                    status.setText(utveier.stoerrelse() + " utveier igjen.");
                }
                else if(utveier.stoerrelse() == 1){
                    status.setText(utveier.stoerrelse() + " utvei igjen.");
                }
                String dennevei = utveier.fjern(); //gjoer det samme som let metoden nar den skal finne utvei
                losning = losningStringTilTabell(dennevei, kol, rad);
                for(int r = 0; r < rad; r++){
                    for(int k = 0; k < kol; k++){
                        if(losning[r][k] == true){
                            listearray[r][k].setStyle("-fx-background-color: #FF0000");

                            final Rute ru = listearray[r][k];
                            listearray[r][k].setOnMouseExited((g) -> ru.setStyle("-fx-background-color: #FF0000"));
                        }
                    }
                }
            }
            else{ //forteller brukeren om de ikke fant utvei
                status.setText("ingen utvei");
            }
        }
    }

    public static void main(String[] args){
        launch(args); //launcher greia
    }
}