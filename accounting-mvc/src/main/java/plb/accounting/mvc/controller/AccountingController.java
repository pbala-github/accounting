package plb.accounting.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dto.DetailedAccountDTO;
import plb.accounting.dto.AccountTypeEnum;
import plb.accounting.dto.AccountDTO;
import plb.accounting.mvc.model.AccountWM;
import plb.accounting.mvc.utils.WebUtilHelper;
import plb.accounting.services.IAccountingService;

import java.util.Arrays;
import java.util.List;

/**
 * User: pbala
 * Date: 12/18/12 10:04 PM
 */
@Service
public class AccountingController {

    @Autowired
    private IAccountingService accountingService;

    /**
     *
     * @return
     */
    public List<AccountDTO> getAllAccounts(){
        return accountingService.getAccounts();
    }

    /**
     *
     * @return
     */
    public AccountWM initNewAccountWM(){
        AccountWM accountWM = new AccountWM();
        accountWM.setRefAccount(new DetailedAccountDTO());

        return accountWM;
    }

    /**
     *
     * @return
     */
    public List<AccountTypeEnum> getAccountTypes(){
        return  Arrays.asList(AccountTypeEnum.values());
    }

    /**
     *
     * @param accountType
     * @return
     */
    public List<AccountDTO> getAccountsByType(AccountTypeEnum accountType){
        AccountSearchCriteria criteria = new AccountSearchCriteria();
        criteria.setAccountType(accountType);
        return WebUtilHelper.serializeAccounts(accountingService.searchAccounts(criteria).toArray(new AccountDTO[0]));
    }

    /**
     *
     * @param accountWM
     */
    public void createAccount(AccountWM accountWM){
        accountingService.saveAccount(accountWM.getRefAccount());
    }
}
