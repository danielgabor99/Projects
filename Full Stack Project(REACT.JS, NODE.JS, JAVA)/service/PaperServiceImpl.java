package ubb.project.iss.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ubb.project.iss.domain.Paper;
import ubb.project.iss.repository.PaperRepository;

import java.util.List;
@Service
public class PaperServiceImpl implements ServiceInterface<Paper> {
    @Autowired
    private PaperRepository paperRepository;


    @Override
    public List<Paper> getAll() {
        List<Paper> result = paperRepository.findAll();
        return result;
    }
}
