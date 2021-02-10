// JavaScript Practice

// Defining a Dictionary
var randomVariable = {
    firstName : 'Rahul',
    lastName : 'Bordoloi',
    age : 21,
    fullName : function() {
        return this.firstName + " " + this.lastName;
    }
};

// Specifying Datatype
var aNewVariable = new Number();
aNewVariable = 707;

// Creating a Function
varStoringFunction = function newFunction(x1, x2){
    return x1 * x2;
}

// Working with Arrays
var newArray = ['Rahul', 'Bordoloi', 'is', 'a', 'pro']
var numericArray = [1, 3, 15, 2]

function compareNumeric(a, b) {
    if (a > b) return 1;
    if (a == b) return 0;
    if (a < b) return -1;
}

// JS Array Functions
var resultSumOfArray = numericArray.reduce((sum, current) => sum + current, 0);
let usersList = [
    {id : 1, name : 'Rahul'},
    {id : 2, name : 'Bordoloi'},
    {id : 3, name : 'Haha'}
]

var resultOfFilter = usersList.filter(item => item.id < 3);

// Working with Strings
let names = 'Rahul, Bordoloi, King'
let splitNames = names.split(', ')

// Trying out Random Stuffs
const es6Function = (a, b) => {return a * b };   // ES6 way of writing JS Functions

// Calling
/*

// <-- JS Introduction -->
console.log(randomVariable.firstName);
console.log(randomVariable.fullName());
console.log(aNewVariable);
console.log(varStoringFunction(7, 8));
*/

// <-- Arrays -->
/*
console.log(newArray);
console.log(newArray.shift());
console.log(newArray.unshift('Rony'));
console.log(newArray);
console.log(newArray.splice(3, 0, ['Rony', 'Boiii']));
console.log(newArray);
console.log(typeof(newArray[3][1]));
console.log(numericArray);
numericArray.sort();
console.log(numericArray);
numericArray.sort(compareNumeric);
console.log(numericArray);
*/

// <-- Strings -->
/*
console.log(splitNames);
for(let i of splitNames){
    console.log(`Using String Formatting: ${i}`)
}
*/

// <-- JS Array Functions -->
/*
console.log(resultSumOfArray)
console.log(resultOfFilter.length);
splitNames.forEach((item, index, array) => {
    console.log(`${item} is at index ${index} in ${array}`);
});
*/

// Trying out Random Stuffs
console.log(es6Function(7, 8));