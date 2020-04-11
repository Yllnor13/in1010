import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Labyrint {
    public Labyrint(){

    }

    public static void lesFraFil(File tall){
        Scanner lesfil = null;
        try{
            lesfil = Scanner(tall);
        }
        catch(FileNotFoundException e){
            System.out.println("ingen fil funnet");
        }
    }
}