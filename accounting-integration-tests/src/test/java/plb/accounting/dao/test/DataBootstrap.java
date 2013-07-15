package plb.accounting.dao.test;

import org.junit.Assert;
import plb.accounting.dao.ExternalPartyDAO;
import plb.accounting.model.Account;
import plb.accounting.model.AccountTypeEnum;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.Transaction;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * User: pbala
 * Date: 11/1/12 10:39 PM
 */
public class DataBootstrap {

    public static final int MAX_ACCOUNTS = 5;

    public static final int MAX_EXTERNAL_PARTIES = 5;

    public static final int MAX_TRANSACTIONS = 25;

    private static Random amountGenerator = new Random(50);

    @Inject
    @Transactional
    private ExternalPartyDAO db;

    private static Boolean initialized = false;

    @PostConstruct
    synchronized void bootstrap() {
        System.out.println("\n\n::::::::::::BOOTSTRAPPING DATA::::::::::::::::::\n\n");
        if (initialized) {
            System.out.println("\n\n:::::::::::::BOOTSTRAP ALREADY COMPLETED::::::::::::::::\n\n");
            return;
        }
        Assert.assertNotNull(db);

        //clean up
        cleanup();

        //Initialize external parties
        List<ExternalParty> externalParties = new ArrayList<ExternalParty>();
        for (int i = 0; i < MAX_EXTERNAL_PARTIES; i++) {
            externalParties.add(db.saveOrUpdate(createParty(i)));
        }

        //Initialize accounts
        for (int i = 0; i < MAX_ACCOUNTS; i++) {
            Account account = db.saveOrUpdate(createAccount(i));
            for (int t = 1; t <= MAX_TRANSACTIONS; t++) {
                createTransaction(t, account, externalParties.get(new Random(externalParties.size()).nextInt()));
            }
        }

        initialized = true;
    }

    /**
     * @param o
     * @return
     */
    public ExternalParty createParty(int o) {
        ExternalParty party = new ExternalParty("org_name_" + o);
        party.setDescription("org_description_" + o);
        party.setVat("00000000" + o);

        return party;
    }

    /**
     * @param a
     * @return
     */
    public Account createAccount(int a) {
        Account account = new Account("Account name " + a, AccountTypeEnum.OUTCOME, BigDecimal.ZERO);
        account.setDescription("Description");

        return account;
    }


    private Transaction createTransaction(int t, Account originAccount, ExternalParty externalParty) {

        Account destinationAccount = createAccount(t + 100);
        db.saveOrUpdate(destinationAccount);
        Transaction transaction = new Transaction(originAccount, destinationAccount, new Date(), new BigDecimal(amountGenerator.nextInt(100)), "tr_description_" + t);
        transaction.setRelatedParty(externalParty);
        db.saveOrUpdate(transaction);

        return transaction;
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("\n\n::::::::::CLEAN UP DATA::::::::::::\n\n");
        Assert.assertNotNull(db);
        deleteAccounts();
        deleteExternalParties();
    }

    private void deleteExternalParties() {
        List<ExternalParty> externalParties = db.getAll(ExternalParty.class);
        if (externalParties != null) {
            for (ExternalParty externalParty : externalParties) {
                db.delete(ExternalParty.class, externalParty.getId());
            }
        }
    }

    private void deleteAccounts() {
        List<Account> accounts = db.getAll(Account.class);
        if (accounts != null) {
            for (Account account : accounts) {
                db.delete(Account.class, account.getId());
            }
        }

    }


}
