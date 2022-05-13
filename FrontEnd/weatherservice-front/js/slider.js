let swiper = new Swiper('.image-slider', {
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev'
    },
    scrollbar:{
        el: '.swiper-scrollbar',
        draggable: true
    },
    simulateTouch: false,
    keyboard:{
        enabled: true,
        onlyInViewport: true,
        pageUpDown: true
    },
    mousewheel:{
        sensitivity: 0.5,
        eventsTarget: ".image-slider"
    },
    autoHeight: true,
    slidesPerView: 3,
    spaceBetween: 10,
    slidesPerGroup: 2,
    freeMode: true,
    autoplay:{
        delay: 2000,
        stopOnLastSlide: true,
        disableOnInteraction: true
    },
    effect: 'coverflow',

    coverflowEffect:{
        rotate: 20,
        stretch: 20,
        slideShadows: true
    },

    breakpoints: {
        480:{
            slidesPerView: 1
        },
        992:{
            slidesPerView: 2
        },
        1286:{
            slidesPerView: 3
        }
    }
});