#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct NoTree
{
    int info;
    struct NoTree *Llink;
    struct NoTree *Rlink;
} TreeNo;

int addABB(TreeNo **t, int value)
{
    if (*t == NULL)
    {
        if ((*t = malloc(sizeof (TreeNo))) != NULL)
        {
            (*t)->info = value;
            (*t)->Llink = NULL;
            (*t)->Rlink = NULL;
        }
        else return false;
    }
    else
    {
        if (value < (*t)->info)
            addABB(&((*t)->Llink), value);
        else if (value > (*t)->info)
            addABB(&((*t)->Rlink), value);
        else return false;
    }
    return true;
}

bool hasSonL(TreeNo **t)
{
    if ((*t)->Llink != NULL) return true;
    else return false;
}

bool hasSonR(TreeNo **t)
{
    if ((*t)->Rlink != NULL) return true;
    else return false;
}

void removeABB(TreeNo **t, int value)
{
    int retorno;

    if (*t == NULL) return;

    else if (value < (*t)->info)
        removeABB(&(*t)->Llink, value);

    else if (value > (*t)->info)
        removeABB(&(*t)->Rlink, value);

    else
    {
        TreeNo *aux;
        if (!(hasSonL(t)) && !(hasSonR(t)))
        {
            free(*t);
            *t = NULL;
        }
        else if ((hasSonL(t)) && !(hasSonR(t)))
        {
            aux = (*t)->Llink;
            *t = aux;
            free(aux);
        }
        else if (!(hasSonL(t)) && (hasSonR(t)))
        {
            aux = (*t)->Rlink;
            *t = aux;
            free(aux);
        }
        else
        {
            aux = (*t)->Rlink;
            while (aux != NULL)
            {
                aux = aux->Llink;
            }
            (*t)->info = aux->info;

            aux = (*t)->Rlink;

            removeABB(&aux, aux->info);
        }
    }
}

void order(TreeNo **t)
{
    if (*t == NULL) return;

    else
    {
        order((*t)->Llink);
        printf("%d ", (*t)->info);
        order((*t)->Rlink);
    }
    printf("\n");
}

void preOrder(TreeNo **t)
{
    if (*t == NULL) return;

    else
    {
        printf("%d ", (*t)->info);
        preOrder((*t)->Llink);
        preOrder((*t)->Rlink);
    }
    printf("\n");
}

void posOrder(TreeNo **t)
{
    if (*t == NULL) return;

    else
    {
        posOrder((*t)->Llink);
        posOrder((*t)->Rlink);
        printf("%d ", (*t)->info);
    }
    printf("\n");
}

bool searchABB(TreeNo **t, int value)
{
    if (*t == NULL)
        return NULL;

    if ((*t)->info == value)
        return true;

    if (value < (*t)->info)
        searchABB(&((*t)->Llink), value);

    if (value > (*t)->info)
        searchABB(&((*t)->Rlink), value);
}

int main()
{
    TreeNo *t = NULL;
    addABB(&t, 1);
    addABB(&t, 2);
    printf("%d\n", searchABB(&t, 2));
    removeABB(&t, 2);
    printf("%d\n", searchABB(&t, 2));

    return (EXIT_SUCCESS);
}

