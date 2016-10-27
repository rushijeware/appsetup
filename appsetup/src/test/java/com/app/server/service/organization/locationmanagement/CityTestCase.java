package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.City;
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
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CityTestCase extends EntityTestCriteria {

    /**
     * CityRepository Variable
     */
    @Autowired
    private CityRepository<City> cityRepository;

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

    private City createCity(Boolean isSave) throws Exception {
        State state = new State();
        state.setStateCapitalLongitude(11);
        state.setStateCapital("jlTH3AlyrXnmimIlpQgMZZVILJ6baG8Qg6xpHN5zh01l7O5Cc1");
        Country country = new Country();
        country.setCurrencySymbol("Yd7Rfg63xnE0UTy6v5Hbl5NpIsuBSW7a");
        country.setCountryCode1("BKm");
        country.setCountryFlag("AVJ29QXykXlqrXwVTSMpoNzvBB99U9NO1obBJ6TnDSoWRKyUOA");
        country.setCurrencyName("a9FiSj1V6vASuQAcitazEpgeIPQd4cdwtb1caMqCvY0GgQIy2I");
        country.setCapital("AykOK2ScpkAhLp9vILuCp9gRtBAoXdrN");
        country.setIsoNumeric(225);
        country.setCurrencyCode("FI0");
        country.setCapitalLongitude(9);
        country.setCapitalLatitude(8);
        country.setCountryName("JBQdpKyvwYrGNxyRIKzmBYJnDL4PeF9mdzav9j65tv4uRUt6jC");
        country.setCountryCode2("HRp");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCapitalLongitude(8);
        state.setStateCapital("6UefsWjbX37Z1zA3pwAB0Ua30js0cAcYGasE8HgJP2vopV1bQw");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("m4awTrWfLH8agj3oFgwRTbcBYUUWmkX1");
        state.setStateFlag("ZI1nGT1RPYCMhfp46rIjs9I7MxZ3DD0kZlHFggmtRQWqgRFqiv");
        state.setStateDescription("lRyZ0pJQsQUJc5ivllgF5hOlN9LZRWIEiicc8rlRUGJFUBXEsV");
        state.setStateCode(2);
        state.setStateCapitalLatitude(8);
        state.setStateName("PAxVCDLAZyMbI6xHyyuc4YZEKylRDc3HqCtWWsNtd8AMu7bg0J");
        state.setStateCodeChar3("PUVLmCsZzEBgOmB2DOQu60mEHXT6rZEm");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityFlag("Y1T6U61fkKcC18bxahG990n3Ib8AoQOEvGALP0D2yvafE0vgUC");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        city.setCityDescription("jzUN3C0L1Hxq4BTjEQ28rqtbRq9f4fCKgw9mRmPQCjLFamHhgL");
        city.setCityCode(3);
        city.setCityLongitude(3);
        city.setCityName("2ZjeOo6fIhWIkmmsXpCI2hEDdk1QT4B44DYeDEtXFxid0OZPQy");
        city.setCityLatitude(9);
        city.setCityCodeChar2("2m5CSNNbUQ4bo6vPr6TMkecf8raFPpAt");
        city.setEntityValidator(entityValidator);
        return city;
    }

    @Test
    public void test1Save() {
        try {
            City city = createCity(true);
            city.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            city.isValid();
            cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("CityPrimaryKey"));
            City city = cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
            city.setVersionId(1);
            city.setCityFlag("aDWQV8C7HgBtx8mfX0aXbUDngqAOUmPzmw2Plo35zMv4z9HAj2");
            city.setCityDescription("KI89OZWLASNpU2vGRiutZLwLk0HaVUOAXEtrv4hOABkVHDN5RA");
            city.setCityCode(2);
            city.setCityLongitude(6);
            city.setCityName("KUsr2QEuHCn4uLSdOOPB34HeZ2y9qvAWdZUx3XiLdzY0gwuIxR");
            city.setCityLatitude(4);
            city.setCityCodeChar2("F9li9pfWCUXidbAtdNnWRmYeoeXH2H90");
            city.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            cityRepository.update(city);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<City> listofstateId = cityRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<City> listofcountryId = cityRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
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
            Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateCity(EntityTestCriteria contraints, City city) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            city.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            city.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            city.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            cityRepository.save(city);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "cityName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "cityName", "5zZgwLiuzWoNDqLlk8xi0L8DvAysw58hfPRi5bKQPDYlD08FGD7SqRtKnEgpfKY3X7qvxgpcaJJV7EZPeW4BtIRQPiZmS5KwrJI0It90jq2U0Xz5q1PVCQ6gzG4D7o4U4E2HJAIYVwSpnQeSgWSwc8u8322HXcPlrbcD3qy6CdDpe2tJwkWXjuU0WGyjdXnZjuaLBXiHSBa5wMh6PZN5mc8jW0flFoGsCgErgMKHrZ4rm7xbD75LWt2xPhKjOKuoE"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "cityCodeChar2", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "cityCodeChar2", "DHlg2igh5IemTyKgf9vRMa8C19if2ssVB"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "cityCode", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "cityCode", 4));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "cityDescription", "vKde0S3W5dRbSi7Rw3qYkNJLD7yPSzyUVLiwAAnEfa21MC6OOw6Qbd2Zr1CEZa47assbl3no3xgxgS1c123r7MwmSiS2HWUMpLs2RPYahjD0C6gvHME8eC3ong9xmK6wE"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "cityFlag", "RpkMvOs9vGIWVWDCtYT6WD8YAZSL04DuBzkGBR53w0zRimUEbl3fmvRUnTDsxkvb43DmHIFHC1W9r1Acd4q5aTFs123B5eVT6WDMQ1p4v4KgSDtmX0Dn0hqXgosAUdziz"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "cityLatitude", 12));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "cityLongitude", 20));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                City city = createCity(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = city.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 2:
                        city.setCityName(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 4:
                        city.setCityCodeChar2(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 6:
                        city.setCityCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 7:
                        city.setCityDescription(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 8:
                        city.setCityFlag(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 9:
                        city.setCityLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 10:
                        city.setCityLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
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
