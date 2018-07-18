<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<fmt:setBundle basename="text" scope="session"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link rel="shortcut icon" href="img/mini.png" type="image/png">
    </head>
    <body>
        <form method="POST" action="Controller">
            
            <input type="hidden" name="action" value="doregister" />
            
            <h1> <fmt:message key="LOGIN"/> </h1>
            
            <input type="text" name="email" value="master">
            
            <br><br>
            
            <h1> <fmt:message key="PASS"/> </h1>
            
            <input type="password" name="password" value="master"> 
            
            <br><br>
            
            <h1> <fmt:message key="RE-PASS"/> </h1>
            
            <input type="password" name="repassword" value="master">
            
            <br><br>
            
            <input type="submit" value="OK">
            
        </form>
        
        <c:if test="${IncorrectPassword != null}">
            <br> <br> <br> <br>
            <center>
                <div class="IncorrectPassword">
                    <${IncorrectPassword}
                </div>
            </center>    
        </c:if>
    
    <a href="start"><img src="img/backward.png"/></a>
        
    </body>
</html>
