package plb.accounting.services.impl.mapping.domain;

import plb.accounting.common.transformation.ITransformationService;
import plb.accounting.dao.AccountDAO;
import plb.accounting.dto.AccountTypeEnum;
import plb.accounting.dto.BaseAccountDTO;
import plb.accounting.dto.BaseAccountInfoDTO;
import plb.accounting.model.AbstractAccount;
import plb.accounting.model.Account;
import plb.accounting.model.AccountComposite;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author: pbala
 */
@ApplicationScoped
public class AccountFactory {

    @Inject
    private ITransformationService transformationService;

    @EJB
    private AccountDAO dao;

    public AbstractAccount toDomainObject(BaseAccountInfoDTO accountDTO) {
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

        return accountDTO.isTransactional() ?//
                transformationService.transform(accountDTO, Account.class) ://
                transformationService.transform(accountDTO, AccountComposite.class);
    }
}
