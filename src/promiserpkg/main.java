package promiserpkg;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class main {

	public static void main(String[] args) {
		
		Promiser<String, Integer> req = ExampleService.fakefetch(5000);
				
		req.success((String res) -> {
			// Handle success here
			
		}).error((Integer err) -> {
			// Handle failure here
	
		});	
		
		
	}	

}


