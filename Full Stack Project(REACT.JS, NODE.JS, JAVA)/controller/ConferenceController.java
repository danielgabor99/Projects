package ubb.project.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ubb.project.iss.domain.Conference;
import ubb.project.iss.domain.Paper;
import ubb.project.iss.service.ServiceInterface;

import java.util.List;

@RestController
public class ConferenceController {
    @Autowired
    private ServiceInterface<Conference> conferenceService;

    @RequestMapping(value = "/conferences", method = RequestMethod.GET)
    List<Conference> getConferences() {
        return conferenceService.getAll();
    }
}
