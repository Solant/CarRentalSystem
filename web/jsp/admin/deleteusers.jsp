<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <fmt:setLocale value="${locale}" scope="session"/>
        <fmt:bundle basename="title" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="header.title"/></title>
        <link rel="stylesheet" href="..\css\style.css" type="text/css">
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
                <div id="page-content">  

                    <div id="adminT"> <fmt:message key="admin.delete.user"/></div>
                    <form name ="BackForm"  method="POST" action="carrent" class="menu">
                        <input type="hidden" name="command" value="usersW" /> 
                        <input type="submit" value="<fmt:message key="menu.users.back" /> " />
                    </form> 
                                                <div class="clarif"><fmt:message key="admin.delete.user.clarif"/> </div>

                    <div id="login">
                        <form name ="order"  method="POST" action="carrent" class="order">
                            <p><fmt:message key="admin.delete.user.warning"/></p> <br/>
                            <input type="hidden" name="command" value="deleteUser" /> 
                            <input type="number" required min="2" max="10000" name="user_id" value="" placeholder="<fmt:message key="greater.than.one"/>" />
                            <input type="submit" value="<fmt:message key="menu.users.delete"/>" />
                        </form> 
                    </div>
                            <c:if test="${not empty deleteError}"><div class="msg"><fmt:message key="delete.user.error"/></div></c:if>
                            <c:if test="${not empty deleteSuccess}"><div class="msg"><fmt:message key="delete.user.success"/></div></c:if>
                    </div>               
                </div>
            </div>                 
            <div class="footer">  <c:import url="..\common\footer.jsp" charEncoding="utf-8"/> </div>
    </body>
</html>
