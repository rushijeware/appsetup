Ext.define('Appsetup.appsetup.web.com.view.organization.locationmanagement.CityMain', {
     "xtype": "cityMainView",
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "CityMainController",
     "restURL": "/City",
     "defaults": {
          "split": true
     },
     "requires": ["Appsetup.appsetup.shared.com.model.organization.locationmanagement.CityModel", "Appsetup.appsetup.web.com.controller.organization.locationmanagement.CityMainController", "Appsetup.appsetup.shared.com.model.organization.locationmanagement.CountryModel", "Appsetup.appsetup.shared.com.model.organization.locationmanagement.StateModel", "Appsetup.appsetup.shared.com.viewmodel.organization.locationmanagement.CityViewModel"],
     "communicationLog": [],
     "tabPosition": "bottom",
     "items": [{
          "title": "Data Browser",
          "layout": "border",
          "defaults": {
               "split": true
          },
          "autoScroll": false,
          "customWidgetType": "vdBorderLayout",
          "items": [{
               "xtype": "tabpanel",
               "customWidgetType": "vdTabLayout",
               "margin": "5 0 5 5",
               "border": 1,
               "style": {
                    "borderColor": "#f6f6f6",
                    "borderStyle": "solid",
                    "borderWidth": "1px"
               },
               "displayName": "City",
               "name": "CityTreeContainer",
               "itemId": "CityTreeContainer",
               "restURL": "/City",
               "autoScroll": false,
               "collapsible": true,
               "titleCollapse": true,
               "collapseMode": "header",
               "collapsed": false,
               "items": [{
                    "xtype": "treepanel",
                    "customWidgetType": "vdTree",
                    "title": "Browse",
                    "name": "entityTreePanel",
                    "useArrows": true,
                    "rootVisible": false,
                    "itemId": "CityTree",
                    "listeners": {
                         "select": "treeClick"
                    },
                    "tbar": [{
                         "xtype": "triggerfield",
                         "customWidgetType": "vdTriggerField",
                         "emptyText": "Search",
                         "triggerCls": "",
                         "listeners": {
                              "change": "onTriggerfieldChange",
                              "buffer": 250
                         }
                    }, "->", {
                         "xtype": "tool",
                         "type": "refresh",
                         "tooltip": "Refresh Tree Data",
                         "handler": "onTreeRefreshClick"
                    }]
               }, {
                    "title": "Advance Search",
                    "xtype": "form",
                    "customWidgetType": "vdFormpanel",
                    "itemId": "queryPanel",
                    "buttons": [{
                         "text": "Filter",
                         "handler": "onFilterClick",
                         "name": "filterButton"
                    }],
                    "items": []
               }],
               "region": "west",
               "width": "20%"
          }, {
               "region": "center",
               "layout": "border",
               "defaults": {
                    "split": true
               },
               "customWidgetType": "vdBorderLayout",
               "items": [{
                    "customWidgetType": "vdFormpanel",
                    "xtype": "form",
                    "displayName": "City",
                    "title": "City",
                    "name": "City",
                    "itemId": "CityForm",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "cityId",
                         "itemId": "cityId",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "City Id",
                         "margin": "5 5 5 5",
                         "fieldLabel": "City Id<font color='red'> *<\/font>",
                         "fieldId": "EF97BFC7-5F92-407C-88A4-26429A8AD5DC",
                         "minLength": "0",
                         "maxLength": "64",
                         "hidden": true,
                         "value": "",
                         "bindable": "cityId"
                    }, {
                         "name": "countryId",
                         "itemId": "countryId",
                         "xtype": "customcombobox",
                         "customWidgetType": "vdCombo",
                         "displayName": "Country",
                         "margin": "5 5 5 5",
                         "valueField": "primaryKey",
                         "displayField": "primaryDisplay",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 2,
                         "customStore": {
                              "model": "Appsetup.appsetup.shared.com.model.organization.locationmanagement.CountryModel",
                              "autoLoad": true,
                              "autoSync": true,
                              "sorters": [{
                                   "property": "primaryDisplay",
                                   "direction": "ASC"
                              }],
                              "proxy": {
                                   "type": "ajax",
                                   "url": "secure/Country/findAll",
                                   "actionMethods": {
                                        "read": "GET"
                                   },
                                   "headers": {
                                        "Content-Type": "application/json"
                                   },
                                   "extraParams": {},
                                   "reader": {
                                        "type": "json",
                                        "rootProperty": "response.data"
                                   }
                              }
                         },
                         "allowBlank": false,
                         "fieldLabel": "Country<font color='red'> *<\/font>",
                         "fieldId": "367CC9AC-254A-4B60-B089-10A8F7BDAE6B",
                         "restURL": "Country",
                         "bindable": "countryId",
                         "columnWidth": 0.5,
                         "listeners": {
                              "change": "onCountryIdChange"
                         }
                    }, {
                         "name": "stateId",
                         "itemId": "stateId",
                         "xtype": "customcombobox",
                         "customWidgetType": "vdCombo",
                         "displayName": "State",
                         "margin": "5 5 5 5",
                         "valueField": "primaryKey",
                         "displayField": "primaryDisplay",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 2,
                         "allowBlank": false,
                         "fieldLabel": "State<font color='red'> *<\/font>",
                         "fieldId": "1C395878-1B59-48FA-B17A-AD4453AE618C",
                         "restURL": "State",
                         "store": {
                              "model": "Appsetup.appsetup.shared.com.model.organization.locationmanagement.StateModel"
                         },
                         "bindable": "stateId",
                         "columnWidth": 0.5
                    }, {
                         "name": "cityName",
                         "itemId": "cityName",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "City Name",
                         "margin": "5 5 5 5",
                         "fieldLabel": "City Name<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "D27182BC-7767-4446-8D91-2E43EE9B1FBA",
                         "minLength": "0",
                         "maxLength": "256",
                         "bindable": "cityName",
                         "columnWidth": 0.5
                    }, {
                         "name": "cityCodeChar2",
                         "itemId": "cityCodeChar2",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "City Code",
                         "margin": "5 5 5 5",
                         "fieldLabel": "City Code<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "F814C32C-D2BF-4B04-8EA5-91B212557A5C",
                         "minLength": "0",
                         "maxLength": "32",
                         "bindable": "cityCodeChar2",
                         "columnWidth": 0.5
                    }, {
                         "name": "cityCode",
                         "itemId": "cityCode",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "City Code1",
                         "margin": "5 5 5 5",
                         "fieldLabel": "City Code1<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "62E18D79-2A63-4D40-B7EE-B31F50266758",
                         "minValue": "0",
                         "maxValue": "3",
                         "bindable": "cityCode",
                         "columnWidth": 0.5
                    }, {
                         "name": "cityDescription",
                         "itemId": "cityDescription",
                         "xtype": "textareafield",
                         "customWidgetType": "vdTextareafield",
                         "displayName": "City Description",
                         "margin": "5 5 5 5",
                         "fieldLabel": "City Description",
                         "fieldId": "4109B4CF-4AFD-48A7-ADB9-ACDA4DA4A28C",
                         "bindable": "cityDescription",
                         "columnWidth": 0.5
                    }, {
                         "name": "cityFlag",
                         "itemId": "cityFlag",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Flag",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Flag",
                         "fieldId": "0C84D0F7-3ABC-48E7-8B7D-28412FBEACB4",
                         "minLength": "0",
                         "maxLength": "128",
                         "bindable": "cityFlag",
                         "columnWidth": 0.5
                    }, {
                         "name": "cityLatitude",
                         "itemId": "cityLatitude",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "City Latitude",
                         "margin": "5 5 5 5",
                         "fieldLabel": "City Latitude",
                         "fieldId": "E3BDC228-EBA1-45D6-B90A-950C6431EB9C",
                         "minValue": "0",
                         "maxValue": "11",
                         "bindable": "cityLatitude",
                         "columnWidth": 0.5
                    }, {
                         "name": "cityLongitude",
                         "itemId": "cityLongitude",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "City Longitude",
                         "margin": "5 5 5 5",
                         "fieldLabel": "City Longitude",
                         "fieldId": "BED213D6-3C56-469D-9A20-A8457DE5E3A3",
                         "minValue": "0",
                         "maxValue": "11",
                         "bindable": "cityLongitude",
                         "columnWidth": 0.5
                    }, {
                         "name": "versionId",
                         "itemId": "versionId",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "versionId",
                         "margin": "5 5 5 5",
                         "value": "-1",
                         "fieldLabel": "versionId",
                         "fieldId": "092B290A-33E1-4F6A-8385-6010F5E9CB0B",
                         "bindable": "versionId",
                         "hidden": true
                    }],
                    "layout": "column",
                    "defaults": {
                         "columnWidth": 0.5,
                         "labelAlign": "left",
                         "labelWidth": 200
                    },
                    "autoScroll": true,
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "ui": "footer",
                         "isToolBar": true,
                         "isDockedItem": true,
                         "parentId": 1,
                         "customId": 796,
                         "layout": {
                              "type": "hbox"
                         },
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 796,
                              "customId": 754
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "saveFormButton",
                              "margin": 5,
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 796,
                              "customId": 755,
                              "listeners": {
                                   "click": "saveForm"
                              }
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "resetFormButton",
                              "margin": 5,
                              "text": "Reset",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "resetFormButton",
                              "parentId": 796,
                              "customId": 756,
                              "listeners": {
                                   "click": "resetForm"
                              }
                         }]
                    }],
                    "tools": [{
                         "type": "help",
                         "tooltip": "Console",
                         "handler": "onConsoleClick"
                    }, {
                         "type": "refresh",
                         "tooltip": "Refresh Tab",
                         "handler": "init"
                    }],
                    "extend": "Ext.form.Panel",
                    "region": "center"
               }, {
                    "xtype": "gridpanel",
                    "customWidgetType": "vdGrid",
                    "displayName": "City",
                    "title": "Details Grid",
                    "name": "CityGrid",
                    "itemId": "CityGrid",
                    "restURL": "/City",
                    "columns": [{
                         "header": "City Id",
                         "dataIndex": "cityId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryDisplay",
                         "dataIndex": "primaryDisplay",
                         "hidden": true
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "Country",
                         "dataIndex": "countryId",
                         "renderer": "renderFormValue",
                         "flex": 1
                    }, {
                         "header": "State",
                         "dataIndex": "stateId",
                         "renderer": "renderFormValue",
                         "flex": 1
                    }, {
                         "header": "City Name",
                         "dataIndex": "cityName",
                         "flex": 1
                    }, {
                         "header": "City Code",
                         "dataIndex": "cityCodeChar2",
                         "flex": 1
                    }, {
                         "header": "City Code1",
                         "dataIndex": "cityCode",
                         "flex": 1
                    }, {
                         "header": "City Description",
                         "dataIndex": "cityDescription",
                         "flex": 1
                    }, {
                         "header": "Flag",
                         "dataIndex": "cityFlag",
                         "flex": 1
                    }, {
                         "header": "City Latitude",
                         "dataIndex": "cityLatitude",
                         "flex": 1
                    }, {
                         "header": "City Longitude",
                         "dataIndex": "cityLongitude",
                         "flex": 1
                    }, {
                         "header": "createdBy",
                         "dataIndex": "createdBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "createdDate",
                         "dataIndex": "createdDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedBy",
                         "dataIndex": "updatedBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedDate",
                         "dataIndex": "updatedDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "versionId",
                         "dataIndex": "versionId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "activeStatus",
                         "dataIndex": "activeStatus",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "txnAccessCode",
                         "dataIndex": "txnAccessCode",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "xtype": "actioncolumn",
                         "customWidgetType": "vdActionColumn",
                         "sortable": false,
                         "menuDisable": true,
                         "items": [{
                              "icon": "images/delete.gif",
                              "tooltip": "Delete Record",
                              "handler": "onDeleteActionColumnClickMainGrid"
                         }]
                    }],
                    "listeners": {
                         "itemclick": "onGridItemClick"
                    },
                    "tools": [{
                         "type": "refresh",
                         "tooltip": "Refresh Grid Data",
                         "listeners": {
                              "click": "onGridRefreshClick"
                         },
                         "hidden": true
                    }],
                    "collapsible": true,
                    "titleCollapse": true,
                    "collapseMode": "header",
                    "region": "south",
                    "height": "40%"
               }]
          }]
     }, {
          "title": "Add New",
          "itemId": "addNewForm",
          "layout": "border",
          "customWidgetType": "vdBorderLayout",
          "autoScroll": false,
          "items": [{
               "customWidgetType": "vdFormpanel",
               "xtype": "form",
               "displayName": "City",
               "title": "City",
               "name": "City",
               "itemId": "CityForm",
               "bodyPadding": 10,
               "items": [{
                    "name": "cityId",
                    "itemId": "cityId",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "City Id",
                    "margin": "5 5 5 5",
                    "fieldLabel": "City Id<font color='red'> *<\/font>",
                    "fieldId": "EF97BFC7-5F92-407C-88A4-26429A8AD5DC",
                    "minLength": "0",
                    "maxLength": "64",
                    "hidden": true,
                    "value": "",
                    "bindable": "cityId"
               }, {
                    "name": "countryId",
                    "itemId": "countryId",
                    "xtype": "customcombobox",
                    "customWidgetType": "vdCombo",
                    "displayName": "Country",
                    "margin": "5 5 5 5",
                    "valueField": "primaryKey",
                    "displayField": "primaryDisplay",
                    "typeAhead": true,
                    "queryMode": "local",
                    "minChars": 2,
                    "customStore": {
                         "model": "Appsetup.appsetup.shared.com.model.organization.locationmanagement.CountryModel",
                         "autoLoad": true,
                         "autoSync": true,
                         "sorters": [{
                              "property": "primaryDisplay",
                              "direction": "ASC"
                         }],
                         "proxy": {
                              "type": "ajax",
                              "url": "secure/Country/findAll",
                              "actionMethods": {
                                   "read": "GET"
                              },
                              "headers": {
                                   "Content-Type": "application/json"
                              },
                              "extraParams": {},
                              "reader": {
                                   "type": "json",
                                   "rootProperty": "response.data"
                              }
                         }
                    },
                    "allowBlank": false,
                    "fieldLabel": "Country<font color='red'> *<\/font>",
                    "fieldId": "367CC9AC-254A-4B60-B089-10A8F7BDAE6B",
                    "restURL": "Country",
                    "bindable": "countryId",
                    "columnWidth": 0.5,
                    "listeners": {
                         "change": "onCountryIdChange"
                    }
               }, {
                    "name": "stateId",
                    "itemId": "stateId",
                    "xtype": "customcombobox",
                    "customWidgetType": "vdCombo",
                    "displayName": "State",
                    "margin": "5 5 5 5",
                    "valueField": "primaryKey",
                    "displayField": "primaryDisplay",
                    "typeAhead": true,
                    "queryMode": "local",
                    "minChars": 2,
                    "allowBlank": false,
                    "fieldLabel": "State<font color='red'> *<\/font>",
                    "fieldId": "1C395878-1B59-48FA-B17A-AD4453AE618C",
                    "restURL": "State",
                    "store": {
                         "model": "Appsetup.appsetup.shared.com.model.organization.locationmanagement.StateModel"
                    },
                    "bindable": "stateId",
                    "columnWidth": 0.5
               }, {
                    "name": "cityName",
                    "itemId": "cityName",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "City Name",
                    "margin": "5 5 5 5",
                    "fieldLabel": "City Name<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "D27182BC-7767-4446-8D91-2E43EE9B1FBA",
                    "minLength": "0",
                    "maxLength": "256",
                    "bindable": "cityName",
                    "columnWidth": 0.5
               }, {
                    "name": "cityCodeChar2",
                    "itemId": "cityCodeChar2",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "City Code",
                    "margin": "5 5 5 5",
                    "fieldLabel": "City Code<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "F814C32C-D2BF-4B04-8EA5-91B212557A5C",
                    "minLength": "0",
                    "maxLength": "32",
                    "bindable": "cityCodeChar2",
                    "columnWidth": 0.5
               }, {
                    "name": "cityCode",
                    "itemId": "cityCode",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "City Code1",
                    "margin": "5 5 5 5",
                    "fieldLabel": "City Code1<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "62E18D79-2A63-4D40-B7EE-B31F50266758",
                    "minValue": "0",
                    "maxValue": "3",
                    "bindable": "cityCode",
                    "columnWidth": 0.5
               }, {
                    "name": "cityDescription",
                    "itemId": "cityDescription",
                    "xtype": "textareafield",
                    "customWidgetType": "vdTextareafield",
                    "displayName": "City Description",
                    "margin": "5 5 5 5",
                    "fieldLabel": "City Description",
                    "fieldId": "4109B4CF-4AFD-48A7-ADB9-ACDA4DA4A28C",
                    "bindable": "cityDescription",
                    "columnWidth": 0.5
               }, {
                    "name": "cityFlag",
                    "itemId": "cityFlag",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Flag",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Flag",
                    "fieldId": "0C84D0F7-3ABC-48E7-8B7D-28412FBEACB4",
                    "minLength": "0",
                    "maxLength": "128",
                    "bindable": "cityFlag",
                    "columnWidth": 0.5
               }, {
                    "name": "cityLatitude",
                    "itemId": "cityLatitude",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "City Latitude",
                    "margin": "5 5 5 5",
                    "fieldLabel": "City Latitude",
                    "fieldId": "E3BDC228-EBA1-45D6-B90A-950C6431EB9C",
                    "minValue": "0",
                    "maxValue": "11",
                    "bindable": "cityLatitude",
                    "columnWidth": 0.5
               }, {
                    "name": "cityLongitude",
                    "itemId": "cityLongitude",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "City Longitude",
                    "margin": "5 5 5 5",
                    "fieldLabel": "City Longitude",
                    "fieldId": "BED213D6-3C56-469D-9A20-A8457DE5E3A3",
                    "minValue": "0",
                    "maxValue": "11",
                    "bindable": "cityLongitude",
                    "columnWidth": 0.5
               }, {
                    "name": "versionId",
                    "itemId": "versionId",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "versionId",
                    "margin": "5 5 5 5",
                    "value": "-1",
                    "fieldLabel": "versionId",
                    "fieldId": "092B290A-33E1-4F6A-8385-6010F5E9CB0B",
                    "bindable": "versionId",
                    "hidden": true
               }],
               "layout": "column",
               "defaults": {
                    "columnWidth": 0.5,
                    "labelAlign": "left",
                    "labelWidth": 200
               },
               "autoScroll": true,
               "dockedItems": [{
                    "xtype ": "toolbar",
                    "customWidgetType": "vdBBar",
                    "dock": "bottom",
                    "ui": "footer",
                    "isToolBar": true,
                    "isDockedItem": true,
                    "parentId": 1,
                    "customId": 796,
                    "layout": {
                         "type": "hbox"
                    },
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill",
                         "parentId": 796,
                         "customId": 754
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "saveFormButton",
                         "margin": 5,
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "parentId": 796,
                         "customId": 755,
                         "listeners": {
                              "click": "saveForm"
                         }
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "resetFormButton",
                         "margin": 5,
                         "text": "Reset",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "resetFormButton",
                         "parentId": 796,
                         "customId": 756,
                         "listeners": {
                              "click": "resetForm"
                         }
                    }]
               }],
               "tools": [{
                    "type": "help",
                    "tooltip": "Console",
                    "handler": "onConsoleClick"
               }, {
                    "type": "refresh",
                    "tooltip": "Refresh Tab",
                    "handler": "init"
               }],
               "extend": "Ext.form.Panel",
               "region": "center"
          }]
     }]
});