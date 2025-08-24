package production_ready_features_spring.production_features.client;

import production_ready_features_spring.production_features.dto.EmployeeDTO;

import java.util.List;

public interface RestClientInterface {
      List<EmployeeDTO> getAllEmployees();
      EmployeeDTO getEmployeeById(Long employee);
      EmployeeDTO createEmployee(EmployeeDTO employeeDTO);


}
