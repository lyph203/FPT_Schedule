<%-- 
    Document   : utime
    Created on : Jun 10, 2023, 9:23:48 PM
    Author     : Pham Huong Ly
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href='${pageContext.request.contextPath}\css\loginFAP.css' rel='stylesheet'>
        <title>Login FAP</title>
    </head>
    <body>
        <style>
            body{
                width: 100%;
                background-image: url('https://i.pinimg.com/originals/1e/bf/8f/1ebf8f92b8ac4f455ef545ec19f23f21.jpg');
                background-size: cover;
                margin: 0;
                font-family: 'Poppins', sans-serif;
                animation: rotate 36s infinite linear alternate;
            }

            .header{
                width: 500px;
                border-radius: 15px;
                box-shadow: 1px 1px 5px 2px lightgray;
                justify-content: center;
                align-items: center;
                margin: 0 auto;
                margin-top: 20px;
                background-color: white;
            }

            .header img{
                width: 15%;
                text-align: center;
                margin-right: 10px;
            }

            .header p{
                font-size: 1.3rem;
                text-align: center;
                font-weight: 400;
            }

            .login-form{
                width: 350px;
                justify-content: center;
                margin: 0 auto;
                margin-top: 9%;
                font-size: 1.3rem;
                padding: 15px;
                border-radius: 15px;
                box-shadow: 1px 1px 5px 2px lightgray;
                background-color: white;
            }

            .login-form h3{
                font-weight: 600;
                text-align: center;
            }

            .login-form p{
                font-weight: 400;
                font-size: 1rem;
                text-align: center;
                color: rgb(201, 197, 197);
                margin-bottom: 40px;
            }

            .login-form form{
                text-align: center;
            }

            .login-form .input-acc{
                border-radius: 15px;
                padding: 15px 18%;
                border: 2px solid #ebebeb;
                font-family: inherit;
                font-size: 14px;
                transition: all 0.375s;
                margin-bottom: 20px;
            }

            .login-form .input-acc:hover{
                border: 2px solid #216ce7;
            }

            .login-form .login-btn{
                margin-top: 30px;
                margin-bottom: 2rem;
                padding: 15px 31%;
                font-weight: 600;
                color: white;
                background: #216ce7;
                border-radius: 15px;
                border: 0;
                box-shadow: 1px 1px 5px 2px lightgray;
                font-size: 1rem;
                transition: all 0.375s;
            }

            .login-form .login-btn:hover{
                background-color: #10449A;
            }

            .opt{
                padding: 15px;
                border-radius: 15px;
                margin-bottom: 50px;
                font-weight: 400;
                font-family: 'Poppins', sans-serif;
                font-size: 1.1rem;
                border: 0;
                box-shadow: 1px 1px 5px 2px lightgray;
            }

            .footer{
                position:absolute;
                bottom:0;
                width:100%;
                height:50px;
                text-align: center;
                align-items: center;
            }

            .footer p{
                display: inline-block;
                margin: 0 auto;
                text-align: center;
                color: white;
                vertical-align: top;
            }

            .footer a{
                display: inline-block;
                margin-left: 20px;
            }

            .displayflex{
                display: flex;
            }

            @keyframes rotate{
                100%{
                    background-position: 15% 50%;
                }
            }
        </style>
        <div class="mySlide fade"></div>
        <!-- header -->
        <div class="header displayflex">
            <img src="https://i.pinimg.com/474x/bd/58/e1/bd58e10dd230eee0f0172a084ed81027.jpg" alt="logo truong fbt">
            <p>FPT UNIVERSITY ACADEMIC PORTAL</p>
        </div>

        <!-- form -->
        <div class="login-form">
            <h3>Đăng nhập</h3>
            <form action="login" method="POST">
                <p>Nhập tài khoản và mật khẩu</p>
                <input type="text" class="input-acc" placeholder="Tên đăng nhập" name="username">
                <br>
                <input type="password" class="input-acc" placeholder="Mật khẩu" name="password">
                <br>
                <input type="submit" class="login-btn" value="Đăng nhập" name="login">
            </form>
        </div>

        <!-- footer -->
        <div class="footer">
            <p>© Powered by Lyreon</p> 
            <a href="https://apps.apple.com/app/id1527723314">
                <img src="https://fap.fpt.edu.vn/images/app-store.png" style="width: 120px; height: 40px" alt="apple store" /></a>
            <a href="https://play.google.com/store/apps/details?id=com.fuct">
                <img src="https://fap.fpt.edu.vn/images/play-store.png" style="width: 120px; height: 40px" alt="google store" /></a>
        </div>
    </body>
</html>
