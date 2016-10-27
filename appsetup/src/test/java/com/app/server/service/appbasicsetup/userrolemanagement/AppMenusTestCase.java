package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
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
public class AppMenusTestCase extends EntityTestCriteria {

    /**
     * AppMenusRepository Variable
     */
    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws Exception {
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuHead(true);
        appmenus.setGoLiveDate(new java.sql.Timestamp(1477391852150l));
        appmenus.setRefObjectId("XjhAZ7BHa3zJs23rUwiiDCJjSvlH46Ko3fzF0LYFS20COziZrB");
        appmenus.setMenuCommands("319iPUrsraFkLsV4o5GycUHFz9oKBFnNYSydrY4h32cJX9riQr");
        appmenus.setAutoSave(true);
        appmenus.setUiType("s0a");
        appmenus.setMenuAction("WqS3FIbyOZ2HQAIHzdGxFVEgowPaNaF8F3DdBffVLgIyqCPMYH");
        appmenus.setMenuIcon("kQIBhgLflPrT6MCcyHaF3kqaOWDGSd1RgD6QjahJAs4vCteQBS");
        appmenus.setAppId("Wy3kK2DKFlo9OpsKoywO8rd9QNbEbj0VAIdrrKW9KOtZL1D6HX");
        appmenus.setStartDate(new java.sql.Timestamp(1477391852150l));
        appmenus.setAppType(2);
        appmenus.setMenuDisplay(true);
        appmenus.setMenuLabel("IIgUrtX4d7CmkG0HzvZ41dfovoKcT2kSgK4y6Hgj5D8MbqHOi1");
        appmenus.setMenuTreeId("FUC7hwt76uwISL0peGIrlcRrxlmEdh5zTPogJsmChm1xKJV1Tt");
        appmenus.setExpiryDate(new java.sql.Timestamp(1477391852150l));
        appmenus.setMenuAccessRights(4);
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setGoLiveDate(new java.sql.Timestamp(1477391852183l));
            appmenus.setRefObjectId("7KFE9kaiK6JYUt0DTnsLODA9fsj2eURlx7F35AHfX9Lp7DeUic");
            appmenus.setMenuCommands("SxJhSvpGQKZtgmGZ4x5xo4QqUS1YRfstlqGR3BdEMqyMyRVSge");
            appmenus.setUiType("Y7e");
            appmenus.setMenuAction("33oyj1T9B3MG6tz8rnal6JONmUmXm3sp6XL2rTnWaUnveoMwva");
            appmenus.setMenuIcon("k4YjDrycmQCLdewlnPAN4EerZF0yb14r59CFeKS6Ykm5PxdMyQ");
            appmenus.setAppId("ihEuhXzuFc1pN7EN8beLeEkkIz2OsFR8JyV4YLpd26Qa0CoyaJ");
            appmenus.setStartDate(new java.sql.Timestamp(1477391852202l));
            appmenus.setAppType(1);
            appmenus.setMenuLabel("6Mzri72lqDIcq7Hv0hOJwoucUCyC1HubQ2xhzdYUB6Of91Piu1");
            appmenus.setVersionId(1);
            appmenus.setMenuTreeId("JzyAQtZ0I0457KUR8e2AlqRGpGjHZpWmry4HeNyhp20BXjBbNU");
            appmenus.setExpiryDate(new java.sql.Timestamp(1477391852217l));
            appmenus.setMenuAccessRights(9);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "OfVPjtpojBrr8tvbl9dfP7GXVlWYHBr39bOIJy99033lcMD2uXBZ4Bg6A5jdA5Q3gJCebxKuFGdGogZiicI16gd3o2PppSrLqjFRk4Gc6mQJac7llvNs74OV8xwnSh5oHGb9umsPwua5tG4FLxwdjamWKFlfVSdjOoDgMJuX6Y3yEiU4RGowskYNENIrK18aI9rJmmzFWTXlIeuZOcGltbr9NjPkNgfnKArrsIjggF8K54TL72RljNTwVXnEpDIFq"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "ZvoqHE4V573tMALh1eItPJK62wZjYkGMubcAMa0zUY82qh2qKxDZ1GOGOxmhbyNoGayZGSv6h9QTsHEn1pizTjva2xQF0pzZeGO6xCbI1m03kwurJwlXjHFdsR5RNoWPPTkD7j4BnDiZi6QMGsn6bwX8bCl7Np3yktyEswXJMu1Rqa1TjRdfHn1fv2bmt7UTZWgjq2JQuO3hVRYJyNxVdCQfHIJjhxMzJiGEC3GGfFqv3rby6r1imvrH6yR7imvfM"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "Ya4Do7piPxgDVpfync6EeChTe4423FfBSD13m0vhy2GN2SxP7eXijikpIzgLHC6vKakdALSCooZszSGwRnZ6NBLbTADCEbdHpAfwFWuI0NEsvTvqleXKcbVwl5GtzS6DSkcYqCTJcNDVzP3wA8ANBrUfG30h3xiEJ71E2AP28pSVZEoLmTI59Iy5gP4GlwMf1Nq6rDDK8kr8Y5L8j5HFwNV5Fy8uS0X7ChAkS6vscSnQPPFJJLZQlwWwDxqPokFE4"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "5Iq8lvEsZF7DlatADsuHfvjObITKHaYm5YoplQSfkyVLff0idZj5FMBxs6ofPNYYo"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "suaaBf3HUyvn7tWyUd8ZWAU8p0ECI3z6sSAdhMW5NyMPqk0rLoBmitRf8UcDLQaGYMAj8Wtqt1BGLiuaDExGbHRLF0FDYY2qjurM3iXUxXFVjbJXaBQX4NMKGl4TyPaSAiabZimeGdJ5DgUDyfv0Xt1CV3DLKFcT9Aykw8bhjbbNOJraU0FjErh4kog1Vp9UrmlRVzsd8YEzkhBTI1Vzg4aOwJDqnizxRAOaJevSFe97HLqr0zIrguI6J67FD1usU"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "q2rr"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "IndqUHt0eCxzYEZ79EP52CAM8aaSpa4nHquR67EmL0yPny0MgQkZIY1IiWjTtBmTGz7t6Mn6p4BK9hiLSEh7gtF8ykGzbP4SO5TdI4Lvh1HnSyQVXwiB8DDjnJVrF5UDqUvktCMlnuxOxyjQ0e78yBvldoKLC6CVm6pqgz4WVPEJEHPt7Ufas52x3gcVOL8hO4djuCOrAgYdJ047X1bBMIdAnKUMNpSTZqAVMIbCgeq8aGDCPt9ssBA7iAue5CEok"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 12));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 3));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "ak9y70F97SarOhisiHpijP0RZALQ3tboal2kex8IRe7M7WfC6WoIdb4bXfgetWkqQNruKK4tcPItbJPQ9TyHzDqhMnpPdZQlGc0jUyiFMaNaXs2jwSNAQDMv6VEd4GGQFHpO9cxHNRGB2Ko0PDlnwA5O0jo2ivY5hdYf88Z6aOMubalXSOBp9DhGW1NZUUByGn8TP4rvtILfjSjkD4pwBSlMeA1P8DseOkjAx7mLC3xvvlMmWOXJ4zIha2820Z3xL"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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
