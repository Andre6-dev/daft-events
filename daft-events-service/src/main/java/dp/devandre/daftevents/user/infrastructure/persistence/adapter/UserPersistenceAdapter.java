package dp.devandre.daftevents.user.infrastructure.persistence.adapter;

import dp.devandre.daftevents.user.application.port.out.LoadUserPort;
import dp.devandre.daftevents.user.application.port.out.SaveUserPort;
import dp.devandre.daftevents.user.domain.User;
import dp.devandre.daftevents.shared.UserNotFoundException;
import dp.devandre.daftevents.user.infrastructure.persistence.mapper.UserMapper;
import dp.devandre.daftevents.user.infrastructure.persistence.model.UserJpaEntity;
import dp.devandre.daftevents.user.infrastructure.persistence.repository.UserRepository;
import jakarta.transaction.Transactional;
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
    public Optional<User> loadUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email)
                .map(userMapper::mapToDomainEntity)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email)));
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
        UserJpaEntity userJpaEntity = userMapper.mapToJpaEntity(user);
        userRepository.saveAndFlush(userJpaEntity);
        return userMapper.mapToDomainEntity(userJpaEntity);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    @Override
    public void updateUserEnabledStatus(String email, boolean enabled) {
        userRepository.updateUserEnabledStatus(email, enabled);
    }

}
