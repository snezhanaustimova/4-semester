$(document).ready(function() {
    $("#signUp").click(async function (){
        let varData = {
            "firstName": $("#firstName").val(),
            "lastName": $("#lastName").val(),
            "middleName": $("#middleName").val(),
            "login": $('#login').val(),
            "passwordHash": $("#password").val(),
        };
        let response = await fetch("http://localhost:8080/trello/user", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(varData)
        });
        console.log(response)
    });
})