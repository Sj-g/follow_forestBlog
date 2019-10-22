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
<body>
<div class="page-wrapper flex-row align-items-center">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-5">
                <div class="card p-4">
                    <div class="card-header text-center text-uppercase h4 font-weight-light">
                        Register
                    </div>
                    <div class="card-body py-5">
                        <div class="form-group">
                            <label class="form-control-label">Name</label>
                            <input type="text" class="form-control" name="name" id="name">
                            <span id="waring"></span>
                        </div>

                        <div class="form-group">
                            <label class="form-control-label">Email</label>
                            <input type="email" class="form-control" name="email" id="email">
                            <span id="em"></span>
                        </div>

                        <div class="form-group">
                            <label class="form-control-label">Password</label>
                            <input type="password" class="form-control" name="password" id="password1">
                            <span></span>
                        </div>

                        <div class="form-group">
                            <label class="form-control-label">Confirm Password</label>
                            <input type="password" class="form-control" id="password2">
                            <span id="pa"></span>
                        </div>
                    </div>

                    <div class="card-footer">
                        <button type="submit" class="btn btn-success btn-block" id="signup" ajax-va="error"
                                ajax-va2="error" ajax-va3="error">Create Account
                        </button>
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
<script type=text/javascript>
    $("#signup").click(function () {
        var adminName = $("#name").val();
        var email = $("#email").val();
        var password = $("#password1").val();
        var password2 = $("#password2").val();
        if (adminName === "") {
            alert("用户名不能为空");
            $("#signup").attr("ajax-va3", "error");
        }else{
            $("#signup").attr("ajax-va3", "success");
        }
        if (email === "") {
            alert("邮箱不能为空");
            $("#signup").attr("ajax-va2", "error");
        }
        if (password2 === "") {
            alert("密码不能为空");
            $("#signup").attr("ajax-va", "error");
        }
        if ($("#signup").attr("ajax-va") === "success") {
            if ($("#signup").attr("ajax-va2") === "success") {
                var s = {"adminName": adminName, "password": password, "email": email};
                $.ajax({
                    url: "${pageContext.request.contextPath}/register",
                    data: JSON.stringify(s),
                    type: "post",
                    contentType: "application/json",
                    success: function (result) {
                        if (result.code === 200) {
                            alert("注册完成");
                            location.reload()
                        } else {
                            alert(result.message);
                            $("#signup").attr("ajax-va2", "error");
                        }
                    }
                });
            }

        }
    });
    /**
     * 密码和确定密码都有一个事件，因为用户可能改密码而不会该确定密码
     */
    //判断密码是否一致
    $("#password2").change(function () {
        var password = $("#password1").val();
        var password2 = $("#password2").val();
        if (password === password2) {
            $("#pa").text("");
            $("#signup").attr("ajax-va", "success");
        } else {
            $("#pa").text("确认密码不一样");
            $("#signup").attr("ajax-va", "error");
        }
    });
    //判断密码是否一致
    $("#password").change(function () {
        var password = $("#password1").val();
        var password2 = $("#password2").val();
        if (password === password2) {
            $("#pa").text("");
            $("#signup").attr("ajax-va", "success");
        } else {
            $("#pa").text("两次密码不一样");
            $("#signup").attr("ajax-va", "error");
        }
    });

    //查看邮箱是否重复
    $("#email").change(function () {
        if (validateAddForm()) {
            var email = $("#email").val();
            $.ajax({
                url: "${pageContext.request.contextPath}/repeat",
                data: "email=" + email,
                type: "POST",
                success: function (result) {
                    if (result.code === 200) {
                        // console.log(result)
                        $("#em").text("邮箱可用");
                        $("#signup").attr("ajax-va2", "success");
                    } else {
                        $("#em").text("邮箱重复");
                        $("#signup").attr("ajax-va2", "error");
                    }
                }

            });
        }
    });

    //检验
    function validateAddForm() {
        var email = $("#email").val();
        // console.log(empName);
        var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
        if (!reg.test(email)) {
            if (email==""||email==null){
                $("#em").text("");
                $("#signup").attr("ajax-va2", "error");
            }else{
                $("#em").text("邮箱格式不正确");
                $("#signup").attr("ajax-va2", "error");
            }
            return false;
        } else {
            $("#em").text("");
            $("#signup").attr("ajax-va2", "success");
        }
        return true;
    }
</script>
</body>
</html>

