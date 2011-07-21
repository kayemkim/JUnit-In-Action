package nephilim.junit.ch07.mockito.target;

import java.util.HashMap;
import java.util.Map;

public class SomeRepository {
	private Map<String, String> map = new HashMap<String, String>();
	
	public void save(String key, String value) {
		map.put(key, value);
	}
	public String find(String key) {
		return map.get(key);
	}
}
