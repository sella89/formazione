package testFunctionalInterface;

public class RetryUtils {
	@FunctionalInterface
	public interface RetryRun {

		void apply() throws Exception;

	}
	public static boolean withRetryLogic(int maxValueRetry, int valueToSleep, RetryRun run) throws Throwable {
		int count = 0;
		while (count <= maxValueRetry) {
			try {
				System.out.println("count: " + count);
				run.apply();
				return true;
			} catch (Exception e) {
				count++;
				System.out.println("count: " + count + ",sleep:" + valueToSleep + " in milliseconds");
				Thread.sleep(valueToSleep);
			}
			
		}
		throw new Exception("Errore....");
		
	}

}