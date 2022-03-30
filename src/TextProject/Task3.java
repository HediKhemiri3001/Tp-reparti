package TextProject;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

//This task is responsible for reading the text from the queue.
public class Task3 {
    private final static String QUEUE_NAME1 = "mq1";
    private final static String QUEUE_NAME2 = "mq2";
    public static void main(String[] argv)throws Exception {
        Text text = new Text("Hedi Khemiri \nMahdi Ghorbel \nNaim Dali. \n");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel1 = connection.createChannel();
        channel1.queueDeclare(QUEUE_NAME1, true, false, false, null);
        Channel channel2 = connection.createChannel();
        channel2.queueDeclare(QUEUE_NAME2, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C.");

        DeliverCallback deliverCallbackQ1 = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            String[] info = message.split("_");
            int lineToChange = Integer.parseInt(info[0]);
            String textToChange = info[1];
            text.setLine(lineToChange,textToChange);
            System.out.println(" [x] Received change from Task 1 :'" + message + "'");
            System.out.println("Displaying entire text: \n" + text.getText());
        };
        DeliverCallback deliverCallbackQ2 = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            String[] info = message.split("_");
            int lineToChange = Integer.parseInt(info[0]);
            String textToChange = info[1];
            text.setLine(lineToChange,textToChange);
            System.out.println(" [x] Received change from Task 2 : '" + message + "'");
            System.out.println("Displaying entire text: \n" + text.getText());
        };
        channel1.basicConsume(QUEUE_NAME1, true, deliverCallbackQ1, consumerTag -> {

        });
        channel1.basicConsume(QUEUE_NAME2, true, deliverCallbackQ2, consumerTag -> {

        });
    }
}
