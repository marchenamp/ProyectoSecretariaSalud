<%-- 
    Document   : expediente
    Created on : 6 may 2024, 16:54:23
    Author     : magda
--%>

<%@page import="controlador.ConsultasExpediente"%>
<%@page import="modelo.Expediente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession objSesion = request.getSession(false);
    String medico = (String) objSesion.getAttribute("nombres");
    String correo = (String) objSesion.getAttribute("correo");
    String idMedico = (String) objSesion.getAttribute("id");

    Expediente expedienteEncontrado = (Expediente) request.getAttribute("expedienteEncontrado");
%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Expediente</title>
        <!-- Incluir Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="CSS/estilos.css">
        <script defer src="https://use.fontawesome.com/releases/v5.0.8/js/all.js"
                integrity="sha384-SlE991lGASHoBfWbelyBPLsUlwY1GwNDJo3jSJO04KZ33K2bwfV9YBauFfnzvynJ"
        crossorigin="anonymous"></script>
    </head>

    <body>
        <header>
            <div class="logo-container">
                <img src="IMG/logoSS.png" alt="logo" class="logoSS">
            </div>
            <div class="title-container">
                <h1>Secretaría de Salud</h1>
            </div>

            <div class="dropdown">
                <button class="dropbtn" id="btnUsuario">
                    <i class="fa fa-user-circle" aria-hidden="true" id="icon"></i>
                    <% out.println(correo);%>
                </button>
                <div class="dropdown-content">
                    <a href="CerrarSesion"><i class="fa fa-sign-out" aria-hidden="true"></i> Cerrar Sesión</a>
                </div>
            </div>

        </header>

        <h2 style="margin-top: 30px; margin-bottom: -30px">EXPEDIENTE</h2>

        <div class="body-styles">
            <div class="register-form">
                <div class="botonesExp" style="margin-top: -15px; margin-bottom: 30px;">
                        <button style="background-color: #aeaeae; color: black; width: 200px;" id="regresar-btn" onclick="window.location.href = 'menuMedico.jsp';">Regresar</button>
                        <button style="background-color: #aeaeae; color: black; width: 200px;" id="agregar-btn" onclick="window.location.href = 'archivos.jsp';" >Ver archivos</button>
                        <form action="ServletExpediente" method="post">
                            <input type="hidden" id="idExpediente" name="idExpediente" value="<%= expedienteEncontrado.getId()%>">
                            <button style="background-color: #aeaeae; color: black; width: 200px;" id="agregar-btn" name="VerEditarExpediente" >Editar</button>
                        </form>
                    </div>
                <form>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <label for="antecedentesHereditarios">Antecedentes Hereditarios:</label>
                                <textarea id="antecedentesHereditarios" name="antecedentesHereditarios" rows="4" cols="50" readonly><%= expedienteEncontrado.getAntecedentesHereditarios()%></textarea>
                            </div>
                            <div class="col">
                                <label for="padecimientosPersonales">Padecimientos Personales:</label>
                                <textarea id="padecimientosPersonales" name="padecimientosPersonales" rows="4" cols="50" readonly><%= expedienteEncontrado.getPadecimientoPersonales()%></textarea>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col">
                                <label for="tipoSangre">Tipo de sangre:</label>
                                <input type="text" id="tipoSangre" name="tipoSangre" value="<%= expedienteEncontrado.getTipoSangre()%>" readonly required>
                            </div>
                            <div class="col">
                                <label for="alergias">Alergias:</label>
                                <input type="text" id="alergias" name="alergias" value="<%= expedienteEncontrado.getAlergias()%>" readonly required>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col">
                                <label for="estatura">Estatura (metros):</label>
                                <input type="text" id="estatura" name="estatura" pattern="[0-9]+(\.[0-9]{1,2})?" placeholder="0.00" value="<%= expedienteEncontrado.getEstatura()%>" readonly required>
                            </div>
                            <div class="col">
                                <label for="peso">Peso (kilos):</label>
                                <input type="text" id="peso" name="peso" pattern="[0-9]{1,3}(\.[0-9]{1,2})?" placeholder="00.0" value="<%= expedienteEncontrado.getPeso()%>" readonly required>
                            </div>
                            <div class="col">
                                <label for="frecuenciaCardiaca">Frecuencia Cardiaca (bpm):</label>
                                <input type="text" id="frecuenciaCardiaca" name="frecuenciaCardiaca" pattern="[0-9]{1,3}" placeholder="00" value="<%= expedienteEncontrado.getFrecuenciaCardiaca()%>" readonly required>
                            </div>
                        </div>
                    </div>
                </form>

            </div> 
        </div>

        <footer>
            <img src="IMG/secretarialogo.png" alt="Logo secretaria de salud" class="logo-secretaria">
        </footer>

        <script type="text/javascript">
            $(document).ready(function () {
                // navigation click actions 
                $('.scroll-link').on('click', function (event) {
                    event.preventDefault();
                    var sectionID = $(this).attr("data-id");
                    scrollToID('#' + sectionID, 750);
                });
                // scroll to top action
                $('.scroll-top').on('click', function (event) {
                    event.preventDefault();
                    $('html, body').animate({scrollTop: 0}, 'slow');
                });
                // mobile nav toggle
                $('#nav-toggle').on('click', function (event) {
                    event.preventDefault();
                    $('#main-nav').toggleClass("open");
                });
            });
            // scroll function
            function scrollToID(id, speed) {
                var offSet = 0;
                var targetOffset = $(id).offset().top - offSet;
                var mainNav = $('#main-nav');
                $('html,body').animate({scrollTop: targetOffset}, speed);
                if (mainNav.hasClass("open")) {
                    mainNav.css("height", "1px").removeClass("in").addClass("collapse");
                    mainNav.removeClass("open");
                }
            }
            if (typeof console === "undefined") {
                console = {
                    log: function () { }
                };
            }

            function soloLetras(event) {
                let tecla = event.keyCode || event.which;

                // Tecla de retroceso para borrar, siempre la permite
                if (tecla === 8) {
                    return true;
                }

                // Obtener el carácter ingresado
                let tecla_final = String.fromCharCode(tecla);

                // Patrón de entrada, en este caso solo acepta letras y espacios
                let patron = /^[a-zA-Z\s]*$/;

                if (!patron.test(tecla_final)) {
                    // Alertar al usuario si la tecla ingresada no cumple el patrón
                    alert("Ingrese únicamente letras y espacios. Evite el uso de caracteres especiales.");
                    return false;
                }

                return true;
            }
            function alerta() {
                var pass = document.getElementById("txtPassword").value;
                var repass = document.getElementById("txtRepassword").value;
                if (pass !== repass)
                {
                    alert("Las contraseñas no coinciden");
                    document.getElementById("txtPassword").value = "";
                    document.getElementById("txtRepassword").value = "";
                } else {
                    confirm("Su registro se ha realizado correctamente.");
                    document.getElementById("form-submit").submit();
                }
            }
            ;
        </script>
    </body>
</html>