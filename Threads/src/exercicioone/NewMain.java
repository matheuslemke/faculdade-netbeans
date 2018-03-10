package exercicioone;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.util.ArrayList;

/**
 *
 * @author Matheus
 */
public class NewMain
{

    public static class Conta
    {

        private double saldo;

        public Conta(double saldo)
        {
            this.saldo = saldo;
        }

        public void remunerate(double taxa)
        {
            saldo += (saldo * taxa);
        }

        public double getSaldo()
        {
            return saldo;
        }

        public void deposit(double valor)
        {
            saldo += valor;
        }
    }

    public static class Banco implements Runnable
    {

        private ArrayList<Conta> contas = new ArrayList<>();
        private double taxa;

        public Banco(double taxa)
        {
            this.taxa = taxa;
        }

        public void addConta(Conta conta)
        {
            contas.add(conta);
        }

        @Override
        public void run()
        {
            for (int i = 0; i < 50; i++)
            {
                contas.stream().forEach((conta) ->
                {
                    conta.remunerate(taxa);
                });
            }
        }

        @Override
        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            int i = 1;

            for (Conta conta : contas)
            {
                sb.append("Conta ").append(i++).append("\n");
                sb.append("Saldo: ").append(conta.getSaldo()).append("\n");
            }

            sb.append("\n");
            return sb.toString();
        }
    }

    public static class Depositador implements Runnable
    {

        private ArrayList<Conta> contas = new ArrayList<>();
        private double valor;

        public Depositador(double valor)
        {
            this.valor = valor;
        }

        public void addConta(Conta conta)
        {
            contas.add(conta);
        }

        @Override
        public void run()
        {
            for (int i = 0; i < 50; i++)
            {
                contas.stream().forEach((conta) ->
                {
                    conta.deposit(valor);
                });
            }

        }

        @Override
        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            int i = 1;
            contas.stream().forEach((conta) ->
            {
                sb.append("Conta ").append(i).append("\n");
                sb.append("Saldo: ").append(conta.getSaldo());
            });
            return sb.toString();
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Conta conta1 = new Conta(10);
        Conta conta2 = new Conta(20);
        Conta conta3 = new Conta(30);
        Conta conta4 = new Conta(40);
        Banco banco = new Banco(0.001);
        Depositador depositador = new Depositador(1);
        banco.addConta(conta1);
        banco.addConta(conta2);
        banco.addConta(conta3);
        banco.addConta(conta4);
        depositador.addConta(conta1);
        depositador.addConta(conta2);
        depositador.addConta(conta3);
        depositador.addConta(conta4);

        System.out.println(banco.toString());

        Thread remunerar = new Thread(banco);
        Thread depositar = new Thread(depositador);
        remunerar.start();
        depositar.start();

        try
        {
            depositar.join();
            remunerar.join();
        }
        catch (InterruptedException ex)
        {
            printStackTrace();
        }

        System.out.println(banco.toString());

    }

}
