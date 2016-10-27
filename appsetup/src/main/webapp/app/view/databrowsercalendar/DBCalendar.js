Ext.define('Appsetup.view.databrowsercalendar.DBCalendar', {
	extend : 'Appsetup.view.databrowsercalendar.DBCalendarView',
	requires : [ 'Appsetup.view.databrowsercalendar.DBCalendarController',
	             'Appsetup.view.databrowsercalendar.DBCalendarView','Ext.layout.container.Card',
	             'Ext.calendar.view.Day', 'Ext.calendar.view.Week',
	             'Ext.calendar.view.Month',
	             'Ext.calendar.form.EventDetails',
	             'Ext.calendar.data.EventMappings'],
	controller : 'databrowsercalendar',
	items : [],
	/*listeners : {
		afterrender : 'loadData',
		scope : "controller"
	}*/
});
