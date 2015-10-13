package promiserpkg;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class ExampleService {
	public static int offsetKudos = 0;	
	
	
	public static Promiser<String, Integer> fakefetch(int ms) {
		Promiser<String, Integer> req = new Promiser<String, Integer>((Resolver<String> resolve, Rejecter<Integer> reject) -> {
			System.out.println("BEGIN");
			
			// Place your asynchronous process here, and make sure to trigger resolve.run() or reject.run() when needed.
			
			new Timer().schedule(new TimerTask() {
			    @Override
			    public void run() {
			    	// resolving 
			    	System.out.println("END");
					resolve.run("ça marche");
			    }
			}, ms);
			
		});
				
		return req;
	}

}


