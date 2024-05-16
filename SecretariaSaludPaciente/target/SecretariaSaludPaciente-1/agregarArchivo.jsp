<%-- 
    Document   : agregarArchivo
    Created on : 6 may 2024, 13:09:30
    Author     : magda
--%>

<%@page import="io.jsonwebtoken.JwtException"%>
<%@page import="io.jsonwebtoken.Claims"%>
<%@page import="io.jsonwebtoken.Jwts"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controlador.ConsultasExpediente"%>
<%@page import="modelo.Expediente"%>
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
    ConsultasExpediente sqlExpediente = new ConsultasExpediente();

    Expediente expedienteEncontrado = sqlExpediente.buscarExpediente(Integer.parseInt(idPaciente));
%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Subir Archivo</title>
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

            <h2>Seleccionar archivo</h2>
            <form action="ServletExpediente" method="post" enctype="multipart/form-data">
                <input type="file" id="archivo" name="archivo" onchange="mostrarBotonEliminar()" required>
                <input type="hidden" id="idExpediente" name="idExpediente" value="<%out.println(expedienteEncontrado.getId());%>" required>
                <div class="botones">
                    <button id="agregarArchivo-btn" name="AgregarArchivo">Agregar a expediente</button>
                </div>
            </form>

            <button id="eliminar-btn" style="display: none;" onclick="eliminarArchivo()">Quitar</button>

        </div>

        <footer>
            <img src="IMG/secretarialogo.png" alt="Logo secretaria de salud" class="logo-secretaria">
        </footer>        

        <script>
            function mostrarBotonEliminar() {
                document.getElementById('eliminar-btn').style.display = 'inline'; // Muestra el botón de eliminar
            }
            function eliminarArchivo() {
                document.getElementById('archivo').value = ''; // Limpia el valor del input de archivo
                document.getElementById('eliminar-btn').style.display = 'none'; // Oculta el botón de eliminar
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
    </body>
</html>
