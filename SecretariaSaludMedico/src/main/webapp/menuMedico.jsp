<%-- 
    Document   : menuMedico
    Created on : 6 may 2024, 14:35:24
    Author     : magda
--%>

<%@page import="io.jsonwebtoken.JwtException"%>
<%@page import="io.jsonwebtoken.Claims"%>
<%@page import="io.jsonwebtoken.Jwts"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Menú - Médico</title>
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
        <h2>¡BIENVENIDO(A) <% out.println(medico);%>!</h2>
        <br>
        <h2>MENÚ</h2>
        <div class="body-styles">

            <div class="grid-medico">
                <div class="item1-medico">
                    <button class="registrarCita-btn" onclick="window.location.href = 'citasAgendadas.jsp';"> 
                        <img src="IMG/registrarCita.png" alt="Citas Agendadas" id="registrarCita-icon"/>
                        <h3>Ver citas agendadas</h3>
                    </button>
                </div>

                <div class="item2-medico">
                    <button class="registrarCita-btn" onclick="window.location.href = 'permisosOtorgados.jsp';"> 
                        <img src="IMG/permisos.png" alt="Permisos Otorgados" id="registrarCita-icon"/>
                        <h3>Ver permisos otorgados</h3>
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
