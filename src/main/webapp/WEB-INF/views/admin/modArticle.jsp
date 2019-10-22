<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/13
  Time: 18:10
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/editormd/css/editormd.css"/>
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
            <li>文章管理&nbsp;/&nbsp;
            </li>
            <li class="active">编写文章
            </li>
        </ol>
        <div class="container-fluid">
            <form id="articleForm">
                <input type="hidden" name="articleId" value="${article.articleId}">
                <div class="form-group">
                    <label>标题(请输入文章题目) <span style="color: #FF5722; ">*</span>
                        <input type="text" name="articleTitle" id="title" class="form-control"
                               value="${article.articleTitle}">
                    </label>
                </div>
                <div class="form-group">
                    <label>简介 <span style="color: #FF5722;">*</span>(文章请在150字内)</label>
                    <textarea class="form-control" rows="3" name="articleSummary">${article.articleSummary}</textarea>
                </div>

                <label>内容 <span style="color: #FF5722; ">*</span></label>
                <div id="my-editormd">
                    <textarea id="my-editormd-markdown-doc" name="my-editormd-markdown-doc"
                              style="display:none;">${article.articleContent}</textarea>
                </div>
                <div class="form-group">
                    <label>&nbsp;&nbsp;标签：</label>
                    <c:forEach items="${tagList}" var="tag">
                        <label class="checkbox-inline">
                            <input type="checkbox" name="articleTagIds" value="${tag.tagId}"> ${tag.tagName}
                        </label>
                    </c:forEach>

                </div>
                <div class="form-group">
                    <label>&nbsp;&nbsp;状态：</label>
                    <label class="radio-inline">
                        <input type="radio" name="articleStatus" id="inlineRadio1" value="1"> 发布
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="articleStatus" id="inlineRadio2" value="0"> 草稿
                    </label>
                </div>
                <div class="form-group">
                    <input type="button" value="提交修改" class="btn btn-success " id="button1"/>&nbsp;&nbsp;&nbsp;
                </div>
            </form>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/vendor/JQuery/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/static/vendor/popper.js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/static/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/vendor/chart.js/chart.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/carbon.js"></script>
<script src="${pageContext.request.contextPath}/static/editormd/editormd.js"></script>
<script type="text/javascript">
    var articleContext;
    $(function () {
        var editor = editormd("my-editormd", {
            width: "100%",
            height: 720,
            path: "${pageContext.request.contextPath}/static/editormd/lib/",
            emoji: true,
            saveHTMLToTextarea: true, // 保存 HTML 到 Textarea
            <%--tex: true,// 开启科学公式TeX语言支持，默认关闭--%>
            <%--flowChart: true,//开启流程图支持，默认关闭--%>
            <%--toolbarAutoFixed: true,//工具栏自动固定定位的开启与禁用--%>
            <%--imageUpload: true,--%>
            <%--imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],--%>
            <%--imageUploadURL: "${pageContext.request.contextPath}/upload"--%>
        });
        $("#button1").click(function () {
            var  articleContent=editor.getMarkdown();
            up(articleContent);
        });
    });
    <%--$("#select_add_dept").change(function () {--%>
    <%--    $("#select_add_dept2").empty();--%>
    <%--    //获取被选着的分类--%>
    <%--    var id = $("#select_add_dept").val();--%>
    <%--    console.log(id);--%>
    <%--    <c:forEach items="${mapCategory}" var="entry">--%>
    <%--    var categoryPid =${entry.key.categoryId};--%>
    <%--    if (categoryPid == id) {--%>
    <%--        <c:forEach items="${entry.value}" var="list">--%>
    <%--        $("#select_add_dept2").append("<option value='${list.categoryId}'>${list.categoryName}</option>");--%>
    <%--        </c:forEach>--%>
    <%--    }--%>
    <%--    </c:forEach>--%>
    <%--});--%>
    $(".icon-puzzle").parent().addClass("active");

    function up(articleContent) {
            $.ajax({
                url:"${pageContext.request.contextPath}/updateArticle",
                dateType:"json",
                type:"post",
                data:$("#articleForm").serialize()+"&articleContent="+articleContent,
                success:function (result) {
                    if (result.code==200){
                        alert("success");
                        window.location.href="${pageContext.request.contextPath}/articleList/1";
                    }else{
                        alert(result.message);
                    }

                }
            });
    }

</script>
</body>

</html>