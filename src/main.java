import com.jom.*;

public class main {
    //  Minimize z = -.5 * x1 + .5 * x2 - x3 + 1
    //
    //  subject to
    //  0.0 <= x1 - .5 * x2 <= 0.2
    //  -x2 + x3 <= 0.4
    //  where,
    //  0.0 <= x1 <= 0.5
    //  0.0 <= x2 <= 0.5
    //  0.0 <= x3 <= 0.5

    public static void main(String[] arg) {
      OptimizationProblem op = new OptimizationProblem();
      int N = 5;
      op.addDecisionVariable("x", true, new int[] { 5 , 5 , 5 }, 0, 1);
      op.setInputParameter("c", new DoubleMatrixND(new int [] { N , N , N} , "random"));
      op.setObjectiveFunction("maximize", "sum(x .* c)");
      op.addConstraint(" sum(sum(x,2),1) <= 1");
      op.solve("glpk", new Object[]{"solverLibraryName", "libglpk.so.36"});
      if (!op.solutionIsOptimal ()) throw new RuntimeException ("An optimal solution was not found");
      DoubleMatrixND sol = op.getPrimalSolution("x");
      System.out.println(sol.toString());
    }
}
