
function company_edit() {
    $.ajax({
        url: '#(basePath)/company/edit',
        type: "POST", 
        data: {},
        beforeSend : function(xhr) {
        },
        complete: function(xhr) {
        },
        error: function(xhr) {
            alert("company info error!");
        },
        success: function(response) {
            $("#main").empty();
            $("#main").html(response);
            init_company_upload();
         }
    });
}

function init_company_upload() {
    var picList = $("#company_pics_list");
    var uploadListIns = upload.render({
        elem: '#company_pics'
        ,url: '#(basePath)/upload/uploadimages'
        ,multiple: true
        ,auto: false
        ,bindAction: '#company_pics_upload'
        ,choose: function(obj){
            var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
            //读取本地文件
            obj.preview(function(index, file, result){
                var tr = $(['<tr id="upload-'+ index +'">'
                            ,'<td>'+ '<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">' +'</td>'
                            ,'<td>等待上传</td>'
                            ,'<td>'
                            ,'<button class="layui-btn layui-btn-xs upload-reload layui-hide">重传</button>'
                            ,'<button class="layui-btn layui-btn-xs layui-btn-danger upload-delete">删除</button>'
                            ,'</td>'
                            ,'</tr>'].join(''));
          
                //单个重传
                tr.find('.upload-reload').on('click', function(){
                    obj.upload(index, file);
                });
          
                //删除
                tr.find('.upload-delete').on('click', function(){
                    delete files[index]; //删除对应的文件
                    tr.remove();
                    uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                });
          
                picList.append(tr);
            });
        }
        ,done: function(res, index, upload){
            if(res.code == 0){ //上传成功
                var tr = picList.find('tr#upload-'+ index)
                ,tds = tr.children();
                tds.eq(1).html('<span style="color: #5FB878;">上传成功</span>');
                tds.eq(2).html(''); //清空操作
                return delete this.files[index]; //删除文件队列已经上传成功的文件
            }
            this.error(index, upload);
        }
        ,error: function(index, upload){
            var tr = picList.find('tr#upload-'+ index)
            ,tds = tr.children();
            tds.eq(1).html('<span style="color: #FF5722;">上传失败</span>');
            tds.eq(2).find('.demo-reload').removeClass('layui-hide'); //显示重传
        }
    });
}

function delete_company_pic(id) {
    $.ajax({
        url: '#(basePath)/company/deletepic',
        type: "POST", 
        data: {},
        beforeSend : function(xhr) {
        },
        complete: function(xhr) {
        },
        error: function(xhr) {
            alert("delete company pic error!");
        },
        success: function(response) {
            $("#uploaded-"+id).remove();
            return false;
        }
    });
}