package plb.accounting.services;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dto.AccountDTO;
import plb.accounting.dto.BaseAccountInfoDTO;
import plb.accounting.dto.DetailedAccountDTO;

import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 3:56 PM
 */
public interface AccountService {

    List<AccountDTO> getAccountsTree();

    DetailedAccountDTO loadAccountById(long accountId);

    BaseAccountInfoDTO saveAccount(BaseAccountInfoDTO account);

    void deleteAccount(long accountId);

    List<BaseAccountInfoDTO> searchAccounts(AccountSearchCriteria criteria);

}
