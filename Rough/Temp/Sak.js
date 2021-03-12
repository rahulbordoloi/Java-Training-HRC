Ext.onReady(test3)

function test3(){
Ext.define('Language', {
   extend: 'Ext.data.Model',
   fields: [{name: 'lang',  type: 'string'},
       
]
});

var lang_states = Ext.create('Ext.data.Store', {
model:'Language',
   data: [
{'lang':'English'},
{'lang':'Italian'},
{'lang':'Japanese'},
{'lang':'Mandarin'},
{'lang':'French'},
{'lang':'German'},
{'lang':'Mongolian'}
],

});
//vh and vw
var form_part=Ext.create('Ext.form.Panel', {
   title: 'Movie Advance Search',
   height:'70vh',
id:'sakila_form',
   fieldDefaults: {
       labelAlign: 'top',
       msgTarget: 'side',
border:0
   },
  layout: {
        type: 'vbox',      
        align: 'stretch',    // Each takes up full width
pack  : 'center',
    },
   items: [{
border: false,
            frame: false,
layout:{
type:'hbox',
pack:'center'
},
       items: [{
border: false,
            frame: false,
           xtype: 'textfield',
           fieldLabel: 'Movie Name',
           name: 'movie',
id:'movieName',
labelAlign : 'left',
margin: '10 60 0 0'
       }, {
           xtype: 'textfield',
border: false,
            frame: false,
           fieldLabel: 'Director Name',
          id:'directorName',
           name: 'director',
labelAlign : 'left',
margin: '10 60 0 0'
       }
]
   }, {
layout:{
type:'hbox',
pack:'center',
align:'stretch'
},
border: false,
            frame: false,
       items: [{

xtype: 'datefield',
id:'releaseYear',
       fieldLabel: 'Release Year',
name:'release_year',
format:'Y',

labelAlign : 'left',
margin: '30 60 0 0'
},{
xtype: 'combo',
id:'language_combo',
fieldLabel: 'Language',
backgroundColor: 'green',
fontWeight: 'bold',
valueField: 'lang',
name:'Language',
displayField: 'lang',
store:lang_states,
editable: false,
labelAlign : 'left',
margin: '30 60 0 0'
}]
   },
{
layout:{
type:'hbox',
pack:'center',
align:'stretch'
},
border: false,
            frame: false,
items:[{
xtype:'button',
text:'Search',
margin: '30 60 0 0',
handler: function () {

                            var form = this.up('form').getForm();
                            user.clearFilter()

                            var formInfo = {
                                movieName_: Ext.getCmp('movieName').getValue(),
                                directorName_: Ext.getCmp('directorName').getValue(),
                                releaseYear_:  Ext.getCmp('releaseYear').getValue()!=null ? Ext.getCmp('releaseYear').getValue().getFullYear():Ext.getCmp('releaseYear').getValue(),
                               
                                language_: Ext.getCmp('language_combo').getValue()
                            }

console.log(formInfo);
                            if (!formInfo.movieName_ && !formInfo.directorName_ && !formInfo.releaseYear_ && !formInfo.language_) {
                                Ext.Msg.alert("Blank Form", "You've submitted a Blank Form!")
                            }
                            else if (formInfo.movieName_ && !formInfo.directorName_ && !formInfo.releaseYear_ && !formInfo.language_) {
                                user.load().filter('title', formInfo.movieName_);
                            }
                            else if (!formInfo.movieName_ && formInfo.directorName_ && !formInfo.releaseYear_ && !formInfo.language_) {
                                user.load().filter('director', formInfo.directorName_);
                            }
                            else if (!formInfo.movieName_ && !formInfo.directorName_ && formInfo.releaseYear_ && !formInfo.language_) {                                  
                                user.load().filter('realeaseYear', formInfo.releaseYear_);
                            }
                            else if (!formInfo.movieName_ && !formInfo.directorName_ && !formInfo.releaseYear_ && formInfo.language_) {                                
                                user.load().filter('languageName', formInfo.language_);
                            }
                          else if(formInfo.movieName_ && formInfo.directorName_ && formInfo.releaseYear_ && formInfo.language_) {
                                var filtersSearch = [
                                    new Ext.util.Filter({
                                        filterFn: function(item) {
                                            return item.get('title') == formInfo.movieName_ && item.get('director') == formInfo.directorName_ && item.get('realeaseYear') == formInfo.releaseYear_ && item.get('languageName') == formInfo.language_;
                                        }
                                    })
                                ];
                                user.load().filter(filtersSearch);
                            }
                        }
                    },
{
xtype:'button',
text:'Reset',
margin: '30 60 0 0',
handler:function(btn,e){
user.clearFilter()
form_part.reset()
user.reload()
}

}]


}],

 
});
Ext.define('Data_main', {
   extend: 'Ext.data.Model',
   fields: [
{
name:'film_id',
type:'int'
}
,{
       name: 'title',
       type: 'string'
   },
{
       name: 'description',
       type: 'string'
   },{
       name: 'realeaseYear',
type:'int'
     
   },{
       name: 'languageName',
       type: 'string'
   },{
       name: 'director',
       type: 'string'
   } ,{
       name: 'rating',
       type: 'string'
   } ,{
       name: 'specialFeatures',
       type: 'string'
   }
]
});

var user = Ext.create('Ext.data.Store', {
        storeId: 'user',
        model: 'Data_main',
pageSize: 15,
//data:dataMovie,
//autoLoad: false,
        proxy: {

        type: 'ajax',
cors: true,
useDefaultXhrHeader : false,
            url: 'http://localhost:8080/FINAL_PROJECT/fetchMovieData',
            reader: {
   type: 'json',
   totalProperty : 'size',
rootProperty : 'data'
},
enablePaging : true
        },
//autoLoad: true

    });
user.load({
params : {
start : 0,
limit : 15,
}
});

console.log(user.data.items);

var rating_states = Ext.create('Ext.data.Store', {
   data: [
['G'],['PG'],['PG-13'],['R'],['NC-17']
],
id: 'rating_combo',
fields: ['rating']
});

var special_features = Ext.create('Ext.data.Store', {
   data: [
['Trailers'],['Commentaries'],['Deleted Scenes'],['Behind the Scenes']
],
id: 'special_combo',
fields: ['special']
});
var myForm_add = new Ext.form.Panel({
        title:'Add film',
 
        floating: true,
        //closable : true,
items: [{
         xtype: 'textfield',
         name: 'title',
         fieldLabel: 'Movie Name ',
         allowBlank: false,
         padding: '20'
         
       }, {
           xtype: 'textfield',
   fieldLabel: 'Director Name ',
   name: 'director',
           padding: '20',
allowBlank: false,
       },
{
           xtype: 'datefield',
allowBlank: false,
   fieldLabel: 'Release Year ',
   name: 'releaseYear',
format:'Y',
           padding: '20'
       },
{
           xtype: 'combo',

fieldLabel: 'Language',

valueField: 'lang',
name:'LanguageName',
displayField: 'lang',
store:lang_states,
editable: false,
padding: '20'
       },
{
           xtype: 'combo',
   fieldLabel: 'Rating',
   name: 'rating',
           padding: '20',
store:rating_states,
displayField: 'rating',
valueField: 'rating',
editable: false
       },
{
           xtype: 'combo',
   fieldLabel: 'Special Features',
   name: 'specialFeatures',
           padding: '20',
store:special_features,
displayField: 'special',

valueField: 'special',
editable: false
       },
{
           xtype: 'textfield',
   fieldLabel: 'Description',
   name: 'description',
           padding: '20'
       }],

buttons: [{
       text       : 'SAVE', //lookup, filter
       handler: function(btn,e) {

console.log(myForm_add.getValues());
var values=myForm_add.getValues();
console.log(values["LanguageName"])
Ext.Ajax.request({
                url:'http://localhost:8080/FINAL_PROJECT/addMovieData',
               
                params: {
title:values["title"],
realeaseYear:values["releaseYear"],
specialFeatures:values["specialFeatures"],
rating:values["rating"],
director:values["director"],
description:values["description"],
LanguageName:values["LanguageName"],
filmId:2

},

                }),
user.reload();
myForm_add.hide();
}

},{
text:'CANCEL',
handler:function(){
myForm_add.hide();
}

}]


    });
var myForm_edit = new Ext.form.Panel({
        title:'Edit Film of your Choice',
 
        floating: true,
       
items: [{
         xtype: 'textfield',
         id: 'titleX',
         fieldLabel: 'Movie Name ',
         padding: '20'
         
       }, {
           xtype: 'textfield',
   fieldLabel: 'Director Name ',
   id: 'directorX',
           padding: '20'
       },
{
           xtype: 'textfield',
   fieldLabel: 'Description',
   id: 'descriptionX',
           padding: '20'
       },
{
           xtype: 'datefield',
   fieldLabel: 'Release Year ',
   id: 'release_yearX',
format:'Y',
           padding: '20'
       },
{
           xtype: 'combo',

fieldLabel: 'Language',
itemId: 'lang_combo',
valueField: 'lang',
id:'langaugeX',
displayField: 'lang',
store:lang_states,
editable: false,
padding: '20',
forceSelection: false
       },
{
           xtype: 'combo',
   fieldLabel: 'Rating',
itemId:'rating_combo',
   id: 'rating_comboX',
           padding: '20',
store:rating_states,
displayField: 'rating',
valueField: 'rating',
editable: false
       }],
buttons: [{
       text       : 'EDIT',
       handler: function() {

var values=myForm_edit.getValues();
console.log(values)
console.log(table_part.getSelectionModel().getSelection()[0].data.film_id)
console.log(values["titleX-inputEl"])
console.log(values["release_yearX-inputEl"])
console.log(values["rating_comboX-inputEl"])
console.log(values["descriptionX-inputEl"])
console.log(values["titleX-inputEl"])
console.log(values["langaugeX-inputEl"])
Ext.Ajax.request({
                url:'http://localhost:8080/FINAL_PROJECT/editMovieData',
               
                params: {
title:values["titleX-inputEl"],
realeaseYear:values["release_yearX-inputEl"],

rating:values["rating_comboX-inputEl"],
director:values["directorX-inputEl"],
description:values["descriptionX-inputEl"],
LanguageName:values["langaugeX-inputEl"],
filmId:table_part.getSelectionModel().getSelection()[0].data.film_id

},

                }),
user.reload();
myForm_edit.hide();

}

},{
text:'CANCEL',
handler:function(){

user.reload();
table_part.getSelectionModel().clearSelections();
myForm_edit.hide();
}

}]


    });

var change = function() {


var length_sel=table_part.getSelectionModel().getCount();
console.log(length_sel)
if(length_sel==1){
myForm_edit.show();
console.log(table_part.getSelectionModel().getSelection()[0].data)
Ext.getCmp("titleX").setValue(table_part.getSelectionModel().getSelection()[0].data.title)
Ext.getCmp("descriptionX").setValue(table_part.getSelectionModel().getSelection()[0].data.description)
Ext.getCmp("release_yearX").setValue(table_part.getSelectionModel().getSelection()[0].data.realeaseYear)
Ext.ComponentQuery.query('#lang_combo')[0].setValue(table_part.getSelectionModel().getSelection()[0].data.languageName)
Ext.ComponentQuery.query('#rating_combo')[0].setValue(table_part.getSelectionModel().getSelection()[0].data.rating)
Ext.getCmp("directorX").setValue(table_part.getSelectionModel().getSelection()[0].data.director)

}
}
var delete_data=function(){

var length_sel=table_part.getSelectionModel().getCount();
console.log(length_sel)

if(length_sel==1){
//for(i=0;i<length_sel;i++){
//temp.push(table_part.getSelectionModel().getSelection()[i].data.filmId)
//}
var temp=table_part.getSelectionModel().getSelection()[0].data.filmId
console.log(temp);
Ext.Ajax.request({
                url:'http://localhost:8080/FINAL_PROJECT/deleteMovieData',
               
                params: {filmId:temp},

                })
user.reload();
}

}



var id_list=[]
    var table_part=Ext.create('Ext.grid.Panel', {
        store: user,
        id: 'user',
        title: 'Movie Grid',


//height: 300,
        //width: 500,

//scrollable:false,
        columns: [{
            header: 'Title',
            dataIndex: 'title',
//width: 100,
flex:1
        }, {
            header: 'Description',
            dataIndex: 'description',
//width: 100,
flex:1
        }, {
            header: 'Release Year',
            dataIndex: 'realeaseYear',
//width: 100,
flex:1
        },{
            header: 'Language',
            dataIndex: 'languageName',
//width: 100,
flex:1
        },{
            header: 'Director Name',
            dataIndex: 'director',
//width: 100,
flex:1
        },{
            header: 'Ratings',
            dataIndex: 'rating',
//width: 100,
flex:1
        },{
            header: 'Special Features',
            dataIndex: 'specialFeatures',
//width: 100,
flex:1
        }],
selModel: {
        selType: 'checkboxmodel',
allowDeselect: true  
    },
dockedItems: [{
       xtype: 'pagingtoolbar',
       store: user,   // same store GridPanel is using
       dock: 'top',
       displayInfo: true,
layout: {
            pack: 'end',
            type: 'hbox'
        },
        items: [
            {
                xtype: 'button',
                text: 'Add',
listeners: {
        click:function(){

myForm_add.show()
}
       
       }
               
            },
            {
                xtype: 'button',
id:'buttonEdit',
                text: 'Edit',

listeners: {

        click:change
       
       }
               
            },
{
                xtype: 'button',
id:'buttonDelete',
                text: 'Delete',
listeners:{
click:delete_data
}
               
            }
        ]
   }],
    /*bbar: new Ext.PagingToolbar({
         pageSize:4,
         store:user,
         displayInfo:true,
         displayMsg:'Records {0} - {1} of {2}',
         emptyMsg:"no record!"
        }),*/
listeners: {
    select: function(selModel, record, index, options){
console.log(record.data)
id_list.push(record.data)
console.log(id_list)
    },
deselect: function(selModel, record, index, options){
console.log(record.data)
id_list=id_list.filter(function(item) {
    return item !== record.data
})
console.log(id_list)
    },

}
        //renderTo: Ext.getBody()
    });


Ext.create('Ext.container.Container', {
    layout: {
        type: 'vbox',
align:'stretch'
    },
    //width: 400,
    renderTo: Ext.getBody(),
    //border: 1,
    //style: {borderColor:'#000000', borderStyle:'solid', borderWidth:'1px'},
items:[form_part,table_part]
   
});



}