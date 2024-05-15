<%-- 
    Document   : menuPaciente
    Created on : 6 may 2024, 10:47:47
    Author     : magda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        </header>

        <h2>MENÚ</h2>
        <div class="body-styles">
            
            <div class="grid-container">
                <div class="item1">
                    <button class="expediente-btn" onclick="window.location.href = 'adminProductos.jsp';"> 
                        <img src="IMG/expediente.png" alt="Ver Expediente" id="expediente-icon"/>
                        <h3>Ver expediente</h3>
                    </button>
                </div>

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
    </body>

</html>