package promiserpkg;

import java.util.Timer;
import java.util.TimerTask;

public class main {

	public static void main(String[] args) {
		
		Promiser<String> req = ExampleService.fakefetch(5000);
				
		req.success((String res) -> {
			// Handle success here
			
		}).error((Object err) -> {
			// Handle failure here
			
		});	
		
		
		
	}	

}


