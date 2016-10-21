<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Patrick-PC
  Date: 08.10.2016
  Time: 08:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User's Datas</title>
</head>
<body>
    <c:forEach items="users" varStatus="i">
        <table>
            <tr class = "tr">
                <td>${users[i.index].login}</td>
                <td>${users[i.index].password}</td>
            </tr>
        </table>
    </c:forEach>
</body>
</html>
