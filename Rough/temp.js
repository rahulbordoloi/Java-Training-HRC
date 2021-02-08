// PRS UI Code
Ext.application({

    name : 'Rahul',
    
        launch : function() {
            
            Ext.define('Movies', {
                    extend: 'Ext.data.Model',
                    pageSize:5,
                    fields: ['Title', 'Description', 'Release Year', 'Language', 'Director', 'Rating', 'Special_Features']
                });
                
                // Defining DB [Data-Store]
                var userStore = Ext.create('Ext.data.Store', {
                    storeId: 'DescriptionStore',
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
                    data: [
                        { Title: 'The Shawshank Redemption', Description: 'Movie of Crime', Release_Year: '1994', Language: 'English', Director: 'X', Rating: '10', Special_Features: 'Deleted Scene' },
                        { Title: 'The Wolf of Wall Street', Description: 'Movie about Wolf-Street', Release_Year: '2013', Language: 'English', Director: 'Y', Rating: '9', Special_Features: 'Deleted Scene' },
                        { Title: 'Avengers: End Game', Description: 'Movie of Avengers', Release_Year: '2019', Language: 'English', Director: 'Z', Rating: '8', Special_Features: 'Deleted Scene' },
                        { Title: 'Dabbang 2', Description: 'Movie of Selmon Bhai', Release_Year: '2012', Language: 'Hindi', Director: 'A', Rating: '7', Special_Features: 'Deleted Scene' },
                        { Title: 'Academy Dinosaur', Description: 'Academy Dinosaur is a movie about Dianosaurs Ofcourse', Release_Year: '2006', Language: 'English', Director: 'B', Rating: '6', Special_Features: 'Deleted Scene' },
                        { Title: 'Academy Dinosaur', Description: 'Academy Dinosaur is a movie about Dianosaurs Ofcourse', Release_Year: '2006', Language: 'Hindi', Director: 'C', Rating: '5', Special_Features: 'Deleted Scene' },
                        { Title: 'Academy Dinosaur', Description: 'Academy Dinosaur is a movie about Dianosaurs Ofcourse', Release_Year: '2006', Language: 'English', Director: 'D', Rating: '4', Special_Features: 'Deleted Scene' },
                        { Title: 'Academy Dinosaur', Description: 'Academy Dinosaur is a movie about Dianosaurs Ofcourse', Release_Year: '2006', Language: 'English', Director: 'E', Rating: '3', Special_Features: 'Deleted Scene' },
                        { Title: 'Academy Dinosaur', Description: 'Academy Dinosaur is a movie about Dianosaurs Ofcourse', Release_Year: '2006', Language: 'English', Director: 'F', Rating: '2', Special_Features: 'Deleted Scene' },
                        { Title: 'Academy Dinosaur', Description: 'Academy Dinosaur is a movie about Dianosaurs Ofcourse', Release_Year: '2006', Language: 'English', Director: 'G', Rating: '1', Special_Features: 'Deleted Scene' },
                        { Title: 'Academy Dinosaur', Description: 'Academy Dinosaur is a movie about Dianosaurs Ofcourse', Release_Year: '2006', Language: 'Assamese', Director: 'H', Rating: '0', Special_Features: 'Deleted Scene' },
                        { Title: 'Academy Dinosaur', Description: 'Academy Dinosaur is a movie about Dianosaurs Ofcourse', Release_Year: '2006', Language: 'English', Director: 'I', Rating: '0', Special_Features: 'Deleted Scene' },
                        { Title: 'Academy Dinosaur', Description: 'Academy Dinosaur is a movie about Dianosaurs Ofcourse', Release_Year: '2006', Language: 'English', Director: 'J', Rating: '0', Special_Features: 'Deleted Scene' },
                        { Title: 'Academy Dinosaur', Description: 'Academy Dinosaur is a movie about Dianosaurs Ofcourse', Release_Year: '2006', Language: 'English', Director: 'K', Rating: '0', Special_Features: 'Deleted Scene' },
                        { Title: 'Academy Dinosaur', Description: 'Academy Dinosaur is a movie about Dianosaurs Ofcourse', Release_Year: '2006', Language: 'English', Director: 'L', Rating: '0', Special_Features: 'Deleted Scene' },
                        { Title: 'Academy Dinosaur', Description: 'Academy Dinosaur is a movie about Dianosaurs Ofcourse', Release_Year: '2006', Language: 'English', Director: 'M', Rating: '0', Special_Features: 'Deleted Scene' },
                    ]
            });
            
            // Creating Panel for Search
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
                items: [{
                        fieldLabel: 'Movie Name',
                        name: 'movieName',
                        width:300,
                        margin:'5 50 5 150'
                   }, {
                        fieldLabel: 'Director Name',
                        name: 'directorName',
                        width: 300,
                        margin: '5 50 5 50'
                    }],
                }, {
                    defaultType: 'textfield',
                    layout : 'hbox',
                    width : '100%',
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
                        fieldLabel: 'Language',
                        name: 'language',
                        width: 300,
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
        }, {
                text: 'Reset',
                handler: function() {
                this.up('form').getForm().reset();
            }
        }],
    });
    
    // Creating Panel for Table        
    Ext.create('Ext.grid.Panel', {
        renderTo: Ext.getBody(),
        store: userStore,
        selModel: {
                    selType: 'checkboxmodel',
                    mode: 'MULTI',
                    checkOnly: true,
                    listeners: {
                        select: function (model, record, index, eOpts) {
                            Ext.Msg.alert('', userStore.data.items[index].data['Title']);
                            console.log(userStore.data.items[index].data['Title'])
                        }
                    }
                },
                plugins: [{
                    ptype: 'rowediting',
                    clicksToEdit: 1
                }],
                pageSize: 4,
                flex: 1,
                title: 'Movies DB Table',
                store: Ext.data.StoreManager.lookup('DescriptionStore'),
                dockedItems: [{
                        xtype: 'pagingtoolbar',
                        store: Ext.data.StoreManager.lookup('DescriptionStore'),
                        displayInfo: true,
                        dock: 'bottom',
                            displayMsg: 'Displaying {0} to {1} of {2} &nbsp;records ',
                            emptyMsg: "No records to display&nbsp;"
                }],
               columns: [{
                        text: 'Title',            
                        dataIndex: 'Title',
                        flex: 1
                    }, {
                        text: 'Description',
                        width: 300,
                        dataIndex: 'Description'
                    }, {
                        text: 'Release Year',
                        flex: 1,
                        dataIndex: 'Release_Year'
                    }, {
                       text: 'Language',
                       flex: 1,
                       dataIndex: 'Language'
                    }, {
                        text: 'Director',
                        flex: 1,
                        dataIndex: 'Director'
                    }, {
                        text: 'Rating',
                        flex: 1,
                        dataIndex: 'Rating'
                   }, {
                        text: 'Special Features',
                        flex: 1,
                        dataIndex: 'Special_Features'
                    }
                ],
        });
    }
});