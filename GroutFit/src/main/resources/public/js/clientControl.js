var isLoggedIn=false;
var curID=null;
var userCreds=[];
var userIds=[];
var userCount=0;

//i should reslly just JSONify the list of ID's anyway and store it here rather than making API calls. Will work on thatlater

function submitLogin() {
    
    var form=document.logForm;
    
    var xhr = new XMLHttpRequest();
    var FD = new FormData(form);
    
    xhr.addEventListener("load", function(event) {
      alert(event.target.responseText); /*Edit this to determine if login fails or works and render certain page conditionally. */
      isLoggedIn=true;
      curID=userIds[form.username.value];
      window.location.href = "index.html";
    });

    xhr.addEventListener("error", function(event) {
      alert('Oops! Something went wrong. Please try again'); //this means the request failed competely, so let's try to log in again.
      window.location.href = "login.html";
    });

    xhr.open("POST", "localhost:4567/api/login", true);
    xhr.send(FD);
}
/*If these routes end up not working, you can use a key value pair of users and their passwords */

function submitRegister() {
    var form = document.regForm;
    pw=form.pass.value;
    if(pw.length<8){
        alert("Password must be at least 8 characters long");
        form.reset();
    }
    var xhr = new XMLHttpRequest();
    var FD = new FormData(form);
    
    xhr.addEventListener("load", function(event) {
      alert(event.target.responseText); /*Edit this to determine if login fails or works and render certain page conditionally. */

      /* Also add user to a key value list, just in case. THIS IS UNSAFE AND CAN ONLY WORK FOR DEMO PURPOSES, incase the server breaks*/
      var userIndex={};
      var userCred={};
      userIndex[form.username.value] = userCount;
      userCred[form.username.value] =form.pass.value;
      userIds.push(userIndex);
      userCreds.push(userCred);
      userCount++; //lt us know how many users the are, this will be useful for later


      window.location.href = "index.html"; //
    });

    xhr.addEventListener("error", function(event) {
      alert('Oops! Something went wrong. Please try again'); //this means the request failed competely, so let's try to log in again.
      window.location.href = "login.html";
    });

    xhr.open("POST", "localhost:4567/api/register", true);
    xhr.send(FD);
}

function logout(){
    isLoggedIn=false;
    curID=null;
    alert("You have been successfully logged out");
    window.location.href = "index.html";
}
function generateFeed(){
    //First, create a post reqquest with a flter valye that will return a JSONified list of item ID's applying to that filter. 
    //Convert to a list of objects
    //Iterate through list, passing the objevts to generateTile

    var listBR=document.createElement("DIV");
    listBR.classList.add("itemList");
    document.body.appendChild(listBR);
    generateTile();
}

function generateTile(){

    var itemBox=document.createElement("DIV");
    itemBox.classList.add("itemBox");

    var itemImg=document.createElement("IMG");
    itemImg.classList.add("itemImg");
    itemImg.setAttribute("src", "clothing_img/293534086.jpg");

    itemBox.appendChild(itemImg);

    var buttonGroup=document.createElement("DIV");
    buttonGroup.classList.add("itemBtn-group");

    var wishButton=document.createElement("BUTTON");
    wishButton.classList.add("itemBtn");
    wishButton.innerHTML="W";

    var cartButton=document.createElement("BUTTON");
    cartButton.classList.add("itemBtn");
    cartButton.innerHTML="C";

    buttonGroup.appendChild(wishButton);
    buttonGroup.appendChild(cartButton);

    itemBox.appendChild(buttonGroup);

    var itemDetail=document.createElement("DIV");
    itemDetail.classList.add("itemDetails");

    var description="This shirt is amazikng. It got me leid"; //need to figure out what descriptive info is returned before i finish thus
    itemDetail.innerHTML=description;

    itemBox.appendChild(itemDetail);
    document.body.appendChild(itemBox);
}

function generateCart(){

}
function generateCartTile(item){

}
function removeFromCart(item){

}



