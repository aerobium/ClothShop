<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Личный кабинет</title>

    <tiles:insertAttribute name="srcLinks"/>

    <style type="text/css">body {
        padding-top: 60px;
        padding-bottom: 40px;
    }

    .sidebar-nav {
        padding: 9px 0;
    }</style>

    <!-- Links for using Bootstrap Tables -->
    <link rel="stylesheet" href="../pages/css/bootstrap-table.css">

</head>

<body>
<tiles:insertAttribute name="header"/>
<div class="container-fluid">
    <div class="row-fluid">

        <tiles:insertAttribute name="userLeftBar"/>

        <div class="span9">

            <div id="content">
                <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                    <li class="active"><a href="#red" data-toggle="tab">Забронировано</a></li>
                    <li><a href="#orange" data-toggle="tab">Ожидает рассмотрения</a></li>
                    <li><a href="#yellow" data-toggle="tab">Ожидает получения/В дороге</a></li>
                    <li><a href="#green" data-toggle="tab">Получено</a></li>
                </ul>
                <div id="my-tab-content" class="tab-content">
                    <div class="tab-pane active" id="red">
                        <form:form action="/account/process" method="post">
                            <table data-toggle="table" data-click-to-select="true">
                                <thead>
                                <tr>
                                    <th data-field="state"></th>
                                    <th data-field="name">Артикул</th>
                                    <th data-field="stargazers_count">Фото</th>
                                    <th data-field="forks_count">Описание</th>
                                    <th data-field="description">Ширина</th>
                                    <th data-field="description">Длина</th>
                                    <th data-field="description">Цена за метр</th>
                                    <th data-field="description">Общая стоимость</th>
                                </tr>
                                </thead>
                                <c:if test="${!empty url}">
                                    <c:forEach items="${url}" var="thePic">
                                        <c:if test="${thePic.status==1}">
                                            <tr>

                                                <td><input type="checkbox" name="checkId" value="${thePic.id}"></td>
                                                <td>
                                                        ${thePic.sku}
                                                </td>
                                                <td>
                                                    <img alt="" src="${thePic.imgurl}" style=" width: 100px;">
                                                </td>
                                                <td>
                                                        ${thePic.description}
                                                </td>
                                                <td>
                                                        ${thePic.width}
                                                </td>
                                                <td>
                                                        ${thePic.length}
                                                </td>
                                                <td>
                                                        ${thePic.costpermetr}
                                                </td>
                                                <td>
                                                        ${thePic.totalcost}
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </c:if>

                            </table>
                            <button name="submit" value="toProcess" class="btn btn-lg btn-success" type="submit">
                                Оформить заказ
                            </button>
                            <button name="submit" value="delete" class="btn btn-lg btn-success" type="submit">Удалить
                            </button>
                        </form:form>
                    </div>

                    <div class="tab-pane" id="orange">
                        <table data-toggle="table" data-click-to-select="true">
                            <thead>
                            <tr>

                                <th data-field="name">Артикул</th>
                                <th data-field="stargazers_count">Фото</th>
                                <th data-field="forks_count">Описание</th>
                                <th data-field="description">Ширина</th>
                                <th data-field="description">Длина</th>
                                <th data-field="description">Цена за метр</th>
                                <th data-field="description">Общая стоимость</th>
                            </tr>
                            </thead>
                            <c:if test="${!empty url}">
                                <c:forEach items="${url}" var="thePic">
                                    <c:if test="${thePic.status==2}">
                                        <tr>

                                            <td>
                                                    ${thePic.sku}
                                            </td>
                                            <td>
                                                <img alt="" src="${thePic.imgurl}" style=" width: 100px;">
                                            </td>
                                            <td>
                                                    ${thePic.description}
                                            </td>
                                            <td>
                                                    ${thePic.width}
                                            </td>
                                            <td>
                                                    ${thePic.length}
                                            </td>
                                            <td>
                                                    ${thePic.costpermetr}
                                            </td>
                                            <td>
                                                    ${thePic.totalcost}
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </c:if>

                        </table>
                    </div>

                    <div class="tab-pane" id="yellow">
                        <form:form action="/account/process" method="post">
                            <table data-toggle="table" data-click-to-select="true">
                                <thead>
                                <tr>
                                    <th data-field="state"></th>
                                    <th data-field="name">Артикул</th>
                                    <th data-field="stargazers_count">Фото</th>
                                    <th data-field="forks_count">Описание</th>
                                    <th data-field="description">Ширина</th>
                                    <th data-field="description">Длина</th>
                                    <th data-field="description">Цена за метр</th>
                                    <th data-field="description">Общая стоимость</th>
                                </tr>
                                </thead>
                                <c:if test="${!empty url}">
                                    <c:forEach items="${url}" var="thePic">
                                        <c:if test="${thePic.status==3}">
                                            <tr>
                                                <td><input type="checkbox" name="checkId" value="${thePic.id}"></td>
                                                <td>
                                                        ${thePic.sku}
                                                </td>
                                                <td>
                                                    <img alt="" src="${thePic.imgurl}" style=" width: 100px;">
                                                </td>
                                                <td>
                                                        ${thePic.description}
                                                </td>
                                                <td>
                                                        ${thePic.width}
                                                </td>
                                                <td>
                                                        ${thePic.length}
                                                </td>
                                                <td>
                                                        ${thePic.costpermetr}
                                                </td>
                                                <td>
                                                        ${thePic.totalcost}
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </table>

                            <button name="submit" value="confirm" class="btn btn-lg btn-success" type="submit">
                                Подтвердить получение
                            </button>

                        </form:form>

                    </div>
                    <div class="tab-pane" id="green">
                        <form:form action="/account/process" method="post">
                            <table data-toggle="table" data-click-to-select="true">
                                <thead>
                                <tr>
                                    <th data-field="state"></th>
                                    <th data-field="name">Артикул</th>
                                    <th data-field="stargazers_count">Фото</th>
                                    <th data-field="forks_count">Описание</th>
                                    <th data-field="description">Ширина</th>
                                    <th data-field="description">Длина</th>
                                    <th data-field="description">Цена за метр</th>
                                    <th data-field="description">Общая стоимость</th>
                                </tr>
                                </thead>
                                <c:if test="${!empty url}">
                                    <c:forEach items="${url}" var="thePic">
                                        <c:if test="${thePic.status==4}">
                                            <tr>
                                                <td><input type="checkbox" name="checkId" value="${thePic.id}"></td>
                                                <td>
                                                        ${thePic.sku}
                                                </td>
                                                <td>
                                                    <img alt="" src="${thePic.imgurl}" style=" width: 100px;">
                                                </td>
                                                <td>
                                                        ${thePic.description}
                                                </td>
                                                <td>
                                                        ${thePic.width}
                                                </td>
                                                <td>
                                                        ${thePic.length}
                                                </td>
                                                <td>
                                                        ${thePic.costpermetr}
                                                </td>
                                                <td>
                                                        ${thePic.totalcost}
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </c:if>

                            </table>

                            <button name="submit" value="stash" class="btn btn-lg btn-success" type="submit">Удалить
                            </button>

                        </form:form>

                    </div>
                </div>

            </div>

        </div>
    </div>

    <tiles:insertAttribute name="footer"/>

    <hr>
    <footer>
        <p>&copy; Ray Anton & Kobylyatskiy Alexander</p>
    </footer>
</div>


<script src="../pages/js/bootstrap-table.js"></script>
<script src="../pages/js/bootstrap-table-uk-UA.js"></script>
<tiles:insertAttribute name="metrika"/>
</body>


<div class="modal fade" id="modal-1" style="width: 450px; top: 20%; left: 60%; display: none;">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h3 class="modal-title" style="text-align: center;">Восстановление<br>учетной записи</h3>
            </div>
            <div class="modal-body">
                <div class="container" style="width: 300px;">
                    <form action="/restore" method="post">
                        <p>Введите логин или e-mail учетной записи.<br>Новый пароль будет отправлен на e-mail который
                            был указан при регистрации.</p>
                        <input type="text" class="form-control" name="j_email" placeholder="Ваш email или логин">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Подтвердить</button>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <a href="" class="btn btn-default" data-dismiss="modal">Отмена</a>
            </div>
        </div>
    </div>
</div>


</html>
