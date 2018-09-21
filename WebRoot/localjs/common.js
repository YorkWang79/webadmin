
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
            load_company_pic();
         }
    });
}

function init_pic_upload() {

}

function load_company_pic() {

}

function init_pic_upload() {
    var picList = $("#company_pics_list");
    var uploadListIns = upload.render({
        elem: '#company_pics'
        ,url: '#(basePath)/upload/uploadimages'
        ,multiple: true
        //,auto: false
        //,bindAction: '#company_pics_upload'
        ,choose: function(obj){
            var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
            //读取本地文件
            obj.preview(function(index, file, result){
                /*
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
                */
                $('#company_pics_demo').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
            });
        }
        ,done: function(res, index, upload){
            if(res.code == 0){ //上传成功
                /*
                var tr = picList.find('tr#upload-'+ index)
                ,tds = tr.children();
                tds.eq(1).html('<span style="color: #5FB878;">上传成功</span>');
                tds.eq(2).html(''); //清空操作
                return delete this.files[index]; //删除文件队列已经上传成功的文件
                */
                delete this.files[index];
                if(Object.keys(this.files).length == 0) {
                    load_pic_library();
                }
                return;
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

function delete_pic(id) {
    $.ajax({
        url: '#(basePath)/upload/deletepic',
        type: "POST", 
        data: {id: id},
        async: false,
        beforeSend : function(xhr) {
        },
        complete: function(xhr) {
        },
        error: function(xhr) {
            alert("delete company pic error!");
        },
        success: function(response) {
            load_pic_library();
            return false;
        }
    });
}

function load_pic_library() {
    $.ajax({
        url: '#(basePath)/upload/loadpics',
        type: "POST", 
        data: {},
        async: false,
        beforeSend : function(xhr) {
        },
        complete: function(xhr) {
        },
        error: function(xhr) {
            alert("load company pic error!");
        },
        success: function(response) {
            $("#company_pics_libaray").empty();
            $("#company_pics_libaray").html(response);
            return false;
        }
    });
}

function pic_lib_page() {
    $.ajax({
        url: '#(basePath)/upload/',
        type: "POST", 
        data: {},
        beforeSend : function(xhr) {
        },
        complete: function(xhr) {
        },
        error: function(xhr) {
            alert("page library error!");
        },
        success: function(response) {
            $("#main").empty();
            $("#main").html(response);
            init_pic_upload();
            load_pic_library();
         }
    });
}