var ParamUtils = (function () {
    function signature(param) {
        var keyValues = [];
        for (var key in param) {
            if (key != "param" && key != "signature") {
                keyValues.push(key + param[key]);
            }
            if (key == "param") {
                var obj = param[key];
                for (var paramKey in obj) {
                    keyValues.push(paramKey + obj[paramKey]);
                }
            }
        }
        keyValues.sort();
        return MD5(keyValues.join(""));
    }

    return {
        "createParam": function (param, token, version) {
            if (!token) {
                token = "";
            }
            if (!version) {
                version = "1.0";
            }
            var temp = {
                timestamp: new Date().getTime(),
                token: token,
                param: param,
                version: version,
                os: "web"
            };
            temp.signature = signature(temp);
            return temp;
        }
    }
})();