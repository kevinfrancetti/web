package ch.supsi.web.service;


import ch.supsi.web.model.User;
import ch.supsi.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User getUserById(String userName){
        return userRepository.findById(userName).get();
    }


}
