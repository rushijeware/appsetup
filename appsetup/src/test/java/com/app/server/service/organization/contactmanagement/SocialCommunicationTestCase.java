package com.app.server.service.organization.contactmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.contactmanagement.SocialCommunicationRepository;
import com.app.shared.organization.contactmanagement.SocialCommunication;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import org.springframework.web.context.request.RequestContextHolder;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.junit.Assert;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;
import com.app.shared.organization.contactmanagement.SocialCategory;
import com.app.server.repository.organization.contactmanagement.SocialCategoryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class SocialCommunicationTestCase extends EntityTestCriteria {

    /**
     * SocialCommunicationRepository Variable
     */
    @Autowired
    private SocialCommunicationRepository<SocialCommunication> socialcommunicationRepository;

    /**
     * RuntimeLogInfoHelper Variable
     */
    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    /**
     * EntityValidator Variable
     */
    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    /**
     * RandomValueGenerator Variable
     */
    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    /**
     * List of EntityCriteria for negative Testing
     */
    private static List<EntityTestCriteria> entityConstraint;

    /**
     *  Variable to calculate health status
     */
    @Autowired
    private ArtMethodCallStack methodCallStack;

    /**
     * MockHttpSession Variable
     */
    protected MockHttpSession session;

    /**
     * MockHttpServletRequest Variable
     */
    protected MockHttpServletRequest request;

    /**
     * MockHttpServletResponse Variable
     */
    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        final MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            final String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).requestCompleted();
        RequestContextHolder.resetRequestAttributes();
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityConstraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private SocialCommunication createSocialCommunication(Boolean isSave) throws Exception {
        SocialCategory socialcategory = new SocialCategory();
        socialcategory.setSocialCatName("g1Op2PWJcMSiY7xw1ceeiclRFZTs63wo4WcQ3Ar4O48GEJ3exZ");
        SocialCategory SocialCategoryTest = new SocialCategory();
        if (isSave) {
            SocialCategoryTest = socialcategoryRepository.save(socialcategory);
            map.put("SocialCategoryPrimaryKey", socialcategory._getPrimarykey());
        }
        SocialCommunication socialcommunication = new SocialCommunication();
        socialcommunication.setCommType((java.lang.String) SocialCategoryTest._getPrimarykey());
        socialcommunication.setSocial("rvSb9TSslIXYtn7wcpxEwJBmGvvjqWfGrELA7pUQswdEGxsi9J");
        socialcommunication.setEntityValidator(entityValidator);
        return socialcommunication;
    }

    @Test
    public void test1Save() {
        try {
            SocialCommunication socialcommunication = createSocialCommunication(true);
            socialcommunication.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            socialcommunication.isValid();
            socialcommunicationRepository.save(socialcommunication);
            map.put("SocialCommunicationPrimaryKey", socialcommunication._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private SocialCategoryRepository<SocialCategory> socialcategoryRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("SocialCommunicationPrimaryKey"));
            SocialCommunication socialcommunication = socialcommunicationRepository.findById((java.lang.String) map.get("SocialCommunicationPrimaryKey"));
            socialcommunication.setSocial("DiZ4cAEBJtZirS6UnG4Btx8luZ8GLkZ8Vd9mXGWmmIKSZh4bVo");
            socialcommunication.setVersionId(1);
            socialcommunication.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            socialcommunicationRepository.update(socialcommunication);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycommType() {
        try {
            java.util.List<SocialCommunication> listofcommType = socialcommunicationRepository.findByCommType((java.lang.String) map.get("SocialCategoryPrimaryKey"));
            if (listofcommType.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("SocialCommunicationPrimaryKey"));
            socialcommunicationRepository.findById((java.lang.String) map.get("SocialCommunicationPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("SocialCommunicationPrimaryKey"));
            socialcommunicationRepository.delete((java.lang.String) map.get("SocialCommunicationPrimaryKey")); /* Deleting refrenced data */
            socialcategoryRepository.delete((java.lang.String) map.get("SocialCategoryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateSocialCommunication(EntityTestCriteria contraints, SocialCommunication socialcommunication) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            socialcommunication.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            socialcommunication.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            socialcommunication.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            socialcommunicationRepository.save(socialcommunication);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "social", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "social", "i3aDwxELNFfgYyti5MnwhCU9vM6LaZ4BMgLsEp9frthELhrOEy02FXn7XAoQWgiRtPuxssJDEfcHV3woQvjb0JFh3NtnYPylvieOFi0II8djSrYFLQ1NODahLrCMZARQXErtMH2H3oBRSsoJIKLiXHU8CkpPKJN0B2UsfhNavEzvE9yzSqYCd9UD1PnyPVWSP7TxuSNSsVatGav0zEYTXoIvaHaTUUj0VCrDmPYvoDnYSHp4ZMtoxRbb50gHCKn0z"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                SocialCommunication socialcommunication = createSocialCommunication(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = socialcommunication.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(socialcommunication, null);
                        validateSocialCommunication(contraints, socialcommunication);
                        failureCount++;
                        break;
                    case 2:
                        socialcommunication.setSocial(contraints.getNegativeValue().toString());
                        validateSocialCommunication(contraints, socialcommunication);
                        failureCount++;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            Assert.fail();
        }
    }
}
