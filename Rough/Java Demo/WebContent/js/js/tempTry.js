// Method 1 for Rendering
/*
Ext.onReady(function() {                          // Render when Ready (Example of Callback Function)
	Ext.create('Ext.panel.Panel', {               // Creates the Component
    	title: 'Hello',
    	width: 200,
    	html: '<p>World!</p>',
    	renderTo: Ext.getBody()                   // Specification as in where to `render`
	});
})
*/

// Method 2 for Rendering
/*
var something = Ext.create('Ext.panel.Panel', {    // Storing the Component in a Function
    	title: 'Hello',
    	width: 200,
    	html: '<p>World!</p>',
	});

Ext.onReady(function() {
	something.render(Ext.getBody());
});
*/

// Some Advanced Rendering
var filterPanel = Ext.create('Ext.panel.Panel', {
	    bodyPadding: 5,
	    width: 300,
	    title: 'Enter your Respective Dates!',
	    items: [{
	        xtype: 'datefield',
	        fieldLabel: 'Start date'
	    }, {
	        xtype: 'datefield',
	        fieldLabel: 'End date'
	    }]
	});
	
Ext.onReady(function() {
	filterPanel.render(Ext.getBody());
});


/*

Ext.onReady(function() {
	
	Ext.define("com.code2succeed.Emp", {
			extend: "Ext.data.Model",
			fields: ["firstName", "lastName", "age", "gender"]
	});
	
	var empStore = Ext.create("Ext.data.Store", {
	model: "com.code2succeed.Emp", 
	data: [
			{'firstName': 'Rahul', 'lastName': 'Bordoloi', 'age': 21, 'gender':'M'},
			{'firstName': 'Cristiano', 'lastName': 'Ronaldo', 'age': 36, 'gender': 'M'},
			{'firstName': 'Saman', 'lastName': 'Aysha', 'age': 18, 'gender': 'F'},
			{'firstName': 'Saman', 'lastName': 'Ayesha', 'age': 20, 'gender': 'F'}
		]
	});
	
			Ext.create("Ext.grid.Panel", {
			renderTo: document.body,
			store: empStore,
			title: "Employees' Details", 
			width: 750,
			columns: [
				{
					text: 'First Name',
					dataIndex: 'firstName'
				},
				{
					text: 'Last Name',
					dataIndex: 'lastName'
				},
				{
					text: 'Age',
					dataIndex: 'age'
				},
				{
					text: 'Gender',
					dataIndex: 'gender'
				}
			],
			
			bbar: [{
                xtype: 'pagingtoolbar',
				width: 750,
                bind:{
                    store: '{StudentListPagingStore}'
                },
                displayInfo: true,
                displayMsg: 'Displaying {0} to {1} of {2} &nbsp;records ',
                emptyMsg: "No Records to Display&nbsp;"
            }]
		});
	
	var filterPanel = Ext.create('Ext.panel.Panel', {
    bodyPadding: 5, 
    width: 300,
    title: 'Enter your Details',
    items: [{
          xtype: 'textfield',
          name: 'Name',
          fieldLabel: 'Enter your Name',
          allowBlank: false,
          minLength: 6
        }, {
            xtype: 'textfield',
            name: 'email',
            fieldLabel: 'Enter Your Email',
            allowBlank: false
        }, {
		      xtype: 'datefield',
		      fieldLabel: 'Enter DOB'
    	}, {
				xtype: 'combo',
				fieldLabel: 'Enter Dept.',
				store: new Ext.data.SimpleStore({
					data: [	
						["ML"],
						["Autonomous"],
						["Slimfast"],
						["ARPA"]
						],
					fields: ['state']
				}),
				displayField: 'state',
				queryMode: 'local'
			}, {
            xtype: 'checkboxfield',
            name: 'acceptTerms',
            fieldLabel: 'Terms of Use',
            hideLabel: true,
            margin: '15 0 0 0',
            boxLabel: "Yes, I'm Sure About Submitting",
    }, {
                xtype: 'button',
                formBind: false,
                disabled: false,
                text: 'Submit',
                width: 140,
				listeners: {
                  click: function() {
                     Ext.Msg.alert('Message Prompt', 'Response Submitted!');
                  }
               }
      }
],
    renderTo: Ext.getBody()
  });
});


Ext.application({
    name : 'ExtJs App',

    launch : function() {
        Ext.Msg.alert('ExtJs App', 'Welcome to My ExtJS App!');
    }
})
*/