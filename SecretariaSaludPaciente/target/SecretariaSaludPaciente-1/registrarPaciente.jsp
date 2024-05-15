<%-- 
    Document   : registrarPaciente
    Created on : 8 abr 2024, 22:22:29
    Author     : magda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registro - Paciente</title>
        <script>
            function mostrarTutor() {
                var fechaNacimiento = document.getElementById("fecha").value;
                var fechaNacimientoDate = new Date(fechaNacimiento);
                var hoy = new Date();
                var edad = hoy.getFullYear() - fechaNacimientoDate.getFullYear();
                var m = hoy.getMonth() - fechaNacimientoDate.getMonth();
                if (m < 0 || (m === 0 && hoy.getDate() < fechaNacimientoDate.getDate())) {
                    edad--;
                }

                if (edad < 18) {
                    document.getElementById("tutorSection").style.display = "block";
                } else {
                    document.getElementById("tutorSection").style.display = "none";
                }
            }
        </script>
        <!-- Incluir Bootstrap CSS -->
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
                <form action="ServletPaciente" method="post">
                    <div class="heading">
                        <h2>REGISTRO DE PACIENTE</h2>
                    </div>

                    <div class="container">
                        <!-- Campos para el paciente -->
                        <div class="row">
                            <!-- Campos para el paciente -->
                            <div class="col">
                                <label for="nombre">Nombre(s):</label>
                                <input type="text" name="nombrePaciente" placeholder="Ingrese su(s) nombre(s)" onkeypress="return soloLetras(event)" title="Ingrese únicamente letras" maxlength="255" required>
                            </div>
                            <div class="col">
                                <label for="apellidoPaterno">Apellido Paterno:</label>
                                <input type="text" name="apellidoPaternoPaciente" placeholder="Ingrese su apellido paterno" onkeypress="return soloLetras(event)" maxlength="255" required>
                            </div>
                        </div>

                        <!-- Campos para el paciente -->
                        <div class="row">
                            <div class="col">
                                <label for="apellidoMaterno">Apellido Materno:</label>
                                <input type="text" name="apellidoMaternoPaciente" placeholder="Ingrese su apellido materno" onkeypress="return soloLetras(event)" maxlength="255" required>
                            </div>
                            <div class="col">
                                <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                                <input name="fechaNacimientoPaciente" type="date" id="fecha" class="form-control" id="fecha" required onchange="mostrarTutor()">
                            </div>
                        </div>

                        <!-- Campos para el paciente -->
                        <div class="row">
                            <div class="col">
                                <label for="genero">Género:</label>
                                <select id="genero" name="generoPaciente" required>
                                    <option value="seleccione">Seleccione su género</option>
                                    <option value="FEMENINO">Femenino</option>
                                    <option value="MASCULINO">Masculino</option>
                                    <option value="NO_BINARIO">No binario</option>
                                </select>
                            </div>
                            <div class="col">
                                <label for="estadoCivil">Estado Civil:</label>
                                <select id="estadoCivil" name="estadoCivilPaciente" required>
                                    <option value="seleccione">Seleccione su estado civil</option>
                                    <option value="SOLTERO">Soltero</option>
                                    <option value="CASADO">Casado</option>
                                </select>
                            </div>
                        </div>

                        <!-- Campos para el paciente -->
                        <div class="row">
                            <div class="col">
                                <label for="email">Correo Electrónico:</label>
                                <input type="email" name="emailPaciente" placeholder="Ingrese su correo electrónico" maxlength="255" required>
                            </div>
                            <div class="col">
                                <label for="telefono">Teléfono:</label>
                                <input type="tel" name="telefonoPaciente" placeholder="Ingrese su número telefónico" maxlength="10" oninput="this.value = this.value.replace(/[^0-9]/g, '').slice(0, 10);" title="Ingrese solo números (máximo 10 dígitos)" required>
                            </div>
                        </div>

                        <!-- Campos para el paciente -->
                        <div class="row">
                            <div class="col">
                                <label for="password">Contraseña:</label>
                                <input name="passwordPaciente" type="password" id="txtPassword" placeholder="Ingrese su contraseña" pattern="(?=.*\d)(?=.*[a-záéíóúüñ]).*[A-ZÁÉÍÓÚÜÑ].*" title="Debe tener al menos una mayúscula, una minúscula y un dígito" minlength="8" maxlength="20" required> 
                                <br>
                                <input name="repass" type="password" id="txtRepassword" placeholder="Ingrese nuevamente su contraseña" pattern="(?=.*\d)(?=.*[a-záéíóúüñ]).*[A-ZÁÉÍÓÚÜÑ].*" title="Debe tener al menos una mayúscula, una minúscula y un dígito" minlength="8" maxlength="20" required>
                            </div>
                        </div>

                        <!-- Sección para el tutor -->
                        <div id="tutorSection" style="display:none;">
                            <div class="heading">
                                <h2>Datos del Tutor</h2>
                            </div>

                            <div class="row">
                                <div class="col">
                                    <label for="nombreTutor">Nombre(s):</label>
                                    <input type="text" name="nombreTutor" placeholder="Ingrese lo(s) nombre(s) del tutor" onkeypress="return soloLetras(event)" title="Ingrese únicamente letras" maxlength="255" required>
                                </div>
                                <div class="col">
                                    <label for="apellidoPaternoTutor">Apellido Paterno:</label>
                                    <input type="text" name="apellidoPaternoTutor" placeholder="Ingrese el apellido paterno del tutor" onkeypress="return soloLetras(event)" maxlength="255" required>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col">
                                    <label for="apellidoMaternoTutor">Apellido Materno:</label>
                                    <input type="text" name="apellidoMaternoTutor" placeholder="Ingrese el apellido materno del tutor" onkeypress="return soloLetras(event)" maxlength="255" required>
                                </div>
                                <div class="col">
                                    <label for="fechaNacimientoTutor">Fecha de Nacimiento:</label>
                                    <input name="fechaNacimientoTutor" type="date" id="fechaTutor" class="form-control" id="fecha" required>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col">
                                    <label for="telefonoTutor">Teléfono:</label>
                                    <input type="tel" name="telefonoTutor" placeholder="Ingrese el número telefónico de su tutor" maxlength="10" oninput="this.value = this.value.replace(/[^0-9]/g, '').slice(0, 10);" title="Ingrese solo números (máximo 10 dígitos)" required>
                                </div>
                                <div class="col">
                                    <label for="generoTutor">Género:</label>
                                    <select id="generoTutor" name="generoTutor" required>
                                        <option value="seleccione">Seleccione el género de su tutor</option>
                                        <option value="FEMENINO">Femenino</option>
                                        <option value="MASCULINO">Masculino</option>
                                        <option value="NO_BINARIO">No binario</option>
                                    </select>
                                </div>
                            </div>

                            <div class="row">
                                <label for="parentesco">Parentesco:</label>
                                <select id="parentesco" name="parentescoTutor" required>
                                    <option value="seleccione">Seleccione el parentesco con el tutor</option>
                                    <option value="Madre">Madre</option>
                                    <option value="Padre">Padre</option>
                                    <option value="Hermano">Hermano(a)</option>
                                    <option value="Abuelo">Abuelo(a)</option>
                                    <option value="Otro">Otro</option>
                                </select>
                            </div>
                        </div>

                        <div class="row">
                            <button type="submit" id="form-submit" name="RegistrarPaciente" onclick="alerta()" class="btn">Registrar</button>
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
