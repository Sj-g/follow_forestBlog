<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/13
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Carbon - Admin Template</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/vendor/simple-line-icons/css/simple-line-icons.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/vendor/font-awesome/css/fontawesome-all.min.css">
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
            <li>文章管理 / </a>
            </li>
            <li class="active">分类管理</a>
            </li>
        </ol>
        <div class="row">
            <div class="col-md-12">
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">添加</button>
                <div class="card">
                    <div class="card-header bg-light">
                        分类 列表
                    </div>

                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>

                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${categoryMap}" var="entry">
                                    <tr>
                                        <td>${entry.key.categoryId}</td>
                                        <td class="text-nowrap">${entry.key.categoryName}</td>
                                        <td>
                                            <c:if test="${entry.key.stair==0}">
                                                <a href="${pageContext.request.contextPath}/delete/${entry.key.categoryId}">
                                                    <button type="button" class="btn btn-success btn-sm">修改</button>
                                                </a>
                                            </c:if>
                                            <c:if test="${entry.key.stair==1}">
                                                <a href="${pageContext.request.contextPath}/delete/${entry.key.categoryId}">
                                                    <button type="button" class="btn btn-success btn-sm">修改</button>
                                                </a>
                                                <a href="${pageContext.request.contextPath}/mod/${entry.key.categoryId}">
                                                    <button type="button" class="btn btn-danger btn-sm">删除</button>
                                                </a>
                                            </c:if>

                                        </td>
                                    </tr>
                                    <c:forEach items="${entry.value}" var="category">
                                        <tr>
                                            <td>--------------</td>
                                            <td class="text-nowrap">${category.categoryName}</td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/delete/${entry.key.categoryId}">
                                                    <button type="button" class="btn btn-success btn-sm">修改</button>
                                                </a>
                                                &nbsp;&nbsp;
                                                <a href="${pageContext.request.contextPath}/mod/${entry.key.categoryId}">
                                                    <button type="button" class="btn btn-danger btn-sm">删除</button>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
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
    $(".icon-grid").parent().addClass("active");
</script>
</body>

</html>
