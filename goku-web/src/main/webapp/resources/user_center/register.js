$("#register").click(function(event) {
    var param = $("#register_form").serializeJson();
    param.password = MD5(param.password);
    param.password2 = MD5(param.password2);
    $.ajax({
        url: global.contextPath + '/api/user_center/register',
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
        }else{
            location.href = "login.html";
        }
    });
});