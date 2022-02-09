<%-- 
    Document   : orderd-quantity
    Created on : Jan 30, 2022, 2:02:15 AM
    Author     : ritup
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        
    </head>
    <body>
                                                <s:if test="ctr>0">
                                                  
                                                    <span style="color: red;"><s:property value="addtocart" /></span>
                                                </s:if>
                                                <s:else>
                                                    <span style="color: red;"><s:property value="msg" /></span>
                                                    
                                                </s:else>
    </body>
</html>
