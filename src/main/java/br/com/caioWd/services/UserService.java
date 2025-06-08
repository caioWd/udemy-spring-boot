package br.com.caioWd.services;

import br.com.caioWd.data.dto.UserDTO;
import br.com.caioWd.exception.ResourceNotFoundException;
import static br.com.caioWd.mapper.ObjectMapper.parseListObjects;
import static br.com.caioWd.mapper.ObjectMapper.parseObject;
import br.com.caioWd.models.User;
import br.com.caioWd.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UserService {

    private final Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository repository;

    public List<UserDTO> findAll(){
        logger.info("Finding users!");

        return parseListObjects(repository.findAll(), UserDTO.class);
    }

    public UserDTO findById(Long id){
        logger.info("Finding user!");

        var entity = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this Id"));

        return parseObject(entity, UserDTO.class);
    }

    public UserDTO create(UserDTO user){
        logger.info("Creating user!");

        var entity = parseObject(user, User.class);

        return parseObject(repository.save(entity), UserDTO.class);
    }

    public UserDTO update(UserDTO user){
        logger.info("Updating user!");
        User entity = repository.findById(user.getId())
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this Id"));

        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setAddress(user.getAddress());
        entity.setGender(user.getGender());

        return parseObject(repository.save(entity), UserDTO.class);
    }

    public void delete(Long id){
        logger.info("Deleting user!");
        User entity = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this Id"));
        repository.delete(entity);
    }
}
