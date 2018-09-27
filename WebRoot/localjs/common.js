
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
            form.on('submit(formCompany)', function(data){
                alert(JSON.stringify(data.field));
                $.ajax({
                    url: '#(basePath)/company/save',
                    type: "POST", 
                    data: data.field,
                    async: false,
                    beforeSend : function(xhr) {
                    },
                    complete: function(xhr) {
                    },
                    error: function(xhr) {
                        alert("save company info error!");
                    },
                    success: function(response) {
                        if(response == "false")
                            layer.alert("保存失败");
                        else
                            layer.alert("保存成功");
                        return false;
                    }
                });
                return false;
            });
         }
    });
}

function init_company_upload() {

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
                    load_pic_library(1);
                }
                return;
            }
            this.error(index, upload);
        }
        ,error: function(index, upload){
//            var tr = picList.find('tr#upload-'+ index)
//            ,tds = tr.children();
//            tds.eq(1).html('<span style="color: #FF5722;">上传失败</span>');
//            tds.eq(2).find('.demo-reload').removeClass('layui-hide'); //显示重传
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
            load_pic_library(1);
            return false;
        }
    });
}

/**
 * type:
 * 1: upload pics library page
 * 2: using pics library for single selection
 * 3: using pics library for multiple selection
 * @return
 */
function load_pic_library(type, index) {
    $.ajax({
        url: '#(basePath)/upload/loadpics',
        type: "POST", 
        data: {type: type},
        async: false,
        beforeSend : function(xhr) {
        },
        complete: function(xhr) {
        },
        error: function(xhr) {
            alert("load company pic error!");
        },
        success: function(response) {
            if(type == 1) {
                $("#company_pics_libaray").empty();
                $("#company_pics_libaray").html(response);
            }
            if(type == 2 || type == 3) {
                var layer_index = layer.open({
                    type: 1,
                    title: '图片库',
                    closeBtn: 1,
                    shadeClose: true,
                    skin: 'layui-layer-lan',
                    area: '600px',
                    content: response,
                  });
                form.render();
                form.on('submit(picCompanySave)', function(data){
                    if(type == 2)
                        save_team_pic(index);
                    if(type == 3)
                        save_company_pic();
                    layer.close(layer_index);
                    return false;
                });
            }
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
         }
    });
}

function save_company_pic() {
    //var company_name = $("#company_name").val();
    //var desc = $("#desc").val();
    var selection = "";
    $('#company_pics_demo').empty();
    $("input:checkbox[name='img_selection']:checked").each(function() {
        var pic_str = $(this).val();
        var pic_obj = JSON.parse(pic_str);
        selection += ',' + pic_obj.id;
        $('#company_pics_demo').append('<img src="#(basePath)/upload/'+ pic_obj.path +'" class="layui-upload-img">');
    });
    if(selection.length != 0)
        selection = selection.slice(1);
    $("#company_pics_ids").val(selection);
}

function save_team_pic(index) {
    var selection = "";
    $('#company_pic' + index + '_demo').empty();
    $("input:radio[name='img_selection']:checked").each(function() {
        var pic_str = $(this).val();
        var pic_obj = JSON.parse(pic_str);
        selection = pic_obj.id;
        $('#company_pic' + index + '_demo').append('<img src="#(basePath)/upload/'+ pic_obj.path +'" class="layui-upload-img">');
    });
    $("#company_pic" + index + "_id").val(selection);
}

function sync_image_selection(id, type) {
    var img_checkbox = $("#img_selection_" + id);
    if(type == 2) {
        img_checkbox.prop("checked", true);
        form ? form.render("radio") : null;
    }
    if(type == 3) {
        img_checkbox.prop("checked", !img_checkbox.prop("checked"));
        form ? form.render("checkbox") : null;
    }
}