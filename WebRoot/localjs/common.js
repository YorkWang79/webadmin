
function company_edit()
{
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
            alert(response);
            $("#main").empty();
            $("#main").html(response);
         }
    });
}