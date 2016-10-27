Ext.define('Appsetup.view.appreportui.ReportViewController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.reportViewController',
	reportView:null,
	isChart:false,
	isMap:false,
	isSearchCriteria:false,
	auto_refersh_interval : null,
	reportDataJson:null,
	init : function() {
		this.control({
			"button" : {
				click : this.filterdaterangedata,
			},
			"menuitem":{
				click:this.filterdaterangedata,
			}
		});
	},
	
	afterRenderReport:function(panel){
		this.reportView=this.getView();
		//To check whether report contains proper unique reference Id
		if (panel.refId == undefined || panel.refId == null || panel.refId == "") {
			Ext.Msg.alert({
				title : 'Error',
				msg : 'Report could not be rendered.Please contact Admin.',
				icon : Ext.MessageBox.ERROR
			});
			return;
		}
		//Get Reports Details By Id
		this.initializeReportView(panel);
	},
	onReportAdded :function(){
		
		var reportview = this.getView();
		var appMainTabPanel = Ext.getCmp("appMainTabPanel");
		var tabSize=appMainTabPanel.getSize();
		reportview.setSize(tabSize.width,tabSize.height);

	},
	initializeReportView:function(panel){
		var me=this;
		Ext.Ajax.request({
			url : 'secure/dataEngineWebService/getReportDetailsById?reportId=' + panel.refId,
			method : 'POST',
			async:false,
			scope : me,
			jsonData : {},
			success : function(response,currentObject, options) {
				var responseJson = Ext.JSON.decode(response.responseText);
				if (responseJson.response.success == true) {
					var data = Ext.JSON.decode(responseJson.response.data);
					currentObject.scope.reportView.reportJSON = data;
					//Check display type as 0 is for tab view and 1 is for panel view
					if(data.data_json.displayType=="0"){
						currentObject.scope.createReportView();
					}else{
						currentObject.scope.createReportPanelView();
					}
				} else {
					Ext.Msg.alert('Error',responseJson.response.message);
				}
			},
			failure : function() {
				Ext.Msg.alert('Error','Cannot connect to server');
			}
		});
	},
	createReportPanelView:function(){
		this.reportView.add({
			xtype:'panel',
			region : "north",
			margin : '2 0 0 0',
			//title : panel.reportJSON.report_name,
			tools : this.getHeaders(this.getView().reportJSON.data_json),
			itemId:'northPanel',
			items : [{
				xtype : "chart-point",
			}, {
				xtype:'dataPointView'
			}]
		},{
			region : 'center',
			itemId : 'centerPanelViewId',
			xtype : 'panel',
			//title : this.reportView.reportJSON.report_name,
			autoScroll:true
		});
		
		this.createCenterPanelViewComponents();
	},
	createCenterPanelViewComponents:function()
	{
		var northPanel=this.reportView.down('#northPanel');
		var centerPanel=this.reportView.down('#centerPanelViewId');
		
		/**Dynamically add panel on basis of report components available for individual report**/	
		
		/**Search Criteria in North Region**/
		if(this.reportView.reportJSON.data_json.queryCWidgetU.length!=0){
			this.isSearchCriteria=true;
			this.reportDataJson=this.reportView.reportJSON.data_json;
			northPanel.add({
				tools : [{
					xtype : 'button',
					text : 'Add Search Criteria',
					handler : function(btn, eOpts) {
						var window = Ext.create('Ext.window.Window', {
							width : '50%',
							height : '50%',
							layout : 'fit',
							title : 'Query Criteria',
							modal : true,
							resizable : true,
							closeAction : 'close',
							border : 0,
							autoScroll : false,
							plain : true
						});
						window.add(Ext.create('Appsetup.view.appreportui.querycriteria.QueryCriteriaPanelView',{
							reportDataJson:btn.up("reportView").controller.reportDataJson,
							reportView:btn.up("reportView")
						}));
						window.show();
					}
				}],
			});
			northPanel.add({
				xtype:'queryCriteriaNorthPanel',
				hidden : true,
				title:'Search Criteria',
				reportView:this.reportView,
				reportDataJson:this.reportView.reportJSON.data_json
			});
		}
		/**Chart in Center Region**/
		if(this.reportView.reportJSON.chart_json.length!=0){
			this.isChart=true;
			centerPanel.add({
				itemId : "chartpanelId",
				layout:'fit',
				items:[{
					xtype:'chartViewPanel',
					reportJSON:this.reportView.reportJSON
				}]
			});
		}
		/**Grid in Center Region**/
		centerPanel.add({
			xtype:'dataGridPanel',
			items:[this.getDataGrid()],
			reportView:this.reportView,
			reportDataJson:this.reportView.reportJSON.data_json,
		});
		
		/**Map in Center Region**/
		if(this.reportView.reportJSON.advanceConfigJson!=null && this.reportView.reportJSON.advanceConfigJson.mapView.isMapView!=undefined && 
				this.reportView.reportJSON.advanceConfigJson.mapView.isMapView=="on"){
			this.isMap=true;
			centerPanel.add({
				layout : "fit",
				itemId : "mappanelId",
				//title:'Map',
				items : [ {
					xtype : 'mapPanel',
					bodyStyle : 'background:#D8D8D8'
				}]
			});
		}
	},
	createReportView:function(){
		this.reportView.add({
			xtype:'panel',
			region : "north",
			margin : '0 0 0 0',
			//title : panel.reportJSON.report_name,
			tools : this.getHeaders(this.getView().reportJSON.data_json),
			itemId:'northPanel',
			bodyStyle:{
    			background:'#f6f6f6'
			},
			items : [ {
				xtype:'dataPointView'
			},{
				xtype : "chart-point",
			}]
		},{
			region : 'center',
			itemId : 'centerTabPanelId',
			xtype : 'tabpanel',
			tabPosition : 'bottom',
			tabBar:{
				defaults:{
					height:32
				}
			},
			items:[{
				xtype:'dataGridPanel',
				icon:'images/rpticon/datagrid_icon.png',
				items:[this.getDataGrid()],
				reportView:this.reportView,
				reportDataJson:this.reportView.reportJSON.data_json,
				plugins:'responsive',
				responsiveConfig:{
					portrait: {
		                   title:''
		            },
		            landscape: {
		                   title:'Data Grid'
		            }
				}
			}]
		});
		
		this.createCenterTabPanelComponents();
	},
	
	//This method is used to add extra features to grid if mentioned in report configuration
	getDataGrid:function()
	{
		var grid = {
			xtype : 'dataGridView',
			title : this.getView().title,
			reportDataJSON:this.reportView.reportJSON.data_json,
			reportView:this.reportView,
			margin:'0 0 0 0'
		};
		
		//Check whether rowHighlight is defined in reportJson
		if(this.reportView.reportJSON.data_json.rowHighlight!=undefined)
		{
			var funcBodyObj={ 
					getRowClass: function(record, rowIndex, rowParams, store) 
					{ 
			        	var rowArray=this.up().reportDataJSON.rowHighlight;
			        	
			        	for(var i=0;i<rowArray.length;i++ )
			        	{
			        		//If row highlight is applied on date field
			        		if(rowArray[i].dateFlag!=undefined && rowArray[i].dateFlag==true){
			        			if(record.data.dateLongValue[rowArray[i].name]>=new Date(rowArray[i].from).getTime() 
			        					&& record.data.dateLongValue[rowArray[i].name]<=new Date(rowArray[i].to).getTime()){
			        				return rowArray[i].styleCss;
			        			}
			        		}
			        		//If row highlight is applied on string and integer fields
			        		else{
			        			if(rowArray[i].parameterType=="range"){
				        			if(rowArray[i].to!=""){
					        			if(record.get(rowArray[i].name)>=parseInt(rowArray[i].from)&& record.get(rowArray[i].name)<=parseInt(rowArray[i].to) )
						        			return rowArray[i].styleCss;
					        		}else{
					        			if(record.get(rowArray[i].name)>=parseInt(rowArray[i].from))
						        			return rowArray[i].styleCss;
					        		}
				        		}else if(rowArray[i].parameterType=="like"){
				        			var resArr=record.get(rowArray[i].name).match(new RegExp(rowArray[i].like,"gi"));
				        			if(resArr!=null && resArr.length>0){
				        				return rowArray[i].styleCss;
				        			}
				        		}
				        		else{
				        			if(record.get(rowArray[i].name).toString().toUpperCase()==rowArray[i].equalTo)
					        			return rowArray[i].styleCss;
				        		}
			        		}
			        	}
					} 
			}
			grid.viewConfig=funcBodyObj;
		}
		//Check whether SummaryGrid is defined in reportJson
		if (this.reportView.reportJSON.data_json.isSummaryGrid != undefined && this.reportView.reportJSON.data_json.isSummaryGrid == 1)
		{
			if (this.reportView.reportJSON.data_json.summaryGroups != undefined && this.reportView.reportJSON.data_json.summaryGroups.length > 0) {

				grid.features = [ {
					id : 'group',
					ftype : 'groupingsummary',
					groupHeaderTpl : '{name}',
					hideGroupedHeader : true,
					enableGroupingMenu : false
				}, {
					ftype : 'summary'
				} ];
				grid.split = true;
				grid.columnLines = true;
			}
		}
		return grid;
	},
	
	createCenterTabPanelComponents:function()
	{
		var centerTabPanel=this.reportView.down('#centerTabPanelId');
		
		/**Dynamically add tab on basis of report components available for individual report**/	
		
		/**Chart**/
		if(this.reportView.reportJSON.chart_json.length!=0){
			this.isChart=true;
			centerTabPanel.add({
				itemId : "chartpanelId",
				layout:'fit',
				icon:'images/rpticon/chart_icon.png',
				items:[{
					xtype:'chartViewPanel',
					reportJSON:this.reportView.reportJSON
				}],
				plugins:'responsive',
				responsiveConfig:{
					portrait: {
		                   title:''
		              },
		              landscape: {
		                   title:'Chart'
		              }
				}
			});
		}
		/**Map**/
		if(this.reportView.reportJSON.advanceConfigJson!=null && this.reportView.reportJSON.advanceConfigJson.mapView.isMapView!=undefined && 
				this.reportView.reportJSON.advanceConfigJson.mapView.isMapView=="on"){
			this.isMap=true;
			centerTabPanel.add({
				layout : "fit",
				itemId : "mappanelId",
				icon:'images/rpticon/map_icon.png',
				items : [ {
					xtype : 'mapPanel',
					bodyStyle : 'background:#D8D8D8'
				}],
				plugins:'responsive',
				responsiveConfig:{
					portrait: {
		                   title:''
		              },
		              landscape: {
		                   title:'Map'
		               }
				}
			});
		}
		/**Search Criteria**/
		if(this.reportView.reportJSON.data_json.queryCWidgetU.length!=0){
			this.isSearchCriteria=true;
			centerTabPanel.add({
				xtype:'queryCriteriaPanel',
				icon:'images/rpticon/querycriteria_icon.png',
				reportView:this.reportView,
				reportDataJson:this.reportView.reportJSON.data_json,
				plugins:'responsive',
				responsiveConfig:{
					portrait: {
		                   title:''
		              },
		              landscape: {
		                   title:'Search Criteria'
		              }
				}
			});
		}
	},
	
	/**This method is called on reportView box ready event to get all data through ajax request */
	fetchReportData:function(){
			this.getDataPointChartDataByAjax();
			this.getGridData();
			this.getMapData();
	},
	
	getDataPointChartDataByAjax:function(queryCriteriaPanelView)
	{
		var params1 = this.getChartDataParams(queryCriteriaPanelView);
		var me=this;
		Ext.Ajax.request({
			url : 'secure/dataEngineWebService/getChartData',
			method : 'POST',
			async:false,
			scope : me,
			jsonData : Ext.encode(params1),
			success : function(response,currentObject, options) {
				var responseJson = Ext.JSON.decode(response.responseText);
				if (responseJson.response.success == true) {
					var data = responseJson.response.data;
					
					currentObject.scope.loadChartData(data.charts); 
					currentObject.scope.loadDataPoint(data.datapoints,data.isDataPointGrouped,data.totalDataPoints);
					currentObject.scope.loadChartPoints(data.datapoints);
				} else {
					Ext.Msg.alert('Error',responseJson.response.message);
				}
			},
			failure : function() {
				Ext.Msg.alert('Error','Cannot connect to server');
			}
		});
	},
	
	/**Get params for chart and datapoints to be fetched*/
	getChartDataParams : function(view) {
		var params=null
		if(view==null && view==undefined){
			params = {
					report_id : this.reportView.reportJSON.report_id,
					queryCriteria : this.reportView.down('#querycriteria')!=null? this.reportView.down('#querycriteria').controller.fetchQueryCriteria():[],
					sqlId : this.reportView.reportJSON.data_json.sqlId
				};
		}else{
			params = {
					report_id : this.reportView.reportJSON.report_id,
					queryCriteria : view!=null? view.controller.fetchQueryCriteria():[],
					sqlId : this.reportView.reportJSON.data_json.sqlId
				};
		}
		return params;
	},
	
	getMapDataParams:function(){
		var params = {
				report_id : this.reportView.report_id,
				queryCriteria : this.reportView.down('#querycriteria')!=null? this.reportView.down('#querycriteria').controller.fetchQueryCriteria():[],
				sqlId : this.reportView.reportJSON.data_json.sqlId,
				map : this.reportView.reportJSON.advanceConfigJson.mapView
		};
		return params;
	},
	
	getGridParams:function(view){
		var params=null;
		if(view==undefined || view==null){
			params={
					sqlId:this.reportView.reportJSON.data_json.sqlId,
					queryCriteria:this.reportView.down('#querycriteria')!=null? this.reportView.down('#querycriteria').controller.fetchQueryCriteria():[]
				};
		}else{
			params={
					sqlId:this.reportView.reportJSON.data_json.sqlId,
					queryCriteria:view!=null? view.controller.fetchQueryCriteria():[]
				};
		}
		return params;
	},
	
	loadChartData:function(charts){
		if(this.reportView.down('#chartViewPanelId')!=null){
			this.reportView.down('#chartViewPanelId').controller.loadCharts(charts);
		}
	},
	
	loadDataPoint:function(datapoints,isDataPointGrouped,totalDp){
		if(totalDp==0){
			this.reportView.down('#northPanel').remove(this.reportView.down('#dataPointViewId'));
		}else{
			if(this.reportView.down('#dataPointViewId')==null){
				this.reportView.down('#northPanel').add({xtype:'dataPointView'});
			}
			this.reportView.down('#dataPointViewId').controller.isResized=false;
			this.reportView.down('#dataPointViewId').controller.loadDataPoints(datapoints,isDataPointGrouped,totalDp);
		}
	},
	loadChartPoints:function(datapoints){
		if(datapoints.length!=0){
			if(this.reportView.down('#chartpoint')!=null){
				this.reportView.down('#chartpoint').controller.fetchChartPoints(datapoints);
			}
		}
	},
	getGridData:function(){
		var datagrid=this.reportView.down('#dataGridViewId');
		
		var sorters = this.reportView.reportJSON.data_json.sorters != undefined ? this.reportView.reportJSON.data_json.sorters : [];
		var gridStore = this.getGridStore(this.reportView.reportJSON.data_json.sqlId, sorters);
		
		datagrid.controller.setHrefToColumns(this.reportView.reportJSON.data_json.gridColumns);
		datagrid.controller.addToolbartoGrid(datagrid, gridStore);
		datagrid.controller.initObjects(datagrid);
		datagrid.reconfigure(gridStore,this.reportView.reportJSON.data_json.gridColumns);
	},
	getGridStore:function(sqlId,sorters)
	{
		var params = this.getGridParams();
		var modelname = this.getView().id + '-gridmodel';
		Ext.define(modelname, {
			extend : 'Ext.data.Model',
			fields : this.reportView.reportJSON.data_json.gridmodel
		})
		return new Ext.create('Ext.data.Store', {
			model : modelname,
			async : false,
			autoLoad : true,
			pageSize : this.reportView.reportJSON.data_json.pageSize != undefined ? this.reportView.reportJSON.data_json.pageSize : 20,
			queryId : sqlId,
			data : [],
			groupField : this.reportView.reportJSON.data_json.summaryGroups != undefined && this.reportView.reportJSON.data_json.summaryGroups.length > 0 ? this.reportView.reportJSON.data_json.summaryGroups[0]
					: "",
			sorters : sorters,
			proxy : {
				type:'ajax',
				url : "secure/dataEngineWebService/getGridData",
				actionMethods : {
					read : 'POST',
				},
				headers : {
					'Content-Type' : 'application/json;application/xml'
				},
				paramsAsJson:true,
				extraParams : params,
				reader : {
					type : 'json',
					rootProperty : 'response.data',
					totalProperty : 'response.total'
				},
				writer : {
					type : 'json'
				}
			}
		});
	},
	
	getHeaders : function(reportJSON) {
		var qcxtypes = Ext.Array.pluck(reportJSON.queryCWidgetU, "xtype");
		var tools = [];
		if (qcxtypes.indexOf("daterange") > -1) {
			tools = [{
				xtype : "button",
				text : "This Week",
				plugins: 'responsive',
				daterangevalue : "thisweek",
				margin : "0 3 0 0",
				responsiveConfig: {
					portrait: {
                        visible: false
                    },
                   landscape: {
                        visible: true
                    }
                }
			}, {
				xtype : "button",
				text : "This Month",
				plugins: 'responsive',
				daterangevalue : "thismonth",
				margin : "0 3 0 0",
				responsiveConfig: {
					portrait: {
                        visible: false
                    },
                   landscape: {
                        visible: true
                    }
                }
			}, {
				xtype : "button",
				text : "Last 3 Months",
				plugins: 'responsive',
				daterangevalue : "last3month",
				margin : "0 3 0 0",
				responsiveConfig: {
					portrait: {
                        visible: false
                    },
                  landscape: {
                        visible: true
                    }
                }
			}, {
				xtype : "button",
				text : "Last 6 Months",
				plugins: 'responsive',
				daterangevalue : "last6month",
				margin : "0 3 0 0",
				responsiveConfig: {
                   portrait: {
                        visible: false
                    },
                    landscape: {
                        visible: true
                    }
                }
			}, {
				xtype : "button",
				text : "This Year",
				plugins: 'responsive',
				daterangevalue : "thisyear",
				margin : "0 3 0 0",
				responsiveConfig: {
					portrait: {
                        visible: false
                    },
                   landscape: {
                        visible: true
                    }
                }
			},{
				xtype:'splitbutton',
				//cls:'test',
	    		plugins: 'responsive',
	    		//arrowVisible:false,
	    		style: "border:0;background-color:#f5f5f5;padding-bottom:0px;border-radius: 10%;background-image: url('images/menu/menu.png');background-repeat: no-repeat;",
	    		menu:[
	    		      {
	    		    	  text : "This Week",
	    		    	  daterangevalue : "thisweek"
	    		      },'-',{
	    		    	  text : "This Month",
	    		    	  daterangevalue : "thismonth"
	    		      },'-',
	    		      {
	    		    	  text : "Last 3 Months",
	    				  daterangevalue : "last3month"
	    		      },'-',
	    		      {
	    		    	  text : "Last 6 Months",
	    		    	  daterangevalue : "last6month"
	    		      },'-',
	    		      {
	    		    	  text : "This Year",
	    		    	  daterangevalue : "thisyear"
	    		      }],
	    		responsiveConfig: {
	    			portrait: {
	                          visible: true
	                      },
	                landscape: {
	                          visible: false
	                      }
	                  }
			}];
		}//if ends
		return tools;
	},
	
	/**Get Map Data By Ajax Call*/
	getMapData:function()
	{
		/**If map is configured in report then only fetch map data through ajax call */
		if(this.isMap==true){
			var params1=this.getMapDataParams();
			//var mapPanelController=this.reportView.down('#googleMapPanelId').controller;
			var mapPanelView=this.reportView.down('#mappanelId').down();
			mapPanelView.configData=params1.map;
			Ext.Ajax.request({
					url : "secure/dataEngineWebService/getMapData",
					method : 'POST',
					jsonData : Ext.encode(params1),
					mapPanelView:mapPanelView,
					params : {},
					success : function(response, currentObject, options) {
						var responseJson=Ext.decode(response.responseText);
						if(responseJson.response.success==true){
							var data=responseJson.response.data;
							currentObject.mapPanelView.mapData=data.mapdata;
						}else{
							Ext.Msg.alert('Error',responseJson.response.message);
						}
					},
					failure : function() {
						Ext.Msg.alert('Error', 'Cannot connect to server');
					}
			});
		}
	},
	
	/**This method is called for auto refreshing report by setting refresh interval time*/
	updateTimer : function(me, value) {
		me.stopTimer(me);
		if (value > 0) {
			me.auto_refersh_interval = setInterval(function() {
				me.refereshReport(me)
			}, value);
		}

	},
	/**This method is called to stop the refresh interval functionality*/
	stopTimer : function(me) {
		clearTimeout(me.auto_refersh_interval);
	},
	refereshReport : function(me) {
		view = this.getView();
		try {
			btn = view.down("#refresh");
			btn.fireEvent('click', btn);
			pagingtoolbar = view.query("pagingtoolbar")[0];
			pagingtoolbar.doRefresh();
		} catch (e) {
			clearTimeout(me.auto_refersh_interval)
		}
	},
	/**On Click event of DateRange widget shortcuts used in headers as buttons, menus.*/
	filterdaterangedata : function(btn)
	{
		if (btn.hasOwnProperty("daterangevalue")) {
			// date radio is checked when clicked on filter buttons
			this.reportView.down('#querycriteria').down('#dateRangeRadio').query("[boxLabel=Default]")[0].setValue(true);
			
			var defaultDate = this.getView().down("#defaultsDate");
			if (defaultDate != null) {
				defaultDate.setValue(btn.daterangevalue);
				this.reportView.down('#querycriteria').controller.filterData();
			}
		}
	},
});