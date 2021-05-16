$(document).ready(function() {
    $("#signIn").click(async function(){
        let varData = {
            "login": $('#login').val(),
            "password": $("#password").val(),
        };
        console.log(varData);
        let response = await fetch("http://localhost:8080/trello/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(varData)
        });
        console.log(response)
    });
})