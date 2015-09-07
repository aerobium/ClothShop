<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Администратор: заказы</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <tiles:insertAttribute name="srcLinks"/>

    <!-- Links for using Bootstrap Tables -->
    <link rel="stylesheet" href="../pages/css/bootstrap-table.css">
    <script src="../pages/js/bootstrap-table.js"></script>
    <script src="../pages/js/bootstrap-table-uk-UA.js"></script>

    <!-- Custom styles for this template -->
    <link href="<c:url value="/pages/css/jumbotron-narrow.css" />" rel="stylesheet">


</head>

<body>
<tiles:insertAttribute name="header"/>
<div class="container-fluid">
    <div class="row-fluid">
        <tiles:insertAttribute name="adminLeftBar"/>
        <div class="span9">

            <div class="container">
                <div id="content">

                    <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                        <li class="active"><a href="#work" data-toggle="tab">На отправку</a></li>
                        <li><a href="#road" data-toggle="tab">В дороге</a></li>
                        <li><a href="#recived" data-toggle="tab">Полученные</a></li>
                        <li><a href="#new" data-toggle="tab">Новые заказы</a></li>
                    </ul>

                    <div id="my-tab-content" class="tab-content">

                        <!-- Таблица для на отправку -->
                        <div class="tab-pane active" id="work">
                            <form:form action="/admin/process" method="post">
                                <table data-toggle="table" data-click-to-select="true" data-sort-name="client"
                                       data-sort-order="desc">
                                    <thead>
                                    <tr>
                                        <th data-field="state"></th>
                                        <th data-field="sku">Артикул</th>
                                        <th data-field="photo">Фото</th>
                                        <th data-field="desctiption">Описание</th>
                                        <th data-field="width">Ширина</th>
                                        <th data-field="length">Длина</th>
                                        <th data-field="sum">Сумма</th>
                                        <th data-field="client" data-sortable="true">Клиент</th>
                                    </tr>
                                    </thead>
                                    <c:if test="${!empty orders}">
                                        <c:forEach items="${orders}" var="order">
                                            <c:if test="${order.status==2}">
                                                <tr>
                                                    <td><input type="checkbox" name="checkId" value="${order.id}"></td>
                                                    <td>
                                                            ${order.sku}
                                                    </td>
                                                    <td>
                                                        <img alt="" src="${order.imgurl}" style=" width: 100px;">
                                                    </td>
                                                    <td>
                                                            ${order.description}
                                                    </td>
                                                    <td>
                                                            ${order.width}
                                                    </td>
                                                    <td>
                                                            ${order.length}
                                                    </td>

                                                    <td>
                                                            ${order.totalcost}
                                                    </td>
                                                    <td>
                                                            ${order.costumerLogin}
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </table>
                                <button name="submit" value="toPack" class="btn btn-lg btn-success" type="submit">
                                    Собрал
                                </button>
                                <button name="submit" value="reservation" class="btn btn-lg btn-success" type="submit">
                                    Вернуть в бронь
                                </button>
                            </form:form>
                        </div>
                        <!-- Конец Таблица для на отправку -->


                        <!--Таблица Отправленных в дороге -->
                        <div class="tab-pane" id="road">
                            <form:form action="/admin/process" method="post">
                                <table data-toggle="table" data-click-to-select="true" data-sort-name="client"
                                       data-sort-order="desc">
                                    <thead>
                                    <tr>
                                        <th data-field="state"></th>
                                        <th data-field="sku">Артикул</th>
                                        <th data-field="photo">Фото</th>
                                        <th data-field="desctiption">Описание</th>
                                        <th data-field="width">Ширина</th>
                                        <th data-field="length">Длина</th>
                                        <th data-field="sum">Сумма</th>
                                        <th data-field="client" data-sortable="true">Клиент</th>
                                    </tr>
                                    </thead>
                                    <c:if test="${!empty orders}">
                                        <c:forEach items="${orders}" var="order">
                                            <c:if test="${order.status==3}">
                                                <tr>
                                                    <td><input type="checkbox" name="checkId" value="${order.id}"></td>
                                                    <td>
                                                            ${order.sku}
                                                    </td>
                                                    <td>
                                                        <img alt="" src="${order.imgurl}" style=" width: 100px;">
                                                    </td>
                                                    <td>
                                                            ${order.description}
                                                    </td>
                                                    <td>
                                                            ${order.width}
                                                    </td>
                                                    <td>
                                                            ${order.length}
                                                    </td>

                                                    <td>
                                                            ${order.totalcost}
                                                    </td>
                                                    <td>
                                                            ${order.costumerLogin}
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </table>
                                <button name="submit" value="unpack" class="btn btn-lg btn-success" type="submit">
                                    Вернуть в Отправку
                                </button>
                                <button name="submit" value="receive" class="btn btn-lg btn-success" type="submit">В
                                    полученные
                                </button>
                            </form:form>
                        </div>
                        <!--Конец таблицы Отправленных в дороге -->

                        <!--Таблица Полученных -->
                        <div class="tab-pane" id="recived">
                            <form:form action="/admin/process" method="post">
                                <table data-toggle="table" data-click-to-select="true" data-sort-name="client"
                                       data-sort-order="desc">
                                    <thead>
                                    <tr>
                                        <th data-field="state"></th>
                                        <th data-field="sku">Артикул</th>
                                        <th data-field="photo">Фото</th>
                                        <th data-field="desctiption">Описание</th>
                                        <th data-field="width">Ширина</th>
                                        <th data-field="length">Длина</th>
                                        <th data-field="sum">Сумма</th>
                                        <th data-field="client" data-sortable="true">Клиент</th>
                                    </tr>
                                    </thead>
                                    <c:if test="${!empty orders}">
                                        <c:forEach items="${orders}" var="order">
                                            <c:if test="${order.status==4}">
                                                <tr>
                                                    <td><input type="checkbox" name="checkId" value="${order.id}"></td>
                                                    <td>
                                                            ${order.sku}
                                                    </td>
                                                    <td>
                                                        <img alt="" src="${order.imgurl}" style=" width: 100px;">
                                                    </td>
                                                    <td>
                                                            ${order.description}
                                                    </td>
                                                    <td>
                                                            ${order.width}
                                                    </td>
                                                    <td>
                                                            ${order.length}
                                                    </td>

                                                    <td>
                                                            ${order.totalcost}
                                                    </td>
                                                    <td>
                                                            ${order.costumerLogin}
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </table>
                                <button name="submit" value="archive" class="btn btn-lg btn-success" type="submit">В
                                    архив
                                </button>
                            </form:form>
                        </div>
                        <!--Конец таблицы Полученных -->

                        <!--Таблица Новых заказов -->
                        <div class="tab-pane" id="new">
                            <form:form action="/admin/process" method="post">
                                <table data-toggle="table" data-click-to-select="true" data-sort-name="client"
                                       data-sort-order="desc">
                                    <thead>
                                    <tr>
                                        <th data-field="state"></th>
                                        <th data-field="sku">Артикул</th>
                                        <th data-field="photo">Фото</th>
                                        <th data-field="desctiption">Описание</th>
                                        <th data-field="width">Ширина</th>
                                        <th data-field="length">Длина</th>
                                        <th data-field="sum">Сумма</th>
                                        <th data-field="client" data-sortable="true">Клиент</th>
                                    </tr>
                                    </thead>
                                    <c:if test="${!empty orders}">
                                        <c:forEach items="${orders}" var="order">
                                            <c:if test="${order.status==1}">
                                                <tr>
                                                    <td><input type="checkbox" name="checkId" value="${order.id}"></td>
                                                    <td>
                                                            ${order.sku}
                                                    </td>
                                                    <td>
                                                        <img alt="" src="${order.imgurl}" style=" width: 100px;">
                                                    </td>
                                                    <td>
                                                            ${order.description}
                                                    </td>
                                                    <td>
                                                            ${order.width}
                                                    </td>
                                                    <td>
                                                            ${order.length}
                                                    </td>

                                                    <td>
                                                            ${order.totalcost}
                                                    </td>
                                                    <td>
                                                            ${order.costumerLogin}
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </table>
                                <button name="submit" value="delete" class="btn btn-lg btn-success" type="submit">Снять
                                    бронь
                                </button>
                            </form:form>
                        </div>
                        <!--Конец таблицы новых заказов -->
                    </div>
                </div>
            </div>
        </div>
    </div>

    <tiles:insertAttribute name="footer"/>

    <div class="footer">
        <p>&copy; Ray Anton & Kobylyatskiy Alexander</p>
    </div>

</div>
<tiles:insertAttribute name="metrika"/>
</body>
</html>