<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="utf-8">
  <title>Личные сообщения</title>

  <tiles:insertAttribute name="srcLinks"/>

  <script src="../pages/js/typeahead.js" type="text/javascript"></script>

  <style type="text/css">body {
    padding-top: 60px;
    padding-bottom: 40px;
  }

  .sidebar-nav {
    padding: 9px 0;
  }</style>

  <!-- Links for using Bootstrap Tables -->
  <link rel="stylesheet" href="../pages/css/bootstrap-table.css">
  <script src="../pages/js/bootstrap-table.js"></script>
  <script src="../pages/js/bootstrap-table-uk-UA.js"></script>


</head>

<body>
<tiles:insertAttribute name="header"/>
<div class="container-fluid">
  <div class="row-fluid">


    <sec:authorize url="/account">
      <tiles:insertAttribute name="userLeftBar"/>
    </sec:authorize>

    <sec:authorize url="/admin">
      <div class="span3">
        <div class="well sidebar-nav">
          <ul class="nav nav-list">
            <li class="nav-header">Товар</li>
            <li><a href="#" style="color: #999">Редактор товаров</a></li>
            <li><a href="#" style="color: #999">Заказы</a></li>
            <li><a href="#" style="color: #999">Архив заказов</a></li>
            <li class="nav-header">Кленты</li>
            <li><a href="<c:url value="/mail" />">Почта</a></li>
            <li><a href="#" style="color: #999">Баланс</a></li>
            <li class="nav-header">Информация</li>
            <li><a href="#" style="color: #999">Изменение информации на страницах</a></li>
            <li><a href="#" style="color: #999">Оформление</a></li>

          </ul>
        </div>
      </div>
    </sec:authorize>


    <div class="span9">

      <sec:authorize url="/account">

          <table id="body" class="bordered"></table>

        <form  method="post">

          <br>

          <p>Сообщение<Br>
            <textarea name="text" cols="40" rows="3"></textarea></p>
          <button class="btn btn-lg btn-success" onClick="g1()"  type="submit">Отправить</button>    <a class="btn btn-lg btn-success"  href="<c:url value="/account" />" role="button">Личный кабинет</a>
          <button name="delete" value="hide" class="btn btn-lg btn-success"  type="submit">Удалить диалог</button>

        </form>

      </sec:authorize>

      <sec:authorize url="/admin">

        <center>
          <c:if test="${!empty dialogWith}">
            <c:if test="${!empty userMassages}">

              <h2 style="margin: auto">${dialogWith}</h2>
              <br>
              <table id="body" class="bordered"></table>

            </c:if>

            <form method="post">

              <br>

              <input style="display:none"  name="loginToSend" value="${dialogWith}">
              <p>Сообщение<Br>
                <textarea name="text" cols="40" rows="3"></textarea></p>
              <button class="btn btn-lg btn-success"  role="button"  type="submit">Отправить</button>   <button name="delete" value="delete" class="btn btn-lg btn-success"  type="submit">Удалить диалог</button>
            </form>
          </c:if>

          <br>
          <form action="${reg}" method="post">
            <div class="bs-example">
              <input id="dialogWith" type="text" data-provide="typeahead" data-items="4"
                     autocomplete="off" placeholder="логин пользователя"     name="loginToSend"  data-source='${login}'/>
              <button class="btn btn-lg btn-success" onClick="g1()"  type="submit">Выбрать диалог</button>   <a class="btn btn-lg btn-success"  href="<c:url value="/mail" />" role="button">Почта</a>
            </div>
          </form>

        </center>

      </sec:authorize>

    </div>

  </div>

  <tiles:insertAttribute name="footer"/>

  <hr>
  <footer>
    <p>&copy; Ray Anton & Kobylyatskiy Alexander</p>
  </footer>
</div>

<script src="../pages/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="../pages/js/bootstrap.js" type="text/javascript"></script>
<script src="../pages/js/typeahead.js" type="text/javascript"></script>
<script src="../pages/js/mail.js" type="text/javascript"></script>
<tiles:insertAttribute name="metrika"/>
</body>

</html>
