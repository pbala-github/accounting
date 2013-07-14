package com.plb.accounting.model.integration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author: pbala
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({AccountTest.class, ExternalPartyTest.class, TransactionTest.class})
public class AccountingDomainIntegrationTestSuite {
}
