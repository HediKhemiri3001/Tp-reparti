package TextProject;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Task1 {
    private final static String QUEUE_NAME = "mq1";
    private final static int lineToChange = 2;
    private final static String textToChange = "Mohamed Mahdi Ghorbel";
    public static void main(String[] args) throws Exception {
        Text text = new Text("Hedi Khemiri \n Mahdi Ghorbel \n Naim Dali. \n" );
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            String message = lineToChange + "_" + textToChange ;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            text.setLine(lineToChange,textToChange);
            System.out.println("[x] sent change request for line  '" + lineToChange + " with content of " + textToChange+ "'"); }
    }
}
