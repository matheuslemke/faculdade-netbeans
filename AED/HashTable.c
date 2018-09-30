#include <stdio.h>
#include <stdlib.h>
#define N 10

typedef struct NodeTag
{
    int x;
    struct NodeTag *link;
} Node;

int espalhamentoDiv(int k)
{
    return k % N;
}

void remover(Node *v[], int k)
{
    int i = espalhamentoDiv(k);
    Node *aux = v[i], *anterior;
    if (aux == NULL)
    {
        printf("Valor %d nao encontrado!\n", k);
        return;
    }

    if (aux->x == k)
    {
        v[i] = v[i]->link;
        return;
    }

    while (aux->x != k)
    {
        anterior = aux;
        aux = aux->link;
        if (aux == NULL)
        {
            printf("Valor %d nao encontrado!\n", k);
            return;
        }
    }
    anterior->link = aux->link;
    free(aux);
}

void add(Node *v[], int k)
{
    int i = espalhamentoDiv(k);

    Node *new = (Node *) malloc(sizeof (Node)), *aux = v[i];
    new->x = k;
    new->link = NULL;

    if (aux == NULL)
        v[i] = new;
    else
    {
        while (aux->link != NULL)
            aux = aux->link;
        aux->link = new;
    }
}

void mostrarVetor(Node *v[])
{
    printf("Vetor:\n");
    Node *aux;
    int j;
    for (j = 0; j < N; j++)
    {
        aux = v[j];
        while (aux != NULL)
        {
            printf("%d ", aux->x);
            aux = aux->link;
        }
    }
    printf("\n\n");
}

void buscar(Node *v[], int k)
{
    Node *aux;
    int j;
    for (j = 0; j < N; j++)
    {
        aux = v[j];
        while (aux != NULL)
        {
            if (aux->x == k)
            {
                printf("Valor %d encontrado!\n", k);
                return;
            }
            aux = aux->link;
        }
    }
    printf("Valor %d nao encontrado!\n", k);

}

int main()
{
    Node * v[N] = {NULL};

    add(v, 10);
    add(v, 32);
    add(v, 14);
    add(v, 2);
    add(v, 22);
    add(v, 0);
    add(v, 12);
    add(v, 19);
    add(v, 5);
    add(v, 29);

    mostrarVetor(v);

    remover(v, 5);
    remover(v, 10);
    remover(v, 12);
    remover(v, 2);
    remover(v, 32);
    remover(v, 22);
    remover(v, 29);

    mostrarVetor(v);

    remover(v, 50);
    
    buscar(v, 10);

    return (EXIT_SUCCESS);
}

