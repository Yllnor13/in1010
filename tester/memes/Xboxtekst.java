public class Xboxtekst{
    static int xbox = 1;
    public static void main(String[] args){
        System.out.println("Xbox 360? what happened to...");
        System.out.print("Xbox 1, ");
        while(xbox < 359){
            xbox ++;
            System.out.print(", Xbox " + xbox);
        }
        System.out.print("?\n" );
    }
}