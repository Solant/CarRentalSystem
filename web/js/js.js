window.onload=function() {
    document.getElementById("pas1").onchange = validatePassword;
    document.getElementById("pas2").onchange = validatePassword;
    };
    
function validatePassword() {
    var p1 = document.getElementById("pas1").value;
    var p2 = document.getElementById("pas2").value;
    if(p1!=p2)
    
    document.getElementById("pas2").setCustomValidity("Пароли должны совпадать");
else
    document.getElementById("pas2").setCustomValidity("");
    
}

