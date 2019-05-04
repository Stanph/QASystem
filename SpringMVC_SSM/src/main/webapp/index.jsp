<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>test json</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">
        function testJson() {
            //获取输入的用户名和密码
            var userID=$("#userID").val();
            var pwd=$("#pwd").val();
            $.ajax({
                url:"${pageContext.request.contextPath}/user/testJson",
                type:"post",
                //data表示发送的数据
                data:JSON.stringify({userID:userID,pwd:pwd}),
                //定义发送请求的数据格式为JSON字符串
                contentType:"application/json;charset=UTF-8",
                //定义回调响应的数据格式为JSON字符串，该属性可以省略
                dataType:"json",
                //成功响应的结果
                success:function (data) {
                    if(data!=null){
                        alert("您输入的用户名为："+data.userID+"密码为："+data.pwd)
                    }

                }
            });
        }
    </script>
</head>
<body>
    <form>
        用户名：<input type="text" name="userID" id="userID"/><br />
        密&nbsp;&nbsp;&nbsp;码：<input type="password" name="pwd" id="pwd"/><br />
        <input type="button" value="test JSON" onclick="testJson()"/>
    </form>
</body>
</html>