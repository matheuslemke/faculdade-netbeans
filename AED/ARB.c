/* 
 * File:   ARB.c
 * Author: Matheus
 *
 * Created on 4 de Setembro de 2014, 09:02
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <windows.h>

enum Color
{
    BLACK, RED
} Cor;

typedef struct NoTagRB
{
    struct NoTagRB *father;
    struct NoTagRB *LLink;
    struct NoTagRB *RLink;
    enum Color color;
    int info;
} TreeNoRB;

void rotationRight(TreeNoRB **t)
{
    TreeNoRB *pivo = (*t)->LLink;
    TreeNoRB *fatherT = (*t)->father;
    (*t)->LLink = pivo->RLink;
    pivo->RLink = *t;
    (*t)->father = pivo;
    pivo->father = fatherT;
    *t = pivo;
    if ((*t)->RLink->LLink != NULL)
        (*t)->RLink->LLink->father = (*t)->RLink;
}

void rotationLeft(TreeNoRB **t)
{
    TreeNoRB *pivo = (*t)->RLink, *fatherT = (*t)->father;
    (*t)->RLink = pivo->LLink;
    pivo->LLink = *t;
    (*t)->father = pivo;
    pivo->father = fatherT;
    *t = pivo;
    if ((*t)->LLink->RLink != NULL)
        (*t)->LLink->RLink->father = (*t)->LLink;
}

void alterRed(TreeNoRB **t)
{
    (*t)->color = RED;
}

void alterBlack(TreeNoRB **t)
{
    (*t)->color = BLACK;
}

bool isSonRightRed(TreeNoRB **t)
{
    if ((*t)->RLink != NULL)
        if ((*t)->RLink->color == RED)
            return TRUE;
    return FALSE;
}

bool isSonLeftRed(TreeNoRB **t)
{
    if ((*t)->LLink != NULL)
        if ((*t)->LLink->color == RED)
            return TRUE;
    return FALSE;
}

void simpleLeft(TreeNoRB **t)
{
    rotationLeft(&(*t));
    alterBlack(&(*t));
    alterRed(&(*t)->LLink);
}

void simpleRight(TreeNoRB **t)
{
    rotationRight(&(*t));
    alterBlack(&(*t));
    alterRed(&(*t)->RLink);
}

void alterColors(TreeNoRB **t)
{
    if ((*t)->father != NULL)
        alterRed(&*t);
    alterBlack(&(*t)->LLink);
    alterBlack(&(*t)->RLink);
}

void balancear(TreeNoRB **t)
{
    if (isSonRightRed(&*t))
    {
        if (isSonRightRed(&(*t)->RLink))
        {
            if (isSonLeftRed(&*t))//se tio é vermelho
                alterColors(&*t);
            else//se tio é preto
                simpleLeft(&*t);
        }
        if (isSonLeftRed(&(*t)->RLink))
        {
            if (isSonLeftRed(&*t))
                alterColors(&*t);
            else
            {
                rotationRight(&(*t)->RLink);
                rotationLeft(&(*t));
                alterBlack(&(*t));
                alterRed(&(*t)->LLink);
            }
        }
    }
    if (isSonLeftRed(&*t))
    {
        if (isSonRightRed(&(*t)->LLink))
        {
            if (isSonRightRed(&*t))
                alterColors(&*t);
            else
            {
                rotationLeft(&(*t)->LLink);
                rotationRight(&*t);
                alterBlack(&(*t));
                alterRed(&(*t)->RLink);
            }
        }
        if (isSonLeftRed(&(*t)->LLink))
        {
            if (isSonRightRed(&*t)) //se tio é vermelho
                alterColors(&*t);
            else //se tio é preto
                simpleRight(&*t);
        }
    }
}

void addARBRed(TreeNoRB **t, int info, TreeNoRB **father)
{
    if (*t != NULL)
    {
        if (info < (*t)->info)
        {
            addARBRed(&((*t)->LLink), info, &*t);
        }
        else if (info > (*t)->info)
        {
            addARBRed(&((*t)->RLink), info, &*t);
        }
    }
    else
    {
        if ((*t = malloc(sizeof (TreeNoRB))) != NULL)
        {
            (*t)->info = info;
            (*t)->LLink = NULL;
            (*t)->RLink = NULL;
            (*t)->color = RED;
            (*t)->father = *father;
        }
    }
    if (*t != NULL)
        balancear(&*t);
}

void addARB(TreeNoRB **t, int info)
{
    if (*t == NULL)
    {
        if ((*t = malloc(sizeof (TreeNoRB))) != NULL)
        {
            (*t)->info = info;
            (*t)->LLink = NULL;
            (*t)->RLink = NULL;
            (*t)->color = BLACK;
            (*t)->father = NULL;
        }
    }
    else
        addARBRed(&*t, info, NULL);
}

//void gotoxy(int x, int y)
//{
//    COORD coord;
//    HANDLE handle;
//    handle = GetStdHandle(STD_OUTPUT_HANDLE);
//    coord.X = x;
//    coord.Y = y;
//    SetConsoleCursorPosition(handle, coord);
//}

void exibir_arvore(TreeNoRB **t, int col, int lin, int desloc)
{
    if ((*t) == NULL)
        return;

    //gotoxy(col, lin);

    if ((*t)->father != NULL)
        printf("%d(%d)%d\n", (*t)->color, (*t)->info, ((*t)->father)->info);
    else
        printf("%d(%d)NULL\n", (*t)->color, (*t)->info);

    if ((*t)->LLink != NULL)
        exibir_arvore(&(*t)->LLink, col - desloc, lin + 2, desloc / 2 + 1);
    if ((*t)->RLink != NULL)
        exibir_arvore(&(*t)->RLink, col + desloc, lin + 2, desloc / 2 + 1);
}

void imprimir_arvore(TreeNoRB **t)
{
    if ((*t) == NULL) printf("  NULL  ");
    else
    {
        printf("%d %d  ", (*t)->info, (*t)->color);
        imprimir_arvore(&(*t)->LLink);
        imprimir_arvore(&(*t)->RLink);
    }
}

bool searchARB(TreeNoRB **t, int info)
{
    if (*t == NULL)
    {
        printf("\nValor %d nao encontrado\n", info);
        return;
    }

    if ((*t)->info == info)
    {
        printf("\nValor %d encontrado\n", info);
        return true;
    }
    if (info < (*t)->info)
        searchARB(&((*t)->LLink), info);

    if (info > (*t)->info)
        searchARB(&((*t)->RLink), info);
}

void order(TreeNoRB **t)
{
    if (*t == NULL) return;

    else
    {
        order(&(*t)->LLink);
        printf("%d ", (*t)->info);
        order(&(*t)->RLink);
    }
}

void preOrder(TreeNoRB **t)
{
    if (*t == NULL) return;

    else
    {
        printf("%d ", (*t)->info);
        preOrder(&(*t)->LLink);
        preOrder(&(*t)->RLink);
    }
}

void preOrderComNull(TreeNoRB **t)
{
    if (*t == NULL)
    {
        printf("NULL ");
        return;
    }

    else
    {
        if ((*t)->father != NULL)
            printf("(P%d %d %d)", (*t)->father->info, (*t)->info, (*t)->color);
        else
            printf("(PNull %d %d)", (*t)->info, (*t)->color);

        preOrderComNull(&(*t)->LLink);
        preOrderComNull(&(*t)->RLink);
    }
}

void posOrder(TreeNoRB **t)
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

bool hasSonL(TreeNoRB **t)
{
    if ((*t)->LLink != NULL) return true;
    else return false;
}

bool hasSonR(TreeNoRB **t)
{
    if ((*t)->RLink != NULL) return true;
    else return false;
}

void removeOneNull(TreeNoRB **t)
{
    TreeNoRB *aux = *t;
    if (hasSonL(&*t))
    {
        *t = (*t)->LLink;
    }
    else if (hasSonR(&*t))
    {
        *t = (*t)->RLink;
    }
    free(aux);
    (*t)->color = BLACK;
}

bool hasSonNull(TreeNoRB **t)
{
    if ((*t)->LLink == NULL || (*t)->RLink == NULL)
        return TRUE;
    return FALSE;
}

enum Color getColor(TreeNoRB *t)
{
    if (t == NULL)
        return BLACK;
    else
        return t->color;
}

TreeNoRB* brother_(TreeNoRB **t)
{
    TreeNoRB *brother;

    if ((*t) == (*t)->father->LLink)
        brother = (*t)->father->RLink;
    else
        brother = (*t)->father->LLink;
    return brother;
}

/* Criar função que retorna o sobrinho mais próximo de N */
TreeNoRB * siblingNext_(TreeNoRB **t)
{
    TreeNoRB *brother, *sibling;
    brother = brother_(&*t);

    if ((*t) == (*t)->father->LLink)
        sibling = brother->LLink;
    else
        sibling = brother->RLink;

    return sibling;
}

void changeColors(TreeNoRB **t1, TreeNoRB **t2)
{
    enum Color corAux = (*t1)->color;
    (*t1)->color = (*t2)->color;
    (*t2)->color = corAux;
}

void caseSix(TreeNoRB **t)
{
    printf("\n--Caso 6--\n");

    TreeNoRB *brother, *siblingAway, *siblingNext;
    brother = brother_(&*t);
    siblingNext = siblingNext_(&*t);
    siblingAway = brother_(&siblingNext);
    if (brother->color == BLACK && siblingAway->color == RED)
    {
        changeColors(&(*t)->father, &brother);
        siblingAway->color = BLACK;
        if ((*t) == (*t)->father->LLink)
            rotationLeft(&(*t)->father);
        else
            rotationRight(&(*t)->father);
    }

}

void caseFive(TreeNoRB **t)
{
    printf("\n--Caso 5--\n");

    TreeNoRB *brother, *siblingNext, *siblingAway;
    brother = brother_(&*t);
    siblingNext = siblingNext_(&*t);
    siblingAway = brother_(&siblingNext);
    if ((getColor(brother) == BLACK) && (getColor(siblingNext) == RED) && (getColor(siblingAway) == BLACK))
    {
        brother->color = RED;
        siblingNext->color = BLACK;
    }
    //    if ((*t) == (*t)->father->LLink)
    //        rotationRight(&brother);
    //    else
    //        rotationLeft(&brother);


    if ((*t) == (*t)->father->LLink)
    {
        rotationRight(&(*t)->father->RLink);
    }
    else
        rotationLeft(&(*t)->father->LLink);


    caseSix(&*t);

}

void caseFour(TreeNoRB **t)
{
    printf("\n--Caso 4--\n");

    TreeNoRB *brother = brother_(&*t);
    if ((getColor((*t)->father) == RED) && (getColor(brother) == BLACK) && (getColor(brother->LLink) == BLACK) && (getColor(brother->RLink) == BLACK))
    {
        brother->color = RED;
        (*t)->father->color = BLACK;
    }

    else caseFive(&*t);
}

void caseThree(TreeNoRB **t)
{
    printf("\n--Caso 3--\n");

    TreeNoRB *brother = brother_(&*t);

    if (((*t)->father->color == BLACK) && (brother->color == BLACK) && (getColor(brother->LLink) == BLACK) && (getColor(brother->RLink) == BLACK))
    {
        brother->color = RED;
        caseOne(&((*t)->father));
    }
    else caseFour(&*t);
}

void caseTwo(TreeNoRB **t)
{
    printf("\n--Caso 2--\n");
    TreeNoRB *brother = brother_(&*t);

    if (getColor(brother) == RED)
    {
        (*t)->father->color = RED;
        brother->color = BLACK;

        if ((*t) == (*t)->father->LLink)
            rotationLeft(&(*t)->father);
        else rotationRight(&(*t)->father);
    }
    caseThree(&*t);
}

void caseOne(TreeNoRB **t)
{
    printf("\n--Caso 1--\n");

    if ((*t)->father == NULL)
        return;
    else caseTwo(&*t);
}

//TreeNoRB *minimo(TreeNoRB *x)
//{
//    while (x->LLink != NULL)
//    {
//        x = x->LLink;
//
//    }
//    return x;
//}
//
//TreeNoRB *sucessor(TreeNoRB *x)
//{
//    TreeNoRB *y;
//    if (x->RLink != NULL)
//    {
//        return
//        minimo(x->RLink);
//
//    }
//    y = x->father;
//    while ((y != NULL) && (x == y->RLink))
//    {
//        x = y;
//        y = y->father;
//
//    }
//    return y;
//}
//
//void del(TreeNoRB **t, int info)
//{
//    TreeNoRB *x, *nor = NULL;
//    x = (*t);
//    while (x != NULL && x-> info != info)
//    {
//        if (info < x->info)
//        {
//            x = x->LLink;
//        }
//        else x = x->RLink;
//
//    }
//    if (x == NULL)
//    {
//        printf("Elemento nao existe na arvore");
//    }
//    else
//    {
//        nor = retira(t, x);
//        free(&nor);
//
//    }
//}
//
//TreeNoRB * retira(TreeNoRB *t, TreeNoRB *z)
//{
//    TreeNoRB *y, *x;
//    if ((z)->LLink == NULL || (z)->RLink == NULL)
//        y = z;
//    else y = sucessor(z);
//
//    if (y->LLink != NULL)
//
//        x = y->LLink;
//    else x = y->RLink;
//    if (x != NULL)
//
//        x->father = y->father;
//
//    if (y->father == NULL)
//
//        t = x;
//    else if (y = y->father->LLink)
//        y->father->LLink = x;
//    else y->father->RLink = x;
//
//    if (y != z)
//    {
//        (z)->info = y->info;
//
//    }
//
//    return y;
//}
//
//void replace_(TreeNoRB **t, TreeNoRB *newn)
//{
//
//    if ((*t) == (*t)->father->LLink)
//        (*t)->father->LLink = newn;
//    else
//        (*t)->father->RLink = newn;
//
//    if (newn != NULL)
//    {
//        newn->father = (*t)->father;
//    }
//}

void removeARB(TreeNoRB **t, int info)
{
    //    TreeNoRB *child;
    //
    //    if (*t == NULL) return; /* Key not found, do nothing */
    //    if ((*t)->LLink != NULL && (*t)->RLink != NULL)
    //    {
    //        /* Copy key/info from predecessor and then delete it instead */
    //        TreeNoRB *sucessor;
    //        sucessor = (*t)->RLink;
    //
    //        while (sucessor->LLink != NULL)
    //        {
    //            sucessor = sucessor->LLink;
    //        }
    //        (*t)->info = sucessor->info;
    //        *t = sucessor;
    //    }
    //
    //    if ((*t)->RLink == NULL)
    //        child = (*t)->LLink;
    //    else
    //        child = (*t)->RLink;
    //
    //    if (getColor(*t) == BLACK)
    //    {
    //        (*t)->color = getColor(child);
    //        caseOne(&*t);
    //    }
    //    replace_(&*t, &child);
    //    if ((*t)->father == NULL && child != NULL) // root should be black
    //        child->color = BLACK;
    //    free(*t);

    if (*t == NULL) return;

    else if (info < (*t)->info)
        removeARB(&(*t)->LLink, info);

    else if (info > (*t)->info)
        removeARB(&(*t)->RLink, info);

    else
    {
        TreeNoRB *aux, *fatherT;

        if (hasSonL(&*t) && hasSonR(&*t))
        {
            TreeNoRB *sucessor;
            sucessor = (*t)->RLink;

            while (sucessor->LLink != NULL)
            {
                sucessor = sucessor->LLink;
            }
            (*t)->info = sucessor->info;
            removeARB(&(*t)->RLink, sucessor->info);
        }

        else
        {
            if ((*t)->color == BLACK)
            {
                caseOne(&*t);
            }

            if ((hasSonL(t)) && !(hasSonR(t)))
            {
                aux = *t;
                fatherT = aux->father;
                *t = (*t)->LLink;
                (*t)->father = fatherT;
                if ((*t)->father != NULL)
                    (*t)->father->LLink = *t;
                free(aux);
            }
            else if (!(hasSonL(t)) && hasSonR(t))
            {
                aux = *t;
                fatherT = aux->father;
                *t = (*t)->RLink;
                (*t)->father = fatherT;
                if ((*t)->father != NULL)
                    (*t)->father->RLink = *t;
                free(aux);
            }
            else if (!(hasSonL(t)) && !(hasSonR(t)))
            {
                if (*t == (*t)->father->LLink)
                    (*t)->father->LLink = NULL;
                else (*t)->father->RLink = NULL;

                free(*t);
                *t = NULL;
            }
        }


    }
}

int main()
{
    TreeNoRB *t = NULL;

    addARB(&t, 1);
    addARB(&t, 2);
    addARB(&t, 9);
    addARB(&t, 10);
    addARB(&t, 8);
    addARB(&t, 7);
    addARB(&t, 6);
    addARB(&t, 3);
    addARB(&t, 5);
    addARB(&t, 4);

    puts("\nPre ordem: ");
    preOrderComNull(&t);
    puts("\n\n");

    puts("\nEm ordem: ");
    order(&t);
    puts("\n\n");


    removeARB(&t, 7);

    puts("\n7 - Pre ordem: ");
    preOrderComNull(&t);
    puts("\n\n");

    puts("\nEm ordem: ");
    order(&t);
    puts("\n\n");

    removeARB(&t, 8);
    //    removeARB(&t, 10);
    //    removeARB(&t, 3);
    //    removeARB(&t, 2);
    //    removeARB(&t, 1);

    puts("\n 8 - Pre ordem: ");
    preOrderComNull(&t);
    puts("\n\n");

    puts("\nEm ordem: ");
    order(&t);
    puts("\n\n");


    return (EXIT_SUCCESS);
}

