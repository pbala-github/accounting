package plb.accounting.dao.test;

import com.db4o.ObjectContainer;
import plb.accounting.model.Account;
import plb.accounting.model.AccountTypeEnum;
import plb.accounting.model.ExternalOrganization;
import plb.accounting.model.Transaction;

import java.math.BigDecimal;
import java.util.*;

/**
 * User: pbala
 * Date: 11/1/12 10:39 PM
 */
public abstract class DataBootstrap {
    
    public static final int MAX_TRANSACTIONS  = 5;

    private static Random amountGenerator = new Random(50);

    private static ObjectContainer db;

    static void bootstrap(ObjectContainer db){

        DataBootstrap.db = db;

        for(int t = 1 ; t <= MAX_TRANSACTIONS; t++){

            db.store(createTransaction(t));
        }
    }

    private static ExternalOrganization createOrganization(int o) {
        ExternalOrganization organization = new ExternalOrganization();
        organization.setDescription("org_description_" + o);
        organization.setName("org_name_" + o);
        organization.setVat("00000000"+o);

        return organization;
    }

    private static Account createAccount(int a) {
        Account account = new Account();
        account.setCurrentBalance(BigDecimal.ZERO);
        account.setDescription("Description");
        account.setInitialBalance(BigDecimal.ONE);
        account.setName("Account name " + a);
        account.setType(AccountTypeEnum.OUTCOME);

        return account;
    }



    private static Transaction createTransaction(int t) {

        ExternalOrganization organization = createOrganization(t);
        Account originAccount = createAccount(t);
        Account destinationAccount = createAccount(t+100);

        db.store(originAccount);
        originAccount.setId(db.ext().getID(originAccount));
        System.out.println(originAccount);
        db.store(destinationAccount);
        destinationAccount.setId(db.ext().getID(destinationAccount));
        System.out.println(destinationAccount);
        db.store(organization);
        organization.setId(db.ext().getID(organization));
        System.out.println(organization);

        db.store(originAccount);
        db.store(destinationAccount);
        db.store(organization);

        Transaction transaction = new Transaction();
        transaction.setAmount(new BigDecimal(amountGenerator.nextInt(100)));
        transaction.setDescription("tr_description_" + t);
        transaction.setExecutionDate(new Date());

        transaction.setRelatedOrganization(organization);
        transaction.setOriginAccount(originAccount);
        transaction.setDestinationAccount(destinationAccount);

        originAccount.setTransactions(Arrays.asList(transaction));
        destinationAccount.setTransactions(Arrays.asList(transaction));
        organization.setTransactions(Arrays.asList(transaction));

        db.store(originAccount);
        db.store(destinationAccount);
        db.store(organization);

        return transaction;
    }
}
