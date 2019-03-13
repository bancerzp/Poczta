import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class PostOffice {

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
//	Tutaj mo�esz swobodnie zmienia� liczb� pracownik�w oraz pojemno�� poczekalni~
// ZASADY:
//liczba prcownik�w>=2

//wielko�c poczekalni >=2
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	public int pojemnosc; // 			
	public int custNum =100; // liczbatworzonych klientow
	public int workNum ; // liczba tworzonych pracownik�w
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	
	public Semaphore pracownik = new Semaphore(1, true);
	public Queue<Integer> queue1 = new LinkedList<>(); // lista do pracownika z  zadaniem 1							
	public Queue<Integer> queue2 = new LinkedList<>(); // lista do pracownika z zadaniem 2											
	public Queue<Integer> all = new LinkedList<>();	//lista wszystkich utworzonych klient�w
	public ArrayList<Worker> pracownicy = new ArrayList<Worker>();
	public ArrayList<Customer> klienci = new ArrayList<Customer>();
	int ilosc1=1,ilosc2=1; //zliczanie liczby pracownik�w poszczeg�lnych rodzaj�w
	int razem=0;
	PostOffice(){
		
	}
PostOffice(int ile){
		this.workNum=ile;
	}
public Customer[] objCust = new Customer[custNum]; // tablica klientow
public Worker[] objWork = new Worker[10]; // tablica pracownik�w
public Thread[] t1 = new Thread[custNum]; // tablica w�tk�w klientow
public Thread[] t2 = new Thread[10]; // tablica w�tk�w pracownik�w
}
