package plb.accounting.dao.test;

import org.junit.Assert;
import plb.accounting.dao.AccountDAO;
import plb.accounting.dao.EntityDAO;
import plb.accounting.dao.ExternalPartyDAO;
import plb.accounting.model.Account;
import plb.accounting.model.AccountTypeEnum;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.Transaction;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * User: pbala
 * Date: 11/1/12 10:39 PM
 */
public class DataBootstrap {

    public static final int MAX_TRANSACTIONS = 5;

    private static Random amountGenerator = new Random(50);

    @Inject
    @Transactional
    private ExternalPartyDAO db;

    private static Boolean initialized = false;

    @PostConstruct
    synchronized void bootstrap() {
        System.out.println("\n\n::::::::::::BOOTSTRAPPING DATA::::::::::::::::::\n\n");
        if (initialized) {
            return;
        }
        Assert.assertNotNull(db);
        cleanup();
        for (int t = 1; t <= MAX_TRANSACTIONS; t++) {
            createTransaction(t);
        }

        initialized = true;
    }

    public ExternalParty createParty(int o) {
        ExternalParty party = new ExternalParty();
        party.setDescription("org_description_" + o);
        party.setName("org_name_" + o);
        party.setVat("00000000" + o);

        return party;
    }

    public Account createAccount(int a) {
        Account account = new Account();
        account.setCurrentBalance(BigDecimal.ZERO);
        account.setDescription("Description");
        account.setInitialBalance(BigDecimal.ONE);
        account.setName("Account name " + a);
        account.setType(AccountTypeEnum.OUTCOME);

        return account;
    }


    private Transaction createTransaction(int t) {

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
