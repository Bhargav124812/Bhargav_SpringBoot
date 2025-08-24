package production_ready_features_spring.production_features.client;

import com.sun.net.httpserver.Authenticator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import production_ready_features_spring.production_features.advices.ApiResponse;
import production_ready_features_spring.production_features.dto.EmployeeDTO;
import production_ready_features_spring.production_features.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestClientImpl implements RestClientInterface{

    private final RestClient restClient;

    Logger log= LoggerFactory.getLogger(RestClientImpl.class);


    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.trace("Getting All the Employees from the Server");
        try {
            ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient.get()
                    .uri("employee")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError ,(req ,res) -> {
                          log.error(new String(res.getBody().readAllBytes()));
                          throw new ResourceNotFoundException("could not find the Employess");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.debug("Successfull Retrived the Employees");
            log.trace("Retrived the employees With details : {} ",employeeDTOList.getData());
            return employeeDTOList.getData();
        } catch (Exception e) {
            log.error("Error in the Server",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employee){
        try {
            log.trace("Getting the Employee With ID");
        ApiResponse<EmployeeDTO> employeeById=restClient.get()
                .uri("employee/{employee}",employee)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError , (req,res)->{
                    log.error(new String(res.getBody().readAllBytes()));
                    throw new ResourceNotFoundException("Unable to Get The Employee");
                })
                .body(new ParameterizedTypeReference<>() {
                });
        log.trace("Employee With id {} Has Been Successfully retrived",employee);
        return employeeById.getData();
        } catch (Exception e) {
            log.error("Error While Retiving THe Data",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        try {
            log.trace("Creating the Employee");
            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse=restClient.post().uri("employee")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.debug("4xxClient error occurred during createNewEmployee");
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not create the employee");
                    })
                    .toEntity(new ParameterizedTypeReference<>() {
                    });
            log.trace("Succeffuly CReated the Employee {}",employeeDTOApiResponse.getBody().getData());
            return employeeDTOApiResponse.getBody().getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
