var isLoggedIn;
var curID;
var Pants=["659004047","660115502","674932943","720120373","720211709","836673326","842239534"];
var Shirts=["130416315","140226300","430627026","250879861","457136344","293534086","395557109"];
var Skirts=["240698130","374581910","468276714","524635841","565956011","765158210"];
var All=["130416315","140226300","240698130","250879861","285499569","293534086","374581910","395557109","430627026","457136344","468276714","524635841","565956011","659004047","660115502","674932943","709054560","720120373","720211709","756741519","765158210","836673326","842239534","849825435","859609403","887162937","998342202"];




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
function generateFeed(e) {
    //Get filter value from button
    var filter=e.target.id;
    clearFeed();
    console.log("Applying filter of type: " + filter);
    var items=[];
    if("pants"==filter){
        items=Pants;
    } else if("shirts"==filter){
        items=Shirts;
    } else if("skirts"==filter){
        items=Skirts;
    } else if("all"==filter){
        items=All;
    }
    for(var i=0; i<items.length; i++){
        generateTile(items[i]);
    }
}
function generateAll(){
    var items=All;
    for(var i=0; i<items.length; i++){
        generateTile(items[i]);
    }
}

function clearFeed(){
    var myNode = document.getElementById("IL");
    while (myNode.firstChild) {
        myNode.removeChild(myNode.firstChild);
    }
}

//if we choose to keep the feed client sid, we will keep this clint side as well (i'll have all thw puctures and mtadata in the DOM for th presentation)
function generateTile(cid) {
    var itemList=document.getElementById("IL");
    var itemBox = document.createElement("DIV");
    itemBox.classList.add("itemBox");

    var imageBox = document.createElement("DIV");
    imageBox.classList.add("itemImg");

    var itemImg = document.createElement("IMG");

    itemImg.setAttribute("src", "clothing_img/" + cid + ".jpg");
    itemImg.setAttribute("alt", "A GROUTFIT");
    imageBox.appendChild(itemImg);
    itemBox.appendChild(imageBox);

    var buttonGroup = document.createElement("DIV");
    buttonGroup.classList.add("itemBtn-group");

    var wishButton = document.createElement("BUTTON");
    wishButton.classList.add("itemBtn");
    wishButton.innerHTML = "W";
    wishButton.id=cid;
    wishButton.addEventListener("click", function(e){
        var wlARR=JSON.parse(localStorage.getItem("WishList"));
        console.log("Target id: " + e.target.id);
        if(wlARR==null){
            wlARR=[];
        }
        if(wlARR.includes(e.target.id)==false){
            wlARR.push(e.target.id);
            console.log("Wishlist after adding item: " + JSON.stringify(wlARR));
            localStorage.setItem("WishList", JSON.stringify(wlARR));
        } else {
            console.log("Item already in wishlist");
        }

    });

    buttonGroup.appendChild(wishButton);

    itemBox.appendChild(buttonGroup);

    var itemDetail = document.createElement("DIV");
    itemDetail.classList.add("itemDetails");

    var description = "This shirt is amazikng. It got me leid"; //need to figure out what descriptive info is returned before i finish thus
    itemDetail.innerHTML = description;

    itemBox.appendChild(itemDetail);
    itemList.appendChild(itemBox);
}

//I can handle cart on the client side.
function generateWishlist() {
    var wlARR=JSON.parse(localStorage.getItem("WishList"));
    if(wlARR==null){
        wlARR=[];
    }
    var wlSize=wlARR.length;
    if(wlSize==0){
        alert("Your wishlist is empty");
        window.location.href="index.html";
    }else{
        for(var i=0; i<wlARR.length; i++){
            generateCartTile(wlARR[i]);
        }
    }
}

//very similar to feed tile
function generateCartTile(cid) {
    var itemList=document.getElementById("IL");
    var itemBox = document.createElement("DIV");
    itemBox.classList.add("itemBox");

    var imageBox = document.createElement("DIV");
    imageBox.classList.add("itemImg");

    var itemImg = document.createElement("IMG");

    itemImg.setAttribute("src", "clothing_img/" + cid + ".jpg");
    itemImg.setAttribute("alt", "A GROUTFIT");
    imageBox.appendChild(itemImg);
    itemBox.appendChild(imageBox);

    var buttonGroup = document.createElement("DIV");
    buttonGroup.classList.add("itemBtn-group");

    var wishButton = document.createElement("BUTTON");
    wishButton.classList.add("itemBtn");
    wishButton.innerHTML = "R";
    wishButton.id=cid;
    wishButton.addEventListener("click", function(e){
        var wlARR=JSON.parse(localStorage.getItem("WishList"));
        console.log("Target id: " + e.target.id);
        if(wlARR==null){
            wlARR=[];
        }
        wlARR.splice(wlARR.indexOf(e.target.id), 1);
        console.log("Wishlist after removing item: " + JSON.stringify(wlARR));
        localStorage.setItem("WishList", JSON.stringify(wlARR));
        window.location.reload();
    });

    buttonGroup.appendChild(wishButton);

    itemBox.appendChild(buttonGroup);

    var itemDetail = document.createElement("DIV");
    itemDetail.classList.add("itemDetails");

    var description = "This shirt is amazikng. It got me leid"; //need to figure out what descriptive info is returned before i finish thus
    itemDetail.innerHTML = description;

    itemBox.appendChild(itemDetail);
    itemList.appendChild(itemBox);
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

