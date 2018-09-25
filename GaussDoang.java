import java.io.*;
import java.util.*;
import java.text.*;

/* isinya prosedur gauss sama prosedur tukarbaris*/
public class GaussDoang
{
  public static void bacam(double mx[][],int m,int n)
  {
      Scanner scanner = new Scanner( System.in );
      for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
              mx[i][j]=scanner.nextInt();
      }
      }
      System.out.println();
  }
  public static void tulism(double mx[][],int m,int n)
  {
      DecimalFormat df = new DecimalFormat("#.##");
      for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
              if ((mx[i][j] != -0))
              {
                System.out.print(df.format(mx[i][j]) + " ");
              } else {
                System.out.print(0 + " ");
              }
              if (j == (n-2))
              {
                System.out.print("| ");
              }
      }
      System.out.print("\n");
      }
  }

  public static double[][] ref(double[][] mat, int m, int n)
  {
    double[][] ref = new double[m][n];

    /* Copy matrix */
    for (int r = 0; r < m; r++)
    {
        for (int c = 0; c < n; c++)
        {
            ref[r][c] = mat[r][c];
        }
    }

    for (int p = 0; p < m; p++)
    {
        /* Mmembuat leading point 1 */
        double pv = ref[p][p];
        if (pv != 0)
        {
            double pvInv = 1.0 / pv;
            for (int i = 0; i < n; i++)
            {
                ref[p][i] *= pvInv;
            }
        }

        /* nge-Gauss biasa*/
        for (int r = 0; r < m; r++)
        {
            if (r > p)
            {
                double bagi = ref[r][p];
                for (int i = 0; i < n; i++)
                {
                    ref[r][i] -= bagi * ref[p][i];
                }
            }
        }
    }
    /* cari leading point*/
    for (int a = 0; a < m; a++)
    {
      int c = 0; boolean lp = false;
      double semen = 1;
      while ((c < n) && !(lp))
      {
        if (ref[a][c] != 0)
        {
          lp = true;
          semen = ref[a][c];
        } else {
          c++;
        }
      }
      for (int b = 0; b < n; b++)
      {
        ref[a][b] = ref[a][b] / semen;
      }
    }

    return ref;
  }
  public static void TukarBaris(double[][] mx, int m, int n, int a, int b)
  {
    double temp;
    for (int j = 0; i < n; i++)
    {
      temp = mx[a][j];
      mx[a][j] = mx[b][j];
      mx[b][j] = temp;
    }
  }
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner( System.in );
    int m = scanner.nextInt();  // converts a String into an int value
    int n = scanner.nextInt();
    double[][] mx = new double[m][n];
    double[][] ma = new double[m][n];
    bacam(mx,m,n);
    ma = ref(mx, m, n);
    tulism(ma,m,n);
  }
}
