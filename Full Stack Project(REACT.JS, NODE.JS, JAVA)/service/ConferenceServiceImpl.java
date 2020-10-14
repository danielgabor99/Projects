package ubb.project.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ubb.project.iss.domain.Conference;
import ubb.project.iss.domain.Paper;
import ubb.project.iss.repository.ConferenceRepository;

import java.util.List;
@Service
public class ConferenceServiceImpl implements ServiceInterface<Conference> {
    @Autowired
    private ConferenceRepository conferenceRepository;
    @Override
    public List<Conference> getAll() {
        List<Conference> result = conferenceRepository.findAll();
        return result;
    }
}
