package com.app.server.service.organization.contactmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.contactmanagement.EmailCommunicationRepository;
import com.app.shared.organization.contactmanagement.EmailCommunication;
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
import com.app.shared.organization.contactmanagement.EmailCategory;
import com.app.server.repository.organization.contactmanagement.EmailCategoryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class EmailCommunicationTestCase extends EntityTestCriteria {

    /**
     * EmailCommunicationRepository Variable
     */
    @Autowired
    private EmailCommunicationRepository<EmailCommunication> emailcommunicationRepository;

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

    private EmailCommunication createEmailCommunication(Boolean isSave) throws Exception {
        EmailCategory emailcategory = new EmailCategory();
        emailcategory.setEmailCatName("VCSqqQrBKO1eJEFRv3LMFb7fBcimajzuDNi3nxZlfp0L8dQlRn");
        EmailCategory EmailCategoryTest = new EmailCategory();
        if (isSave) {
            EmailCategoryTest = emailcategoryRepository.save(emailcategory);
            map.put("EmailCategoryPrimaryKey", emailcategory._getPrimarykey());
        }
        EmailCommunication emailcommunication = new EmailCommunication();
        emailcommunication.setEmail("wp5cb0Ad483oIl7brrK0DfwjuxXUIfqqeCTpS6xEQtNXzs1YcP");
        emailcommunication.setCommType((java.lang.String) EmailCategoryTest._getPrimarykey());
        emailcommunication.setEntityValidator(entityValidator);
        return emailcommunication;
    }

    @Test
    public void test1Save() {
        try {
            EmailCommunication emailcommunication = createEmailCommunication(true);
            emailcommunication.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            emailcommunication.isValid();
            emailcommunicationRepository.save(emailcommunication);
            map.put("EmailCommunicationPrimaryKey", emailcommunication._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private EmailCategoryRepository<EmailCategory> emailcategoryRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("EmailCommunicationPrimaryKey"));
            EmailCommunication emailcommunication = emailcommunicationRepository.findById((java.lang.String) map.get("EmailCommunicationPrimaryKey"));
            emailcommunication.setEmail("IFEndCK5ymS4UIMnhPV6viwJnxBhvqOz7BqXbxYy8iqpvlivrJ");
            emailcommunication.setVersionId(1);
            emailcommunication.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            emailcommunicationRepository.update(emailcommunication);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycommType() {
        try {
            java.util.List<EmailCommunication> listofcommType = emailcommunicationRepository.findByCommType((java.lang.String) map.get("EmailCategoryPrimaryKey"));
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
            Assert.assertNotNull(map.get("EmailCommunicationPrimaryKey"));
            emailcommunicationRepository.findById((java.lang.String) map.get("EmailCommunicationPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("EmailCommunicationPrimaryKey"));
            emailcommunicationRepository.delete((java.lang.String) map.get("EmailCommunicationPrimaryKey")); /* Deleting refrenced data */
            emailcategoryRepository.delete((java.lang.String) map.get("EmailCategoryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateEmailCommunication(EntityTestCriteria contraints, EmailCommunication emailcommunication) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            emailcommunication.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            emailcommunication.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            emailcommunication.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            emailcommunicationRepository.save(emailcommunication);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "email", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "email", "dQHEImi9tbt7Apv701cHdIEdQ3xh3GECa05YPaE2mQJqEueqDmRaRO5wbAYY2r4wE4oVDfxs1E8WNudXQ947AwMOPEkPOUzACR3XBAu2XQdIXj4xfE1aAG5I74ksrRQwnMH6j6J88zFckjZkouPG9cpZmF1Btm0GVhWtUYVU595sUosTpKXPyISxCugdVmjQWhb0QtS2ubW7aL8mI7yYs0gNaQ3rFMoaRBg4fLYFgWT653ZNpRPSED3CQrPbLbBVv"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                EmailCommunication emailcommunication = createEmailCommunication(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = emailcommunication.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(emailcommunication, null);
                        validateEmailCommunication(contraints, emailcommunication);
                        failureCount++;
                        break;
                    case 2:
                        emailcommunication.setEmail(contraints.getNegativeValue().toString());
                        validateEmailCommunication(contraints, emailcommunication);
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
