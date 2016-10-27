package com.app.server.businessservice.organization.locationmanagement;
import org.springframework.stereotype.Service;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.deo.camel.utility.ExternalIntegrationCaller;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.shared.organization.locationmanagement.PaymentDetails;
import com.app.shared.organization.locationmanagement.TransactionResponse;
import org.apache.commons.lang.exception.ExceptionUtils;

@Service
@SourceCodeAuthorClass(createdBy = "deepali", updatedBy = "deepali", versionNumber = "3", comments = "OrderProcessServiceImpl", complexity = Complexity.HIGH)
public class OrderProcessServiceImpl {

    @Autowired
    private ExternalIntegrationCaller externalIntegrationCaller;

    public TransactionResponse processOrder(PaymentDetails paymentDetails) throws Exception {
        try {
            java.util.HashMap map = new java.util.HashMap();
            map.put("paymentDetails", paymentDetails);
            com.app.shared.organization.locationmanagement.TransactionResponse transactionresponse = (com.app.shared.organization.locationmanagement.TransactionResponse) externalIntegrationCaller.executeRoute("DD92761C-01CB-4937-9879-D665A4CF65A7", map);
            return transactionresponse;
        } catch (java.lang.Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
