package production_ready_features_spring.production_features.client;

import lombok.RequiredArgsConstructor;
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


    @Override
    public List<EmployeeDTO> getAllEmployees() {
        try {
            ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient.get()
                    .uri("employee")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDTOList.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employee){
        try {
        ApiResponse<EmployeeDTO> employeeById=restClient.get()
                .uri("employee/{employee}",employee)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        return employeeById.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        try {
            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse=restClient.post().uri("employee")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        System.out.println(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not create the employee");
                    })
                    .toEntity(new ParameterizedTypeReference<>() {
                    });
            return employeeDTOApiResponse.getBody().getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
