let posKD = document.getElementById("kd");
let posTC = document.getElementById("tc");
let posTT = document.getElementById("tt");
let posKH = document.getElementById("kh");
let posContent = $("#content");

posKD.addEventListener("click", function(){
    posContent.load("http://localhost:63342/Tao_Trang_Doc_Truyen_Ngan/kinh-di.html");
}, false);

posTC.addEventListener("click", function(){
    posContent.load("http://localhost:63342/Tao_Trang_Doc_Truyen_Ngan/tinh-cam.html");
}, false);

posTT.addEventListener("click", function(){
    posContent.load("http://localhost:63342/Tao_Trang_Doc_Truyen_Ngan/trinh-tham.html");
}, false);

posKH.addEventListener("click", function(){
    posContent.load("http://localhost:63342/Tao_Trang_Doc_Truyen_Ngan/khoa-hoc.html");
}, false);

