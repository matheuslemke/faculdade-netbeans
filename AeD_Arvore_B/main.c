
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>


const int
T = 2,
        MAX_CHAVES = 2 * T - 1, //Quantidade máxima de chaves
        MAX_FILHOS = 2 * T, //Quantidade máxima de filhos
        MIN_OCUP = T - 1; //Ocupação mínima em cada nó

typedef struct no_arvoreB arvoreB;

struct no_arvoreB
{
    int num_chaves; //Quantidades de chaves contida no nó
    int chaves[MAX_CHAVES]; //Chaves armazenadas no nó
    arvoreB *filhos[MAX_FILHOS]; //Ponteiro para os filhos
};

int busca_binaria(arvoreB *no, int info)
{
    int meio, i, f;
    i = 0;
    f = no->num_chaves - 1;

    while (i <= f)
    {
        meio = (i + f) / 2;
        if (no->chaves[meio] == info)
            return (meio); //Encontrou. Retorna a posíção em que a chave está.
        else if (no->chaves[meio] > info)
            f = meio - 1;
        else
            i = meio + 1;
    }
    return (i); //Não encontrou. Retorna a posição do ponteiro para o filho.
}



//Insere uma chave e o ponteiro para o filho da direita em um nó

void insere_chave(arvoreB *raiz, int info, arvoreB *filhodir)
{
    int k, pos;

    //busca para obter a posição ideal para inserir a nova chave
    pos = busca_binaria(raiz, info);
    k = raiz->num_chaves;

    //realiza o remanejamento para manter as chaves ordenadas
    while (k > pos && info < raiz->chaves[k - 1])
    {

        raiz->chaves[k] = raiz->chaves[k - 1];
        raiz->filhos[k + 1] = raiz->filhos[k];
        k--;
    }
    //insere a chave na posição ideal
    raiz->chaves[pos] = info;
    raiz->filhos[pos + 1] = filhodir;
    raiz->num_chaves++;
}

//Realiza a busca do nó para inserir a chave e faz as subdivisões quando necessárias

arvoreB *insere(arvoreB *raiz, int info, bool *h, int *info_retorno)
{
    int i, j, pos,
            info_mediano; //auxiliar para armazenar a chave que irá subir para o pai
    arvoreB *temp, *filho_dir; //ponteiro para o filho à direita da chave 

    if (raiz == NULL)
    {
        //O nó anterior é o ideal para inserir a nova chave (chegou em um nó folha)
        *h = true;
        *info_retorno = info;
        return (NULL);
    }
    else
    {
        pos = busca_binaria(raiz, info);
        if (raiz->num_chaves > pos && raiz->chaves[pos] == info)
        {
            printf("Chave já contida na Árvore");
            *h = false;
        }
        else
        {
            //desce na árvore até encontrar o nó folha para inserir a chave.
            filho_dir = insere(raiz->filhos[pos], info, h, info_retorno);
            if (*h) //Se true deve inserir a info_retorno no nó.
            {
                if (raiz->num_chaves < MAX_CHAVES) //Tem espaço na página
                {
                    insere_chave(raiz, *info_retorno, filho_dir);
                    *h = false;
                }
                else
                { //Overflow. Precisa subdividir
                    temp = (arvoreB *) malloc(sizeof (arvoreB));
                    temp->num_chaves = 0;

                    //inicializa filhos com NULL
                    for (i = 0; i < MAX_FILHOS; i++)
                        temp->filhos[i] = NULL;

                    //elemento mediano que vai subir para o pai
                    info_mediano = raiz->chaves[MIN_OCUP];

                    //insere metade do nó raiz no temp (efetua subdivisão)
                    temp->filhos[0] = raiz->filhos[MIN_OCUP + 1];
                    for (i = MIN_OCUP + 1; i < MAX_CHAVES; i++)
                        insere_chave(temp, raiz->chaves[i], raiz->filhos[i + 1]);

                    //atualiza nó raiz. 
                    for (i = MIN_OCUP; i < MAX_CHAVES; i++)
                    {
                        raiz->chaves[i] = 0;
                        raiz->filhos[i + 1] = NULL;
                    }
                    raiz->num_chaves = MIN_OCUP;

                    //Verifica em qual nó será inserida a nova chave
                    if (pos <= MIN_OCUP)
                        insere_chave(raiz, *info_retorno, filho_dir);
                    else insere_chave(temp, *info_retorno, filho_dir);

                    //retorna o mediano para inserí-lo no nó pai e o temp como filho direito do mediano.
                    *info_retorno = info_mediano;

                    return (temp);
                }
            }
        }
    }
}

arvoreB *insere_arvoreB(arvoreB *raiz, int info)
{
    bool h;
    int info_retorno, i;
    arvoreB *filho_dir, *nova_raiz;

    filho_dir = insere(raiz, info, &h, &info_retorno);
    if (h)
    { //Aumetará a altura da árvore
        nova_raiz = (arvoreB *) malloc(sizeof (arvoreB));
        nova_raiz->num_chaves = 1;
        nova_raiz->chaves[0] = info_retorno;
        nova_raiz->filhos[0] = raiz;
        nova_raiz->filhos[1] = filho_dir;
        for (i = 2; i <= MAX_CHAVES; i++)
            nova_raiz->filhos[i] = NULL;
        return (nova_raiz);
    }
    else return (raiz);
}

void main()
{
    
}