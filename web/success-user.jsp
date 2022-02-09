<%-- 
    Document   : success-order
    Created on : Jan 31, 2022, 8:45:17 AM
    Author     : abhin
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