<%-- 
    Document   : citasPendientes
    Created on : 6 may 2024, 00:20:12
    Author     : magda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controlador.ConsultasCita" %>
<% ConsultasCita cc = new ConsultasCita(); %>
<%
    HttpSession objSesion = request.getSession(false);
    String paciente = (String) objSesion.getAttribute("nombres");
    String correo = (String) objSesion.getAttribute("correo");
    String idPaciente = (String) objSesion.getAttribute("id");

%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Citas Pendientes</title>
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
                    <a href="CerrarSesion"><i class="fa fa-sign-out" aria-hidden="true"></i> Cerrar Sesión</a>
                </div>
            </div>
            
        </header>
        <div class="body-styles">
            <div class="tabla-container">
                <table>
                    <caption><h3>Citas Pendientes:</h3></caption>
                    <thead>
                        <tr>
                            <th>Fecha</th>
                            <th>Hora</th>
                            <th>Doctor</th>
                            <th>Motivo</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%= cc.obtenerTablaPacienteCitas(Integer.parseInt(idPaciente)) %>
                    </tbody>
                </table>
            </div>    

            <div class="botones">
                <button id="regresar-btn" onclick="window.location.href = 'menuPaciente.jsp';">Regresar</button>
            </div>

        </div>
        <footer>
            <img src="IMG/secretarialogo.png" alt="Logo secretaria de salud" class="logo-secretaria">
        </footer>
    </body>

</html>