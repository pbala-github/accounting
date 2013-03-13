package plb.accounting.dao.test;

import org.junit.Assert;
import plb.accounting.dao.EntityDAO;
import plb.accounting.model.Account;
import plb.accounting.model.AccountTypeEnum;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.Transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

/**
 * User: pbala
 * Date: 11/1/12 10:39 PM
 */
public abstract class DataBootstrap {

    public static final int MAX_TRANSACTIONS = 5;

    private static Random amountGenerator = new Random(50);

    private static EntityDAO db;

    private static Boolean initialized = false;

    static synchronized void bootstrap(EntityDAO db) {

        if (initialized) {
            return;
        }
        Assert.assertNotNull(db);
        DataBootstrap.db = db;

        for (int t = 1; t <= MAX_TRANSACTIONS; t++) {
            createTransaction(t);
        }

        initialized = true;
    }

    public static ExternalParty createParty(int o) {
        ExternalParty party = new ExternalParty();
        party.setDescription("org_description_" + o);
        party.setName("org_name_" + o);
        party.setVat("00000000" + o);

        return party;
    }

    public static Account createAccount(int a) {
        Account account = new Account();
        account.setCurrentBalance(BigDecimal.ZERO);
        account.setDescription("Description");
        account.setInitialBalance(BigDecimal.ONE);
        account.setName("Account name " + a);
        account.setType(AccountTypeEnum.OUTCOME);

        return account;
    }


    private static Transaction createTransaction(int t) {

        ExternalParty party = createParty(t);
        Account originAccount = createAccount(t);
        Account destinationAccount = createAccount(t + 100);

        db.saveOrUpdate(originAccount);
        System.out.println(originAccount);
        db.saveOrUpdate(destinationAccount);
        System.out.println(destinationAccount);
        db.saveOrUpdate(party);
        System.out.println(party);

        Transaction transaction = new Transaction();
        transaction.setAmount(new BigDecimal(amountGenerator.nextInt(100)));
        transaction.setDescription("tr_description_" + t);
        transaction.setExecutionDate(new Date());

        transaction.setRelatedParty(party);
        transaction.setOriginAccount(originAccount);
        transaction.setDestinationAccount(destinationAccount);

        db.saveOrUpdate(transaction);

        return transaction;
    }
}
