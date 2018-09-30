/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aula25;

import org.hibernate.*;
import org.hibernate.cfg.*;

/**
 *
 * @author matheus
 */
public class Main
{

    private static SessionFactory sessionFactory;

    public static void setup()
    {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static void close()
    {
        sessionFactory.close();
    }

    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public static void main(String[] args)
    {
        
    }
}
