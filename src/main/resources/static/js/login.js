checkLoginStatus();
// 监听 beforeunload 事件，页面即将被卸载时触发
$(window).on('beforeunload', function () {
    // 在强制刷新页面时重新检查登录状态
    checkLoginStatus();
});
if (location.href==="http://localhost:8080/forgetpassword"){
    // 如果是在忘記密碼網址就秀出密碼標籤
    $("#showpassword").show();
    $.post('/check-session', function (data) {
        if (data === "") {
            //如果沒登入
            $(".login_modal").show();
        } else {
            $(".login_modal").hide();
        }
    });
    // $("#forget_password3").hide();
}

// 封裝檢查是否登入
function checkLoginStatus() {
    $(".d-none1").hide(); //隱藏登出
    // $("#d-none3").hide(); //隱藏會員資料
    // $("#d-none4").hide(); //隱藏後台資料
    $.post('/check-session', function (data) {
        if (data === "") {
            //如果沒登入
            console.log("沒登入")
            $("#login_bar").show();
            $(".d-none1").hide();
        } else{
            console.log("有登入")
            if (data[data.length - 1]==="2"){
                console.log("管理者登入")
                $("#login_bar01").hide();
                $(".d-none1").show();
                $("#d-none3").show();
                $("#d-none4").show();
            }else{
                console.log("會員登入")
                $("#login_bar01").hide();
                $(".d-none1").show();
                $("#d-none3").show();
                $("#d-none4").hide();
            }


        }
    });
}

$("#d-none2").on('click', function () {
    alert("會員已登出 將返回首頁")
})

$(document).on('click', function (e) {

    var modal = $(".login_bar_up");
    //如果點會員登入跳出框框
    if (e.target.id == $("#login_bar01").attr("id")) {
        $.post('/check-session', function (data) {
            if (data === "") {
                //如果沒登入
                $(".login_modal").show();
                $('a').css('pointer-events', 'none');
                $('.login_text2').css('pointer-events', 'auto');
            } else {
                console.log("有登入")
                alert("您已登入")
                $("#login_bar").hide();
                $(".d-none1").show();
            }
        });

    } else if (!modal.is(e.target) && modal.has(e.target).length === 0) {
        // 將彈出框隱藏
        $(".login_modal").hide();
        $('a').css('pointer-events', 'auto');
    }


});


$("#forget_password2_2").click(function () {
    $("#forget_password3").show();
    $("#login_modal_2").hide();
});
$("#login_up3").click(function () {
    $("#forget_password3").hide();
    $("#login_modal_2").show();
});

$("#log-in").click(function () {
    $(".login_modal").hide();
});



// $("#verify").click(function (){
//     $("#login_modal_2").hide();
//     $("#forget_password3").show();
// })