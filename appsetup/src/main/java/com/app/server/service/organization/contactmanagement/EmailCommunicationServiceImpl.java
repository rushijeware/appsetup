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
import com.app.server.repository.organization.contactmanagement.EmailCommunicationRepository;
import com.app.shared.organization.contactmanagement.EmailCommunication;
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
@SourceCodeAuthorClass(createdBy = "deepali", updatedBy = "deepali", versionNumber = "3", comments = "Service for EmailCommunication Transaction table", complexity = Complexity.MEDIUM)
@RequestMapping("/EmailCommunication")
public class EmailCommunicationServiceImpl extends EmailCommunicationService {

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EmailCommunicationRepository<EmailCommunication> emailCommunicationrepo;

    /**
     * Retrieve list of  <EmailCommunication> object
     * @return HttpEntity<ResponseBean>
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findAll", consumes = "application/json", method = RequestMethod.GET)
    @Override
    public HttpEntity<ResponseBean> findAll() throws Exception {
        java.util.List<EmailCommunication> lstemailcommunication = emailCommunicationrepo.findAll();
        AppAlarm appAlarm = Log.getAlarm("ORGCM124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "EmailCommunication"));
        responseBean.add("data", lstemailcommunication);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationServiceImpl", "findAll", "EmailCommunication");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieve Page-wise list of  <EmailCommunication> object
     * @return HttpEntity<ResponseBean>
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findPageWiseData", consumes = "application/json", method = RequestMethod.GET)
    @Override
    public HttpEntity<ResponseBean> findPageWiseData(@RequestParam("page") Integer page, @RequestParam("start") Integer start, @RequestParam("limit") Integer limit) throws Exception {
        AppAlarm appAlarm = Log.getAlarm("ORGCM124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "EmailCommunication"));
        responseBean.add("data", emailCommunicationrepo.findPageWiseData("EmailCommunication.findAll", limit, page));
        responseBean.add("totalSize", emailCommunicationrepo.getTotalPageCount("EmailCommunication.findAll"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationServiceImpl", "findPageWiseData", "EmailCommunication");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Saves the new  <EmailCommunication> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- EmailCommunication
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody EmailCommunication entity) throws Exception {
        emailCommunicationrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("ORGCM122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "EmailCommunication"));
        responseBean.add("data", entity);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationServiceImpl", "save", "EmailCommunication");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Saves the new  list of new <EmailCommunication> object.
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- List<EmailCommunication>
     * @Params request :- boolean
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody List<EmailCommunication> entity, @RequestHeader("isArray") boolean request) throws Exception {
        emailCommunicationrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("ORGCM122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "EmailCommunication"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationServiceImpl", "save", "EmailCommunication");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Deletes the   <EmailCommunication> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- [@org.springframework.web.bind.annotation.PathVariable("cid") java.lang.String]
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/{cid}", consumes = "application/json", method = RequestMethod.DELETE)
    @Override
    public HttpEntity<ResponseBean> delete(@PathVariable("cid") String entity) throws Exception {
        emailCommunicationrepo.delete(entity);
        AppAlarm appAlarm = Log.getAlarm("ORGCM128100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "EmailCommunication"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationServiceImpl", "delete", "EmailCommunication");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Updates the  <EmailCommunication> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- EmailCommunication
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody EmailCommunication entity) throws Exception {
        emailCommunicationrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("ORGCM123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "EmailCommunication"));
        responseBean.add("data", entity._getPrimarykey());
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationServiceImpl", "update", "EmailCommunication");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Updates the list of  <EmailCommunication> object.
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- List<EmailCommunication>
     * @Params  request type:-boolean
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody List<EmailCommunication> entity, @RequestHeader("isArray") boolean request) throws Exception {
        emailCommunicationrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("ORGCM123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "EmailCommunication"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationServiceImpl", "update", "EmailCommunication");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieves list of  <EmailCommunication> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findByCommType", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByCommType(@RequestBody FindByBean findByBean) throws Exception {
        List<EmailCommunication> lstemailcommunication = emailCommunicationrepo.findByCommType((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("ORGCM124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "EmailCommunication"));
        responseBean.add("data", lstemailcommunication);
        Log.out.println("ORGCM124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationServiceImpl", "findByCommType", "EmailCommunication");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieves list of  <EmailCommunication> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findById(@RequestBody FindByBean findByBean) throws Exception {
        EmailCommunication lstemailcommunication = emailCommunicationrepo.findById((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("ORGCM124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "EmailCommunication"));
        responseBean.add("data", lstemailcommunication);
        Log.out.println("ORGCM124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationServiceImpl", "findById", "EmailCommunication");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }
}
