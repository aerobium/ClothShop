<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Страница редактирования личных данных">
    <meta name="author" content="">
    <title>Редактирование личных данных</title>

    <tiles:insertAttribute name="srcLinks"/>

    <!-- Custom styles for this template -->
    <link href="<c:url value="/pages/css/jumbotron-narrow.css" />" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

    <style>
        p:hover {
            opacity: 0.5;
        }
    </style>

</head>
<body>
<tiles:insertAttribute name="header"/>

<div class="container-fluid">
    <div class="row-fluid">

        <tiles:insertAttribute name="userLeftBar"/>

        <div class="span9">
            <div id="content">

                <h3>
                    <center>Редактирование персональной информации</center>
                </h3>
                <div class="alert alert-block">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Обратите внимание!</strong> Указанная в форме информация будет использована в качестве
                    данных "Получателя" <br>
                    при отправки курьерскими службами.
                </div>

                <form action="${loginUrl}" method="post">

                    <table class="bordered" style="margin: auto">
                        <tr>
                            <th>e-mail: ${user.email}</th>
                            <th><input type="text" class="form-control" name="email" placeholder="Новый e-mail"></th>
                        </tr>

                        <tr>
                            <th>Имя: ${user.firstName}</th>
                            <th><input type="text" class="form-control" name="firstName" placeholder="Имя"></th>
                        </tr>

                        <tr>
                            <th>Фамилия: ${user.lastName}</th>
                            <th><input type="text" class="form-control" name="lastName" placeholder="Фамилия"></th>
                        </tr>

                        <tr>
                            <th>Телефон: ${user.phone}</th>
                            <th><input type="text" class="form-control" name="phone" placeholder="Телефон"></th>
                        </tr>

                        <tr>
                            <th>Адрес: ${user.adress}</th>
                            <th><input type="text" class="form-control" name="adress" placeholder="Адрес"></th>
                        </tr>

                        <tr>
                            <th>Доставка: ${user.transport}</th>
                            <th><input type="text" class="form-control" name="transport" placeholder="Адрес доставки">
                            </th>

                        </tr>

                    </table>
                    <br>
                    <center>
                        <button class="btn btn-lg btn-primary btn-block" style="width: 400px;" type="submit"
                                href="<c:url value="/accountEdit" />">Сохранить
                        </button>
                    </center>
                </form>


            </div>
        </div>
    </div>

    <tiles:insertAttribute name="footer"/>

    <hr>
    <footer>
        <p>&copy; Ray Anton & Kobylyatskiy Alexander</p>
    </footer>
</div>

<tiles:insertAttribute name="metrika"/>
</body>
</html>
