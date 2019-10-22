<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/16
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
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
    <script src="${pageContext.request.contextPath}/static/js/jquery-2.1.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/pace.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/modernizr.custom.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/editormd/css/editormd.preview.css"/>
</head>

<body id="single">
<div class="container">
    <header id="site-header">
        <div class="row">
            <div class="col-md-4 col-sm-5 col-xs-8">
                <div class="logo">
                    <h1>文章详细</h1>
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
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </nav>
                <div id="header-search-box">
                    <a id="search-menu" href="#"><span id="search-icon" class="ion-ios-search-strong"></span></a>
                    <div id="search-form" class="search-form">
                        <form role="search" method="get" id="searchform" action="#">
                            <input type="search" placeholder="搜索" required>
                            <button type="submit"><span class="ion-ios-search-strong"></span></button>
                        </form>
                    </div>
                </div>
            </div><!-- col-md-8 -->
        </div>
    </header>
</div>

<div class="content-body">
    <div class="container">
        <div class="row">
            <main class="col-md-12">
                <article class="post post-1">
                    <header class="entry-header">
                        <h1 class="entry-title">${article.articleTitle}</h1>
                        <div class="entry-meta">
                            <span class="post-date"><a href="#"><time class="entry-date"
                                                                      datetime="2012-11-09T23:15:57+00:00">${article.articleCreateTime}</time></a></span>
                            <span class="comments-link"><a href="#">${article.articleCommentCount} 评论</a></span>
                            <span class="views-count"><a href="#">${article.articleViewCount} 阅读</a></span>
                        </div>
                    </header>
                    <div id="doc-content">
                        <!-- Server-side output Markdown text -->
                        <textarea style="display:none;"> ${article.articleContent}</textarea>
                    </div>
                </article>
                <section class="comment-area" id="comment-area">
                    <hr>
                    <h3>发表评论</h3>
                    <form action="#" method="post" class="comment-form">
                        <div class="row">
                            <div class="col-md-12">
                                <label for="id_comment">评论：</label>
                                <textarea name="comment" id="id_comment" required></textarea>
                                <button type="submit" class="comment-btn">发表</button>
                            </div>
                        </div>    <!-- row -->
                    </form>
                    <div class="comment-list-panel">
                        <h3>评论列表，共 <span>${article.articleCommentCount}</span> 条评论</h3>
                        <ul class="comment-list list-unstyled">
                            <c:forEach var="comment" items="${commentList}">
                                <li class="comment-item">
                                    <span class="nickname">${comment.commentAuthorName}</span>
                                    <time class="submit-date"
                                          datetime="2012-11-09T23:15:57+00:00">${comment.commentCreateTime}</time>
                                    <div class="text">
                                            ${comment.commentContent}
                                    </div>
                                </li>
                            </c:forEach>

                        </ul>
                    </div>
                </section>
            </main>
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
<script src="${pageContext.request.contextPath}/static/vendor/JQuery/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/static/editormd/editormd.js"></script>
<script src="${pageContext.request.contextPath}/static/editormd/lib/marked.min.js"></script>
<script src="${pageContext.request.contextPath}/static/editormd/lib/prettify.min.js"></script>
<script type="text/javascript">
    var testEditor;
    $(function () {
        testEditor = editormd.markdownToHTML("doc-content", {//注意：这里是上面DIV的id
            htmlDecode: "style,script,iframe",
            emoji: true,
            taskList: true,
            tex: true, // 默认不解析
            flowChart: true, // 默认不解析
            sequenceDiagram: true, // 默认不解析
            codeFold: true,
        });
    });
</script>
</body>
</html>
