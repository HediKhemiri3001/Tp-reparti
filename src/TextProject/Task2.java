package TextProject;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Task2 {
    private static Text text = new Text("Hedi Khemiri \n Mahdi Ghorbel \n Naim Dali. \n" );
    private final static String QUEUE_NAME = "mq2";
    private final static int lineToChange = 1;
    private final static String textToChange = "Mohamed Hedi Khemiri";
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            String message = lineToChange + "_" + textToChange ;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            text.setLine(lineToChange, textToChange);
            System.out.println("[x] sent change request for line  '" + lineToChange + " with content of " + textToChange+ "'"); }
    }

}
