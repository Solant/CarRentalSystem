<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="custom" uri="customtags" %>

<!DOCTYPE html>
<html>
    <head>
        <fmt:setLocale value="${locale}" scope="session"/>
        <fmt:bundle basename="title" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="header.title"/></title>
        <link rel="stylesheet" href="..\css\style.css" type="text/css">
                        <link rel="shortcut icon" href="..\img\favicon.ico" type="image/x-icon" />

    </head>
    <body>
        <div class="page-wrapper"> 
            <div class="page-buffer">    
                <div class="header"> 
                    <c:import url='..\common\header.jsp' charEncoding="utf-8"/> 
                </div>
                <div id="menu">     
                    <c:import url="..\common\menu_admin.jsp" charEncoding="utf-8"/> 
                </div>
                 <custom:info-tag type="${userType}" username="${userName}">
                    <fmt:message key='infotag.access'/>
                </custom:info-tag>
                <div id="page-content">  

                    <div id="adminT"> <fmt:message key="admin.delete.user"/></div>
                    <form name ="BackForm"  method="POST" action="carrent" class="menu">
                        <input type="hidden" name="command" value="usersW" /> 
                        <input type="submit" value="<fmt:message key="menu.users.back" /> " />
                    </form> 
                    <br/>
                    <p><fmt:message key="admin.delete.user.warning"/>:</p> <br/>
                    <form name ="ChangeCarInfoForm"  method="POST" action="carrent" class="menu">
                        <input type="hidden" name="active" value="${active}" /> 
                        <input type="hidden" name="carid" value="${carid}" /> 
                        <input type="hidden" name="command" value="deleteUser" /> 
                        <input type="number" required min="2" max="10000" name="user_id" value="" placeholder="<fmt:message key="greater.than.one"/>" />
                        <input type="submit" value="<fmt:message key="car.change"/> " />
                    </form>
                </div>               
            </div>
        </div>                 
        <div class="footer">  <c:import url="..\common\footer.jsp" charEncoding="utf-8"/> </div>
    </body>
</html>
