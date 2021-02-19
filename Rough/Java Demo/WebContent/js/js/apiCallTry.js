// AJX Class to get in API Data
Ext.onReady(function(){

    /*
    Ext.Ajax.request({
        url: 'http://localhost:8080/JavaDemo/DemoDBConnection',
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
            Ext.Msg.alert('Status', 'Request Succesful!');
        },
        failure: function (response) {
            console.log("Failed Status: " + response.status)
            Ext.Msg.alert('Status', 'Request Failed!');
        }
    });
    */
    
    Ext.create("Ext.panel.Panel", {
        width : 250,
        height : 250,
        renderTo : Ext.getBody(),
        layout : 'fit',
        items : [{
            xtype : 'grid',
            listeners : {
                boxready : function(obj) {

                    Ext.Ajax.request({
                        url: 'http://localhost:8080/JavaDemo/DemoDBConnection',
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
            },
            columns : [{
                text : 'No. of Columns',
				flex : 1,
                dataIndex : 'noOfCols'
            }, {
                text : 'Result Status',
                dataIndex : 'resultStatus'
            }],
            store : {
                fields : ['noOfCols', 'resultStatus'],
				flex : 1,
                data : []
            }
        }]
    })

});