package plb.accounting.services.impl;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.common.utils.Assert;
import plb.accounting.dao.AccountDAO;
import plb.accounting.dto.*;
import plb.accounting.model.AbstractAccount;
import plb.accounting.model.Account;
import plb.accounting.model.view.AccountView;
import plb.accounting.services.AccountService;
import plb.accounting.services.impl.intercept.Validate;
import plb.accounting.services.impl.mapping.domain.AccountFactory;
import plb.accounting.services.impl.mapping.dto.AccountDtoFactory;
import plb.accounting.services.impl.validation.AccountCreation;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 4:08 PM
 */
public class AccountServiceImpl implements AccountService {

    @EJB
    private AccountDAO dao;

    @Inject
    private AccountDtoFactory dtoFactory;

    @Inject
    private AccountFactory domainFactory;

    /**
     *
     * @param accountId
     * @return
     */
    @Override
    public DetailedAccountDTO loadAccountById(long accountId) {
        Assert.isTrue(accountId > 0);
        Account account = dao.findById(Account.class, accountId);
        return account != null ? dtoFactory.toDetailedDto(account) : null;
    }

    /**
     *
     * @param accountDTO
     * @return
     */
    @Validate(groups = {AccountCreation.class})
    @Override
    public BaseAccountInfoDTO saveAccount(BaseAccountInfoDTO accountDTO) {
        Assert.notNull(accountDTO);
        Account account = domainFactory.toDomainObject(accountDTO);
        account = dao.saveOrUpdate(account);
        return dtoFactory.toBaseDto(account);
    }

    /**
     *
     * @param accountId
     */
    @Override
    public void deleteAccount(long accountId) {
        Assert.isTrue(accountId > 0);
        dao.delete(AbstractAccount.class, accountId);
    }

    /**
     *
     * @param criteria
     * @return
     */
    @Override
    public List<BaseAccountInfoDTO> searchAccounts(AccountSearchCriteria criteria) {
        Assert.notNull(criteria);
        List<BaseAccountInfoDTO> accountInfoDTOList = new ArrayList<BaseAccountInfoDTO>();
        List<AccountView> accountViewList = dao.searchAccounts(criteria);

        for (AccountView accountView : accountViewList) {
            accountInfoDTOList.add(dtoFactory.toBaseDto(accountView));
        }

        return accountInfoDTOList;
    }

    /**
     *
     * @return
     */
    @Override
    public List<AccountDTO> getAccountsTree() {
        AccountSearchCriteria criteria = new AccountSearchCriteria();
        criteria.setTopParentAccount(true);
        List<AccountDTO> accountDTOList = new ArrayList<AccountDTO>();
        List<AccountView> accountViewList = dao.searchAccounts(criteria);

        for (AccountView accountView : accountViewList) {
            accountDTOList.add(dtoFactory.toDto(accountView));
        }

        return accountDTOList;
    }
}
