<%--
  Created by IntelliJ IDEA.
  User: tienb
  Date: 4/10/2019
  Time: 1:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //    User user = (User) request.getAttribute("user");
//    if (user == null) {
//        user = new User();
//    }
%>
<html>
<head><title>Login</title></head>
<jsp:include page="head.jsp"/>
<body>

<div class="container ">
    <div class="col-3">

    </div>

    <div class="col-6">
        <form action="/login" method="post">
            <h1>Login form</h1>
            <div class="form-group ">
                <label for="exampleInputUsername">User name</label>
                <input type="text" class="form-control" name="username" id="exampleInputUsername"
                       placeholder="Enter username">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input type="password" class="form-control" name="password" id="exampleInputPassword1"
                       placeholder="Enter password">
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>
            <button type="reset" class="btn btn-dark ">Reset</button>

        </form>
    </div>
    <div class="col-3">
    </div>
</div>
</body>
</html>
