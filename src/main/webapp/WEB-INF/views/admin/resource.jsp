<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/13
  Time: 18:10
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
            <li>网站资源 &nbsp;/&nbsp;</li>
            <li class="active">资源管理</li>
        </ol>
        <div class="row">
            <div class="col-md-12">
                <c:forEach items="${stringListMap}" var="map">
                <div class="card">
                    <div class="card-header bg-light">
                        ${map.key}
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>

                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${map.value}" var="resource">
                                    <tr>
                                        <td>${resource.resourceId}</td>
                                        <td class="text-nowrap">${resource.resourceName}</td>
                                        <c:if test="${resource.resourceStatus==1}">
                                            <td>启用</td>
                                        </c:if>
                                        <c:if test="${resource.resourceStatus==0}">
                                            <td>禁用</td>
                                        </c:if>
                                        <td>
                                            <c:if test="${resource.resourceStatus==0}">
                                                <a href="${pageContext.request.contextPath}/enable/${resource.resourceId}">
                                                    <button type="button" class="btn btn-success btn-sm">启用</button>
                                                </a>
                                            </c:if>
                                            <c:if test="${resource.resourceStatus==1}">
                                                <a href="${pageContext.request.contextPath}/disable/${resource.resourceId}">
                                                    <button type="button" class="btn btn-danger btn-sm">禁用</button>
                                                </a>
                                            </c:if>

                                        </td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                </c:forEach>
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
    $(".icon-speedometer").parent().addClass("active");
</script>
</body>

</html>