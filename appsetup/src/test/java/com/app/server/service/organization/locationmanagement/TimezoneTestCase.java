package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.app.shared.organization.locationmanagement.Timezone;
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
public class TimezoneTestCase extends EntityTestCriteria {

    /**
     * TimezoneRepository Variable
     */
    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

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

    private Timezone createTimezone(Boolean isSave) throws Exception {
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("oO8oSFtTgu1ZWuPCgdMBtYirXoFxrKjXDelexWK3ErgIPnwWLx");
        timezone.setUtcdifference(10);
        timezone.setCities("CCqkQA076NKm8GjbXMEwzYfsuqgu1ZEKN143vsUvzbxQLDnFL9");
        timezone.setGmtLabel("URC4RP9Eo46Hscd24Vd5sXhupyGCL53MjDPdmJHkMcn24oRepD");
        timezone.setCountry("GPRzdFDQlH78QOFC0z571TwI4M3d4LTAICbwXnToTGg4FMJWR3");
        timezone.setEntityValidator(entityValidator);
        return timezone;
    }

    @Test
    public void test1Save() {
        try {
            Timezone timezone = createTimezone(true);
            timezone.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            timezone.isValid();
            timezoneRepository.save(timezone);
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            Timezone timezone = timezoneRepository.findById((java.lang.String) map.get("TimezonePrimaryKey"));
            timezone.setTimeZoneLabel("fnOj0S9L58aa1kUPn0tDf5j9lU9NpGF7unybvZ8TRxAGlVIc5q");
            timezone.setUtcdifference(6);
            timezone.setVersionId(1);
            timezone.setCities("NQWTZOsGxnjPtIm6StFByysmTaSsRI9LfRcG6d0Q3Nj6JaEzNk");
            timezone.setGmtLabel("r9qYQaXW3ZPLFomM5FfSUUgthsLieYchGKpykkX4XM0K8URgbz");
            timezone.setCountry("Vv5mY8RcnEj6IpLP4ju2uX0YvJ8PBdkMEVACk4vuH3OLbllFMT");
            timezone.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            timezoneRepository.update(timezone);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            timezoneRepository.findById((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateTimezone(EntityTestCriteria contraints, Timezone timezone) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            timezone.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            timezone.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            timezone.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            timezoneRepository.save(timezone);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "utcdifference", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "utcdifference", 19));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "gmtLabel", "JmoI1VGS1DFuiyqAElJO6fyIbEduib0tTYMw89F5TCTLItV1Pi1WA1vZXeTFn073s4jCrQiR0tl3eAepGK5bF8Oa7zJDw5SHXqoQWqw5BQZVg4DygNeDkbbBsMZlVXPXetTGdh5166PugX6f8FTtvroo1fBxMmcI28tjXi3BYiXxQdURpuKiHwN5rUugDZe2htBfuD5iOMDNRHfkB3YFyETGWglY7hds3C314SZ17sSxzqp2XVUnOqiQ1kXIo5cGS"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "timeZoneLabel", "Bod16bI6TUow8vp3lq3TgQeRBMMO5ve3wdMvPzNROJXc3lJPV3uAKkCnHNVDTQ1K1qCzLw4HdurDzPuNrYqldMDa0XCQNRhuzcWJ4h2YdMNOPMQpr97LmMgQdIsJtCtgdg9x0cDc4oKfiA4Uz58EbL3EphT3mGTfbOyhvpG7hCvSs59QNN584trJ8DX5mgRtDv2RI89X3tOFPzbCKjsolCuEXyTMsOrBygGBHvhHQ0gWR99fqyW87x0WNjglYEKsH"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "country", "3jAi4UNjrxSbRKJahjZHkl4pNkgawkLdzWSRowZUtjiWdGdhcmNhNxQ4yDarwwfEL4B3c59hfXkuk6q3GsAThD3MHwpDLT54xClDK38xgdRwSMJR8ewQc7svHcVfL5MAAdqJwEERo38w5qt4N2SrOhGZosU27n6hPAdgko60pWtg2Kh6iYU2G7I5t0Pvp8EvBAWOJJTIi5FL7KcXFdDVA6ANxhyvVAj851v5LPMrbHJBU6Y0AkGsuQFaCdGNs5DRB"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "cities", "kG42cSSsFHHssIuQG8ianwghLADmvfVLWLztQP4fE1m5M9ha8FgAPIARVtVxIZD0YjZwKCMf3VpZc1D3UMuNUgHFYivL8dBBYsjr2fqT09fz2XJBuZcyZoFy9hxSFiHXpayyrqIohs57YLYFjgp5q73oXX9bIZoXxpHcHjxaRActOg0SoFW86MHPqixvMQ5Jvlx3RjAsIx5UZ6YuaooVwYPQUUmUhfK3yp4vzKZb0HFUFBjSP0OASEE046IKWGzAp"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Timezone timezone = createTimezone(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = timezone.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(timezone, null);
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 2:
                        timezone.setUtcdifference(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 3:
                        timezone.setGmtLabel(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 4:
                        timezone.setTimeZoneLabel(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 5:
                        timezone.setCountry(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 6:
                        timezone.setCities(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
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
