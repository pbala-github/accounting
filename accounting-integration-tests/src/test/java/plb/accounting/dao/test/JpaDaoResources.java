package plb.accounting.dao.test;

import plb.accounting.dao.AccountDAO;
import plb.accounting.dao.ExternalPartyDAO;
import plb.accounting.dao.TransactionDAO;
import plb.accounting.dao.impl.jpa.JPAEntityDao;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * User: pbala
 * Date: 3/13/13 10:21 AM
 */
public class JpaDaoResources {

    @Inject
    EntityManager em;

    @Inject
    UserTransaction userTransaction;

    @EJB
    private AccountDAO dao;

    @EJB
    private ExternalPartyDAO externalPartyDAO;

    @EJB
    private TransactionDAO transactionDAO;

    @Produces
    @Transactional
    public AccountDAO getTransactionalAccountDAO() {
//        System.out.println("Entity Manager: " + em);
//        System.out.println("Accounts: " + em.createQuery("from Account ").getResultList());
        return advanceDao(dao, AccountDAO.class);
    }

    @Produces
    @Transactional
    public ExternalPartyDAO getTransactionalExternalPartyDAO() {
        return advanceDao(externalPartyDAO, ExternalPartyDAO.class);
    }

    @Produces
    @Transactional
    public TransactionDAO getTransactionalTransactionDAO() {
        return advanceDao(transactionDAO, TransactionDAO.class);
    }

    public <T> T advanceDao(final T dao, Class<T> i) {

        return (T) Proxy.newProxyInstance(dao.getClass().getClassLoader(), new Class[]{i}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result;
                userTransaction.begin();
                try {
                    result =  method.invoke(dao, args);
                } catch (Exception e) {
                    e.printStackTrace();
                    userTransaction.rollback();
                    throw new RuntimeException(e);
                } 

                userTransaction.commit();
                return result;
            }
        });

    }


}
