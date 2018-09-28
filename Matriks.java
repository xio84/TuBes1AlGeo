import java.util.*;
import java.text.*;

public class Matriks
{
  public double [][] Isi;
  private int bar, kol;

  public Matriks(int m, int n)
  {
    this.bar = m;
    this.kol = n;
    Isi = new double[m][n];
  }

  public void bacam()
  {
    Scanner scanner = new Scanner( System.in );
    for(int i=0; i<this.bar; i++)
    {
          for(int j=0; j<this.kol; j++)
          {
            Isi[i][j]=scanner.nextInt();
          }
    }
    System.out.print("\n");
  }

  public void tulism()
  {
    DecimalFormat df = new DecimalFormat("#.###");
    for(int i=0; i<this.bar; i++)
    {
          for(int j=0; j<this.kol; j++)
          {
              if (j == (this.kol-1))
              {
                  System.out.print("| " + df.format(Isi[i][j]));
              }
              else if ((Isi[i][j] != -0))
              {
                System.out.print(df.format(Isi[i][j]) + " ");
              } else {
                System.out.print(0 + " ");
              }
          }
    System.out.println();
    }
  }

  public void kkalibaris(int i, double x)
  {
    for(int j=0; j<this.kol; j++)
    {
      (Isi[i][j])=(Isi[i][j])*x;
    }
  }

  public void kurangbaris(int i1, int i2, double x)
  {
    for(int j=0; j<this.kol; j++)
    {
          (Isi[i1][j])=(Isi[i1][j])-(x*Isi[i2][j]);
    }
  }

  public void susunkali()
  {
    for(int i=0; i<this.bar; i++)
    {
        int j = -1;
        do {
            j++;
            if(Isi[i][j] != 0)
            {
                this.kkalibaris(i, (1/(Isi[i][j])));
            }
        } while (j<(this.kol-1) && Isi[i][j]==0);
    }
  }

  public void hapusbaris(double mx[][], int n,int i)
  {
    for (int j=0; j<n; j++)
    {
        mx[i][j]=0;
    }
  }
  public void susunmatrix()
  {
    int[] rank = new int[this.bar];
    int x=0;
    double[][] tx = new double[this.bar][this.kol];

    //inisialisasi tx
    for (int j = 0; j < this.kol; j++)
    {
      for (int i = 0; i < this.bar; i++)
      {
        tx[i][j] = Isi[i][j];
      }
    }
    //hapus baris yg perlu dihapus
    for (int j = 0; j < this.kol; j++)
    {
      for (int i = 0; i < this.bar; i++)
      {
        if (tx[i][j] != 0)
        {
            rank[x] = i;
            x += 1;
            hapusbaris(tx, this.kol, i);
        }
      }
    }

    int limit = x-1;
    double[][] nx = new double[this.bar][this.kol];
    //inisialisasi nx dengan 0
    for (int i = 0; i < this.bar; i++)
    {
      for (int j = 0; j < this.kol; j++)
      {
        nx[i][j] = 0;
      }
    }

    int y=0; //iterator
    while(y<=limit)
    {
        for (int j=0; j<this.kol; j++)
        {
            nx[y][j] = Isi[(rank[y])][j];
        }
        y++;
    }
    //memasukkan elemen nx ke this matrix
    for (int j=0; j < this.kol; j++)
    {
        for (int i = 0; i < this.bar; i++)
        {
            Isi[i][j]=nx[i][j];
        }
    }
  }
  public void Gauss()
  {
    for(int i=0; i<this.bar; i++)
    {
      this.susunmatrix();
      if(Isi[i][i] != 0)
      {
          this.kkalibaris(i, (1 / Isi[i][i]));
      }
      for(int a=0; a<this.kol; a++)
      {
        if (a > i)
        {
            this.kurangbaris(a, i,Isi[a][i]);
        }
      }
    }
  }
  public void GaussJordan()
  {
    for(int i=0; i<this.bar; i++)
    {
      this.susunmatrix();
      if(Isi[i][i] != 0)
      {
          this.kkalibaris(i, (1 / Isi[i][i]));
      }
      for(int a=0; a<this.kol; a++)
      {
        if (a != i)
        {
            this.kurangbaris(a, i,Isi[a][i]);
        }
      }
    }
  }

  public void Interpolasi(int n)
  {
    Scanner scanner = new Scanner ( System.in );
    double[][] mat = new double[n][n+1];

    for (int i = 0; i < n; i++)
    {
      double x = scanner.nextDouble();
      double y = scanner.nextDouble();
      for(int j = 0; j < n+1 ; j++)
      {
        if (j == 0)
        {
          mat[i][j] = 1;
        }
        else if (j == n)
        {
          mat[i][j] = y;
        } else {
          mat[i][j] = Math.pow(x , j);
        }
      }
    }
    DecimalFormat df = new DecimalFormat("#.###"); //print mat
    for(int i=0; i<n; i++)
    {
        for(int j=0; j<n+1; j++)
        {
          if (j == (n))
          {
              System.out.print("| " + df.format(mat[i][j]));
          }
          else if ((mat[i][j] != -0))
          {
            System.out.print(df.format(mat[i][j]) + " ");
          } else {
            System.out.print(0 + " ");
          }
        }
        System.out.println();
    }
    submenu();
    int sub = scanner.nextInt();
    if (sub==1)
    {
      // pake gauss nanti
    }
    else if (sub==2)
    {
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
}
