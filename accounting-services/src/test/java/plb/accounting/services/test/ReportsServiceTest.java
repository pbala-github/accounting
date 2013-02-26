package plb.accounting.services.test;

import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import plb.accounting.common.transformation.DozerTransformationService;
import plb.accounting.common.transformation.ITransformationService;
import plb.accounting.dto.reporting.BalanceReportCriteria;
import plb.accounting.dto.reporting.BalanceReportResult;
import plb.accounting.services.IReportService;
import plb.accounting.services.impl.ReportService;
import plb.accounting.services.impl.reporting.ReportManager;

import java.util.Arrays;

/**
 * User: pbala
 * Date: 1/16/13 2:13 PM
 */
public class ReportsServiceTest {
    
    private static IReportService reportService;

    @BeforeClass
    public static void setup(){
        reportService = new ReportService();
        ((ReportService)reportService).setReportManager(new ReportManager());
        ((ReportService)reportService).setAccountingDAOFacade(new MockAccountingDAOFacade());
        Mapper dozerBeanMapper = DozerBeanMapperSingletonWrapper.getInstance();
        ITransformationService transformationService = new DozerTransformationService();
        ((DozerBeanMapper)dozerBeanMapper).setMappingFiles(Arrays.asList("file:/home/pbala/myProjects/accounting/accounting-common/src/main/resources/config/dozer/dozerBeanMapping.xml"));
        ((DozerTransformationService)transformationService).setMapper(dozerBeanMapper);
        ((ReportService)reportService).setTransformationService(transformationService);
    }


    @Test
    public void createBalanceReport(){
        BalanceReportCriteria criteria = new BalanceReportCriteria();
        BalanceReportResult report = reportService.createBalanceReport(criteria);
        
        Assert.assertNotNull(report);
        Assert.assertNotNull(report.getReportCriteria());
    }
}
