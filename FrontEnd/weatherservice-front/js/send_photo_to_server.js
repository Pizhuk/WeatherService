const input = document.querySelector('input');
const preview = document.querySelector('.preview');
setTimeout(function(){
    document.getElementById("preloader").style.display = "none";
}, 2000);

input.style.opacity = "0";
input.addEventListener('change', updateImageDisplay);
function updateImageDisplay() {
    let int = 1;
    let last_file = 1;
    const curFiles = input.files;
    if(curFiles.length === 0) {
        document.getElementById("error_text").style.display = "block";
        document.getElementById("error_inner").innerHTML = "No files currently selected for upload";
        setTimeout(function(){
            document.getElementById('error_text').style.display = 'none';
        }, 5000);
    } else if(curFiles.length > 10){
        document.getElementById("error_text").style.display = "block";
        document.getElementById("error_inner").innerHTML = "You cannot upload more than 10 images";
        setTimeout(function(){
            document.getElementById('error_text').style.display = 'none';
        }, 5000);
    } else {
        for(const file of curFiles) {
            if(validFileType(file)) {
                last_file++;
                if (int === 1){
                    document.getElementById("upload_string").remove();
                    int++;
                }
                const file_div = document.createElement("div");
                file_div.classList.add("file");
                const p_div = document.createElement("p");
                p_div.innerHTML = file.name+"("+returnFileSize(file.size)+")";
                const img_div = document.createElement("img");
                img_div.src = "images/load.png";
                img_div.classList.add("rot");
                preview.appendChild(file_div);
                file_div.appendChild(p_div);
                file_div.appendChild(img_div);

                const image = document.createElement('img');
                getEncodePhoto(file, image, img_div, file_div, last_file, curFiles.length);

            } else {
                document.getElementById("error_text").style.display = "block";
                document.getElementById("error_inner").innerHTML = "Not a valid file type. Use JPG or PNG";
                setTimeout(function(){
                    document.getElementById('error_text').style.display = 'none';
                }, 5000);
            }
        }
    }
}

const fileTypes = [
    "image/jpeg",
    "image/jpg",
    "image/png"
];

function getEncodePhoto(file, image, img_div, file_div, last_file, curFiles){
    image.src = URL.createObjectURL(file);
    image.onload = function() {
        let key = encodeURIComponent(URL.createObjectURL(file)),
            canvas = document.createElement("canvas");

        canvas.width = image.width;
        canvas.height = image.height;
        let ctx = canvas.getContext("2d");
        ctx.drawImage(image, 0, 0);
        let exp = file.name.split('.').pop();

        if (exp === "png"){
            let str = canvas.toDataURL("image/png");
            str = str.slice(22);
            let json = '{"exp":"png", "photo_code":"'+str+'"}';
            console.log(json);
            sendCodeOfPhoto(json, img_div, file_div, last_file, curFiles);
        }
        else {
            let str = canvas.toDataURL("image/jpeg");
            str = str.slice(23);
            let json = '{"exp":"jpg", "photo_code":"'+str+'"}';
            console.log(json);
            sendCodeOfPhoto(json, img_div, file_div, last_file, curFiles);
        }
        image.style.width = "15vw";
    }
}

function sendCodeOfPhoto(str, img_div, file_div, last_file, curFiles){
    axios.post("http://127.0.0.1:8090/upload", str)
        .then((response) => {
            let resp = response.data;
            const img_div2 = document.createElement("img");
            img_div2.src = "images/galochka.png";
            img_div.remove();
            file_div.appendChild(img_div2);
            if (last_file === curFiles){
                document.getElementById("error_text").style.display = "block";
                document.getElementById("error_inner").innerHTML = "All the files were uploaded. Moderator will check it soon";
                document.getElementById("error_inner").style.color = "#28890D";
                document.getElementById("error_inner").style.fontSize = "0.9vw";
                setTimeout(function(){
                    document.getElementById('error_text').style.display = 'none';
                }, 5000);
            }
        }, (error) => {
            console.log(error.response.data);
        });
}

function validFileType(file) {
    return fileTypes.includes(file.type);
}
function returnFileSize(number) {
    if(number < 1024) {
        return number + 'bytes';
    } else if(number >= 1024 && number < 1048576) {
        return (number/1024).toFixed(1) + 'KB';
    } else if(number >= 1048576) {
        return (number/1048576).toFixed(1) + 'MB';
    }
}
