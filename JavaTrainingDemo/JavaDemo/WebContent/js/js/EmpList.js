
Ext.application({
	name : 'Rahul',

	launch : function() {
		
		Ext.define('User', {
		    extend: 'Ext.data.Model',
		    fields: [ 'Name', 'Dept', 'Age', 'Salary']
		});
		
		var userStore = Ext.create('Ext.data.Store', {
		    model: 'User',
		    data: [
		        { Name: 'Rahul', Dept: 'ML', Age: '21', Salary: '720000' },
		        { Name: 'Saman', Dept: 'Autonomous', Age: '20', Salary: '5600000' },
		        { Name: 'Ayesha', Dept: 'ARPA', Age: '21', Salary: '7800000' },
		        { Name: 'Ahseya', Dept: 'Slimfast', Age: '49', Salary: '2300000'},
				{ Name: 'Chris', Dept: 'Analytics', Age: '37', Salary: '3400000'},
				{ Name: 'Dude', Dept: 'Freeda', Age: '89', Salary: '800000' },
				{ Name: 'Jack', Dept: 'Consulting', Age: '60', Salary: '10' },
		   	]
		});
		
		Ext.create('Ext.grid.Panel', {
		    renderTo: Ext.getBody(),
		    store: userStore,
			selModel: {
		        selType: 'checkboxmodel',
		        mode: 'MULTI',
		        checkOnly: true,
		        listeners: {
		            select: function (model, record, index, eOpts) {
		                Ext.Msg.alert('You have Selected a Row!',  userStore.data.items[index].data['Dept']);
						console.log('Name : ', userStore.data.items[index].data['Name'])
		            }
		        }
		    },
		    plugins: [{
		        ptype: 'rowediting',
		        clicksToEdit: 1
		    }],
			pageSize:4,
		    flex: 1,
		    title: 'EmpDetails',
		    columns: [
		        {
		            text: 'Name',
		            width: 200,
		            dataIndex: 'Name',
		            //flex: 1
		        },
		        {
		            text: 'Dept',
		            width: 150,
		            //flex: 1,
		            dataIndex: 'Dept'
		        },
		        {
		            text: 'Age',
		            flex: 1,
		            dataIndex: 'Age'
		        },
				{
		            text: 'Salary',
		            flex: 1,
		            dataIndex: 'Salary'
		        }
		    ],
		    
			bbar: [{
                xtype: 'pagingtoolbar',
				width: 950,
                bind:{
                    store: '{StudentListPagingStore}'
                },
                displayInfo: true,
                displayMsg: 'Displaying {0} to {1} of {2} &nbsp;records ',
                emptyMsg: "No records to display&nbsp;"
            }]
		});
		
	}
});