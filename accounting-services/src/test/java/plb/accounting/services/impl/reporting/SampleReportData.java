package plb.accounting.services.impl.reporting;

import plb.accounting.model.AccountTypeEnum;
import plb.accounting.model.view.TransactionView;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author: pbala
 */
public class SampleReportData {
    private static List<TransactionView> transactionViewList;

    public static List<TransactionView> getSampleTransactionViews() {
        if (transactionViewList != null) {
            return transactionViewList;
        }

        transactionViewList = new ArrayList<TransactionView>();
        Properties properties = new Properties();
        try {
            properties.load(SampleReportData.class.getClassLoader().getResourceAsStream("transaction_views.properties"));
            String[] ids = getPropertyValueList(properties, "id");
            String[] executionDates = getPropertyValueList(properties, "executionDate");
            String[] originAccountNames = getPropertyValueList(properties, "originAccountName");
            String[] originAccountDbIds = getPropertyValueList(properties, "originAccountDbId");
            String[] destinationAccountNames = getPropertyValueList(properties, "destinationAccountName");
            String[] destinationAccountDbIds = getPropertyValueList(properties, "destinationAccountDbId");
            String[] amounts = getPropertyValueList(properties, "amount");
            String[] descriptions = getPropertyValueList(properties, "description");
            String[] relatedPartyNames = getPropertyValueList(properties, "relatedPartyName");
            String[] externalPartyDbIds = getPropertyValueList(properties, "externalPartyDbId");
            String[] originAccountTypes = getPropertyValueList(properties, "originAccountType");
            String[] destinationAccountTypes = getPropertyValueList(properties, "destinationAccountType");


            for (int i = 0; i < ids.length; i++) {
                transactionViewList.add(//
                        new TransactionView(//
                                Long.parseLong(ids[i]),//
                                getDate(executionDates[i]),
                                originAccountNames[i],
                                Long.parseLong(originAccountDbIds[i]),
                                destinationAccountNames[i],
                                Long.parseLong(destinationAccountDbIds[i]),
                                new BigDecimal(amounts[i]),
                                descriptions[i],
                                relatedPartyNames[i],
                                Long.parseLong(externalPartyDbIds[i]),
                                AccountTypeEnum.valueOf(originAccountTypes[i]),
                                AccountTypeEnum.valueOf(destinationAccountTypes[i])
                        ));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return transactionViewList;
    }

    private static Date getDate(String executionDate) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(executionDate);
    }

    private static String[] getPropertyValueList(Properties properties, String propertyName) {
        return properties.getProperty(propertyName).split(",");
    }

}
