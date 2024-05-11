package com.javarush.springbootdouble.service;

import com.javarush.springbootdouble.dto.UserDto;
import com.javarush.springbootdouble.dto.UserRegistrationDto;
import com.javarush.springbootdouble.entity.User;
import com.javarush.springbootdouble.repository.UserRepository;
import com.javarush.springbootdouble.service.exception.EntityNotFoundRuntimeException;
import com.javarush.springbootdouble.service.exception.EntityType;
import com.javarush.springbootdouble.service.exception.RegistrationRuntimeException;
import com.javarush.springbootdouble.service.mapper.UserMapper;
import com.javarush.springbootdouble.service.validator.UserValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// update --
// swagger
// soap
// profile (dev h2), profile(prod mysql)
// mvc test,
// repository tests
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserValidator userValidator;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public void register(UserRegistrationDto userDto) {
        userValidator.validateUserRegistration(userDto);

        boolean emailIsUsed = userRepository.findByEmail(userDto.getEmail()).isPresent();
        if (emailIsUsed) {
            throw new RegistrationRuntimeException(String.format("This email [%s] is already used", userDto.getEmail()));
        }

        User user = userMapper.mapUserRegistrationDtoToUser(userDto);

        userRepository.save(user);
    }

    @Override
    public Page<UserDto> findAll(Integer page, Integer perPage) {
        PageRequest pageRequest = PageRequest.of(page, perPage);

        return userRepository.findAll(pageRequest)
                .map(userMapper::mapUserToUserDto);
    }

    @Override
    public UserDto findById(String id) throws EntityNotFoundRuntimeException {
        return userRepository.findById(id)
                .map(userMapper::mapUserToUserDto)
                .orElseThrow(() -> new EntityNotFoundRuntimeException(String.format("User with id[%s] does not exist", id), EntityType.USER));
    }

    @Transactional
    @Override
    public UserDto deleteById(String userId) throws EntityNotFoundRuntimeException {
        UserDto user = findById(userId);
        userRepository.deleteById(userId);
        return user;
    }

}
