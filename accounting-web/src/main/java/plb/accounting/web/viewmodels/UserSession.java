package plb.accounting.web.viewmodels;

import plb.accounting.dto.TransactionDTO;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * User: pbala
 * Date: 4/13/13 10:34 PM
 */
@Named
@SessionScoped
public class UserSession implements Serializable {

    private TransactionDTO transactionTemplate;

    public TransactionDTO getTransactionTemplate() {
        if (transactionTemplate == null) {
            transactionTemplate = new TransactionDTO();
        }

        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionDTO transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
}
