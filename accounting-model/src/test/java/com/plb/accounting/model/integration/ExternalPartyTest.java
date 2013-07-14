package com.plb.accounting.model.integration;

import com.plb.accounting.model.SampleFactory;
import org.junit.Assert;
import org.junit.Test;
import plb.accounting.model.ExternalParty;

/**
 * @author pbala
 */
public class ExternalPartyTest extends BaseIntegrationTest{

    @Test
    public void testPersistExternalParty() {
        ExternalParty externalParty = SampleFactory.getExternalParty();
        entityTransaction.begin();
        entityManager.persist(externalParty);
        entityTransaction.commit();
        Assert.assertNotNull(externalParty.getId());
    }
}
