$(function() {
    $(".menuLateral").on("click", "li", function() {
        $(this).closest('span').find('ul').find('li').removeClass('active');
        $(this).addClass("active");
    });
});