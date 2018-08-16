
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix = "z" uri = "/WEB-INF/tag" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Open Rooms</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link rel="shortcut icon" href="img/mini.png" type="image/png">
    </head>
    <body>
        <form action="FilterDeleteRoom" method="POST">
            <br><br>
            
            <input type="text" name="address" value="name"/>
            
            <br><br>
            
            <input type="submit"/>
        </form>
        
        <br><br>
        
        <p>${info}</p>
        
        <form action="FilterLink" method="POST">
            <input type="hidden" name="link" value="doListAndOpen"/>
            <button class="button"><img src="img/backward.png"/></button>   
        </form>
        
        <br><br>
        
        <em> <z:ListOpenRooms/> </em>
        
    </body>
</html>
