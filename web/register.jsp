<%@ page import="com.assignmentjavaweb.entity.User" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: tienb
  Date: 4/10/2019
  Time: 12:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String title = (String) request.getAttribute("title");
    HashMap<String, ArrayList<String>> errors = (HashMap<String, ArrayList<String>>) request.getAttribute("errors");
    if (errors == null) {
        errors = new HashMap<>();
    }
    User user = (User) request.getAttribute("user");
    if (user == null) {
        user = new User();
        user.setUsername("");
    }
%>
<html>
<%--<jsp:include page="register.jsp">--%>
<%--    <jsp:param name="title" value="<%= title%>"/>--%>
<%--    <jsp:param name="description" value="Mô tả"/>--%>
<%--</jsp:include>--%>

<jsp:include page="head.jsp"/>
<head><title>Register</title></head>

<body>

<%--<jsp:include page="login.jsp"/>--%>
<div class="container ">
    <div class="col-3">

    </div>

    <div class="col-6">
        <h1>Register form</h1>
        <form action="/register" method="post">

            <div class="form-group ">
                <label for="exampleInputUsername">User name</label>
                <input type="text" class="form-control" name="username" id="exampleInputUsername"  placeholder="Enter username" value="<%= user.getUsername()%>">
                <% if (errors.containsKey("username")) {
                    ArrayList<String> userNameError = errors.get("username");
                    for (String error :
                            userNameError) {
                %>
                <span class="text-danger">- <%= error%></span>
                <br>

                <%
                        }
                    }
                %>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Enter password">
                <% if (errors.containsKey("password")) {
                    ArrayList<String> passwordError = errors.get("password");
                    for (String error :
                            passwordError) {
                %>
                <span class="text-danger">- <%= error%></span>
                <br>
                <%
                        }
                    }
                %>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
            <button type="reset" class="btn btn-dark">Reset</button>

        </form>

    </div>
    <div class="col-3">

    </div>
</div>
</body>


</html>