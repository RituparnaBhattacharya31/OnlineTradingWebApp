<%-- 
    Document   : success-updateuser
    Created on : Feb 4, 2022, 3:41:11 PM
    Author     : ritup
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <body>
         <s:if test="ctr>0">
                                                <span style="color: red;"><s:property value="msg" /></span>
                                            </s:if>
                                            <s:else>
                                                <span style="color: red;"><s:property value="msg" /></span>
                                            </s:else>
    </body>
</html>
