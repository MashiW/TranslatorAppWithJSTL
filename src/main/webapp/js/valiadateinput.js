$(document).ready(function () {
//
//User add form validation
//
    function validateUserAdd() {
        /*
         * Define Name validation pattern
         */
        var namePat = /^[A-Za-z]+$/;

        /*
         * username validation
         */
        var usrnm = document.getElementById("txtuname");
        if (!usrnm.value.match(namePat)) {
            if (usrnm.value == "") {  //username should not be empty
                document.getElementById("unmerr").innerHTML = "Please enter user name!";
                return false;
            } else {
                document.getElementById("unmerr").innerHTML = "Error: Username must contain only letters !";
                usrnm.value = ""; //clear username field
                usrnm.focus();  //focus in username field
                return false;
            }
        } else {  //if the input is correct, then clear the error
            document.getElementById("unmerr").innerHTML = "";
        }

        /*
         * first name validation
         */

        var usrfn = document.getElementById("txtfname");
        if (!usrfn.value.match(namePat)) {
            if (usrfn.value == "") { //firstname should not be empty
                document.getElementById("fnerr").innerHTML = "Please enter first name !";
                return false;
            } else {
                document.getElementById("fnerr").innerHTML = "Error: Name should contain only letters ! ";
                usrfn.value = ""; //clear firstname field
                usrfn.focus();  //focus in firstname field
                return false;
            }
        } else {  //if the input is correct, then clear the error
            document.getElementById("fnerr").innerHTML = "";
        }

        /*
         * last name validation
         */

        var usrln = document.getElementById("txtlstnm");
        if (!usrln.value.match(namePat)) {
            document.getElementById("lnerr").innerHTML = "Error: Name should contain only letters !";
            usrln.value = ""; //clear lastname field
            usrln.focus();  //focus in lastname field
            return false;
        }
        else {  //if the input is correct, then clear the error
            document.getElementById("lnerr").innerHTML = "";
        }


        /*
         * DOB validation
         */

        var dob = document.getElementById("date");
        if (dob.value == "") {
            document.getElementById("doberr").innerHTML = "Please enter date of birth !";
            return false;
        }


        /*
         * Password validation pattern
         */
        var passwdPat = /^(?=.{8,32}$)(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).*$/;

        /*
         * password validation
         */

        var password = document.getElementById("txtpass");
        if (!password.value.match(passwdPat)) {
            if (password.value == "") {
                document.getElementById("pwderr").innerHTML = "Please enter a password !";
                return false;
            } else {
                document.getElementById("pwderr").innerHTML = "Invalid password. Use a password atleast with  8 characters mixed with upper and lower case";
                password.value = "";
                password.focus();
                return false;
            }
        } else {  //if the input is correct, then clear the error
            document.getElementById("pwderr").innerHTML = "";
        }


        /*
         * confirm password
         */

        var confpass = document.getElementById("txtconfpass");
        if (confpass.value !== password.value) {
            if (confpass.value == "") {
                document.getElementById("cnfpwderr").innerHTML = "Please re-enter your password !";
                return false;
            } else {
                document.getElementById("cnfpwderr").innerHTML = "Passwords are not matching !";
                return false;
            }
        } else {
            document.getElementById("cnfpwderr").innerHTML = "";

        }


        /*
         * User group validation
         */
        var grp = document.getElementById("slctgrp");
        var slctgrp = grp.options[grp.selectedIndex].value;
        if (slctgrp == 0) {
            document.getElementById("grperr").innerHTML = "Please select user group !";
            return false;
        }


        /*
         * Country validation
         */
        var e = document.getElementById("slctcountry");
        var slctcountry = e.options[e.selectedIndex].value;
        if (slctcountry == 0) {
            document.getElementById("cntryerr").innerHTML = "Please select your country !";
            return false;
        }

        /*
         * phone number validation
         */
        var phoneNoPat = /^\d{11}$/;
        var phnno = document.getElementById("txtphone");
        if (!phnno.value.match(phoneNoPat)) {
            if (phnno.value == "") {
                document.getElementById("phnerr").innerHTML = "Please enter a contact number !";
                return false;
            } else {
                document.getElementById("phnerr").innerHTML = "Invalid phone number. Please enter a correct number !";
                phnno.value = "";
                phnno.focus();
                return false;
            }
        } else {  //if the input is correct, then clear the error
            document.getElementById("phnerr").innerHTML = "";
        }


        /*
         * Define Email validation pattern
         */

        var emailPat = /^[a-zA-Z0-9\.\_]+\@[a-zA-Z\d\-\.]+\.[a-zA-Z]{2,}$/;
        ///^[_A-Za-z0-9-\\+]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$/;

        /*
         * user email validation
         */

        var usrEmail = document.getElementById("txtemail");
        if (!usrEmail.value.match(emailPat)) {
            if (usrEmail.value == "") {
                document.getElementById("emailerr").innerHTML = "Please enter your email !";
                return false;
            } else {
                document.getElementById("emailerr").innerHTML = "Invalid email. Please enter a valid email !";
                usrEmail.value = "";
                usrEmail.focus();
                return false;
            }
        } else {  //if the input is correct, then clear the error
            document.getElementById("emailerr").innerHTML = "";
        }

        return true;
    }


    /*
     * User registration function
     */

    $("#btnAddusr").click(function () {
        var result = validateUserAdd();

        if (result == true) {
            $.ajax
            ({
                type: "POST",
                url: "RegisterUser",
                data: $("#frmUsrAdd").serialize(),
                success: function (msg) {
                    if (msg == 1) {
                        alert("Added new user ");
                        $("#frmUsrAdd").trigger('reset');
                    }
                }
            });
        } else {
            alert("Please fill the relevant fields !");
        }
    })

    $("#btncancel").click(function () {
        location.reload();
    })


    /*
     * loading group list
     */

    $.ajax({
        url: 'LoadGroup',
        dataType: 'JSON',
        type: 'POST',
        success: function (data) {

            var slctgrp = $("#slctgrp"), option = "";
            slctgrp.empty();

            for (var i = 0; i < data.length; i++) {
                option = option + "<option value='" + data[i].group + "'>" + data[i].group + "</option>";
            }
            slctgrp.append(option);
        }
    })



    /*
     *loading city function
     */

    $("#slctcountry").change(function () {

        var country = $(this).val();

        $.ajax({
            type: "POST",
            url: "LoadCity",
            dataType: "JSON",
            data: {"country": country},
            success: function (data) {

                var slctcty = $("#slctcity"), option = "";
                slctcty.empty();

                for (var c = 0; c < data.length; c++) {
                    option = option + "<option value='" + data[c].cityName + "'>" + data[c].cityName + "</option>";
                }
                slctcty.append(option);
            }
        })
    })
})
