package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
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
public class UserAccessLevelTestCase extends EntityTestCriteria {

    /**
     * UserAccessLevelRepository Variable
     */
    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("5aXy7c21ktEXLEfoqPGl7FdkkcGyd8rDaNPUrc6E1D8DJTMu1Q");
        useraccesslevel.setLevelHelp("qwZZSgPZtBJC99OMZ9minJJqWvjsAVhVAQ8Z7dMZZlQuEoE7pf");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelIcon("MPaiSI5w4lLPpa9R8qYfnaF27LLJSl1SoxXlzqLjGE2hh3M5o2");
        useraccesslevel.setLevelName("C4JoWhDqGcUeXEJJ85UGmmEk6ns0L1fMk5g4ar752tJPLRQI3G");
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setLevelDescription("aX6YTdz9Y8YSAKQNskZ0gfdTlXA8Ku3kPSH6r6tgqHFah26MVb");
            useraccesslevel.setLevelHelp("0DrWFwBQSyVzdMtZ3dhcsskq8ax233jUmPLeWCefSP22nyetvt");
            useraccesslevel.setVersionId(1);
            useraccesslevel.setUserAccessLevel(12296);
            useraccesslevel.setLevelIcon("loxFySBsUTeBKi3eFbn4Y4j4TPiYGvx8uHj2OpLxh9RPQEzTZg");
            useraccesslevel.setLevelName("t6vZJXHQYXXSULTLWlKsas9MErLm6Vkf6C5N3xkBtv5fBuBd6C");
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 152025));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "WnDk54dD5ZUy2saAsfJHdCYut9uZxZFM0Wt9IrfXs6xHwT4wGNNI8X5nzvmQEZQbWIWYVqryUOgpQk34j01Nq4JgGd1o9Ii1oMWohg12WOunFS9erDRohwzbgs54Cjjqku2D4Tt1Iq90ASwnaHxt0R9759oluuX12BR69qSiHHhww8zAvlcxmtnOkSMJlqpvie3oQe1FUX2neVUAFmdZc072NQVxK7AFYWl6s9saVE6aPitYwO56LAA83tjsTGI73"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "O6CPgpo36NMcLH9hwsRwO22PBUozHT8FvQqwkQzVpScgHnKC5MoM4PWnjU6Ej7h9tyTThkB90zTqxTYO2GsgElepWodrlFYOLYEtnIzlnfP9lXgJYuCp2swfsUkCHOLmmyucy8dJNdWsRu8Gv7e1HuDxtft8V8Jgvosvg6TL5HPaZA6TsC4abqqHHyyuiJA5xM2NicVjT4kINmbaOXLd9adN3DrRvgKJumZrA9KTKx9SEiHBMfXmSI4pMQMQ0Kpo9"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "5xyLKaNXZwEwtbZuhr2O2XAWdehy7Rg13ctg7wsnJrlrc69w5LSvrKr6qEUM01qaqmrtG1wExhRerHnCx05kzx6FG5sDpeljIaLZFM1Gq0gnkfCL4rot6M5zhUWaVi73nXadBvg68yHueWDMP1FxG4EcZt2649BHzoedMRP1Qm0avvLTWH0qOvlVHRmxquyecGBNOWRcN4XrHEQ4UWrYkknEgActBwvgADklNlBxLMEj97lHyHRmPB8OhpQLiJVlp"));
        entityConstraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
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
