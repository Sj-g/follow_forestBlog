<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/13
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Carbon - Admin Template</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/vendor/simple-line-icons/css/simple-line-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/vendor/font-awesome/css/fontawesome-all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css">
</head>

<body class="sidebar-fixed header-fixed">
<div class="page-wrapper">
    <!--顶层栏-->
    <%@include file="publics/topmenu.jsp" %>
    <!--导航栏-->
    <%@include file="publics/menu.jsp" %>
    <!--内容-->
    <div class="content">
        <ol class="breadcrumb">
            <li>网站资源 &nbsp;/&nbsp;
            </li>
            <li class="active">用户管理
            </li>
        </ol>
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header bg-light">
                        用户 管理
                    </div>

                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                               <c:forEach items="${userList}" var="user">
                                   <tr>
                                       <td>${user.userId}</td>
                                       <td class="text-nowrap">${user.userNickName}</td>
                                       <td>${user.userEmail}</td>
                                       <c:if test="${user.userStatus==1}">
                                           <td>启用</td>
                                       </c:if>
                                       <c:if test="${user.userStatus==0}">
                                           <td>禁用</td>
                                       </c:if>
                                       <td>
                                           <c:if test="${user.userStatus==0}">
                                           <a href="${pageContext.request.contextPath}/enUser/${user.userId}">
                                               <button type="button" class="btn btn-success btn-sm">启用</button>
                                           </a>
                                           </c:if>
                                           <c:if test="${user.userStatus==1}">
                                           <a href="${pageContext.request.contextPath}/unUser/${user.userId}">
                                               <button type="button" class="btn btn-danger btn-sm">禁用</button>
                                           </a>
                                           </c:if>
                                   </tr>
                               </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/vendor/JQuery/jquery-3.4.1.min.js"></script>

<script src="${pageContext.request.contextPath}/static/vendor/popper.js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/static/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/vendor/chart.js/chart.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/carbon.js"></script>

<script type="text/javascript">
    $(".icon-target").parent().addClass("active");
</script>
</body>

</html>
