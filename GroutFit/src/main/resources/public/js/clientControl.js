var isLoggedIn = false;
var curID = null;
var userCreds = [];
var userIds = [];
var cart = [];
var userCount = 0;

window.addEventListener("load", function (event) {
    var form = document.logForm;
    if(form==null){
        console.log("form is false");
        return false;
    }
    var u=form.username.value;
    if(u==null || u==""){
        return false;
    }
    //console.log("Form exists in window!");
    responseAction();
    function submitLogin() {
        form.submit();
    }
    function responseAction() {
        alert("Username Found! You're logged On!");
        //window.location.href = "index.html";
    }

    // ...and take over its submit event.
    form.addEventListener("submit", function (event) {
        event.preventDefault();

        submitLogin();
    });
});

window.addEventListener("error", function (event){
    var form = document.logForm;
    if(form==null){
        return false;
    }
    alert("Something went wrong!");

});

/*If these routes end up not working, you can use a key value pair of users and their passwords */

//Same deal as login, you should only recieve the username and password. Return a boolean, successful or nor
function submitRegister() {
    var form = document.regForm;
    var uName = form.username.value;
    var uPass = form.password.value;
    var encodedString="username=" + uName + "&password=" + uPass;
    console.log("Encoded string: " + encodedString);
    if (uPass.length < 8) {
        alert("Password must be at least 8 characters long");
        form.reset();
    }

    var xhr = new XMLHttpRequest();

    xhr.addEventListener("load", function (event) {
        alert("WE DID IT!!!" + event.target.responseText);

        /*Edit this to determine if login fails or works and render certain page conditionally. */

        /* Also add user to a key value list, just in case. THIS IS UNSAFE AND CAN ONLY WORK FOR DEMO PURPOSES, incase the server breaks*/
        var userIndex = {};
        var userCred = {};
        userIndex[form.username.value] = userCount;
        userCred[form.username.value] = form.password.value;
        userIds.push(userIndex);
        userCreds.push(userCred);
        userCount++; //lt us know how many users the are, this will be useful for later
    });

    xhr.addEventListener("error", function (event) {
        alert('Oops! Something went wrong. Please try again'); //this means the request failed competely, so let's try to log in again.
        window.location.href = "index.html";
    });

    xhr.open("POST", "localhost:4567/api/register", true);
    xhr.send(encodedString);
}

//this function will require no API calls
function logout() {
    isLoggedIn = false;
    curID = null;
    alert("You have been successfully logged out");
    window.location.href = "index.html";
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
    generateOutfitTile();
}

function generateOutfitTile() {

    var outfitFrame = document.createElement("DIV");
    outfitFrame.classList.add("outfitFrame");

    var outfitBox = document.createElement("DIV");
    outfitBox.classList.add("outfitBox");

    var imageBox = document.createElement("DIV");
    imageBox.classList.add("itemImg");

    var itemImg = document.createElement("IMG");

    itemImg.setAttribute("src", "clothing_img/293534086.jpg");
    itemImg.setAttribute("alt", "A GROUTFIT");
    imageBox.appendChild(itemImg);
    outfitBox.appendChild(imageBox);

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

    outfitBox.appendChild(buttonGroup);

    var itemDetail = document.createElement("DIV");
    itemDetail.classList.add("itemDetails");

    //need to figure out what descriptive info is returned before i finish thus
    itemDetail.innerHTML = "This shirt is amazing. It got me a gf";

    outfitBox.appendChild(itemDetail);
    outfitFrame.appendChild(outfitBox);
    document.body.appendChild(outfitBox);
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

    //need to figure out what descriptive info is returned before i finish thus
    itemDetail.innerHTML = "This shirt is amazing. It got me a gf";

    itemBox.appendChild(itemDetail);
    document.body.appendChild(itemBox);
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

