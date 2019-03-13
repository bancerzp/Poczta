import java.util.Random;

public class Main {
	int workNum;
	PostOffice po;
	Random l = new Random();
	
	public Main(PostOffice po) {
		this.po = po;
		CustGen c = new CustGen(po);
		c.start();
		workNum=po.workNum;
		// tworze dwóch pierwszych pracowników o ró¿nych obowi¹zkach
		po.pracownicy.add(po.objWork[0] = new Worker(0, po, 0));
		po.t2[0] = new Thread(po.objWork[0]);
		po.t2[0].start();

		po.pracownicy.add(po.objWork[1] = new Worker(1, po, 1));
		po.t2[1] = new Thread(po.objWork[1]);
		po.t2[1].start();

		for (int i = 2; i < po.workNum; i++) { // Tworze pracownikï¿½w i
												// ich uruchamiam
			po.pracownicy.add(po.objWork[i] = new Worker(i, po, l.nextInt(2)));
			po.t2[i] = new Thread(po.objWork[i]);
			po.t2[i].start();
			if (po.objWork[i].obowiazek == 0) {
				po.ilosc1++;
			}
			if (po.objWork[i].obowiazek == 1) {
				po.ilosc2++;
			}
		}
	}
}
