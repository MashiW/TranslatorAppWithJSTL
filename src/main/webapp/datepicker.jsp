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
        <label id="doberr" class="input-group-error form-error"></label>
    </div>
</div>

<!-- Include jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

<!-- Include Date Range Picker -->
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>

<script>
    $(document).ready(function () {
        var date_input = $('input[name="date"]'); //our date input has the name "date"
        var container = $('.bootstrap-iso form').length > 0 ? $('.bootstrap-iso form').parent() : "body";
        date_input.datepicker({
            format: 'yyyy-mm-dd',
            container: container,
            todayHighlight: true,
            autoclose: true,
            startView: 2,
            defaultViewDate: {year: 1990},
            endDate: '-1d'
        })
    })
</script>

