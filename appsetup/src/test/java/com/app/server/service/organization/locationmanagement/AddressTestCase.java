package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.Address;
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
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AddressTestCase extends EntityTestCriteria {

    /**
     * AddressRepository Variable
     */
    @Autowired
    private AddressRepository<Address> addressRepository;

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

    private Address createAddress(Boolean isSave) throws Exception {
        State state = new State();
        state.setStateCapitalLongitude(8);
        state.setStateCapital("sEtO5fNJRdQvZ5PNjmtAbSvfQtDbcldfYP0HHuJ1Q2HHSoQH1F");
        Country country = new Country();
        country.setCurrencySymbol("2PEchAsNPAi8s1XnyFkPoONM3QgasMcP");
        country.setCountryCode1("5sc");
        country.setCountryFlag("e2VhBUjqR5XbScVyXzSqmcdOtkwabL3iXD9ZFMhn3pfXzPehQn");
        country.setCurrencyName("RwiuBlwEeMg7m48Hya6UkMPIsFEcNRrbubvomlyoifL4lGvMN8");
        country.setCapital("iuWGD1RCkjn7pzaJX8AxAQATr84HutYc");
        country.setIsoNumeric(578);
        country.setCurrencyCode("zY5");
        country.setCapitalLongitude(10);
        country.setCapitalLatitude(6);
        country.setCountryName("QB5iDSIxqkUwiNP7xpfpbbkZmh0TBOxz702cefS1s8nKHqXdLB");
        country.setCountryCode2("tGm");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCapitalLongitude(3);
        state.setStateCapital("mWlteI3cMgiiKfMHLfZEyJiav6HBIkYnjEsqdvCkTz0dJXujx6");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("tg65MYOQka2lYjezK4GXoch9otaVovPY");
        state.setStateFlag("TlCzoCaTwHam3YPuLLg6WAqbEXetarcx2HJ0DCdAracByCOZF0");
        state.setStateDescription("adUUH7hOafi0bSwP3c1MayN6C5lpIe1FhlnqL5c12SWG9yZAp8");
        state.setStateCode(1);
        state.setStateCapitalLatitude(5);
        state.setStateName("nKmYOdgwo0lM3m1YzYShdJYA8k7JfZ2Fqco8tNbskhWER8XGeY");
        state.setStateCodeChar3("HkGMphPXZSzFERaSmxCzsRLKx6Kle0xY");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityFlag("sixkrItjD4M9qweUt3Pl5edh2ieSEXEr9XjkcF8umRJkAPxkTD");
        city.setCityFlag("UQteO8nO1XVvda9lYt0OpSwB0TneEiuM2KUJHPGUdVkAn1SVgj");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("P9UlvRkXpbTDV9a0DZwoU0cQUpIvfZqe1Irxk0yxJNKJnvLGMd");
        city.setCityCode(3);
        city.setCityLongitude(2);
        city.setCityName("3BblaVhkiBsTF5YrtWJiW8GptnAotlsFBuVu7bYQsRGeJfzKIj");
        city.setCityLatitude(5);
        city.setCityCodeChar2("x0bFA3h1rCvoSPU0uhnF1nuuibHt8r6V");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("VOcdw72hOj3AJz83wensc8xd85GAapgjJmZrvibB3AMTyTUBVS");
        addresstype.setAddressType("eHWHG48Poj6Ze3R8hhJ93CW5CMvQqa2JMTR4rYbC9BdiC5SfiV");
        addresstype.setAddressTypeDesc("C717SLmtyED1oS95jZOuRiGD86zeXgf5qMNxsIBud5ty4ccIdM");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        Address address = new Address();
        address.setAddress2("h2BGlWBGXg8MGsLwbydFINY66rjHV6RrJWVSFM9a4UxolkJuUI");
        address.setAddressLabel("dvXGoMcHtGQ");
        address.setLongitude("isegoANgsz34sr4v03egHlQbI4fj6Cu53hiC15XNsrTZIqlIct");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("sbwl1NyyrbC0UQa9n8M4DTiEK7qX7OFcxPnKiBEunUQgMHygGS");
        address.setLatitude("mnKVyhVRFoUDW3hSrl8U8NKrEdgMWf4Mx9RjlEAgf8hGVQKDBu");
        address.setZipcode("TFSEjf");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey());
        address.setAddress3("FT5EHNALipcn6IYdbcAwX6lfe6EGPs3xnStVXtrOZQDrqQE5oC");
        address.setEntityValidator(entityValidator);
        return address;
    }

    @Test
    public void test1Save() {
        try {
            Address address = createAddress(true);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            address.isValid();
            addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setAddress2("ravcgOwqhtxVZyq1xGl1af1Q6GUPIybIyo800Zlsgixier30Qe");
            address.setAddressLabel("E0U7d42lcAx");
            address.setVersionId(1);
            address.setLongitude("48v6R8eUsGn0O7fmqVGP0grnIgmZU6GnWsiOk2oGYhgHyz8ztp");
            address.setAddress1("k93ashCNq7krxrzKZsN7tWNtpM7YSSSy2fVCpTt45lyJj9tpss");
            address.setLatitude("mZWlAZOYOyu5jczzbazbIMoiLHwVI6sr8Whdfu8LxXeYzQMnRc");
            address.setZipcode("ZfTaHz");
            address.setAddress3("YkrfCgcA5cl8ZjDKxXM6IfqOGCPxFb9DU3NBUdCGjPkakIymwi");
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Address> listofstateId = addressRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycityId() {
        try {
            java.util.List<Address> listofcityId = addressRepository.findByCityId((java.lang.String) map.get("CityPrimaryKey"));
            if (listofcityId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Address> listofcountryId = addressRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
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
            Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByaddressTypeId() {
        try {
            java.util.List<Address> listofaddressTypeId = addressRepository.findByAddressTypeId((java.lang.String) map.get("AddressTypePrimaryKey"));
            if (listofaddressTypeId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateAddress(EntityTestCriteria contraints, Address address) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            address.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            address.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            address.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressRepository.save(address);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "LpNxAFI1nhD4"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "VWilUA8ieyTIyfLmoDTmV1wD7BitTY3ctE52iwLku6CdQjEqxSsoTKsbM"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "R6X46RhpKCrOmZyMv8yWYVPayOOpi9ySz1cXxxiizocf0n3ycGf7z8HVb"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "8kVucA7WDnwuv0RE2ZbqRHPnKWRbn3PYjY4NOobZdeJPrnEqyWeDfX4LP"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "4yFapZu"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "fsRHrrCWQc2LHxl1XdoBTIxjsdH1H3HyKfMk1n5cX122rBPQtHUupNctCzrdKfXkV"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "T92qfxqLrgWrd54b8Palvr7Fs2b9tYt9aV7uOFmpwKCAc2ZRGHCduMMsGcTjGZg4Y"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Address address = createAddress(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = address.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        address.setAddressLabel(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 2:
                        address.setAddress1(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 3:
                        address.setAddress2(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 4:
                        address.setAddress3(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(address, null);
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 6:
                        address.setZipcode(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 7:
                        address.setLatitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 8:
                        address.setLongitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
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
