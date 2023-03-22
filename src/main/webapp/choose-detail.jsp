<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.shulha.service.DetailService" %>
<%@ page import="com.shulha.model.*" %>
<%@ page import="java.util.List" %>

<html>

<head>
    <title>Choose some detail</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>
    <link rel="stylesheet" href="css/zeroing.css">
    <link rel="stylesheet" href="css/choose-detail.css">
</head>

<body>
    <section class="main">
        <% final DetailService detailService = DetailService.getInstance(); %>
        <% final List<Detail> detailList = detailService.getAll(); %>

        <h1 class="header">Choose ID of your detail:</h1>
        <form method="post" action="show-one.jsp">
            <select name="detail-option" class="selectpicker">
                <option value="none" disabled="disabled" selected="selected">Choose ID</option>
                <% for (Detail detail : detailList) { %>
                <% final String id = detail.getId(); %>
                <option value=<%= id %>><%= id %></option>
                <% } %>
            </select>
            <button class="get-button" type="submit">Get</button>
        </form>

        <p><a id="back-to-main" href="/..">Back to the main page</a></p>
    </section>
</body>

</html>