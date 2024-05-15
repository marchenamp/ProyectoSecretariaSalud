<%-- 
    Document   : agregarArchivo
    Created on : 6 may 2024, 13:09:30
    Author     : magda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controlador.ConsultasExpediente"%>
<%@page import="modelo.Expediente"%>
<%
    HttpSession objSesion = request.getSession(false);
    String paciente = (String) objSesion.getAttribute("nombres");
    String correo = (String) objSesion.getAttribute("correo");
    String idPaciente = (String) objSesion.getAttribute("id");
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
                    <a href="CerrarSesion"><i class="fa fa-sign-out" aria-hidden="true"></i> Cerrar Sesión</a>
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

    </body>
</html>
