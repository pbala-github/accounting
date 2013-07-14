package com.plb.accounting.model.integration;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author pbala
 */
public class BaseIntegrationTest {

    protected static EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;
    protected EntityTransaction entityTransaction;

    @BeforeClass
    public static void globalSetUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("accountingTestMySqlPU");
    }

    @Before
    public void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }

    @Test
    public void tearDown() {
        entityManager.close();
    }

    @AfterClass
    public static void globalTearDown() {
        entityManagerFactory.close();
    }
}
