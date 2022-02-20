/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var clicked=false;
function buttonclicked()
{
    clicked=true;
}
function onSignIn(googleUser){
    if(clicked==true)
    {
        var profile = googleUser.getBasicProfile();
            var name = profile.getName();
            var emailId = profile.getEmail();
            console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
            console.log('Name: ' + profile.getName());
            console.log('Image URL: ' + profile.getImageUrl());
            console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
            $.ajax({
                    url: 'registerusersignup',
                    method: 'POST',
                    data: {name: name,emailId:emailId},
                    success: function (resultText) { 
                        $('#gbutton').hide();
                       var msg="Password has successfully sent to your registered email!";
                        $('#result').html(msg);
                        window.location.replace("pages-login.jsp");
                        
                    },
                    error: function (jqXHR, exception) {
                        console.log('Error occured!!');
                    }
            });  
    }
            
}