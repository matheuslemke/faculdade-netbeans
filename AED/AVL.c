#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <windows.h>

typedef struct NoTagF
{
    int info;
    struct NoTagF *RLink;
    struct NoTagF *LLink;
    int height;
} TreeNoF;

int height(TreeNoF **t)
{
    if (*t == NULL)
        return (-1);
    return (*t)->height;
}

int fatBal(TreeNoF **t)
{
    return height(&(*t)->LLink) - height(&(*t)->RLink);
}

void rotationRight(TreeNoF **t)
{
    TreeNoF *aux = (*t)->LLink;
    (*t)->LLink = ((*t)->LLink)->RLink;
    aux->RLink = *t;
    *t = aux;
    (*t)->RLink->height = 1 + bigger(height(&((*t)->RLink)->LLink), height(&((*t)->RLink)->RLink));
    (*t)->height = 1 + bigger(height(&(*t)->LLink), height(&(*t)->RLink));
}

void rotationLeft(TreeNoF **t)
{
    TreeNoF *aux = (*t)->RLink;
    (*t)->RLink = ((*t)->RLink)->LLink;
    aux->LLink = *t;
    *t = aux;
    (*t)->LLink->height = 1 + bigger(height(&((*t)->LLink)->LLink), height(&((*t)->LLink)->RLink));
    (*t)->height = 1 + bigger(height(&(*t)->LLink), height(&(*t)->RLink));
}

bool hasSonL(TreeNoF **t)
{
    if ((*t)->LLink != NULL) return true;
    else return false;
}

bool hasSonR(TreeNoF **t)
{
    if ((*t)->RLink != NULL) return true;
    else return false;
}

int bigger(int a, int b)
{
    if (a >= b) return a;
    return b;
}

void balancear(TreeNoF **t)
{
    if (fatBal(&(*t)) == 2)
    {
        if ((fatBal(&(*t)->LLink) != -1))
        {
            rotationRight(&(*t));
        }
        else //se for -1
        {
            rotationLeft(&(*t)->LLink);
            rotationRight(&(*t));
        }
    }
    else if (fatBal(&(*t)) == -2)
    {
        if (fatBal(&(*t)->RLink) != 1)
        {
            rotationLeft(&(*t));
        }
        else //se for 1
        {
            rotationRight(&(*t)->RLink);
            rotationLeft(&(*t));
        }
    }
    (*t)->height = 1 + bigger(height(&(*t)->LLink), height(&(*t)->RLink));

}

void addAVL(TreeNoF **t, int value)
{
    if (*t == NULL)
    {
        if ((*t = malloc(sizeof (TreeNoF))) != NULL)
        {
            (*t)->info = value;
            (*t)->LLink = NULL;
            (*t)->RLink = NULL;
            (*t)->height = 0;
        }
    }
    else
    {
        if (value < (*t)->info)
        {
            addAVL(&((*t)->LLink), value);
        }
        else if (value > (*t)->info)
        {
            addAVL(&((*t)->RLink), value);
        }
    }
    balancear(&(*t));
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

void exibir_arvore(TreeNoF **t, int col, int lin, int desloc)
{
    if ((*t) == NULL)
        return;

    gotoxy(col, lin);

    printf("(%d) - %d\n", (*t)->info, (*t)->height);

    if ((*t)->LLink != NULL)
        exibir_arvore(&(*t)->LLink, col - desloc, lin + 2, desloc / 2 + 1);
    if ((*t)->RLink != NULL)
        exibir_arvore(&(*t)->RLink, col + desloc, lin + 2, desloc / 2 + 1);
}

bool searchAVL(TreeNoF **t, int value)
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
        searchAVL(&((*t)->LLink), value);

    if (value > (*t)->info)
        searchAVL(&((*t)->RLink), value);
}

void order(TreeNoF **t)
{
    if (*t == NULL) return;

    else
    {
        order(&(*t)->LLink);
        printf("%d ", (*t)->info);
        order(&(*t)->RLink);
    }
}

void preOrder(TreeNoF **t)
{
    if (*t == NULL) return;

    else
    {
        printf("%d ", (*t)->info);
        preOrder(&(*t)->LLink);
        preOrder(&(*t)->RLink);
    }
}

void posOrder(TreeNoF **t)
{
    if (*t == NULL)
        return;
    else
    {
        posOrder(&(*t)->LLink);
        posOrder(&(*t)->RLink);
        printf("%d ", (*t)->info);
    }
}

bool removeAVL(TreeNoF **t, int value)
{
    bool retorno = false;
    if (*t == NULL)
        return;

    else if (value < (*t)->info)
    {
        removeAVL(&(*t)->LLink, value);

    }
    else if (value > (*t)->info)
    {
        removeAVL(&(*t)->RLink, value);
    }
    else
    {
        TreeNoF *aux;
        if (!(hasSonL(t)) && !(hasSonR(t)))
        {
            free(*t);
            *t = NULL;
            retorno = true;
        }
        else if ((hasSonL(t)) && !(hasSonR(t)))
        {
            aux = *t;
            *t = (*t)->LLink;
            free(aux);
            aux = NULL;
            retorno = true;

        }
        else if (!(hasSonL(t)) && (hasSonR(t)))
        {
            aux = *t;
            *t = (*t)->RLink;
            free(aux);
            aux = NULL;
            retorno = true;

        }
        else
        {
            TreeNoF *sucessor;
            sucessor = (*t)->RLink;

            while (sucessor->LLink != NULL)
            {
                sucessor = sucessor->LLink;
            }
            (*t)->info = sucessor->info;
            removeAVL(&(*t)->RLink, sucessor->info);
            retorno = true;
        }
    }
    if (*t != NULL)
        balancear(t);
    return retorno;
}

int main()
{
    printf("\n\n\n\n\n\n\n\n\n\n\n\n");


    TreeNoF *t = NULL;
    /**
     * Exercício 4a
     */
    addAVL(&t, 1);
    addAVL(&t, 2);
    addAVL(&t, 9);
    addAVL(&t, 10);
    addAVL(&t, 8);
    addAVL(&t, 7);
    addAVL(&t, 6);
    addAVL(&t, 3);
    addAVL(&t, 5);
    addAVL(&t, 4);

    /**
     * Exercício 4b
     */
    searchAVL(&t, 1);

    /**
     * Exercício 4c
     */
    searchAVL(&t, 6);

    /**
     * Exercício 4d
     */
    printf("\nOrdem: ");
    order(&t);
    printf("\nPre-ordem: ");
    preOrder(&t);
    printf("\nPos-ordem: ");
    posOrder(&t);
    printf("\n");

    /**
     * Exercício 4e
     */
    removeAVL(&t, 7);
    removeAVL(&t, 8);
    removeAVL(&t, 10);
    removeAVL(&t, 3);
    removeAVL(&t, 2);
    removeAVL(&t, 1);

    if (removeAVL(&t, 50) == false)
        printf("\nValor nao encontrado\n");
    if (removeAVL(&t, 10) == false)
        printf("\nValor nao encontrado\n");
    printf("\nOrdem: ");
    order(&t);
    printf("\n");

    exibir_arvore(&t, 32, 4, 15);

    return (EXIT_SUCCESS);
}

