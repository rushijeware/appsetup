Ext.define('Appsetup.view.appreportui.ReportView', {
	extend : 'Ext.form.Panel',
	requires : ['Appsetup.view.appreportui.ReportViewController',
	            'Appsetup.view.appreportui.datagrid.DataGridPanel',
	            'Appsetup.view.appreportui.datagrid.DataGridView',
	            'Appsetup.view.appreportui.querycriteria.QueryCriteriaView',
	            'Appsetup.view.appreportui.chart.ChartView',
	            'Appsetup.view.appreportui.datapoint.DataPointView',
	            'Appsetup.view.googlemaps.map.MapPanel',
	            'Appsetup.view.appreportui.chartpoint.ChartPointView'
	            ],
	xtype : 'reportView',
	controller : 'reportViewController',
	layout : 'border',
	reportJSON:null,
	bodyStyle:{
        background:'#f6f6f6'
    },
	listeners : {
		scope : 'controller',
		afterrender : 'afterRenderReport',
		boxready : 'fetchReportData',
		added:'onReportAdded'
	}
});
