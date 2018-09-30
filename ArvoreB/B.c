#include <stdio.h>
#include <stdlib.h>

#define MAX 4
#define MIN 2

typedef struct tagTreeNoB
{
    int k[MAX + 1], count;
    struct tagTreeNoB *link[MAX + 1];
}TreeNoB;

TreeNoB *raiz;

TreeNoB * criarNo(int k, TreeNoB *filho)
{
    TreeNoB *novo;
    novo = (TreeNoB *) malloc(sizeof (TreeNoB));
    novo->k[1] = k;
    novo->count = 1;
    novo->link[0] = raiz;
    novo->link[1] = filho;
    return novo;
}

void adicionarValorNo(int k, int pos, TreeNoB *node,
        TreeNoB *filho)
{
    int j = node->count;
    while (j > pos)
    {
        node->k[j + 1] = node->k[j];
        node->link[j + 1] = node->link[j];
        j--;
    }
    node->k[j + 1] = k;
    node->link[j + 1] = filho;
    node->count++;
}

void splitNo(int k, int *pk, int pos, TreeNoB *node, TreeNoB *filho, TreeNoB **novo)
{
    int meio, j;

    if (pos > MIN)
        meio = MIN + 1;
    else
        meio = MIN;

    *novo = (TreeNoB *) malloc(sizeof (TreeNoB));
    j = meio + 1;
    while (j <= MAX)
    {
        (*novo)->k[j - meio] = node->k[j];
        (*novo)->link[j - meio] = node->link[j];
        j++;
    }
    node->count = meio;
    (*novo)->count = MAX - meio;

    if (pos <= MIN)
    {
        adicionarValorNo(k, pos, node, filho);
    }
    else
    {
        adicionarValorNo(k, pos - meio, *novo, filho);
    }
    *pk = node->k[node->count];
    (*novo)->link[0] = node->link[node->count];
    node->count--;
}

int definirValor(int k, int *pk, TreeNoB *node, TreeNoB **filho)
{

    int pos;
    if (!node)
    {
        *pk = k;
        *filho = NULL;
        return 1;
    }

    if (k < node->k[1])
    {
        pos = 0;
    }
    else
    {
        for (pos = node->count;
                (k < node->k[pos] && pos > 1); pos--);
        if (k == node->k[pos])
        {
            printf("Duplicates not allowed\n");
            return 0;
        }
    }
    if (definirValor(k, pk, node->link[pos], filho))
    {
        if (node->count < MAX)
        {
            adicionarValorNo(*pk, pos, node, *filho);
        }
        else
        {
            splitNo(*pk, pk, pos, node, *filho, filho);
            return 1;
        }
    }
    return 0;
}

void inserir(int k)
{
    int flag, i;
    TreeNoB *filho;

    flag = definirValor(k, &i, raiz, &filho);
    if (flag)
        raiz = criarNo(i, filho);
}

void copiarSucessor(TreeNoB *NodeB, int pos)
{
    TreeNoB *dummy;
    dummy = NodeB->link[pos];

    for (; dummy->link[0] != NULL;)
        dummy = dummy->link[0];
    NodeB->k[pos] = dummy->k[1];

}

void removerValor(TreeNoB *NodeB, int pos)
{
    int i = pos + 1;
    while (i <= NodeB->count)
    {
        NodeB->k[i - 1] = NodeB->k[i];
        NodeB->link[i - 1] = NodeB->link[i];
        i++;
    }
    NodeB->count--;
}

/* shifts kue from parent to right filho */
void moverDireita(TreeNoB *NodeB, int pos)
{
    TreeNoB *x = NodeB->link[pos];
    int j = x->count;

    while (j > 0)
    {
        x->k[j + 1] = x->k[j];
        x->link[j + 1] = x->link[j];
    }
    x->k[1] = NodeB->k[pos];
    x->link[1] = x->link[0];
    x->count++;

    x = NodeB->link[pos - 1];
    NodeB->k[pos] = x->k[x->count];
    NodeB->link[pos] = x->link[x->count];
    x->count--;
    return;
}

/* shifts kue from parent to left filho */
void moverEsquerda(TreeNoB *NodeB, int pos)
{
    int j = 1;
    TreeNoB *x = NodeB->link[pos - 1];

    x->count++;
    x->k[x->count] = NodeB->k[pos];
    x->link[x->count] = NodeB->link[pos]->link[0];

    x = NodeB->link[pos];
    NodeB->k[pos] = x->k[1];
    x->link[0] = x->link[1];
    x->count--;

    while (j <= x->count)
    {
        x->k[j] = x->k[j + 1];
        x->link[j] = x->link[j + 1];
        j++;
    }
    return;
}

/* merge nodes */
void ordenar(TreeNoB *NodeB, int pos)
{
    int j = 1;
    TreeNoB *x1 = NodeB->link[pos], *x2 = NodeB->link[pos - 1];

    x2->count++;
    x2->k[x2->count] = NodeB->k[pos];
    x2->link[x2->count] = NodeB->link[0];

    while (j <= x1->count)
    {
        x2->count++;
        x2->k[x2->count] = x1->k[j];
        x2->link[x2->count] = x1->link[j];
        j++;
    }

    j = pos;
    while (j < NodeB->count)
    {
        NodeB->k[j] = NodeB->k[j + 1];
        NodeB->link[j] = NodeB->link[j + 1];
        j++;
    }
    NodeB->count--;
    free(x1);
}

/* adjusts the given node */
void adjustNode(TreeNoB *NodeB, int pos)
{
    if (!pos)
    {
        if (NodeB->link[1]->count > MIN)
        {
            moverEsquerda(NodeB, 1);
        }
        else
        {
            ordenar(NodeB, 1);
        }
    }
    else
    {
        if (NodeB->count != pos)
        {
            if (NodeB->link[pos - 1]->count > MIN)
            {
                moverDireita(NodeB, pos);
            }
            else
            {
                if (NodeB->link[pos + 1]->count > MIN)
                {
                    moverEsquerda(NodeB, pos + 1);
                }
                else
                {
                    ordenar(NodeB, pos);
                }
            }
        }
        else
        {
            if (NodeB->link[pos - 1]->count > MIN)
                moverDireita(NodeB, pos);
            else
                ordenar(NodeB, pos);
        }
    }
}

/* delete k from the node */
int delValFromNode(int k, TreeNoB *NodeB)
{
    int pos, flag = 0;
    if (NodeB)
    {
        if (k < NodeB->k[1])
        {
            pos = 0;
            flag = 0;
        }
        else
        {
            for (pos = NodeB->count;
                    (k < NodeB->k[pos] && pos > 1); pos--);
            if (k == NodeB->k[pos])
            {
                flag = 1;
            }
            else
            {
                flag = 0;
            }
        }
        if (flag)
        {
            if (NodeB->link[pos - 1])
            {
                copiarSucessor(NodeB, pos);
                flag = delValFromNode(NodeB->k[pos], NodeB->link[pos]);
                if (flag == 0)
                {
                    printf("Valor %d nao encontrado!\n",k);
                }
            }
            else
            {
                removerValor(NodeB, pos);
            }
        }
        else
        {
            flag = delValFromNode(k, NodeB->link[pos]);
        }
        if (NodeB->link[pos])
        {
            if (NodeB->link[pos]->count < MIN)
                adjustNode(NodeB, pos);
        }
    }
    return flag;
}

/* delete k from B-tree */
void deletion(int k, TreeNoB *NodeB)
{
    TreeNoB *tmp;
    if (!delValFromNode(k, NodeB))
    {
        printf("Given kue is not present in B-Tree\n");
        return;
    }
    else
    {
        if (NodeB->count == 0)
        {
            tmp = NodeB;
            NodeB = NodeB->link[0];
            free(tmp);
        }
    }
    raiz = NodeB;
    return;
}

/* search k in B-Tree */
void buscar(int k, int *pos, TreeNoB *NodeB)
{
    if (!NodeB)
    {
        return;
    }

    if (k < NodeB->k[1])
    {
        *pos = 0;
    }
    else
    {
        for (*pos = NodeB->count;
                (k < NodeB->k[*pos] && *pos > 1); (*pos)--);
        if (k == NodeB->k[*pos])
        {
            printf("Given data %d is present in B-Tree", k);
            return;
        }
    }
    buscar(k, pos, NodeB->link[*pos]);
    return;
}

void traversal(TreeNoB *NodeB)
{
    int i;
    if (NodeB)
    {
        for (i = 0; i < NodeB->count; i++)
        {
            traversal(NodeB->link[i]);
            printf("%d ", NodeB->k[i + 1]);
        }
        traversal(NodeB->link[i]);
    }
}

int main()
{
    int k, ch;
    while (1)
    {

        switch (ch)
        {
            case 1:
                printf("Enter your input:");
                scanf("%d", &k);
                inserir(k);
                break;
            case 2:
                printf("Enter the element to delete:");
                scanf("%d", &k);
                deletion(k, raiz);
                break;
            case 3:
                printf("Enter the element to search:");
                scanf("%d", &k);
                buscar(k, &ch, raiz);
                break;
            case 4:
                traversal(raiz);
                break;
            case 5:
                exit(0);
            default:
                printf("U have entered wrong option!!\n");
                break;
        }
        printf("\n");
    }
}