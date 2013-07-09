package plb.accounting.web.viewmodels;

import plb.accounting.dto.BaseAccountInfoDTO;
import plb.accounting.dto.DetailedAccountDTO;
import plb.accounting.web.jsf.ViewScoped;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

@Named("accountWM")
@ViewScoped
public class AccountWM extends WebModelSupport {

    private DetailedAccountDTO accountDto;

    public AccountWM() {
        accountDto = new DetailedAccountDTO();
        accountDto.setParentAccount(new BaseAccountInfoDTO());
    }

    @Produces
    @Named("account")
    public DetailedAccountDTO getAccountDto() {
        return accountDto;
    }

    public void setAccountDto(DetailedAccountDTO accountDto) {
        this.accountDto = accountDto;
    }
}
