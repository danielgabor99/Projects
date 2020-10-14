package ubb.project.iss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ubb.project.iss.domain.Conference;
import ubb.project.iss.domain.Paper;
import ubb.project.iss.domain.SteeringCommittee;
import ubb.project.iss.domain.User;

import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class ProjectApplication {


    public static void main(String[] args) {

        SpringApplication.run(ProjectApplication.class, args);
        final String URL = "http://localhost:8080/api/users";

        RestTemplate restTemplate=new RestTemplate();

        /*ResponseEntity<Paper[]> response =
                restTemplate.getForEntity(
                        URL,
                        Paper[].class);
        Paper[] clients = response.getBody();
        System.out.println(clients.length);

        for (Paper client : Objects.requireNonNull(clients)) {
            System.out.println(client);
        }*/
        ResponseEntity<SteeringCommittee[]> response =
                restTemplate.getForEntity(
                        URL,
                        SteeringCommittee[].class);
        SteeringCommittee[] conferences = response.getBody();
        System.out.println(conferences.length);

        for (SteeringCommittee conference : Objects.requireNonNull(conferences)) {
            System.out.println(conference);
        }

    }

}
