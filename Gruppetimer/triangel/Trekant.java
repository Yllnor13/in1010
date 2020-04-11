public class Trekant{

    static void triangel(int m, int n){
        int m1 = m;
        int m2 = m;
        int n1 = n;

        while(m1<=n1){
            for(int i = 0;i < m1; i++){
                System.out.print("*");
            }
            System.out.print("\n");
            m1++;
        }
    
        while(m2<m1){
            m1--;
            for(int i = 0;i < m1; i++){
                System.out.print("*");
            }
            System.out.print("\n");
        }
    }

    static void triangel2(int m, int n){
        String utskrift = "";
        for (int i = 0; i < m ; i++){
            utskrift += "*";
            System.out.println(utskrift);
        }

        if(m==n){
            return;
        }
        else{
            triangel(m-1, n);
        }
    }

    public static void main(String[] args) {
        triangel2(3, 5);
    }
}