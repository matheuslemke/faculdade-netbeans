#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#define N_LINHAS 8
#define N_COLUNAS 9

bool transmitir(char matriz[N_LINHAS][N_COLUNAS])
{
    int i, j, um;
    for (j = 0; j < (N_COLUNAS - 1); j++)
    {
        um = 0;
        for (i = 0; i < (N_LINHAS); i++)
        {
            if (matriz[i][j] == '1')
                um++;
        }
        if (um % 2 != 0) return false;
    }

    for (i = 0; i < (N_LINHAS - 1); i++)
    {
        um = 0;
        for (j = 0; j < (N_COLUNAS); j++)
        {
            if (matriz[i][j] == '1')
                um++;
        }
        if (um % 2 != 0) return false;
    }

    return true;
}

int main()
{
    /**
     * Palavra é: M A T H E U S
     */

    char matriz[N_LINHAS][N_COLUNAS];

    /*M*/
    matriz[0][0] = '0';
    matriz[0][1] = '1';
    matriz[0][2] = '0';
    matriz[0][3] = '0';
    matriz[0][4] = '1';
    matriz[0][5] = '1';
    matriz[0][6] = '0';
    matriz[0][7] = '1';
    /*A*/
    matriz[1][0] = '0';
    matriz[1][1] = '1';
    matriz[1][2] = '0';
    matriz[1][3] = '0';
    matriz[1][4] = '0';
    matriz[1][5] = '0';
    matriz[1][6] = '0';
    matriz[1][7] = '1';
    /*T*/
    matriz[2][0] = '0';
    matriz[2][1] = '1';
    matriz[2][2] = '0';
    matriz[2][3] = '1';
    matriz[2][4] = '0';
    matriz[2][5] = '1';
    matriz[2][6] = '0';
    matriz[2][7] = '0';
    /*H*/
    matriz[3][0] = '0';
    matriz[3][1] = '1';
    matriz[3][2] = '0';
    matriz[3][3] = '0';
    matriz[3][4] = '1';
    matriz[3][5] = '0';
    matriz[3][6] = '0';
    matriz[3][7] = '0';
    /*E*/
    matriz[4][0] = '0';
    matriz[4][1] = '1';
    matriz[4][2] = '0';
    matriz[4][3] = '0';
    matriz[4][4] = '0';
    matriz[4][5] = '1';
    matriz[4][6] = '0';
    matriz[4][7] = '1';
    /*U*/
    matriz[5][0] = '0';
    matriz[5][1] = '1';
    matriz[5][2] = '0';
    matriz[5][3] = '0';
    matriz[5][4] = '1';
    matriz[5][5] = '1';
    matriz[5][6] = '0';
    matriz[5][7] = '1';
    /*S*/
    matriz[6][0] = '0';
    matriz[6][1] = '1';
    matriz[6][2] = '0';
    matriz[6][3] = '1';
    matriz[6][4] = '0';
    matriz[6][5] = '0';
    matriz[6][6] = '1';
    matriz[6][7] = '1';

    int i, j, um = 0;

    for (i = 0; i < (N_LINHAS - 1); i++)
    {
        um = 0;
        for (j = 0; j < (N_COLUNAS - 1); j++)
        {
            if (matriz[i][j] == '1')
                um++;
        }
        if (um % 2 != 0)
            matriz[i][N_COLUNAS - 1] = '1';
        else matriz[i][N_COLUNAS - 1] = '0';
    }
    for (j = 0; j < (N_COLUNAS - 1); j++)
    {
        um = 0;
        for (i = 0; i < (N_LINHAS - 1); i++)
        {
            if (matriz[i][j] == '1')
                um++;
        }
        if (um % 2 != 0)
            matriz[N_LINHAS - 1][j] = '1';
        else matriz[N_LINHAS - 1][j] = '0';
    }

    for (i = 0; i < N_LINHAS; i++)
    {
        for (j = 0; j < N_COLUNAS; j++)
        {
            printf("%c ", matriz[i][j]);
        }
        printf("\n");
    }

    printf("Foi transmitido com sucesso? \nR: %d", transmitir(matriz));


    return (EXIT_SUCCESS);
}

