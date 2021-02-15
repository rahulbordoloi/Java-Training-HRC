// Driver ExtJs Program - PRS UI

// Program - 1

Ext.application({
	name : 'Rahul',
    launch: function() {

        // `Movies` Model
        Ext.define('Movies', {
            extend: 'Ext.data.Model',
            pageSize : 5,
            fields: ['film_id', 'title', 'description', 'release_year', 'language_id', 'original_language_id', 'rental_duration', 'rental_rate', 'length', 'replacement_cost', 'rating', 'special_features', 'last_update', 'director']
        });

        // `filmStore` Store Object to Extract Object after AJAX Call
        var filmStore = Ext.create('Ext.data.Store', {
            storeId: 'filmTableStore',
            model: 'Movies',
            enablePaging: true,
            pageSize: 5,
            proxy: {
                url: '/JavaDemo/GetData',
                type: 'ajax',
                enablePaging: true,
                reader: {
                    type: 'json'
                }
            },
            autoLoad: true,
            data: []
        });

        /*
        // Reference Store to Create Static Data Storage [Reference Object]
        var staticStore = Ext.create('Ext.data.Store', {
            storeId: 'staticTableStore',
            model: 'Movies',
            enablePaging: true,
            pageSize: 5,
            proxy: {
                type: 'memory',
                enablePaging: true,
                reader: {
                    rootProperty: 'topics',
                    totalProperty: 'totalResults'
                }
            },
            autoLoad: false,
            listeners: {
                // 'callback' : () => {
                //     staticStore.add(filmStore.getRange());
                // },
                'load': function(store, records, successful) {
                    filmStore.loadData(records, true)
                }
            },
            data: []
        });

        // Reference Store to Create ComboBox for Language Dropdown [Reference Object]
        // // var languageDropDown = Ext.data.Store({
        var languageDropDown = Ext.create('Ext.data.Store', {    
            fields: ['languageSelection'],
            data: [
                {'languageSelection' : "English"},
                {'languageSelection' :"Hindi"},
            ],
        });
        */

        // `Form` Panel for Advanced Search
        Ext.create('Ext.form.Panel', {
            renderTo: document.body,
            title: 'Movie Advanced Search Panel',		    
		    bodyPadding: 50,
			buttonAlign : 'center',   
		    items: [{
                defaultType: 'textfield',
	            layout : 'hbox',
				width : '100%',
				align: 'center',
				border: false, 
				bodyPadding: 5,			
				items: [{
                    fieldLabel: 'Movie Name',
			        name: 'movieName',
			        itemId: 'movieName',
					width: 300,
					margin:'5 50 5 150'
			    }, {
                    fieldLabel: 'Director Name',
			        name: 'directorName',
                    itemId: 'directorName',
					width: 300,
					margin: '5 50 5 50'
			    }], 
            }, {
                defaultType: 'textfield',
	            layout: 'hbox',
				width: '100%',
				align: 'center',
				border: false, 
				bodyPadding: 5,					
				items: [{
			        xtype: 'datefield',
			        fieldLabel: 'Release Year',
			        name: 'releaseYear',
                    itemId: 'releaseYear',
					width: 300,
					margin: '5 50 5 150'					
			    }, {
                    xtype: 'combobox',
                    id: 'language_combo',
                    // // store: languageDropDown,
                    name: 'language',
			        fieldLabel: 'Language',
                    itemId: 'language',

                    // Ext.getCmp('language_combo').getStore().data.items
                    // // queryMode: 'local',
                    // // displayField: 'languageSelection',
                    // // valueField: 'languageSelection',
					width: 300,
					margin: '5 50 5 50'
                }]
            }],
            buttons: [{
                text: 'Search',
                formBind: true,
                disabled: false,
                handler: function() {
                    console.log(this.up('form').items.items)
                    // this.up('form').down('panel').getComponent('movieName').getSubmitValue()
                    // const filterFormValues = form.getValues();
                    // this.up('form').down('#language').getSubmitValue()
                    // this.up('form').findField(''name').getValue
                    var form = this.up('form').getForm();
                    console.log(form);
                    // debugger;
                    if (form.isValid()) {
                        form.submit({
                            // url: '/JavaDemo/postFormData',
                            // method: 'POST',
                            // params: form,
                            success: function(form, action) {
                               Ext.Msg.alert('Success', action.result.msg);
                            },
                            failure: function(form, action) {
                                Ext.Msg.alert('Failed', action.result.msg);
                            }
                        });
                    }
                }
            },
            {
                text: 'Reset',
                handler: function() {
                    this.up('form').getForm().reset();
                }
            }],
        });

        // `Grid` Panel for Display
        Ext.create('Ext.grid.Panel', {
            renderTo: Ext.getBody(),
            store: filmStore,
            selModel: {
                selType: 'checkboxmodel',
		        mode: 'MULTI',
		        checkOnly: true,
		        listeners: {
		            select: function (model, record, index, eOpts) {
		                Ext.Msg.alert('Movie Selected!', filmStore.data.items[index].data['title']);
						console.log(filmStore.data.items[index].data['title'])
		            },
		        }
            },
            plugins : [{
                ptype: 'rowediting',
		        clicksToEdit: 1
            }],
            pageSize: 5,
            height: 450,
            flex: 1,
            title: 'Movies DB Table',
            store: Ext.data.StoreManager.lookup('filmTableStore'),
            dockedItems: [{
	            xtype: 'pagingtoolbar',
	            store: Ext.data.StoreManager.lookup('filmTableStore'),
	            displayInfo: true,
	            dock: 'bottom',
                // pageSize: 5,
                displayMsg: 'Displaying: {0} to {1} out of {2} &nbsp;records ',
                emptyMsg: "No records to display&nbsp;"
         	}],
            columns: [{
                text : 'Film ID',
                flex : 1,
                dataIndex : 'film_id'
            }, {
                text : 'Title',
                flex : 1,
                dataIndex : 'title'
            }, {
                text : 'Description',
                flex : 1,
                dataIndex : 'description'
            }, {
                text : 'Release Year',
                flex : 1,
                dataIndex : 'release_year'
            }, {
                text : 'Language ID',
                flex : 1,
                dataIndex : 'language_id'
            }, {
                text : 'Original Language ID',
                flex : 1,
                dataIndex : 'original_language_id'
            }, {
                text : 'Rental Duration',
                flex : 1,
                dataIndex : 'rental_duration'
            }, {
                text : 'Rental Rate',
                flex : 1,
                dataIndex : 'rental_rate'
            }, {
                text : 'Length',
                flex : 1,
                dataIndex : 'length'
            }, {
                text : 'Replacement Cost',
                flex : 1,
                dataIndex : 'replacement_cost'
            }, {
                text : 'Rating',
                flex : 1,
                dataIndex : 'rating'
            }, {
                text : 'Special Features',
                flex : 1,
                dataIndex : 'special_features'
            }, {
                text : 'Last Updated',
                flex : 1,
                dataIndex : 'last_update'
            }, {
                text : 'Director',
                flex : 1,
                dataIndex : 'director'
            }],
        });
    }
});


// Program - 2
/*
Ext.onReady(function(){
    
    Ext.create("Ext.panel.Panel", {
        
        width : 1500,
        height : 700,
        renderTo : Ext.getBody(),
        layout : 'fit',
        items : [{
            xtype : 'grid',
            selModel: {
                selType: 'checkboxmodel',
                mode: 'MULTI',
                checkOnly: true,
                listeners: {
                    select: function (model, record, index, eOpts) {
                    Ext.Msg.alert('Movie Selected!', store.data.items[index].data['Title']);
                    console.log(store.data.items[index].data['Title'])
                    }
                }
            },
        
            listeners : {
                boxready : function(obj) {
                    
                    Ext.Ajax.request({
                        url: 'http://localhost:8080/JavaDemo/GetData',
                        method: 'GET',
                        timeout: 60000,
                        params: {

                        },
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        success: function (response) {
                            var responseResult = Ext.decode(response.responseText)
                            console.log(responseResult)
                            var storeContent = obj.getStore();
                            storeContent.getProxy().setData(responseResult);
                            storeContent.load();
                            console.log(storeContent)
                            Ext.Msg.alert('Status', 'Request Succesful!');
                        },
                        failure: function (response) {
                            console.log("Failed Status: " + response.status)
                            Ext.Msg.alert('Status', 'Request Failed!');
                        }
                    });
                }
            },
            columns : [{
                text : 'Film ID',
                flex : 1,
                dataIndex : 'film_id'
            }, {
                text : 'Title',
                flex : 1,
                dataIndex : 'title'
            }, {
                text : 'Description',
                flex : 1,
                dataIndex : 'description'
            }, {
                text : 'Release Year',
                flex : 1,
                dataIndex : 'release_year'
            }, {
                text : 'Language ID',
                flex : 1,
                dataIndex : 'language_id'
            }, {
                text : 'Original Language ID',
                flex : 1,
                dataIndex : 'original_language_id'
            }, {
                text : 'Rental Duration',
                flex : 1,
                dataIndex : 'rental_duration'
            }, {
                text : 'Rental Rate',
                flex : 1,
                dataIndex : 'rental_rate'
            }, {
                text : 'Length',
                flex : 1,
                dataIndex : 'length'
            }, {
                text : 'Replacement Cost',
                flex : 1,
                dataIndex : 'replacement_cost'
            }, {
                text : 'Rating',
                flex : 1,
                dataIndex : 'rating'
            }, {
                text : 'Special Features',
                flex : 1,
                dataIndex : 'special_features'
            }, {
                text : 'Last Updated',
                flex : 1,
                dataIndex : 'last_update'
            }, {
                text : 'Director',
                flex : 1,
                dataIndex : 'director'
            }],
            store : {
                fields : ['film_id', 'title', 'description', 'release_year', 'language_id', 'original_language_id', 'rental_duration', 'rental_rate', 'length', 'replacement_cost', 'rating', 'special_features', 'last_update', 'director'],
                flex : 1,
                data : []
            },
            bbar: [{
                xtype: 'pagingtoolbar',
                width: 1500,
                // bind:{
                //     store: '{StudentListPagingStore}'
                // },
                displayInfo: true,
                dock: 'bottom',
                displayMsg: 'Displaying {0} to {1} of {2} &nbsp;records ',
                emptyMsg: "No records to display&nbsp;"
            }]
        }]
    })
});
*/

// Program - 3
/*
Ext.application({
	name : 'Rahul',

	launch : function() {
		
		Ext.define('Movies', {
         extend: 'Ext.data.Model',
		 pageSize: 5,
         fields: ['film_id', 'title', 'description', 'release_year', 'language_id', 'original_language_id', 'rental_duration', 'rental_rate', 'length', 'replacement_cost', 'rating', 'special_features', 'last_update', 'director']
     	});
		
		var userStore = Ext.create('Ext.data.Store', {
			storeId:'movieTableStore',
		    model: 'Movies',
            enablePaging: true,
			pageSize:5,
			proxy: {
                 type: 'memory',
                 enablePaging: true,
                 reader: {
                     rootProperty: 'topics',
                     totalProperty: 'totalResults'
                 }
            },
            items: [
                {
                    listeners : {

                        boxready : function(obj) {

                            Ext.Ajax.request({
                                
                                url: 'http://localhost:8080/JavaDemo/GetData',
                                method: 'GET',
                                timeout: 60000,
                                params: {
                        
                                },
                                headers: {
                                    'Content-Type': 'application/json'
                                },
                                success: function (response) {
                                    var responseResult = Ext.decode(response.responseText)
                                    console.log(responseResult)
                                    var storeContent = obj.getStore();
                                    // var storeContent = obj.down("grid").getStore();
                                    // storeContent.loadData(responseResult);
                                    storeContent.getProxy().setData(responseResult);
                                    storeContent.load();
                                    console.log(storeContent)
                                    Ext.Msg.alert('Status', 'Request Succesful!');
                                },
                                failure: function (response) {
                                    console.log("Failed Status: " + response.status)
                                    Ext.Msg.alert('Status', 'Request Failed!');
                                }
                            });
                        }
                    }
	            }],
		    data: []
		});
		
		Ext.create('Ext.form.Panel', {
		    renderTo: document.body,
		    title: 'Movie Advanced Search Panel',		    
		    bodyPadding: 50,
			buttonAlign : 'center',   
		    items: [{
					defaultType: 'textfield',
	                layout : 'hbox',
					width : '100%',
					align:'center',
					border: false, 
					bodyPadding: 5,			
					items:[
			        {
			            fieldLabel: 'Movie Name',
			            name: 'movieName',
						width:300,
						margin:'5 50 5 150'
			        },
			        {
			            fieldLabel: 'Director Name',
			            name: 'directorName',
						width:300,
						margin:'5 50 5 50'
			        }],
					}, {
					defaultType: 'textfield',
	                layout : 'hbox',
					width : '100%',
					align:'center',
					border: false, 
					bodyPadding: 5,					
					items:[
			        {
			            xtype: 'datefield',
			            fieldLabel: 'Release Year',
			            name: 'releaseYear',
						width:300,
						margin:'5 50 5 150'					
			        },
					{
						xtype: 'combobox',
			            fieldLabel: 'Language',
			            name: 'language',
						width:300,
						margin:'5 50 5 50'					
			        }]
				}
		    ],

			buttons: [{
				        text: 'Search',
				        formBind: true,
				        disabled: true,
				        handler: function() {
				            var form = this.up('form').getForm();
				            if (form.isValid()) {
				                form.submit({
				                    success: function(form, action) {
				                       Ext.Msg.alert('Success', action.result.msg);
				                    },
				                    failure: function(form, action) {
				                        Ext.Msg.alert('Failed', action.result.msg);
				                    }
				                });
				            }
				        }
				    },
					{
				        text: 'Reset',
				        handler: function() {
				            this.up('form').getForm().reset();
				        }
				    }],
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
		                Ext.Msg.alert('Movie Selected!', userStore.data.items[index].data['Title']);
						console.log(userStore.data.items[index].data['Title'])
		            }
		        }
		    },
		    plugins: [{
		        ptype: 'rowediting',
		        clicksToEdit: 1
		    }],
			pageSize:4,
		    flex: 1,
		    title: 'Movies DB Table',
			store: Ext.data.StoreManager.lookup('movieTableStore'),
         	dockedItems: [{
	            xtype: 'pagingtoolbar',
	            store: Ext.data.StoreManager.lookup('movieTableStore'),
	            displayInfo: true,
	            dock: 'bottom',
                displayMsg: 'Displaying: {0} to {1} out of {2} &nbsp;records ',
                emptyMsg: "No records to display&nbsp;"
         	}],
		    columns: [
                {
                    text : 'Film ID',
                    flex : 1,
                    dataIndex : 'film_id'
                }, {
                    text : 'Title',
                    flex : 1,
                    dataIndex : 'title'
                }, {
                    text : 'Description',
                    flex : 1,
                    dataIndex : 'description'
                }, {
                    text : 'Release Year',
                    flex : 1,
                    dataIndex : 'release_year'
                }, {
                    text : 'Language ID',
                    flex : 1,
                    dataIndex : 'language_id'
                }, {
                    text : 'Original Language ID',
                    flex : 1,
                    dataIndex : 'original_language_id'
                }, {
                    text : 'Rental Duration',
                    flex : 1,
                    dataIndex : 'rental_duration'
                }, {
                    text : 'Rental Rate',
                    flex : 1,
                    dataIndex : 'rental_rate'
                }, {
                    text : 'Length',
                    flex : 1,
                    dataIndex : 'length'
                }, {
                    text : 'Replacement Cost',
                    flex : 1,
                    dataIndex : 'replacement_cost'
                }, {
                    text : 'Rating',
                    flex : 1,
                    dataIndex : 'rating'
                }, {
                    text : 'Special Features',
                    flex : 1,
                    dataIndex : 'special_features'
                }, {
                    text : 'Last Updated',
                    flex : 1,
                    dataIndex : 'last_update'
                }, {
                    text : 'Director',
                    flex : 1,
                    dataIndex : 'director'
                }
		    ],
		});		
	}
});
*/