import java.util.concurrent.*;

public class Gaaa implements Runnable{
    Rute naborute;
    String posisjon;
    CountDownLatch cdl;

    public Gaaa(Rute nr, String p, CountDownLatch ldc){
        naborute = nr;
        posisjon = p;
        cdl = ldc;
    }

    public void run(){
        naborute.gaa(posisjon);
        cdl.countDown();
    }
}