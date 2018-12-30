const ajaxUrl = "ajax/profile/meals/";

function updateTable() {
    let ajaxUrlLocal;
    ajaxUrlLocal = ajaxUrl;
    if($("#filterForm").hasClass("filtered")) {
        ajaxUrlLocal = ajaxUrlLocal + "filter?" + $("#filterForm").serialize();
    }
    $.get(ajaxUrlLocal , function (data) {
        datatableApi.clear().rows.add(data).draw();
    });
}

function setFilter() {
    $("#filterForm").addClass("filtered");
    updateTable();
}

function resetFilter(){
    $("#filterForm").removeClass("filtered");
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
