package br.com.caioWd.services;

import br.com.caioWd.exception.ResourceNotFoundException;
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

    public List<User> findAll(){
        logger.info("Finding users!");
        return repository.findAll();
    }

    public User findById(Long id){
        logger.info("Finding user!");
        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this Id"));
    }

    public User create(User user){
        logger.info("Creating user!");
        return repository.save(user);
    }

    public User update(User user){
        logger.info("Updating user!");
        User entity = repository.findById(user.getId())
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this Id"));

        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setAddress(user.getAddress());
        entity.setGender(user.getGender());

        return repository.save(entity);
    }

    public void delete(Long id){
        logger.info("Deleting user!");
        User entity = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this Id"));
        repository.delete(entity);
    }
}
