<%-- 
    Document   : success-order
    Created on : Jan 29, 2022, 4:45:17 PM
    Author     : ritup
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
    <body>
        <div>
            
                                          
                                             <s:if test="ctr>0">
                                                  
                                                    <span style="color: red;"><s:property value="msg" /></span>
                                                </s:if>
                                                <s:else>
                                                    <span style="color: red;"><s:property value="msg" /></span>
                                                    
                                                </s:else>

        </div>
        </body>
</html>    