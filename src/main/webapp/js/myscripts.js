//
//*Swapping translating text*/
//
        function swapText() {

        var x = document.getElementById("fromText").value;
        var y = document.getElementById("toText").value;

         document.getElementById("toText").value =x;
         document.getElementById("fromText").value =y;
        }

//
/*Login modal display in page load */
//
            $(function() {
            $("#loginModal").modal('show');
        });


$(document).ready(function () {

    /* $("#btnlogin").click(function () {

        var usernm = $("#txtlogin").val();
        $.ajax({
            type: "POST",
            url: "UserPermission",
     data: {"usernm": usernm},
     success:function(data){
     alert(data)
     }
        })
     });*/

    $("#usermgtTab").click(function () {

        var sessionuser = $("#lblSessionUser").val();

        $.ajax({
            type: "POST",
            url: "UserPermission",
            data: {"userName": sessionuser},
            success: function (data) {
                alert(data);
            }
        })
    });
})