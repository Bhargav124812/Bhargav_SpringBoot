package SpringSecurityTests;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import securityfeatures.entity.UserEntity;
import securityfeatures.service.JWTService;

@SpringBootTest(classes = securityfeatures.SecurityFeatures.class)
public class SpringSecurityTests {
    @Autowired
    private JWTService jwtService;

    @Test
    public void testJWT(){

        UserEntity user=new UserEntity(4L,"bhargav@gmail.com","bhargav@123");
        String token=jwtService.generateJwtToken(user);
        System.out.println(token);
        System.out.println(jwtService.getIdFromTheToken(token));
    }
}
