package plb.accounting.services;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.common.search.ExternalOrganizationSearchCriteria;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.model.Account;
import plb.accounting.model.ExternalOrganization;
import plb.accounting.model.Transaction;

import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 3:56 PM
 */
public interface IAccountService {

    List<Account> getAccounts();

    Account findAccountById(long accountId);

    Account saveAccount(Account account);

    void deleteAccount(long accountId);

    List<Account> searchAccounts(AccountSearchCriteria criteria);

}
