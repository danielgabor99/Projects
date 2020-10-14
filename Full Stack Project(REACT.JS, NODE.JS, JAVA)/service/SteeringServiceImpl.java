package ubb.project.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ubb.project.iss.domain.SteeringCommittee;
import ubb.project.iss.domain.User;
import ubb.project.iss.repository.steering_committee.SteeringCommitteeRepository;

import java.util.List;
@Service
public class SteeringServiceImpl implements ServiceInterface<SteeringCommittee> {
    @Autowired
    private SteeringCommitteeRepository steeringCommitteeRepository;
    @Override
    public List<SteeringCommittee> getAll() {
        List<SteeringCommittee> result = steeringCommitteeRepository.findAll();
        return result;
    }
}
