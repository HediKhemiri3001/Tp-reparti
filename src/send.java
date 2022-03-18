import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
public class send {
    private final static String QUEUE_NAME = "test-queue";
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            String message = "Validation naim hedi mahdi";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("[x] sent '" + message + "'"); }
    }
}