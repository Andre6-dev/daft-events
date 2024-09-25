package dp.devandre.daftevents.user.application.mapper;

import dp.devandre.daftevents.user.application.dto.UserListResponse;
import dp.devandre.daftevents.user.domain.User;
import dp.devandre.daftevents.user.infrastructure.persistence.mapper.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserListResponseMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "documentNumber", source = "documentNumber")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "profileUrl", source = "profileUrl")
    @Mapping(target = "roles", source = "roles")
    UserListResponse toDto(User user);

    List<UserListResponse> toListDto(List<User> users);
}
