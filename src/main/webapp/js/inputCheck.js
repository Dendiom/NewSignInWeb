function usernameCheck (username) {
    var brief = username.trim();
    if (brief.length < 5 || brief.length > 15) {
        alert("请输入5~15位用户名");
        return false;
    }

    return true;
}

function registerCheck(username, password, passwordAgain) {
    var result = usernameCheck(username);
    if (result === false) {
        return false;
    }

    if (password !== passwordAgain) {
        alert("两次输入的密码不同")
        return false;
    }

    if (password.length < 6) {
        alert("密码请大于6位");
        return false;
    }

    return true;
}