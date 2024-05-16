<%-- 
    Document   : permisosOtorgados
    Created on : 6 may 2024, 16:48:58
    Author     : magda
--%>

<%@page import="io.jsonwebtoken.JwtException"%>
<%@page import="io.jsonwebtoken.Claims"%>
<%@page import="io.jsonwebtoken.Jwts"%>
<%@page import="io.jsonwebtoken.Jwts"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controlador.ConsultasCita" %>
<% ConsultasCita cc = new ConsultasCita(); %>
<%
    String token = (String) session.getAttribute("token");
    String medico = null;
    String correo = null;
    String idMedico = null;
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
            medico = (String) claims.get("nombres");
            correo = (String) claims.get("correo");
            idMedico = String.valueOf(claims.get("id"));
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
        <title>Permisos Otorgados</title>
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
                    <a href="CerrarSesion" onclick="cerrarSesion()"><i class="fa fa-sign-out" aria-hidden="true"></i> Cerrar Sesión</a>
                </div>
            </div>

        </header>

        <div class="body-styles">
            <div class="tabla-container">
                <table>
                    <caption><h3>Expedientes:</h3></caption>
                    <thead>
                        <tr>
                            <th>Fecha cita</th>
                            <th>Hora cita</th>
                            <th>Paciente</th>
                            <th>Expediente</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%= cc.obtenerTablaPermisos(Integer.parseInt(idMedico))%>
                    </tbody>
                </table>
            </div>       

            <div class="botones">
                <button id="regresar-btn" onclick="window.location.href = 'menuMedico.jsp';">Regresar</button>
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
            // JavaScript para cerrar sesión en el frontend
            function cerrarSesion() {
                // Eliminar el token JWT del almacenamiento local
                localStorage.removeItem('token');
                // Redireccionar a la página de inicio
                window.location.href = 'index.jsp';
            }
        </script>
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
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        </script>

    </body>

</html>