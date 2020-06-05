public class Test {
    public static void main(String args[]){
        Robot robert = new Robot(4);
        Sted sted = new Sted("robert er her");

        Spiller test = new Spiller(sted, robert);
    }
}