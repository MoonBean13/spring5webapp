package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner {

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;
	
	// Spring: Dependency-Injection	
	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Started in Bootstrap");

		Publisher publisher = new Publisher();
		publisher.setName("SFG Publishing");
		publisher.setCity("St. Petersburg");
		publisher.setState("FL");
		publisher.setZip("6467");
		publisherRepository.save(publisher);
		System.out.println("Publisher Count: " + publisherRepository.count());
		
		Author eric = new Author("Eric", "Evans");
		Book ddd = new Book("Domain Driven Design", "123123");
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);
		
		ddd.setPublisher(publisher);
		publisher.getBooks().add(ddd);
		
		// Save to repository
		authorRepository.save(eric);
		bookRepository.save(ddd);
		publisherRepository.save(publisher);
		
		Author rob = new Author("Rob", "Johnson");
		Book noEJB = new Book("J2EE Development without EJB", "3939459459");
		rob.getBooks().add(noEJB);
		noEJB.getAuthors().add(eric);
	
		noEJB.setPublisher(publisher);
		publisher.getBooks().add(noEJB);
		
		// Save to repository
		authorRepository.save(rob);
		bookRepository.save(noEJB);	
		publisherRepository.save(publisher);
		
		System.out.println("Number of Books: " + bookRepository.count());	
		System.out.println("Publisher Number of Books: " + publisher.getBooks().size());
	}

}

