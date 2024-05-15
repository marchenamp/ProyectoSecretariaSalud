/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rabbit;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import controlador.ConsultasMedico;
import controlador.ConsultasPaciente;
import controlador.ConsultasTutor;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import modelo.AccionMedico;
import modelo.AccionPaciente;
import modelo.Medico;
import modelo.Paciente;
import modelo.Tutor;

/**
 *
 * @author march
 */
public class ConsumidorUsuarios {

    private static final String EXCHANGE_NAME = "usuarios";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, EXCHANGE_NAME, "");

            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            Gson gson = new Gson();

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");

                System.out.println(" [x] Received '" + message + "'");

                boolean confirmador = false;

                if (message.contains("paciente")) {
                    ConsultasPaciente sqlPaciente = new ConsultasPaciente();
                    ConsultasTutor sqlTutor = new ConsultasTutor();

                    AccionPaciente accionPaciente = gson.fromJson(message, AccionPaciente.class);

                    Paciente paciente = accionPaciente.getPaciente();

                    switch (accionPaciente.getAccion()) {
                        case "registrar_sin_tutor":
                            confirmador = sqlPaciente.registrarPaciente(paciente.getNombres(), paciente.getApellidoPaterno(), paciente.getApellidoMaterno(), paciente.getCorreo(), paciente.getPassword(), paciente.getFechaNacimiento(), paciente.getTelefono(), paciente.getEstadoCivil(), paciente.getGenero());
                            break;
                        case "registrar_con_tutor":
                            Tutor tutor = accionPaciente.getTutor();
                            if (sqlTutor.registrarTutor(tutor.getNombres(), tutor.getApellidoPaterno(), tutor.getApellidoMaterno(), tutor.getFechaNacimiento(), tutor.getTelefono(), tutor.getGenero(), tutor.getParentesco())) {
                                Tutor tutorPaciente = sqlTutor.buscarUltimoTutorRegistrado();
                                confirmador = sqlPaciente.registrarPacienteConTutor(paciente.getNombres(), paciente.getApellidoPaterno(), paciente.getApellidoMaterno(), paciente.getCorreo(), paciente.getPassword(), paciente.getFechaNacimiento(), paciente.getTelefono(), paciente.getEstadoCivil(), paciente.getGenero(), tutorPaciente);
                            } else {
                                confirmador = false;
                            }
                            break;
                        case "actualizar":

                            break;
                        case "eliminar":

                            break;
                        default:
                            System.out.println("Acción no válida");
                            break;
                    }
                } else if (message.contains("medico")) {
                    ConsultasMedico sqlMedico = new ConsultasMedico();
                    
                    AccionMedico accionMedico = gson.fromJson(message, AccionMedico.class);
                    
                    Medico medico = accionMedico.getMedico();
                    
                    switch (accionMedico.getAccion()) {
                        case "registrar":
                            confirmador = sqlMedico.registrarMedico(medico.getNombres(), medico.getApellidoPaterno(), medico.getApellidoMaterno(), medico.getCorreo(), medico.getPassword(), medico.getFechaNacimiento(), medico.getTelefono(), medico.getGenero(), medico.getCedulaProfesional(), medico.getEspecialidadMedica(), medico.getLugarTrabajoActual());
                            break;
                        case "actualizar":

                            break;
                        case "eliminar":

                            break;
                        default:
                            System.out.println("Acción no válida");
                            break;
                    }
                }
                
                // Simular la acción realizada y enviar la confirmación
                String confirmationMessage;
                if (confirmador) {
                    confirmationMessage = "Exito";
                    System.out.println(" [o] Acción exitosa");
                } else {
                    confirmationMessage = "Fallo";
                    System.out.println(" [x] Acción fallida");
                }

                AMQP.BasicProperties replyProps = new AMQP.BasicProperties.Builder()
                        .correlationId(delivery.getProperties().getCorrelationId())
                        .build();
                channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, confirmationMessage.getBytes("UTF-8"));
            };

            // Consumir mensajes de manera continua
            while (true) {
                channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
                });
            }
        }
    }

}
