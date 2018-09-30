/* 
 * File:   main.cpp
 * Author: matheus
 *
 * Created on 15 de Agosto de 2015, 12:04
 */

#include <cstdlib>
#include <iostream>
#include <stdlib.h>
#include <stdio.h>


using namespace std;

unsigned long numero[6] = {0};

int lerGrau()
{
    int grau;



    return grau;
}

int shift(int value, int casas)
{
    return 1 << casas;
}

void fibonacci()
{
    cout << "Digite 0 para parar a leitura" << endl << endl;
    
    int x;
    unsigned long mask;
    cin >> x;
    while (x != 0)
    {
        if (x > 319)
        {
            //manipular [5]
            x = x - (64 * 5);
            mask = 1 << x;
            numero[5] = numero[5] + mask;
        }
        else if (x > 255)
        {
            //manipular [4]
            x = x - (64 * 4);
            mask = 1 << x;
            numero[4] = numero[4] + mask;
        }
        else if (x > 191)
        {
            //manipular [3]
            x = x - (64 * 3);
            mask = 1 << x;
            numero[3] = numero[3] + mask;
        }
        else if (x > 127)
        {
            //manipular [2]
            x = x - (64 * 2);
            mask = 1 << x;
            numero[2] = numero[2] + mask;
        }
        else if (x > 63)
        {
            x = x - 64;
            //manipular [1]
            mask = 1 << x;
            numero[1] = numero[1] + mask;
        }
        else
        {
            //manipular [0]
            mask = 1 << x;
            numero[0] = numero[0] + mask;
        }
        cin >> x;
    }
}

void galois()
{

}

int main(int argc, char** argv)
{
    int x, tipo;



    cout << "Programa que retorna números aleatórios" << endl <<
            "a partir de um polinômio gerador" << endl << endl;
    cout << endl << endl << "POLINÔMIO" << endl;
    cout << "Digite em ordem decrescente os expoentes" << endl <<
            "do polinômio." << endl << endl;
    
    cout << "Digite 1 para Fibonnacci ou" << endl;
    cout << "Digite 2 para Galois" << endl << endl;

    cin >> tipo;



    if (tipo == 1)
        fibonacci();
    else if (tipo == 2)
        galois();





    cin >> x;



    //0 a 63
    //64 a 127
    //128 a 191
    //192 a 255
    //256 a 319
    //320 a 383




    return EXIT_SUCCESS;
}

