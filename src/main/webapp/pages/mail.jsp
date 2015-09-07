<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Администратор: личный кабинет</title>

  <tiles:insertAttribute name="srcLinks"/>


  <!-- Bootstrap core CSS -->


  <script src="../pages/js/typeahead.js" type="text/javascript"></script>

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


<script type="text/javascript">

  var setId='';
  var setRecipients='';


  var addVisible = function(id){

    setId+=id+' ';
    document.getElementById("1").innerHTML=setId;

  };

  var addRecipients = function(author){

    setRecipients+=author+' ';

    document.getElementById("2").innerHTML=setRecipients;

  };

  var addDeleted = function(id){

    setRecipients+=id+' ';

    document.getElementById("3").innerHTML=setRecipients;

  };

</script>
<tiles:insertAttribute name="header"/>

<div class="container-fluid">
  <div class="row-fluid">
    <tiles:insertAttribute name="adminLeftBar"/>
    <div class="span9">


      <div class="container">

        <sec:authorize url="/admin">

          <a class="btn btn-lg btn-success"  href="<c:url value="/admin" />" role="button"> <-- назад</a>

          <center><h1 style="margin: auto">Почта</h1></center>

          <div class="container">

            <!-------->
            <div id="content">
              <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                <li class="active"><a href="#red" data-toggle="tab">Входящие</a></li>
                <li><a href="#orange" data-toggle="tab">Прочитанные</a></li>
                <li><a href="#yellow" data-toggle="tab">Yellow</a></li>
                <li><a href="#green" data-toggle="tab">Green</a></li>
                <li><a href="#blue" data-toggle="tab">Blue</a></li>
              </ul>
              <div id="my-tab-content" class="tab-content">
                <div class="tab-pane active" id="red">
                  <c:if test="${!empty massages}">

                    <table class="bordered">
                      <tr>

                        <th>Логин</th>
                        <th>Откуда</th>
                        <th>Сообщение</th>
                        <th>Ознакомлен</th>
                        <th>Удалить</th>
                        <th>Ответить</th>


                      </tr>
                      <c:forEach items="${massages}" var="massage" >
                        <tr>
                          <td>${massage.author}</td>
                          <c:choose>
                            <c:when test="${massage.place=='0'}">
                              <td>Личный кабинет</td>
                            </c:when>
                            <c:otherwise>
                              <td>${massage.place}</td>
                            </c:otherwise>
                          </c:choose>
                          <td>${massage.text}</td>
                          <td><input type="checkbox" onclick="addVisible(${massage.id})"></td>
                          <td><input type="checkbox" onclick="addDeleted(${massage.id})"></td>
                          <td><input type="checkbox" onclick="addRecipients('${massage.id}')"></td>
                        </tr>
                      </c:forEach>
                    </table>
                  </c:if>
                </div>


                <div class="tab-pane" id="orange">
                  <c:if test="${!empty massages}">

                    <table class="bordered">
                      <tr>

                        <th>Логин</th>
                        <th>Откуда</th>
                        <th>Сообщение</th>
                        <th>Удалить</th>
                        <th>Ответить</th>


                      </tr>
                      <c:forEach items="${readed}" var="massage" >
                        <tr>
                          <td>${massage.author}</td>
                          <c:choose>
                            <c:when test="${massage.place=='0'}">
                              <td>Личный кабинет</td>
                            </c:when>
                            <c:otherwise>
                              <td>${massage.place}</td>
                            </c:otherwise>
                          </c:choose>
                          <td>${massage.text}</td>
                          <td><input type="checkbox" onclick="addDeleted('${massage.id}')"></td>
                          <td><input type="checkbox" onclick="addRecipients('${massage.id}')"></td>
                        </tr>
                      </c:forEach>
                    </table>
                  </c:if>
                </div>
                <div class="tab-pane" id="yellow">
                  <h1>Yellow</h1>
                  <p>yellow yellow yellow yellow yellow</p>
                </div>
                <div class="tab-pane" id="green">
                  <h1>Green</h1>
                  <p>green green green green green</p>
                </div>
                <div class="tab-pane" id="blue">
                  <h1>Blue</h1>
                  <p>blue blue blue blue blue</p>
                </div>
              </div>
            </div>


            <script type="text/javascript">
              jQuery(document).ready(function ($) {
                $('#tabs').tab();
              });
            </script>
          </div> <!-- container -->

          <center>

            <form action="${reg}" method="post">

              <br>
              <div class="bs-example">
                <input type="text" data-provide="typeahead" data-items="4"
                       autocomplete="off" placeholder="логин получателя"     name="loginToSend"  data-source='${login}'/>
              </div>

              <br>

              <p>Сообщение<Br>
                <textarea name="text" cols="40" rows="3"></textarea></p>

              <textarea  class="form-control" style="display:none" ID="1" name="visibility" cols="40" rows="3"></textarea></p>
              <textarea  class="form-control" style="display:none" ID="2" name="recipients" cols="40" rows="3"></textarea></p>
              <textarea  class="form-control" style="display:none" ID="3" name="deleted" cols="40" rows="3"></textarea></p>
              <button class="btn btn-lg btn-success"  type="submit">Сохранить</button>
            </form>
          </center>
        </sec:authorize>

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
