package plb.accounting.services.impl.reporting;

import plb.accounting.common.transformation.ITransformationService;
import plb.accounting.dto.BaseAccountDTO;
import plb.accounting.dto.reporting.BalanceReportCriteria;
import plb.accounting.dto.reporting.BalanceReportResult;
import plb.accounting.model.Account;
import plb.accounting.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * User: pbala
 * Date: 11/10/12 11:16 PM
 */
public class AccountBalanceReportStrategy implements IReportStrategy<BalanceReportResult,BalanceReportCriteria>{

    protected ITransformationService transformationService;

    @Override
    public BalanceReportResult createReport(BalanceReportCriteria reportCriteria, Object data) {

        BalanceReportResult result = new BalanceReportResult();
        List<Transaction> transactions = (List<Transaction>) data;
      

        IGroupStrategy<Account,Transaction> groupStrategy = new TransactionAccountGroupStrategy();

        GroupContainer<Account,Transaction> groupContainer = groupStrategy.group(reportCriteria,transactions);
        
        for(Group<Account,Transaction> group : groupContainer.getGroupList()){

            BigDecimal income =BigDecimal.ZERO, outcome = BigDecimal.ZERO;
            
            for(Transaction t : group.getItems()){
                if(group.getKey() ==  t.getOriginAccount())
                    outcome = outcome.add(t.getAmount());
                else 
                    income = income.add(t.getAmount());
            }

            result.addResultEntry(income.doubleValue(),outcome.doubleValue(),transformationService.transform(group.getKey(), BaseAccountDTO.class));
        }
        
        return result;  
    }
    
}
