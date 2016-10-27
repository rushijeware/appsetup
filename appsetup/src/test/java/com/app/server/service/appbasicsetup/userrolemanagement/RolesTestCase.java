package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;
import com.app.shared.appbasicsetup.userrolemanagement.Roles;
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
import com.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class RolesTestCase extends EntityTestCriteria {

    /**
     * RolesRepository Variable
     */
    @Autowired
    private RolesRepository<Roles> rolesRepository;

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

    private Roles createRoles(Boolean isSave) throws Exception {
        Roles roles = new Roles();
        roles.setRoleIcon("BPZQCQ9cb9yHqbv1cGhkqBAbVeQ2NP96Pr21VHdFl9ECdgEttw");
        roles.setRoleHelp("VAfTGs57s5tcZDNWPUgyWMQuVcNAAxgwNV5YVyVVNE8gTchrWW");
        roles.setRoleDescription("LvwbxDCSRn4TOunIXgtBU7bNrDmJGfpuNcHM4heqanRMt1Da8s");
        roles.setRoleName("NDzZRcSNI7sZWY8ad5a5BzrA7CkFqX1JK9Q4Gj6uDoQ2WffJIJ");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        rolemenubridge.setIsExecute(true);
        rolemenubridge.setIsRead(true);
        rolemenubridge.setIsWrite(true);
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuHead(true);
        appmenus.setGoLiveDate(new java.sql.Timestamp(1477391851626l));
        appmenus.setRefObjectId("qHwsVWiTtQSOpbvHm1AMrXZQA2kuiwAJ08QFnOXMwgAgriSIEP");
        appmenus.setMenuCommands("IrEwnqDUa9bKzuW6nLqYrdu6gK8ci4Mf2GNpYARNWqfuBj2hBP");
        appmenus.setAutoSave(true);
        appmenus.setUiType("FUQ");
        appmenus.setMenuAction("MV2FKOmCzA3buNWQgVMqY2OI7glVGunMgofYRIARjQY4nbrrbE");
        appmenus.setMenuIcon("IU7UlgHcSAyQ7Vzf9V8iJAU2VH7OXC90HORsx5rG5hotbqpE55");
        appmenus.setAppId("fvni3ZVm7xcgkrWJKpimGUosxAxkiXMiXBmsxsBqSFmlLJac6Y");
        appmenus.setStartDate(new java.sql.Timestamp(1477391851626l));
        appmenus.setAppType(2);
        appmenus.setMenuDisplay(true);
        appmenus.setMenuLabel("anvY4zbTSznyi7xnvNQ39WgNLJswmbXEKIEBkXqb5fYlEQf1Nq");
        appmenus.setMenuTreeId("n5yY0mPyrihGPb2KLBMvzQarBajvsMGzXB9ll1Ul75rFAphkUJ");
        appmenus.setExpiryDate(new java.sql.Timestamp(1477391851626l));
        appmenus.setMenuAccessRights(4);
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        }
        rolemenubridge.setIsExecute(true);
        rolemenubridge.setIsRead(true);
        rolemenubridge.setIsWrite(true);
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
        rolemenubridge.setRoles(roles);
        listOfRoleMenuBridge.add(rolemenubridge);
        roles.addAllRoleMenuBridge(listOfRoleMenuBridge);
        roles.setEntityValidator(entityValidator);
        return roles;
    }

    @Test
    public void test1Save() {
        try {
            Roles roles = createRoles(true);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            roles.isValid();
            rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("RolesPrimaryKey"));
            Roles roles = rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
            roles.setRoleIcon("l1Wy4M35MwTK9VTj9usWRXqwpmYn7ESZv5jk85wNYKOysQvgZ3");
            roles.setRoleHelp("gSyqMyyx5bOmRyACeooHKYbHE3Yo72GYBztSfHzzbN133UlXts");
            roles.setVersionId(1);
            roles.setRoleDescription("ulKRad0C3yFJYihmjRQIDqqcEVdbZSwI3rimXQCaGHsEYUbFd2");
            roles.setRoleName("fpDtdSyypzGSx8SEuAKDtCyLAlNMgXVoMTcdVgQNZreWCvvcTL");
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            rolesRepository.update(roles);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.delete((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateRoles(EntityTestCriteria contraints, Roles roles) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            roles.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            roles.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            roles.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            rolesRepository.save(roles);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "RoleName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "t7MNxa9hZBXKwlCE2solrh3RN0Bb5LzsiEXb3NC2W5h8saLLAlJE6AGy4N9IebYgqRtrlmF4tMWMB5oOB44bReX2ObL3Zn8bTTXbvTBjAO1AMaLt5tuKzGbEtcnRma22kuMSfPG1EjbodSwcZ0YAcKmGjCYFcq3S6fZAbjahw1IlHxJlZlf6E9KXy1qzWFYruzQKDRHrmAKkbmWuVIvyprNp73KOwEdjkmrch9HyhG0z6yiZR9BIfEsenFXvC0tBz"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "MrfbgVijzoexw9cH4KV97omwY8zVNLqhjTPCwb9uQAisT6XS1MCDjuKIydSBN5M80YvkJEs9ZyTYmFEe54G3aIutNtkslp3cTH8BQgV1RCp9ywgW6FdX6khM2j8yVXNs48lzcN9uHAVN6iT1ZC4rwwHuyvr3ffjg11v8lcx1xORmtvoMjfR0OCJB6iEVuYpsHTVdJttHLyEV1ZbesaNIiJD583cIvXaFwOo2NcmdY0KS1E3HeeyrBil3KUMjg2WCT"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "ex7dpz8TOgci78ym37DywJNx8xQNYUT4vhAGiuH6A6uJIdOVDyeVTDKW2TRdSKcU8"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "542I36ExZT77RWl4hjiRpvxQiH2pMeOSK1pCOiepu6lbon5njo2CjiLnlNv18P2CS0087Q2UQ7AK6hk97T3XNAYgkLqI81LKUCeuyVEt0jnwvLEBMqpn7vnuKYpCA3HdIp3wNK7t7ezO5ElBT91birbJPmhuMt3kixrC798iE2UkAPsSS8mVr26GYtqRUm7bX1HlKb19KvjgIRMNpLKJqOSaPePoosD7vpTozHTlkogj1x9mtfz9QyiF9tn3gOFpS"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Roles roles = createRoles(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = roles.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 2:
                        roles.setRoleName(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 4:
                        roles.setRoleDescription(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 5:
                        roles.setRoleIcon(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 6:
                        roles.setRoleHelp(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
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
