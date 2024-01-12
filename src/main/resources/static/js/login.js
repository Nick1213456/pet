$(document).ready(function () {
    $(".login_modal").hide();

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

    console.log(modal.has(e.target).length);



});
