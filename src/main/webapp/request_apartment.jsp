<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<fmt:setBundle basename="text" scope="session"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Request Apartment</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link rel="shortcut icon" href="img/mini.png" type="image/png">
    </head>
    <body>
        
        <a href="wait_order"><img height="250px" src="img/wait_order.png"/></a>

    <div class="request_apartment">
        
        <form method="POST" action="Controller">
                        
            <input type="hidden" name="action" value="doRequestApartment" />
            
            <center><fmt:message key="Leasetime"/></center>
            
            <input type="text" name="leaseTime" value="1 day">
            
            <select name="numberRooms">
                <option value="one_room">one-room</option>
                <option value="two_room">two-room</option>
                <option value="three_room">three-room</option>
                <option value="four_room">four-room</option>
                <option value="five_room">five-room</option>
            </select>
            
            <br><br>
            
            <center><fmt:message key="Desiredprice"/></center>
            
            <input type="number" name="desiredPrice"> 
            
            <br><br>
            
            <center><fmt:message key="Recommendation"/></center>
            
            <textarea name="recommendation" rows="10" cols="45"></textarea>
            
            <br><br>
            
            <center><fmt:message key="Phone"/></center>
            
            <input type="number" name="phone">
            
            <br><br>
            
            <input type="submit" value="OK">
            
        </form> 
        
    </div>
    
    <c:if test="${request_accepted != null}">
        <center>
            <br><br>
            <div class="IncorrectPassword">
                ${request_accepted}
            </div>
        </center>
    </c:if>
    
    </body>
</html>
