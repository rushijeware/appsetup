package com.app.server.service.organization.contactmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.contactmanagement.PhoneCommunicationRepository;
import com.app.shared.organization.contactmanagement.PhoneCommunication;
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
import com.app.shared.organization.contactmanagement.PhoneCategory;
import com.app.server.repository.organization.contactmanagement.PhoneCategoryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class PhoneCommunicationTestCase extends EntityTestCriteria {

    /**
     * PhoneCommunicationRepository Variable
     */
    @Autowired
    private PhoneCommunicationRepository<PhoneCommunication> phonecommunicationRepository;

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

    private PhoneCommunication createPhoneCommunication(Boolean isSave) throws Exception {
        PhoneCategory phonecategory = new PhoneCategory();
        phonecategory.setPhoneCatName("qeBFsXi8csXTLmY1IyZDgIlHUyHPeATM4ezgbRtYP2GR9HYpb0");
        PhoneCategory PhoneCategoryTest = new PhoneCategory();
        if (isSave) {
            PhoneCategoryTest = phonecategoryRepository.save(phonecategory);
            map.put("PhoneCategoryPrimaryKey", phonecategory._getPrimarykey());
        }
        PhoneCommunication phonecommunication = new PhoneCommunication();
        phonecommunication.setPhoneNo("mFLJHU9WPWxCEyofahZM5wruxJIEjyy7jTUH8nns1tk3P2d8hs");
        phonecommunication.setCommType((java.lang.String) PhoneCategoryTest._getPrimarykey());
        phonecommunication.setEntityValidator(entityValidator);
        return phonecommunication;
    }

    @Test
    public void test1Save() {
        try {
            PhoneCommunication phonecommunication = createPhoneCommunication(true);
            phonecommunication.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            phonecommunication.isValid();
            phonecommunicationRepository.save(phonecommunication);
            map.put("PhoneCommunicationPrimaryKey", phonecommunication._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private PhoneCategoryRepository<PhoneCategory> phonecategoryRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("PhoneCommunicationPrimaryKey"));
            PhoneCommunication phonecommunication = phonecommunicationRepository.findById((java.lang.String) map.get("PhoneCommunicationPrimaryKey"));
            phonecommunication.setVersionId(1);
            phonecommunication.setPhoneNo("rHnxMsSxrr0Fq5wligd82hawDP8DdJvA0xuhDg6ygXoGdl3Zip");
            phonecommunication.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            phonecommunicationRepository.update(phonecommunication);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("PhoneCommunicationPrimaryKey"));
            phonecommunicationRepository.findById((java.lang.String) map.get("PhoneCommunicationPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycommType() {
        try {
            java.util.List<PhoneCommunication> listofcommType = phonecommunicationRepository.findByCommType((java.lang.String) map.get("PhoneCategoryPrimaryKey"));
            if (listofcommType.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("PhoneCommunicationPrimaryKey"));
            phonecommunicationRepository.delete((java.lang.String) map.get("PhoneCommunicationPrimaryKey")); /* Deleting refrenced data */
            phonecategoryRepository.delete((java.lang.String) map.get("PhoneCategoryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validatePhoneCommunication(EntityTestCriteria contraints, PhoneCommunication phonecommunication) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            phonecommunication.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            phonecommunication.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            phonecommunication.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            phonecommunicationRepository.save(phonecommunication);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "phoneNo", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "phoneNo", "jbinVaCij22vVhXhczXtkOkr9M7wSa8mtPzMQtZdwDfcl1ENoYFXDDkU5QLN2ZUUpi261vJx0dhcqbvmCPGC8Hy0USqgKQqGGv3o2TUUIdQWOIiIUpDrLPivuRyxfYYpPVx6zP1pKPJ9bPg4PYbkk734GDn2tdamIEww1jVJ7glsSqwvKjPZStNLPdkbUJedUZcvq4Q7LKb5BjqW7T7YXW9BeuJtLoiLKnkwmzlYDoaizz4l8u7nS2JyECyIgeUXe"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                PhoneCommunication phonecommunication = createPhoneCommunication(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = phonecommunication.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(phonecommunication, null);
                        validatePhoneCommunication(contraints, phonecommunication);
                        failureCount++;
                        break;
                    case 2:
                        phonecommunication.setPhoneNo(contraints.getNegativeValue().toString());
                        validatePhoneCommunication(contraints, phonecommunication);
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
