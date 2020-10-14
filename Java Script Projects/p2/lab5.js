$(document).ready(function () {
    $('.tab-menu button').show().css("display", "inline-block");
    $('.tab-menu button').show().css("border", "#ccc");
    $('.tab-menu button').show().css("float", "left");
    $('.tab-content').hide()
    $('.tab-menu button p').click(function () {
        var content = $(this).attr('href');
        $(content).show();
        $(content).siblings('.tab-content').hide();
    });
});
