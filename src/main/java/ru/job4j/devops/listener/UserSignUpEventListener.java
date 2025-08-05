package ru.job4j.devops.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.job4j.devops.models.User;
import ru.job4j.devops.repository.UserRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserSignUpEventListener {

    private final UserRepository userRepository;

    @KafkaListener(topics = "signUp", groupId = "job4j")
    public void signUp(User user) {
        log.debug("Received user: {}", user.getName());
        userRepository.save(user);
    }
}
