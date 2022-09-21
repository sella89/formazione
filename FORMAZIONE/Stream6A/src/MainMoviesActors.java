import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.Actor;
import model.Movie;

public class MainMoviesActors {

	public static void main(String[] args) throws IOException {

		Set<Movie> movies = new HashSet<>();
		Stream<String> lines = Files.lines(Paths.get("files", "movies-mpaa.txt"));
		lines.forEach(
				(String line) -> {
					String[] elements = line.split("/");
					String title = 
							elements[0].substring(0, elements[0].lastIndexOf("(")).trim();
					String releaseYear = 
							elements[0].substring(elements[0].lastIndexOf("(")+1, elements[0].lastIndexOf(")"));
					
					 if (releaseYear.contains(",")) {
	                        // with skip movies with a coma in their title
	                        return;
	                    }

	                    Movie movie = new Movie(title, Integer.valueOf(releaseYear));

	                    for (int i = 1; i < elements.length; i++) {
	                        String[] name = elements[i].split(", ");
	                        String lastName = name[0].trim();
	                        String firstName = "";
	                        if (name.length > 1) {
	                            firstName = name[1].trim();
	                        }

	                        Actor actor = new Actor(lastName, firstName);
	                        movie.addActor(actor);
	                    }

	                    movies.add(movie);
				}
		);
		System.out.println("# movies : " + movies.size());
		// # of actors
		long numberOfActors = movies.stream()
				.flatMap(movie -> movie.getActors().stream())//Stream<Actors>
				.distinct()
				.count();
		
		System.out.println("# of Actors: " + numberOfActors);
		
		// # of actors that played in the greatest # of movies
//		Map<Actor, Long> collect = 
		Entry<Actor, Long> mostViewedActor = movies.stream()
				.flatMap(movie -> movie.getActors().stream())
				.collect(
						Collectors.groupingBy(
//								actor -> actor,
								Function.identity(),
								Collectors.counting()
				)
		)
		.entrySet().stream()
		.max(
//				Comparator.comparing(entry-> entry.getValue())
				Map.Entry.comparingByValue()
		).get();
		
		System.out.println("Most viewed actor: "+ mostViewedActor);
		
//		Map<Actor, Long> collect = 
//				movies.stream()
//				.flatMap(
//						movie -> movie.getActors().stream())
//				.collect(
//						Collectors.groupingBy(
//								Function.identity(),
//								Collectors.counting()));
//		
//		
//		collect.forEach((key,value)->System.out.println("attore: "+key.getFirstName()+" "+ key.getLastName()+",counting:"+value));

		//Actor that played in the greatest # of movies during a year
		// Map<releaseYears, Map<Actor, # of movies during that Years>>
		
		Map<Integer, HashMap<Actor, AtomicLong>> collect = movies.stream()
			.collect(
					Collectors.groupingBy(
							movie -> movie.getReleaseYear(),
							Collector.of(
									() -> new HashMap<Actor,AtomicLong>(),// supplier
									(map, movie) -> {
                                        movie.getActors().forEach(
                                                actor -> map.computeIfAbsent(actor, a -> new AtomicLong()).incrementAndGet()
                                        );
                                    }, // accumulator
									 (map1, map2) -> {
                                         map2.entrySet().forEach(
                                                 entry2 -> map1.merge(
                                                         entry2.getKey(), entry2.getValue(),
                                                         (al1, al2) -> {
                                                             al1.addAndGet(al2.get());
                                                             return al1;
                                                         }
                                                 )
                                         );
                                         return map1;
                                     }, // combiner
                                     Collector.Characteristics.IDENTITY_FINISH
							)
					)
			);
//		collect.entrySet().stream().forEach(System.out::println);
		
		Map<Integer, Entry<Actor, AtomicLong>> collect2 = collect.entrySet().stream().collect(
				Collectors.toMap(
						entry-> entry.getKey(), 
						entry-> entry.getValue().entrySet().stream()
								.max(
									Map.Entry.comparingByValue(Comparator.comparing(l->l.get()))
								).get()
						
				)
		);
		//Map<Integer, Entry<Actor, AtomicLong>>
//		collect2.entrySet().stream().forEach(System.out::println);
		
		Entry<Integer, Entry<Actor, AtomicLong>> entry2 = collect2.entrySet().stream()
			.max(
				Map.Entry.comparingByValue(
						Comparator.comparing( entry -> entry.getValue().get())
						)
			).get();
		
		System.out.println(entry2);
		
	}

}
