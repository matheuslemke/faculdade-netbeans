/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exerciciobonus;

import javax.crypto.SecretKey;

/**
 *
 * @author matheus
 */
public class ExercicioBonus
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
//        String msg = "TESTE DE DIGEST - SHA-256";
//
//        Digest digestAlgorithm = new Digest(msg);
//
//        byte[] digest = digestAlgorithm.doIt();
//
//        RSA rsaAlgorithm = new RSA(digest);
//
//        rsaAlgorithm.encrypt();
//        rsaAlgorithm.decrypt();

        System.out.println("\n\n\n\n\n\n\nDH!\n");

        DH dh = new DH();

        SecretKey[] vector = dh.doIt();

        for (SecretKey elem : vector)
        {
            System.out.println(elem);
        }

    }

}
