package plb.accounting.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plb.accounting.dto.AccountDTO;
import plb.accounting.dto.BaseAccountDTO;
import plb.accounting.mvc.model.AccountWM;
import plb.accounting.services.IAccountingService;

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
    public List<BaseAccountDTO> getAllAccounts(){
        return accountingService.getAccounts();
    }

    /**
     *
     * @return
     */
    public AccountWM initNewAccountWM(){
        AccountWM accountWM = new AccountWM();
        accountWM.setRefAccount(new AccountDTO());

        return accountWM;
    }
}
