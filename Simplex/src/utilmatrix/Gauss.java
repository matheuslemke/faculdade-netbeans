/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilmatrix;

/**
 *
 * @author ander
 */
public class Gauss
{

    private static final double EPSILON = 1e-10;

    public static double[] solve(double[][] A, double[] b)
    {
        double[][] Aaux = A.clone();
        double[] baux = b.clone();
        int N = baux.length;

        for (int p = 0; p < N; p++)
        {
            // Encontra o pivô da linha e troca
            int max = p;
            for (int i = p + 1; i < N; i++)
            {
                if (Math.abs(Aaux[i][p]) > Math.abs(Aaux[max][p]))
                {
                    max = i;
                }
            }
            double[] temp = Aaux[p];
            Aaux[p] = Aaux[max];
            Aaux[max] = temp;
            double t = baux[p];
            baux[p] = baux[max];
            baux[max] = t;

            // Não tem solução
            if (Math.abs(Aaux[p][p]) <= EPSILON)
                throw new RuntimeException("Gauss não tem solução!");

            // Pivo dentro de A e B
            for (int i = p + 1; i < N; i++)
            {
                double alpha = Aaux[i][p] / Aaux[p][p];
                baux[i] -= alpha * baux[p];
                for (int j = p; j < N; j++)
                {
                    Aaux[i][j] -= alpha * Aaux[p][j];
                }
            }
        }

        // Substituição
        double[] x = new double[N];
        for (int i = N - 1; i >= 0; i--)
        {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++)
            {
                sum += Aaux[i][j] * x[j];
            }
            x[i] = (baux[i] - sum) / Aaux[i][i];
        }
        return x;
    }

    /*
    private double[][] a;
    private double[] b;
    private double[][] matrix;

    public GaussForSimplex()
    {
    }

    public GaussForSimplex(double[][] a, double[] b)
    {
        this.a = a;
        this.b = b;
        this.matrix = new double[a.length][a.length + 1];
        initializeMatrix();
    }

    public double[] solve() throws GaussHasNotSolution
    {
        double mult;
        for (int j = 0; j < a.length - 1; j++) // Percorre colunas
        {
            if (findPivot(j))
            {
                for (int i = j + 1; i < a.length; i++) // Percorre linhas
                {
                    if (matrix[i][j] != 0)
                    {
                        mult = matrix[i][j] / matrix[j][j];
                        for (int k = 0; k < a.length + 1; k++) // Percorre colunas
                        {
                            matrix[i][k] = matrix[i][k] - mult * matrix[j][k];
                        }
                    }
                }
            } else
                throw new GaussHasNotSolution();
        }

        double[] solution = new double[matrix.length];
        double parcialSolution;

        for (int j = matrix.length - 1; j >= 0; j--)
        {
            parcialSolution = matrix[j][matrix.length];
            for (int k = j + 1; k < matrix.length; k++)
            {
                parcialSolution -= matrix[j][k] * solution[k];
            }
            solution[j] = parcialSolution / matrix[j][j];
        }

        return solution;

        /*
        solution[solution.length - 1] = matrix[matrix.length - 1][matrix.length] / matrix[matrix.length - 1][matrix.length - 1];
        
        solution[solution.length - 2] = matrix[matrix.length - 2][matrix.length] / matrix[matrix.length - 2][matrix.length - 2] + 
                matrix[matrix.length - 1][matrix.length];
        
        for (int i = matrix.length - 2; i >= 0; i--) // Percorre linhas de matrix de baixo pra cima a partir da penúltima linha
        {
            // NOVA IDEIA: RESOLVER O DE BAIXO PRIMEIRO DAI SÓ RECUPERAR AS SOLUÇÕES
            parcialSolution = 0;
            for (int j = i; j < matrix.length - 1; j++) // Percorre colunas de matrix e linhas da solution
            {
                //parcialSolution += matrix[i][matrix.length] / matrix[];
            }
        }
     *//*
    }

    private boolean findPivot(int j)
    {
        showMatrix();
        if (matrix[j][j] == 0)
        {
            System.out.println("Pivo " + j + " é igual a 0");
            if (j != matrix.length - 1)
                for (int i = j + 1; i < matrix.length; i++)
                    if (matrix[i][j] != 0)
                    {
                        changeLines(i, j);
                        return true;
                    }
            System.out.println("Gauss não tem solução");
            return false;
        }
        return true;
    }

    private void changeLines(int i, int j)
    {
        double aux;
        for (int k = 0; k < matrix.length + 1; k++)
        {
            aux = matrix[j][k];
            matrix[j][k] = matrix[i][k];
            matrix[i][k] = aux;
        }
    }

    private void initializeMatrix()
    {
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix.length + 1; j++)
                matrix[i][j] = (j == matrix.length) ? b[i] : a[i][j];
    }

    private void showMatrix()
    {
        System.out.println();
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix.length + 1; j++)
                System.out.print(matrix[i][j] + "\t");
            System.out.println();
        }
    }
     */

}
