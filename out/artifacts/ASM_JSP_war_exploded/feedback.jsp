<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.assignmentjavaweb.entity.Feedback" %>
<%--
  Created by IntelliJ IDEA.
  User: tienb
  Date: 4/10/2019
  Time: 1:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession httpSession = request.getSession();
    String username = (String) httpSession.getAttribute("logUsername");
    String title = (String) httpSession.getAttribute("logTitle");
    String content = (String) httpSession.getAttribute("logContent");
    if (content == null){
        content = "";
    }
    if (title == null){
        title = "";
    }
    HashMap<String, ArrayList<String>> errors = (HashMap<String, ArrayList<String>>) request.getAttribute("errors");
    if (errors == null) {
        errors = new HashMap<>();
    }
    Feedback feedback = (Feedback) request.getAttribute("feedback");
    if (feedback == null) {
        feedback = new Feedback();
    }
%>
<html>
<head>
    <title>Feedback</title>
</head>
<jsp:include page="head.jsp"/>

<body>

<div class="container ">

    <div class="col-3">

    </div>

    <div class="col-6">
        <h1>Feedback form</h1>

        <form action="/feedback" method="post">
            <div class="form-group row">
                <label for="staticEmail" class="col-sm-2 col-form-label">Username:</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="<%= username%>">
                </div>
            </div>
            <div class="form-group">
                <label for="exampleInputTitle">Title</label>
                <input type="text" class="form-control" name="title" id="exampleInputTitle"
                       placeholder="Enter title" value="<%= title%>">
                <%
                    if (errors.containsKey("title")){
                        ArrayList<String> feedbackError = errors.get("title");
                        for (String error :
                                feedbackError) {
                %>
                <span class="text-danger">- <%= error%></span>
                <br>
                <%
                        }
                    }
                %>
                <label for="exampleFormControlTextarea1">Content</label>
                <textarea class="form-control" placeholder="Please write your feedback here. Maximum 2000 characters" name="content" id="exampleFormControlTextarea1" rows="3" ><%= content%></textarea>
                <%
                    if (errors.containsKey("content")){
                        ArrayList<String> feedbackError = errors.get("content");
                        for (String error :
                                feedbackError) {
                %>
                <span class="text-danger">- <%= error%></span>
                <br>
                <%
                        }
                    }
                %>
            </div>
            <button type="submit" class="btn btn-primary">Confirm</button>
            <button type="reset" class="btn ">Reset</button>
        </form>
    </div>
    <div class="col-3">
    </div>
</div>
</body>
</html>
