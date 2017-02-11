import java.util.*;
import java.lang.Math;


public class Pizza{
  int R,C,L,H;
  int[][] pizza;
  int[][] work;
  int minr;
  int minc;
  public Pizza(Scanner in){
    this.R = in.nextInt();
    this.C = in.nextInt();
    this.L = in.nextInt();
    this.H = in.nextInt();
    in.nextLine();
    this.pizza = new int[R][C];
    for (int i = 0; i < R; i++) {
      String row = in.nextLine();
       for (int j = 0; j < C; j++) {
        this.pizza[i][j] = (row.charAt(j) == 'T') ? 1 : 0;
      }
    }
    this.work = pizza.clone();
    minr=0;
    minc=0;
  }

  int c2lb(int c1,int r2,int r1,int bestslice){
    int mini = Math.max(2*L, bestslice+1);
    return Math.max(0,c1+(int) Math.floor(((double) mini / ((double) (1 + r2 - r1))))-1);
  }

  int c2iv(int c1,int r2,int r1){
    return Math.min(c1+(int) Math.floor(((float) H / (1+ r2 - r1)))-1,C-1);
  }

  public Slice max(){
    int maxr1 = 0,maxr2 = 0,maxc1 = 0,maxc2 = 0;
    int bestslice = 0;

    for(int r1=0;r1<R;r1++){
      //System.out.println(R);
      for(int c1=0;c1<C;c1++){
        //System.out.println(C);
        for(int r2=Math.min(r1+H-1,R-1);r2>=r1;r2--){
          //System.out.println(r1);
          //System.out.println("diff: "+(1 + r2 - r1));
          for(int c2=c2iv(c1,r2,r1);c2>=c2lb(c1,r2,r1,bestslice);c2--){
            //System.out.println("c1: "+c1);
            //System.out.println("c2: "+c2);
            //System.out.println("lower bound:"+c2lb(c1,r2,r1,bestslice));
            boolean breakin = false;
            int jsave=0 ;
            int countM=0;
            int countT=0;
            for(int j=c1;j<=c2;j++){
              for(int i=r1;i<=r2;i++){
                if(work[i][j]==(-1)){
                  breakin=true;
                  jsave = j;
                  break;
                }
                else if(work[i][j]==(0))
                  countM+=1;
                else
                  countT+=1;
              }
              if(breakin){
                break;
              }
            }
            if(breakin){
              c2=jsave;
            }
            else if(countT >= L && countM >= L){
              bestslice = (1+c2-c1)*(1+r2-r1);
              maxr1 = r1;
              maxr2 = r2;
              maxc1 = c1;
              maxc2 = c2;
            }
          }
        }
      }
    }
    
    for(int j=maxc1;j<=maxc2;j++){
      for(int i=maxr1;i<=maxr2;i++){
        work[i][j]=(-1);
      }
    }

    if(bestslice==0){
      return null;
    }

    return new Slice(maxr1,maxc1,maxr2,maxc2);
  }


}
