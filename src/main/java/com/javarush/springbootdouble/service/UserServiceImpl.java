package com.javarush.springbootdouble.service;

import com.javarush.springbootdouble.dto.UserDto;
import com.javarush.springbootdouble.dto.UserRegistrationDto;
import com.javarush.springbootdouble.entity.User;
import com.javarush.springbootdouble.repository.UserRepository;
import com.javarush.springbootdouble.service.exception.RegistrationRuntimeException;
import com.javarush.springbootdouble.service.mapper.UserMapper;
import com.javarush.springbootdouble.service.validator.UserValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            throw new RegistrationRuntimeException("");
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

}
