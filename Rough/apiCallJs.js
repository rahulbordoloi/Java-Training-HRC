// Since fetch is not included in Node directly
const fetch = require("node-fetch")

/*
// Code
var fetchData = (url) => {
    fetch(url).then(response => {
        return response.json();
    }).then(data => {
        return data.data;
    });
}; 

var url = 'http://localhost:8080/JavaDemo/DemoDBConnection'
console.log(fetchData(url));

*/
var url = 'http://localhost:8080/JavaDemo/DemoDBConnection'
/*
fetch(url)
    .then(response => {
        if(response.ok){
            console.log('Success!')
        }
        else{
            console.log('Failed!')
        }
    })
    .then(data => console.log(data))
    .catch(error => console.log('Error!'))
*/