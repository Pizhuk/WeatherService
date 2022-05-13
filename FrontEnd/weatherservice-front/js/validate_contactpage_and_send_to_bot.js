let buttonSend = document.getElementById("buttSend");
let name = document.getElementById("name");
let email = document.getElementById("email");
let subj = document.getElementById("subject");
let mes = document.getElementById("message");

setTimeout(function(){
    document.getElementById("preloader").style.display = "none";
}, 2000);

buttonSend.onclick = function (){
    let name_text = name.value;
    let email_text = email.value;
    let subj_text = subj.value;
    let mes_text = mes.value;
    if (!checkName(name_text)){
        name.style.borderBottom = "1px solid rgba(255, 0, 0, 0.5)"
    }
    if (!checkEmail(email_text)){
        email.style.borderBottom = "1px solid rgba(255, 0, 0, 0.5)"
    }
    if (!checkMes(mes_text)){
        mes.style.borderBottom = "1px solid rgba(255, 0, 0, 0.5)"
    }

    if (checkName(name_text)){
        name.style.borderBottom = "1px solid rgba(255, 255, 255, 0.5)"
    }
    if (checkEmail(email_text)){
        email.style.borderBottom = "1px solid rgba(255, 255, 255, 0.5)"
    }
    if (checkMes(mes_text)){
        mes.style.borderBottom = "1px solid rgba(255, 255, 255, 0.5)"
    }

    if (checkName(name_text) && checkEmail(email_text) && checkMes(mes_text)){
        name.style.borderBottom = "1px solid rgba(255, 255, 255, 0.5)";
        email.style.borderBottom = "1px solid rgba(255, 255, 255, 0.5)";
        mes.style.borderBottom = "1px solid rgba(255, 255, 255, 0.5)";

        let mes_post = '{"name":"'+name_text+'", "email":"'+email_text+'", "subj":"'+subj_text+'", ' +
            '"mes_text":"'+mes_text+'"}';
        axios.post("http://127.0.0.1:8090/contactUs", mes_post)
            .then((response) => {
                let info_json = response.data;
            }, (error) => {
                console.log(error.response.data);
            });
    }
}

function checkName(name){
    let valid = /^[a-zA-Z]+$/;
    return valid.test(String(name).toLowerCase());
}

function checkEmail(email){
    let valid = /^([a-z0-9_\.-]+)@([a-z0-9_\.-]+)\.([a-z\.]{2,6})$/;
    return valid.test(String(email).toLowerCase());
}

function checkMes(mes){
    let valid = /^[a-zA-Z0-9,.?!@#$%^&*()_+={}\\|"' :;\-></~`\[\]]+$/;
    return valid.test(String(mes).toLowerCase());
}