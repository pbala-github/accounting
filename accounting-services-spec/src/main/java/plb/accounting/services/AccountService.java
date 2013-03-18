package plb.accounting.services;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dto.AccountDTO;
import plb.accounting.dto.BaseAccountDTO;
import plb.accounting.dto.DetailedAccountDTO;

import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 3:56 PM
 */
public interface AccountService {

    List<BaseAccountDTO> getAccounts();

    DetailedAccountDTO loadAccountById(long accountId);

    BaseAccountDTO saveAccount(BaseAccountDTO account);

    void deleteAccount(long accountId);

    List<BaseAccountDTO> searchAccounts(AccountSearchCriteria criteria);

}
