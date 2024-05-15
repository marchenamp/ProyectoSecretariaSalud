<%-- 
    Document   : autenticacionMEdico
    Created on : 6 may 2024, 00:18:55
    Author     : magda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession objSesion = request.getSession(false);
    String correo = (String) objSesion.getAttribute("correo");
    String idExpediente = (String) objSesion.getAttribute("idExpedienteAut");
%>
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

                <form action="ServletExpediente" method="post" id="autenticacionForm">
                    <p><%out.println(idExpediente);%></p>
                    <input type="hidden" id="idExpediente" name="idExpediente" value="<%out.println(idExpediente);%>" >
                    <div class="button-container">
                        <button type="submit" name="VerExpediente" class="fingerprint-button" id="fingerprintButton">
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
                document.getElementById('autenticacionForm').submit(); // Enviar formulario
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

    </script>

</html>
