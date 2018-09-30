/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.bancoa22;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Lucas
 */
public class Main
{

    private static SessionFactory sessionFactory;

    public static void main(String[] args)
    {

    }

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

}
