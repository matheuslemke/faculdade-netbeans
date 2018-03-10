#include <stdio.h>
#define MAX 9

void build_max_heap(int [ ], int);
void heapsort(int [ ], int);

void main()
{
    int vetor[MAX] = {3, 0, 2, 1, 9, 6, 7, 8, 4}, i;

    printf("\nVetor:\n");
    for (i = 0; i < MAX; i++)
        printf("%d ", vetor[i]);

    heapsort(vetor, MAX);

    printf("\n\nVetor ordenado:\n");
    for (i = 0; i < MAX; i++)
        printf("%d ", vetor[i]);

    printf("\n\n");
}

void max_heapify(int vetor[], int i)
{
    int val, s, f;
    val = vetor[i];
    s = i;
    f = (s - 1) / 2;
    while (s > 0 && vetor[f] < val)
    {
        vetor[s] = vetor[f];
        s = f;
        f = (s - 1) / 2;
    }
    vetor[s] = val;
}

void build_max_heap(int vetor[ ], int indice)
{
    int i;
    for (i = 1; i < indice; i++)
    {
        max_heapify(vetor, i);
    }
}

void heapsort(int vetor[], int indice)
{
    int i, s, f, ivalue;

    build_max_heap(vetor, indice);

    for (i = indice - 1; i > 0; i--)
    {
        ivalue = vetor[i];
        vetor[i] = vetor[0];
        f = 0;

        if (i == 1)
            s = -1;
        else
            s = 1;

        if (i > 2 && vetor[2] > vetor[1])
            s = 2;

        while (s >= 0 && ivalue < vetor[s])
        {
            vetor[f] = vetor[s];
            f = s;
            s = 2 * f + 1;

            if (s + 1 <= i - 1 && vetor[s] < vetor[s + 1])
                s++;
            if (s > i - 1)
                s = -1;
        }

        vetor[f] = ivalue;
    }
}