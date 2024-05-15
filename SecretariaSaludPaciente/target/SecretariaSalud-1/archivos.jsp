<%-- 
    Document   : expedientes
    Created on : 6 may 2024, 12:16:14
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
                    <caption><h3>Expedientes:</h3></caption>
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Tipo</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Rx Abdomen</td>
                            <td>.jpg</td>
                            <td class="celda-btn">
                                <button id="ver-btn" onclick="window.location.href = 'menuPaciente.jsp';">Ver</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>    

            <div class="botones">
                <button id="regresar-btn" onclick="window.location.href = 'menuPaciente.jsp';">Regresar</button>
                <!-- Botón de agregar con ventana emergente -->
                <button id="agregar-btn" onclick="openFileWindow()">Agregar</button>
            </div>
        </div>
        <footer>
            <img src="IMG/secretarialogo.png" alt="Logo secretaria de salud" class="logo-secretaria">
        </footer>

        <!-- Script para abrir la ventana emergente -->
        <script>
            function agregarExpediente() {
                // Aquí puedes agregar el código para enviar el archivo al servidor
                // Por ejemplo, puedes utilizar AJAX para enviar el archivo sin recargar la página
                alert("Archivo agregado al expediente");
                closeModal();
            }

            function closeModal() {
                document.getElementById('fileModal').style.display = 'none';
            }

            function openFileWindow() {
                // Abrir ventana emergente
                var fileWindow = window.open('agregarArchivo.jsp', '_blank', 'width=750,height=400');
            }
        </script>
    </body>

</html>