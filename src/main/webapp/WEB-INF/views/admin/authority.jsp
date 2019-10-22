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
            <li>网站资源 /
            </li>
            <li class="active">权限管理
            </li>
        </ol>
        <div class="row">
            <div class="col-md-12">
                <a href="${pageContext.request.contextPath}/adminRegister">
                    <button type="button" class="btn btn-primary">添加</button>
                </a>

                <div class="card">
                    <div class="card-header bg-light">
                        权限 管理
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
                                <c:forEach items="${adminList}" var="admin">
                                    <tr>
                                        <td class="adminId">${admin.adminId}</td>
                                        <td>${admin.adminNickName}</td>
                                        <td>${admin.adminEmail}</td>
                                        <td>
                                            <c:if test="${admin.adminStatus==0}">
                                                禁用中
                                            </c:if>
                                            <c:if test="${admin.adminStatus==1}">
                                                启用中
                                            </c:if>
                                        </td>

                                        <td>
                                            <button type="button" class="btn btn-primary btn-sm"
                                                    id="btn_admin">查看
                                            </button>
                                            &nbsp;&nbsp;
                                            <c:if test="${admin.adminStatus==0}">
                                                <a href="${pageContext.request.contextPath}/enAdmin/${admin.adminId}">
                                                    <button type="button" class="btn btn-success btn-sm">启用</button>
                                                </a>
                                            </c:if>
                                            <c:if test="${admin.adminStatus==1}">
                                                <a href="${pageContext.request.contextPath}/unAdmin/${admin.adminId}">
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
            </div>
        </div>
    </div>
</div>
<!-- Modal 2-->
<<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel2">权限查看</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <%--以获取权限--%>
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header bg-light">
                                获取 权限
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table" id="en">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>状态</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="Eninsert">

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%--                未获取权限--%>
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header bg-light">
                                未获 权限
                            </div>

                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table" id="un">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>状态</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="Uninsert">

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
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
    $(".icon-energy").parent().addClass("active");
    $(document).on("click", "#btn_admin", function () {
        // alert()
        var adminId = this.parentNode.parentNode.querySelector('.adminId').innerHTML;
        send(adminId);
        // 弹出模态框
        $('#myModal2').modal({
            backdrop: 'static'
        });
    });

    function send(adminId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/enAuthority",
            dateType: "json",
            data: "adminId=" + adminId,
            success: function (data) {
                if (data.code == 200) {
                    var item = data.mapper.haveRight;
                    $("#Eninsert").empty();
                    for (var i = 0; i < item.length; i++) {
                        var  address="${pageContext.request.contextPath}/unAbleAuthority/"+item[i].resourceId+"/"+adminId;
                       var s = '<tr> <td>' + item[i].resourceId + '</td> <td>' + item[i].resourceName + '</td> <td>启用中</td> <td> <button type="button" class="btn btn-danger btn-sm" onclick="UnAble('+item[i].resourceId+','+adminId+')">禁用</button> </td></tr>';
                        $("#Eninsert").append(s);
                    }
                    var item2 = data.mapper.noRight;
                    console.log(item2);
                    $("#Uninsert").empty();
                    for (var i = 0; i < item2.length; i++) {
                        var  address="${pageContext.request.contextPath}/unAbleAuthority/"+item2[i].resourceId+"/"+adminId;
                        var s = '<tr><td>' + item2[i].resourceId + '</td> <td>' + item2[i].resourceName + '</td> <td>禁用中</td> <td><button type="button" class="btn btn-primary btn-sm" onclick="EnAble('+item2[i].resourceId+','+adminId+')">启用</button> </td></tr>';
                        $("#Uninsert").append(s);
                    }
                }

            },
            error: function () {
                alert("fail");
            }
        });
    }
    function UnAble(resourceId,adminId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/unAbleAuthority/"+resourceId+"/"+adminId,
            dateType: "json",
            success:function (data) {
                alert(data.message);
                // $("#myModal2").reload();
                // location.reload();
                $('#myModal2').modal('toggle');
                // $('#myModal2').modal({
                //     backdrop: 'static'
                // });
            },
            error:function () {
                alert("错误");
            }
        });
    }
    function EnAble(resourceId,adminId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/enAbleAuthority/"+resourceId+"/"+adminId,
            dateType: "json",
            success:function (data) {
                alert(data.message);
                // $("#myModal2").reload();
                // location.reload();
                $('#myModal2').modal('toggle');
                // $('#myModal2').modal({
                //     backdrop: 'static'
                // });
            },
            error:function () {
                alert("错误");
            }
        });
    }
</script>
</body>

</html>