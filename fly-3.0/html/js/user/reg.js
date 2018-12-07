layui.config({
    base: "js/"
}).use(['form', 'element', 'layer'], async () => {
    var form = layui.form,
        layer = layui.layer,
        element = layui.element;

    form.on("submit(reg)", (data) => {
        var index = layer.load();
        $.post("/user/reg", data.field,function(loginResult){
            layer.close(index);
            if (!loginResult.success) {
                layer.msg(loginResult.message);
                return;
            }
            location.href="/html/user/login.html";
        });
        return false;
    });
})