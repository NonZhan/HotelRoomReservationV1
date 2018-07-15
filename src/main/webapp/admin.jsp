<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<fmt:setBundle basename="text" scope="session"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADMIN</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link rel="shortcut icon" href="img/mini.png" type="image/png">
    </head>
    <body>
        
        <form method="POST" action="Controller">
            
            <input type="hidden" name="action" value="doadmin" />
            
            <h1> <fmt:message key="login"/> </h1>
            
            <input type="text" name="login">
            
            <br><br>
            
            <h1> <fmt:message key="password"/> </h1>
            
            <input type="password" name="password"> 
            
            <br><br>
            
            <input type="submit" value="OK">
            
        </form>
        
        <p> ${empty_login_or_pass} </p>
        
    </body>
</html>
