package dp.devandre.daftevents.user.infrastructure.persistence.adapter;

import dp.devandre.daftevents.user.application.port.out.LoadUserPort;
import dp.devandre.daftevents.user.application.port.out.SaveUserPort;
import dp.devandre.daftevents.user.domain.User;
import dp.devandre.daftevents.shared.UserNotFoundException;
import dp.devandre.daftevents.user.infrastructure.persistence.mapper.UserMapper;
import dp.devandre.daftevents.user.infrastructure.persistence.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserPersistenceAdapter implements LoadUserPort, SaveUserPort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserPersistenceAdapter(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> loadUser(Integer id) {
        return Optional.ofNullable(userRepository.findById(id)
                .map(userMapper::mapToDomainEntity)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id)));
    }

    @Override
    public List<User> loadUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::mapToDomainEntity)
                .toList();
    }

    @Override
    public User saveUser(User user) {
        return userMapper.mapToDomainEntity(userRepository.saveAndFlush(userMapper.mapToJpaEntity(user)));
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
