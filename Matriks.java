import java.util.*;
import java.text.*;
import java.lang.Math.*;
import java.io.*;

public class Matriks
{
  public double [][] Isi;
  private int bar, kol;
  Scanner scanner = new Scanner(System.in);
  public String pers, persI;
  public double hasil;

  public Matriks(int m, int n)
  //I.S. m dan n terdefinisi
  //F.S. terbentuk matriks dengan jumlah baris m dan jumlah kolom n
  {
    this.bar = m;
    this.kol = n;
    Isi = new double[m][n];
    pers = new String("");
    persI = new String("");
    this.hasil=0;
  }

  public void bacam()
  //I.S. ukuran Isi terdefinisi
  //F.S. Matriks Isi memiliki isi sesuai input user
  {
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
  //I.S. Isi terdefinisi
  //F.S. menampilkan Matriks Isi dengan format Augmented Matriks
  {
    //DecimalFormat df = new DecimalFormat("#.##");
    for(int i=0; i<this.bar; i++)
    {
          for(int j=0; j<this.kol; j++)
          {
              if (j == (this.kol-1))
              {
                  System.out.printf("| %.2f", Isi[i][j]);
              }
              else if ((Isi[i][j] != -0))
              {
                System.out.printf("%.2f ", Isi[i][j]);
              } else {
                System.out.printf("%.2f ", 0.00);
              }
          }
    System.out.println();
    }
  }

  public void kkalibaris(int i, double x)
  //I.S. Matriks Isi dan skalar terdefinsisi
  //F.S. Salah satu baris matriks Isi dikalikan dengan skalar
  {
    for(int j=0; j<this.kol; j++)
    {
      (Isi[i][j])=(Isi[i][j])*x;
    }
  }

  public void kurangbaris(int i1, int i2, double x)
  //I.S. matriks Isi, nomor baris, dan skalar terdefinisi
  //F.S. salah satu baris pada matriks dikurangi dengan skalar*baris lain
  {
    for(int j=0; j<this.kol; j++)
    {
          (Isi[i1][j])=(Isi[i1][j])-(x*Isi[i2][j]);
    }
  }

  public void susunkali()
  //I.S. Isi terdefinisi
  //F.S. membuat semua pivot point menjadi 1
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
  //I.S. Isi terdefinsisi
  //F.S. menghapus(mengganti semua elemen dengan 0) dalam satu baris
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
  //I.S. Isi terdefinsisi
  //F.F menghasilkan matriks yang sudah menjadi Row Echelon Form
  {
    int n = (bar<kol)? bar:kol;
    for(int i=0; i<n; i++)
    {
      susunmatrix();
      if(Isi[i][i] != 0)
      {
          kkalibaris(i, (1 / Isi[i][i]));
      }

      for(int a=0; a<bar; a++)
      {
        if (a > i)
        {
            kurangbaris(a, i,Isi[a][i]);
        }
      }
    }
  }
  public void GaussJordan()
  //I.S. Isi terdefinsisi
  //F.S. terbentuk matriks yang sudah menjadi bentuk Gauss-Jordan
  {
    Gauss();
    int n = (bar<kol)? bar:kol;
    for(int i=0; i<n; i++)
    {
      susunmatrix();
      if(Isi[i][i] != 0)
      {
          kkalibaris(i, (1 / Isi[i][i]));
      }

      for(int a=0; a<bar; a++)
      {
        if (a < i)
        {
            kurangbaris(a, i,Isi[a][i]);
        }
      }
    }
  }

  public void Interpolasi(int n)
  //I.S. banyaknya titik terdefinsisi
  //F.S. membentuk sebuah matriks interpolasi dan memberikan solusi.
  //Lalu membuat persamaan polinom dan menyelesaikan fungsinya
  {
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
    /*DecimalFormat df = new DecimalFormat("#.###"); //print mat*/
    for(int i=0; i<n; i++)
    {
        for(int j=0; j<n+1; j++)
        {
          if (j == (n))
          {
              System.out.printf("| %.2f", mat[i][j]);
          }
          else if ((mat[i][j] != -0))
          {
            System.out.printf("%.2f ", mat[i][j]);
          } else {
            System.out.printf("%.2f ", 0.00);
          }
        }
        System.out.println();
    }
    menu3();
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
  
  public void solveGaussJordan()
    //I.S. Isi terdefinisi dan dalam bentuk row echelon
    //F.S. Terbentuk persamaan dari matriks row echelon
  {
      GaussJordan();
      int n = (bar < kol) ? bar : kol;
      if(Isi[0][0]!=0){
          pers=pers + "X" + "1" + " = " + String.format("%.2f", Isi[0][kol-1]) + ";";
      }
      for (int i = 1; i < n; i++) {
          pers=pers + "X" + (i+1) + " = " + String.format("%.2f", Isi[i][kol-1]) + ";";
      }
  }
   public void solveInterpolasi(int x)
    //I.S. Isi terdefinisi dan dalam bentuk row echelon
    //F.S. Terbentuk persamaan interpolasi dari matriks row echelon
  {
      int n = (bar < kol) ? bar : kol;
      if(Isi[0][0]!=0){
          persI=persI + String.format("%.2f", Isi[0][kol-1]) + "X^" + (n-1);
      }
      for (int i = 1; i < n; i++) {
          if((n-1-i)==0){
              persI=persI + "+" + String.format("%.2f", Isi[i][kol-1]);
          }
          else {
              persI = persI + "+" + String.format("%.2f", Isi[i][kol - 1]) + "X^" + (n - 1 - i);
          }
      }
      persI=persI + "=0";
      for (int i =0; i<n; i++){
          this.hasil=this.hasil+((Math.round((Isi[i][kol-1])*100)/100)*(x^(n-1-i)));
      }
  }
  public void bacafile()
  //Membaca file yang berisi matriks dan mengisikan ke bentuk matriks
  {
    try
    {
      int Nbar = -1;
      int Nkol = -1;
      ArrayList<ArrayList<Double>> baca = new ArrayList<ArrayList<Double>>();
      String namfil = new String();
      System.out.print("Masukkan Nama File(diakhiri .txt) : ");
      namfil = scanner.nextLine(); //menginput nama file
      System.out.println();
      File f = new File(namfil);
      Scanner sb = new Scanner(f);
      while (sb.hasNextLine())
      {
          Nbar++;
          baca.add(new ArrayList<Double>());
          String sebaris = sb.nextLine();
          Scanner sa = new Scanner(sebaris);
          while (sa.hasNextDouble())
          {
            Double angka = sa.nextDouble();
            baca.get(Nbar).add(angka);
          }
      }
      if (Nbar == -1)
      {
        System.out.println("File Kosong");
      }
      else
      {
        Nkol = baca.get(0).size();
        this.Isi = new double[baca.size()][baca.get(0).size()];
        for (int i = 0; i <= Nbar ; i++){
          for (int j = 0; j < Nkol ; j++){
              Isi[i][j] = baca.get(i).get(j);
          }
        }
        this.bar = Nbar + 1;
        this.kol = Nkol;
      }
    } catch (Exception e) {
            System.out.println("Error : " + e);
    }
  }

  public boolean nosol()
  {
    int i = this.bar-1;
    int j = 0; boolean iya = false;
    boolean IsNoSol = false;
    while ((j <= this.kol-1) && !(iya))
    {
      if (j != this.kol-1)
      {
        if (Isi[i][j] == 0)
        {
          j++;
        } else {
          iya = true;
        }
      } else { //j == this.kol-1
        if (Isi[i][j] != 0)
        {
          IsNoSol = true;
        }
      }
    }
    return IsNoSol;
  }
  public static int menu1()
  //menuliskan menu awal dan membaca masukan menu
  {
    System.out.println("\nMENU 1\n1. Sistem Persamaan Linier\n2. Interpolasi Polinom\n3. Keluar\n");
    Scanner scanner = new Scanner(System.in);
    System.out.print("Inputmu : ");
    int hasil = scanner.nextInt();
    return hasil;
  }

  public static int menu2()
  {
    System.out.println("\nMENU 2\n1. Matriks Input\n2. Matriks dari File\n3. Keluar\n");
    Scanner scanner = new Scanner(System.in);
    System.out.print("Inputmu : ");
    int hasil = scanner.nextInt();
    return hasil;
  }

  public static int menu3()
  {
    System.out.println("\nMENU 3\n1. Gauss\n2. Gauss Jordan\n3. Keluar\n");
    Scanner scanner = new Scanner(System.in);
    System.out.print("Inputmu : ");
    int hasil = scanner.nextInt();
    return hasil;
  }
}
