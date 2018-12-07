var $;
layui.config({
    base: "js/"
}).use(['jquery'], async () => {
    $ = layui.jquery;
    $.ajaxSetup({
        beforeSend:function(xhr,option){
            if(option.url.indexOf("api/")!=-1){
                option.url="http://localhost:8765"+option.url;
            }
            option.data=option.data || {};
            option.data.token=localStorage.getItem("token");
        }
    });
});