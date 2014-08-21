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

                   <h2> <fmt:message key="admin.users.title"/></h2>
                   <form name ="BackForm"  method="POST" action="carrent" class="menu">
                        <input type="hidden" name="command" value="usersW" /> 
                        <input type="submit" value="<fmt:message key="menu.users.back" /> " />
                    </form> 
                    <table id="usertable">
                        <tr>
                            <td class="heading">
                                <b> <fmt:message key="column.userid"/> </b>                                       
                            </td>
                            <td class="heading">
                                <b> <fmt:message key="column.username"/> </b>                                       
                            </td>
                            <td class="heading">
                                <b> <fmt:message key="column.name"/> </b>
                            </td>
                            <td class="heading">
                                <b> <fmt:message key="column.surname"/></b>
                            </td> 
                            <td class="heading">
                                <b> <fmt:message key="column.passNum"/></b>
                            </td> 
                            <td class="heading">
                                <b> <fmt:message key="column.active"/></b>
                            </td> 
                            <td class="heading">
                                <b> <fmt:message key="register.email"/></b>
                            </td> 
                        </tr>
                        <c:forEach var="elem" items="${lst}" varStatus="status">
                            <tr>
                                <td>
                                    <c:out value="${elem.id}"/>                                        
                                </td>
                                <td>
                                    <c:out value="${elem.login}"/>                                        
                                </td>
                                <td>
                                    <c:out value="${elem.name}"/> 
                                </td>
                                <td>
                                    <c:out value="${elem.surname}"/> 
                                </td> 
                                <td>
                                    <c:out value="${elem.passNum}"/> 
                                </td> 
                                <td>
                                    <c:out value="${elem.active}"/> 
                                </td> 
                                <td>
                                    <c:out value="${elem.email}"/> 
                                </td> 
                            </tr>
                            <tr>
                            </tr>
                        </c:forEach>
                    </table>
                    
                </div>               
            </div>
        </div>                 
        <div class="footer">  <c:import url="..\common\footer.jsp" charEncoding="utf-8"/> </div>
    </body>
</html>
