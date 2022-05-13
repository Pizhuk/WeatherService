const list = "list";
axios.post("http://127.0.0.1:8090/list", list)
    .then((response) => {
        let info_json = response.data;
        const words = info_json.split('@');
        for (let i = 0; i < words.length; i++){
            makePhotos(findNeed(words[i]));
        }
        setTimeout(function(){
            document.getElementById("preloader").style.display = "none";
        }, 500);
    }, (error) => {
        console.log(error.response.data);
    });

function makePhotos(dir){
    let firstDiv = document.querySelector(".image-slider__wrapper");
    let secDiv = document.createElement("div");
    secDiv.classList.add("image-slider__slide");
    secDiv.classList.add("swiper-slide");
    firstDiv.appendChild(secDiv);

    let thirdDiv = document.createElement("div");
    thirdDiv.classList.add("image-slider__image");
    secDiv.appendChild(thirdDiv);

    let img = document.createElement("img");
    img.src = dir;
    thirdDiv.appendChild(img);
}

function findNeed(dir){
    let divisions = dir.split("/");
    return divisions[divisions.length-2]+"/"+divisions[divisions.length-1];
}
