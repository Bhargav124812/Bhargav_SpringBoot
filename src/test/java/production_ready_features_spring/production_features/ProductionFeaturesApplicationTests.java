package production_ready_features_spring.production_features;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import production_ready_features_spring.production_features.client.RestClientInterface;
import production_ready_features_spring.production_features.dto.EmployeeDTO;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductionFeaturesApplicationTests {

    @Autowired
    RestClientInterface restClientInterface;

	@Test
    @Order(3)
	void getAllEmployeesTest() {
        List<EmployeeDTO> employess=restClientInterface.getAllEmployees();
        System.out.println(employess);
	}
    @Test
    @Order(2)
    void getEmployeeById() {
        System.out.println(restClientInterface.getEmployeeById(1000L));
    }
    @Test
    @Order(1)
    void createEmployee(){
        EmployeeDTO employeeDTO = new EmployeeDTO(null, "Bhargav", "bhargav@gmail.com", 22,
                "USER", 50005.0, LocalDate.of(2025, 8, 23), true);
        System.out.println(restClientInterface.createEmployee(employeeDTO));
    }

}
