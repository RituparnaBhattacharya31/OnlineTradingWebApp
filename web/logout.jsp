<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<p Style="color:red; align-content: center; margin-left: 40%; margin-top:50px;">You are logged out successfully!</p>
<jsp:include page="pages-login.jsp" />

 <script>
           $(document).ready(function() {
               function disableBack() {
                   window.history.forward()
               }
               window.onload = disableBack();
               window.onpageshow = function(e) {
                   if (e.persisted)
                       disableBack();
               }
           });
       </script>

    <script src=
           "https://code.jquery.com/jquery-3.6.0.min.js" 
           integrity=
   "sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" 
           crossorigin="anonymous"></script>


