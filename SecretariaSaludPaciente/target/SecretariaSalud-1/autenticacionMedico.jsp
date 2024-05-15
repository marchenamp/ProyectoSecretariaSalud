<%-- 
    Document   : autenticacionMEdico
    Created on : 6 may 2024, 00:18:55
    Author     : magda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Autenticación Médico</title>
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
            <!-- Formulario de inicio de sesión -->
            <% String advertencia = (String) request.getAttribute("txt-advertencia"); if (advertencia != null){ %>
            <div class="alert alert-danger alert-dismissible text-center"><%=advertencia%></div>
            <% } %>
            <div class="login-form">
                <h2>
                    AUTENTICACIÓN
                    <br> - MÉDICO -
                </h2>

                <form action="expedientePaciente.jsp" method="POST" id="autenticacionForm">
                    <input type="hidden" id="autenticado" name="autenticado" value="false" />
                    <label for="password">Contraseña:</label>

                    <div class="password-container">
                        <input type="password" id="password" name="password" placeholder="Ingrese su contraseña" required>
                        <img src="IMG/candado-cerrado.png" alt="Candado cerrado" id="candado" class="icon" onclick="mostrarPassword()">
                    </div>


                    <div class="button-container">
                        <button type="submit" class="fingerprint-button" id="fingerprintButton">
                            <img src="IMG/huelladigital.png" alt="huella" class="huella" id="fingerprint-image" />
                            <img src="IMG/huellaverificada.png" alt="huella verificada" class="huella-verificada" />
                        </button>
                    </div>

                </form>
                <br>
            </div>
        </div>
        <footer>
            <img src="IMG/secretarialogo.png" alt="Logo secretaria de salud" class="logo-secretaria">
        </footer>
    </body>
    <script>
        var isPressed = false; // Variable para controlar si el botón ha sido presionado
        var timer; // Variable para almacenar el temporizador

        // Función para iniciar el temporizador cuando se presiona el botón
        document.getElementById('fingerprintButton').addEventListener('mousedown', function () {
            isPressed = true;
            timer = setTimeout(function () {
                document.getElementById('fingerprint-image').src = 'IMG/huellaverificada.png';
                alert("Autenticación exitosa.");
                document.getElementById('autenticado').value = 'true';
                document.getElementById('loginForm').submit(); // Enviar formulario
            }, 5000); // 5000 milisegundos = 5 segundos
        });

        // Función para detener el temporizador y evitar el inicio de sesión si se suelta el botón antes de los 5 segundos
        document.getElementById('fingerprintButton').addEventListener('mouseup', function () {
            if (isPressed) {
                clearTimeout(timer); // Limpia el temporizador
                isPressed = false;
                alert("Mantenga apretado por 5 segundos para iniciar sesión");
            }
        });
        
         function mostrarPassword() {
            var cambio = document.getElementById("password");
            var icono = document.getElementById("show_password");
            if (cambio.type === "password") {
                cambio.type = "text";
                document.getElementById('candado').src = 'IMG/candado-abierto.png';
            } else {
                cambio.type = "password";
                document.getElementById('candado').src = 'IMG/candado-cerrado.png';
            }
        }
    </script>

</html>
