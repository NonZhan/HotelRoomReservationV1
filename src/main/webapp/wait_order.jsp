<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<fmt:setBundle basename="text" scope="session"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${order_is_accepted}</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link rel="shortcut icon" href="img/mini.png" type="image/png">
    </head>
    <body>
        
        <h1>${order_is_accepted}</h1>
        
        <form method="POST" action="Controller">
            
            <input type="hidden" name="action" value="gocalculate" />
            
            <p> <fmt:message key="ADDRESS"/> = ${address} </p>
            
            <p> <fmt:message key="NumberRooms"/> = ${numberrooms} </p>
            
            <p> <fmt:message key="Price"/> = ${price} </p>
            
            <input type="submit" value="<fmt:message key="Update"/>">
            
        <a href="request_apartment"><img src="img/backward.png"/></a>
            
        </form>
    </body>
</html>
