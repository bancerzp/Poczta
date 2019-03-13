import java.util.Random;

public class CustGen extends Thread {
	Random r = new Random();
	PostOffice po = new PostOffice();

	public CustGen(PostOffice po) {
		this.po = po;
	}

	public void run() {
		for (int i = 0; i < po.custNum; i++) { // tworze klientow i ich
												// uruchamiam
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			po.klienci.add(po.objCust[i] = new Customer(i, r.nextInt(3), po));

			po.all.add(i);
			// po.razem++;

			po.t1[i] = new Thread(po.objCust[i]);
			if (po.queue1.size() + po.queue2.size() < po.pojemnosc) {
				dequeue();
			}
			po.t1[i].start();
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void dequeue() { // dodawanie z kolejki g³ownej do tych dwóch
							// ró¿nych
		// kolejek(w srodku poczty)

		int next_cust_number;
		next_cust_number = po.all.remove();
		if (po.objCust[next_cust_number].task == 0) {
			po.queue1.add(next_cust_number);

		} else if (po.objCust[next_cust_number].task == 1) {
			po.queue2.add(next_cust_number);

		} else if (po.objCust[next_cust_number].task == 2) {

			if ((100 * po.queue1.size()) / po.ilosc1 < (100 * po.queue2.size()) / po.ilosc2) {

				po.queue1.add(next_cust_number);

			} else if ((100 * po.queue1.size()) / po.ilosc1 == (100 * po.queue2.size()) / po.ilosc2) {
				if (po.ilosc1 >= po.ilosc2) {
					po.queue1.add(next_cust_number);
				} else if (po.ilosc1 < po.ilosc2) {
					po.queue2.add(next_cust_number);

				} else if ((100 * po.queue1.size()) / po.ilosc1 > (100 * po.queue2.size()) / po.ilosc2) {
					po.queue2.add(next_cust_number);

				}

			}
		}
	}
}
