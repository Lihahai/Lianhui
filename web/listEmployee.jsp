<%--
  Created by IntelliJ IDEA.
  User: Real
  Date: 2019/11/24
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>listEmployee</title>
</head>
<body>
<table align='center' border='1' cellspacing='0'>
    <tr><td>eID</td><td>eNO</td><td>eName</td><td>eMouthIntegral</td><td>eSumIntegral</td><td>eExtraIntegral</td></tr>
    <c:forEach items="${employees}" var="employee" varStatus="st">
    <tr>
        <td>${employee.eID}</td>
        <td>${employee.eNo}</td>
        <td>${employee.eName}</td>
        <td>${employee.eMouthIntegral}</td>
        <td>${employee.eSumIntegral}</td>
        <td>${employee.eExtraIntegral}</td>

    </tr>
    </c:forEach>

</body>
</html>
