/* 
 * File:   B.c
 * Author: Matheus
 *
 * Created on November 6, 2014, 8:25 AM
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#define MINIMUM_DEGREE 3

typedef struct NoTagB
{
    int n;
    int key[2 * MINIMUM_DEGREE - 1];
    bool leaf;
    struct NoTagB *c[2 * MINIMUM_DEGREE];
} TreeNoB;

bool searchB(TreeNoB **t, int value)
{
    int i;
    for (i = 0; i < (*t)->n && value > (*t)->key[i]; i++)
    {
        ;
    }

    if (i <= (*t)->n && value == (*t)->key[i])
        return true;
    else if ((*t)->leaf)
        return false;
    else return searchB(&(*t)->c[i], value);
}

void createB(TreeNoB **t)
{
    TreeNoB *x = malloc(sizeof (TreeNoB));
    x->leaf = true;
    x->n = 0;
    *t = x;
}

void splitChildB(TreeNoB **x, int i)
{
    int j;
    TreeNoB *z = malloc(sizeof (TreeNoB)), *y;
    y = (*x)->c[i];
    z->leaf = y->leaf;
    z->n = MINIMUM_DEGREE - 1;

    for (j = 1; j <= MINIMUM_DEGREE - 1; j++)
        z->key[j] = y->key[j + MINIMUM_DEGREE];

    if (!y->leaf)
        for (j = 1; j <= MINIMUM_DEGREE; j++)
            z->c[j] = y->c[j + MINIMUM_DEGREE];

    y->n = MINIMUM_DEGREE - 1;

    for (j = ((*x)->n + 1); j >= i + 1; j--)
        (*x)->c[j + 1] = (*x)->c[j];

    (*x)->c[i + 1] = z;

    for (j = (*x)->n; j >= i; j--)
        (*x)->key[j + 1] = (*x)->key[j];

    (*x)->key[i] = y->key[MINIMUM_DEGREE];
    (*x)->n = (*x)->n + 1;
}

void insertNonFull(TreeNoB **x, int k)
{
    int i = (*x)->n;
    if ((*x)->leaf)
    {
        while (i >= 1 && k < (*x)->key[i])
        {
            (*x)->key[i + 1] = (*x)->key[i];
            i = i - 1;
        }
        (*x)->key[i + 1] = k;
        (*x)->n = (*x)->n + 1;
    }
    else
    {
        while (i >= 1 && k < (*x)->key[i])
            i = i - 1;
        i = i + 1;
        if ((*x)->c[i]->n == 2 * MINIMUM_DEGREE - 1)
        {
            splitChildB(&*x, i);
            if (k > (*x)->key[i])
                i = i + 1;
        }
        insertNonFull(&(*x)->c[i], k);
    }
}

void insert(TreeNoB **T, int k)
{
    TreeNoB *r = *T;

    if (r->n == 2 * MINIMUM_DEGREE - 1)
    {
        TreeNoB *s = malloc(sizeof (TreeNoB));
        *T = s;
        s->leaf = false;
        s->n = 0;
        s->c[1] = r;
        splitChildB(&s, 1);
        insertNonFull(&T, k);
    }
}

void showB(TreeNoB **T)
{
    int i, j;
    for (i = 0; i < (*T)->n; i++)
    {
        printf("%d ", (*T)->key[i]);
        if ((*T)->c[i] != NULL)
            showB(&(*T)->c[i]);
    }
}

int main()
{
    TreeNoB *t;
    insert(&t, 10);
    showB(&t);

    return (EXIT_SUCCESS);
}

