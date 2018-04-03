function submitLogin() {
    /*alert("Sending Json");*/
    var xhr = new XMLHttpRequest();
    xhr.open(form.method, form.action, true);
    xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
    var formData = JSON.stringify($("#myForm").serializeArray());
    xhr.send(formData);
}

function submitRegister() {
    /*alert("Sending Json");*/
    var xhr = new XMLHttpRequest();
    xhr.open(form.method, form.action, true);
    xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
    var formData = JSON.stringify($("#myForm").serializeArray());
    xhr.send(formData);
}