package test.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;
import java.util.stream.Collectors;

import test.model.Person;

public class Main {

	public static void main(String[] args) {

		Person p1 = new Person("Riccardo","Sellitto",32);
		Person p2 = new Person("Alessandra","Ciorra",31);
		Person p3 = new Person("Alessandro","Sellitto",24);
		Person p4 = new Person("Mario","Sellitto",74);
		Person p5 = new Person("Eleonora","Sellitto",38);
		Person p6 = new Person("Michela","Sellitto",41);
		Person p7 = new Person("Maurizio","Ciorra",65);
		
		List<Person> people = List.of(p1,p2,p3,p4,p5,p6,p7);
		
		//stampa people
//		people.stream().forEach(System.out::println);
		
		Entry<String, Long> entry = people.stream().collect(
			Collectors.groupingBy(
					p-> p.getSurname(),
					Collectors.counting()
					)
			).entrySet().stream()
			.max(
				Map.Entry.comparingByValue())
			.get();
		System.out.println(entry);
		
//		Supplier<List<Person>> supplier = ()-> new ArrayList<Person>();		
//		BiConsumer<List<Person>,Person> accumulator= (list, person)->{
//			(list).add(person);
//		};
		
//		BinaryOperator<List<Person>> combiner = (list1,list2)->{
//			list1.addAll(list2);
//			return list1;
//			
//		};
		Map<String, List<Person>> collect = 
				people.stream().collect(
				Collectors.groupingBy(
						person->person.getSurname(),
						Collector.of(
								()-> new ArrayList<Person>(), 
								(list, person)->{
									(list).add(person);
								}, 
								(list1,list2)->{									
									list1.addAll(list2);
									return list1;
									
								}, 
								Characteristics.IDENTITY_FINISH)
				)
		);
		
//		collect.entrySet().stream().forEach(System.out::println);
		
		Map<String, Person> collect2 = 
				collect.entrySet().stream().collect(
				Collectors.toMap(
						p->p.getKey(), 
						p->p.getValue().stream()
						.max(
							Comparator.comparing(pe->pe.getAge())
						).get()
				)
		);
		
		collect2.entrySet().stream().forEach(System.out::println);
	}
}
