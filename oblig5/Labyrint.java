import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Labyrint {
    private static int rader;       //rader
    private static int kolonner;    //kolonner
    private static Rute[][] ruter;  //todimensjonell array


    private Labyrint(int rad, int kol, Rute[][] rut){
        rader = rad;
        kolonner = kol;
        ruter = rut;
    }

    public static Labyrint lesFraFil(File tall){
        Scanner lesfil = null;
        lesfil = new Scanner(tall);
        
        String info = lesfil.nextLine();
        String[] inforadkol = info.split(" ");
        rader = Integer.parseInt(inforadkol[0]);
        kolonner = Integer.parseInt(inforadkol[1]);
        Rute[][] todimensjonell = new Rute[rader][kolonner];

        int rad = 0;
        while(lesfil.hasNextLine()){
            int kol = 0;
            Rute[] radikart = new Rute[kolonner];
            String lest = lesfil.nextLine();
            String[] tegn = lest.split("");
            for(String t : tegn){
                Rute nyRute = null;
                if(t.equals("#")){
                    nyRute = new SortRute(kol, rad);
                }
                else if (t.equals(".")){
                    if(rad == 0 || rad == rader-1 || kol == 0 || kol == kolonner-1){ //hint 2
                        nyRute = new Aapning(kol, rad);
                    }
                    else{
                        nyRute = new HvitRute(kol, rad);
                    }
                }
                radikart[kol] = nyRute;
                kol++;
            }
            todimensjonell[rad] = radikart;
            rad++;
        }

        //hint 1, finnnabo
        for(int i = 0; i < rader; i++){
            for(int j = 0; j < kolonner; j++){
                Rute over = null;
                Rute under = null;
                Rute venstre = null;
                Rute hoeyre = null;
                Rute denneRuten = null;

                if(i>0){
                    over = todimensjonell[i-1][j];
                }
                if(i<rad-1){
                    under = todimensjonell[i+1][j];
                }
                if(j>0){
                    venstre = todimensjonell[i][j-1];
                }
                if(j<kolonner-1){
                    hoeyre = todimensjonell[i][j+1];
                }
                denneRuten.nyNabo(over, under, venstre, hoeyre);
            }
        }

        Labyrint nyLabyrint = new Labyrint(rader, kolonner, todimensjonell);
        return nyLabyrint;
    }
}