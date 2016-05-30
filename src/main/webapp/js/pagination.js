
/*
* pagination function
*/

    $(document).ready(function(){

        $.ajax({
            type:'POST',
            url:'PaginationTable',
            success: function(recCount){
                pag.simplePaginator('setTotalPages',Math.ceil(recCount/10));
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
                type:'POST',
                url:'SearchUser',
                /*dataType: 'JSON',*/
                data:{"initPage":page},
                success: function(data){
                    $("#table").bootstrapTable('load',data);
                    alert(data);
                    pag.simplePaginator('changePage', pgNumber);
                }
            })
        }

        // set totalPages option
        /* pag.simplePaginator('setTotalPages', 10);

         // go to page 3
         pag.simplePaginator('changePage', 3);*/

    });
})






