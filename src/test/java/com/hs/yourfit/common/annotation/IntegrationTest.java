package com.hs.yourfit.common.annotation;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hs.yourfit.common.before.UserDtoSetUp;
import com.hs.yourfit.common.before.UserSetUp;
import com.hs.yourfit.core.security.jwt.JwtCore;
import com.hs.yourfit.domain.user.repository.UserAddressRepository;
import com.hs.yourfit.domain.user.repository.UserBusinessInfoRepository;
import com.hs.yourfit.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "spring.config.location=" +
                "classpath:/application.yml," +
                "/var/lib/jenkins/workspace/real-application.yml," +
                "classpath:/secret.yml"
)
@ActiveProfiles("test")
@Transactional
public abstract class IntegrationTest {

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Autowired
    protected JwtCore jwtCore;

    @Autowired
    protected ObjectMapper objectMapper;

    @LocalServerPort
    protected static int port;

    protected MockMvc mockMvc;

    protected UserSetUp userSetUp = new UserSetUp();
    protected UserDtoSetUp userDtoSetUp = new UserDtoSetUp();


    //Repository
    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected UserAddressRepository userAddressRepository;

    @Autowired
    protected UserBusinessInfoRepository userBusinessInfoRepository;

    protected final static String URL = "http://localhost:";

    protected final static String USER_URL = URL + port + "/v1/api/users";


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();

        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }
}
