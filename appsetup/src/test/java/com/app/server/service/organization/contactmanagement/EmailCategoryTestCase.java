package com.app.server.service.organization.contactmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.contactmanagement.EmailCategoryRepository;
import com.app.shared.organization.contactmanagement.EmailCategory;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class EmailCategoryTestCase extends EntityTestCriteria {

    /**
     * EmailCategoryRepository Variable
     */
    @Autowired
    private EmailCategoryRepository<EmailCategory> emailcategoryRepository;

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

    private EmailCategory createEmailCategory(Boolean isSave) throws Exception {
        EmailCategory emailcategory = new EmailCategory();
        emailcategory.setEmailCatName("E4H61aGMhh1rUxaZP7biQ59V8pnYGPw6ZMGwXF7M15DdsEStAJ");
        emailcategory.setEntityValidator(entityValidator);
        return emailcategory;
    }

    @Test
    public void test1Save() {
        try {
            EmailCategory emailcategory = createEmailCategory(true);
            emailcategory.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            emailcategory.isValid();
            emailcategoryRepository.save(emailcategory);
            map.put("EmailCategoryPrimaryKey", emailcategory._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("EmailCategoryPrimaryKey"));
            EmailCategory emailcategory = emailcategoryRepository.findById((java.lang.String) map.get("EmailCategoryPrimaryKey"));
            emailcategory.setVersionId(1);
            emailcategory.setEmailCatName("DiWqx1H05VgZ3GQeqsQL7vcR7KhKSIyY4oE2QP6kOkaJJuF3fs");
            emailcategory.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            emailcategoryRepository.update(emailcategory);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("EmailCategoryPrimaryKey"));
            emailcategoryRepository.findById((java.lang.String) map.get("EmailCategoryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("EmailCategoryPrimaryKey"));
            emailcategoryRepository.delete((java.lang.String) map.get("EmailCategoryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateEmailCategory(EntityTestCriteria contraints, EmailCategory emailcategory) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            emailcategory.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            emailcategory.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            emailcategory.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            emailcategoryRepository.save(emailcategory);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "emailCatName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "emailCatName", "EKUgaq3zr6MpR6DYMuBiNo544L6XPwoItbXYPAmheNVHTwjzaY4c4IJRKd6JnejXIxO7WEW4TMx2OPIxMQVwVXNTlmNIJN3AUnGCgykf22uM0Tq85xlRFKwCLfdKeS797rjYvPsUHCaf2NMiYvGR4ELAOuITbFqcmcKkJsbI3hFUn89dwesJnNzT9jM02dsD1JV9BtLBaX9EEHURsOJtrt4LLCbjTnpJYkmG853kW00r9k1kFIMz4GhvmIdPpXgua"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                EmailCategory emailcategory = createEmailCategory(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = emailcategory.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(emailcategory, null);
                        validateEmailCategory(contraints, emailcategory);
                        failureCount++;
                        break;
                    case 2:
                        emailcategory.setEmailCatName(contraints.getNegativeValue().toString());
                        validateEmailCategory(contraints, emailcategory);
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
