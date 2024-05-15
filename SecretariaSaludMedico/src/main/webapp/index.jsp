<%-- 
    Document   : index
    Created on : 8 abr 2024, 22:21:43
    Author     : magda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Inicio de Sesión - Médico</title>
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
        <div class="body-styles">
            <!-- Formulario de inicio de sesión -->
            <%
                String advertencia = (String) request.getAttribute("txt-advertencia");
                if (advertencia != null){
            %>
            <div class="alert alert-danger alert-dismissible text-center"><%=advertencia%></div>
            <%
            }
            %>
            <div class="login-form">
                <h2>
                    INICIO DE SESIÓN
                    <br>
                    - MÉDICO -
                </h2>
                <form action="IniciarSesion" method="post">
                    <label for="cedula">Cédula Profesional:</label>
                    <input type="text" id="cedula" name="cedula" placeholder="Ingrese su cédula profesional" maxlength="8" required>

                    <div class="password-container">
                        <label for="password">Contraseña:</label>
                        <input type="password" id="password" name="password" placeholder="Ingrese su contraseña" 
                               pattern="(?=.*\d)(?=.*[a-záéíóúüñ]).*[A-ZÁÉÍÓÚÜÑ].*" 
                               title="Debe tener al menos una mayúscula, una minúscula y un dígito" minlength="8" maxlength="20" required>
                        <img src="IMG/candado-cerrado.png" alt="Candado cerrado" id="candadoLogin" class="icon" onclick="mostrarPassword()">
                    </div>

                    <button type="submit">Ingresar</button>
                </form>
                <br>
                <div style="text-align: center;">
                    <a  style="color: white;" href="registrarMedico.jsp">¿Eres médico y no estás registrado? ¡Registrate aquí!</a>
                </div>
            </div>
        </div> 
        <footer>
            <img src="IMG/secretarialogo.png" alt="Logo secretaria de salud" class="logo-secretaria">
        </footer>
    </body>
    <script>
        function mostrarPassword() {
            var cambio = document.getElementById("password");
            var icono = document.getElementById("show_password");
            if (cambio.type === "password") {
                cambio.type = "text";
                document.getElementById('candadoLogin').src = 'IMG/candado-abierto.png';
            } else {
                cambio.type = "password";
                document.getElementById('candadoLogin').src = 'IMG/candado-cerrado.png';
            }
        }
    </script>
</html>