package chatapp.test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * Class to test new URL endpoints
 */
@Controller
public class Test {
	@RequestMapping("/test")
	public void test() {
		System.out.println("test");
	}

}
