$(document).ready(function(){
    $("#demosMenu").change(function(){
        window.location.href = $(this).find("option:selected").attr("id") + '.html';
    });

    $('.nextsection').on('click', function(){
        $target = $($(this).attr('href')).offset().top-30;

        $('body, html').animate({scrollTop : $target}, 750, 'easeOutExpo');
        return false;
    });

});
$(window).on('hashchange', function(){
    var datos = window.location.hash;
    if((datos=='#second-page')||(datos=='#first-page')||(datos=='#second-page/1')||(datos=='#second-page/2')){
        $('.menu-button').addClass('menu-negro');
        return false;
    }else{
        $('.menu-button').removeClass('menu-negro');
        return false;
    }
});

