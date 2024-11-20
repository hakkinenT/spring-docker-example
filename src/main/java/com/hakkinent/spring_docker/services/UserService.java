package com.hakkinent.spring_docker.services;

import com.hakkinent.spring_docker.entities.User;
import com.hakkinent.spring_docker.entities.dto.UserDTO;
import com.hakkinent.spring_docker.exceptions.custom.ResourceNotFoundException;
import com.hakkinent.spring_docker.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserDTO insert(UserDTO dto){
        User user = new User();
        copyDTOToEntity(dto, user);

        user = userRepository.save(user);

        return new UserDTO(user);
    }

    private static void copyDTOToEntity(UserDTO dto, User user) {
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return new UserDTO(user);
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAll(){
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).toList();
    }

    @Transactional
    public UserDTO update(Long id, UserDTO dto){
        try {
            User user = userRepository.getReferenceById(id);
            copyDTOToEntity(dto, user);

            user = userRepository.save(user);

            return new UserDTO(user);

        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Usuário não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if(!userRepository.existsById(id)){
            throw new ResourceNotFoundException("Usuário não encontrado");
        }

        userRepository.deleteById(id);
    }
}
