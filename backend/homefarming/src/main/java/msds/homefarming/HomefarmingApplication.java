package msds.homefarming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class HomefarmingApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(HomefarmingApplication.class, args);
    }

}
