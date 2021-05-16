$(document).ready(function() {
    $("#save").click(async function (){
        let varData = {
            "firstName": $("#firstName").val(),
            "lastName": $("#lastName").val(),
            "middleName": $("#middleName").val(),
            "login": $("#login").val(),
            "passwordHash": $("#password").val(),
            "newPasswordHash": $("#newPassword").val()
        };
        console.log(varData);
        let response = await fetch ("http://localhost:8080/user/", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(varData)
        });
        console.log(response)
    });
})