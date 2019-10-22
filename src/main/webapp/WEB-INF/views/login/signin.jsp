<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/5
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html lang="en">

<head>
    <!-- meta data -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--font-family-->
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&amp;subset=devanagari,latin-ext"
          rel="stylesheet">
    <!-- title of site -->
    <title>Sign in</title>
    <!-- For favicon png -->
    <link rel="shortcut icon" type="image/icon" href="resource/assets/logo/favicon.png"/>
    <!--animate.css-->
    <link rel="stylesheet" href="resource/assets/css/animate.css">
    <!--bootstrap.min.css-->
    <link rel="stylesheet" href="resource/assets/css/bootstrap.min.css">
    <!-- bootsnav -->
    <link rel="stylesheet" href="resource/assets/css/bootsnav.css">
    <!--style.css-->
    <link rel="stylesheet" href="resource/assets/css/style.css">
    <!--responsive.css-->
    <link rel="stylesheet" href="resource/assets/css/responsive.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
</head>
<body>

<!-- signin end -->
<section class="signin">
    <div class="container">
        <div class="sign-content">
            <h2>sign in</h2>
            <div class="row">
                <div class="col-sm-12">
                    <div class="signin-form">
                        <form id="signinForm">
                            <div class="form-group">
                                <label for="signin_formEmaile">Email</label>
                                <input type="email" class="form-control" id="signin_formEmaile" name="email"
                                       placeholder="enter your email here">
                                <span id="email_span" judge="success" style="color: #dc3932"></span>
                            </div>
                            <div class="form-group">
                                <label for="signin_formPassword">password</label>
                                <input type="password" class="form-control" id="signin_formPassword" name="password"
                                       placeholder="Password">
                                <span id="password_span" judge="success" style="color: #dc3932"></span>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-12">
                    <div class="signin-password">
                        <div class="awesome-checkbox-list">
                            <ul class="unstyled centered">

                                <li>
                                    <input class="styled-checkbox" id="styled-checkbox-2" type="checkbox"
                                           value="value2">
                                    <label for="styled-checkbox-2">remember password</label>
                                </li>

                                <li>
                                    <a href="#">Forgot email or password ?</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="signin-footer">
                        <button type="button" class="btn signin_btn" data-toggle="modal" data-target=".signin_modal" id="buttonsigin">
                            sign in
                        </button>
                        <p>Don’t have an Account ?<a href="${pageContext.request.contextPath}/register">register</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>

</section>

<!--footer copyright start -->
<footer class="footer-copyright">
    <div id="scroll-TopFooter">
        <i class="fa fa-angle-double-up return-to-top" id="scroll-top" data-toggle="tooltip" data-placement="top"
           title="" data-original-title="Back to Top" aria-hidden="true"></i>
    </div><!--/.scroll-Top-->

</footer><!--/.hm-footer-copyright-->
<!--footer copyright  end -->

<script src="resource/assets/js/jquery.js"></script>

<!--modernizr.min.js-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js"></script>

<!--bootstrap.min.js-->
<script src="resource/assets/js/bootstrap.min.js"></script>

<!-- bootsnav js -->
<script src="resource/assets/js/bootsnav.js"></script>

<!-- jquery.sticky.js -->
<script src="resource/assets/js/jquery.sticky.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<!--Custom JS-->
<script src="resource/assets/js/custom.js"></script>
</body>
<script type="application/javascript">
    $("#buttonsigin").click(function () {
        var email=$("#signin_formEmaile").val();
        var password=$("#signin_formPassword").val();
        console.log(email);
        if (email==""||password==null){
            $("#email_span").text("邮箱不能为空").attr("judge","fail");
        }else{
            $("#email_span").text("").attr("judge","success");
        }
        if (!email.match("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")){
            $("#email_span").text("邮箱格式错误").attr("judge","fail");
        }else{
            $("#email_span").text("").attr("judge","success");
        }
        if (password==""||password==null){
            $("#password_span").text("密码不能为空").attr("judge","fail");
        }else{
            $("#password_span").text("").attr("judge","success");
        }
        if($("#password_span").attr("judge")=="success"&& $("#email_span").attr("judge")=="success") {
            console.log($("#signinForm").serialize());
            $.ajax({
                async: false,
                type: "POST",
                dataType: "JSON",
                data: $("#signinForm").serialize(),
                contentType: "application/x-www-form-urlencoded",
                url: "${pageContext.request.contextPath}/verification",
                success: function (date) {
                    console.log(date);
                    // window.location.href=""
                },
                error: function () {
                    alert("登陆失败");
                }
            });
        }
    });
</script>
</html>
