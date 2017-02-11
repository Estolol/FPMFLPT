import java.util.Scanner;
import java.util.LinkedList;

class slice {
  public int r1;
  public int c1;
  public int r2;
  public int c2;

  public slice(int r1, int c1, int r2, int c2) {
    this.r1 = r1;
    this.c1 = c1;
    this.r2 = r2;
    this.c2 = c2;
  }

  public slice() {
    this.r1 = 0;
    this.c1 = 0;
    this.r2 = 0;
    this.c2 = 0;
  }

  public void print() {
    System.out.println(r1 + " " + c1 + " " + r2 + " " + c2);
  }
}

public class main {
  public static void main(String[] arg) {
        int R, C, L, H;
        //Type; 0 = Mushroom, 1 = Tomato
        /* Scanning input */
        Scanner in = new Scanner(System.in);
        R = in.nextInt();
        C = in.nextInt();
        L = in.nextInt();
        H = in.nextInt();
        in.nextLine();
        int[][] pizza = new int[R][C];
        for (int i = 0; i < R; i++) {
          String row = in.nextLine();
           for (int j = 0; j < C; j++) {
            pizza[i][j] = (row.charAt(j) == 'T') ? 1 : 0;
          }
        }
        //printTable(pizza);
        /* Solving problem */
        //LinkedList<slice> slices = new LinkedList<slice>();
        LinkedList<slice> slices = generateRandomSlices();
        /* Output solution */
        int S = slices.size();
        System.out.println(S);
        for (int i = 0; i < S; i++) {
          slices.get(i).print();
        }
      }

      static void printTable(int[][] table) {
        int R = table.length;
        int C = table[0].length;
        for (int i = 0; i < R; i++) {
          for (int j = 0; j < C; j++) {
            System.out.print(table[i][j] + " ");
          }
          System.out.println("");
        }
      }
      static LinkedList<slice> generateRandomSlices() {
          LinkedList<slice> result = new LinkedList<slice>();
          for (int i = 1; i < 10; i++) {
            slice slice = new slice();
            slice.r1 = i;
            slice.r2 = i + 1;
            slice.c1 = i;
            slice.c2 = i + 1;
            result.add(slice);
          }
          return result;
        }
}
