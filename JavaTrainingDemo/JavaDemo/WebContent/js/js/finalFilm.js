// Driver ExtJs Program - PRS UI [Sakila]

/* <----Stores and Models ----> */ 

// `Movies` Model
Ext.define('Movies', {
    extend: 'Ext.data.Model',
    pageSize : 10,
    fields: ['film_id', 'title', 'description', 'release_year', 'language', 'original_language_id', 'rental_duration', 'rental_rate', 'length', 'replacement_cost', 'rating', 'special_features', 'last_update', 'director']
});

// `filmStore` Store Object to Extract Object after AJAX Call
var filmStore = Ext.create('Ext.data.Store', {
    storeId: 'filmTableStore',
    model: 'Movies',
    enablePaging: true,
    pageSize: 10,
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

// Reference Model to Create ComboBox for Language Dropdown
var languageModel = Ext.define('language', {
    extend: 'Ext.data.Model',
    fields: [{
        name: 'languageSelection',
        type: 'string'
    }]
});

// `languageDropDown` Store Object for Language Combobox
var languageDropDown = Ext.create('Ext.data.Store', { 
    model:  languageModel,  
    fields: ['languageSelection'],
    data: [
        {'languageSelection' : "English"},
        {'languageSelection' : "French"},
        {'languageSelection' : "German"},
        {'languageSelection' : "Italian"},
        {'languageSelection' : "Japanese"},
        {'languageSelection' : "Mandarin"},
        {'languageSelection' : "Mongolian"},
        {'languageSelection' : "Hindi"}
    ],
    autoLoad: true
});

// Reference Model to Create ComboBox for Rating Dropdown
var ratingModel = Ext.define('ratingsM', {
    extend: 'Ext.data.Model',
    fields: [{
        name: 'ratingSelection',
        type: 'string'
    }]
});

// `ratingDropDown` Store Object for Rating Combobox
var ratingDropDown = Ext.create('Ext.data.Store', { 
    model:  specialFeaturesModel,  
    fields: ['ratingSelection'],
    data: [
        {'ratingSelection' : "PG"},
        {'ratingSelection' : "G"},
        {'ratingSelection' : "NC-17"},
        {'ratingSelection' : "PG-13"},
        {'ratingSelection' : "PG-13"}
    ],
    autoLoad: true
});

// Reference Model to Create ComboBox for Special Features Dropdown
var specialFeaturesModel = Ext.define('speacialF', {
    extend: 'Ext.data.Model',
    fields: [{
        name: 'specialFeaturesSelection',
        type: 'string'
    }]
});

// `specialFeaturesDropDown` Store Object for Special Features Combobox
var specialFeaturesDropDown = Ext.create('Ext.data.Store', { 
    model:  specialFeaturesModel,  
    fields: ['languageSelection'],
    data: [
        {'specialFeaturesSelection' : "Trailers"},
        {'specialFeaturesSelection' : "Deleted Scenes"},
        {'specialFeaturesSelection' : "Commentaries"},
        {'specialFeaturesSelection' : "Behind the Scenes"},
    ],
    autoLoad: true
});

/* <----Windows ----> */ 

// `addWindow` Window for Add Button Functionality
var addWindow = Ext.create('Ext.window.Window', {
    title: 'Add',
    width: 400,
    layout: 'fit',
    autoDestroy: false,
    closeAction: 'close',
    items: [{
        xtype: 'form',
        bodyPadding: 5,
        items: [{
            xtype: 'textfield',
            fieldLabel: 'Title',
            id: 'movieTitle',
            name: 'movieTitle'
        }, {
            xtype: 'textarea',
            fieldLabel: 'Description',
            id: 'movieDescription',
            name: 'movieDescription'
        }, {
            xtype: 'textfield',
            fieldLabel: 'Release Year',
            id: 'movieReleaseYear',
            name: 'movieReleaseYear'
        }, {
            xtype: 'combobox',
            fieldLabel: 'Language',
            id: 'movieLanguage',
            store: languageDropDown,
            displayField: 'languageSelection',
            queryMode: 'local',
            name: 'movieLanguage'
        }, {
            xtype: 'textfield',
            fieldLabel: 'Director',
            id: 'movieDirector',
            name: 'movieDirector'
        }, {
            xtype: 'combobox',
            fieldLabel: 'Rating',
            id: 'movieRating',
            store: ratingDropDown,
            displayField: 'ratingSelection',
            queryMode: 'local',
            name: 'movieRating'
        }, {
            xtype: 'combobox',
            fieldLabel: 'Special Features',
            id: 'movieSpecialFeatures',
            store: specialFeaturesDropDown,
            displayField: 'specialFeaturesSelection',
            queryMode: 'local',
            name: 'movieSpecialFeatures'
        }],
        buttons: [{
            text: 'Add',
            handler: function() {
                // debugger;
                var addForm = this.up('form').getForm();
                var formEncoded = Ext.encode(addForm.getValues());
                console.log("Add Form: " + formEncoded)
                if(addForm.isValid()) {
                    // Insert Data
                    Ext.Ajax.request({
                        url: '/JavaDemo/AddData',
                        method: 'POST',
                        // params: {data: formEncoded},
                        // params: formEncoded,
                        params: this.up('form').getForm().getValues(),
                        success: function(response) {
                            filmStore.load({
                                params: {
                                    start: 0,
                                    limit: 10
                                }
                            });
                            addWindow.close();
                        },
                        failure: function(response) {
                            Ext.Msg.alert('Oops', 'Request Failed!');
                        }
                    });
                }
            }
        }, {
            text: 'Cancel',
            handler: function() {
                addWindow.close();
            }
        }],
        buttonAlign: 'center'
    }]
});

// `editWindow` Window for Edit Button Functionality
var editWindow = Ext.create('Ext.window.Window', {
    title: 'Edit',
    width: 400,
    layout: 'fit',
	autoDestroy: false,
	closeAction: 'close',
    items: [{ 
        xtype: 'form',
		bodyPadding: 5,
		id: 'editFormId',
	    items: [{
            xtype : 'textfield',
            fieldLabel: 'Film ID',
            id: 'editFilmId_',
            name: 'editFilmId'
        }, {
            xtype : 'textfield',
            fieldLabel: 'Title',
            id: 'editMovieTitle_',
            name: 'editMovieTitle'
        }, {
            xtype : 'textarea',
            fieldLabel: 'Description',
            id: 'editDescription_',
            name: 'editDescription'
        }, {
            xtype : 'textfield',
            fieldLabel: 'Release Year',
            id: 'editReleaseYear_',
            name: 'editReleaseYear'
        }, {
            xtype: 'combobox',
            fieldLabel: 'Language',
            store: languageDropDown,
            queryMode: 'local',
            displayField: 'languageSelection',
			id: 'editLanguage_',
            name: 'editLanguage'
        }, {
            xtype : 'textfield',
            fieldLabel: 'Director',
            id: 'editDirector_',
            name: 'editDirector'
        }, {
            xtype: 'combobox',
            fieldLabel: 'Rating',
            store: ratingDropDown,
            queryMode: 'local',
            displayField: 'ratingSelection',
			id: 'editFilmRating_',
            name: 'editFilmRating'
        }, {
            xtype: 'combobox',
            fieldLabel: 'Special Features',
            store: specialFeaturesDropDown,
            queryMode: 'local',
            displayField: 'specialFeaturesSelection',
			id: 'editSpecialFeatures_',
            name: 'editSpecialFeatures'
        }],
        
        buttons: [{
            text: 'Edit',
            handler: function() {
                Ext.getCmp('editFilmId').enable()
                // debugger;
				var editForm = this.up('form').getForm()
				var editFormEncoded = Ext.encode(editForm.getValues())
				// console.log("Edit Form: " + editFormEncoded)
                console.log(this.up('form').getForm().getValues())
                if (editForm.isValid()) {
                    Ext.Ajax.request({
                        url: '/JavaDemo/EditData',
                        method: 'POST',
						params: this.up('form').getForm().getValues(),
                        success: function(response) {
                            filmStore.load({
                                params: {
                                    start: 0,
                                    limit: 10
                                }
                            });
							editWindow.close();
                        },
                        failure: function(response) {
                            Ext.Msg.alert('Oops', 'Request Failed!');
                        }
                    });
                }
            }
        }, {
            text: 'Cancel',
            handler: function() {
                editWindow.close();
            }
        }],
        buttonAlign: 'center',
    }]
});

// `deleteWindow` Window for Delete Button Functionality
var deleteWindow = Ext.create('Ext.window.Window', {
    title: 'Delete',
    width: 400,
	html: "<p>Are you sure you want to Delete!?</p>",
    layout: 'fit',
	autoDestroy: false,
	closeAction: 'close',
    items: [{ 
        xtype: 'form',
		bodyPadding: 5,
		id: 'deleteFormId',
	    items: [{
            xtype : 'textfield',
            fieldLabel: 'Film ID',
            id: 'deleteFilmId',
            name: 'deleteFilmId',
        }],
        buttons: [{
            text: 'Delete',
            handler: function() {
                Ext.getCmp('deleteFilmId').enable()
                var deleteForm = this.up('form').getForm()
                var deleteFormEncoded = Ext.encode(form.getValues())
                console.log("Delete Form: " + deleteForm);
                if(deleteForm.isValid()) {
                    Ext.Ajax.request({
                        url: 'JavaDemo/DeleteData',
                        method: 'POST',
                        params: this.up('form').getForm().getValues(),
					    success: function(response) {
                            filmStore.load({
                                params: {
                                    start: 0,
                                    limit: 10
								}
                            });
							deleteWindow.close();
						},
						failure: function(response) {
                            Ext.Msg.alert('Oops', 'Request Failed!');
						}
					});
                }
            }
		}, {
            text: 'Cancel',
            handler: function() {
                deleteWindow.close();
			}
        }],
        buttonAlign: 'center',
    }]
});

// Main UI Rendering Function
Ext.onReady(function() {
    Ext.create('Ext.container.Viewport', {
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
            // Search Form Panel
            items: [{
                title: 'Movie Advanced Search Panel',
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
                        align: 'middle'
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
                            margin: '0 10 0 10'
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
                                filmStore.clearFilter()
                                var formInfo = {
                                    movieName_: Ext.getCmp('movieName').getValue(),
                                    directorName_: Ext.getCmp('directorName').getValue(),
                                    releaseYear_: Ext.getCmp('releaseYear').getValue().getFullYear(),
                                    language_: Ext.getCmp('language_combo').getValue()
                                }

                                // Modifying Release Year
                                // // formInfo.releaseYear_ = parseInt(String(formInfo.releaseYear_).slice(0, 4));
                                console.log("Search: " + JSON.stringify(formInfo));

                                // Using Servlet [Backend]
                                /*
                                form.submit({
                                    url: '/JavaDemo/GetFormData',
                                    method: 'POST',
                                    params: {data: JSON.stringify(formInfo),}
                                    success: function(response) {
                                        Ext.Msg.alert('Success', "Request Successful!");
                                        filmStore.load({
                                            params: {
                                                start: 0,
                                                limit: 10
                                            }
                                        })
                                    },
                                    failure: function(response) {
                                        Ext.Msg.alert('Failed', "Request Failed!");
                                    }
                                });
                                */

                                // Manual (UI Based)
                                if (!formInfo.movieName_ && !formInfo.directorName_ && !formInfo.releaseYear_ && !formInfo.language_) {
                                    Ext.Msg.alert("Blank Form!", "You've submitted a Blank Form!")
                                } 
                                else if (formInfo.movieName_ && !formInfo.directorName_ && !formInfo.releaseYear_ && !formInfo.language_) {
                                    filmStore.load().filter('title', formInfo.movieName_);
                                } 
                                else if (!formInfo.movieName_ && formInfo.directorName_ && !formInfo.releaseYear_ && !formInfo.language_) {
                                    filmStore.load().filter('director', formInfo.directorName_);
                                } 
                                else if (!formInfo.movieName_ && !formInfo.directorName_ && formInfo.releaseYear_ && !formInfo.language_) {                                   
                                    filmStore.load().filter('release_year', formInfo.releaseYear_);
                                } 
                                else if (!formInfo.movieName_ && !formInfo.directorName_ && !formInfo.releaseYear_ && formInfo.language_) {                                 
                                    filmStore.load().filter('language', formInfo.language_);
                                } 
                                else {
                                    Ext.Msg.alert("Multiple Values", "Application Still in Development!")
                                }
                            }
                        }
                    }, {
                        text: 'Reset',
                        handler: function() {
                            filmStore.clearFilter();
                            filmStore.load({
                                params: {
                                    start: 0,
                                    limit: 10
                                }
                            })
                            this.up('form').getForm().reset();
                        }
                    }]
                }]
            }, {
                // Movie Grid
                title: 'Movies DB Table',
                id: 'movieGrid',
                name: 'movieGrid',
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
                    text : 'Language',
                    flex : 1,
                    dataIndex : 'language'
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

                    'select': function() {
                        var gridData = {};
                        var selected = Ext.getCmp('movieGrid').getSelectionModel().getSelection();
                        // console.log("Selected: " + selected)
                        if(selected.length == 1) {
                            Ext.getCmp('editButton').setDisabled(false);
                            Ext.getCmp('deleteButton').setDisabled(false);
                            gridData = selected[0].data;
                            Ext.getCmp('editFormId').getForm().setValues(gridData);
							Ext.getCmp('deleteFormId').getForm().setValues(gridData);
							Ext.getCmp('editFilmId').disable();
                        }
                        else if(selected.length > 1) {
                            Ext.getCmp('editButton').setDisabled(true);
							Ext.getCmp('deleteButton').setDisabled(false);
							gridFilmIdList = [];
                            for(let i = 0; i < selected.length; i++) {
								gridFilmIdList.push(selected[i].data.film_id)
							}
							Ext.getCmp('deleteFilmId').setValue(gridFilmIdList);
							Ext.getCmp('deleteFilmId').disable();
                        }
                        else {
                            Ext.getCmp('editButton').setDisabled(true);
                            Ext.getCmp('deleteButton').setDisabled(true);
                        }
                    },

                    'deselect': function() {
                        var selected = Ext.getCmp('movieGrid').getSelectionModel().getSelection();
                        if(selected.length == 1) {
                            Ext.getCmp('editButton').setDisabled(false);
                            Ext.getCmp('deleteButton').setDisabled(false);
                        }
                        else if(selected.length == 0) {
                            Ext.getCmp('editButton').setDisabled(true);
                            Ext.getCmp('deleteButton').setDisabled(true);
                        }
                        else if(selected.length > 1) {
                            Ext.getCmp('editButton').setDisabled(true);
                            Ext.getCmp('deleteButton').setDisabled(false);
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
                    items: ['->', {
                        xtype: 'button',
                        text: 'Add',
                        iconCls: 'x-fa fa-plus-circle',
                        listeners: {
                            click: function() {
                                // Ext.Msg.alert('Voila', 'Add Button Clicked!')
                                addWindow.show()
                            }
                        }
                    }, '-', {
                        xtype: 'button',
                        text: 'Edit',
                        id: 'editButton',
                        iconCls: 'x-fa fa-pencil-square-o',
                        disabled: true,
                        listeners: {
                            click: function() {
                                // Ext.Msg.alert('Voila', 'Edit Button Clicked!')
                                editWindow.show()
                            }
                        }
                    }, '-', {
                        xtype: 'button',
                        text: 'Delete',
                        id: 'deleteButton',
                        iconCls: 'x-fa fa-trash',
                        listeners: {
                            click: function()  {
                                // Ext.Msg.alert('Voila', 'Delete Button Clicked!')
                                deleteWindow.show()
                            }
                        }
                    }]
                }]
            }]
        }],
        renderTo: 'sakilaBody'
        // renderTo: document.body
        // renderTo: Ext.getBody()
    })
})