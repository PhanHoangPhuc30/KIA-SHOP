<%-- 
    Document   : pin.jsp
    Created on : Jan 22, 2024, 2:11:21 PM
    Author     : MSI GTX
--%>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link href="css/login.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10/dist/sweetalert2.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

        <title>KiA Shop - Be good, Be bad, Be yourself | Login</title>
        <style>
            .error {
                color: #fff;
                font-size: 16px;
                display: block;
                margin-top: 5px;
            }
        </style>
        <!-- Favicon  -->
        <link rel="icon" href="img/core-img/favicon2.ico">
    </head>
    <body>
        <div class="wel">
            <h1>Welcome to KiA Shop</h1>
            <p>Be good, Be bad, Be yourself</p>
        </div>
        <div id="logreg-forms">
            <form action="pin" method="post" class="form-signup" id="formforgot"style="display: block;" >
                <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Confirm Pin</h1>
                <input name="pin" type="text" id="pin" class="form-control" placeholder="Pin">
                <span class="error" id="pin-error"></span>
                <button class="btn btn-warning btn-block" name="btnpin" id="btnpin" value="Pin" type="submit"> Submit</button>
            </form>

            <br>

        </div>

        <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
            $(document).ready(function () {
                $("#btnpin").click(function (event) {
                    event.preventDefault(); // Prevent form submission
                    performAjaxRequestForgot();
                });
            });


            function performAjaxRequestForgot() {
                var pin = $("#pin").val().trim();
                var hasErrorse = false;
                $("#pin-error").text("");

                // Check if email is empty or missing '@'
                if (pin === "") {
                    $("#pin-error").text("Please enter a valid pin.").css("color", "red");
                    hasErrorse = true;
                } else {
                    $("#pin-error").text("");
                }

                // If there is an error, stop further processing
                if (hasErrorse) {
                    return;
                }

                $.ajax({
                    type: "POST",
                    url: "pin",
                    data: {btnpin: "Pin", pin: pin},
                    success: function (response) {
                        if (response === "SUCCESS") {
                            window.location.href = 'login'; // Change this to the desired URL

                        } else {
                            // Display SweetAlert for failure
                            Swal.fire({
                                title: 'Wrong Pin',
                                text: 'Email does not exist, please check your email again.',
                                icon: 'error',
                                confirmButtonText: 'OK'
                            });
                        }
                    },
                    error: function () {
                        // Display SweetAlert for error
                        Swal.fire({
                            title: 'System Error',
                            text: 'The system is failing',
                            icon: 'error',
                            confirmButtonText: 'OK'
                        });
                    }
                });
            }
        </script>

    </body>
</html>
