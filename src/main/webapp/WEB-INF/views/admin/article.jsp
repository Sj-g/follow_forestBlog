<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/13
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <li>文章管理 &nbsp /</li>
            <li class="active">全部文章</li>
        </ol>
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header bg-light">
                        文章 列表
                    </div>

                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>文章题目</th>
                                    <th>状态</th>
                                    <th>观看人数</th>
                                    <th>喜欢人数</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="article" items="${pageInfo.list}">
                                    <tr>
                                        <td>${article.articleId}</td>
                                        <td class="text-nowrap">${article.articleTitle}</td>
                                        <c:if test="${article.articleStatus==0}">
                                            <td>草稿</td>
                                        </c:if>
                                        <c:if test="${article.articleStatus==1}">
                                            <td>已发布</td>
                                        </c:if>
                                        <td>${article.articleViewCount}</td>
                                        <td>${article.articleLikeCount}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/getArticleById/${article.articleId}">
                                                <button type="button" class="btn btn-primary btn-sm">
                                                    编辑
                                                </button>
                                            </a>
                                            &nbsp;&nbsp;
                                            <c:if test="${article.articleStatus==1}">
                                            <button type="button" class="btn btn-danger btn-sm" onclick="banArticleById(${article.articleId})">
                                                禁用
                                            </button>
                                            </c:if>
                                            <c:if test="${article.articleStatus==0}">
                                            <button type="button" class="btn btn-success btn-sm" onclick="starArticleById(${article.articleId})">
                                                启用
                                            </button>
                                            </c:if>
                                            &nbsp;&nbsp;
                                            <button type="button" class="btn btn-danger btn-sm" onclick="deleteArticleById(${article.articleId})">
                                                删除
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="publics/paging.jsp" %>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/vendor/JQuery/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/static/vendor/popper.js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/static/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/vendor/chart.js/chart.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/carbon.js"></script>
<script type="text/javascript">
    //改变导航栏css样式
    $(".icon-graph").parent().addClass("active");
    <%--console.log(${map});--%>
    //禁用文章
    function banArticleById(articleId) {
        // console.log(articleId);
        var boolean = confirm("确定禁用文章吗？");
        if (boolean === true) {
            $.ajax({
                url: "${pageContext.request.contextPath}/banArticleById/" + articleId,
                dateType: "json",
                success: function (data) {
                    if (data.code === 200) {
                        alert("禁用成功");
                        location.reload();
                    }
                },
                error: function () {
                    alert("禁用失败");
                }
            });
        }
    }
    //删除文章
    function deleteArticleById(articleId) {
        // console.log(articleId);
        var boolean = confirm("确定删除文章吗？");
        if (boolean === true) {
            $.ajax({
                url: "${pageContext.request.contextPath}/deleteArticleById/" + articleId,
                dateType: "json",
                success: function (data) {
                    if (data.code === 200) {
                        alert("删除成功");
                        location.reload();
                    }
                },
                error: function () {
                    alert("删除失败");
                }
            });
        }
    }
    //启用文章
    function starArticleById(articleId) {
        // console.log(articleId);
        var boolean = confirm("确定启用文章吗？");
        if (boolean === true) {
            $.ajax({
                url: "${pageContext.request.contextPath}/starArticleById/" + articleId,
                dateType: "json",
                success: function (data) {
                    if (data.code === 200) {
                        alert("启用成功");
                        location.reload();
                    }
                },
                error: function () {
                    alert("启用失败");
                }
            });
        }
    }
</script>
</body>
</html>
