<%-- 
    Document   : agendarCita
    Created on : 6 may 2024, 15:45:10
    Author     : magda
--%>

<%@page import="io.jsonwebtoken.JwtException"%>
<%@page import="io.jsonwebtoken.Claims"%>
<%@page import="io.jsonwebtoken.Jwts"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String token = (String) session.getAttribute("token");
    String paciente = null;
    String correo = null;
    String idPaciente = null;
    System.out.println("Initializes token");
    System.out.println(token);
    System.out.println("Finishes token");

    boolean isTokenValid = true;

    if (token != null) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("2bb80a5ff92fefff0d3fc3e46fd8f5e2a63d78377757c713f4a3e6e11e78c9a5a553891de4f598a6f0f6a2854e23bcb8b286e069e7c5d7d60c03441c0e285383")
                    .parseClaimsJws(token)
                    .getBody();
            paciente = (String) claims.get("nombres");
            correo = (String) claims.get("correo");
            idPaciente = String.valueOf(claims.get("id"));
        } catch (JwtException e) {
            isTokenValid = false;
        }
    } else {
        isTokenValid = false;
    }

    if (!isTokenValid) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Agendar Cita</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="CSS/estilos.css">
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
                    <% out.println(correo); %>
                </button>
                <div class="dropdown-content">
                    <a href="CerrarSesion" onclick="cerrarSesion()"><i class="fa fa-sign-out" aria-hidden="true"></i> Cerrar Sesión</a>
                </div>
            </div>

        </header>

        <div class="body-styles">
            <div class="register-form">
                <form action="ServletCita" method="post">
                    <div class="heading">
                        <h2>AGENDAR CITA</h2>
                    </div>

                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <label for="fechaNacimiento">Fecha:</label>
                                <input name="fecha" type="date" id="fecha" class="form-control" required">
                            </div>
                            <div class="col">
                                <label for="cedulaProfesional">Cédula Profesional:</label>
                                <input name="cedulaProfesional" placeholder="Ingrese su cédula profesional" type="text" id="cedula" class="form-control" maxlength="8" required">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col">
                                <label for="hora" id="hora">Hora:</label>
                                <select required name="hora" onchange='this.form.()'>
                                    <option value="" disabled selected hidden>Selecciona la hora</option>
                                    <option value="08:00">08:00</option>
                                    <option value="08:30">08:30</option>
                                    <option value="09:00">09:00</option>
                                    <option value="09:30">09:30</option>
                                    <option value="10:00">10:00</option>
                                    <option value="10:30">10:30</option>
                                    <option value="11:00">11:00</option>
                                    <option value="11:30">11:30</option>
                                    <option value="12:00">12:00</option>
                                    <option value="12:30">12:30</option>
                                    <option value="13:00">13:00</option>
                                    <option value="13:30">13:30</option>
                                    <option value="14:00">14:00</option>
                                    <option value="14:30">14:30</option>
                                    <option value="15:00">15:00</option>
                                    <option value="15:30">15:30</option>
                                    <option value="16:00">16:00</option>
                                    <option value="16:30">16:30</option>
                                    <option value="17:00">17:00</option>
                                    <option value="17:30">17:30</option>
                                    <option value="18:00">18:00</option>
                                    <option value="18:30">18:30</option>
                                    <option value="19:00">19:00</option>
                                    <option value="19:30">19:30</option>
                                    <option value="20:00">20:00</option>
                                </select>
                                <script>
                                    function bloquearFechasAnteriores() {
                                        var fechaActual = new Date().toISOString().split('T')[0];
                                        document.getElementById('fecha').min = fechaActual;
                                    }

                                    document.body.onload = bloquearFechasAnteriores;
                                </script>
                            </div>
                            <div class="col">

                            </div>
                        </div>

                        <div class="row">
                            <div class="col">
                                <label for="motivos">Motivos:</label>
                                <textarea id="motivos" placeholder="Ingrese los motivos de la cita..." name="motivos" rows="4" cols="50"></textarea>
                            </div>
                        </div>

                    </div>
                    <input type="hidden" id="idPaciente" name="idPaciente" value="<% out.println(idPaciente);%>" >
                    <div class="botones">
                        <button type="button" name="RegistrarCita" style="background-color: #D9D9D9; color: black" id="agregar-btn" onclick="abrirModal()">Continuar</button>
                    </div>
                    <input type="hidden" name="RegistrarCita" value="true">
                </form>
                <div class="botones">
                    <button   style="background-color: #D9D9D9; color: black" id="regresar-btn" onclick="window.location.href = 'menuPaciente.jsp';">Regresar</button>
                </div>
            </div>
        </div>

        <div id="myModal" class="modal">
            <div class="modal-content">
                <p>Ingrese su huella dactilar</p>
                <br>
                <button type="submit" class="fingerprint-button" id="modalButton">
                    <img src="IMG/huelladigital.png" class="huella" id="fingerprint-image">
                    <img src="IMG/huellaverificada.png" class="huella-verificada" />
                </button>
            </div>
        </div>

        <footer>
            <img src="IMG/secretarialogo.png" alt="Logo secretaria de salud" class="logo-secretaria">
        </footer>

        <script>
            // Función para abrir el modal
            function abrirModal() {
                // Mostrar el modal
                var modal = document.getElementById("myModal");
                modal.style.display = "block";

                // Variables para controlar el tiempo
                var pressStartTime;
                var pressEndTime;

                // Cuando el usuario presiona el botón dentro del modal
                var modalBtn = document.getElementById("modalButton");
                modalBtn.onmousedown = function () {
                    // Registrar el tiempo de inicio de la presión del botón
                    pressStartTime = new Date().getTime();
                }

                // Cuando el usuario suelta el botón dentro del modal
                modalBtn.onmouseup = function () {
                    // Registrar el tiempo de finalización de la presión del botón
                    pressEndTime = new Date().getTime();

                    // Calcular la duración de la presión del botón en milisegundos
                    var pressDuration = pressEndTime - pressStartTime;

                    // Si la duración de la presión del botón es mayor o igual a 5 segundos, enviar el formulario
                    if (pressDuration >= 5000) {
                        // Enviar el formulario al servlet
                        document.querySelector('form').submit();
                        // Cerrar el modal
                        modal.style.display = "none";
                    } else {
                        // Mostrar un mensaje de que debe mantener presionado el botón por 5 segundos
                        alert("Por favor, mantenga el dedo en el escáner durante al menos 5 segundos para continuar.");
                    }
                }
            }

            // Cuando el usuario haga clic fuera del modal, cerrarlo
            window.onclick = function (event) {
                var modal = document.getElementById("myModal");
                if (event.target === modal) {
                    modal.style.display = "none";
                }
            }
        </script>

        <script>
            // JavaScript para cerrar sesión en el frontend
            function cerrarSesion() {
                // Eliminar el token JWT del almacenamiento local
                localStorage.removeItem('token');
                // Redireccionar a la página de inicio
                window.location.href = 'index.jsp';
            }
        </script>

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

            function validarCampos() {
                // Obtener los valores de los campos
                var fecha = document.getElementById('fecha').value;
                var cedula = document.getElementById('cedula').value;
                var hora = document.getElementById('hora').value;
                var motivos = document.getElementById('motivos').value;

                // Verificar si los campos no están vacíos
                if (fecha !== '' && cedula !== '' && hora !== '' && motivos !== '') {
                    // Todos los campos están completos, continuar
                    window.location.href = 'autenticacionPaciente.jsp';
                } else {
                    // Algunos campos están vacíos, mostrar mensaje de error
                    alert('Por favor, completa todos los campos antes de continuar.');
                }
            }

            document.getElementById('agregar-btn').addEventListener('click', function (event) {
                event.preventDefault();
                validarCampos();
            });

        </script>
    </body>
</html>