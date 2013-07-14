package com.plb.accounting.model;

import plb.accounting.model.Account;
import plb.accounting.model.AccountTypeEnum;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.Transaction;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * @author pbala
 */
public class SampleFactory {

    /**
     * @return
     */
    public static Account getOriginAccount() {
        return new Account("origin account 1", AccountTypeEnum.STORAGE, new BigDecimal(2000));
    }

    /**
     * @return
     */
    public static Account getDestinationAccount() {
        return new Account("destination account 1", AccountTypeEnum.OUTCOME, new BigDecimal(0));
    }

    public static ExternalParty getExternalParty() {
        return new ExternalParty("external party 1");
    }

    public static Transaction getTransaction() {
        return getTransaction(getOriginAccount(), getDestinationAccount());
    }

    public static Transaction getTransaction(Account originAccount, Account destinationAccount) {
        return new Transaction(originAccount, destinationAccount, Calendar.getInstance().getTime(), new BigDecimal(340), "sample transaction");
    }
}
