package com.app.server.service.appbasicsetup.aaa;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.aaa.JwtConfigRepository;
import com.app.shared.appbasicsetup.aaa.JwtConfig;
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
public class JwtConfigTestCase extends EntityTestCriteria {

    /**
     * JwtConfigRepository Variable
     */
    @Autowired
    private JwtConfigRepository<JwtConfig> jwtconfigRepository;

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

    private JwtConfig createJwtConfig(Boolean isSave) throws Exception {
        JwtConfig jwtconfig = new JwtConfig();
        jwtconfig.setTokenKey("OePyEOkDUsgdpEj2jtEjrCgk5e89fbP8BqwGgseixMlAbOvQiS");
        jwtconfig.setExpiration(new java.sql.Timestamp(1477391854164l));
        jwtconfig.setJwtAlgorithm("vstGiceVVELOc2rcAh6sceTtqB3YE180cvpPvo1lwIsx0RSROZ");
        jwtconfig.setEntityValidator(entityValidator);
        return jwtconfig;
    }

    @Test
    public void test1Save() {
        try {
            JwtConfig jwtconfig = createJwtConfig(true);
            jwtconfig.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            jwtconfig.isValid();
            jwtconfigRepository.save(jwtconfig);
            map.put("JwtConfigPrimaryKey", jwtconfig._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("JwtConfigPrimaryKey"));
            JwtConfig jwtconfig = jwtconfigRepository.findById((java.lang.String) map.get("JwtConfigPrimaryKey"));
            jwtconfig.setTokenKey("oaTyjHyWXRXWPbZekFVJ5dHATc4VQhajEKArVDDtlqtog4Hx30");
            jwtconfig.setVersionId(1);
            jwtconfig.setExpiration(new java.sql.Timestamp(1477391854199l));
            jwtconfig.setJwtAlgorithm("zxJ4iPl6gzuvpY8isXgzNnxFsjCku7MS8ulh3Of27L87RujC6R");
            jwtconfig.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            jwtconfigRepository.update(jwtconfig);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("JwtConfigPrimaryKey"));
            jwtconfigRepository.findById((java.lang.String) map.get("JwtConfigPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("JwtConfigPrimaryKey"));
            jwtconfigRepository.delete((java.lang.String) map.get("JwtConfigPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateJwtConfig(EntityTestCriteria contraints, JwtConfig jwtconfig) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            jwtconfig.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            jwtconfig.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            jwtconfig.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            jwtconfigRepository.save(jwtconfig);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "jwtAlgorithm", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "jwtAlgorithm", "zKZedKc6Uhsq8FLpCtvkZMOMkwiviKInMIPa0rNTRpBYoBndBNwnTJ5nK4LeuMza0AwiUXmP5bYmpCWI6pfNrUBWzDETJSGzENKvP4G4GUjZx0ZZ25xmMzIk48FSVEf4uqLkUThpFxE5gcXCqaz66xMtmjGYU9cApmXORAhd1OBGwgFCq2MZxOXDB63Won6JQC3XhcRwLW8VWBS0vY8FSkDCuDkwEbvVbxP3uf553dMXBZrM2u6tTHbsMqhkbzItx"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "expiration", null));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "tokenKey", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "tokenKey", "EkCkZe8aKXZUvFgDLaDmNXtWUHtQw4yrqAqYOZZxJlq9NqL3TkvJuQ8UrAkVOnNBqLmyOizwPuPzxpCffUc74jWEh2N6iC0ktI2mly1kfKoncxJ0aTLBbh8KJBYZ7ztf1hsW3O2us1pRSSrdJHc0N7gCAckL7yjt7sDOMp2V55Y3A9uCPIj0IrCTNjxF01j7vnYvwGbpLVJRuQ1zfmCUmMqo2cL8Cq4tYQHqhYsBjVwXCr5HulKfJJVLg9hGtqUdc"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                JwtConfig jwtconfig = createJwtConfig(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = jwtconfig.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(jwtconfig, null);
                        validateJwtConfig(contraints, jwtconfig);
                        failureCount++;
                        break;
                    case 2:
                        jwtconfig.setJwtAlgorithm(contraints.getNegativeValue().toString());
                        validateJwtConfig(contraints, jwtconfig);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(jwtconfig, null);
                        validateJwtConfig(contraints, jwtconfig);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(jwtconfig, null);
                        validateJwtConfig(contraints, jwtconfig);
                        failureCount++;
                        break;
                    case 5:
                        jwtconfig.setTokenKey(contraints.getNegativeValue().toString());
                        validateJwtConfig(contraints, jwtconfig);
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
