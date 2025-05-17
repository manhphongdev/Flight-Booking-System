
function checkPassword() {
    var password = document.getElementById("password").value;
    var rePassword = document.getElementById("rePassword").value;
    var errorText = document.getElementById("errorPassword");

    if (!password || !rePassword) {
        errorText.innerHTML = "";
        return;
    }

    if (password !== rePassword) {
        errorText.innerHTML = "Mật khẩu không khớp!";
        errorText.style.color = "red";
    } else {
        errorText.innerHTML = "";
    }
}