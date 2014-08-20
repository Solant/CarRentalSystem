<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <fmt:setLocale value="${locale}" scope="session"/>
        <fmt:bundle basename="title" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="err.title"/></title>
        <link rel="stylesheet" href="css/style.css" type="text/css">

    </head>
    <body>
        <div class="page-wrapper"> <div class="page-buffer"> <div class="header"> <c:import url="\common\header.jsp"/> </div>
        <div class="errorMes">
            <b><fmt:message key="err.title"/> </b><br/>
            <fmt:message key="error.request"/> ${pageContext.errorData.requestURI} <fmt:message key="error.request.cont"/>
            <br/>
            <fmt:message key="error.servlet"/> ${pageContext.errorData.servletName}
            <br/>
            <fmt:message key="error.status"/> : ${pageContext.errorData.statusCode}
            <br/>
            <fmt:message key="error.exception"/> ${pageContext.errorData.throwable}
            <br/>            <input type="submit" value="<fmt:message key="back.button"/>" onclick='history.back()'/>

            
        </div> </div> </div>
   
        <div class="footer">  <c:import url="\common\footer.jsp" charEncoding="utf-8"/> </div>
    </body>
</html>
