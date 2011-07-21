package nephilim.junit.ch07.mockito.target;

public class SomeService {
	private SomeRepository repo;

	int count = 0;
	
	public SomeRepository getRepo() {
		return repo;
	}
	
	public void addEmptyString() {
		count++;
		repo.save( Integer.toString(count), "");
	}
	
	public String get(String key) {
		return repo.find(key);
	}
	
}
