let modal = document.getElementById("my_modal");
let modal_content = document.getElementById("modal_content");
let span = document.getElementsByClassName("close_modal_window")[0];
let tomorrow_but = document.getElementById("tomorrow_but");
let week_but = document.getElementById("week_but");

span.onclick = function () {
    hiddenModal();
    document.getElementById("rot").style.display = "block";
    document.getElementById("date").style.display = "none";
    document.getElementById("weather_content-in-modal").style.display = "none";
    document.getElementById("week-weather").style.display = "none";
}

tomorrow_but.onclick = function (){
    modal_content.style.height = "calc((100vh - 9.1vw)/1.3)";
    showOfModal();
    axios.post("http://127.0.0.1:8090/tomorrow", localStorage.getItem("city"))
        .then((response) => {
            let info_json = response.data;
            let date_split = info_json["dt"].split(" ");
            document.getElementById("city_name1").innerHTML = localStorage.getItem("city");
            document.getElementById("date").innerHTML = date_split[0]+", "+date_split[1]+" "+date_split[2]+", "+date_split[5];
            document.getElementById("temp1").innerHTML = "Morning: "+info_json["morn_temp"]+"°";
            document.getElementById("temp2").innerHTML = "Day: "+info_json["day_temp"]+"°";
            document.getElementById("temp3").innerHTML = "Evening: "+info_json["eve_temp"]+"°";
            document.getElementById("temp4").innerHTML = "Night: "+info_json["night_temp"]+"°";
            document.getElementById("description1").innerHTML = info_json["description"];
            document.getElementById("pressure1").innerHTML = "Pressure: "+info_json["pressure"]+" hPa";
            document.getElementById("humidity1").innerHTML = "Humidity: "+info_json["humidity"]+"%";
            document.getElementById("wind_speed1").innerHTML = info_json["wind"]+" m/s";
            document.getElementById("sunrise1").innerHTML = info_json["sunrise"];
            document.getElementById("sunset1").innerHTML = info_json["sunset"];
            if (localStorage.getItem("city").length > 7){
                document.getElementById("city_name").style.fontSize = "1.7vw";
                document.getElementById("city_name").style.width = "1vw";
                document.getElementById("description").style.fontSize = "1.7vw";
            }
            else {
                document.getElementById("city_name").style.fontSize = "2.5vw";
                document.getElementById("city_name").style.width = "1.5vw";
                document.getElementById("description").style.fontSize = "2.5vw";
            }
            setTimeout(function(){
                document.getElementById("rot").style.display = "none";
                document.getElementById("date").style.display = "block";
                document.getElementById("weather_content-in-modal").style.display = "flex";
            }, 1000);
        }, (error) => {
            console.log(error.response.data);
        });
}

week_but.onclick = function (){
    modal_content.style.height = "18vw"
    showOfModal();
    axios.post("http://127.0.0.1:8090/week", localStorage.getItem("city"))
        .then((response) => {
            let info_json = response.data;
            console.log(info_json)
            document.getElementById("city_name3").innerHTML = localStorage.getItem("city");
            document.getElementById("max1").innerHTML = info_json[0]["max"]+"°";
            document.getElementById("min1").innerHTML = info_json[0]["min"]+"°";
            document.getElementById("desc1").innerHTML = info_json[0]["desc"];
            document.getElementById("day1").innerHTML = info_json[0]["dt"].split(" ")[0];

            document.getElementById("max2").innerHTML = info_json[1]["max"]+"°";
            document.getElementById("min2").innerHTML = info_json[1]["min"]+"°";
            document.getElementById("desc2").innerHTML = info_json[1]["desc"];
            document.getElementById("day2").innerHTML = info_json[1]["dt"].split(" ")[0];

            document.getElementById("max3").innerHTML = info_json[2]["max"]+"°";
            document.getElementById("min3").innerHTML = info_json[2]["min"]+"°";
            document.getElementById("desc3").innerHTML = info_json[2]["desc"];
            document.getElementById("day3").innerHTML = info_json[2]["dt"].split(" ")[0];

            document.getElementById("max4").innerHTML = info_json[3]["max"]+"°";
            document.getElementById("min4").innerHTML = info_json[3]["min"]+"°";
            document.getElementById("desc4").innerHTML = info_json[3]["desc"];
            document.getElementById("day4").innerHTML = info_json[3]["dt"].split(" ")[0];

            document.getElementById("max5").innerHTML = info_json[4]["max"]+"°";
            document.getElementById("min5").innerHTML = info_json[4]["min"]+"°";
            document.getElementById("desc5").innerHTML = info_json[4]["desc"];
            document.getElementById("day5").innerHTML = info_json[4]["dt"].split(" ")[0];

            document.getElementById("max6").innerHTML = info_json[5]["max"]+"°";
            document.getElementById("min6").innerHTML = info_json[5]["min"]+"°";
            document.getElementById("desc6").innerHTML = info_json[5]["desc"];
            document.getElementById("day6").innerHTML = info_json[5]["dt"].split(" ")[0];

            document.getElementById("max7").innerHTML = info_json[6]["max"]+"°";
            document.getElementById("min7").innerHTML = info_json[6]["min"]+"°";
            document.getElementById("desc7").innerHTML = info_json[6]["desc"];
            document.getElementById("day7").innerHTML = info_json[6]["dt"].split(" ")[0];
            setTimeout(function(){
                document.getElementById("rot").style.display = "none";
                document.getElementById("week-weather").style.display = "flex";
            }, 1000);
        }, (error) => {
            console.log(error.response.data);
        });
}

window.onresize = function () {
    changeModalLoader();
}

function changeModalLoader(){
    let sizeW = modal_content.offsetWidth;
    let sizeH = modal_content.offsetHeight;
    document.getElementById("rot").style.marginTop = (sizeH-document.getElementById("rot").offsetHeight)/2.2+"px";
    document.getElementById("rot").style.marginLeft = (sizeW-document.getElementById("rot").offsetWidth)/2.2+"px";
}

function showOfModal(){
    modal.style.opacity = "0";
    modal.style.display = "block";
    let i = 0;
    setTimeout(load, 10);
    function load() {
        modal.style.opacity = i+"";
        i = i + 0.02;
        if (i <= 1){
            setTimeout(load, 10);
        }
    }
    changeModalLoader();
}

function hiddenModal(){
    let i = 0.98;
    setTimeout(load, 10);
    function load() {
        modal.style.opacity = i+"";
        i = i - 0.02;
        if (i >= 0){
            setTimeout(load, 10);
        }
        if (i <= 0){
            modal.style.display = "none";
        }
    }
}