<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.shulha.service.DetailService" %>
<%@ page import="com.shulha.model.*" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.io.PrintWriter" %>

<html>

<head>
    <title>Show some detail</title>
    <link rel="stylesheet" href="css/zeroing.css">
    <link rel="stylesheet" href="css/create.css">
</head>

<body>
    <section>
        <% String collect = request.getReader().lines().collect(Collectors.joining(System.lineSeparator())); %>
        <% String id = collect.split("=")[1]; %>
        <% final DetailService detailService = DetailService.getInstance(); %>
        <% final Detail detail = detailService.getById(id); %>
        <% final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); %>

        <table style="width:100%">
            <tr>
                <td>ID</td>
                <td>BEGINNING TIME</td>
                <td>ENDING TIME</td>
                <td>SPENT TIME</td>
                <td>SPENT FUEL</td>
                <td>MINED FUEL</td>
                <td>BROKEN CHIPS</td>
            </tr>
            <tr>
                <td><%= detail.getId() %></td>
                <td><%= detail.getBeginningTime().format(dateTimeFormatter) %></td>
                <td><%= detail.getEndingTime().format(dateTimeFormatter) %></td>
                <td><%= detail.getSpentTime() %> seconds</td>
                <td><%= detail.getSpentFuel() %></td>
                <td><%= detail.getMinedFuel() %></td>
                <td><%= detail.getAmountOfBrokenChips() %></td>
            </tr>
        </table>

        <p><a id="back-to-main" href="/choose-detail.jsp">Back to the choosing some detail</a></p>
    </section>
</body>

</html>