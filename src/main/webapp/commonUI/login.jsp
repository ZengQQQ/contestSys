<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Archway HTML Template - Contact Us</title>

<!-- Stylesheets -->
<link href="${pageContext.request.contextPath}\css\bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}\css\style.css" rel="stylesheet">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png" type="image/x-icon">
<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.png" type="image/x-icon">
<!-- Responsive -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<link href="${pageContext.request.contextPath}\css\responsive.css" rel="stylesheet">

    <script>
        window.onload = function () {
            document.getElementById("vcode").onclick = function () {
                this.src = "${pageContext.request.contextPath}/checkCodeServlet?time=" + new Date().getTime();
            }
        }

        function refreshCode() {
            document.getElementById("vcode").src = "${pageContext.request.contextPath}/checkCodeServlet?time=" + new Date().getTime();
        }
    </script>
</head>

<body>
<div class="page-wrapper"> 
  
    <!-- Preloader -->

    <!-- Main Header-->
    <header class="main-header">
        <!-- Header Upper -->
        <div class="header-upper">
            <div class="auto-container">
                <!-- Main Box -->
                <div class="main-box clearfix">
                    <!--Logo-->
                    <div class="logo-box">
                        <div class="logo"><a href="${pageContext.request.contextPath}/index.jsp" title="Archway HTML Template"><img src="${pageContext.request.contextPath}/images/logo.png" alt="" title="Archway HTML Template"></a></div>
                    </div>

                    <div class="nav-box clearfix">
                        <!--Nav Outer-->
                        <div class="nav-outer clearfix">
                            <nav class="main-menu">
                            </nav>
                            <!-- Main Menu End-->
                        </div>
                        <!--Nav Outer End-->



                        <!-- Hidden Nav Toggler -->
                        <div class="nav-toggler">
                            <button class="hidden-bar-opener"><span class="icon"><img src="${pageContext.request.contextPath}/images/icons/menu-icon.png" alt=""></span></button>
                        </div>

                    </div>

                </div>
            </div>
        </div>
    </header>
    <!--End Main Header -->

    <!--Search Backdrop-->
    <div class="search-backdrop"></div>

    <!--Menu Backdrop-->
    <div class="menu-backdrop"></div>

    

    <!-- Page Title -->
    <section class="page-title" style="background-image: url(${pageContext.request.contextPath}/images/background/page-title.jpg);">
        <div class="auto-container">
            <h2>欢迎来到</h2>
            <h2>ATHLETIC_LEAGUE 组队系统</h2>
        </div>
    </section>
    <!-- End Page Title -->

    <!-- Contact One -->
    <section class="contact-one">
        <div class="auto-container">
        
            <div class="row clearfix">
                <!-- Column -->
                <div class="form-column col-lg-6 col-md-12 col-sm-12">
                    <div class="inner-column">
                        <h2>竞技联盟登录</h2>

                        <!-- Default Form -->
						<div class="contact-form">
							<form method="post" action="/loginPage" id="contact-form">
								
		                        <div class="form-group">
                                    <input type="text" name="Student_Id" placeholder="输入学号" maxlength="20" required>
                                </div>
                                
                                <div class="form-group">
                                    <input type="password" name="pwd" placeholder="输入密码" maxlength="20" required>
                                </div>
                                
                                <div class="form-group">
                                    <input type="text" name="verifycode" placeholder="输入验证码" maxlength="20" required>
                                </div>

                                <div class="form-group">
                                    <a href="javascript:refreshCode();" >
                                        <img style="margin-bottom: 0" src="${pageContext.request.contextPath}/checkCodeServlet" title="Click Refresh" id="vcode" width="150" height="75"/>
                                    </a>
                                </div>

                                
                                <div class="form-group text-right">
                                    <button class="theme-btn btn-style-two">
                                       <span class="btn-box"><span class="btn-txt">Login</span><i class="btn-arrow fal fa-arrow-right"></i></span>
                                    </button>
                                </div>
                                <style>
                                   span.btn-box:hover{
                                        color:rgb(255, 200, 0);
                                        background-color: rgba(255, 174, 0, 0.991);
                                        }
                                </style>

							</form>
                            <div class="alert alert-warning alert-dismissible" role="alert" >
                                <strong>${login_msg}</strong>
                            </div>
						</div>
						<!-- End Default Form -->

                    </div>
                </div>
                <!-- Column -->
                <div class="social-column col-lg-6 col-md-12 col-sm-12">
                    <div class="inner-column">
                        <h2>注册导航</h2>
                        <ul class="social-links">
                            <li><a href="${pageContext.request.contextPath}/commonUI/register.jsp">注 册<span class="arrow fa-solid fa-arrow-right fa-fw"></span></a></li>
                            <li><a href="#">There is nothing here <span class="arrow fa-solid fa-arrow-right fa-fw"></span></a></li>
                            <li><a href="#">It's true<span class="arrow fa-solid fa-arrow-right fa-fw"></span></a></li>
                            <li><a href="#">Believe me <span class="arrow fa-solid fa-arrow-right fa-fw"></span></a></li>
                        </ul>
                    </div>
                </div>

                <!-- Column -->
            

            </div>
        </div>
    </section>
    <!-- End Contact One -->
    
    <!--Subscribe Section-->
    <section class="subscribe-section">
        <div class="bg-layer" style="background-image: url(${pageContext.request.contextPath}/images/background/pattern-2.jpg);"></div>
        <div class="auto-container">
            <div class="content-box">
                <div class="row clearfix">
                    <div class="text-col col-xl-8 col-lg-12 col-md-12 col-sm-12">
                        <div class="inner">
                            <h2>联系我们!</h2>
                            <div class="text">如果遇到问题可以告诉我们你的联系方式</div>
                        </div>
                    </div>
                    <div class="form-col col-xl-4 col-lg-12 col-md-12 col-sm-12">
                        <div class="inner">
                            <div class="form-box subscribe-form">
                                <form method="post" action="new_for_manage.html">
                                    <div class="form-group">
                                        <div class="field-inner">
                                            <input type="text" name="massage" value="" placeholder="your Email" required>
                                        </div>
                                        <button type="submit" class="theme-btn"><i class="icon fal fa-arrow-right"></i></button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>


    <!--Main Footer-->

</div>
<!--End pagewrapper--> 

<!--Scroll to top-->
<div class="scroll-to-top scroll-to-target" data-target="html" title="Go To Top"><span class="icon fa fa-angle-up"></span></div>

<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.fancybox.js"></script>
<script src="${pageContext.request.contextPath}/js/owl.js"></script>
<script src="${pageContext.request.contextPath}/js/appear.js"></script>
<script src="${pageContext.request.contextPath}/js/wow.js"></script>
<script src="${pageContext.request.contextPath}/js/validate.js"></script>
<script src="${pageContext.request.contextPath}/js/custom-script.js"></script>
</body>
</html>
