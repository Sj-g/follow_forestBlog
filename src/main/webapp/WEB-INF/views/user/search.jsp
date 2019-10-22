<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/16
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>文章详情</title>

    <!-- meta -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
<%--    <link rel="stylesheet" href="http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/pace.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/custom.css">
    <!-- js -->
    <script src="${pageContext.request.contextPath}/static/js/ jquery-2.1.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/pace.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/modernizr.custom.js"></script>
</head>

<body>
<div class="container">
    <header id="site-header">
        <div class="row">
            <div class="col-md-4 col-sm-5 col-xs-8">
                <div class="logo">
                    <h1>文章详情</h1>
                </div>
            </div><!-- col-md-4 -->
            <div class="col-md-8 col-sm-7 col-xs-4">
                <nav class="main-nav" role="navigation">
                    <div class="navbar-header">
                        <button type="button" id="trigger-overlay" class="navbar-toggle">
                            <span class="ion-navicon"></span>
                        </button>
                    </div>

                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav navbar-right">
                            <li class="cl-effect-11"><a href="${pageContext.request.contextPath}/user/homepage/1"
                                                        data-hover="首页">首页</a></li>
                            <li>
                                <form action="${pageContext.request.contextPath}/user/search" method="get">
                                    <input type="search" name="message"  class="form-control">
                                    <button type="submit" class="btn btn-default ">提交</button>
                                </form>
                            </li>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </nav>
            </div><!-- col-md-8 -->
        </div>
    </header>
</div>

<div class="content-body">
    <div class="container">
        <div class="row">
            <main class="col-md-8">
                <c:forEach var="article" items="${pageInfo.list}">
                    <article class="post post-1">
                        <header class="entry-header">
                            <h1 class="entry-title">
                                <a href="${pageContext.request.contextPath}/user/getDetailArticle/${article.articleId}">${article.articleTitle}</a>
                            </h1>
                            <div class="entry-meta">
                                <span class="post-date"><a href="#"><time class="entry-date"
                                                                          datetime="2012-11-09T23:15:57+00:00">${article.articleCreateTime}</time></a></span>
                                <span class="comments-link"><a href="#">${article.articleCommentCount} 评论</a></span>
                                <span class="views-count"><a href="#">${article.articleViewCount} 阅读</a></span>
                            </div>
                        </header>
                        <div class="entry-content clearfix">
                            <p>${article.articleSummary}</p>
                        </div>
                        <hr>
                    </article>
                </c:forEach>

                <div class="pagination">
                    <ul class="pagination">
<%--                        <li><a href="${pageContext.request.contextPath}/user/search?page=1">首页</a></li>--%>
                        <c:if test="${pageInfo.isFirstPage==true}">
                            <li class="previous disabled"><a href="javascript:void(0)"><span
                                    aria-hidden="true">&laquo;</span></a></li>
                        </c:if>
                        <c:if test="${pageInfo.isFirstPage==false}">
                            <li class="next"><a
                                    href="${pageContext.request.contextPath}/user/search?page=${pageInfo.pageNum-1}"><span
                                    aria-hidden="true">&laquo;</span></a></li>
                        </c:if>
                        <c:forEach items="${pageInfo.navigatepageNums}" var="page_Num">
                            <c:if test="${page_Num==pageInfo.pageNum}">
                                <li class="active"><a
                                        href="${pageContext.request.contextPath}/user/search?page=${page_Num}">${page_Num}</a>
                                </li>
                            </c:if>
                            <c:if test="${page_Num!=pageInfo.pageNum}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/user/search?page=${page_Num}">${page_Num}</a>
                                </li>
                            </c:if>
                        </c:forEach>
                        <c:if test="${pageInfo.isLastPage==true}">
                            <li class="previous disabled"><a href="javascript:void(0)"><span
                                    aria-hidden="true">&raquo;</span></a></li>
                        </c:if>
                        <c:if test="${pageInfo.isLastPage==false}">
                            <li class="next"><a
                                    href="${pageContext.request.contextPath}/user/search?page=${pageInfo.pageNum+1}"><span
                                    aria-hidden="true">&raquo;</span></a></li>
                        </c:if>
<%--                        <li><a href="${pageContext.request.contextPath}/user/search?page=${pageInfo.pages}">末页</a></li>--%>
                    </ul>
                </div>
            </main>

            <aside class="col-md-4">
                <div class="widget widget-recent-posts">
                    <h3 class="widget-title">最新文章</h3>
                    <ul>
                        <c:forEach items="${newestArticle}" var="article">
                            <li>
                                <a href="${pageContext.request.contextPath}/user/getDetailArticle/${article.articleId}">${article.articleTitle}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="widget widget-recent-posts">
                    <h3 class="widget-title">最火文章</h3>
                    <ul>
                        <c:forEach items="${mostPoplarArticle}" var="article">
                            <li>
                                <a href="${pageContext.request.contextPath}/user/getDetailArticle/${article.articleId}">${article.articleTitle}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>

                <%--                <div class="widget widget-category">--%>
                <%--                    <h3 class="widget-title">分类</h3>--%>
                <%--                    <ul>--%>
                <%--                        <c:forEach items="${categoryList}" var="category">--%>
                <%--                            <li>--%>
                <%--                                <a href="#">${category.key.categoryName}</a>--%>
                <%--                            </li>--%>
                <%--                            <c:forEach items="${category.value}" var="ca">--%>
                <%--                                <li>--%>
                <%--                                    <a href="#">${ca.categoryName}</a>--%>
                <%--                                </li>--%>
                <%--                            </c:forEach>--%>
                <%--                        </c:forEach>--%>
                <%--                    </ul>--%>
                <%--                </div>--%>

                <div class="widget widget-tag-cloud">
                    <h3 class="widget-title">标签云</h3>
                    <ul>
                        <c:forEach items="${tagList}" var="tag">
                            <li>
                                <a href="${pageContext.request.contextPath}/user/search?tagId=${tag.tagId}">${tag.tagName}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </aside>
        </div>
    </div>
</div>
<footer id="site-footer">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <p class="copyright">&copy 2019 - 10 - 18
                    <a href="${pageContext.request.contextPath}/login" title="后台" target="_blank">后台</a>
                </p>
            </div>
        </div>
    </div>
</footer>
<script type="text/javascript">
    console.log(${pageInfo.list})
</script>
</body>
</html>

