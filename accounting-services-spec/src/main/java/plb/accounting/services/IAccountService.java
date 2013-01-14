package plb.accounting.services;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dto.DetailedAccountDTO;
import plb.accounting.dto.AccountDTO;

import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 3:56 PM
 */
public interface IAccountService {

    List<AccountDTO> getAccounts();

    DetailedAccountDTO loadAccountById(long accountId);

    DetailedAccountDTO saveAccount(DetailedAccountDTO account);

    void deleteAccount(long accountId);

    List<AccountDTO> searchAccounts(AccountSearchCriteria criteria);

}
