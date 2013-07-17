package plb.accounting.services.impl;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dao.AccountDAO;
import plb.accounting.dto.*;
import plb.accounting.dto.AccountTypeEnum;
import plb.accounting.model.*;
import plb.accounting.services.AccountService;
import plb.accounting.services.impl.intercept.Validate;
import plb.accounting.services.impl.validation.AccountCreation;

import javax.ejb.EJB;
import java.util.ArrayList;
import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 4:08 PM
 */
public class AccountServiceImpl extends BaseService implements AccountService {

    @EJB
    private AccountDAO dao;

    @Override
    public List<BaseAccountInfoDTO> getAccounts() {
        return transformationService.transform(dao.getAll(), BaseAccountInfoDTO.class);
    }

    @Override
    public DetailedAccountDTO loadAccountById(long accountId) {
        Account account = dao.findById(Account.class, accountId);
        DetailedAccountDTO accountDTO = null;

        if (account != null) {
            accountDTO = transformationService.transform(account, DetailedAccountDTO.class);
            accountDTO.setTransactions(new ArrayList<TransactionDTO>());
            if(account.getInTransactions()!=null)
                accountDTO.getTransactions().addAll(transformationService.transform(account.getInTransactions(), TransactionDTO.class));
            if(account.getOutTransactions()!=null)
                accountDTO.getTransactions().addAll(transformationService.transform(account.getOutTransactions(), TransactionDTO.class));
        }

        return accountDTO;
    }

    @Validate(groups = {AccountCreation.class})
    @Override
    public BaseAccountInfoDTO saveAccount(BaseAccountInfoDTO accountDTO) {
        //new account
        if (accountDTO.getId() == null) {
            accountDTO.setCurrentBalance(accountDTO.getInitialBalance());
        } else {
            accountDTO.setCurrentBalance(dao.findById(Account.class, accountDTO.getId()).getCurrentBalance());
        }

        if (accountDTO.getType() == null) {
            BaseAccountDTO baseAccountDTO = (BaseAccountDTO) accountDTO;
            plb.accounting.model.AccountTypeEnum parentAccountType = dao.findById(Account.class, baseAccountDTO.getParentAccount().getId()).getType();
            accountDTO.setType(AccountTypeEnum.valueOf(parentAccountType.name()));
        }

        Account account = dao.saveOrUpdate(transformationService.transform(accountDTO, Account.class));
        return transformationService.transform(account, BaseAccountInfoDTO.class);
    }

    @Override
    public void deleteAccount(long accountId) {
        dao.delete(Account.class, accountId);
    }

    @Override
    public List<BaseAccountInfoDTO> searchAccounts(AccountSearchCriteria criteria) {
        return transformationService.transform(dao.searchAccounts(criteria), BaseAccountInfoDTO.class);
    }

    @Override
    public List<AccountDTO> getAccountsTree() {
        AccountSearchCriteria criteria = new AccountSearchCriteria();
        criteria.setTopParentAccount(true);
        return transformationService.transform(dao.searchAccounts(criteria), AccountDTO.class);
    }
}
