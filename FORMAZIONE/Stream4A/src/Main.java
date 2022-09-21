import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		
//		List<Double>result= new ArrayList<>();
//		ThreadLocalRandom.current()
//			.doubles(10_000).boxed()
//			.forEach(
//				d -> NewMath.inv(d)
//							.ifPresent(
//								inv -> NewMath.sqrt(inv)
//										.ifPresent(
//												sqrt-> result.add(sqrt)
//										)
//							)
//			);
//		System.out.println("# result: "+result.size());
		
		Function<Double, Stream<Double>> flatMapper = 
				d -> NewMath.inv(d)
							.flatMap(inv -> NewMath.sqrt(inv))
							.map( sqrt -> Stream.of(sqrt))
							.orElseGet(() -> Stream.empty());
				
		List<Double> collect = ThreadLocalRandom.current()
						.doubles(10_000).parallel()
						.boxed()
						.flatMap(flatMapper)
						.collect(Collectors.toList());
		
		System.out.println("# righResult: " +collect.size());
	}

}
