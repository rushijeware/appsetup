package com.app.server.service.organization.contactmanagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import org.springframework.http.HttpStatus;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.app.server.repository.organization.contactmanagement.PhoneCommunicationRepository;
import com.app.shared.organization.contactmanagement.PhoneCommunication;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import java.lang.Override;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;
import com.athena.server.pluggable.utils.bean.FindByBean;

@RestController
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "deepali", updatedBy = "deepali", versionNumber = "3", comments = "Service for PhoneCommunication Transaction table", complexity = Complexity.MEDIUM)
@RequestMapping("/PhoneCommunication")
public class PhoneCommunicationServiceImpl extends PhoneCommunicationService {

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private PhoneCommunicationRepository<PhoneCommunication> phoneCommunicationrepo;

    /**
     * Retrieve list of  <PhoneCommunication> object
     * @return HttpEntity<ResponseBean>
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findAll", consumes = "application/json", method = RequestMethod.GET)
    @Override
    public HttpEntity<ResponseBean> findAll() throws Exception {
        java.util.List<PhoneCommunication> lstphonecommunication = phoneCommunicationrepo.findAll();
        AppAlarm appAlarm = Log.getAlarm("ORGCM124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "PhoneCommunication"));
        responseBean.add("data", lstphonecommunication);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCommunicationServiceImpl", "findAll", "PhoneCommunication");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieve Page-wise list of  <PhoneCommunication> object
     * @return HttpEntity<ResponseBean>
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findPageWiseData", consumes = "application/json", method = RequestMethod.GET)
    @Override
    public HttpEntity<ResponseBean> findPageWiseData(@RequestParam("page") Integer page, @RequestParam("start") Integer start, @RequestParam("limit") Integer limit) throws Exception {
        AppAlarm appAlarm = Log.getAlarm("ORGCM124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "PhoneCommunication"));
        responseBean.add("data", phoneCommunicationrepo.findPageWiseData("PhoneCommunication.findAll", limit, page));
        responseBean.add("totalSize", phoneCommunicationrepo.getTotalPageCount("PhoneCommunication.findAll"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCommunicationServiceImpl", "findPageWiseData", "PhoneCommunication");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Saves the new  <PhoneCommunication> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- PhoneCommunication
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody PhoneCommunication entity) throws Exception {
        phoneCommunicationrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("ORGCM122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "PhoneCommunication"));
        responseBean.add("data", entity);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCommunicationServiceImpl", "save", "PhoneCommunication");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Saves the new  list of new <PhoneCommunication> object.
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- List<PhoneCommunication>
     * @Params request :- boolean
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody List<PhoneCommunication> entity, @RequestHeader("isArray") boolean request) throws Exception {
        phoneCommunicationrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("ORGCM122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "PhoneCommunication"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCommunicationServiceImpl", "save", "PhoneCommunication");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Deletes the   <PhoneCommunication> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- [@org.springframework.web.bind.annotation.PathVariable("cid") java.lang.String]
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/{cid}", consumes = "application/json", method = RequestMethod.DELETE)
    @Override
    public HttpEntity<ResponseBean> delete(@PathVariable("cid") String entity) throws Exception {
        phoneCommunicationrepo.delete(entity);
        AppAlarm appAlarm = Log.getAlarm("ORGCM128100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "PhoneCommunication"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCommunicationServiceImpl", "delete", "PhoneCommunication");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Updates the  <PhoneCommunication> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- PhoneCommunication
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody PhoneCommunication entity) throws Exception {
        phoneCommunicationrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("ORGCM123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "PhoneCommunication"));
        responseBean.add("data", entity._getPrimarykey());
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCommunicationServiceImpl", "update", "PhoneCommunication");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Updates the list of  <PhoneCommunication> object.
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- List<PhoneCommunication>
     * @Params  request type:-boolean
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody List<PhoneCommunication> entity, @RequestHeader("isArray") boolean request) throws Exception {
        phoneCommunicationrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("ORGCM123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "PhoneCommunication"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCommunicationServiceImpl", "update", "PhoneCommunication");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieves list of  <PhoneCommunication> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findByCommType", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByCommType(@RequestBody FindByBean findByBean) throws Exception {
        List<PhoneCommunication> lstphonecommunication = phoneCommunicationrepo.findByCommType((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("ORGCM124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "PhoneCommunication"));
        responseBean.add("data", lstphonecommunication);
        Log.out.println("ORGCM124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCommunicationServiceImpl", "findByCommType", "PhoneCommunication");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieves list of  <PhoneCommunication> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findById(@RequestBody FindByBean findByBean) throws Exception {
        PhoneCommunication lstphonecommunication = phoneCommunicationrepo.findById((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("ORGCM124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "PhoneCommunication"));
        responseBean.add("data", lstphonecommunication);
        Log.out.println("ORGCM124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PhoneCommunicationServiceImpl", "findById", "PhoneCommunication");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }
}
