<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 02.08.2015
  Time: 8:43
  To change this template use File | Settings | File Templates.
--%>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="<c:url value="/" />">Loskut</a>

            <div class="nav-collapse collapse">
                <sec:authorize access="isAuthenticated()">
                    <p class="navbar-text pull-right">
                        Вы вошли как: <a href="#" class="navbar-link">
                        <sec:authentication property="principal.username"/>
                    </a> Ваш баланс: <a href="#" class="navbar-link">${user.balance/100}&nbsp;грн.</a>
                    </p>

                    <ul class="nav">
                        <li>
                            <a href="<c:url value="/"/>">Главная</a>
                        </li>
                        <li><a href="<c:url value="/logout" />">Выйти</a></li>
                    </ul>
                </sec:authorize>
            </div>
        </div>
    </div>
</div>