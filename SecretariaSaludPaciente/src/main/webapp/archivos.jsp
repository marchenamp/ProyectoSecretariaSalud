<%-- 
    Document   : expedientes
    Created on : 6 may 2024, 12:16:14
    Author     : magda
--%>

<%@page import="io.jsonwebtoken.JwtException"%>
<%@page import="io.jsonwebtoken.Claims"%>
<%@page import="io.jsonwebtoken.Jwts"%>
<%@page import="io.jsonwebtoken.Jwts"%>
<%@page import="io.jsonwebtoken.Jwts"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controlador.ConsultasArchivos" %>
<%@page import="controlador.ConsultasExpediente"%>
<%@page import="modelo.Expediente"%>
<% ConsultasArchivos ca = new ConsultasArchivos(); %>

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
        <title>Expedientes Clínicos</title>
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
                            <th>Nombre</th>
                            <th>Tipo</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%= ca.obtenerTablaArchivos(expedienteEncontrado.getId())%>
                    </tbody>
                </table>
            </div>    

            <div class="botones">
                <button id="regresar-btn" onclick="window.location.href = 'menuPaciente.jsp';">Regresar</button>
                <button id="agregar-btn" onclick="window.location.href = 'agregarArchivo.jsp';">Agregar</button>
            </div>
        </div>

        <footer>
            <img src="IMG/secretarialogo.png" alt="Logo secretaria de salud" class="logo-secretaria">
        </footer>

        <script>
            function verArchivo(idArchivo) {
                // Enviar solicitud AJAX al servidor para obtener el contenido del archivo
                console.log("si entro al metodo verArchivo");

                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        // Cuando la solicitud sea exitosa, mostrar el contenido del archivo
                        mostrarContenido(this.responseText);
                    }
                };
                xhttp.open("GET", "ServletObtenerArchivo?idArchivo=" + idArchivo, true);
                xhttp.send();
            }

            function mostrarContenido(contenido, tipo) {
                var ventanaEmergente = window.open("", "Contenido del Archivo", "width=600,height=400");
                console.log("si entro al metodo mostrarContenido");

                if (tipo === "text/plain") {
                    // Si es texto plano, mostrarlo en un elemento de texto
                    ventanaEmergente.document.write("<pre>" + contenido + "</pre>");
                } else if (tipo.startsWith("image/")) {
                    // Si es una imagen, mostrarla utilizando la etiqueta <img>
                    ventanaEmergente.document.write("<img src='data:" + tipo + ";base64," + contenido + "' />");
                } else if (tipo === "application/pdf") {
                    // Si es un archivo PDF, mostrarlo utilizando un visor de PDF
                    ventanaEmergente.document.write("<embed src='data:" + tipo + ";base64," + contenido + "' type='" + tipo + "' width='100%' height='100%' />");
                } else {
                    // Para otros tipos de archivo, simplemente mostrar el contenido como texto
                    ventanaEmergente.document.write("<pre>" + contenido + "</pre>");
                }

                ventanaEmergente.document.close();
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