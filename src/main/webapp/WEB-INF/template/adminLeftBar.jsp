<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 12.08.2015
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="span3">
    <div class="well sidebar-nav">
        <ul class="nav nav-list">
            <li class="nav-header">Товар и клиенты</li>
            <li><a href="<c:url value="/admin" />">Заказы</a></li>
            <li><a href="<c:url value="/editor" />">Редактор товаров</a></li>
            <li><a href="<c:url value="/admin/payment" />">Платежи</a></li>
            <li><a href="<c:url value="/mail" />">Почта</a></li>
            <li><a href="<c:url value="/dialogs" />">Диалоги</a></li>
            <li class="nav-header">Информация</li>
            <li><a href="#" style="color: #999">Изменение информации на страницах</a></li>
            <li><a href="#" style="color: #999">Оформление</a></li>

        </ul>
    </div>
</div>