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
import controlador.ConsultasExpediente;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import modelo.AccionExpediente;
import modelo.Expediente;

/**
 *
 * @author march
 */
public class ConsumidorExpedientes {

    private static final String EXCHANGE_NAME = "expedientes";

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

                ConsultasExpediente sqlExpediente = new ConsultasExpediente();

                AccionExpediente accionExpediente = gson.fromJson(message, AccionExpediente.class);

                Expediente expediente = accionExpediente.getExpediente();

                switch (accionExpediente.getAccion()) {
                    case "obtener":

                        break;
                    case "registrar":
                        confirmador = sqlExpediente.registrarExpediente(expediente.getTipoSangre(), expediente.getEstatura(), expediente.getPeso(), expediente.getAlergias(), expediente.getFrecuenciaCardiaca(), expediente.getPadecimientoPersonales(), expediente.getAntecedentesHereditarios(), expediente.getNombreContactoEmergencia(), expediente.getTelefonoContactoEmergencia(), accionExpediente.getIdPaciente());
                        break;
                    case "editar":
                        confirmador = sqlExpediente.editarExpediente(expediente.getId(), expediente.getTipoSangre(), expediente.getEstatura(), expediente.getPeso(), expediente.getAlergias(), expediente.getFrecuenciaCardiaca(), expediente.getPadecimientoPersonales(), expediente.getAntecedentesHereditarios());
                        break;
                    case "eliminar":

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
