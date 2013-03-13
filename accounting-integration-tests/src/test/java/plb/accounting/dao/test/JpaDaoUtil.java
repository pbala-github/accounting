package plb.accounting.dao.test;

import plb.accounting.dao.impl.jpa.JPAEntityDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * User: pbala
 * Date: 3/13/13 10:21 AM
 */
public class JpaDaoUtil {

    public static <T> T advanceDao(final T dao, Class<T> i) {

        final EntityManager entityManager = Persistence.createEntityManagerFactory("testAccountingPU").createEntityManager();

        ((JPAEntityDao) dao).setEm(entityManager);

        return (T) Proxy.newProxyInstance(dao.getClass().getClassLoader(), new Class[]{i}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                EntityTransaction transaction = entityManager.getTransaction();
                transaction.begin();
                try {
                    return method.invoke(dao, args);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                } finally {
                    if (transaction.isActive())
                        if (transaction.getRollbackOnly()) {
                            transaction.rollback();
                        } else {
                            transaction.commit();
                        }
                }

            }
        });

    }


}
