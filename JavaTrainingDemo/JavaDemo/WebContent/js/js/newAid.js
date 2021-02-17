// Main UI Rendering Function
Ext.onReady(function () {
    Ext.create('Ext.container.Viewport', {
	    
        layout: {
            type: 'fit',
            pack: 'center',
            align: 'middle'
        },
        items: [{
            title: 'Main Panel',
            xtype: 'panel',
            layout: {
                type: 'border'
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
                    
                        xtype: 'panel',
                        title: 'Form',
                        
                    
						}]
            }, {
					
                title: 'Movies DB Table',
                id: 'movieGrid',
                region: 'south',
                xtype: 'panel',
                height: '60%',
                minHeight: 100,
                split: true,
				}]
        }],
        renderTo: document.body
    });
});