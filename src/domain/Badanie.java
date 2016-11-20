public class Badanie {

	private int id;
	private String wynik;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWynik() {
		return wynik;
	}
	public void setWynik(String wynik) {
		this.wynik = wynik;
	}
	public Badanie(int id, String wynik) {
		this.id = id;
		this.wynik = wynik;
	}
	public Badanie(String wynik) {
		super();
		this.wynik = wynik;
	}
	public Badanie() {
		
	}
}
