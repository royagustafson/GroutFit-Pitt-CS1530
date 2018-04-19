var isLoggedIn;
var curID;
var cart = [];

/*
window.onload = function (event) {
    var reg=document.getElementById("link1");
    var log=document.getElementById("link2");
    var out=document.getElementById("link3");
    isLoggedIn=localStorage.getItem(isLoggedIn);
    console.log("logged in:" + isLoggedIn);
    if(isLoggedIn=="true"){
        reg.classList.add("hidden");
        log.classList.add("hidden");
        out.classList.remove("hidden");
    }else{
        reg.classList.remove("hidden");
        log.classList.remove("hidden");
        out.classList.add("hidden");
    }

}
*/

function submitLogin(enc, name){
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:4567/api/login", true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    xhr.onload= function(){
        if("\"Success\""==xhr.responseText){
            isLoggedIn="true";
            localStorage.setItem("isLoggedIn", isLoggedIn);
            localStorage.setItem("UserID", name);
            alert("Login successful");
            window.location.href="index.html";
        } else {
            alert("Login failed");
            window.location.href="login.html";
        }
    };
    xhr.send(enc);
}

/*If these routes end up not working, you can use a key value pair of users and their passwords */

//Same deal as login, you should only recieve the username and password. Return a boolean, successful or nor
function submitRegister(enc, name) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:4567/api/register", true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    xhr.onload = function(){//Call a function when the state changes.
        if(xhr.status == 200) {
            if("\"Success\""==xhr.responseText){
                alert("Registration successful");
                window.location.href="index.html";
            } else if("\"Invalid username\""==xhr.responseText){
                alert("username already exists. try again");
                window.location.href="register.html";
            }
        }
    };
    xhr.send(enc);
}

//this function will require no API calls
function logout() {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:4567/api/auth/logout", true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    xhr.onload = function() {//Call a function when the state changes.
        if (xhr.status == 200) {
            if ("\"Success\"" == xhr.responseText) {
                localStorage.setItem("isLoggedIn", "false");
                localStorage.setItem("UserID", "");
                alert("You have been successfully logged out");
                window.location.href="index.html";
            }
        } else{
            alert("You are not logged in" + xhr.responseText);
            window.location.href="index.html";
        }
    };
    curID = localStorage.getItem("UserID");
    alert("Logging out " + curID);
    var enc="username=" + curID;
    xhr.send(enc);
}


//*******************NEXT STEP: Generate full outfit feed from text file of all piture filenmes.****************************
//Lets see if we havw time to worry about this way. I have a quick workaround for the demo that would still allow us to filter.
function generateFeed() {
    //First, create a post reqquest with a flter valye that will return a JSONified list of item ID's applying to that filter.
    //Convert to a list of objects
    //Iterate through list, passing the objevts to generateTile

    var listBR = document.createElement("DIV");
    listBR.classList.add("itemList");
    document.body.appendChild(listBR);
    generateTile();
}

//if we choose to keep the feed client sid, we will keep this clint side as well (i'll have all thw puctures and mtadata in the DOM for th presentation)
function generateTile() {

    var itemBox = document.createElement("DIV");
    itemBox.classList.add("itemBox");

    var imageBox = document.createElement("DIV");
    imageBox.classList.add("itemImg");

    var itemImg = document.createElement("IMG");

    itemImg.setAttribute("src", "clothing_img/293534086.jpg");
    itemImg.setAttribute("alt", "A GROUTFIT");
    imageBox.appendChild(itemImg);
    itemBox.appendChild(imageBox);

    var buttonGroup = document.createElement("DIV");
    buttonGroup.classList.add("itemBtn-group");

    var wishButton = document.createElement("BUTTON");
    wishButton.classList.add("itemBtn");
    wishButton.innerHTML = "W";

    var cartButton = document.createElement("BUTTON");
    cartButton.classList.add("itemBtn");
    cartButton.innerHTML = "C";

    buttonGroup.appendChild(wishButton);
    buttonGroup.appendChild(cartButton);

    itemBox.appendChild(buttonGroup);

    var itemDetail = document.createElement("DIV");
    itemDetail.classList.add("itemDetails");

    var description = "This shirt is amazikng. It got me leid"; //need to figure out what descriptive info is returned before i finish thus
    itemDetail.innerHTML = description;

    itemBox.appendChild(itemDetail);
    document.body.appendChild(itemBox);
}
function getTile(itemID){

}

//I can handle cart on the client side.
function generateCart() {

}

//very similar to feed tile
function generateCartTile(item) {

}

//no back end work necesary
function removeFromCart(item) {

}

//no back end work necessary
function clearCart() {

}
function loadHomepage(){
    window.location.href = "index.html";
}

//REFER BELOW FOR THE DATA STRUTURE I'LL USE FOR CART


/* It will simply be a 2 dimensional array. I increase a global counter var when a new user registers, so that unique integer key value corresponds directly to array positons
  For every indexed user, there will be an array of links to their shoping cart items. */

