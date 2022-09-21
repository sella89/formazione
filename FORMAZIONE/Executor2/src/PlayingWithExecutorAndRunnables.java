import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlayingWithExecutorAndRunnables {

	public static void main(String[] args) {
//		Runnable task  = () -> prova();
		
//		ExecutorService service = Executors.newSingleThreadExecutor();

		for(int i = 0; i<5;i++) {
			System.out.println("number of time:"+i);
			extracted();
			System.out.println("finish");
		}
	}

	private static void extracted() {
		ExecutorService service = Executors.newFixedThreadPool(4);
		for(int i = 0; i<4;i++) {
//			new Thread(task).start();			
			final int prova = i;
			service.submit(() -> prova(prova));
		}
		
		service.shutdown();
		System.out.println("shutdown");
	}
	
	private static void prova(final int i) {
		try {
			System.out.println("int i:"+i);
			Thread.currentThread().sleep(5000L);
			System.out.println(
					"I am in thread" + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
