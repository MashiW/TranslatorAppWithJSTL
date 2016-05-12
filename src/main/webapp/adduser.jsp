<div class="container-fluid">

    <div class="panel panel-primary" style="border-color:#bfbfbf; width: 60%; margin-left: 20%;">
        <div class="panel-heading"
             style="color:#ffffff; background-color:#ababab; border-color: #bfbfbf ; font-size: large;"><b>Add New
            User</b></div>

        <div class="panel-body">

            <form class="form-horizontal" id="frmUsrAdd" name="frmUsrAdd" method="post">
                <fieldset>

                    <legend><h5><i>Fill all the required fields *</i></h5></legend>

                    <!-- usrname input-->
                    <div class="form-group required">
                        <label class="col-md-4 control-label" for="txtuname">User Name :</label>

                        <div class="col-md-5">
                            <input id="txtuname" name="txtuname" placeholder="enter user name"
                                   class="form-control input-md" type="search"><span class="status"></span>
                            <label id="unmerr" class="input-group-error form-error"></label>
                        </div>
                    </div>

                    <!-- firstname input-->
                    <div class="form-group required">
                        <label class="col-md-4 control-label" for="txtfname">First Name :</label>

                        <div class="col-md-5">
                            <input id="txtfname" name="txtfname" placeholder="enter first name"
                                   class="form-control input-md" type="text">
                            <label id="fnerr" class="input-group-error form-error"></label>
                        </div>
                    </div>

                    <!-- lastname input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="txtlstnm">Last Name :</label>

                        <div class="col-md-5">
                            <input id="txtlstnm" name="txtlstnm" placeholder="enter last name"
                                   class="form-control input-md" type="text">
                            <label id="lnerr" class="input-group-error form-error"></label>
                        </div>
                    </div>

                    <%--DOB calendar ref--%>
                    <div>
                        <link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css"/>

                        <style>.bootstrap-iso .formden_header h2, .bootstrap-iso .formden_header p, .bootstrap-iso form {
                            font-family: Arial, Helvetica, sans-serif;
                            color: black
                        }

                        .bootstrap-iso form button, .bootstrap-iso form button:hover {
                            color: white !important;
                        }

                        .asteriskField {
                            color: red;
                        }</style>

                        <div class="bootstrap-iso form-group">
                            <label class="col-md-4 control-label requiredField" for="date">
                                Date of Birth:
       <span class="asteriskField">
        *
       </span>
                            </label>

                            <div class="col-md-5">
                                <input class="form-control" id="date" name="date" type="text" required readonly/>
                                <label id="doberrUpdt" class="input-group-error form-error"></label>
                            </div>
                        </div>
                        <label id="doberr" class="input-group-error form-error"></label>
                    </div>

                    <!-- Password input-->
                    <div class="form-group required">
                        <label class="col-md-4 control-label" for="txtpass">Password :</label>

                        <div class="col-md-5">
                            <input id="txtpass" name="txtpass" placeholder="enter password"
                                   class="form-control input-md" type="password">
                            <label id="pwderr" class="input-group-error form-error"></label>
                        </div>
                    </div>

                    <!-- Password confirm input-->
                    <div class="form-group required">
                        <label class="col-md-4 control-label" for="txtconfpass">Confirm Password :</label>

                        <div class="col-md-5">
                            <input id="txtconfpass" name="txtconfpass" placeholder="re-enter password"
                                   class="form-control input-md" type="password">
                            <label id="cnfpwderr" class="input-group-error form-error"></label>
                        </div>
                    </div>

                    <!--User group-->
                    <div class="form-group required">
                        <label class="col-md-4 control-label" for="slctgrp">Select Group :</label>

                        <div class="col-md-4">
                            <select id="slctgrp" name="slctgrp" class="form-control">
                                <option value="0">--Select--</option>
                            </select>
                            <label id="grperr" class="input-group-error form-error"></label>
                        </div>
                    </div>

                    <!--Country-->
                    <div class="form-group required">
                        <label class="col-md-4 control-label" for="slctcountry">Select Country :</label>

                        <div class="col-md-4">
                            <select id="slctcountry" name="slctcountry" class="form-control">
                                <option value="0">--Select--</option>
                                <option>Sri Lanka</option>
                                <option>Japan</option>
                                <option>India</option>
                                <option>China</option>
                                <option>England</option>
                            </select>
                            <label id="cntryerr" class="input-group-error form-error"></label>
                        </div>
                    </div>

                    <%--City--%>
                    <div class="form-group required">
                        <label class="col-md-4 control-label" for="slctcity">Select City :</label>

                        <div class="col-md-4">
                            <select id="slctcity" name="slctcity" class="form-control">
                                <option>--Select--</option>
                            </select>
                            <label id="cityerr" class="input-group-error form-error"></label>
                        </div>
                    </div>

                    <!-- phone no input-->
                    <div class="form-group required">
                        <label class="col-md-4 control-label" for="txtphone">Phone :</label>

                        <div class="col-md-4">
                            <input id="txtphone" name="txtphone" placeholder="enter phone number"
                                   class="form-control input-md" type="text">
                            <label id="phnerr" class="input-group-error form-error"></label>
                        </div>
                    </div>

                    <!-- email input-->
                    <div class="form-group required">
                        <label class="col-md-4 control-label" for="txtemail">Email :</label>

                        <div class="col-md-5">
                            <input id="txtemail" name="txtemail" placeholder="enter your email"
                                   class="form-control input-md" type="email">
                            <label id="emailerr" class="input-group-error form-error"></label>
                        </div>
                    </div>



                </fieldset>
            </form>

            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="btnAddusr"></label>

                <div class="col-md-4">
                    <button id="btnAddusr" name="btnAddusr" class="btn btn-default">Add User
                    </button>
                </div>
                <label class="col-md-4 control-label"></label>

                <div class="col-md-4">
                    <button id="btncancel" name="btncancel" class="btn btn-default">Cancel</button>
                </div>
            </div>

        </div>
    </div>
</div>
