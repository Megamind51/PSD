

package worker;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.security.SecureRandom;

import static java.lang.Thread.sleep;


public class PusherTest {
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";

    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static SecureRandom random = new SecureRandom();

    public static void main(String[] args) throws Exception {
        try (ZContext context = new ZContext()) {
            ZMQ.Socket push = context.createSocket(SocketType.PUSH);
            push.connect("tcp://localhost:" + args[0]);
            int i = 0;
            sleep(20000);
            while (i < 10000) {
                String str = "" + i++;
                push.send(str);
                System.out.println("Sent " + str);
            }
        }
    }

        public static String generateRandomString(int length) {
            if (length < 1) throw new IllegalArgumentException();

            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {

                // 0-62 (exclusive), random returns 0-61
                int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
                char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

                // debug
                System.out.format("%d\t:\t%c%n", rndCharAt, rndChar);

                sb.append(rndChar);

            }

            return sb.toString();

        }
    }