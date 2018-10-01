import java.util.*;  // needed for Scanner
import java.text.*;

/* memanggil Matriks.Java */
public class TuBes {

    public static void main(String args[]) {
        Scanner scanner = new Scanner( System.in );
        int m = scanner.nextInt();  // converts a String into an int value
        int n = scanner.nextInt();
        Matriks mat = new Matriks(m, n);
        mat.bacam();
        mat.GaussJordan();
        mat.tulism();
        mat.solveGaussJordan();
        mat.solveInterpolasi(3);
        System.out.printf("%.2f",mat.hasil);
    }
}
/* PROGRESS
1. Gauss Jordan
2. Print Augmented format
3. Cara nge-gauss ada di file GaussDoang.java
4. uda ngebuat Matriks.Java
*/
