<div class="container-fluid">

    <div class="panel panel-primary" style="border-color:#bfbfbf">
        <div class="panel-heading"
             style="color:#ffffff; background-color:#ababab; border-color: #bfbfbf ; font-size: large;"><b><fmt:message
                key="search.label.searchuser"></fmt:message></b></div>

        <div class="panel-body">
            <br>
            <!-- usrname input-->
            <div class="form-group required">
                <label class="col-md-3 control-label" for="txtSrchun" align="right"><fmt:message
                        key="search.label.username"></fmt:message></label>

                <div class="col-md-4">
                    <input id="txtSrchun" name="txtSrchun" placeholder="enter user name"
                           class="form-control typeahead">
                    <label id="sercherr" class="input-group-error form-error"></label>
                </div>
                <div class="btn btn-default" id="btnSrchUser" value="Search"><fmt:message
                        key="search.button.searchuser"></fmt:message></div>
            </div>

            <table id="table">

            </table>
            <div id="pagination" class="text-center">


            </div>
        </div>
    </div>
</div>


<%--user update modal--%>

<div class="modal fade" id="usrUpdateModal" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-md">
        <div class="modal-content" style="">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                <h4 class="modal-title"><fmt:message
                        key="search.modal.updateuser"></fmt:message></h4>
            </div>
            <div class="modal-body">
            </div>
            <div style="width: 80%;">
                <form class="form-horizontal" id="frmUsrUpdt" name="frmUsrUpdt" method="post">
                    <fieldset>

                        <legend><h5><i><fmt:message key="search.modal.message"></fmt:message></i></h5></legend>

                        <!-- usrname input-->
                        <div class="form-group required">
                            <label class="col-md-5 control-label" for="txtunameUpd">
                                <fmt:message key="searchform.label.username"></fmt:message> </label>

                            <div class="col-md-6">
                                <input id="txtunameUpd" name="txtunameUpd" placeholder="edit user name"
                                       class="form-control input-md" type="search" readonly><span class="status"></span>
                                <label id="unmerrUpd" class="input-group-error form-error"></label>
                            </div>
                        </div>

                        <!-- firstname input-->
                        <div class="form-group required">
                            <label class="col-md-5 control-label" for="txtfnameUpd">
                                <fmt:message key="searchform.label.firstname"></fmt:message></label>

                            <div class="col-md-6">
                                <input id="txtfnameUpd" name="txtfnameUpd" placeholder="edit first name"
                                       class="form-control input-md" type="text">
                                <label id="fnerrUpd" class="input-group-error form-error"></label>
                            </div>
                        </div>

                        <!-- lastname input-->
                        <div class="form-group">
                            <label class="col-md-5 control-label" for="txtlstnmUpd">
                                <fmt:message key="searchform.label.lastname"></fmt:message>
                            </label>

                            <div class="col-md-6">
                                <input id="txtlstnmUpd" name="txtlstnmUpd" placeholder="edit last name"
                                       class="form-control input-md" type="text">
                                <label id="lnerrUpd" class="input-group-error form-error"></label>
                            </div>
                        </div>

                        <%--DOB calendar ref--%>
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
                        <div>
                            <div class="bootstrap-iso form-group">
                                <label class="col-md-5 control-label requiredField" for="dateUpdt">
                                    <fmt:message key="searchform.label.dob"></fmt:message>
                                   <span class="asteriskField">
                                    *
                                   </span>
                                </label>

                                <div class="col-md-6">
                                    <input class="form-control" id="dateUpdt" name="dateUpdt" type="text" required
                                           readonly/>
                                    <label id="doberrUpdt" class="input-group-error form-error"></label>
                                </div>
                            </div>

                        </div>


                        <!-- Password input-->
                        <div class="form-group required">
                            <label class="col-md-5 control-label" for="txtpassUpd">
                                <fmt:message key="searchform.label.password"></fmt:message>
                            </label>

                            <div class="col-md-6">
                                <input id="txtpassUpd" name="txtpassUpd" placeholder="enter password"
                                       class="form-control input-md" type="password">
                                <label id="pwderrUpd" class="input-group-error form-error"></label>
                            </div>
                        </div>

                        <!-- Password confirm input-->
                        <div class="form-group required">
                            <label class="col-md-5 control-label" for="txtconfpassUpd">
                                <fmt:message key="searchform.label.confpasswd"></fmt:message></label>

                            <div class="col-md-6">
                                <input id="txtconfpassUpd" name="txtconfpassUpd" placeholder="re-enter password"
                                       class="form-control input-md" type="password">
                                <label id="cnfpwderrUpd" class="input-group-error form-error"></label>
                            </div>
                        </div>

                        <!--User group-->
                        <div class="form-group required">
                            <label class="col-md-5 control-label" for="slctgrpUpd"><fmt:message
                                    key="addform.label.selectgroup"></fmt:message></label>

                            <div class="col-md-5">
                                <select id="slctgrpUpd" name="slctgrpUpd" class="form-control selectpicker" multiple
                                        title="Select group(s)">
                                    <option value="0">--Select--</option>
                                </select>
                                <label id="grperrUpd" class="input-group-error form-error"></label>
                            </div>
                        </div>

                        <!--Country-->
                        <div class="form-group required">
                            <label class="col-md-5 control-label" for="slctcountryUpd">
                                <fmt:message key="searchform.label.selectcountry"></fmt:message>
                            </label>

                            <div class="col-md-5">
                                <select id="slctcountryUpd" name="slctcountryUpd" class="form-control">
                                    <option value="0">--Select--</option>
                                    <option>Sri Lanka</option>
                                    <option>Japan</option>
                                    <option>India</option>
                                    <option>China</option>
                                    <option>England</option>
                                </select>
                                <label id="cntryerrUpd" class="input-group-error form-error"></label>
                            </div>
                        </div>

                        <%--City--%>
                        <div class="form-group required">
                            <label class="col-md-5 control-label" for="slctcityUpd">
                                <fmt:message key="searchform.label.selectcity"></fmt:message>
                            </label>

                            <div class="col-md-4">
                                <select id="slctcityUpd" name="slctcityUpd" class="form-control">
                                    <option>--Select--</option>
                                </select>
                                <label id="cityerrUpd" class="input-group-error form-error"></label>
                            </div>
                        </div>

                        <!-- phone no input-->
                        <div class="form-group required">
                            <label class="col-md-5 control-label" for="txtphoneUpd">
                                <fmt:message key="searchform.label.phone"></fmt:message>
                            </label>

                            <div class="col-md-5">
                                <input id="txtphoneUpd" name="txtphoneUpd" placeholder="enter phone number"
                                       class="form-control input-md" type="text">
                                <label id="phnerrUpd" class="input-group-error form-error"></label>
                            </div>
                        </div>

                        <!-- email input-->
                        <div class="form-group required">
                            <label class="col-md-5 control-label" for="txtemailUpd">
                                <fmt:message key="searchform.label.email"></fmt:message>
                            </label>

                            <div class="col-md-6">
                                <input id="txtemailUpd" name="txtemailUpd" placeholder="enter your email"
                                       class="form-control input-md" type="email">
                                <label id="emailerrUpd" class="input-group-error form-error"></label>
                            </div>
                        </div>

                    </fieldset>

                </form>
                <!-- Button -->
                <div class="form-group">
                    <label class="col-md-4"></label>

                    <div class="col-md-4">
                        <button id="btnUpdtusr" name="btnUpdtusr" class="btn btn-default">
                            <fmt:message key="searchform.button.updateuser"></fmt:message>
                        </button>
                    </div>

                    <div class="col-md-4">
                        <button id="btncancelUpdt" name="btncancelUpdt" class="btn btn-default">
                            <fmt:message key="searchform.button.reset"></fmt:message>
                        </button>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <fmt:message key="searchform.button.cancel"></fmt:message>
                </button>
            </div>
        </div>
    </div>
</div>

<%--usrDeleteModal--%>

<div class="modal fade" id="usrDeleteModal">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                <h4 class="modal-title">
                    <fmt:message key="deletemodal.title"></fmt:message>
                </h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label><fmt:message key="deletemodal.message"></fmt:message><label id="lblUname"></label></label>
                    <br>
                    <button class="btn" type="button" value="Yes" id="btnDeltUsr">
                        <fmt:message key="deletemodal.button.yes"></fmt:message>
                    </button>
                    <button class="btn" type="button" value="cancel" id="btnCnclDelt">
                        <fmt:message key="deletemodal.button.cancel"></fmt:message>
                    </button>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-backdrop="static" data-keyboard="false">
                    <fmt:message key="deletemodal.button.close"></fmt:message>
                </button>
            </div>
        </div>
    </div>
</div>

