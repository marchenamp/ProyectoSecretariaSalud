<%-- 
    Document   : citasPendientes
    Created on : 6 may 2024, 00:20:12
    Author     : magda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession objSesion=request.getSession(false);
    String paciente=(String)objSesion.getAttribute("nombres");
    if(paciente == null){
        paciente = "";
    }
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
        </header>

        <%
            if (paciente != ""){
        %>

        <h2>¡BIENVENIDO(A) <% out.println(paciente);%>!</h2>

        <%
            }
        %>

        <%
            if (paciente == ""){
        %>

        <h2>¡BIENVENIDO(A)!</h2>

        <%
            }
        %>
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
                        <tr>
                            <td>28 Feb 2024</td>
                            <td>15:00 Hrs</td>
                            <td>Alejandro Gamboa</td>
                            <td>Dolor de estómago.</td>
                        </tr>
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