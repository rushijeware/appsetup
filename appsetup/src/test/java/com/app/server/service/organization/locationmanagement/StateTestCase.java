package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.State;
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
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    /**
     * StateRepository Variable
     */
    @Autowired
    private StateRepository<State> stateRepository;

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

    private State createState(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCurrencySymbol("eZcRZ2TfeVf1hQLAGX7ZsTAaNnDn57UV");
        country.setCountryCode1("R8b");
        country.setCountryFlag("tZayM7mw3Xjj9KHRwQ92y6U0bp0UE6WMT7WWiJdKb34Ogvj6fO");
        country.setCurrencyName("rp4W1qTSKjpbFRmnOqjjeCAX6kVzvYAMoGN1XTSKs5Tzh2331W");
        country.setCapital("q7mK3z5lAI60CkKe1VUvXGeQZTgJgY9k");
        country.setIsoNumeric(994);
        country.setCurrencyCode("HmW");
        country.setCapitalLongitude(9);
        country.setCapitalLatitude(7);
        country.setCountryName("xruvhBwNrALTZKoFDP4VyCzu3SjK5ISwpw4Fq4qGfLPkjmF1sP");
        country.setCountryCode2("CY0");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCapitalLongitude(4);
        state.setStateCapital("zDDbgYhK0VLkZGLGTTi6OiKCtyeMuRdJN3K8DbV6ii6XTQeVmi");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateCodeChar2("nAL9t2RDVZgltfPZlHhxg40olKelAE2n");
        state.setStateFlag("WF62YqLVuso4ChAwoZTEO2NaJuyA3Q4eTlRmDvqLbbYc0etq9e");
        state.setStateDescription("D94hF4M42iPMBVy7FQleEdYtCXjDIbdm3GfhQ1qZVQjLPgWiVH");
        state.setStateCode(1);
        state.setStateCapitalLatitude(3);
        state.setStateName("efwwFqPjySHJsOk22kVEVHLQUzIhlFNXc9jhon02M5kL7j54Jj");
        state.setStateCodeChar3("zTYnfxzgOnGQMZcxzFNDF6S4YZMxOklU");
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setStateCapitalLongitude(10);
            state.setStateCapital("sy2Ka6jAbADoqszvefFoC4tN4JYoxDaV6ZrfMYytV2zClOyLKi");
            state.setStateCodeChar2("ML2iK42JpjVk9QCjgJdHfUicY1tecXiV");
            state.setVersionId(1);
            state.setStateFlag("TX8eeWGivmnNCb4pazyNhRrOoIqHYJV8Sl9nGzJAwmzhQ5kMJv");
            state.setStateDescription("EKXEOsnlGPX6Z7ii509JBtoOaR5AFicobAxvvKlL8B7SOvN12D");
            state.setStateCode(1);
            state.setStateCapitalLatitude(6);
            state.setStateName("WwsgHEerzwmbpSDKOeZ78pXG6H8Bct91u627l9uqNffzk5TrIs");
            state.setStateCodeChar3("duKzho9qYpCKBVvOdbYWz7Rsi0vF2D2t");
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "bSPyAw151MLlEMeKJQsUb1VfK9mdTD5CxfpoYpBrxbbDCoFSAENK3KwV9FVEW3OmphiW7RrRVpgaMKlIqjXv7Tl5QCmT3qAcISQIFjS5vEClT5g4XNGGtTm3aE1rll4zJTX3ZiZdZwfmKnv31BYneapR5lkFNNThYnGMsTNSED9hdnajCFcDnj9I13F2bpJVpT1T2hOEwENpQE1iToQvm5wgALQoh15XFxhxFsmwe9A7eafIEgJn64Gztj9grZ5LC"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 3));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "7EHdi05ycqRUGLvvPpoObutIjPPzCMQq9"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "9w8g3uUcY1qTA8dgPyEjgERME8JOMMfBA"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "vYfwkDpX7IHxcBZZwfWfcOwPmihWCRqfC4amPaFhcavNTR4t2nTpG3aq9ZNkXw9got6DyZEfXAINwXFT8Uxs8AAueDhL0edI0FfJDU3bQiZX05i5j8OvWEC7sKELMr8Y1gB5caf5hVEXbI6rn5Du6x20YiL3dj4Kgd7yFp1vse0kmDUrsvpryF4cNtIuUunEoDfNiwfFwFKpGeivTlGBXuDTXGYhN0i1UNv8Q3x5AWkPHXyVrQbjRhMnv0y5iguOy"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "PtijK7ZUthkNhIjY2ZMwJ94kWuT1WsFhTApTIWXAeSdp96XcLPHaikSFiJ68ce3em3GINfnx7JF2tV6m5DEZDz9fmqZMr4t1hNXHcnbj7l3CxCyyYWpqwGHGipdXyahHO"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "eZo6yzMjFrIPi0mADXHH2ZnQDALHz2Ih99ELWHKplvMGDetkoCcN7uikdl8CZuy9NcoNjNIbwuhD37ZX6IFrEctd5QMIiaCV29tmBOiiBNpqIxLLw6wdpEUWsYQNL0tLr"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 22));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 13));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = state.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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
