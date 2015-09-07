<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="utf-8">
    <tiles:insertAttribute name="title"/>
    <tiles:insertAttribute name="srcLinks"/>

    <script type="text/javascript" src="../pages/fancybox/jquery.fancybox-1.3.4.pack.js"></script>

    <style type="text/css">body {
        padding-top: 60px;
        padding-bottom: 40px;
    }

    .sidebar-nav {
        padding: 9px 0;
    }</style>

    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <link rel="shortcut icon" href="../pages/assets/ico/favicon.png">

    <link type="text/css" rel="stylesheet" href="../pages/css/slider.css">
    <script src="../pages/js/bootstrap-slider.js" type="text/javascript"></script>

    <link rel="stylesheet" type="text/css" href="../pages/fancybox/jquery.fancybox-1.3.4.css" media="screen"/>

</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="#">Loskut</a>

            <div class="nav-collapse collapse">

                <sec:authorize access="!isAuthenticated()">

                    <p class="navbar-text pull-right">
                        Вы вошли как: <a href="#" class="navbar-link">Гость</a>
                    </p>

                    <ul class="nav">
                        <li class="active">

                            <a href="#modal-1" role="button" data-toggle="modal" data-target="#modal-1">Войти</a>

                        </li>
                        <li><a href="#modal-2" role="button" data-toggle="modal" data-target="#modal-2">Зарегистрироваться</a>
                        </li>
                        <li><a href="#modal-2" role="button" data-toggle="modal" data-target="#modal-3">Восстановить
                            пароль</a></li>
                    </ul>

                </sec:authorize>

                <sec:authorize access="isAuthenticated()">

                    <p class="navbar-text pull-right">
                        Вы вошли как: <a href="#" class="navbar-link">
                        <sec:authentication property="principal.username"/>
                    </a> Ваш баланс: <a href="#" class="navbar-link">${user.balance/100}&nbsp;грн.</a>
                    </p>
                    <ul class="nav">
                        <li>
                            <sec:authorize url="/admin">
                                <a href="<c:url value="/admin" />">Панель администратора</a>
                            </sec:authorize>
                            <sec:authorize url="/account">
                                <a href="<c:url value="/account" />">Личный кабинет</a>
                            </sec:authorize>
                        </li>
                        <li><a href="<c:url value="/logout" />">Выйти</a></li>
                    </ul>

                </sec:authorize>
            </div>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span3">
            <tiles:insertAttribute name="leftMenu"/>

            <div class="row-fluid">
                <div class="span12">
                    <div class="well sidebar-nav">
                        <div class="checkbox" style="padding-left: 25px;">
                            <form action="/" method="get">
                                <label>
                                    <input type="checkbox" name="listParams" value="0">
                                    Трикотаж
                                </label>
                                <label>
                                    <input type="checkbox" name="listParams" value="1">
                                    Хлопок
                                </label>
                                <label>
                                    <input type="checkbox" name="listParams" value="2">
                                    Шерсть
                                </label>
                                <label>
                                    <input type="checkbox" name="listParams" value="3">
                                    Джинс
                                </label>
                                <label>
                                    <input type="checkbox" name="listParams" value="4">
                                    Шёлк
                                </label>
                                <label>
                                    <input type="checkbox" name="listParams" value="5">
                                    Подклады
                                </label>
                                <label>
                                    <input type="checkbox" name="listParams" value="6">
                                    Вискоза
                                </label>
                                <label>
                                    <input type="checkbox" name="listParams" value="7">
                                    Мебельные ткани
                                </label>
                                <label>
                                    <input type="checkbox" name="listParams" value="8">
                                    Шторные
                                </label>
                                <label>
                                    <input type="checkbox" name="listParams" value="9">
                                    Кожа
                                </label>
                                <button class="btn btn-lg btn-primary btn-block" type="submit">Фильтр</button>
                            </form>


                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="span9">

            <c:if test="${!empty url}">
                <c:forEach items="${url}" var="thePic">
                    <c:if test="${thePic.status==0}">
                        <ul style="display: inline-table">

                            <li><a rel="example_group" href="${thePic.imgurl}"
                                   title="Артикул: ${thePic.sku}&nbsp;&nbsp;&nbsp;&nbsp;${thePic.description}&nbsp;&nbsp;&nbsp;&nbsp;
                     Ширина: ${thePic.width/100}&nbsp;м&nbsp;&nbsp;&nbsp;&nbsp;Длина: ${thePic.length/100}&nbsp;м&nbsp;&nbsp;&nbsp;&nbsp;Цена за 1 м: ${thePic.costpermetr} грн.&nbsp;&nbsp;&nbsp;&nbsp;Общая цена: ${thePic.totalcost/100} грн."
                                    >
                                <img alt="" src="${thePic.imgurl}">
                            </a>
                                <sec:authorize url="/account">
                                    <form method="post">
                                        <center>
                                            <button class="btn btn-lg btn-success" name="idResource"
                                                    value="${thePic.id}"
                                                    type="submit">Добавить в корзину
                                            </button>
                                        </center>
                                    </form>
                                </sec:authorize>
                            </li>
                        </ul>
                    </c:if>
                </c:forEach>
            </c:if>
        </div>
    </div>

    <tiles:insertAttribute name="footer"/>

    <hr>
    <footer>
        <p>&copy; Ray Anton & Kobylyatskiy Alexander</p>
    </footer>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $("a[rel=example_group]").fancybox({
            'transitionIn': 'none',
            'transitionOut': 'none',
            'titlePosition': 'over',
            'titleFormat': function (title) {

                return '<span id="fancybox-title-over">' + (title.length ? ' &nbsp; ' + title : '') + '</span>';
            }
        })
    });
</script>

<script type="text/javascript">

    function responseFio() {

        $.ajax({
            type: "POST",
            url: "/registration/ajaxCheckUnique",
            data: document.reg.j_username.value,
            cache: false,
            success: function (response) {
                if (response == 'off') {
                    $("#responseFioSpan").text("Имя занято").css("color", "red");
                    document.reg.releFio.value = 'on';
                } else {
                    $("#responseFioSpan").text("Имя свободно").css("color", "green");
                    document.reg.releFio.value = 'off';
                }
                ;
            }
        });
    }
    ;

    function responseEmail() {

        $.ajax({
            type: "POST",
            url: "/registration/ajaxCheckUnique",
            data: document.reg.j_email.value,
            cache: false,
            success: function (response) {
                if (response == 'off') {
                    $("#responseEmailSpan").text("E-mail занят").css("color", "red");
                    document.reg.releEmail.value = 'on';
                } else {
                    $("#responseEmailSpan").text("E-mail свободен").css("color", "green");
                    document.reg.releEmail.value = 'off';
                }
                ;
            }
        });
    }
    ;
</script>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
<script type="text/javascript" src="../pages/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="../pages/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<tiles:insertAttribute name="metrika"/>
</body>

<div class="modal fade" id="modal-1" style="width: 270px; top: 20%; left: 60%; display: none;">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h3 class="modal-title" style="margin-left: 15%;">Авторизация</h3>
            </div>
            <div class="modal-body">
                <div class="container" style="width: 200px;">
                    <c:url value="/j_spring_security_check" var="loginUrl"/>
                    <form action="${loginUrl}" method="post">
                        <h2 class="form-signin-heading"></h2>
                        <input type="text" class="form-control" name="j_username" placeholder="Login"
                               style="width: 185px;">
                        <input type="password" class="form-control" name="j_password" placeholder="Password"
                               style="width: 185px;">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
                        <a class="btn btn-lg btn-primary btn-block" href="<c:url value="/restore" />">Восстановить
                            пароль</a>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <a href="" class="btn btn-default" data-dismiss="modal">Отмена</a>
            </div>

        </div>
    </div>
</div>

<div class="modal fade" id="modal-2" style="width: 450px; top: 20%; left: 60%; display: none;">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h3 class="modal-title" style="margin-left: 15%;">Зарегистрируйтесь</h3>
            </div>
            <div class="modal-body">
                <div class="container" style="width: 400px;">
                    <form name="reg" action="/registration" method="post">
                        <input name="releFio" type="hidden">
                        <input name="releEmail" type="hidden">
                        <input maxlength="32" name="j_username" placeholder="Ваш логин" type="text" style="width:65%;"
                               onchange="responseFio();">
                        <span id="responseFioSpan" style="margin-left: 10px;"></span>
                        <input maxlength="32" name="j_password" placeholder="Ваш пароль" type="text" style="width:65%;">
                        <input maxlength="64" name="j_email" placeholder="Ваш email" type="text" style="width:65%;"
                               onchange="responseEmail();">
                        <span id="responseEmailSpan" style="margin-left: 10px;"></span>
                        <button class="btn btn-lg btn-primary btn-block" type="submit" style="width: 70%;">Подтвердить
                        </button>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <a href="" class="btn btn-default" data-dismiss="modal">Отмена</a>
            </div>

        </div>
    </div>
</div>

<div class="modal fade" id="modal-3" style="width: 450px; top: 20%; left: 60%; display: none;">
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

<script>
    var slider = new Slider('#sl1', {});
</script>
<script>
    var slider = new Slider('#sl2', {});
</script>
</html>
