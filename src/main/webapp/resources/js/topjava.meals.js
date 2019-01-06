const ajaxUrl = "ajax/profile/meals/";

function updateTable() {
    let ajaxUrlLocal;
    ajaxUrlLocal = ajaxUrl;
    let arrParameters = $("#filterForm").serializeArray();
    for(let parameter of arrParameters) {
        if(parameter.value.length) {
            ajaxUrlLocal = ajaxUrl + "filter?" + $("#filterForm").serialize();
            break;
        }
    }
    $.get(ajaxUrlLocal , function (data) {
        datatableApi.clear().rows.add(data).draw();
    });
}

function resetFilter(){
    $("#filterForm").find("input").val("");
    updateTable();
}

$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ]
    });
});
