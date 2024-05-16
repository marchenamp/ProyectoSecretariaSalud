<%-- 
    Document   : menuPaciente
    Created on : 6 may 2024, 10:47:47
    Author     : magda
--%>

<%@page import="io.jsonwebtoken.JwtException"%>
<%@page import="io.jsonwebtoken.Claims"%>
<%@page import="io.jsonwebtoken.Jwts"%>
<%@page import="controlador.ConsultasExpediente"%>
<%@page import="modelo.Expediente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String token = (String) session.getAttribute("token");
    String paciente = null;
    String correo = null;
    String idPaciente = null;
    ConsultasExpediente sqlExpediente = new ConsultasExpediente();
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
        <title>Menú - Paciente</title>
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
                    <a href="CerrarSesion" onclick="cerrarSesion()"><i class="fa fa-sign-out" aria-hidden="true" ></i> Cerrar Sesión</a>
                </div>
            </div>

        </header>
        <h2>¡BIENVENIDO(A) <% out.println(paciente);%>!</h2>
        <br>
        <h2>MENÚ</h2>
        <div class="body-styles">

            <div class="grid-container">
                <%
                    Expediente expedienteEncontrado = sqlExpediente.buscarExpediente(Integer.parseInt(idPaciente));

                    if (expedienteEncontrado != null) {
                %>
                <div class="item1">
                    <button class="expediente-btn" onclick="window.location.href = 'expediente.jsp';"> 
                        <img src="IMG/expediente.png" alt="Ver Expediente" id="expediente-icon"/>
                        <h3>Ver expediente</h3>
                    </button>
                </div>
                <%
                } else {
                %>
                <div class="item1">
                    <button class="expediente-btn" onclick="window.location.href = 'registrarExpediente.jsp';"> 
                        <img src="IMG/expediente.png" alt="Crear Expediente" id="expediente-icon"/>
                        <h3>Crear expediente</h3>
                    </button>
                </div>
                <%
                    }
                %>

                <div class="item2">
                    <button class="registrarCita-btn" onclick="window.location.href = 'agendarCita.jsp';"> 
                        <img src="IMG/registrarCita.png" alt="Registrar Cita" id="registrarCita-icon"/>
                        <h3>Agendar cita</h3>
                    </button>
                </div>

                <div class="item3">
                    <button class="verCita-btn" onclick="window.location.href = 'citasPendientes.jsp';"> 
                        <img src="IMG/verCitas.png" alt="Ver Citas" id="verCitas-icon"/>
                        <h3>Ver citas pendientes</h3>
                    </button>
                </div>
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
    </body>

</html>