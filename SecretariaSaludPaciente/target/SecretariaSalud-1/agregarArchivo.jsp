<%-- 
    Document   : agregarArchivo
    Created on : 6 may 2024, 13:09:30
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
        <title>Subir Archivo</title>
        <link rel="stylesheet" href="CSS/estilos.css">
    </head>

    <body>
        <div class="body-styles">

            <h2>Seleccionar archivo</h2>

            <input type="file" id="archivo" onchange="mostrarPrevisualizacion()">

            <div class="botones">
                <button id="eliminar-btn" onclick="eliminarArchivo()" style="display: none;">Eliminar archivo</button>
                <button id="agregarArchivo-btn" onclick="agregarArchivo()">Agregar a expediente</button>
            </div>

            <div id="previsualizacion" style="margin-top: 20px;"></div>

        </div>

        <script>
            let archivoSeleccionado = null;

            function mostrarPrevisualizacion() {
                const archivoInput = document.getElementById('archivo');
                const archivos = archivoInput.files;

                if (archivos.length > 0) {
                    archivoSeleccionado = archivos[0];
                    const reader = new FileReader();

                    reader.onload = function (e) {
                        const previsualizacion = document.getElementById('previsualizacion');
                        previsualizacion.innerHTML = `<img src="${e.target.result}" alt="Previsualización" style="max-width: 100%; max-height: 200px;">`;
                        document.getElementById('eliminar-btn').style.display = 'inline'; // Muestra el botón de eliminar
                    };

                    reader.readAsDataURL(archivoSeleccionado);
                }
            }

            function agregarArchivo() {
                if (archivoSeleccionado) {
                    // código para enviar el archivo al servidor
                    alert("Archivo agregado al expediente");
                } else {
                    alert("Por favor, seleccione un archivo primero.");
                }
            }

            function eliminarArchivo() {
                archivoSeleccionado = null;
                const previsualizacion = document.getElementById('previsualizacion');
                previsualizacion.innerHTML = ''; // Elimina la previsualización
                document.getElementById('archivo').value = ''; // Limpia el input de archivo
                document.getElementById('eliminar-btn').style.display = 'none'; // Oculta el botón de eliminar
            }
        </script>

    </body>
</html>
