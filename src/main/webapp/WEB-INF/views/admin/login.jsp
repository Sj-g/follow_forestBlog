<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/13
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<body>
<div class="page-wrapper flex-row align-items-center">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-5">
                <div class="card p-4">
                    <div class="card-header text-center text-uppercase h4 font-weight-light">
                        管理员 登 陆
                    </div>
                    <div class="card-body py-5">
                        <div class="form-group">
                            <label class="form-control-label">邮箱</label>
                            <input type="email" class="form-control" id="email" name="email" judge="fail">
<%--                            <span id="waring"></span>--%>
                        </div>

                        <div class="form-group">
                            <label class="form-control-label">密码</label>
                            <input type="password" class="form-control" id="password" name="password" judge="fail">
                        </div>
                    </div>

                    <div class="card-footer">
                        <div class="row">
                            <div class="col-6">
                                <button type="submit" class="btn btn-primary px-5" id="sign_in">登陆</button>
                            </div>
                        </div>
                    </div>
                </div>
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
    $("#sign_in").click(function () {
        var email = $("#email").val();
        var password = $("#password").val();
        if (email == "" || password == null) {
            $("#email").attr("judge", "fail");
            alert("邮箱不能为空");
            // $("#waring").text("邮箱不能为空");
        } else {
            $("#email").attr("judge", "success");
            // $("#waring").text("");
        }
        if (!email.match("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {
            $("#email").attr("judge", "fail");
            alert("邮箱格式错误");
            // $("#waring").text("邮箱格式错误");
        } else {
            $("#email").attr("judge", "success");
            // $("#waring").text("")
        }
        if (password == "" || password == null) {
            $("#password").attr("judge", "fail");
            alert("密码不能为空");
            // $("#waring").text("密码不能为空");
        } else {
            $("#password").attr("judge", "success");
            // $("#waring").text("");
        }
        if ($("#password").attr("judge") === "success" && $("#email").attr("judge") === "success") {
            var s = {"email": email, "password": password};
            $.ajax({
                url: "${pageContext.request.contextPath}/verification",
                dateType: "json",
                // contentType: "application/json",
                data: s,
                type: "POST",
                success: function (result) {
                    // alert("success");
                    console.log(result);
                    if (result.code === 200) {
                        alert("登陆成功");
                        window.location.href = "${pageContext.request.contextPath}/articleList/1";
                    } else {
                        alert(result.message);
                    }
                },
                error: function () {
                    alert("错误登陆");
                }
            });
        }

    });

</script>
</body>
</html>