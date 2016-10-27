package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserDetailRepository;
import com.app.shared.appbasicsetup.usermanagement.UserDetail;
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
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.app.shared.appbasicsetup.usermanagement.Question;
import com.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.app.shared.appbasicsetup.usermanagement.UserData;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserDetailTestCase extends EntityTestCriteria {

    /**
     * UserDetailRepository Variable
     */
    @Autowired
    private UserDetailRepository<UserDetail> userdetailRepository;

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

    private UserDetail createUserDetail(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("QFRBJvBYi7ozXCWsP9vq3Ev1ai8kFNcJr3zOftdPZ10dZMgdtJ");
        useraccesslevel.setLevelHelp("IJVpALeE4PQfWXrceiMBPQ8tVWZZdToPli35zOSbc3yZYoZ1Tk");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelIcon("OlbXR9QALGBThouSODBhVfHmoBolj4EN8M0LcIsJXtjNHLPrmT");
        useraccesslevel.setLevelName("vBY1q5EFmr1k4r0Rs1bu6mZggzkqFWDJThxB6KoS8z7ejLYO6M");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainName("R9b5YKq4DeFI2eUhjhNgFgcMmyShJXQd5aOmUv98828afgMQML");
        useraccessdomain.setDomainIcon("tuxt4ml9M6kCbca8fr74foHUkFPfN5gbTfkCHWpSKY33vqAaVq");
        useraccessdomain.setDomainHelp("XvpvMHQxmAqn1voOxhnSPtZ2CwuUVRkuDsp7HS6tib1IjFjvvC");
        useraccessdomain.setDomainDescription("9eHDiJIKcplpFlmeYLGMNDSsrTwZfRooTcINYdyG1Gq40LEJQy");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserDetail userdetail = new UserDetail();
        userdetail.setPasswordExpiryDate(new java.sql.Timestamp(1477391844960l));
        userdetail.setMultiFactorAuthEnabled(1);
        userdetail.setChangePasswordNextLogin(1);
        userdetail.setPasswordAlgo("j6MYdA87KYZ0JijUSditYnA7nsQ3CivCB5yb51T8I3S1uIcy0C");
        userdetail.setAllowMultipleLogin(1);
        userdetail.setIsLocked(1);
        userdetail.setSessionTimeout(1466);
        userdetail.setGenTempOneTimePassword(1);
        userdetail.setLastPasswordChangeDate(new java.sql.Timestamp(1477391844961l));
        userdetail.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        userdetail.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        userdetail.setUserAccessCode(54429);
        userdetail.setIsDeleted(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        passrecovery.setAnswer("Jjzr3QqcdjgWeViakw52ckRgkPoYmngTWojjhVOhtwkE0bGR2o");
        Question question = new Question();
        question.setLevelid(10);
        question.setQuestionDetails("j3vkbuyaIJ5Nd9359d7rryOXruAIgY9PCLYtFK5PyGvutN320L");
        question.setQuestion("fAIAQLDJCa7bDtYPICR77ICNAvr9mBTUE7LuaBRXuaDPsEj5KZ");
        question.setQuestionIcon("MqvRV3ZNstOUEnkTXPTpny6JornDlXew9eniGGH6lyq3XuHxHP");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setAnswer("PXUsyom2seYyup7gSaqnVAflqJmwD7b05q68SgodldQeojt3fG");
        passrecovery.setUserDetail(userdetail);
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey());
        listOfPassRecovery.add(passrecovery);
        userdetail.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1477391845450l));
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1477391845479l));
        userdata.setUserDetail(userdetail);
        userdata.setOneTimePassword("Cala8uAMDFF65HdaYSWHm5lrVDZdSyHR");
        userdata.setOneTimePasswordExpiry(8);
        userdata.setPassword("ctijUAqnEoQrdvi6zaKIkPPTUgJU1FDsWKHQ47d8Y3yMH4ONda");
        userdata.setLast5Passwords("MlDIjpgEZunhKiyMCL7gkJatXbzfvwG3wyM19h0DppIH08rBjW");
        userdetail.setUserData(userdata);
        userdetail.setEntityValidator(entityValidator);
        return userdetail;
    }

    @Test
    public void test1Save() {
        try {
            UserDetail userdetail = createUserDetail(true);
            userdetail.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            userdetail.isValid();
            userdetailRepository.save(userdetail);
            map.put("UserDetailPrimaryKey", userdetail._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Test
    public void test2findByuserAccessLevelId() {
        try {
            java.util.List<UserDetail> listofuserAccessLevelId = userdetailRepository.findByUserAccessLevelId((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            if (listofuserAccessLevelId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2findByuserAccessDomainId() {
        try {
            java.util.List<UserDetail> listofuserAccessDomainId = userdetailRepository.findByUserAccessDomainId((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            if (listofuserAccessDomainId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("UserDetailPrimaryKey"));
            userdetailRepository.delete((java.lang.String) map.get("UserDetailPrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateUserDetail(EntityTestCriteria contraints, UserDetail userdetail) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            userdetail.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            userdetail.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            userdetail.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            userdetailRepository.save(userdetail);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 1, "userAccessCode", 69223));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "multiFactorAuthEnabled", 2));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "genTempOneTimePassword", 2));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "allowMultipleLogin", 2));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "isLocked", 2));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "isDeleted", 2));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "changePasswordNextLogin", 2));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "passwordAlgo", "Do3lQs08GhBLcgJ3cgleXjM5vtcHxijWbJWGo9Pl9ZepQ7NPf6kN8hxjyG1DdXKJh"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "sessionTimeout", 5549));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                UserDetail userdetail = createUserDetail(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = userdetail.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        userdetail.setUserAccessCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserDetail(contraints, userdetail);
                        failureCount++;
                        break;
                    case 2:
                        userdetail.setMultiFactorAuthEnabled(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserDetail(contraints, userdetail);
                        failureCount++;
                        break;
                    case 3:
                        userdetail.setGenTempOneTimePassword(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserDetail(contraints, userdetail);
                        failureCount++;
                        break;
                    case 4:
                        userdetail.setAllowMultipleLogin(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserDetail(contraints, userdetail);
                        failureCount++;
                        break;
                    case 5:
                        userdetail.setIsLocked(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserDetail(contraints, userdetail);
                        failureCount++;
                        break;
                    case 6:
                        userdetail.setIsDeleted(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserDetail(contraints, userdetail);
                        failureCount++;
                        break;
                    case 7:
                        userdetail.setChangePasswordNextLogin(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserDetail(contraints, userdetail);
                        failureCount++;
                        break;
                    case 8:
                        userdetail.setPasswordAlgo(contraints.getNegativeValue().toString());
                        validateUserDetail(contraints, userdetail);
                        failureCount++;
                        break;
                    case 9:
                        userdetail.setSessionTimeout(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserDetail(contraints, userdetail);
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
