package plb.accounting.dao.test;

import org.junit.Assert;
import plb.accounting.dao.AccountDAO;
import plb.accounting.dao.ExternalPartyDAO;
import plb.accounting.dao.TransactionDAO;
import plb.accounting.model.Account;
import plb.accounting.model.AccountTypeEnum;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.Transaction;
import plb.accounting.model.view.AccountView;
import plb.accounting.model.view.ExternalPartyView;

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
    private ExternalPartyDAO externalPartyDAO;

    @Inject
    @Transactional
    private AccountDAO accountDAO;

    @Inject
    @Transactional
    private TransactionDAO transactionDAO;


    private static Boolean initialized = false;

//    @PostConstruct
    synchronized void bootstrap() {
        System.out.println("\n\n::::::::::::BOOTSTRAPPING DATA::::::::::::::::::\n\n");
        if (initialized) {
            System.out.println("\n\n:::::::::::::BOOTSTRAP ALREADY COMPLETED::::::::::::::::\n\n");
            return;
        }
        Assert.assertNotNull(externalPartyDAO);
        Assert.assertNotNull(accountDAO);

        //clean up
        cleanup();

        //Initialize external parties
        List<ExternalParty> externalParties = new ArrayList<ExternalParty>();
        for (int i = 0; i < MAX_EXTERNAL_PARTIES; i++) {
            externalParties.add(externalPartyDAO.saveOrUpdate(createParty(i)));
        }

        //Initialize accounts
        for (int i = 0; i < MAX_ACCOUNTS; i++) {
            Account account = accountDAO.saveOrUpdate(createAccount(i));
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
        accountDAO.saveOrUpdate(destinationAccount);
        Transaction transaction = new Transaction(originAccount, destinationAccount, new Date(), new BigDecimal(amountGenerator.nextInt(100)), "tr_description_" + t);
        transaction.setRelatedParty(externalParty);
        transactionDAO.saveOrUpdate(transaction);

        return transaction;
    }

//    @PreDestroy
    public void cleanup() {
        System.out.println("\n\n::::::::::CLEAN UP DATA::::::::::::\n\n");
        deleteAccounts();
        deleteExternalParties();
    }

    private void deleteExternalParties() {
        List<ExternalPartyView> externalParties = externalPartyDAO.getAll();
        if (externalParties != null) {
            for (ExternalPartyView externalParty : externalParties) {
                externalPartyDAO.delete(ExternalParty.class, externalParty.getDbId());
            }
        }
    }

    private void deleteAccounts() {
        List<AccountView> accounts = accountDAO.getAll();
        if (accounts != null) {
            for (AccountView account : accounts) {
                accountDAO.delete(Account.class, account.getDbId());
            }
        }
    }
}
