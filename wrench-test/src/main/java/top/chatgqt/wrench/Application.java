package top.chatgqt.wrench;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Configurable
@SpringBootApplication(scanBasePackages = {"top.chatgqt.wrench"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
