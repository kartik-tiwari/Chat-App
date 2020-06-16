package chatapp.test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Test {
	@RequestMapping("/test")
	public void test() {
		System.out.println("test");
	}
	@RequestMapping("/home")
	public String homepage() {
		return "landing/welcomeView";
	}

}
