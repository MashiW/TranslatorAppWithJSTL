/*
 * search user function
 */

$(document).ready(function () {

    var usrnm = $("#txtSrchun").val();

    $.ajax({
        type: "POST",
        url: "SearchUser",
        dataType: "json",
        data: {"usrnm": usrnm},
        success: function (result) {

            $('#table').bootstrapTable({
                pagination: true,
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
})

function operateFormatter(value, row, index) {
    return [
        '<center>',
        '<a class="edit" href="javascript:void(0)" title="Edit">',
        '<i class="glyphicon glyphicon-edit">Edit</i>',
        '</a>&nbsp;&nbsp;&nbsp;&nbsp;',
        '<a class="delete" href="javascript:void(0)" title="Delete">',
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
        $('#slctgrpUpd').val(objc["usrgrp"]);
        $('#slctcountryUpd').val(objc["usrcntry"]);
        $("#slctcityUpd").val(objc["usrcity"]);
        $('#txtemailUpd').val(objc["usremail"]);
        $('#usrUpdateModal').modal('show');
    },
    'click .delete': function (e, value, row, index) {
        var js = JSON.stringify(row);
        var obj = JSON.parse(js);
        $('#lblUname').text(obj["usrnm"]);
        $('#usrDeleteModal').modal('show');
    }

};

/*
 * sorting column
 */
//  sortTable(f,n)
//  f : 1 ascending order, -1 descending order
//  n : n-th child(<td>) of <tr>
/*    function sortTable(f,n){

 var rows = $("#table tbody tr").get();

 rows.sort(function(a,b){

 var A=getVal(a);
 var B=getVal(b);

 if(A<B){
 return -1*f;
 }
 if(A>B){
 return 1*f;
 }
 return 0;
 })

 function getVal(elm){
 var v =$(elm).children('td').eq(n).text().toUpperCase();
 if($.isNumeric(v)){
 v=parseInt(v,10);
 }
 return v;
 }
 $.each(rows, function (index,row) {
 $("#table").children('tbody').append(row);
 })
 }

 var f_sl=1;
 var f_nm=1;
 $("#s1").click(function(){
 f_sl *=1;
 var n = $(this).prevAll().length;
 sortTable(f_sl,n);
 })
 $("#nm").click(function(){
 f_nm *= -1; // toggle the sorting order
 var n = $(this).prevAll().length;
 sortTable(f_nm,n);
 });*/