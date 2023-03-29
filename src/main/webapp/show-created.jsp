<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.shulha.service.DetailService" %>
<%@ page import="com.shulha.model.*" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<html>

<head>
    <title>Show some created detail</title>
    <link rel="stylesheet" href="css/zeroing.css">
    <link rel="stylesheet" href="css/create.css">
</head>

<body>
    <section>
        <% final DetailService detailService = DetailService.getInstance(); %>
        <% final Detail detail = detailService.createAndSave(); %>
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

        <p><a id="back-to-main" href="/create.jsp">Back to the creating some detail</a></p>
    </section>
</body>

</html>