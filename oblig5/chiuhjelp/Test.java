import java.util.*;
import java.io.*;

public class Test{
    public static void main(String[] args) {
        try {
            File fil = new File("1.in");
            Labyrint lab = Labyrint.lesFraFil(fil);
            System.out.println(lab);
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke fil");
        }
    }
}