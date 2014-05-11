<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" >
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="<c:url value="/c/style.css" />" rel="stylesheet">
<title>Luncher</title>
</head>
  <body>
    <h1>Luncher:</h1>
    <p>Tulemused</p>
    <p>Parim oleks teil minna sööma</p>
    <p>${best}</p>
    <br />
    <br />
    <table>
	    <!-- here should go some titles... -->
	    <tr>
	        <th>Nimi</th>
	        <th>Köök</th>
	        <th>Teeninduse tase</th>
	        <th>Skoor</th>
	    </tr>
	    <c:forEach begin="0" step="1"
	        items="${list}" var="restaurants">
	    <tr>
	        <td>
	            <c:out value="${restaurants.name}" />
	        </td>
	        <td>
	            <c:out value="${restaurants.cuisine}" />
	        </td>
	        <td>
	            <c:out value="${restaurants.serviceClass}" />
	        </td>
	        <td>
	            <c:out value="${restaurants.perceptron}" />
	        </td>
	    </tr>
	    </c:forEach>
	</table>
    <br />
    <a href="/drools/clear">Kustuta sessioon</a>
  </body>
</html>