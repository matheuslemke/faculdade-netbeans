package letra.a;

import util.prova.HeapSort;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author matheus
 */
public class ProvaAnalise
{

    private static void gerarArquivos(int[] arquivos, int tamanhoMaximoPorArquivo)
    {
        for (int i = 0; i < arquivos.length; i++)
            arquivos[i] = ThreadLocalRandom.current().nextInt(1, tamanhoMaximoPorArquivo);
    }

    public static void main(String[] args)
    {
        int tamanhoHD = 1048576;
        int numArquivosTotal = 10000;
        int tamanhoMaximoPorArquivo = 32768;
        int[] arquivos = new int[numArquivosTotal];
        int espacoRestante = tamanhoHD, i;
        boolean cabeMais = true;

        gerarArquivos(arquivos, tamanhoMaximoPorArquivo);

        HeapSort.sort(arquivos);

        for (i = 0; i < numArquivosTotal && cabeMais; i++)
        {
            if (espacoRestante >= arquivos[i])
            {
                espacoRestante -= arquivos[i];
                System.out.println("Arquivo adicionado! Tamanho: " + arquivos[i]);
            }
            else
                cabeMais = false;
        }
        System.out.println("Número de arquivos adicionados: " + i);
        System.out.println("Espaço ocupado: " + (tamanhoHD - espacoRestante));
    }
}
