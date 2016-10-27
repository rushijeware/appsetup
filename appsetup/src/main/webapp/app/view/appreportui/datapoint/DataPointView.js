Ext.define('Appsetup.view.appreportui.datapoint.DataPointView', {
	extend : 'Ext.panel.Panel',
	requires:['Appsetup.view.appreportui.datapoint.DataPointViewController',
	          'Appsetup.view.fw.DataPointPanel'],
	controller : 'datapointController',
	xtype:'dataPointView',
	itemId:'dataPointViewId',
	margin:'0 0 10 0',
	bodyStyle:{
    	background:'#ffffff'
	},
	style:{
    	background:'#ffffff',
    	"box-shadow": "0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.23);"
	},
	items : [ {
		xtype : "tabbar",
		rotation : "top",
		itemId : 'maindatapointpanel',
		tabRotation : 0,
		style:{
        	background:'#ffffff'
        },
		adjustTabPositions : function() {
		}
	} ],
	listeners : {
		scope:'controller',
		resize:'onResizeDataPointView'
	}
});
