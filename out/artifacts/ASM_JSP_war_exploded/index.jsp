<%@ page import="java.util.ArrayList" %>
<%@ page import="com.assignmentjavaweb.entity.Feedback" %>
<%@ page import="com.assignmentjavaweb.model.FeedbackModel" %>
<%--
  Created by IntelliJ IDEA.
  User: tienb
  Date: 4/10/2019
  Time: 1:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  FeedbackModel feedbackModel = new FeedbackModel();
  ArrayList<Feedback> listFeedback = feedbackModel.getListFeedbacks();
%>
<html>
<head>
  <title>List Feedback</title>
</head>
<jsp:include page="head.jsp"/>
<style>
  .btn {
    border: none;
    color: white;
    padding: 12px 16px;
    font-size: 26px;
    cursor: pointer;
  }


  /* Darker background on mouse-over */
  .btn:hover {
    background-color: #5a6268;
  }
</style>
<body>

<div class="container ">

  <div class="col-3">

  </div>

  <div class="col-6">
    <h1>Feedback form</h1>
    </h3>

    <table class="table table-striped">
      <thead>
      <tr>
        <th scope="col">Id</th>
        <th scope="col">Username</th>
        <th scope="col">Title</th>
        <th scope="col">Feedback</th>
      </tr>
      </thead>
      <tbody>
      <%
        for (Feedback feedback :
                listFeedback) {
            if (feedback.getStatus() == 1){
      %>
      <tr>
        <th scope="row"><%= feedback.getId()%>
        </th>
        <th scope="row"><%= feedback.getUsername()%>
        </th>
        <th scope="row"><%= feedback.getTitle()%>
        </th>
        <td><%= feedback.getContent()%>
        </td>
      </tr>
      <%
          }}%>


      </tbody>
    </table>
  </div>
  <div class="col-3">

  </div>
</div>

</body>
</html>
