Ext.define('Appsetup.appsetup.shared.com.model.organization.contactmanagement.PhoneContactMapModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "phonecontactmapPkey",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "contactid",
          "reference": "CoreContacts",
          "defaultValue": ""
     }, {
          "name": "phoneid",
          "reference": "PhoneCommunication",
          "defaultValue": ""
     }, {
          "name": "entityAudit",
          "reference": "EntityAudit"
     }, {
          "name": "primaryDisplay",
          "type": "string",
          "defaultValue": ""
     }]
});