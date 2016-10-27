package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import com.app.shared.appbasicsetup.usermanagement.Login;
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
import com.app.shared.appbasicsetup.usermanagement.UserDetail;
import com.app.server.repository.appbasicsetup.usermanagement.UserDetailRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.app.shared.appbasicsetup.usermanagement.Question;
import com.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.app.shared.appbasicsetup.usermanagement.UserData;
import com.app.shared.organization.contactmanagement.CoreContacts;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
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
public class LoginTestCase extends EntityTestCriteria {

    /**
     * LoginRepository Variable
     */
    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws Exception {
        UserDetail userdetail = new UserDetail();
        userdetail.setPasswordExpiryDate(new java.sql.Timestamp(1477391847159l));
        userdetail.setMultiFactorAuthEnabled(1);
        userdetail.setChangePasswordNextLogin(1);
        userdetail.setPasswordAlgo("6yngGlujcKalCeYHgvTzaoHxtER3r6KaumyJGpszIh7pxdk1d6");
        userdetail.setAllowMultipleLogin(1);
        userdetail.setIsLocked(1);
        userdetail.setSessionTimeout(1094);
        userdetail.setGenTempOneTimePassword(1);
        userdetail.setLastPasswordChangeDate(new java.sql.Timestamp(1477391847160l));
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("nBUAHe7xDQQiB7Ta0iEbkqQFbMs4eF9fIyYhuoxkGYhjBi2F4T");
        useraccesslevel.setLevelHelp("XhD1xMHssptWs1uKFQnHM4tZsEUiXWTDalVONq6uBvmf97qzEK");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelIcon("2hSrBgTzVc78WyBVr1ta4VhTvTzs9qJMQ99vr3GxCOindmRqDv");
        useraccesslevel.setLevelName("g8uq2MeNRsTHHySmcTWeUEThfIwFSHIsLxMAqgOXEIo8WZQSNH");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainName("jOZ1WiRNpTfdlIvhyTN2EuAPsUVb0gmKvbOFo1BAbX0XKubDZv");
        useraccessdomain.setDomainIcon("M1RTYk53UMxFi3G3HUMfcVpPe35H7YHW48dwEKdnW04VAXH90X");
        useraccessdomain.setDomainHelp("hPn33c5aZsyl7zpaW0VliflrbznfGlvrcM163sJNHW6SInMWHA");
        useraccessdomain.setDomainDescription("xm3bbwvyOdjNxLJalrMDEZj7VWs5x6fYrzeDSzFI6WVQYYl1mx");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        userdetail.setPasswordExpiryDate(new java.sql.Timestamp(1477391847211l));
        userdetail.setMultiFactorAuthEnabled(1);
        userdetail.setChangePasswordNextLogin(1);
        userdetail.setPasswordAlgo("ESeJgTKyTVLl5a64xpg5i364NzthneCO8KYLjQTV3iB6pOwU5D");
        userdetail.setAllowMultipleLogin(1);
        userdetail.setIsLocked(1);
        userdetail.setSessionTimeout(1366);
        userdetail.setGenTempOneTimePassword(1);
        userdetail.setLastPasswordChangeDate(new java.sql.Timestamp(1477391847212l));
        userdetail.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        userdetail.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        userdetail.setUserAccessCode(2075);
        userdetail.setIsDeleted(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        passrecovery.setAnswer("mR0n3bIFRFMDmlhQn0mVvqAgoM33Yj9LXKjS6Tfrr4EsVoHZ7c");
        Question question = new Question();
        question.setLevelid(10);
        question.setQuestionDetails("95eyGuwGt7gyswyrN1N5752JCXNUKGocKHYAYldIwO3qVhSelm");
        question.setQuestion("Pfmkl7lE0OJMjmekLGbiC5hiQpj69UGl1g9zViinWCr7DiRjyO");
        question.setQuestionIcon("XPROy1mrmhh2iKQrq8CTYltn6NwBpFCGPJgghMt5F86jC0O3jj");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setAnswer("fPwtit5pPtX3VypzzH9KYQ5RrESeqTAnPmYVPlrXUYxL8G6Jsg");
        passrecovery.setUserDetail(userdetail);
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfPassRecovery.add(passrecovery);
        userdetail.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1477391847699l));
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1477391847746l));
        userdata.setUserDetail(userdetail);
        userdata.setOneTimePassword("2CMUk5et6EegQCeQv0gQtzYsaCheIjui");
        userdata.setOneTimePasswordExpiry(5);
        userdata.setPassword("XzjbSdhl5ep1yVgjAvn5PRzKIJdGoTDsdrYXMYG24KC5INPsEt");
        userdata.setLast5Passwords("ngltxAoLCBfwqipLhEyoHwzbMIhu69VViUR9QuvWH7QmddC3qp");
        userdetail.setUserData(userdata);
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setMiddleName("hGsaRfq36FBEnddyijmg3XRaW6OFqjHbE3GzDDaGzDh8gHaGso");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1477391847990l));
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("JoifWvbQDpyuO9aLdNCQtvAeBKCySwQjgttWy3bhkzLltuhU58");
        timezone.setUtcdifference(2);
        timezone.setCities("LbEUmxbr2AYXVaX0jDPFCUaxnvpoNTFpYj0pkcJikpTN2SVuXz");
        timezone.setGmtLabel("iZiofszhwPsURQcqFqgYdcIVROD8ReZmwWmbHYUeB0cyysrwXO");
        timezone.setCountry("Gdlq5gzVowt3vXywHrOWSS15s3RkMg1Frif2vYHLoo3CuNz1r9");
        Title title = new Title();
        title.setTitles("vpuBEBANgQyGmV1Wa4IVBTKqpSLuS3DVXWaP4cvnmsg80Uddh4");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Language language = new Language();
        language.setLanguageDescription("Zlkpgb8stjrGnI8nf0msgRlJKslxIqtCtsr5wS6pnGu2twO3YC");
        language.setAlpha4("oRhO");
        language.setLanguageType("tZFQVzky9hKW0xhm7XIjeWhVdP2RpOb7");
        language.setLanguageIcon("E7MS9pruroKHmcI2MtOJg6lLxFkcNbNaOyvRC0GiSnKqfHjl73");
        language.setAlpha4parentid(5);
        language.setLanguage("7WAx0LYN18tP5LHu1ABeePPcQjMTuHsHrdRGg9yAlMrdKs8jMR");
        language.setAlpha2("aq");
        language.setAlpha3("YyQ");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("pxn86tFjYL7M6DTN02TO2kc2iOQzz4hZOJqwexyhK5VFgmr92H");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        corecontacts.setMiddleName("f7P9ptNfOAS7wBFEgW7vXfAvi96o4MyIXKFT1tAFGlFB1cgD4D");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1477391848036l));
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setNativeTitle("WNlr3iY30xOoT7XNV0TRmTKqPtrLSpE9XE8ds2QNFs84E6EmTF");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setAge(33);
        corecontacts.setPhoneNumber("yXERUophDIA8lWxg75Wl");
        corecontacts.setEmailId("U2WxaqXGoCcj4aFQ1MQabkDsy8SdyFBSuHUs6EXxu1VzfkGgZ7");
        corecontacts.setNativeFirstName("aQC6xYKsHTg9G3L4gC362xGeoG21lfpGGhbMByY2luojDuNo9W");
        corecontacts.setLastName("RbTLhrJ9YfBYZWcedefaM3DWt88bKvARfWIdNQMsnPXx6K16H7");
        corecontacts.setNativeLastName("fSuc51eoYbaZdx6RfJhsRly7Dq8Sq8qLAEtYeq4RrRMwt6MpB0");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1477391848170l));
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeMiddleName("YseMCBWqdhI2XmZOksI6E6hEAE3Fn9U5BSkj3ShYFHUxHo8s3K");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setFirstName("i4RSLwaMx6C4wW0bVUVz1XVbgJdSd9pIttIF6DXAhMCCFuiV57");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddress2("HVFF3gsKDiVnk33jxwt0AW9MHCzXdtZW5DUMlrE56Y8fqAvXe7");
        address.setAddressLabel("MrQVGh9v0U9");
        address.setLongitude("VMea8AVb67aUmsJ4b9FtIr9aSUom5qHlS52Wn4Sk452Ewroxhl");
        State state = new State();
        state.setStateCapitalLongitude(7);
        state.setStateCapital("WINkAM7owrmbJx3RN89hFjw8haPmhDRo60bAKWiPJLa4Gt3Fn9");
        Country country = new Country();
        country.setCurrencySymbol("qLnSRnvKb4l6FEbrkQNoiruW4sGgKLLJ");
        country.setCountryCode1("4eb");
        country.setCountryFlag("zkJlfJvYVWQwwvbFUm6LloXOfO1pVrHz53NehpOLIy2iIMuH5i");
        country.setCurrencyName("oXHg6Ks9QSYWFqWkCa3Ak69OuuHXcfbv1ApvVgrjPvosVrUyrE");
        country.setCapital("YZRn0mxnBecETJOnbNEkksM0p01NBAUa");
        country.setIsoNumeric(834);
        country.setCurrencyCode("PIz");
        country.setCapitalLongitude(2);
        country.setCapitalLatitude(3);
        country.setCountryName("DvdwbDqmqiNJ07GfUiwRP5MeBsWE2cJ2U4S2qVl1XXKfdaQWWE");
        country.setCountryCode2("X9K");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCapitalLongitude(1);
        state.setStateCapital("5UxTz4nUl12iht1s6ELOfshkKnWCfFA0b62BcXls8kQlfRLLO2");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("xcMPm1xmbIjVmf7vMLJgwGnMbn0tRetc");
        state.setStateFlag("FufgBiekuaNbp92X5xyaBQ67sQ57Gqm9zZpmJPTVdgAOuJb6Rb");
        state.setStateDescription("uFZfvnzsylRp33DKj3ZbP9ZHXv86FHjfveil80dsDCTw8mMMJJ");
        state.setStateCode(1);
        state.setStateCapitalLatitude(1);
        state.setStateName("Qpoou9CfrGwM6kl4t5fyzeBAeJHqHLU4lDVGQQ5BYJFitq2wh6");
        state.setStateCodeChar3("7XrEEQlZfAWYCloSW9Mi6EpR1DFd6pG8");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityFlag("IHdeE0lbBf22N2hDkPtuF5oj1U7QhoH2Si23ZmWZiZlbAwFd6C");
        city.setCityFlag("JIytNjEYHph9iDsXyctcF0yr7DLSfMDxSqtCkfHxcGKHeTyIrW");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("yowRu2PwhoXlQ67XenFy3HlBkvQxdn2dUxslmqTIk3Shetsr7b");
        city.setCityCode(2);
        city.setCityLongitude(5);
        city.setCityName("4J7Tp4Do0SPmAB1NaLkewOKP0KanXeemT9i9OO5RiNijzMdrJl");
        city.setCityLatitude(9);
        city.setCityCodeChar2("lepCdwfXK39gx3mdqveuvFL9drTnye2J");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("ufG9kt6H92KCspBZI67xTvHeB4nBlYwgYLThnN4mMxAIOsCdww");
        addresstype.setAddressType("bZsRWlbjiVW7uZCfE7trVK2kq8bM9Zi2RvLcmaQgVmNJc6WvWi");
        addresstype.setAddressTypeDesc("HRWjlPYYbzExJaTiG5NTkUgNESsP6F5sUwy8eUAVAViLH6B6EO");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setAddress2("wL39H8LtS5iHDCm4fYO2wpnvZqspu7xKEGTzh7E6W8hLaDwEOy");
        address.setAddressLabel("pf2PzIgJkaK");
        address.setLongitude("TPQGIhRHqMgNtfVvLwC93mD25yI9gl9Twrjfs1vhqyQSiUZCgt");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("bdklyCtOhjhwoAsz3jz8L8EaIyUkwTHCqmRb7xmCF8LGLSbT4B");
        address.setLatitude("XQi9x8TmNfk5CSrsumJgQq7aD8OhYa5n5wpXFglxTtPANd7RwR");
        address.setZipcode("zXkIJS");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey());
        address.setAddress3("E4Ffbye53cLlA5Iz49Nd8iCpv9gj6k6AbocxWcBTOO4huoCYNt");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        Login login = new Login();
        userdetail.setUserId(null);
        login.setUserDetail(userdetail);
        login.setServerAuthImage("SBWtbkCbtaARC8LvMlMgkePj2LHce9p7");
        login.setFailedLoginAttempts(5);
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setIsAuthenticated(true);
        login.setLoginId("hkG9XxImSnA7QWcPdttoiiYMX0RxbykU3EQ9JJBYa44YJOyZ8B");
        login.setServerAuthText("pfgrO8Od7vhICxXP");
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("UserDetailPrimaryKey", login.getUserDetail()._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserDetailRepository<UserDetail> userdetailRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setVersionId(1);
            login.setServerAuthImage("4aztrJHugSbPdwAFV8bdZxWYwY2GQZvV");
            login.setFailedLoginAttempts(11);
            login.setLoginId("XSVc6RrPYxfkeS5h1TGL64bN8XdX923dQjuhLcUK7EFL6kNw0F");
            login.setServerAuthText("T589yMRZreWXM9rh");
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateLogin(EntityTestCriteria contraints, Login login) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "rJsIryHuHByoobh3ARuXJdxOuDupeNL5wwsPgxf8Tr40E9oeKJyGsTv1zX3e3GsDqHiIvrBzoLOIfcuf6GP3AWAB8zMUNmTm9jbV0qLO9ytW1yX4n8b7VjO3fXjuQHKPzU2yt2lHRdzC5trg8tfpwa6uSijZi8uBaBEAqsOP94eLOMzvCspDuRNITTeLIjZnM5EFFNGNN"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "WqfrQxRwbyUJBNuWqVJ4mzb74Xg0FcIya"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "rsjgCozOS8RVXGBem"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 20));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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
