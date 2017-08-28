package osedevops.group.springbootmongo;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import osedevops.group.springbootmongo.SpringbootmongoApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringbootmongoApplication.class)
@WebAppConfiguration
public class SpringbootmongoApplicationTests {
    
    @Test
    public void contextLoads() {
    }
    
}