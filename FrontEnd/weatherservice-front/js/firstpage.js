let button_search = document.getElementById("button_search");
let input_search = document.getElementById('find_field');
setLocalStorageIfNull();
makePostQuery();

input_search.addEventListener("keyup", function (event){
    if (event.keyCode === 13 && input_search.value.length !== 0){
        startOfSearchingCity();
    }
})
button_search.onclick = function (){
    if (input_search.value.length !== 0){
        startOfSearchingCity()
    }
}

function setLocalStorageIfNull(){
    if (localStorage.getItem("city") == null){
        localStorage.setItem("city", "Kyiv");
    }
}

function startOfSearchingCity(){
    let last_city = localStorage.getItem("city");
    localStorage.setItem("city", input_search.value);
    document.getElementById("preloader").style.display = "block";
    makePostQuery(last_city);
}

function makePostQuery(last_city){
    axios.post("http://127.0.0.1:8090/current", localStorage.getItem("city"))
        .then((response) => {
            let info_json = response.data;
            if (info_json["info"] === undefined){
                document.getElementById("city_name").innerHTML = localStorage.getItem("city");
                document.getElementById("city_name2").innerHTML = localStorage.getItem("city");
                document.getElementById("current_temp").innerHTML = info_json["temp"]+"°";
                document.getElementById("feels_like").innerHTML = "Feels like: "+info_json["feels_like"]+"°";
                document.getElementById("description").innerHTML = info_json["description"];
                document.getElementById("pressure").innerHTML = "Pressure: "+info_json["pressure"]+" hPa";
                document.getElementById("humidity").innerHTML = "Humidity: "+info_json["humidity"]+"%";
                document.getElementById("wind_speed").innerHTML = info_json["wind"]+" m/s";
                document.getElementById("visibility").innerHTML = info_json["visibility"]+" m";
                document.getElementById("sunrise").innerHTML = info_json["sunrise"];
                document.getElementById("sunset").innerHTML = info_json["sunset"];
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

                axios.post("http://127.0.0.1:8090/hourly", localStorage.getItem("city"))
                    .then((response) => {
                        let info_json = response.data;
                        document.getElementById("first_time").innerHTML = info_json["0"]["time"]+"0";
                        document.getElementById("second_time").innerHTML = info_json["1"]["time"]+"0";
                        document.getElementById("third_time").innerHTML = info_json["2"]["time"]+"0";
                        document.getElementById("fourth_time").innerHTML = info_json["3"]["time"]+"0";
                        document.getElementById("fifth_time").innerHTML = info_json["4"]["time"]+"0";
                        document.getElementById("sixth_time").innerHTML = info_json["5"]["time"]+"0";

                        document.getElementById("first_temp").innerHTML = info_json["0"]["temp"]+"°";
                        document.getElementById("second_temp").innerHTML = info_json["1"]["temp"]+"°";
                        document.getElementById("third_temp").innerHTML = info_json["2"]["temp"]+"°";
                        document.getElementById("fourth_temp").innerHTML = info_json["3"]["temp"]+"°";
                        document.getElementById("fifth_temp").innerHTML = info_json["4"]["temp"]+"°";
                        document.getElementById("sixth_temp").innerHTML = info_json["5"]["temp"]+"°";
                    }, (error) => {
                        console.log(error.response.data);
                    });
                document.getElementById("find_field").value = "";
                setTimeout(function(){
                    document.getElementById("preloader").style.display = "none";
                }, 500);
            }
            else {
                localStorage.setItem("city", last_city);
                document.getElementById("find_field").value = "";
                document.getElementById("preloader").style.display = "none";
            }
        }, (error) => {
            console.log(error.response.data);
        });
}






