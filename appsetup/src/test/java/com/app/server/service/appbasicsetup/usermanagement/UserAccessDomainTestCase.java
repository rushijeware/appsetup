package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
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
public class UserAccessDomainTestCase extends EntityTestCriteria {

    /**
     * UserAccessDomainRepository Variable
     */
    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

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

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainName("8yHLL0o0jmFDTOfWufKQGAilonpt98tH8jS6Cw6p1Ni6QOzrjH");
        useraccessdomain.setDomainIcon("Pgt051GcsGITpRmOsZs9zawmBTVynp69QGK5PNliubT0AHTaFp");
        useraccessdomain.setDomainHelp("SU6MEYLFzajyahqWuInEllNUTqpEqV9USTFH0knZykzdXiC8Dv");
        useraccessdomain.setDomainDescription("WihmFknjmEfwmBeLeboRHBVGAK2hSJsWgdCCZK6fJ1zhqtbFgK");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setUserAccessDomain(10273);
            useraccessdomain.setDomainName("o25ON8hFrwMr6N9rCUelK3vr62ekiimI2Mvf3EcSr4r4u2YnYR");
            useraccessdomain.setDomainIcon("1vkH6QKlaRipyERF75gf5QyEMb2URtY6sM0hWDdRvH82MkBETS");
            useraccessdomain.setDomainHelp("HtxP2jFwdNIX9VV2aLOgPmeg3QPGh0GylmMl1MR1a48s3xwaRv");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainDescription("5Uga1DQvGp43mtKvAdz3gCKOYmNzXwHLpxcuuhXjPeyqEYTJSD");
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 124123));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "iLNcpQaC7CKUkjuvXLs7Liul4M7bi6xNsArylDsGbPeZF6hpPrClk7mV5EzCijd8tOKJd7l0Dslgr8QvCoMfzCnt9zXitMq4PlBbc9ikRqAnptU7KEpbY1him89Ws5p9MTwDvDb4D24TyhpbvYwmUWXoMA7xNE74i5p157AVDjJ7vad8nPJAPRgB7ZbZXqkruVbgaael1KByDJ7MczS5aTuhTmpNwRcecuHPcvVrfyRPyubhVUYmdhxBGg7kJBc9b"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "b5mCgxPPXeilLww4xaMlpRrrqQyRoAPnD8D5IDBpMTCsKzrkSsIFkxJVfZS5WyLLjVeQH7g4kCBupKQaXrcS7seaPXr6GfWrjwPzcNwO4boXrFkKuqdRG4AfJIcNCcwlO8aJnbVyVb0g22ttOdAbiHRuUCV9KHgtxRl9frH2XTBDGITOXoXFQmnJlWe3W6V6s3t4TZ1z9p4VNexDwqxHiA7tRYKYcpxxI6hMRORBcFUiZcfqQ2dPav2uCDbBwFoiL"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "AboXuqzyjKxlHaL91dCvenYo03sdMZ0Slsg8XKbMWzHzJ5CMVJcSrsBXh8arSI1lO6YDVPV6ccNO2SEZ2PZfjj2lw7R7P1PKAAVTTjQB7XbXI3K6NMHRAIvClkDcVDzkpFvmVyq4oodabXPRAwqotp6IZO6k0z7uRymeRw358i5fqB45vMIaI8uUs91745WQFwYRNJxUYEQhFJG78uzKfNBjOssu9sLRs984dUZwUvYHaKLpariprKic7I3czT90x"));
        entityConstraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
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
