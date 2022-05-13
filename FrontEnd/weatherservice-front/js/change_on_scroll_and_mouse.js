window.onscroll = function() {
    scrollFunction();
    moveImage();
};

document.getElementById('menu_photos').onmouseover = function() {
    $('.upload_menu').css({display:'flex'}).animate({opacity:'1'}, 500);
}

document.getElementById('menu_photos').onmouseout = function() {
    $('.upload_menu').css({display:'none', opacity:'0'});
}

document.getElementById('upload_menu').onmouseover = function() {
    $('.upload_menu').css({display:'flex', opacity:'1'});
}

document.getElementById('upload_menu').onmouseout = function() {
    $('.upload_menu').css({display:'none', opacity:'0'});
}

function scrollFunction() {
    if (document.body.scrollTop > 35 || document.documentElement.scrollTop > 35) {
        document.getElementById("header").style.height = "4vw";
        document.getElementById("logo_text").style.opacity = "0";
        document.getElementById("header__inner").style.marginTop = "1.85vw";
        document.getElementById("background_header").style.paddingBottom = "20px";
    } else {
        document.getElementById("header").style.height = "7vw";
        document.getElementById("logo_text").style.opacity = "1";
        document.getElementById("header__inner").style.marginTop = "0px";
        document.getElementById("background_header").style.paddingBottom = "0px";
    }
}

function moveImage(){
    let top = 0;
    top = (top - document.documentElement.scrollTop)*0.5;
    document.getElementById("back_img").style.top = top+"px";
}