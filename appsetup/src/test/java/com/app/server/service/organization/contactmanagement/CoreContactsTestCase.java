package com.app.server.service.organization.contactmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.app.shared.organization.contactmanagement.CoreContacts;
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
import com.app.shared.organization.locationmanagement.Timezone;
import com.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.app.shared.organization.contactmanagement.Title;
import com.app.server.repository.organization.contactmanagement.TitleRepository;
import com.app.shared.organization.locationmanagement.Language;
import com.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.app.shared.organization.contactmanagement.Gender;
import com.app.server.repository.organization.contactmanagement.GenderRepository;
import com.app.shared.organization.locationmanagement.Address;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
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
public class CoreContactsTestCase extends EntityTestCriteria {

    /**
     * CoreContactsRepository Variable
     */
    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws Exception {
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("anwHAqjIEoKpNixuAeO2agonjqQ74pVwUAHgLPme8ruujeuuek");
        timezone.setUtcdifference(7);
        timezone.setCities("YgmFoHtmoc8N3xKewL0CNvmgOwIO576lFTRENhyVoXugkXg9zg");
        timezone.setGmtLabel("ioOTDHqSA7BDt4BPRrCPRWWpPqsA5B0b86cKzZl2Mah2N1EzbL");
        timezone.setCountry("oJXJjd5O5QkplNQ6NZmSorQrEO8m0qMXm0eEQZCjg1ZqcknruO");
        Title title = new Title();
        title.setTitles("v9bpMdirQHDbyq1iogAv9Cmde9x5HFiYKbm2atvpAI4nRTTy5w");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Language language = new Language();
        language.setLanguageDescription("2bJpugwh0Wz8brdQe6XYiybByBMXWfEoNlFz90yseWMGWBe3sj");
        language.setAlpha4("NF5l");
        language.setLanguageType("5mSFwRIwfeN6C91HPrue9jDuF9dWwzDz");
        language.setLanguageIcon("Db2vTiy9Filu2JpmAewleum9Lxx6JBRlMmME9j1UWAR48m4YOX");
        language.setAlpha4parentid(5);
        language.setLanguage("o39Omg6Q63YG5U8lKm4QCchssmGnhLy3Lmgmu1Uur5CnPX6Atf");
        language.setAlpha2("6L");
        language.setAlpha3("x01");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("2qCH2zXrbT7SU7Ox7jKjhHqj7vBAykiOoF8OKG7by35AzlRE0P");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setMiddleName("ZqCjjc24L32lIEBabjmnv1Up5XyP2qjJ3FonpwTSw5EJ9MtXvm");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1477391839170l));
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setNativeTitle("0E300ZFybmjMmV1IYrxUL3xLMI5hUiMvZYvKddh4XzIoUEn9KO");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setAge(80);
        corecontacts.setPhoneNumber("gejMsYLYlMRslUFVlgmH");
        corecontacts.setEmailId("LKX9gVAArg6T6rqWGZWW8kKTfGWAWKN6JJC3u3ggXda23GKhum");
        corecontacts.setNativeFirstName("onlkMcWKOLGlEU0Ffz1u49VJZOgYZ2aP1uL6IJsQvwaVQj42xc");
        corecontacts.setLastName("vPbjACLYQu8ZuROHwvE63mFWOpC7He4DKzCMLBWDzw77qUjCoO");
        corecontacts.setNativeLastName("INkRwMgScMgbXNrSEgymA8AlMni4SO0ZxM4twjzcRo5TBgtX98");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1477391839327l));
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeMiddleName("p9utRCQ8ZDTzl3BEiriLymVkApIG4mNbDHODTnCLf20UJHn8gQ");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setFirstName("rYoBShkoC6t5LCnl7Q4iIBOTeWPoToymiHSE3OpaJCw9JKTvaO");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddress2("5AikliNhh7opFH4tR28q2VHR0HxrwD8ASPiz3GVAdalD8I2iMg");
        address.setAddressLabel("H9bMwIa5oH2");
        address.setLongitude("gi8QocGZIrUbb8EWBbf6UUQb0F6EhAOlH2dVX9nZeK8M7esfU2");
        State state = new State();
        state.setStateCapitalLongitude(1);
        state.setStateCapital("NEjXEyFqssPYZo9cEMeQwJHDKRUafG2V3yNPDHbM2xQMjiJ91B");
        Country country = new Country();
        country.setCurrencySymbol("51LYWJeXOxUtAVWWBlppQiOrTT88J4ZN");
        country.setCountryCode1("Brx");
        country.setCountryFlag("WPjmp6UCny7HKv9FvPvYzorIVDZ8hOB0aOjYyGctWhVuIy5HLw");
        country.setCurrencyName("xvri6cWfFxZ5sC4BvGhEHAcGTTPNwjip9XeDizoYB5cGg7s6jn");
        country.setCapital("bGTNejFntaLVmoC0n5Bi7DZUbbNoCmmw");
        country.setIsoNumeric(653);
        country.setCurrencyCode("lbw");
        country.setCapitalLongitude(1);
        country.setCapitalLatitude(8);
        country.setCountryName("LExIcrJBMOhRWb33l70RpPHoPHvZ84dgENfD3jHp7iSsxaNpi2");
        country.setCountryCode2("i4B");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCapitalLongitude(11);
        state.setStateCapital("wDvjYUvnHumcGXGDUz05RWma53GdpxYOKs53Jo4Snp1qGvLtva");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("954e1ufBxAhevEcaQ2GthY3rdtdTIxM0");
        state.setStateFlag("8L5KbSSR3ADOElK52OIYxlFPzVRu3Aqf5YTLQp4spXZVY55OUh");
        state.setStateDescription("64dibN7TGwDPNvhJp9McilQKJERrp2iDeVf0otMZrf6I5ZlEFc");
        state.setStateCode(2);
        state.setStateCapitalLatitude(8);
        state.setStateName("yjhAYw0yzjciJCCTN5SSLUAF6CRvtATBlqVtIlMIcYuNUl7T9i");
        state.setStateCodeChar3("zVD2lRxlfh8DsR9YfZE6v9kTXR4wfgXP");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityFlag("WNjNSpLgihqIT94QTtP22bXQ1fYzfSPuwuKOIGsvnuANale2rC");
        city.setCityFlag("XtlA0QewjSpZbF9zXemMQCGGiAP017z4zgXn1myo65TxbWbVnv");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("2TtXhnreVF26jKJp81hrmtjX0AdAdWbAazIRBmS16qkEFBLHCy");
        city.setCityCode(2);
        city.setCityLongitude(4);
        city.setCityName("WEiCFCvPvDK3on1cD850yR9Rb8AJmlKh3WT50UQMHuOZYqBBjJ");
        city.setCityLatitude(8);
        city.setCityCodeChar2("nw08N4eb7lNALJOsCMNNdVmICKyCCdd1");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("VmwxJo4tmAASFlx31IYhKrVBV1P8wfqvqwkSKaA310tvfK7feJ");
        addresstype.setAddressType("8dfjYBwjKbym8ONCoIvuzjzY3sULc7XTBnyf7EXoYdBhwyjfp7");
        addresstype.setAddressTypeDesc("od4MjBTFkl9PKvlEvcxusXmiAvtxX1UQsez041JvIM5IdbQoR1");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setAddress2("qPQ5UzH3pA6Jl80XEfCuVVGazWrzJhx122HunAIH5n8I0zDMrX");
        address.setAddressLabel("oIuOsWwbYKI");
        address.setLongitude("NE96uUmZEqEzTdHaNatanL1boejEjSETmB5a9aUhxx4Rcgqey3");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("GJTM31g3fMjKR38WvjQXRBw8zl0hGE7pARkvexjBathyMXwdCk");
        address.setLatitude("SasAwx9zhE3EcR1qyBbbwEm660R9u0KrDm79FDw2PurVTW1n0n");
        address.setZipcode("FN9VD5");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey());
        address.setAddress3("M4A4LsDlxxvKCLw6kYrmL5zfAfx7TVZonrR4szu4qUAoIeqMyF");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

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
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setVersionId(1);
            corecontacts.setMiddleName("10Mpnly8x1OCF061ihHW1GAgKzdKJKCVDfuvahsKndhTzCoAIy");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1477391840084l));
            corecontacts.setNativeTitle("EZ84oN50otAT6vOHgbrIrgBEh4Vwk7VL3Sd8pNSFbxlG7yf6Ub");
            corecontacts.setAge(106);
            corecontacts.setPhoneNumber("vHlSQyjBwl0M4JMUFgkm");
            corecontacts.setEmailId("m6JT9OOXMrqKABXJvzXIO9Dfjr8P64BeS50Nr4jPCwB6hqeS1F");
            corecontacts.setNativeFirstName("tIx4rYPjtuDOwzM6ktTWYOlfbiBwi70eGcmgO9ptOnYbbVQ32F");
            corecontacts.setLastName("UXkMAGBm0XHThv4w7VwuSudTj7EAEPjyCg0Ihp8mZ0o5ycn05Y");
            corecontacts.setNativeLastName("xR1v6lltzWsWZepGUl338cwzar25WiCDbUQ0WAuiZIZauHhYLi");
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1477391840321l));
            corecontacts.setNativeMiddleName("Cynygj9IhIeWG3z4xSraZCaVcRN2YjQg7G367NfF4tr3Z74LNR");
            corecontacts.setFirstName("oDvuvFGrSf0Ur74vtQWWNJWz73PSP1mSKAuFpHg11M9nuKY64r");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "tvUPCAJe63iXYBsD0UCH7L1AJhSQ8dqSiTz5NJXdEq9QMBh21EVip5lloJ45lXv3HIYtgPESXKYokvmmvekfLxj7uXazqFTMur9iCdyusC2sxrjEidHxmBhD7A8DyzFgfbvFCwvfjV4PffS5Enib1OWd8vpVeXiSKQ5vt4Ad7uXcgGns3dL7EMGVRRsJ2SQCPVOLlGbuoNItDcqyHG0TAJn3bPHXFYPLeG8J2GdkqDxTmtNWPbKur1wYAmBNTlYUk"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "P55fsd0gEudHHi56fK9ym6x1zVtdVKQ5ZdGKx3dQInTjkqFGsVlcvFpaCH5r8Xv6HDzz3BhhLz8Lk9Ig9NjwBP8hcaXYypld7fnqNtMiHb1vLBU4tIB7ip9gwk3IisuZFzh8CmYnjewxErXd9cQr0FBkfTnE87xmX9tx1NTEbcU6vFcRKm7eL0eKg7hOy3QTsyqh1acbE9Uo2JflEviVEQsUleMLcGZJFKvHsVxOf8alnFIPisz6BX6m3Q46goxUW"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "SIYRLbzY8ZcUHmHPirrjkFnrsD6HOYK7KQpWaKCoqO7ANbPGNUDqMDGERAKT7cnpKDBTGws48elPnYrIDQe6BazdLEpIr6f022XqkCAHKXk5erfpK5Fm1k0saJEo0gUe5wOERk4OTkha12T23LrZ9rxsyMal4aQ0HjtjtkJiKSKZD3rCxQfrz1PSjoGgs3P6sl1c6AeKBR4edgp09ydDAGQT1o8791pP4HA8twbmUAeCgX5fJB95OdJab31ErG1vK"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "z9oovPJG4ZCMOe6dXaaXEOP4p79AKMLP9KPsWY08zmbqkypj93q4cwduan2piYA2I"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "IFw9YiPKe6o6m5Hu5GWV94pRX8QI0K3DhvuS9qfqihcI4XzOjFdNbsdX0qxqeoeOKnUJnmUvnxRWrHyK01hKCpkNOEBduYeAhfscnHXY8g7RRJKwTkxY0ZKU1oT5vC6swD3kVYrigCtzxUcncvVMVtWck4N39e50kWjrzDWXEZ8Tem5WXwvVdJsCFfbTz6FF1kA3F1WuGucpe65Ffv2NEIntmloyvjCcTRg3aSzeBttjxT5bMmNSSvgza7qzfLNOt"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "DlxWtdS1IVeLahTvX8OPIZWIvYLnRdyK21YvsaGVhrcWwtcAsLVnGV9klNsRuQ40vuOh0J7hG7Bk5dnktm41vAGS8NQJdqC7LvrmiBqugvvFqlij5KcfRGVD7wQ6f4raSthRei9FErFgIlV6ocLGiD8hcuIqyd4BtdewFyNJitXYlIHQUGewEdXOmecq3NE0tUPueHml5ZtzmpyYXjKGsAQp1SLezloS0jrYk1PwS64gptCTLJAKsxZk2QXOTOw1D"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "OkkqEb9pIuyFabwJsIhdZwihSwH9e12V0CBMj37TN9tis4YZ3q2uX7ZdCgT8JVD83z2zOQRsQZs8XYiNgEzeLsixAW5WizuA7lvsqWkH9fDyUgZL2pAO3ZdhzaYJcE8fjbSaEExvsYaoe0BIFLR0QClvXw4Of4p7vzhwchUrvNPhkxhwPe0LOKN8algaaDFlcqkLBTGDe8eRNYRM1zz6YAcB5v1B2PlvfspIDwtQ3nz0TrYrswmUXs56ykvM5kCHY"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 158));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "VaR1VaejkgY4vuJmhuCTH72bmosF15FrQ4bKI5JO4jUqmwC15Y8h9fjlkewVtb8a7aRoTXZ6amcYluR8kqh5F40RdzHY1YcyCc570MCilH3DyXAVERMyOLDt1KN8LpkA9LKsFwLuhK4mvdjtUBEIHAJbScpay6TgQRyMwIY4uxqpSrA4cxYt1g1eyNyXX0eKe9b5jrl92wL4sMR8YXYdoznlnOdpnCSC8zWuWuSMH6m54jeGsoxZu2VuEUtM0TT7s"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "ayLDA5V4zMKIlg0fgxPAj"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
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
