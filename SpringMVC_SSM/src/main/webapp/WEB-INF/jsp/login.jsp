<%--
  Created by IntelliJ IDEA.
  User: haopan
  Date: 2019-05-04
  Time: 09:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
    ${msg}
    <form action="${pageContext.request.contextPath}/user/login" method="post">
        用户名：<input type="text" name="userID"/><br />
        密&nbsp;&nbsp;&nbsp;码：<input type="password" name="pwd"/><br />
        <input type="submit" value="LOGIN">

    </form>
</body>
</html>
