Ext.define('Appsetup.view.usermanagement.users.UserRoleMainView', {
	extend : 'Ext.tab.Panel',
	requires : ['Appsetup.view.usermanagement.users.AddUserRole',
	            'Appsetup.view.usermanagement.users.EditUserRole'],
	xtype : 'userRoleMainView',
	margin : '3 0 0 0',
	items:[{
				xtype:'addUserRole',
				title:'Add User Role Mapping',
				iconCls:'newTabIcon',
				tooltip:'Add user to role mapping'
			},
			{
				xtype:'editUserRole',
				title:'Edit User Role Mapping',
				iconCls:'editTabIcon',
				tooltip:'Edit user to role mapping'
			}]
});