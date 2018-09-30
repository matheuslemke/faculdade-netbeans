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
import utilmatrix.Multiplier;
import utilmatrix.Printer;
import utilmatrix.Relation;
import utilmatrix.Transpose;

/**
 *
 * @author ander
 */
public class Simplex
{

    private int n_originalVar;
    private FunctionType functionType;

    private final double[][] constraintsCoeficients;
    private final Relation[] relations;
    private final double[] b;

    public Simplex(double[][] constraintsCoeficients, Relation[] relations, double[] b)
    {
        this.constraintsCoeficients = constraintsCoeficients;
        this.relations = relations;
        for (int i = 0; i < b.length; i++)
            if (b[i] < 0)
                invertConstraint(i);
        this.b = b;
    }

    public SimplexSolution run(FunctionType functionType, double[] objCosts)
    {
        // Definindo o número de variáveis originais
        this.n_originalVar = objCosts.length;

        // Definindo o tipo da função
        this.functionType = functionType;

        // Colocando a matriz na forma padrão
        double[][] A = putOnStandardForm();
        if (Constants.DEBUG)
        {
            System.out.println("A");
            Printer.printMatrix(A);
        }

        // Definindo o número de variáveis incluindo as de folga
        int n_var_total = A[0].length;

        // Convertendo a função para MIN
        if (functionType.equals(FunctionType.MAX))
            for (int i = 0; i < objCosts.length; i++)
                objCosts[i] = -objCosts[i];

        // Adicionando os custos das variáveis incluindo as de folga em outro vetor
        double[] costs = new double[n_var_total];
        System.arraycopy(objCosts, 0, costs, 0, n_originalVar); // TODO: fase 1

        // Encontrando a partição básica inicial
        BasicPartition partition = initialBasicPartition(A);

        // Verificando se há partição factível
        if (Objects.isNull(partition))
            return new SimplexSolution(A, null, null, 0, Constants.NOT_FACTIBLE);

        // Início da iteração simplex
        return simplex(A, n_var_total, partition.getN(), partition.getB(), costs, false);
    }

    private SimplexSolution simplex(double[][] A, int n_var_total,
            int[] N, int[] B, double[] costs, boolean isArtificial)
    {
        /**
         * INÍCIO ITERAÇÃO SIMPLEX
         */
        for (int iteration = 0, artificialIteration = 0;;)
        {
            if (isArtificial)
            {
                System.out.println("------Iteração Artificial " + ++artificialIteration + "------");
                System.out.println("Artificial A");
                Printer.printMatrix(A);
            } else
                System.out.println("------Iteração " + ++iteration + "------");
            if (Constants.DEBUG)
            {
                System.out.println("B indexes");
                Printer.printArray(B);
                System.out.println("N indexes");
                Printer.printArray(N);
                System.out.println("B");
                Printer.printMatrix(getMatrix(A, B));
                System.out.println("b");
                Printer.printArray(b);
            }
            /**
             * Passo 1: Cálculo da solução básica
             */
            double[] x_ = new double[n_var_total];
            double[] x_B = Gauss.solve(getMatrix(A, B), b);

            for (int i = 0; i < x_B.length; i++)
                x_[B[i]] = x_B[i];

            if (Constants.DEBUG)
            {
                System.out.println("x_B");
                Printer.printArray(x_B);
            }
            /**
             * Passo 2: Cálculo dos custos relativos
             */
            /**
             * 2.1: Vetor multiplicativo simplex
             */
            double[] cB = new double[B.length];
            for (int i = 0; i < B.length; i++)
                cB[i] = costs[B[i]];
            if (Constants.DEBUG)
            {
                System.out.println("cB");
                Printer.printArray(cB);
            }
            double[] lambda = Gauss.solve(Transpose.transpose(getMatrix(A, B)), cB);
            if (Constants.DEBUG)
            {
                System.out.println("lambda");
                Printer.printArray(lambda);
            }
            /**
             * 2.2: Custos relativos 2.3: Determinação da variável a entrar na
             * base
             */
            double c_Nk = Double.POSITIVE_INFINITY;
            int k = N.length;
            double[] c_N = new double[N.length];
            for (int j = 0; j < N.length; j++)
            {
                c_N[j] = (costs[N[j]] - Multiplier.multiply(lambda, getColumn(A, N[j])));
                if (c_Nk > c_N[j])
                {
                    c_Nk = c_N[j];
                    k = j;
                }
            }
            if (Constants.DEBUG)
            {
                System.out.println("c_N");
                Printer.printArray(c_N);
                System.out.format("k = %d\n", k);
                System.out.format("N[%d] = %d\n", k, N[k]);
                System.out.format("c_N[%d] = %.2f\n", N[k], c_Nk);
                System.out.println();
            }
            /**
             * Passo 3: Teste de otimalidade
             */
            if (c_Nk >= 0)
            {
                if (isArtificial)
                    return new SimplexSolution(A, null, null, 0, Constants.NOT_FACTIBLE);
                return new SimplexSolution(A, new BasicPartition(N, B), x_, calculateSolution(costs, x_), Constants.OK);
            }
            /**
             * Passo 4: Cálculo da direção simplex
             */
            double[] y = Gauss.solve(getMatrix(A, B), getColumn(A, N[k]));
            if (Constants.DEBUG)
            {
                System.out.println("y");
                Printer.printArray(y);
            }
            /**
             * Passo 5: Determinação do passo e variável a sair da base
             */
            if (isLessOrEqualToZero(y))
                return new SimplexSolution(A, new BasicPartition(N, B), x_, 0, Constants.INFINITE_SOLUTIONS);
            double epsilon_ = Double.POSITIVE_INFINITY;
            int l = B.length;
            double[] epsilons = new double[B.length];
            for (int i = 0; i < epsilons.length; i++)
                epsilons[i] = Double.POSITIVE_INFINITY;
            for (int i = 0; i < x_B.length; i++)
                if (y[i] > 0)
                {
                    epsilons[i] = x_B[i] / y[i];
                    if (epsilon_ > epsilons[i])
                    {
                        epsilon_ = epsilons[i];
                        l = i;
                    }
                }
            if (Constants.DEBUG)
            {
                System.out.println("Epsilons");
                Printer.printArray(epsilons);
                System.out.format("l = %d\n", l);
                System.out.format("B[%d] = %d\n", l, B[l]);
                System.out.format("eps = %.2f\n", epsilon_);
                System.out.println();
            }
            /**
             * Passo 6: Atualização
             */
            int aux = B[l];
            B[l] = N[k];
            N[k] = aux;
            if (isArtificial && areAllArtificialVarOut(B, n_var_total, constraintsCoeficients.length))
                return new SimplexSolution(A, new BasicPartition(N, B), x_, calculateSolution(costs, x_), Constants.OK);
        }
    }

    /**
     *
     * @param A matriz na forma padrão
     * @return [0] Vetor de índices das variaveis não-básicas [1] Vetor de
     * índices das variaveis básicas
     */
    private BasicPartition initialBasicPartition(double[][] A)
    {
        BasicPartition partition;
        if (needFaseOne())
        {
            double[][] artificialA = new double[A.length][A[0].length + constraintsCoeficients.length];
            for (int i = 0, j = A[0].length; i < A.length; i++, j++)
            {
                // Copiando os valores de A para o A artificial
                System.arraycopy(A[i], 0, artificialA[i], 0, A[0].length);
                // Adicionando uma variavel artificial para cada restrição
                artificialA[i][j] = 1;
            }
            // Definindo os custos das variáveis artificiais
            int n_originalsVar = artificialA[0].length - constraintsCoeficients.length;
            double[] artificialCosts = new double[artificialA[0].length];
            for (int i = artificialCosts.length - 1; i >= n_originalsVar; i--)
                artificialCosts[i] = 1;

            int n_nonBasicVar = artificialA[0].length - constraintsCoeficients.length;
            partition = new BasicPartition(new int[n_nonBasicVar], new int[constraintsCoeficients.length]);

            // Chamada para o método simplex artificial
            setPartitionAsIdentity(partition);
            SimplexSolution artificialSolution = simplex(artificialA, artificialA[0].length, partition.getN(), partition.getB(), artificialCosts, true);

            if (artificialSolution.getLabel().equals(Constants.NOT_FACTIBLE))
                return null;
            // Definição da partição B
            partition.setB(artificialSolution.getBasicPartition().getB());
            // Definição da partição N
            int[] artificialN = artificialSolution.getBasicPartition().getN();
            int[] N = new int[n_originalsVar];
            for (int i = 0, j = 0; i < artificialN.length; i++)
            {
                if (artificialN[i] < n_originalsVar)
                    N[j++] = artificialN[i];
            }
            partition.setN(N);
        } else
        {
            int n_nonBasicVar = A[0].length - constraintsCoeficients.length;
            partition = new BasicPartition(new int[n_nonBasicVar], new int[constraintsCoeficients.length]);
            setPartitionAsIdentity(partition);
        }
        return partition;
    }

    private boolean needFaseOne()
    {
        for (Relation relation : relations)
            if (relation.equals(Relation.EQUAL) || relation.equals(Relation.GREATER_THAN))
                return true;
        return false;
    }

    private double[][] getMatrix(double[][] A, int[] B)
    {
        double[][] ret = new double[A.length][B.length];

        for (int i = 0, j = 0; i < B.length; i++, j++)
        {
            for (int k = 0; k < A.length; k++)
            {
                ret[k][j] = A[k][B[i]];
            }
        }
        return ret;
    }

    private double[] getColumn(double[][] A, int index)
    {
        double[] column = new double[A.length];
        for (int i = 0; i < A.length; i++)
            column[i] = A[i][index];
        return column;
    }

    private boolean isLessOrEqualToZero(double[] y)
    {
        for (int i = 0; i < y.length; i++)
        {
            if (y[i] > 0)
                return false;
        }
        return true;
    }

    private double calculateSolution(double[] costs, double[] x_)
    {
        if (functionType.equals(FunctionType.MAX))
            for (int i = 0; i < costs.length; i++)
                costs[i] = -costs[i];

        return Multiplier.multiply(costs, x_);
    }

    private double[][] putOnStandardForm()
    {
        int[] n_relations = getNumberOfRelations();
        double[][] A = new double[constraintsCoeficients.length][n_originalVar + n_relations[0] + n_relations[1]];
        for (int i = 0, j = n_originalVar; i < A.length; i++)
        {
            // Copiando valores das restrições para as primeiras colunas
            System.arraycopy(constraintsCoeficients[i], 0, A[i], 0, n_originalVar);
            // Colocando as variáveis de folga para as restrições de maior e menor igual
            if (!relations[i].equals(Relation.EQUAL))
            {
                if (relations[i] == Relation.GREATER_THAN)
                    A[i][j] = -1;
                else
                    A[i][j] = 1;
                j++;
            }
        }
        return A;
    }

    /**
     *
     * @return [0]Número de restrições LESS_THAN [1]Número de restrições
     * GREATER_THAN [2]Número de restrições EQUAL
     */
    private int[] getNumberOfRelations()
    {
        int less = 0, greater = 0, equal = 0;
        for (Relation relation : relations)
        {
            switch (relation)
            {
                case LESS_THAN:
                    less++;
                    break;
                case GREATER_THAN:
                    greater++;
                    break;
                default:
                    equal++;
                    break;
            }
        }
        return new int[]
        {
            less, greater, equal
        };
    }

    private void setPartitionAsIdentity(BasicPartition partition)
    {
        int[] N = partition.getN(), B = partition.getB();
        // Preenchendo o N com os índices das variáveis do problema
        for (int i = 0; i < N.length; i++)
            N[i] = i;
        // Preenchendo o B com os índices das variáveis de folga
        for (int i = 0, j = N.length; i < B.length; i++, j++)
            B[i] = j;
        partition.setB(B);
        partition.setN(N);
    }

    private boolean areAllArtificialVarOut(int[] B, int n_var_total, int n_artificialVar)
    {
        int[] artificialIndexes = new int[n_artificialVar];
        // Definindo os índices das variáveis artificiais
        for (int i = 0, j = (n_var_total - n_artificialVar); i < artificialIndexes.length; i++)
            artificialIndexes[i] = j;

        for (int i = 0; i < B.length; i++)
            for (int j = 0; j < artificialIndexes.length; j++)
                if (B[i] == artificialIndexes[j])
                    return false;
        return true;
    }

    private void invertConstraint(int i)
    {
        for (int j = 0; j < constraintsCoeficients[i].length; j++)
            constraintsCoeficients[i][j] = -constraintsCoeficients[i][j];
        if (relations[i].equals(Relation.GREATER_THAN))
            relations[i] = Relation.LESS_THAN;
        else if (relations[i].equals(Relation.LESS_THAN))
            relations[i] = Relation.GREATER_THAN;
        b[i] = -b[i];
    }

}
