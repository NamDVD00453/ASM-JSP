<%@ page import="java.util.ArrayList" %>
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
    String username = (String) httpSession.getAttribute("loggedUser");
    Integer status = (Integer) request.getAttribute("status");
    Integer role = (Integer) httpSession.getAttribute("role");
    ArrayList<String> listFeedback = (ArrayList<String>) request.getAttribute("listfeedback");

%>
<html>
<head>
    <title>Admin</title>
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
        background-color: #c7c3cc;
    }


</style>


<body>

<div class="container ">

    <div class="col-3">

    </div>

    <div class="col-6">
        <h1>Admin form</h1>
        <h3>Username: <%= username%>
        </h3>
        <h3>role: <%
            if (role == 1) {
        %> admin<%
        } else {
        %> user<%
            }%></h3>

        <div id="myModal" class="modal fade">
            <div class="modal-dialog modal-confirm">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Are you sure?</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">
                        <p>Do you really want to delete these records? This process cannot be undone.</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-info" data-dismiss="modal">Cancel</button>
                        <button type="button" class="btn btn-danger">Delete</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#home">List feedback</a></li>
                <li><a data-toggle="tab" href="#menu1">List user</a></li>
            </ul>

            <div class="tab-content">
                <div id="home" class="tab-pane fade in active">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">id</th>
                            <th scope="col">Feedback</th>
                            <th scope="col">Handle</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            for (String content :
                                    listFeedback) {

                        %>
                        <tr>
                            <th scope="row"><%= status%>
                            </th>
                            <td><%= content%>
                            </td>
                            <td style="display: inline-grid">
                                <%
                                    if (status == 1) {
                                %>
                                <button style="color: #007bff" class="btn" disabled><i class="fa fa-check"></i></button>
                                <%
                                } else {
                                %>
                                <button style="color: #ff6528" class="btn" disabled><i class="fa fa-bomb"></i></button>
                                <%
                                    }
                                %>
                                <button style="color: #b21f2d" href="#myModal" class="btn fa fa-trash trigger-btn" data-toggle="modal"></button>
                            </td>
                        </tr>
                        <%
                            }%>


                        </tbody>
                    </table>
                </div>
                <%--                menu feedback--%>
                <div id="menu1" class="tab-pane fade">


                    <!-- The Modal  edit-->
                    <div class="modal fade" id="editModal">
                        <div class="modal-dialog">
                            <div class="modal-content">

                                <!-- Modal Header -->
                                <div class="modal-header">
                                    <h4 class="modal-title">Change Role</h4>
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                </div>

                                <!-- Modal body -->
                                <div class="modal-body">
                                    <form action="/action_page.php">
                                        <div class="form-group">
                                            <label for="sel1">Select role:</label>
                                            <select class="form-control" id="sel1" name="sellist1">
                                                <option value="0">User</option>
                                                <option value="1">Admin</option>

                                            </select>
                                        </div>
                                        <button type="submit" class="btn btn-primary">Submit</button>
                                        <button type="button" class="btn btn-primary">Cancel</button>
                                    </form>
                                </div>

                                <!-- Modal footer -->
                                <div class="modal-footer">
                                </div>

                            </div>
                        </div>
                    </div>

                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">id</th>
                            <th scope="col">Username</th>
                            <th scope="col">Role</th>
                            <th scope="col">Handle</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            for (String content :
                                    listFeedback) {

                        %>
                        <tr>
                            <th scope="row"><%= status%>
                            </th>
                            <td><%= content%>
                            </td>
                            <td>
                                <%
                                    if (role == 1) {
                                %> admin<%
                            } else {
                            %> user<%
                                }%>
                            </td>
                            <td style="display: inline-grid">
                                <button style="color: #484cb2" type="button" class="btn fa fa-edit" data-toggle="modal" data-target="#editModal">
                                </button>

                            </td>
                        </tr>
                        <%
                            }%>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>
    <div class="col-3">
    </div>
</div>
</body>
</html>
