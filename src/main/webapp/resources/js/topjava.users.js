const ajaxUrl = "ajax/admin/users/";
let datatableApi;

function setEnableUser(obj, id) {
    $.post(ajaxUrl + id + "/enable/" + obj.checked).done(function () {
        obj.closest("tr").setAttribute("data-userEnable", obj.checked);
        successNoty(obj.checked ? "Enabled" : "Disabled");
    });
}

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled"
            },
            {
                "data": "registered"
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
                "asc"
            ]
        ]
    });
    makeEditable();
});