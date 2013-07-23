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

import javax.ejb.EJB;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.*;

/**
 * User: pbala
 * Date: 11/1/12 10:39 PM
 */
public class DataBootstrap {

    public static final int MAX_ACCOUNTS = 10;

    public static final int MAX_EXTERNAL_PARTIES = 5;

    public static final int MAX_TRANSACTIONS = 25;

    private static Random amountGenerator = new Random(50);

    @EJB
    private ExternalPartyDAO externalPartyDAO;

    @EJB
    private AccountDAO accountDAO;

    @EJB
    private TransactionDAO transactionDAO;

    //    @PostConstruct
    public synchronized void bootstrap() {
        System.out.println("\n\n::::::::::::BOOTSTRAPPING DATA::::::::::::::::::\n\n");

        Assert.assertNotNull(externalPartyDAO);
        Assert.assertNotNull(accountDAO);
        Assert.assertNotNull(transactionDAO);

        //clean up
        cleanup();

        //Initialize external parties
        List<ExternalParty> externalParties = new ArrayList<ExternalParty>();
        for (int i = 0; i < MAX_EXTERNAL_PARTIES; i++) {
            externalParties.add(externalPartyDAO.saveOrUpdate(createParty(i)));
        }

        //Initialize accounts
        Map<Boolean, List<Account>> accountsPerDestination = new HashMap<Boolean, List<Account>>();
        accountsPerDestination.put(Boolean.FALSE, new ArrayList<Account>());
        accountsPerDestination.put(Boolean.TRUE, new ArrayList<Account>());
        for (int i = 0; i < MAX_ACCOUNTS; i++) {
            boolean isOriginAccount = i % 2 == 1;
            Account account = !isOriginAccount ? createDestinationAccount(i) : createOriginAccount(i);
            account = accountDAO.saveOrUpdate(account);
            accountsPerDestination.get(isOriginAccount).add(account);
        }

        for (int t = 1; t <= MAX_TRANSACTIONS; t++) {
            Account originAccount = accountsPerDestination.get(Boolean.TRUE).get(new Random().nextInt(accountsPerDestination.get(Boolean.TRUE).size()));
            Account destinationAccount = accountsPerDestination.get(Boolean.FALSE).get(new Random().nextInt(accountsPerDestination.get(Boolean.FALSE).size()));
            createTransaction(t, originAccount, destinationAccount, externalParties.get(new Random().nextInt(externalParties.size())));
        }

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
    public Account createOriginAccount(int a) {
        Account account = new Account("Account name " + a, AccountTypeEnum.STORAGE, new BigDecimal(50000));
        account.setDescription("Description");

        return account;
    }

    /**
     * @param a
     * @return
     */
    public Account createDestinationAccount(int a) {
        Account account = new Account("Account name " + a, AccountTypeEnum.OUTCOME, BigDecimal.ZERO);
        account.setDescription("Description");

        return account;
    }


    private Transaction createTransaction(int t, Account originAccount, Account destinationAccount, ExternalParty externalParty) {

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
