<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/13
  Time: 18:11
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
            <li>文章管理&nbsp;/&nbsp;</li>
            <li class="active"> 标签管理</li>
        </ol>
        <div class="row">
            <!--添加-->

            <div class="col-md-12">
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">添加</button>

                <div class="card">
                    <div class="card-header bg-light">
                        标签 列表
                    </div>

                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>

                                    <th>描述</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${tagList}" var="tag">
                                    <tr>
                                        <td>${tag.tagId}</td>
                                        <td>${tag.tagName}</td>
                                        <td class="text-nowrap">${tag.tagDescription}</td>
                                        <td>

                                            <button type="button" class="btn btn-success btn-sm"
                                                    onclick="mod(${tag.tagId},'${tag.tagName}','${tag.tagDescription}')">
                                                修改
                                            </button>

                                            &nbsp;&nbsp;
                                            <button type="button" class="btn btn-danger btn-sm"
                                                    onclick="hint(${tag.tagId})">删除
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
                <form id="tagAdd">
                    <div class="form-group">
                        <label for="exampleInput1">TagName</label>
                        <input type="text" class="form-control" name="tagName" id="exampleInput1">
                    </div>
                    <div class="form-group">
                        <label for="exampleInput2">tagDescription</label>
                        <input type="text" class="form-control" name="tagDescription" id="exampleInput2">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary" onclick="saveTag()">Submit</button>
            </div>
        </div>
    </div>
</div>
<!-- Modal2 -->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel2">Modal title</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="tagAdd2">
                    <div class="form-group">
                        <label for="exampleInput12">TagName</label>
                        <input type="text" class="form-control" name="tagName" id="exampleInput12">
                    </div>
                    <div class="form-group">
                        <label for="exampleInput22">tagDescription</label>
                        <input type="text" class="form-control" name="tagDescription" id="exampleInput22">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary" onclick="mod2()" value="" id="submit">Submit</button>
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
    $(".icon-umbrella").parent().addClass("active");

    function hint(tagId) {
        var boolean = confirm("确定要删除");
        if (boolean === true) {
            $.ajax({
                url: "${pageContext.request.contextPath}/deleteTag/" + tagId,
                dateType: "json",
                success: function (data) {
                    if (data.code == 200) {

                        alert("删除成功");
                        location.reload();
                    } else {
                        alert("删除失败");
                    }
                }

            });

        }
    }

    function saveTag() {
        console.log($("#tagAdd").serialize());
        $.ajax({
            url: "${pageContext.request.contextPath}/addTag",
            dateType: "json",
            type: "post",
            data: $("#tagAdd").serialize(),
            success: function (data) {
                console.log(data);
                if (data.code == 200) {
                    alert("添加成功");
                    location.reload();
                    $('#myModal').modal('toggle');

                }
            },
            error: function () {
                alert("添加失败");
            }
        });
    }

    function mod(tagId, tagName, tagDescription) {
        $("#exampleInput12").attr("value", tagName);
        $("#exampleInput22").attr("value", tagDescription);
        $("#submit").val(tagId);
        $('#myModal2').modal({
            backdrop: 'static'
        });
    }

    function mod2() {
        var tagName = $("#exampleInput12").val();
        var tagDescription = $("#exampleInput22").val();
        var tagId = $("#submit").val();
        var s = {"tagId": tagId, "tagName": tagName, "tagDescription": tagDescription};
        console.log(s);
        $.ajax({
            url: "${pageContext.request.contextPath}/modTag",
            dateType: "json",
            contentType: "application/json",
            data:JSON.stringify(s),
            type:"post",
            success: function (data) {
                if (data.code ==200){
                    alert("更新成功");
                    location.reload();
                }
            },
            error:function () {
                alert("更新失败");
            }
        });
    }
</script>
</body>
</html>
