<%-- 
    Document   : agendarCita
    Created on : 6 may 2024, 15:45:10
    Author     : magda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        </header>
        
        <div class="body-styles">
            <div class="register-form">
                <form action="ServletMedico" method="post">
                    <div class="heading">
                        <h2>AGENDAR CITA</h2>
                    </div>

                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <label for="fechaNacimiento">Fecha:</label>
                                <input name="fechaNacimientoMedico" type="date" id="fecha" class="form-control" required">
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

                    <div class="botones">
                        <button   style="background-color: #D9D9D9; color: black" id="regresar-btn" onclick="window.location.href = 'menuPaciente.jsp';">Regresar</button>
                        <!-- Botón de agregar con ventana emergente -->
                        <button   style="background-color: #D9D9D9; color: black" id="agregar-btn">Continuar</button>
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