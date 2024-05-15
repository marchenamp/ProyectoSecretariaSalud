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
import controlador.ConsultasCita;
import controlador.ConsultasExpediente;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import modelo.AccionCita;
import modelo.AccionExpediente;
import modelo.Cita;
import modelo.Expediente;

/**
 *
 * @author march
 */
public class ConsumidorCitas {

    private static final String EXCHANGE_NAME = "citas";

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

                ConsultasCita sqlCitas = new ConsultasCita();
                ConsultasExpediente sqlExpediente = new ConsultasExpediente();

                AccionCita accionCita = gson.fromJson(message, AccionCita.class);

                Cita cita = accionCita.getCita();

                switch (accionCita.getAccion()) {
                    case "obtener":

                        break;
                    case "registrar":
                        if (sqlCitas.registrarCita(cita.getMotivo(), cita.getFechaCita(), cita.getHora(), cita.getPaciente().getId(), cita.getMedico().getCedulaProfesional())) {
                            Expediente expediente = sqlExpediente.buscarExpediente(cita.getPaciente().getId());

                            Cita citaRegistrada = sqlCitas.obtenerUltimaCitaPorPaciente(cita.getPaciente().getId());

                            confirmador = sqlCitas.crearPermiso(expediente.getId(), cita.getMedico().getId(), citaRegistrada.getId());

                        } else {
                            confirmador = false;
                        }
                        break;

                    case "eliminar":
                        if (sqlCitas.eliminarCita(cita.getId())) {
                            confirmador = sqlCitas.eliminarPermiso(cita.getId());
                        }
                        break;
                    default:
                        System.out.println("Opción no válida");
                        break;
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
