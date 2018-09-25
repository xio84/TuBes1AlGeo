import java.util.Scanner;  // needed for Scanner
import java.text.*;
public class TuBes {
    public static void bacam(double mx[][],int m,int n){
        Scanner scanner = new Scanner( System.in );
        for(int i=0; i<m; i++){
              for(int j=0; j<n; j++){
                mx[i][j]=scanner.nextInt();
        }
        }
        System.out.print("\n");
    }
    public static void tulism(double mx[][],int m,int n){
        DecimalFormat df = new DecimalFormat("#.###");
        for(int i=0; i<m; i++){
              for(int j=0; j<n; j++){
                  if (j == (n-1))
                  {
                      System.out.print("| " + df.format(mx[i][j]));
                  }
                else if ((mx[i][j] != -0))
                {
                  System.out.print(df.format(mx[i][j]) + " ");
                } else {
                  System.out.print(0 + " ");
                }
        }
        System.out.print("\n");
        }
    }
    public static void kkalibaris(double mx[][],int m,int n,double x,int i){
              for(int j=0; j<n; j++){
                (mx[i][j])=(mx[i][j])*x;
        }
    }
    public static void susunkali(double mx[][],int m,int n){
        for(int i=0; i<m; i++){
            int j=-1;
            do {
                j++;
                if(mx[i][j] != 0){
                    kkalibaris(mx,m,n,(1/(mx[i][j])),i);
                }
            } while (j<(n-1) && mx[i][j]==0);
        }
    }
    public static void kurangbaris(double mx[][], int m, int n, int i1, int i2, double x){
        for(int j=0; j<n; j++){
                (mx[i1][j])=(mx[i1][j])-(x*mx[i2][j]);
        }
    }
    public static void gaussjordan(double mx[][],int m,int n){
        for(int i=0; i<m; i++){
            susunmatrix(mx,m,n);
            if(mx[i][i] != 0) {
                kkalibaris(mx, m, n, (1 / mx[i][i]), i);
            }
              for(int a=0; a<m; a++){
                  if (a != i){
                      kurangbaris(mx,m,n,a,i,mx[a][i]);
                  }
              }
        }
    }
    public static void ngeGauss(double mx[][],int m,int n){
        for(int i=0; i<m; i++){
            susunmatrix(mx,m,n);
            if(mx[i][i] != 0) {
                kkalibaris(mx, m, n, (1 / mx[i][i]), i);
            }
              for(int a=0; a<m; a++){
                  if (a > i){ // apabila (a != i), maka terbentuk Gauss-Jordan, apabila (a > i), terbentuk Gauss
                      kurangbaris(mx,m,n,a,i,mx[a][i]);
                  }
              }
        }
    }
    public static void hapusbaris(double mx[][],int m,int n,int x){
        for (int j=0; j<n; j++){
            mx[x][j]=0;
        }
    }
    public static void susunmatrix(double mx[][],int m,int n){
        int[] rank = new int[m];
        int x=0;
        int limit;
        double[][] tx = new double[m][n];
        for (int j=0; j<n; j++) {
            for (int i = 0; i < m; i++) {
                tx[i][j]=mx[i][j];
            }
        }
        for (int j=0; j<n; j++) {
            for (int i = 0; i < m; i++) {
                if (tx[i][j] != 0) {
                    rank[x] = i;
                    x++;
                    hapusbaris(tx, m, n, i);
                }
            }
        }
        limit=x-1;
        double[][] nx = new double[m][n];
        for (int j=0; j<n; j++) {
            for (int i = 0; i < m; i++) {
                nx[i][j]=0;
            }
        }
        int y=0;
        while(y<=limit){
            for (int j=0; j<n; j++) {
                nx[y][j] = mx[(rank[y])][j];
            }
            y++;
        }
        for (int j=0; j<n; j++) {
            for (int i = 0; i < m; i++) {
                mx[i][j]=nx[i][j];
            }
        }
      }
    public static void Interpolate(int n){
      Scanner scanner = new Scanner( System.in );
      double[][] mat = new double[n][n+1];
      for(int i = 0;i<n;i++){
        double x = scanner.nextDouble();
        double y = scanner.nextDouble();
        for(int j = 0;j<n+1;j++){
          if (j==0){
            mat[i][j] = 1;
          }
          else if (j==n){
            mat[i][j] = y;
          }
          else {
            mat[i][j] = Math.pow(x , j);
          }
        }
      }
      tulism(mat, n, n+1);
      submenu();
      int sub = scanner.nextInt();
      if (sub==1){
        // pake gauss nanti
      }
      else if (sub==2){
        // pake gauss-jordan ntar
      }

    }
    public static int menu(){
      System.out.println("MENU\n1. Sistem Persamaan Linier\n2. Interpolasi Polinom\n3. Keluar");
      Scanner scanner = new Scanner( System.in );
      int a = scanner.nextInt();
      return a;
    }
    public static int submenu(){
      System.out.println("1. Metode eliminasi Gauss\n2. Metode eliminasi Gauss-Jordan");
      Scanner scanner = new Scanner( System.in );
      int a = scanner.nextInt();
      return a;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner( System.in );
        int m = scanner.nextInt();  // converts a String into an int value
        int n = scanner.nextInt();
        double[][] mx = new double[m][n];
        System.out.println("Sekarang cek Gauss-Jordan");
        TuBes.bacam(mx,m,n);
        gaussjordan(mx,m,n);
        susunkali(mx,m,n);
        TuBes.tulism(mx,m,n);
        double[][] my = new double[m][n];
        System.out.println("Sekarang cek Gauss");
        TuBes.bacam(my,m,n);
        ngeGauss(my,m,n);
        susunkali(my,m,n);
        TuBes.tulism(my,m,n);
    }
}
/* PROGRESS
1. Gauss Jordan
2. Print Augmented format
3. Cara ngeGauss udah ada di file ini
4. Prosedur Interpolate udah ada
5. menu submenu udah jadi
*/
