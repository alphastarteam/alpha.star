layui.config({
    base: "js/"
}).use(['form', 'element', 'layer'], async () => {
    var form = layui.form,
        layer = layui.layer,
        element = layui.element;

    form.on("submit(login)", (data) => {
        var index = layer.load();
        $.post("/authapi/user/login", data.field,function(loginResult){
            layer.close(index);
            if (!loginResult.success) {
                layer.msg(loginResult.message);
                return;
            }
            localStorage.setItem("token",loginResult.content);
            location.href="/html";
        });
        return false;
    });
})