#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <windows.h>

typedef struct NoTree
{
    int info;
    struct NoTree *Llink;
    struct NoTree *Rlink;
} TreeNo, NoTree;

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
            aux = *t;
            *t = (*t)->Llink;
            free(aux);
        }
        else if (!(hasSonL(t)) && (hasSonR(t)))
        {
            aux = *t;
            *t = (*t)->Rlink;
            free(aux);
        }
        else
        {
            TreeNo *sucessor;
            sucessor = (*t)->Rlink;

            while (sucessor->Llink != NULL)
            {
                sucessor = sucessor->Llink;
            }
            (*t)->info = sucessor->info;
            removeABB(&(*t)->Rlink, sucessor->info);
        }
    }
}

void order(TreeNo **t)
{
    if (*t == NULL) return;

    else
    {
        order(&(*t)->Llink);
        printf("%d ", (*t)->info);
        order(&(*t)->Rlink);
    }
}

void preOrder(TreeNo **t)
{
    if (*t == NULL) return;

    else
    {
        printf("%d ", (*t)->info);
        preOrder(&(*t)->Llink);
        preOrder(&(*t)->Rlink);
    }
}

void posOrder(TreeNo **t)
{
    if (*t == NULL)
        return;
    else
    {
        posOrder(&(*t)->Llink);
        posOrder(&(*t)->Rlink);
        printf("%d ", (*t)->info);
    }
}

bool searchABB(TreeNo **t, int value)
{
    if (*t == NULL)
    {
        printf("\nValor %d nao encontrado\n", value);
        return;
    }

    if ((*t)->info == value)
    {
        printf("\nValor %d encontrado\n", value);
        return true;
    }
    if (value < (*t)->info)
        searchABB(&((*t)->Llink), value);

    if (value > (*t)->info)
        searchABB(&((*t)->Rlink), value);
}

int numberNos(TreeNo **t)
{
    if (*t == NULL)
        return 0;
    else
        return 1 + numberNos(&(*t)->Llink) + numberNos(&(*t)->Rlink);
}

void nosFolha(TreeNo **t)
{
    if (*t == NULL) return;
    if (!(hasSonL(t)) && !(hasSonR(t)))
    {
        printf("%d ", (*t)->info);
    }
    else
    {
        nosFolha(&(*t)->Llink);
        nosFolha(&(*t)->Rlink);
    }
}

void erase(TreeNo **t)
{
    if (*t == NULL) return;
    erase(&(*t)->Llink);
    erase(&(*t)->Rlink);
    free(*t);
    *t = NULL;
}

int lowerRecursive(TreeNo **t)
{
    if ((*t)->Llink == NULL)
        return (*t)->info;
    lowerRecursive(&(*t)->Llink);
}

int lowerIterative(TreeNo **t)
{
    TreeNo *aux = *t;
    while ((aux)->Llink != NULL)
        aux = (aux)->Llink;
    return (aux)->info;
}

int biggerRecursive(TreeNo **t)
{
    if ((*t)->Rlink == NULL)
        return (*t)->info;
    biggerRecursive(&(*t)->Rlink);
}

int biggerIterative(TreeNo **t)
{
    TreeNo *aux = *t;
    while ((aux)->Rlink != NULL)
        aux = (aux)->Rlink;
    return (aux)->info;
}

void gotoxy(int x, int y)
{
    COORD coord;
    HANDLE handle;
    handle = GetStdHandle(STD_OUTPUT_HANDLE);
    coord.X = x;
    coord.Y = y;
    SetConsoleCursorPosition(handle, coord);
}

void exibir_arvore(TreeNo **t, int col, int lin, int desloc)
{
    if ((*t) == NULL)
        return;

    gotoxy(col, lin);

    printf("(%d)\n", (*t)->info);

    if ((*t)->Llink != NULL)
        exibir_arvore(&(*t)->Llink, col - desloc, lin + 2, desloc / 2 + 1);
    if ((*t)->Rlink != NULL)
        exibir_arvore(&(*t)->Rlink, col + desloc, lin + 2, desloc / 2 + 1);
}

int maior(int a, int b)
{
    if (a > b)
        return a;
    else
        return b;
}

int alturaRecursive(TreeNo **t)
{
    if ((*t == NULL) || ((*t)->Llink == NULL && (*t)->Rlink == NULL))
        return 0;
    else
        return 1 + maior(alturaRecursive(&(*t)->Llink), alturaRecursive(&(*t)->Rlink));
}

int main()
{
    TreeNo *t = NULL;

    //2a
    addABB(&t, 26);
    addABB(&t, 30);
    addABB(&t, 14);
    addABB(&t, 23);
    addABB(&t, 36);
    addABB(&t, 8);
    addABB(&t, 18);
    addABB(&t, 35);

    //2b
    searchABB(&t, 26);

    //2c
    searchABB(&t, 18);

    //2d
    printf("\nEm-Ordem:\n");
    order(&t);
    printf("\nPre-Ordem:\n");
    preOrder(&t);
    printf("\nPos-Ordem:\n");
    posOrder(&t);

    //2e
    removeABB(&t, 23);

    //2f
    searchABB(&t, 23);

    //2g
    removeABB(&t, 8);

    //2h
    removeABB(&t, 26);

    //2i
    order(&t);

    //3a
    printf("\nNumero de nos: %d\n", numberNos(&t));

    //3b
    printf("Nos folha: ");
    nosFolha(&t);

    //3c
    //erase(&t);

    //4a
    printf("\nMenores:\nIterativo: %d\nRecursivo: %d\n", lowerIterative(&t), lowerRecursive(&t));

    //4b
    printf("\nMaiores:\nIterativo: %d\nRecursivo: %d\n", biggerIterative(&t), biggerRecursive(&t));

    //4c
    printf("Altura: %d", alturaRecursive(&t));


    return (EXIT_SUCCESS);
}

