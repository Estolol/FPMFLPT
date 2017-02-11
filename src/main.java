import java.util.Scanner;
public class main {
  public static void main(String[] arg) {
        System.out.println("Beginning");
        int R, C, L, H;
        //Type; 0 = Mushroom, 1 = Tomato
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
        printTable(pizza);
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
}
