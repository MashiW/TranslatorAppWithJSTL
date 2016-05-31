$(document).ready(function () {
    /*
     * Update User function
     */
    function validateUserUpdate() {
        /*
         * Define Name validation pattern
         */
        var namePatU = /^[A-Za-z]+$/;

        /*
         * username validation
         */
        var usrnmU = document.getElementById("txtunameUpd");
        if (!usrnmU.value.match(namePatU)) {
            if (usrnmU.value == "") {  //username should not be empty
                document.getElementById("unmerrUpd").innerHTML = "Please enter user name!";
                return false;
            } else {
                document.getElementById("unmerrUpd").innerHTML = "Error: Username must contain only letters !";
                usrnmU.value = ""; //clear username field
                usrnmU.focus();  //focus in username field
                return false;
            }
        } else {  //if the input is correct, then clear the error
            document.getElementById("unmerrUpd").innerHTML = "";
        }

        /*
         * first name validation
         */

        var usrfnU = document.getElementById("txtfnameUpd");
        if (!usrfnU.value.match(namePatU)) {
            if (usrfnU.value == "") { //firstname should not be empty
                document.getElementById("fnerrUpd").innerHTML = "Please enter first name !";
                return false;
            } else {
                document.getElementById("fnerrUpd").innerHTML = "Error: Name should contain only letters ! ";
                usrfnU.value = ""; //clear firstname field
                usrfnU.focus();  //focus in firstname field
                return false;
            }
        } else {  //if the input is correct, then clear the error
            document.getElementById("fnerrUpd").innerHTML = "";
        }

        /*
         * last name validation
         */

        var usrlnU = document.getElementById("txtlstnmUpd");
        if (!usrlnU.value.match(namePatU)) {
            document.getElementById("lnerrUpd").innerHTML = "Error: Name should contain only letters !";
            usrlnU.value = ""; //clear lastname field
            usrlnU.focus();  //focus in lastname field
            return false;
        }
        else {  //if the input is correct, then clear the error
            document.getElementById("lnerrUpd").innerHTML = "";
        }


        /*
         * DOB validation
         */

        var dobU = document.getElementById("dateUpdt");
        if (dobU.value == "") {
            document.getElementById("doberrUpdt").innerHTML = "Please enter date of birth !";
            return false;
        }


        /*
         * Password validation pattern
         */
        var passwdPatU = /^(?=.{8,32}$)(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).*$/;

        /*
         * password validation
         */

        var passwordU = document.getElementById("txtpassUpd");
        if (!passwordU.value.match(passwdPatU)) {
            if (passwordU.value == "") {
                document.getElementById("pwderrUpd").innerHTML = "Please enter a password !";
                return false;
            } else {
                document.getElementById("pwderrUpd").innerHTML = "Invalid password. Use a password atleast with  8 characters mixed with upper and lower case";
                passwordU.value = "";
                passwordU.focus();
                return false;
            }
        } else {  //if the input is correct, then clear the error
            document.getElementById("pwderrUpd").innerHTML = "";
        }


        /*
         * confirm password
         */

        var confpassU = document.getElementById("txtconfpassUpd");
        if (confpassU.value !== passwordU.value) {
            if (confpassU.value == "") {
                document.getElementById("cnfpwderrUpd").innerHTML = "Please re-enter your password !";
                return false;
            } else {
                document.getElementById("cnfpwderrUpd").innerHTML = "Passwords are not matching !";
                return false;
            }
        } else {
            document.getElementById("cnfpwderrUpd").innerHTML = "";

        }


        /*
         * User group validation
         */
        var grpUp = document.getElementById("slctgrpUpd");
        var slctgrpUp = grpUp.options[grpUp.selectedIndex].value;
        if (slctgrpUp == 0) {
            document.getElementById("errgrpUpd").innerHTML = "Please select user group !";
            return false;
        }


        /*
         * Country validation
         */
        var es = document.getElementById("slctcountryUpd");
        var slctcountryU = es.options[es.selectedIndex].value;
        if (slctcountryU == 0) {
            document.getElementById("cntryerrUpd").innerHTML = "Please select your country !";
            return false;
        }

        /*
         * City validation
         */
        var cityU = document.getElementById("slctcityUpd");
        var slctcityU = cityU.options[cityU.selectedIndex].value;
        if (slctcityU == 0) {
            document.getElementById("cityerrUpd").innerHTML = "Please select your city !";
            return false;
        }

        /*
         * phone number validation
         */
        var phoneNoPat = /^\d{11}$/;
        var phnnoU = document.getElementById("txtphoneUpd");
        if (!phnnoU.value.match(phoneNoPat)) {
            if (phnnoU.value == "") {
                document.getElementById("phnerrUpd").innerHTML = "Please enter a contact number !";
                return false;
            } else {
                document.getElementById("phnerrUpd").innerHTML = "Invalid phone number. Please enter a correct number !";
                phnnoU.value = "";
                phnnoU.focus();
                return false;
            }
        } else {  //if the input is correct, then clear the error
            document.getElementById("phnerrUpd").innerHTML = "";
        }


        /*
         * Define Email validation pattern
         */

        var emailPatU = /^[a-zA-Z0-9\.\_]+\@[a-zA-Z\d\-\.]+\.[a-zA-Z]{2,}$/;
        ///^[_A-Za-z0-9-\\+]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$/;

        /*
         * user email validation
         */

        var usrEmailU = document.getElementById("txtemailUpd");
        if (!usrEmailU.value.match(emailPatU)) {
            if (usrEmailU.value == "") {
                document.getElementById("emailerrUpd").innerHTML = "Please enter your email !";
                return false;
            } else {
                document.getElementById("emailerrUpd").innerHTML = "Invalid email. Please enter a valid email !";
                usrEmailU.value = "";
                usrEmailU.focus();
                return false;
            }
        } else {  //if the input is correct, then clear the error
            document.getElementById("emailerrUpd").innerHTML = "";
        }

        return true;
    }

    /*
     * User validate function
     */

    $("#btnUpdtusr").click(function () {
        var res = validateUserUpdate();

        if (res == true) {
            $.ajax({
                type: "POST",
                url: "UpdateUser",
                data: $("#frmUsrUpdt").serialize(),
                success: function (msg) {
                    if (msg == 1) {

                        alert("Update has been done !");
                        $("#usrUpdateModal").modal('hide');
                    }
                }
            })
        } else {
            alert("error in usr update validation !")
        }
    })

    $("#btncancelUpdt").click(function () {
        $("#txtfnameUpd, #txtlstnmUpd,#txtpassUpd, #txtconfpassUpd, #dateUpdt, #slctgrpUpd, #slctcountryUpd, #slctcityUpd, #txtphoneUpd, #txtemailUpd").val('');
    })

    /*
     * User delete function
     */

    $("#btnDeltUsr").click(function () {

        var val = $("#lblUname").text();

        $.ajax({
            type: "POST",
            url: "DeleteUser",
            data: {"val": val},
            success: function (msg) {
                if (msg == 1) {
                    alert("Deleted user !");
                    $("#usrDeleteModal").modal('hide');
                }
            }
        })
    })

    $("#btnCnclDelt").click(function () {
        $("#usrDeleteModal").modal("hide");
    })


    /*
     * Loading city function
     */

    $("#slctcountryUpd").change(function () {

        var countryUp = $(this).val();

        $.ajax({
            type: "POST",
            url: "LoadCity",
            dataType: "JSON",
            data: {"country": countryUp},
            success: function (data) {

                var slctctyUpd = $("#slctcityUpd"), option = "";
                slctctyUpd.empty();

                for (var C = 0; C < data.length; C++) {
                    option = option + "<option value='" + data[C].cityName + "'>" + data[C].cityName + "</option>";
                }
                slctctyUpd.append(option);
            }
        })
    })

})