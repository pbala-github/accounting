package plb.accounting.services;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dto.AccountDTO;
import plb.accounting.dto.BaseAccountDTO;

import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 3:56 PM
 */
public interface IAccountService {

    List<BaseAccountDTO> getAccounts();

    AccountDTO loadAccountById(long accountId);

    AccountDTO saveAccount(AccountDTO account);

    void deleteAccount(long accountId);

    List<BaseAccountDTO> searchAccounts(AccountSearchCriteria criteria);

}
