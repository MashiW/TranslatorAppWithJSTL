/*
 * default pagination function
 */

$(document).ready(function () {

    /**
     * ajax function for getting records count
     */
    $("#pagination2").hide();

    $.ajax({
        type: 'POST',
        url: 'PaginationTable',
        success: function (recCount) {
            pag.simplePaginator('setTotalPages', Math.ceil(recCount / 10));
        }
    })

    var pag = $('#pagination').simplePaginator({
        // options here

        // the number of total pages
        totalPages: 7,

        // maximum of visible buttons
        maxButtonsVisible: 5,

        /*setTotalPages:10,*/

        // page selected
        currentPage: 1,

        // text labels for buttons
        nextLabel: 'next',
        prevLabel: 'prev',
        firstLabel: 'first',
        lastLabel: 'last',

        // specify if the paginator click in the currentButton
        clickCurrentPage: true,

        // called when a page is changed.
        pageChange: function (page) {

            $.ajax({
                type: 'POST',
                url: 'SearchUser',
                dataType: "json",
                data: {"initPage": page},
                success: function (data) {
                    $('#table').bootstrapTable('load', data);


                }
            })
        }
    });
})






