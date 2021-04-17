package common.server;

import common.server.domain.User;
import common.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {

            User admin = new User();
            admin.setEmail("admin@app.com");
            admin.setPassword(encoder.encode("password"));
            admin.setRole("ADMIN");

            User user = new User();
            user.setEmail("user@app.com");
            user.setPassword(encoder.encode("password"));
            user.setRole("USER");

            userRepository.save(admin);
            userRepository.save(user);
        }


    }
}
