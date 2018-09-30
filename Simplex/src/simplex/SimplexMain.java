/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

import java.util.Objects;
import utilmatrix.Constants;
import utilmatrix.FunctionType;
import utilmatrix.Gauss;
import utilmatrix.Printer;
import utilmatrix.Relation;

/**
 *
 * @author ander
 */
public class SimplexMain
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        double[] objCosts =
        {
            -1, -1
        };
        double[][] constraintsCoef =
        {
            {
                1, -1
            },
            {
                -1, 1
            }
        };
        Relation[] relations =
        {
            Relation.LESS_THAN,
            Relation.LESS_THAN
        };
        double[] b =
        {
            4,
            4
        };

        Simplex simplex = new Simplex(constraintsCoef, relations, b);
        SimplexSolution solution = simplex.run(FunctionType.MIN, objCosts);

        if (solution.getLabel().equals(Constants.OK))
        {
            System.out.println();
            for (int i = 0; i < solution.getX_().length * (1.5); i++)
                System.out.print("- ");
            System.out.print("Solution ");
            for (int i = 0; i < solution.getX_().length * (1.5); i++)
                System.out.print("- ");
            System.out.println();

            System.out.println("x_");
            Printer.printArray(solution.getX_());
            System.out.print("Value = ");
            System.out.println(solution.getSolutionValue());

        } else
            System.out.println(solution.getLabel());
    }

}
