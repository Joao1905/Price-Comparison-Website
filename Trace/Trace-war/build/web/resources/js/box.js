function darkMode () {
    if (document.getElementById("dark_mode").value == "on") {
        document.getElementById("dark_mode").style.color = "#ffffff";
        document.getElementById("dark_mode").value = "off";
        $('html').css("transition", "0.3s");
        $('html').css("background-color", "#ffffff");
    } else {
        document.getElementById("dark_mode").style.color = "#26a69a";
        document.getElementById("dark_mode").value = "on";
        document.body.style.transition = "0.3s";
        $('html').css("transition", "0.3s");
        $('html').css("background-color", "#212121");
    }
  
}

function upPage () {
    $("html, body").animate({ scrollTop: 0 }, "slow");
}

function downPage () {
    $("html, body").animate({ scrollTop: 10000 }, "slow");
}



    