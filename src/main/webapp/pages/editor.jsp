<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 22.06.2015
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="utf-8">
    <title>Администратор: редактор товаров</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <tiles:insertAttribute name="srcLinks"/>


    <!-- Custom styles for this template -->
    <link href="<c:url value="/pages/css/jumbotron-narrow.css" />" rel="stylesheet">
    <link href="<c:url value="/pages/css/custom-style.css" />" rel="stylesheet">


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


                    <!-- Таблица со списком всех существующих товаров -->

                    <form:form action="/editor/delete" method="post">
                        <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
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

                                    <tr>
                                        <td><input type="checkbox" name="checkId" value="${order.id}">
                                        </td>
                                        <td onclick="modal(${order.id})">
                                                ${order.sku}
                                        </td>
                                        <td onclick="modal(${order.id})">
                                            <img alt="" src="${order.imgurl}" style=" width: 100px;">
                                        </td>
                                        <td onclick="modal(${order.id})">
                                                ${order.description}
                                        </td>
                                        <td onclick="modal(${order.id})">
                                                ${order.width}
                                        </td>
                                        <td onclick="modal(${order.id})">
                                                ${order.length}
                                        </td>

                                        <td onclick="modal(${order.id})">
                                                ${order.totalcost}
                                        </td>
                                        <td onclick="modal(${order.id})">
                                                ${order.costumerLogin}
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </table>
                        <button name="submit" value="toPack" class="btn btn-lg btn-success" type="submit">
                            Удалить
                        </button>
                    </form:form>
                </div>

                <!-- Fields for recources creating -->
                <div class="container" style="width: 900px;">
                    <form action="/editor/new" method="post">
                        <h2 class="form-signin-heading">Создание новой товарной позиции</h2>
                        <input style="display: inline-table; width: 20%" type="text" class="form-control" name="j_sku"
                               placeholder="Артикул">
                        <input style="display: inline-table; width: 20%" type="text" class="form-control" name="j_type"
                               placeholder="Тип">
                        <input style="display: inline-table; width: 20%" type="text" class="form-control"
                               name="j_description"
                               placeholder="Описание">
                        <input style="display: inline-table; width: 20%" type="text" class="form-control" name="j_width"
                               placeholder="Ширина">
                        <input style="display: inline-table; width: 20%" type="text" class="form-control"
                               name="j_length"
                               placeholder="Длина">
                        <input style="display: inline-table; width: 20%" type="text" class="form-control"
                               name="j_costpermetr"
                               placeholder="Цена за метр">
                        <input style="display: inline-table; width: 20%" type="text" class="form-control"
                               name="j_totalcost"
                               placeholder="Общая цена">
                        <input style="display: inline-table; width: 20%" type="text" class="form-control" name="j_cost"
                               placeholder="Себестоимость">
                        <input style="display: inline-table; width: 20%" type="text" class="form-control"
                               name="j_profit"
                               placeholder="Прибыль">
                        <input style="display: inline-table; width: 20%" type="text" class="form-control"
                               name="j_imgurl"
                               placeholder="URL фото">
                        <input style="display: inline-table; width: 20%" type="text" class="form-control"
                               name="j_status"
                               value="1">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Создать</button>
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


<%--JS which generate id of modal window--%>
<script>
    function modal(id) {
        $("#" + id).modal();
    }

</script>

<%--Modal window for resource editing--%>
<c:if test="${!empty orders}">
    <c:forEach items="${orders}" var="order">

        <div class="modal fade" id="${order.id}"
             style="width: 450px; top: 5%; left: 60%; display: none;">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <form:form action="/editor" method="post">

                        <div class="modal-header">
                            <button type="button" class="close"
                                    data-dismiss="modal">&times;</button>
                            <h3 class="modal-title" style="margin-left: 35%;">Редактор</h3>
                        </div>


                        <div class="modal-body">

                            <div class="container" style="width: 400px;">

                                <input style="display: none" name="j_id"
                                       value="${order.id}">

                                <div class="editor-table">

                                    <div class="editor-row">
                                        <div class="editor-cell">Артикул</div>
                                        <div class="editor-cell"><input
                                                style="display: inline-table; width: 90%"
                                                type="text"
                                                class="form-control" name="j_sku"
                                                value="${order.sku}"></div>
                                    </div>

                                    <div class="editor-row">
                                        <div class="editor-cell">Тип</div>
                                        <div class="editor-cell"><input
                                                style="display: inline-table; width: 90%"
                                                type="text"
                                                class="form-control" name="j_type"
                                                value="${order.type}"></div>
                                    </div>
                                    <div class="editor-row">
                                        <div class="editor-cell">Описание</div>
                                        <div class="editor-cell"><input
                                                style="display: inline-table; width: 90%"
                                                type="text"
                                                class="form-control"
                                                name="j_description"
                                                value="${order.description}"></div>
                                    </div>
                                    <div class="editor-row">
                                        <div class="editor-cell">Ширина</div>
                                        <div class="editor-cell"><input
                                                style="display: inline-table; width: 90%"
                                                type="text"
                                                class="form-control" name="j_width"
                                                value="${order.width}"></div>
                                    </div>
                                    <div class="editor-row">
                                        <div class="editor-cell">Длина</div>
                                        <div class="editor-cell"><input
                                                style="display: inline-table; width: 90%"
                                                type="text"
                                                class="form-control"
                                                name="j_length"
                                                value="${order.length}"></div>
                                    </div>
                                    <div class="editor-row">
                                        <div class="editor-cell">Цена за метр</div>
                                        <div class="editor-cell"><input
                                                style="display: inline-table; width: 90%"
                                                type="text"
                                                class="form-control"
                                                name="j_costpermetr"
                                                value="${order.costpermetr}"></div>
                                    </div>
                                    <div class="editor-row">
                                        <div class="editor-cell">Общая цена</div>
                                        <div class="editor-cell"><input
                                                style="display: inline-table; width: 90%"
                                                type="text"
                                                class="form-control"
                                                name="j_totalcost"
                                                value="${order.totalcost}"></div>
                                    </div>
                                    <div class="editor-row">
                                        <div class="editor-cell">Себестоимость</div>
                                        <div class="editor-cell"><input
                                                style="display: inline-table; width: 90%"
                                                type="text"
                                                class="form-control" name="j_cost"
                                                value="${order.cost}"></div>
                                    </div>
                                    <div class="editor-row">
                                        <div class="editor-cell">Прибыль</div>
                                        <div class="editor-cell"><input
                                                style="display: inline-table; width: 90%"
                                                type="text"
                                                class="form-control"
                                                name="j_profit"
                                                value="${order.profit}"></div>
                                    </div>
                                    <div class="editor-row">
                                        <div class="editor-cell">Фото</div>
                                        <div class="editor-cell"><input
                                                style="display: inline-table; width: 90%"
                                                type="text"
                                                class="form-control"
                                                name="j_imgurl"
                                                value="${order.imgurl}"></div>
                                    </div>
                                    <div class="editor-row">
                                        <div class="editor-cell">Статус</div>
                                        <div class="editor-cell"><input
                                                style="display: inline-table; width: 90%"
                                                type="text"
                                                class="form-control"
                                                name="j_status"
                                                value="${order.status}"></div>
                                    </div>
                                    <div class="editor-row">
                                        <div class="editor-cell">Логин</div>
                                        <div class="editor-cell"><input
                                                style="display: inline-table; width: 90%"
                                                type="text"
                                                class="form-control"
                                                name="j_user"
                                                value="${order.costumerLogin}"></div>
                                    </div>
                                </div>


                            </div>
                        </div>

                        <div class="modal-footer" style="margin-bottom: -20px;">
                            <button class="btn btn-lg btn-primary btn-block"
                                    type="submit" style="width: 70%; display: inline;">Подтвердить
                            </button>

                            <a href="" class="btn btn-default" data-dismiss="modal">Отмена</a>
                        </div>
                    </form:form>


                </div>
            </div>
        </div>

    </c:forEach>
</c:if>
</html>