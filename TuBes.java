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
    public static void main(String args[]) {
        Scanner scanner = new Scanner( System.in );
        int m = scanner.nextInt();  // converts a String into an int value
        int n = scanner.nextInt();
        double[][] mx = new double[m][n];
        TuBes.bacam(mx,m,n);
        gaussjordan(mx,m,n);
        susunkali(mx,m,n);
        TuBes.tulism(mx,m,n);
    }
}
/* PROGRESS
1. Gauss Jordan
2. Print Augmented format
*/
