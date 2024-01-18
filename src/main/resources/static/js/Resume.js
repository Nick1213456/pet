$(".photo").css("height",$(".photo").width());
$(".circle").css("height",$(".circle").width());
$(".right").css("min-height",parseInt($(".left").height()) + 40 +'px');

$(window).resize(function() {
    $(".photo").css("height",$(".photo").width());
    $(".circle").css("height",$(".circle").width());
    $(".right").css("min-height",parseInt($(".left").height()) + 40 +'px');
});