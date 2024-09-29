package dp.devandre.daftevents.user.infrastructure.persistence.adapter;

import dp.devandre.daftevents.user.application.port.out.ActivationCodePort;
import dp.devandre.daftevents.user.domain.ActivationCode;
import dp.devandre.daftevents.user.infrastructure.persistence.mapper.ActivationCodeMapper;
import dp.devandre.daftevents.user.infrastructure.persistence.repository.ActivationCodeRepository;
import org.springframework.stereotype.Component;

@Component
public class ActivationCodePersistenceAdapter implements ActivationCodePort {

    private final ActivationCodeRepository activationCodeRepository;
    private final ActivationCodeMapper activationCodeMapper;

    public ActivationCodePersistenceAdapter(ActivationCodeRepository activationCodeRepository, ActivationCodeMapper activationCodeMapper) {
        this.activationCodeRepository = activationCodeRepository;
        this.activationCodeMapper = activationCodeMapper;
    }

    @Override
    public void saveActivationCode(ActivationCode activationCode) {
        activationCodeRepository.saveAndFlush(activationCodeMapper.mapToJpaEntity(activationCode));
    }

    @Override
    public ActivationCode getActivationCodeByKey(String key) {
        return activationCodeRepository.findActivationCodeJpaEntityByKey(key)
                .map(activationCodeMapper::mapToDomainEntity)
                .orElse(null);
    }

    @Override
    public ActivationCode getActivationCodeByUserEmail(String email) {
        return activationCodeRepository.findActivationCodeJpaEntityByUser_Email(email)
                .map(activationCodeMapper::mapToDomainEntity)
                .orElse(null);
    }

    @Override
    public void deleteActivationCodeById(Long id) {
        activationCodeRepository.deleteById(id);
    }
}
