package plb.accounting.dao.test;

import com.db4o.ObjectContainer;
import plb.accounting.model.Account;
import plb.accounting.model.AccountTypeEnum;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.Transaction;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

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

            createTransaction(t);
        }
    }

    private static ExternalParty createParty(int o) {
        ExternalParty party = new ExternalParty();
        party.setDescription("org_description_" + o);
        party.setName("org_name_" + o);
        party.setVat("00000000"+o);

        return party;
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

        ExternalParty party = createParty(t);
        Account originAccount = createAccount(t);
        Account destinationAccount = createAccount(t+100);

        db.store(originAccount);
        originAccount.setId(db.ext().getID(originAccount));
        System.out.println(originAccount);
        db.store(destinationAccount);
        destinationAccount.setId(db.ext().getID(destinationAccount));
        System.out.println(destinationAccount);
        db.store(party);
        party.setId(db.ext().getID(party));
        System.out.println(party);

        db.store(originAccount);
        db.store(destinationAccount);
        db.store(party);

        Transaction transaction = new Transaction();
        transaction.setAmount(new BigDecimal(amountGenerator.nextInt(100)));
        transaction.setDescription("tr_description_" + t);
        transaction.setExecutionDate(new Date());

        transaction.setRelatedParty(party);
        transaction.setOriginAccount(originAccount);
        transaction.setDestinationAccount(destinationAccount);

        db.store(transaction);

        originAccount.setOutTransactions(Arrays.asList(transaction));
        destinationAccount.setInTransactions(Arrays.asList(transaction));
        party.setTransactions(Arrays.asList(transaction));

        db.store(originAccount);
        db.store(destinationAccount);
        db.store(party);

        return transaction;
    }
}
