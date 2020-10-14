var button1 = document.querySelector(".button1");
var imagesArray = ["url('im1.jpg')", "url('im6.png')", "url('im3.png')", "url('im4.png')", "url('im5.png')"];
var i = 0;
button1.addEventListener("click", function () {
    if (i == 5)
        i = 0;
    document.body.style.backgroundImage = imagesArray[i];
    document.body.style.backgroundSize = "cover";
    i = i + 1;
}, false);


var button2 = document.querySelector(".button2")
var x = document.getElementsByClassName("link");
for (j = 0; j < 10; j++) {
    x[j].style.borderRadius = "0%";
    x[j].style.backgroundColor = "black";
}
button2.addEventListener("click", function () {
    for (j = 0; j < 10; j++) {
        x[j].style.borderRadius = "100%";
        x[j].style.color = "red";
        x[j].style.backgroundColor = "yellow";
    }
}, false);