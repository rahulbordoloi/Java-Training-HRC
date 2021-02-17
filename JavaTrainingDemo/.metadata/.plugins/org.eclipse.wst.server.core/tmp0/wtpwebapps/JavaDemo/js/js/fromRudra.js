/**
 * 
 */

Ext.define('MovieData', {
    extend: 'Ext.data.Model',
    fields: ['film_id', 'Title', 'Description', 'release_year', 'language', 'Director', 'Rating', 'special_features']
});

var userStore = Ext.create('Ext.data.Store', {
    model: 'MovieData',
	proxy: {
        type: 'ajax',
		url : 'http://localhost:8080/TrainingExtJS/FetchDataServlet',
		enablePaging: true,
		reader: {
			type: 'json',
			rootProperty: "data",
			totalProperty: "totalCount"
		}
    },
	pageSize: 15
});

userStore.load({
	params: {
		start: 0,
		limit: 15
	}
});

var searchStore = Ext.create('Ext.data.Store', {
    model: 'MovieData',
	proxy: {
        type: 'ajax',
		url : 'http://localhost:8080/TrainingExtJS/FetchFormServlet',
		enablePaging: true,
		reader: {
			type: 'json',
			rootProperty: "data",
			totalProperty: "totalCount"
		}
    },
	pageSize: 15
});

var languageChoice = Ext.create('Ext.data.Store', {
    fields: ['language'],
    data: [{
        "language": "English"
    }, {
        "language": "Italian"
    }, {
        "language": "Japanese"
    },{
        "language": "Mandarin"
    }, {
        "language": "French"
    }, {
        "language": "German"
    }, {
        "language": "Mongolian"
    }]
});

var RatingsChoice = Ext.create('Ext.data.Store', {
    fields: ['language'],
    data: [{'rating': 'G'
			}, {'rating': 'PG'
			}, {'rating': 'PG-13'
			}, {'rating': 'R'
			}, {'rating': 'NC-17'
			}]
});

var SFChoice = Ext.create('Ext.data.Store', {
    fields: ['language'],
    data: [{'sf': 'Trailers'
			}, {'sf': 'Commentaries'
			}, {'sf': 'Deleted Scenes'
			}, {'sf': 'Behind the Scenes'
			}]
});

var addWin = Ext.create('Ext.window.Window', {
    title: 'Add',
    width: 400,
    layout: 'fit',
	autoDestroy: false,
	closeAction: 'close',
    items: [{ 
        xtype: 'form',
		bodyPadding: 5,
	    items: [{
                  xtype : 'textfield',
                  fieldLabel: 'Title',
	              name: 'movie_name',
				  id: 'movie_title'
               },{
                  xtype : 'textarea',
                  fieldLabel: 'Description',
				  name: 'movie_description',
				  id: 'movie_desc'
               },{
                  xtype : 'textfield',
                  fieldLabel: 'Release Year',
                  name: 'movie_release_year',
				  id: 'movie_ry'
               },{
                            xtype: 'combobox',
                            fieldLabel: 'Language',
                            store: languageChoice,
                            queryMode: 'local',
                            displayField: 'language',
                            name: 'movie_language',
							id: 'movie_lang'
                        },{
                  xtype : 'textfield',
                  fieldLabel: 'Director',
                  name: 'movie_director',
				  id: 'movie_dir'
               },{
                            xtype: 'combobox',
                            fieldLabel: 'Rating',
                            store: RatingsChoice,
                            queryMode: 'local',
                            displayField: 'rating',
                            name: 'movie_rating',
							id: 'movie_rate'
                        },{
                            xtype: 'combobox',
                            fieldLabel: 'Special Features',
                            store: SFChoice,
                            queryMode: 'local',
                            displayField: 'sf',
                            name: 'movie_special_features',
							id: 'movie_sf'
                        }],
               
               buttons: [{
                  text: 'Add',
                  handler: function(){
						//Ext.Msg.alert('Add', 'Your msg is saved');
						var form = this.up('form').getForm();
						var formEnc = Ext.encode(form.getValues());
						console.log(formEnc);
                            if (form.isValid()) {
                                Ext.Ajax.request({
						            url: 'http://localhost:8080/TrainingExtJS/InsertData',
						            method: 'POST',
						            params: {key: formEnc},
						            
						            success: function (response) {
						            		userStore.load({
												params: {
													start: 0,
													limit: 15
												}
											});
											addWin.close();
						            },
						            failure: function (response) {
						                Ext.Msg.alert('Status', 'Request Failed.');
						
						            }
						        });
								console.log(form.getValues())
                            }
                        }
				  },{
                  text: 'Cancel',
                  handler: function(){
                     addWin.close();
				   }
               }],
               buttonAlign: 'center',
    }]
});


var editWin = Ext.create('Ext.window.Window', {
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
	              name: 'film_id',
				  id: 'edit_film_id'
               }, {
                  xtype : 'textfield',
                  fieldLabel: 'Title',
	              name: 'Title',
				  id: 'edit_title'
               },{
                  xtype : 'textarea',
                  fieldLabel: 'Description',
				  name: 'Description',
				  id: 'edit_desc'
               },{
                  xtype : 'textfield',
                  fieldLabel: 'Release Year',
                  name: 'release_year',
				  id: 'edit_ry'
               },{
                            xtype: 'combobox',
                            fieldLabel: 'Language',
                            store: languageChoice,
                            queryMode: 'local',
                            displayField: 'language',
                            name: 'language',
							id: 'edit_lang'
                        },{
                  xtype : 'textfield',
                  fieldLabel: 'Director',
                  name: 'Director',
				  id: 'edit_dir'
               },{
                            xtype: 'combobox',
                            fieldLabel: 'Rating',
                            store: RatingsChoice,
                            queryMode: 'local',
                            displayField: 'rating',
                            name: 'Rating',
							id: 'film_rate'
                        },{
                            xtype: 'combobox',
                            fieldLabel: 'Special Features',
                            store: SFChoice,
                            queryMode: 'local',
                            displayField: 'sf',
                            name: 'special_features',
							id: 'edit_sf'
                        }],
               
               buttons: [{
                  text: 'Edit',
                  handler: function(){
						// ajax call by get form
						//Ext.Msg.alert('Edit', 'Your msg is edited');
						Ext.getCmp('edit_film_id').enable();
						var form = this.up('form').getForm();
						var formEncEdit = Ext.encode(form.getValues());
						console.log(formEncEdit);
                            if (form.isValid()) {
                                Ext.Ajax.request({
						            url: 'http://localhost:8080/TrainingExtJS/EditData',
						            method: 'POST',
						            params: {edit: formEncEdit},
						            
						            success: function (response) {
						            		userStore.load({
												params: {
													start: 0,
													limit: 15
												}
											});
											editWin.close();
						            },
						            failure: function (response) {
						                Ext.Msg.alert('Status', 'Request Failed.');
						
						            }
						        });
								console.log(form.getValues())
                            }
                        }
				  },{
                  text: 'Cancel',
                  handler: function(){
                     editWin.close();
				   }
               }],
               buttonAlign: 'center',
    }]
});

var deleteWin = Ext.create('Ext.window.Window', {
    title: 'Edit',
    width: 400,
	html: "<p>Are you sure you want to delete?</p>",
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
	              name: 'film_id',
				  id: 'delete_film_id'
               }],
               
               buttons: [{
                  text: 'Delete',
                  handler: function(){
						// ajax call by get form
						//Ext.Msg.alert('Edit', 'Your msg is edited');
						Ext.getCmp('delete_film_id').enable();
						var form = this.up('form').getForm();
						var formEncDel = Ext.encode(form.getValues());
						console.log(formEncDel);
                            if (form.isValid()) {
                                Ext.Ajax.request({
						            url: 'http://localhost:8080/TrainingExtJS/DeleteData',
						            method: 'POST',
						            params: {delete: formEncDel},
						            
						            success: function (response) {
						            		userStore.load({
												params: {
													start: 0,
													limit: 15
												}
											});
											deleteWin.close();
						            },
						            failure: function (response) {
						                Ext.Msg.alert('Status', 'Request Failed.');
						
						            }
						        });
								console.log(form.getValues())
                            }
                        }
				  },{
                  text: 'Cancel',
                  handler: function(){
                     deleteWin.close();
				   }
               }],
               buttonAlign: 'center',
    }]
});

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
                            name: 'Title',
							id: 'title'
                        }, {
                            xtype: 'splitter',
							margin: '0 10 0 10'
                        }, {
                            xtype: 'textfield',
                            fieldLabel: 'Director',
                            name: 'Director',
							id: 'director'
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
                            xtype: 'textfield',
                            fieldLabel: 'Release Year',
                            name: 'release_year',
							id: 'ry'
                        }, {
                            xtype: 'splitter',
							margin: '0 10 0 10'
                        }, {
                            xtype: 'combobox',
                            fieldLabel: 'Language',
                            store: languageChoice,
                            queryMode: 'local',
                            displayField: 'language',
                            name: 'language',
							id: 'lang'
                        }]
                    }],
                    buttons: [{
                        text: 'Search',
                        formBind: true, //only enabled once the form is valid
                        disabled: true,
                        enableToggle: false,
                        handler: function () {
                            var form = this.up('form').getForm();
							/*var formEncSearch = Ext.encode(form.getValues());
							console.log(formEncSearch);
                            if (form.isValid()) {
	
								Ext.Ajax.request({
						            url: 'http://localhost:8080/TrainingExtJS/FetchFormServlet',
						            method: 'POST',
						            params: {search: formEncSearch},
						            
						            success: function (response) {
						            		searchStore.load({
												params: {
													start: 0,
													limit: 15
												}
											});
						            },
						            failure: function (response) {
						                Ext.Msg.alert('Status', 'Request Failed.');
						
						            }
						        });
								console.log(form.getValues())*/
								console.log(form.getValues())
								userStore.clearFilter();
								var searchValueTitle = Ext.getCmp("title").getValue(); //get new value 
								var searchValueDirector = Ext.getCmp("director").getValue(); //get new value
								var searchValueReleaseYear = Ext.getCmp("ry").getValue(); //get new value
								var searchValueLanguage = Ext.getCmp("lang").getValue(); //get new value
								
								// console.log(searchValueTitle, searchValueDirector, searchValueReleaseYear, searchValueLanguage);
								if (!searchValueTitle && !searchValueDirector && !searchValueReleaseYear && !searchValueLanguage) {
								    Ext.Msg.alert("Blank Form", "You submitted a blank form!")// return a error message
								} else if (searchValueTitle && !searchValueDirector && !searchValueReleaseYear && !searchValueLanguage) {
								    userStore.load().filter('Title', searchValueTitle);
								} else if (!searchValueTitle && searchValueDirector && !searchValueReleaseYear && !searchValueLanguage) {
								
								    userStore.load().filter('Director', searchValueDirector);
								} else if (!searchValueTitle && !searchValueDirector && searchValueReleaseYear && !searchValueLanguage) {
								   
								    userStore.load().filter('release_year', searchValueReleaseYear);
								} else if (!searchValueTitle && !searchValueDirector && !searchValueReleaseYear && searchValueLanguage) {
								 
								    userStore.load().filter('language', searchValueLanguage);
								} else {
								    Ext.Msg.alert("Multiple Values", "Currently multi value search is not developed!")// return multiple search message
								}
								
                            }
                        }, {
                        text: 'Reset',
                        handler: function () {
							userStore.clearFilter();
							userStore.load({
								params: {
									start: 0,
									limit: 15
								}
							});
                            this.up('form').getForm().reset();
                        }
                    }]
                }]
            }, {
                title: 'Movie Grid',
				id: 'movie_grid',
				name: 'movieGrid',
                region: 'south',
                xtype: 'grid',
                height: '60%',
                minHeight: 100,
                split: true,
                margin: '0 5 5 5',
                store: userStore,
                columns: [{
                    text: 'Film Id',
                    flex: 1,
                    sortable: true,
                    hideable: false,
                    dataIndex: 'film_id'
                }, {
                    text: 'Title',
                    flex: 2,
                    sortable: true,
                    hideable: false,
                    dataIndex: 'Title'
                }, {
                    text: 'Description',
                    flex: 1,
                    sortable: true,
                    hideable: false,
                    dataIndex: 'Description'
                }, {
                    text: 'Release year',
                    flex: 1,
                    sortable: true,
                    hideable: false,
                    dataIndex: 'release_year'
                }, {
                    text: 'Language',
                    flex: 1,
                    sortable: true,
                    hideable: false,
                    dataIndex: 'language'
                }, {
                    text: 'Director',
                    flex: 1,
                    sortable: true,
                    hideable: false,
                    dataIndex: 'Director'
                },  {
                    text: 'Rating',
                    flex: 1,
                    sortable: true,
                    hideable: false,
                    dataIndex: 'Rating'
                }, {
                    text: 'Special Feature',
                    flex: 1,
                    sortable: true,
                    hideable: false,
                    dataIndex: 'special_features'
                }],
				selModel: {
          			selType: 'checkboxmodel'
      			},
				listeners: {
					'select': function(){
						var gridData = {};
						var slct = Ext.getCmp('movie_grid').getSelectionModel().getSelection();
						//console.log(slct.length);
						if(slct.length == 1){
							Ext.getCmp('editButtonId').setDisabled(false);
							Ext.getCmp('deleteButtonId').setDisabled(false);
							gridData=slct[0].data;
							Ext.getCmp('editFormId').getForm().setValues(gridData);
							Ext.getCmp('deleteFormId').getForm().setValues(gridData);
							Ext.getCmp('edit_film_id').disable();
						} else if(slct.length > 1){
							Ext.getCmp('editButtonId').setDisabled(true);
							Ext.getCmp('deleteButtonId').setDisabled(false);
							gridListOfFilmId=[];
							for(let i = 0; i < slct.length; i++){
								gridListOfFilmId.push(slct[i].data.film_id)
							}
							Ext.getCmp('delete_film_id').setValue(gridListOfFilmId);
							Ext.getCmp('delete_film_id').disable();
							//console.log(gridListOfFilmId);
						}else{
							Ext.getCmp('editButtonId').setDisabled(true);
							Ext.getCmp('deleteButtonId').setDisabled(true);
						}
					},
					
					'deselect': function(){
							var slct = Ext.getCmp('movie_grid').getSelectionModel().getSelection();
							if (slct.length == 0){
								Ext.getCmp('editButtonId').setDisabled(true);
								Ext.getCmp('deleteButtonId').setDisabled(true);
							} else if (slct.length == 1){
								Ext.getCmp('editButtonId').setDisabled(false);
								Ext.getCmp('deleteButtonId').setDisabled(false);
							} else if (slct.length > 1){
								Ext.getCmp('editButtonId').setDisabled(true);
								Ext.getCmp('deleteButtonId').setDisabled(false);
							}
					}
				},
				dockedItems: [{
			        xtype: 'pagingtoolbar',
			        store: userStore,   // same store GridPanel is using
			        dock: 'top',
			        displayInfo: true,
					items: ['->', '->',
					'-',
					{
						xtype: 'button',
						text: 'Add',
						iconCls: 'x-fa fa-plus-circle',
						listeners: {
					        click: function() {
					            //Ext.Msg.alert('Add Button', 'The add button was clicked');
								addWin.show();
					        }
					    }
					},
					'-',
					{
						xtype: 'button',
						text: 'Edit',
						id: 'editButtonId',
						iconCls: 'x-fa fa-pencil-square-o', 
						disabled: true,
						listeners: {
					        click: function() {
					            //Ext.Msg.alert('Edit Button', 'The edit button was clicked');
								editWin.show();
					        }
					    }
					},
					'-',
					{
						xtype: 'button',
						text: 'Delete',
						disabled: true,
						id: 'deleteButtonId',
						iconCls: 'x-fa fa-trash', 
						listeners: {
					        click: function() {
					            //Ext.Msg.alert('Delete Button', 'The delete button was clicked');
								deleteWin.show();
					        }
					    }
					}]
			    }]
            }]
        }],
        renderTo: 'newBody'
    });
});

// index.jsp
/*
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-triton/resources/theme-triton-all.css"/>
<script type="text/javascript" src="./js/extjs/ext-all.js"></script>
<script type="text/javascript" src="./js/extjs/ext-all-6.0.2.js"></script>
<script type="text/javascript" src="./js/extjs/ext-all-debug.js"></script>
<script type="text/javascript" src="./js/extjs/ext-all-debug-6.0.2.js"></script>
<script type="text/javascript" src="./js/js/sakila.js"></script>
<title>Project Sakila</title>
</head>
<body>
	<div id = "newBody"></div>
</body>
</html>
*/
