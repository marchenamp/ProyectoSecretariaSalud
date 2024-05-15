<%-- 
    Document   : permisosOtorgados
    Created on : 6 may 2024, 16:48:58
    Author     : magda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        </header>

        <div class="body-styles">
            <div class="tabla-container">
                <table>
                    <caption><h3>Expedientes:</h3></caption>
                    <thead>
                        <tr>
                            <th>Paciente</th>
                            <th>Expediente</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Misael Marchena</td>
                            <td class="celda-btn">
                                <button id="ver-btn" onclick="window.location.href = 'autenticacionMedico.jsp';">Ver</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>       

            <div class="botones">
                <button id="regresar-btn" onclick="window.location.href = 'menuMedico.jsp';">Regresar</button>
            </div>

        </div>
        <footer>
            <img src="IMG/secretarialogo.png" alt="Logo secretaria de salud" class="logo-secretaria">
        </footer>
    </body>

</html>