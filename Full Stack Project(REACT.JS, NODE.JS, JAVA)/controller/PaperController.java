package ubb.project.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ubb.project.iss.domain.Paper;
import ubb.project.iss.service.PaperServiceImpl;
import ubb.project.iss.service.ServiceInterface;

import java.util.List;
import java.util.Set;

@RestController
public class PaperController {
    @Autowired
    private ServiceInterface<Paper> paperService;

    @RequestMapping(value = "/papers", method = RequestMethod.GET)
    List<Paper> getPapers() {
        return paperService.getAll();
    }
}
