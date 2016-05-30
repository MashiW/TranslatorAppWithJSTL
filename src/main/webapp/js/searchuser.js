/*
 * search user function
 */

$(document).ready(function () {

    var usrnm = $("#txtSrchun").val();

    $.ajax({
        type: "POST",
        url: "SearchUser",
        dataType: "json",
        data: {"usrnm": usrnm, "initPage": "1"},
        success: function (result) {

            $('#table').bootstrapTable({
                pagination: false,
                pageSize: 10,
                pageList: [10, 25, 50, 100, 200],
                search: false,
                showColumns: true,
                showRefresh: true,
                clickToSelect: true,
                singleSelect: true,
                minimumCountColumns: 3,
                columns: [{
                    field: 'usrnm',
                    title: 'User Name',
                    id: 'sl',
                    sortable: true
                }, {
                    field: 'usrfn',
                    title: 'First Name',
                    id: 'nm',
                    sortable: true
                }, {
                    field: 'usrln',
                    title: 'Last Name',
                    sortable: true
                }, {
                    field: 'usrdob',
                    title: 'DOB',
                    sortable: true
                }, {
                    field: 'usrphone',
                    title: 'Phone No:'
                }, {
                    field: 'usrcntry',
                    title: 'Country',
                    sortable: true
                }, {
                    field: 'usrcity',
                    title: 'City',
                    sortable: true
                }, {
                    field: 'usremail',
                    title: 'Email'
                }, {
                    field: 'Options',
                    title: 'Options',
                    align: 'center',
                    formatter: operateFormatter,
                    events: operateEvents
                }],
                data: result
            });
        }
    });

    /*
     * typeahead function
     */
    $("#txtSrchun").keyup(function () {


        $.ajax({
            type: "POST",
            url: "TypeaheadUsername",
            dataType: "json",
            data: {"usrnm": usrnm},
            success: function (data) {

                $('#txtSrchun').typeahead({
                    source: data
                }).focus();
            }
        })
    })


    $("#btnSrchUser").click(function () {

        $.ajax({
            type: "POST",
            url: "SearchUser",
            dataType: "json",
            data: {"usrnm": $("#txtSrchun").val()},
            success: function (data) {
                $('#table').bootstrapTable('load', data);
            }
        })
    })

    /**
     * edit and delete permission granting function
     */

    $("#userSearchLink").click(function () {

        var userName = $("#lblSessionUser").val();

        $.ajax({
            type: "POST",
            url: "UserPermission",
            dataType: "JSON",
            data: {"userName": userName},
            success: function (data) {
                /* alert(data);*/

                if (data.indexOf('Edit_user') < 0) {
                    $(".edit").hide();
                }
                if (data.indexOf('Delete_user') < 0) {
                    $(".delete").hide();
                }
                if (data.indexOf('Edit_user') < 0 && data.indexOf('Delete_user') < 0) {

                    $('#table').bootstrapTable('hideColumn', 'Options');
                }

                /*if(typeof data[i].permission != 'Delete_user'){
                 if(typeof  data[i].permission != 'Edit_user'){
                 $('#table').bootstrapTable('hideColumn', 'Options');
                 }else{
                 $(".edit").hide();
                 }
                 $(".delete").hide();
                 }*/

            }
        })
    })

    $.ajax({
        type: 'POST',
        url: 'LoadGroup',
        dataType: 'JSON',
        success: function (data) {

            var slctgrpUp = $("#slctgrpUpd"), option = "";
            slctgrpUp.empty();

            for (var i = 0; i < data.length; i++) {
                option = option + "<option value='" + data[i].groupNm + "'>" + data[i].groupNm + "</option>";

            }
            slctgrpUp.append(option);
        }
    })
})

function operateFormatter(value, row, index) {
    return [
        '<center>',
        '<a class="edit" href="javascript:void(0)" title="Edit" id="linkEditUser">',
        '<i class="glyphicon glyphicon-edit">Edit</i>',
        '</a>&nbsp;&nbsp;&nbsp;&nbsp;',
        '<a class="delete" href="javascript:void(0)" title="Delete" id="linkDeleteUser">',
        '<i class="glyphicon glyphicon-remove">Delete</i>',
        '</a></center>'
    ].join('');
}


window.operateEvents = {
    'click .edit': function (e, value, row, index) {

        var data = JSON.stringify(row);
        var objc = JSON.parse(data);
        $('#txtunameUpd').val(objc["usrnm"]);
        $('#txtfnameUpd').val(objc["usrfn"]);
        $('#txtlstnmUpd').val(objc["usrln"]);
        $('#dateUpdt').val(objc["usrdob"]);
        $('#txtphoneUpd').val(objc["usrphone"]);
        /*$('#slctgrpUpd').val(objc["usrgrp"]);*/
        $('#slctcountryUpd').val(objc["usrcntry"]);
        $("#slctcityUpd").val(objc["usrcity"]);
        $('#txtemailUpd').val(objc["usremail"]);

        $('#usrUpdateModal').modal('show');

        /*$.ajax({
         type: 'POST',
         url: 'LoadGroup',
         dataType: 'JSON',
         success: function (data) {

         var slctgrpUp = $("#slctgrpUpd"), option = "";
         slctgrpUp.empty();

         for (var i = 0; i < data.length; i++) {
         option = option + "<option value='" + data[i].groupNm + "'>" + data[i].groupNm + "</option>";

         }
         slctgrpUp.append(option);
         }
         })*/
    },

    'click .delete': function (e, value, row, index) {
        var js = JSON.stringify(row);
        var obj = JSON.parse(js);
        $('#lblUname').text(obj["usrnm"]);
        $('#usrDeleteModal').modal('show');
    }

};