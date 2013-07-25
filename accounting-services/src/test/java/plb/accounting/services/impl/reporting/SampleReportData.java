package plb.accounting.services.impl.reporting;

import net.sf.json.*;
import net.sf.json.util.NewBeanInstanceStrategy;
import org.apache.commons.io.IOUtils;
import plb.accounting.model.AccountTypeEnum;
import plb.accounting.model.view.TransactionView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: pbala
 */
public class SampleReportData {
    /**
     *
     */
    private static List<TransactionView> transactionViewList;

    /**
     * @return
     */
    public static synchronized List<TransactionView> getSampleTransactionViews() {
        if (transactionViewList == null) {
            transactionViewList = loadJson();
        }

        return transactionViewList;
    }

    private static List<TransactionView> loadJson() {
        List<TransactionView> transactionViewList = new ArrayList<TransactionView>();
        try {

            InputStream resourceAsStream = SampleReportData.class.getClassLoader().getResourceAsStream("transactions_view.json");
            String jsonString = IOUtils.toString(resourceAsStream);
            JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(jsonString);
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.setRootClass(TransactionView.class);
            jsonConfig.setNewBeanInstanceStrategy(new NewBeanInstanceStrategy() {
                @Override
                public Object newInstance(Class aClass, JSONObject jsonObject) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, InvocationTargetException {

                    try {
                        return new TransactionView(//
                                jsonObject.getLong("dbId"),//
                                getDate(jsonObject.getString("executionDate")),
                                jsonObject.getString("originAccountName"),
                                jsonObject.getLong("originAccountDbId"),
                                jsonObject.getString("destinationAccountName"),
                                jsonObject.getLong("destinationAccountDbId"),
                                new BigDecimal(jsonObject.getString("amount")),
                                jsonObject.getString("description"),
                                jsonObject.getString("relatedPartyName"),
                                jsonObject.getLong("externalPartyDbId"),
                                AccountTypeEnum.valueOf(jsonObject.getString("originAccountType")),
                                AccountTypeEnum.valueOf(jsonObject.getString("destinationAccountType"))
                        );
                    } catch (ParseException e) {
                        throw new InstantiationException();
                    }
                }
            });


            for (Object o : jsonArray) {
                TransactionView transactionView = (TransactionView) JSONSerializer.toJava((JSON) o, jsonConfig);
                transactionViewList.add(transactionView);
            }

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return transactionViewList;
    }

    private static Date getDate(String executionDate) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(executionDate);
    }
}
