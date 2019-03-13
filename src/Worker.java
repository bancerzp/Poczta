
import java.util.concurrent.Semaphore;

public class Worker implements Runnable {
	/* Structure of Worker object */

	public int workerNum;
	// public static Queue<Integer> workerQueue1 = new LinkedList<>();
	// public static Queue<Integer> workerQueue2 = new LinkedList<>();
	int obowiazek; // 0-paczki/listy 1-przekazy/listy
	int[] klient = { -1 }; // jesli zadnego klienta nie obsuguje to nie powinno
							// byc zadnego przy oienku
	int x; // lokalizacja pozioma
	int y;// lokalizacja pionowa
	PostOffice po;
	
	Worker(int workerNum, PostOffice po, int obowiazek) {
		this.workerNum = workerNum;

		this.obowiazek = obowiazek;
		this.po = po;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void run() {
		work_created();

		while (true) {

			wait(po.pracownik);
			czekaj(1200);// czekaj(400);//400 zeby po kolei sie zape³nia³y
			serve_cust();
			signal(po.pracownik);
			czekaj(12000); //czas obs³ugi klienta/czas przerwy pracownika
			klient[0] = -1; //zerowanie tablicy obs³ugiwanego klienta
			po.razem++;

		}
	}

	void work_created() { // tworzenie pracownika
		System.out.println("Pracownik " + workerNum + " zosta³ utworzony");

	}

	void serve_cust() { // obs³uga klientów bêd¹cych w œrodku poczty
		int task = 0;
		 synchronized (po.queue1) {
		if (obowiazek == 0 && !po.queue1.isEmpty()) {
			task = po.queue1.remove(); // usuwam pierwszy element z
										// kolejki któr¹ obs³ugujê
			klient[0] = task;
		}
		 }
		 synchronized (po.queue2) {
		if (obowiazek == 1 && !po.queue2.isEmpty()) {
			task = po.queue2.remove();
			klient[0] = task;
			 }
		}
	}

	void wait(Semaphore s) {
		try {
			s.acquire();
		} catch (InterruptedException e) {

		}
	}

	void signal(Semaphore s) {

		s.release();

	}

	private void czekaj(int n) {
		try {
			Thread.sleep(n);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
