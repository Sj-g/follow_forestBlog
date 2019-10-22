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
                <form id="signupForm">
                    <div class="card p-4">
                        <div class="card-header text-center text-uppercase h4 font-weight-light">
                            Register
                        </div>
                        <div class="card-body py-5">
                            <div class="form-group">
                                <label class="form-control-label">Name</label>
                                <input type="text" class="form-control" name="name" id="name">
                            </div>

                            <div class="form-group">
                                <label class="form-control-label">Email</label>
                                <input type="email" class="form-control" name="email" id="email">
                            </div>

                            <div class="form-group">
                                <label class="form-control-label">Password</label>
                                <input type="password" class="form-control" name="password" id="password1">
                            </div>

                            <div class="form-group">
                                <label class="form-control-label">Confirm Password</label>
                                <input type="password" class="form-control" id="password2">
                            </div>
                        </div>

                        <div class="card-footer">
                            <button type="submit" class="btn btn-success btn-block" id="signup" ajax-va="error" ajax-va2="error">Create Account</button>
                        </div>
                    </div>
                </form>

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
    $("#registtration").click(function () {
        var empName = $("#name").val();
        var password = $("#password1").val();
        var password2 = $("#password2").val();
        if (empName===""){
            showValidateMsg("#name", "error", "用户名不能为空");
            $("#signup").attr("ajax-va2", "error");
        }
        if (password2===""){
            showValidateMsg("#password2", "error", "密码不能为空");
            $("#signup").attr("ajax-va", "error");
        }
        if($("#signup").attr("ajax-va")==="success"){
            if($("#signup").attr("ajax-va2")==="success"){
                var s = {"username": empName, "password": password};
                $.ajax({
                    url: "${pageContext.request.contextPath}/login/save",
                    data: JSON.stringify(s),
                    type: "post",
                    contentType: "application/json",
                    success: function (result) {
                        if(result.state===200){
                            alert("注册完成");
                            location.reload()
                        }else{
                            // alert("错误");
                            showValidateMsg("#name", "error",result.message);
                            $("#signup").attr("ajax-va2", "error");
                        }
                    }
                });
            }

        }
    });
    //判断密码是否一致
    $("#password2").change(function () {
        var password = $("#password1").val();
        var password2 = $("#password2").val();
        if (password === password2) {
            showValidateMsg("#password2", "success", "");
            $("#signup").attr("ajax-va", "success");
        } else {
            showValidateMsg("#password2", "error", "密码不一样");
            $("#signup").attr("ajax-va", "error");
        }
    });
    // 判读密码是否一致
    $("#password1").change(function (){
        // console.log($("#registtration").attr("ajax-va"));
        var password = $("#password1").val();
        var password2 = $("#password2").val();
        if(password2!=="") {
            if (password === password2) {
                $("#signup").attr("ajax-va", "success");
                showValidateMsg("#password2", "success", "");

            } else {
                $("#signup").attr("ajax-va", "error");
                showValidateMsg("#password2", "error", "密码不一样");
            }
        }
    });
    //查看用户名是否重复
    $("#name").change(function () {
        if (validateAddForm()) {
            var empName = $("#name").val();
            $.ajax({
                url: "${pageContext.request.contextPath}/login/checkuser",
                data: "empName=" + empName,
                type: "POST",
                success: function (result) {
                    console.log(result);
                    if (result.code === 200) {
                        // console.log(result)
                        showValidateMsg("#name", "success", "用户名可用");
                        $("#signup").attr("ajax-va2", "success");
                    } else {
                        showValidateMsg("#name", "error", result.message);
                        $("#signup").attr("ajax-va2", "error");
                    }
                }

            });
        }

    });

    //给前端页面添加特效
    function showValidateMsg(ele, status, msg) {
        // 清除当前元素的校验状态
        $(ele).parent().removeClass("has-success has-error");
        $(ele).next("span").text("");
        if ("success" === status) {
            $(ele).parent().addClass("has-success");
            $(ele).next("span").text(msg);
        } else if ("error" === status) {
            $(ele).parent().addClass("has-error");
            $(ele).next("span").text(msg);
        }
    }
    //检验
    function validateAddForm() {
        var empName = $("#name").val();
        // console.log(empName);
        var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
        if (!regName.test(empName)) {
            // 应该清空这个元素之前的样式
            showValidateMsg("#name", "error", "用户名必须是2-5位中文或者6-16位英文和数字的组合");
            $("#signup").attr("ajax-va2", "error");
            return false;
        } else {
            showValidateMsg("#name", "success", "");
            $("#signup").attr("ajax-va2", "success");
        }
        return true;
    }
</script>
</body>
</html>

