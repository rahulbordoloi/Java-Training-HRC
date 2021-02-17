Ext.onReady(function () {
    Ext.create('Ext.container.Viewport', {
	    // Require the Pictos font package
		"requires": [
		   "font-pictos"
		],
        layout: {
            type: 'fit',
            pack: 'center',
            align: 'middle'
        },
        items: [{
            xtype: 'panel',
            layout: {
                type: 'border',
                pack: 'center',
                align: 'middle'
            },
            items: [{
                title: 'Movie Advance Search',
                region: 'center',
                xtype: 'panel',
                layout: {
                    type: 'fit',
                    pack: 'center',
                    align: 'middle'
                },
                margin: '5 5 5 5',
                items: [{
                    
                        xtype: 'form',
                        bodyPadding: 5,
                        autoScroll: true,
                        buttonAlign: 'center',
                        layout: {
                            type: 'anchor',
                            pack: 'center',
                            align: 'middle' // 365
                        },
                        defaults: {
                            anchor: '100%'
                        },
                        url: 'add_item',
                        defaultType: 'textfield',
                        items: [{
                            xtype: 'fieldcontainer',
                            margin: '20 0 0 0',
                            bodyPadding: 5,
                            layout: {
                                type: 'hbox',
                                pack: 'center',
                                align: 'middle'
                            },
                            items: [{
                                xtype: 'textfield',
                                fieldLabel: 'Movie Name',
                                name: 'movieName',
                                id: 'movieName'
                            }, {
                                xtype: 'splitter',
                                margin: '0 10 0 10'
                            }, {
                                xtype: 'textfield',
                                fieldLabel: 'Director Name',
                                name: 'directorName',
                                id: 'directorName'
                            }]
                        }, {
                            xtype: 'fieldcontainer',
                            margin: '10 0 0 0',
                            bodyPadding: 5,
                            layout: {
                                type: 'hbox',
                                pack: 'center',
                                align: 'middle' 
                            },
                            items: [{
                                xtype: 'datefield',
                                format: 'Y',
                                fieldLabel: 'Release Year',
                                name: 'releaseYear',
                                id: 'releaseYear'
                            }, {
                                xtype: 'splitter',
                                margin: '0 10 10 0'
                            }, {
                                xtype: 'combobox',
                                fieldLabel: 'Language',
                                name: 'language_combo',
                                id: 'language_combo',
                                store: languageDropDown,
                                queryMode: 'local',
                                displayField: 'languageSelection',
                                valueField: 'languageSelection',
                            }]
                        }],
                        buttons: [{
                            text: 'Search',
                            formBind: true,
                            disabled: false,
                            enableToggle: false,
                            handler: function() {
                                var form = this.up('form').getForm();
                                if(form.isValid()){
                                    var formInfo = {
                                        movieName_: Ext.getCmp('movieName').getValue(),
                                        directorName_: Ext.getCmp('directorName').getValue(),
                                        releaseYear_: Ext.getCmp('releaseYear').getValue(),
                                        language_: Ext.getCmp('language_combo').getValue()
                                    }
                                    console.log(JSON.stringify(formInfo));
                                    form.submit({
                                        // url: '/JavaDemo/postFormData',
                                        // method: 'POST',
                                        // params: form,
                                        success: function(form, action) {
                                            console.log(JSON.stringify(form));
                                            Ext.Msg.alert('Success', action.result.msg);
                                        },
                                        failure: function(form, action) {
                                            Ext.Msg.alert('Failed', action.result.msg);
                                        }
                                    });
                                }
                            }
                        }, {
                            text: 'Reset',
                            handler: function() {
                            this.up('form').getForm().reset();
                            }
                        }]
                    
						}]
            }, {
					
                title: 'Movies DB Table',
                id: 'movieGrid',
                region: 'south',
                xtype: 'grid',
                height: '60%',
                minHeight: 100,
                split: true,
                margin: '0 5 5 5',
                store: filmStore,
                columns: [{
                    text : 'Film ID',
                    flex : 1,
                    dataIndex : 'film_id'
                }, {
                    text : 'Title',
                    flex : 2,
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
                selModel: {
                    selType: 'checkboxmodel',
                    mode: 'MULTI',
                    checkOnly: true
                },
                listeners: {
                    'select': () => {
                        let gridData = {};
                        let selected = Ext.getCmp('movieGrid').getSelectionModel().getSelection();
                        console.log("Selected: " + selected)
                        if(selected.length == 1) {
                            Ext.getCmp('editButton').setDisabled(false);
                            Ext.getCmp('deleteButton').setDisabled(false);
                            gridData = selected[0].data;
                        }
                        else {
                            Ext.getCmp('editButton').setDisabled(true);
                            Ext.getCmp('deleteButton').setDisabled(true);
                        }
                    },
                    'deselect': () => {
                        let gridData = {};
                        let selected = Ext.getCmp('movieGrid').getSelectionModel().getSelection();
                        if(selected.length == 1) {
                            Ext.getCmp('editButton').setDisabled(false);
                            Ext.getCmp('deleteButton').setDisabled(false);
                            gridData = selected[0].data;
                        }
                        else {
                            Ext.getCmp('editButton').setDisabled(true);
                            Ext.getCmp('deleteButton').setDisabled(true);
                        }
                    } 
                },
                dockedItems: [{
                    xtype: 'pagingtoolbar',
                    store:  Ext.data.StoreManager.lookup('filmTableStore'),
                    dock: 'top',
                    displayInfo: true,
                    displayMsg: 'Displaying: {0} to {1} out of {2} &nbsp;Records ',
                    emptyMsg: "No Records to Display!&nbsp;",
                    items: ['->', '->', '->', {
                        xtype: 'button',
                        text: 'Add',
                        iconCls: 'x-fa fa-plus-circle',
                        listeners: {
                            click: () => {
                            Ext.Msg.alert('Voila', 'Add Button Clicked!')
                            // addWindow.show()
                            }
                        }
                    }, '-', {
                        xtype: 'button',
                        text: 'Edit',
                        id: 'editButton',
                        iconCls: 'x-fa fa-pencil-square-o',
                        disabled: true,
                        listeners: {
                            click: () => {
                            Ext.Msg.alert('Voila', 'Edit Button Clicked!')
                            }
                        }
                    }, '-', {
                        xtype: 'button',
                        text: 'Delete',
                        id: 'deleteButton',
                        iconCls: 'x-fa fa-trash',
                        listeners: {
                            click: () => {
                            Ext.Msg.alert('Voila', 'Delete Button Clicked!')
                            }
                        }
                    }]
                }]
            
				}]
        }],
        renderTo: 'newBody'
    });
});