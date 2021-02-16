// Driver ExtJs Program for PRS Implementation
Ext.application({
	name : 'Rahul',
    launch: function() {

        // `Movies` Model
        Ext.define('Movies', {
            extend: 'Ext.data.Model',
            pageSize : 5,
            fields: ['film_id', 'title', 'description', 'release_year', 'language_id', 'original_language_id', 'rental_duration', 'rental_rate', 'length', 'replacement_cost', 'rating', 'special_features', 'last_update', 'director']
        });

        // `filmStore` Store 
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
                    type: 'json',
                    totalProperty: 'totalCount',
                    rootProperty: 'filmData',
                    successProperty: 'success'
                }
            },
            autoLoad: true,
            data: []
        });

        // Reference Store to Create ComboBox for Language Dropdown [Reference Object]
        var languageModel = Ext.define('language', {
            extend: 'Ext.data.Model',
            fields: [{
                name: 'languageSelection',
                type: 'string'
            }]
        })

        var languageDropDown = Ext.create('Ext.data.Store', { 
            model:  languageModel,  
            fields: ['languageSelection'],
            data: [
                {'languageSelection' : "English"},
                {'languageSelection' : "Hindi"},
            ],
            autoLoad: true
        });

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
					width: 300,
					margin:'5 50 5 150'
			    }, {
                    fieldLabel: 'Director Name',
			        name: 'directorName',
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
					width: 300,
					margin: '5 50 5 150'					
			    }, {
                    xtype: 'combobox',
                    id: 'language_combo',
                    store: languageDropDown,
			        fieldLabel: 'Language',
                    itemId: 'language',
			        name: 'language',
					width: 300,
                    displayField: 'languageSelection',
                    valueField: 'languageSelection',
					margin: '5 50 5 50'
                }]
            }],
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
            flex: 1,
            title: 'Movies DB Table',
            store: Ext.data.StoreManager.lookup('filmTableStore'),
            dockedItems: [{
	            xtype: 'pagingtoolbar',
	            store: Ext.data.StoreManager.lookup('filmTableStore'),
	            displayInfo: true,
	            dock: 'bottom',
                // pageSize: 5,
                displayMsg: 'Displaying: {0} to {1} out of {2} &nbsp;Records ',
                emptyMsg: "No Records to display&nbsp;"
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