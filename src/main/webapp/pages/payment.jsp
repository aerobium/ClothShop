<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 07.07.2015
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Администратор: платежи</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <tiles:insertAttribute name="srcLinks"/>

    <!-- Custom styles for this template -->
    <link href="<c:url value="/pages/css/jumbotron-narrow.css" />" rel="stylesheet">

    <script src="../pages/js/typeahead.js" type="text/javascript"></script>

    <!--Table -->
    <link type="text/css" rel="stylesheet" href="../pages/css/jquery.dataTables.css">
    <script src="../pages/js/jquery.dataTables.min.js" type="text/javascript"></script>

    <!--Bootstrap for table -->
    <script src="../pages/js/dataTables.bootstrap.js" type="text/javascript"></script>

    <link type="text/css" rel="stylesheet" href="../pages/css/dataTables.bootstrap.css">
</head>
<body>
<tiles:insertAttribute name="header"/>
<div class="container-fluid">
    <div class="row-fluid">
        <tiles:insertAttribute name="adminLeftBar"/>
        <div class="span9">

            <div class="container">
                <div id="content">

                    <!-- Таблица со списком всех существующих платежей -->

                    <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
                        <thead>
                        <tr>

                            <th data-field="date">Дата</th>
                            <th data-field="login">Логин</th>
                            <th data-field="sum">Сумма, грн</th>
                            <th data-field="comment" data-sortable="true">Комментарий</th>
                        </tr>
                        </thead>
                        <c:if test="${!empty payments}">
                            <c:forEach items="${payments}" var="payment">

                                <tr>

                                    <td>
                                            ${payment.date}
                                    </td>
                                    <td>
                                            ${payment.login}
                                    </td>
                                    <td>
                                            ${payment.transaction/100}
                                    </td>
                                    <td>
                                            ${payment.comment}
                                    </td>
                                </tr>

                            </c:forEach>
                        </c:if>
                    </table>
                    <!-- Конеч таблицы со списком всех существующих товаров -->

                </div>

                <div class="bs-example">
                    <form action="/admin/payment" method="post">
                        <input type="text" data-provide="typeahead" data-items="4"
                               autocomplete="off" placeholder="логин получателя" name="login" data-source='${login}'/>
                        <input type="text" autocomplete="off" placeholder="сумма пополнения" name="amount"/>
                        <input type="text" placeholder="комментарий" name="comment"/>
                        <button class="btn btn-lg btn-success" type="submit">Добавить</button>
                    </form>
                </div>

            </div>
        </div>
    </div>

    <tiles:insertAttribute name="footer"/>

    <div class="footer">
        <p>&copy; Ray Anton & Kobylyatskiy Alexander</p>
    </div>

</div>
<script type="text/javascript" class="init">
    $(document).ready(function () {
        $('#example').DataTable();
    });

</script>
<tiles:insertAttribute name="metrika"/>
</body>
</html>
