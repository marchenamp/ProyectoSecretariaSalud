# Usamos una imagen base de GlassFish
FROM glassfish:7.0.6-jdk11

# Copiamos el archivo WAR al directorio de despliegue de GlassFish
COPY target/SecretariaSaludPaciente-1.war $GLASSFISH_HOME/glassfish/domains/domain1/autodeploy/

# Exponemos el puerto en el que corre GlassFish
EXPOSE 8080

# Comando para correr GlassFish
CMD ["asadmin", "start-domain", "-v"]