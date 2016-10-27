Ext.define('Appsetup.view.appreportui.datagrid.DataGridViewController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.dataGridController',
	downloadFileform : null,
	
	init:function(){
		var body = Ext.getBody();
		this.frame = body.createChild({
			tag : 'iframe',
			cls : 'x-hidden',
			id : 'hiddenform-iframe' + this.getView().id,
			name : 'iframe' + this.getView().id
		});
		this.downloadFileform = body.createChild({
			tag : 'form',
			cls : 'x-hidden',
			id : 'hiddenform-form' + this.getView().id,
			method : 'POST',
			ContentType : 'application/json;application/xml',
			action : "",
			target : 'iframe' + this.getView().id
		});
		this.downloadFileform.createChild({
			tag : 'input',
			name : "params",
			id : "params" + this.getView().id
		});
	},
	
	initObjects : function(datagrid) {
		this.queryCriteria = this.getView().reportView.down("#qcInnerPanelId");
		this.datagrid = datagrid;
	},
	
	/** This method is called from reportview controller getGridData() to set various functionality*/
	setHrefToColumns : function(columns) {
		columns.forEach(function(item) {
			//If drilldown is true set hyperlink
			if (item.href == true) {
				item.renderer = this.hrefRenderer
			}
			if (item.columns != undefined) {
				this.setHrefToColumns(item.columns);
			}
			//If summary caption is true
			if (item.summaryCaption != undefined && item.summaryCaption.length > 0) {
				item.summaryRenderer = function(value, summaryData, dataIndex) {
					
					return eval(item.summaryCaption);
				}
			}
			//If column highlight is true
			if(item.colHighlight!=undefined && item.colHighlight.length>0){
				item.renderer=this.setColumnRenderer
			}
		}, this);
	},
	
	/** This method used to set column value as link in case of drill down report*/
	hrefRenderer : function(value, metaData, record, row, col, store, gridView) {
		myURL = '';
		if (value !== '') {
			myURL = '<a href="javascript:void(0);">' + value + '</a>'
		}
		return myURL;
	},
	
	/**This method used to set column highlight*/
	setColumnRenderer: function(value, metaData, record, row, col, store, gridView) {
		var colArr=metaData.column.colHighlight;
		for(var i=0;i<colArr.length;i++){
			//If cell highlight is applied on date field
    		if(colArr[i].dateFlag!=undefined && colArr[i].dateFlag==true){
    			if(record.data.dateLongValue[colArr[i].name]>=new Date(colArr[i].from).getTime() 
    					&& record.data.dateLongValue[colArr[i].name]<=new Date(colArr[i].to).getTime()){
    				metaData.tdCls = colArr[i].styleCss;
    			}
    		}
    		//If cell highlight is applied on string and integer fields
    		else{
    			if(colArr[i].parameterType=="range"){
        			if(colArr[i].to!=""){
	        			if(value>=parseInt(colArr[i].from)&& value<=parseInt(colArr[i].to) )
	        				metaData.tdCls = colArr[i].styleCss;
	        		}else{
	        			if(value>=parseInt(colArr[i].from))
	        				metaData.tdCls = colArr[i].styleCss;
	        		}
        		}else if(colArr[i].parameterType=="like"){
        			var resArr=record.get(colArr[i].name).match(new RegExp(colArr[i].like,"gi"));
        			if(resArr!=null && resArr.length>0){
        				metaData.tdCls = colArr[i].styleCss;
        			}
        		}
        		else{
        			if(value.toString().toUpperCase()==colArr[i].equalTo)
        				metaData.tdCls = colArr[i].styleCss;
        		}
    		}
		}
		//If same column is having drilldown link as well as cell highlight
		if(metaData.column.href==true){
			myURL = '';
			if (value !== '') {
				myURL = '<a href="javascript:void(0);">' + value + '</a>'
			}
			return myURL;
		}else{
			return value
		}
	},
	
	/**This method is called from reportview controller to add customized toolbar to grid*/
	addToolbartoGrid : function(grid, gridStore) {
		var paging = this.getGridPaging(gridStore);
		var excelbutton = this.getExcelButton();
		var toolbar = Ext.create('Ext.toolbar.Toolbar', {
			dock : 'bottom',
			items : [ paging, '->', excelbutton ]
		});
		grid.addDocked(toolbar)
	},
	getGridPaging : function(gridStore) {
		return {
			xtype : 'pagingtoolbar',
			store : gridStore,
			displayInfo : true,
			controllScope : this,
			dock : 'bottom',
			style:{
                borderWidth:'0px'
            },
			displayMsg : 'Displaying {0} - {1} of {2}',
			doRefresh : function() {
				this.controllScope.refreshDataChart();
				// Keep or remove these code
				var me = this, current = me.store.currentPage;

				if (me.fireEvent('beforechange', me, current) !== false) {
					me.store.loadPage(current);
				}
			}

		};
	},
	getExcelButton : function() {
		return Ext.create('Ext.button.Button', {
			tooltip : 'Excel Download',
			icon : 'images/rpticon/excel.gif',
			scope : this,
			height:40,
			width:40,
            arrowVisible:false,
			margin:'5 20 5 20',
			style:{
                     borderWidth:'0px',
                     borderRadius:'50%',background:'#f6f6f6',
                     "box-shadow": "0 2px 5px 0 rgba(0, 0, 0, 0.26);"
            },
            //listeners : {mouseover : function() { this.menu.showBy(this); }},
			handler : 'downloadSinglePageD',
			menu : [ {
				 text : '<span class="user-settings-menu-item-cls">Current<span>',
                 height : 38,
				icon : 'images/rpticon/excel.gif',
				listeners : {
					click : this.downloadSinglePage,

				}
			}, {
				 text : '<span class="user-settings-menu-item-cls">All<span>',
                 height : 38,
				icon : 'images/rpticon/excel.gif',
				listeners : {
					click : this.downloadAllPage,

				}
			} ]
		});
	},
	/** used to refresh report */
	refreshDataChart : function() {
		this.getView().reportView.controller.getDataPointChartDataByAjax();
	},
	
	/**On click of Excel button to download the current page of grid data*/
	downloadSinglePageD : function() {
		this.downloadSingleSheet();
	},
	
	/**On click of Excel button advance option to download the current page of grid data*/
	downloadSinglePage:function(){
		var controller = this.up().up().scope
		controller.downloadSingleSheet();
	},
	
	downloadSingleSheet : function() {
//		var params = this.getView().getStore().getProxy().extraParams;
		var params = Ext.clone(this.getView().getStore().getProxy().extraParams);
		var pagingtoolbar = this.datagrid.down("pagingtoolbar");
		pageData = pagingtoolbar.getPageData();
		if (this.getView().reportDataJSON.summaryGroups != undefined && this.getView().reportDataJSON.summaryGroups.length > 0) {
			params["rowgrouping"] = this.getView().reportDataJSON.summaryGroups[0];
		} else {
			params["rowgrouping"] = "";
		}
		params["columnsHeader"] = this.getView().reportDataJSON.gridColumns;
		params["title"] = this.getView().reportDataJSON.report_name;
		
		//These 3 keys are added coz they are not getting found in params implicitly
		params["page"]= pageData.currentPage;
		params["start"]= pageData.fromRecord;
		params["limit"]= 50;
		
		this.downloadFileform.dom.childNodes[0].value = Ext.encode(params);
		this.downloadFileform.dom.action = ("secure/dataEngineWebService/excelSinglePage");
		this.downloadFileform.dom.submit();
	},
	
	/**On click of Excel button advance option to download the all pages of grid data*/
	downloadAllPage:function(){
		var controller = this.up().up().scope;
//		var params = controller.getView().getStore().getProxy().extraParams;
		var params = Ext.clone(this.getView().getStore().getProxy().extraParams);
		if (controller.getView().reportDataJSON.summaryGroups != undefined && controller.getView().reportDataJSON.summaryGroups.length > 0) {
			params["rowgrouping"] = controller.getView().reportDataJSON.summaryGroups[0];
		} else {
			params["rowgrouping"] = "";
		}
		params["columnsHeader"] = controller.getView().reportDataJSON.gridColumns;
		params["title"] = controller.getView().reportDataJSON.report_name;
		
		//These 3 keys are added coz they are not getting found in params implicitly
		params["page"]=1;
		params["start"]=0;
		params["limit"]=20;
		
		controller.downloadFileform.dom.childNodes[0].value = Ext.encode(params);
		controller.downloadFileform.dom.action = ("secure/dataEngineWebService/excelAllPage");
		controller.downloadFileform.dom.submit();
	},
	
	/**On cell click of grid having drilldown*/
	dataGridCellClick : function(grid, td, cellIndex, record, tr, rowIndex, e, eOpts) {
		var columns = grid.getGridColumns();
		
		/**Index is calculated here so that if any column is hidden then cellIndex causes issue*/
		var clickedColumnName = record.getFields()[cellIndex].getName();
		var index = -1;
		for(var i=0;i<columns.length;i++){
			if(columns[i].dataIndex==clickedColumnName){
				index=i;
				break;
			}
		}
			 
		var column = columns[index];
		if (column.href != undefined && column.href == true) {
			// redirect to default report
			if (column.targetRList != undefined
					&& column.targetRList.length > 0) {
			//	var queryCriteria = {};
				var val;
				var displayval;
				var qc = [];
				val = record.data[column.qFieldName != undefined ? column.qFieldName : column.dataIndex];
				displayval = record.data[column.dataIndex];
				if (this.getView().reportView.rptHrefQC != undefined && this.getView().reportView.rptHrefQC.length!=0) {
					var t = this.getView().reportView.rptHrefQC;
					for (var x = 0; x < t.length; x++) {
						qc.push(t[x]);
					}
				}
				for (var x = 0; x < column.targetRList.length; x++) {
					var queryCriteria = {};
					queryCriteria["name"] = column.targetRList[x].targetField!=undefined?column.targetRList[x].targetField:column.targetRList[x].targetField;
					/*queryCriteria["name"] = column.qFieldName != undefined
					&& column.qFieldName.length > 0 ? column.qFieldName : column.dataIndex;*/
					queryCriteria["value"] = val;
					queryCriteria["datatype"] = column.datatype;
					queryCriteria["displayValue"] = displayval;
					queryCriteria["label"] = column.text;
					qc.push(queryCriteria);
				}
				
				
				//Check whether queryContext is checked true or not
				if (column.targetRList[0].qcContext != undefined
						&& column.targetRList[0].qcContext == 1) {
					this.getView().reportView.down('#querycriteria').controller.getQueryCriteria(qc);
				}
				
				this.openDrillDown(
						column.targetRList[0].report_id,
						column.targetRList[0].report_name, qc,
						this.datagrid,column.targetRList[0]);
			}
		}
	},
	/** used to open sub report 
	 * reportId report id or form id of sub report
	 * reportName report Name or form Name of sub report
	 * rptHrefQC parameter need to be pass
	 * gridObj grid object
	 * type r - Report f- form
	 */
	openDrillDown : function(reportId, reportName, rptHrefQC,gridObj,targetObj) 
	{
		//Open in next Tab
		if(targetObj.targetSrc=="tab"){
			var component ;
			if(targetObj.type == "f"){
				component= Ext.create(
						targetObj.menuAction, {
							closable : true,
							drilled : true,		
							title : reportName,
							refId : targetObj.reffId,
							rptHrefQC : rptHrefQC,
							primaryKey : rptHrefQC[0].value,
							caller : this,
							disableDB:true
						});
			}else{
				component= Ext.create(
						'Appsetup.view.appreportui.ReportView', {
							closable : true,
							drilled : true,		
							title : reportName,
							refId : reportId,
							rptHrefQC : rptHrefQC
						});
			}
			var tabs = Ext.getCmp('appMainTabPanel');
			var tab = tabs.add({
				xtype : component,
				title : reportName
			});
			tabs.setActiveTab(tab);
			// This condition is used for custom form with state function add in drill down report
			if(targetObj.stateFunctionName!=null && targetObj.stateFunctionName!=undefined){
				var functionValue=rptHrefQC[0].value;
				if(typeof(component.controller[targetObj.stateFunctionName]) == "function") {
					component.controller[targetObj.stateFunctionName](functionValue);
				}
			}
		}
		//Open in a Window
		else{
			var drillDownWindow = Ext.create('Ext.window.Window', {
				title: reportName, 
				bodyPadding:'5',
				layout:'fit',
				width:'50%',
				width:600,
				height:600,
				resizable : true,
				modal:true,
				autoScroll:true,
				closeAction:'destroy',
				//items:[{xtype : component1}]
			});
			var component1;
			if(targetObj.type == "f"){
				component1= Ext.create(
						targetObj.menuAction, {
							//closable : true,
							drilled : true,		
							//title : reportName,
							refId : targetObj.reffId,
							rptHrefQC : rptHrefQC,
							primaryKey : rptHrefQC[0].value,
							caller : this,
							disableDB : true,
							drillDownWindow : drillDownWindow
						});
			}else{
				component1= Ext.create(
						'Appsetup.view.appreportui.ReportView', {
							//closable : true,
							drilled : true,		
							//title : reportName,
							refId : reportId,
							rptHrefQC : rptHrefQC
						});
			}
			drillDownWindow.add({xtype : component1});
			drillDownWindow.show();
			// This condition is used for custom form with state function add in drill down report
			if(targetObj.stateFunctionName!=null && targetObj.stateFunctionName!=undefined){
				var functionValue=rptHrefQC[0].value;
				if(typeof(component1.controller[targetObj.stateFunctionName]) == "function") {
					component1.controller[targetObj.stateFunctionName](functionValue);
				}
			}
		}
	},
	/**On right click of grid for advance option of drilldown*/
	dataGridRightClick : function(gridView, record, item,index, e, eOpts) 
	{
		e.stopEvent();
		var xPos = e.getXY()[0];
		//var columns = gridView.getGridColumns();
		/*Used to get all column of grid including hidden columns*/
		var columns = gridView.getColumnManager().columns;
		for ( var c in columns) {
			var leftEdge = columns[c].getPosition()[0];
			var rightEdge = columns[c].getSize().width + leftEdge;
			if (xPos >= leftEdge && xPos <= rightEdge) {
				console.log(columns[c].dataIndex);
				var column = columns[c];
				var queryCriteria = {};
				var val;
				var displayval;
				var qc = [];
				val = record.data[column.qFieldName != undefined ? column.qFieldName
						: column.dataIndex];
				displayval = record.data[column.dataIndex];
				if (this.getView().reportView.rptHrefQC != undefined) {

					var t = this.getView().reportView.rptHrefQC;
					for (var x = 0; x < t.length; x++) {
						qc.push(t[x]);
					}

				}
				
				queryCriteria["name"] = column.targetRList[0].targetField!=undefined?column.targetRList[0].targetField:column.qFieldName;
				/*queryCriteria["name"] = column.qFieldName != undefined
				&& column.qFieldName.length > 0 ? column.qFieldName: column.dataIndex;*/
				queryCriteria["value"] = val;
				queryCriteria["datatype"] = column.datatype;
				queryCriteria["displayValue"] = displayval;
				queryCriteria["label"] = column.text;
				qc.push(queryCriteria);
				
				//Check whether queryContext is checked true or not
				if (column.targetRList[0].qcContext != undefined && column.targetRList[0].qcContext == 1){
                    this.getView().reportView.down('#querycriteria').controller.getQueryCriteria(qc);
                }
				
				this.showTargetReportMenu(columns,column,record, qc, e, gridView.up(),this.queryCriteria);
			}
		}
	},
	showTargetReportMenu : function(columns, column, record,queryCriteria, e, gridObj, qcPanel) {
		var targetReportList = column.targetRList;
		var menuItems = [];
		for (var x = 0; x < targetReportList.length; x++) {
			menuItems.push(Ext.create('Ext.Action', {
				text :targetReportList[x].type!=undefined && targetReportList[x].type == 'f' ?'Launch '+targetReportList[x].report_name:  targetReportList[x].report_name,
						reportId : targetReportList[x].report_id,
						targetObj:targetReportList[x],
						icon : "images/rpticon/redirect.png",
						handler : function() {
							var menuObj = this.up();
							menuObj.scope.openDrillDown(this.reportId,
									this.text, menuObj.queryCriteria,
									menuObj.gridObj,this.targetObj);
						}
			}));
		}
		menuItems.push("-");
		menuItems.push(Ext.create('Ext.Action', {
			text : "Advance",
			icon : "images/rpticon/advance.png",
			handler : function() {
				var menuObj = this.up();
				menuObj.scope.getAdvanceTargetRptWind(menuObj)
			}
		}));
		return Ext.create('Ext.menu.Menu', {
			scope : this,
			queryCriteria : queryCriteria,
			record : record.data,
			column : column,
			columns : columns,
			gridObj : gridObj,
			items : menuItems,
			qcPanel : qcPanel
		}).showAt(e.getXY());
	},

	getAdvanceTargetRptWind : function(menuObj)
	{
		return Ext.create('Ext.window.Window',{
					title : 'Advance Option',
					height : '40%',
					width : '40%',
					menuObj : menuObj,
					modal : true,
					resizable : true,
					closeAction : 'close',
					border : 0,
					layout : 'fit',
					bodyStyle : {"background-color" : "transparent"},
					plain : true,
					currentObject: this,
					items : [{
						xtype : "panel",
						autoScroll : true,
						layout :'vbox',
						items : [
						         this.getTargetReportList(menuObj.column,this),
						         {
									xtype : "fieldset",
									width : "100%",
									flex : 1,
									margin : '5 5 5 5',
									title : "Column List",
									autoScroll : true,
									items : [ this.getColumnsListTRpt(
											menuObj.columns,
											menuObj.record,
											menuObj.column)]
						         }]
					}],
					buttons : [{
						text : 'Execute',
						icon : 'images/rpticon/execute.png',
						tooltip : 'Execute',
						handler : function() {
							var windowObj = this.up().up();
							var selColumns = windowObj.query("[name=chkcolumn]");
							var tReport = windowObj.query("[comboType=targetReportSe]")[0];
							if (tReport.getValue() == null) {
								Ext.MessageBox.alert('Report Builder',"Select Target Report");
								return;
							}
							var queryC = [];
							var qcFlag = false;
							for (var x = 0; x < selColumns.length; x++) {
								
								var selCol = selColumns[x];
								var columns  = windowObj.currentObject.datagrid.getColumns();
								
								for(var cIndex =0 ; cIndex <  columns.length ; cIndex++){
									
									var columnValue = columns[cIndex];
									
									if(columnValue && columnValue.dataIndex &&  columnValue.targetRList && (selCol.dataIndex == columnValue.dataIndex)){

										var targetReport = Ext.Array.filter(columnValue.targetRList,function(rec){ return rec.report_id == tReport.getValue()});;
										if (selCol && (selCol.checked == true)) {
											if(targetReport.length>=2){
												for(var targetValue=0;targetValue<targetReport.length;targetValue++){
													var qcValue = {};
													qcValue["name"] = targetReport[targetValue].targetField;
													qcValue["value"] = selCol.inputValue;
													qcValue["datatype"]=columnValue.datatype,
													qcValue["displayValue"] = selCol.inputValue;
													qcValue["label"] = selCol.boxLabel;
													queryC.push(qcValue);
													/*if (!qcFlag && targetReport[targetValue].qcContext && targetReport[targetValue].qcContext == 1) {
														qcFlag = true;
													}*/
												}
											}else{
												var qcValue = {};
												qcValue["name"] = targetReport[0].targetField;
												qcValue["value"] = selCol.inputValue;
												qcValue["displayValue"] = selCol.inputValue;
												qcValue["label"] = selCol.boxLabel;
												queryC.push(qcValue);
												if (!qcFlag && targetReport[0].qcContext && targetReport[0].qcContext == 1) {
													qcFlag = true;
												}
											}
										}
									}
								}
							}
							
							if(qcFlag){
								windowObj.currentObject.getView().reportView.down('#querycriteria').controller.getQueryCriteria(queryC);
							}
							
							var selRpt = tReport.getStore().getAt(tReport.getStore().findExact("report_id",tReport.getValue())).data;
							var reportName = selRpt.report_name;
							windowObj.menuObj.scope.openDrillDown(tReport.getValue(),reportName,queryC,windowObj.menuObj.gridObj,selRpt);
							this.up('.window').close();
						}
					},{
						text : 'Cancel',
						icon : 'images/delete_icon.png',
						handler : function() {
							this.up('.window').close();
						}
					} ]
				}).show();

	},
	getColumnsListTRpt : function(columns, record, column) {
		var colsList = [];
		var width = 0;
		columns.forEach(function(item) {
			if (width < item.text.length) {
				width = item.text.length;
			}
		
			var val = record[item.qFieldName != undefined ? item.qFieldName
					: item.dataIndex];
			var c = {
					boxLabel : item.text + ' ('+val+')',
					name : 'chkcolumn',
					inputValue : val,
					dataIndex : item.dataIndex,
					disabled:true,
					readonly: true,
					isHidden:item.hidden==undefined?false:item.hidden
			};
			if (this.column.qFieldName == item.dataIndex) {
				c["checked"] = true;
			}
			this.colsList.push(c)
		}, {
			colsList : colsList,
			record : record,
			column : column
		});
		
		return {
			xtype : 'checkboxgroup',
			itemId:'checkboxgroupColList',
			columns : 3,
			vertical : true,
			items : colsList
		}
	},
	
	getTargetReportList : function(column,scope) {
		return new Ext.form.ComboBox({
			xtype : 'combobox',
			comboType : 'targetReportSe',
			margin : '5 5 5 5',
			labelWidth : 150,
			fieldLabel : 'Select Target Report',
			valueField : 'report_id',
			displayField : 'report_name',
			typeAhead : true,
			emptyText : 'Select Report...',
			editable : true,
			anyMatch : true,
			store : {
				autoLoad : true,
				fields : [ 'report_id', 'report_name' ],
				data : column.targetRList,
				sorters:['report_name','ASC']
			},
			listeners:{
				select:scope.onTargetReportSelect,
				scope : scope
			}
		});
	},
	
	/**Used to get target report details specially its querycriteria**/
	onTargetReportSelect:function(combo, records, eOpts ){
		var queryC = [];
		var columns  = this.datagrid.getColumns();
		var checkboxgroupColList =  combo.up().up().down("#checkboxgroupColList");


		for(var y=0;y<checkboxgroupColList.items.items.length;y++){
			checkboxgroupColList.items.items[y].setValue(false);
			checkboxgroupColList.items.items[y].setDisabled(true);
		}
		
		for(var cIndex =0 ; cIndex <  columns.length ; cIndex++){
			var columnValue = columns[cIndex];
			if(columnValue && columnValue.dataIndex &&  columnValue.targetRList){
				var targetReport = Ext.Array.filter(columnValue.targetRList,function(rec){ return rec.report_id == combo.getValue()});;
				if(targetReport.length>0) 
				{
					for(var y=0;y<checkboxgroupColList.items.items.length;y++){
						if(checkboxgroupColList.items.items[y].dataIndex == columnValue.dataIndex){
							checkboxgroupColList.items.items[y].setValue(true);
							checkboxgroupColList.items.items[y].setDisabled(false);
							checkboxgroupColList.items.items[y].setReadOnly(true);
						}
					}
				}
			}
		}
	},
	
	/**Used to filter column list according to target report querycriteria and hidden fields as well**/
	filterColumnList:function(tReportQCList,window){
		var checkboxgroupColList=window.down('#checkboxgroupColList');
		
		for(var x=0;x<tReportQCList.length;x++){
			for(var y=0;y<checkboxgroupColList.items.items.length;y++){
				if(checkboxgroupColList.items.items[y].dataIndex==tReportQCList[x].name){
					if(checkboxgroupColList.items.items[y].isHidden){
						checkboxgroupColList.items.items[y].setValue(true);
					}else{
						checkboxgroupColList.items.items[y].setDisabled(false);
						//checkboxgroupColList.items.items[y].setBoxLabel('<b>'+checkboxgroupColList.items.items[y]['boxLabel']+'</b>');
					}
				}
			}
		}
	}
});