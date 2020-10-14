package ubb.project.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ubb.project.iss.domain.Paper;
import ubb.project.iss.domain.SteeringCommittee;
import ubb.project.iss.service.ServiceInterface;

import java.util.List;
@RestController
public class SteeringController {
    @Autowired
    private ServiceInterface<SteeringCommittee> steeringCommitteeService;

    @RequestMapping(value = "/steerings", method = RequestMethod.GET)
    List<SteeringCommittee> getPapers() {
        return steeringCommitteeService.getAll();
    }
}
