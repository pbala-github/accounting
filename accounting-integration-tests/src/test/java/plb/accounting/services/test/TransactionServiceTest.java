package plb.accounting.services.test;

import com.googlecode.jeeunit.JeeunitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dto.BaseAccountDTO;
import plb.accounting.dto.BaseAccountInfoDTO;
import plb.accounting.dto.BaseExternalPartyDTO;
import plb.accounting.dto.TransactionDTO;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.*;

/**
 * User: pbala
 * Date: 3/17/13 11:48 PM
 */
@RunWith(JeeunitRunner.class)
public class TransactionServiceTest extends AbstractServiceTest {

    @Test
    public void persist() {
        TransactionDTO transaction = new TransactionDTO();
        transaction.setAmount(BigDecimal.TEN);
        transaction.setDescription("transaction description");
        BaseAccountInfoDTO destinationAccount = service.searchAccounts(new AccountSearchCriteria()).get(0);
        transaction.setDestinationAccount(destinationAccount);
        transaction.setExecutionDate(new Date());
        BaseAccountInfoDTO originAccount = service.searchAccounts(new AccountSearchCriteria()).get(1);
        transaction.setOriginAccount(originAccount);
        BaseExternalPartyDTO externalParty = service.searchExternalParties(new ExternalPartySearchCriteria()).get(0);
        transaction.setRelatedParty(externalParty);

        transaction = service.saveTransaction(transaction);
        assertNotNull(transaction.getId());
    }

    @Test
    public void findById() {
        TransactionDTO transaction = service.searchTransactions(new TransactionSearchCriteria()).get(0);
        assertNotNull(transaction);
        assertEquals(transaction.getId(), service.findTransactionById(transaction.getId()).getId());
    }

    @Test
    public void delete() {
        TransactionDTO transaction = service.searchTransactions(new TransactionSearchCriteria()).get(0);
        assertNotNull(transaction);
        service.deleteTransaction(transaction.getId());
        assertNull(service.findTransactionById(transaction.getId()));
    }

    @Test
    public void update() {
        TransactionDTO transaction = service.searchTransactions(new TransactionSearchCriteria()).get(0);
        assertNotNull(transaction);
        transaction.setDescription("hhhhhhhhhhh");
        service.saveTransaction(transaction);
        transaction = service.findTransactionById(transaction.getId());
        assertEquals("hhhhhhhhhhh", transaction.getDescription());
    }

    @Test
    public void getAll() {
        assertNotNull(service.searchTransactions(new TransactionSearchCriteria()));
    }

    @Test
    public void searchByCriteria() {
        TransactionSearchCriteria criteria = new TransactionSearchCriteria();
        criteria.setDescription("tr_description_4");
        List<TransactionDTO> transactions = service.searchTransactions(criteria);
        assertEquals(1, transactions.size());
        assertEquals("tr_description_4", transactions.get(0).getDescription());

        criteria.setAmountFrom(new BigDecimal(0));
        transactions = service.searchTransactions(criteria);
        assertEquals(1, transactions.size());
        assertEquals("tr_description_4", transactions.get(0).getDescription());

        criteria.setAmountTo(new BigDecimal(9));
        transactions = service.searchTransactions(criteria);
        assertEquals(0, transactions.size());

        criteria = new TransactionSearchCriteria();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 1);
        criteria.setExecutionDateFrom(calendar.getTime());
        transactions = service.searchTransactions(criteria);
        assertFalse(transactions.isEmpty());

        calendar.set(Calendar.MONTH, 2);
        criteria.setExecutionDateTo(calendar.getTime());
        transactions = service.searchTransactions(criteria);
        assertEquals(0, transactions.size());

        criteria = new TransactionSearchCriteria();
        criteria.setOrgName("org_name_3");
        transactions = service.searchTransactions(criteria);
        assertFalse(transactions.isEmpty());
        assertEquals("org_name_3", transactions.get(0).getRelatedParty().getName());

        criteria = new TransactionSearchCriteria();
        criteria.setOriginAccountIds(new HashSet<Long>(Arrays.asList(1l, 2l, 3l)));
        assertNotNull(service.searchTransactions(criteria));
    }
}
