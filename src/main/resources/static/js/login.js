$(document).ready(function () {
    $(".login_modal").hide();
    // 判斷登入登出顯示
    $.post('/check-session', function(data) {
        if (data===""){
            //如果沒登入
            $(".login_bar").show();
            $(".d-none1").hide();
        }else {
            $(".login_bar").hide();
            $(".d-none1").show();
        }

    });
    $(".d-none1").on('click',function(){
        alert("會員已登出 將返回首頁")
    })
});

$(document).on('click', function (e) {

    var modal = $(".login_bar_up");
    if (e.target.className == $(".login_bar").attr("class")) {
        $(".login_modal").show();
        $('a').css('pointer-events', 'none');
        $('.login_text2').css('pointer-events', 'auto');

    } else if (!modal.is(e.target) && modal.has(e.target).length === 0) {
        // 將彈出框隱藏                
        $(".login_modal").hide();
        $('a').css('pointer-events', 'auto');
    }



});
