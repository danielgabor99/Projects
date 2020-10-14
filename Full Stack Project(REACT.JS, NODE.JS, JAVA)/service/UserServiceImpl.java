package ubb.project.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ubb.project.iss.domain.Paper;
import ubb.project.iss.domain.User;
import ubb.project.iss.repository.PaperRepository;
import ubb.project.iss.repository.regular_user.RegularUserRepository;

import java.util.List;
@Service
public class UserServiceImpl implements ServiceInterface<User> {
    @Qualifier("regularUserRepository")
    @Autowired
    private RegularUserRepository userRepository;
    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        return result;
    }
}
