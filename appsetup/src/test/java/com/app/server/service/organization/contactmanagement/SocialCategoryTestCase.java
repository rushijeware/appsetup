package com.app.server.service.organization.contactmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.contactmanagement.SocialCategoryRepository;
import com.app.shared.organization.contactmanagement.SocialCategory;
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
public class SocialCategoryTestCase extends EntityTestCriteria {

    /**
     * SocialCategoryRepository Variable
     */
    @Autowired
    private SocialCategoryRepository<SocialCategory> socialcategoryRepository;

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

    private SocialCategory createSocialCategory(Boolean isSave) throws Exception {
        SocialCategory socialcategory = new SocialCategory();
        socialcategory.setSocialCatName("r9aSlZBy63hDKAGsSsGX3aZQigObKAldRktHuQst0gA08BaGMK");
        socialcategory.setEntityValidator(entityValidator);
        return socialcategory;
    }

    @Test
    public void test1Save() {
        try {
            SocialCategory socialcategory = createSocialCategory(true);
            socialcategory.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            socialcategory.isValid();
            socialcategoryRepository.save(socialcategory);
            map.put("SocialCategoryPrimaryKey", socialcategory._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("SocialCategoryPrimaryKey"));
            SocialCategory socialcategory = socialcategoryRepository.findById((java.lang.String) map.get("SocialCategoryPrimaryKey"));
            socialcategory.setVersionId(1);
            socialcategory.setSocialCatName("0hJjZXVUiV2LzCiGHWC6pWpWufq4l38OTzJ2ATUHKMvBhintuu");
            socialcategory.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            socialcategoryRepository.update(socialcategory);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("SocialCategoryPrimaryKey"));
            socialcategoryRepository.findById((java.lang.String) map.get("SocialCategoryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("SocialCategoryPrimaryKey"));
            socialcategoryRepository.delete((java.lang.String) map.get("SocialCategoryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateSocialCategory(EntityTestCriteria contraints, SocialCategory socialcategory) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            socialcategory.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            socialcategory.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            socialcategory.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            socialcategoryRepository.save(socialcategory);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "socialCatName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "socialCatName", "FB7dmU5D3fpWRNJRtNGoQcKdJqnysLKkHeav4cFp5MRz1BukegcV7DaKM7OxHPL6iQTq7S2MjofcaK0ZVmrqnmksj6ft0pUTAvivklOXj6s5NOBK22AXq6wiHOM8KJXYvcQkzjFTHrWikJnQ4rkKXzOhAmLFq8ta8uDNKMrYRp0cgiUJz7rvnre24PxqOXqeyuKdscI1LPuVWBZlb5d9AtcwMG1fYxWcTCtoLZsZdem4Cpo5GgbqovOzfDkU3y67D"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                SocialCategory socialcategory = createSocialCategory(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = socialcategory.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(socialcategory, null);
                        validateSocialCategory(contraints, socialcategory);
                        failureCount++;
                        break;
                    case 2:
                        socialcategory.setSocialCatName(contraints.getNegativeValue().toString());
                        validateSocialCategory(contraints, socialcategory);
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
