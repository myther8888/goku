$("#login").click(function(event) {
    var param = $("#login_form").serializeJson();
    param.password = MD5(param.password);
    $.ajax({
        url: global.contextPath + '/api/user_center/login',
        type: 'POST',
        headers: {
            "content-type": "application/json"
        },
        dataType: 'json',
        data: JSON.stringify(ParamUtils.createParam(param))
    }).done(function(response) {
        console.log(response);
        if (response.errorCode != 0) {
            $('#hint-modal').modal('show');
            $("#hint-modal div.modal-body").html(response.errorMsg);
            if (response.errorCode == 1016 || response.errorCode == 1017) {
                showVerifyCode(function() {
                    $(".verify_code").show();
                });
            }
        } else {
            $.cookie("token", response.data.token);
            location.href = "index.html";
        }
    });
});

function showVerifyCode(callback) {
    var uuid = guid();
    $("#verify_code_uuid").val(uuid);
    $("#verify_code_img").attr('src', global.contextPath + '/api/user_center/verify_code/images/' + uuid);
    if (callback) {
        callback();
    }
}