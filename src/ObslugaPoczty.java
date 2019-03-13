import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ObslugaPoczty extends JPanel {

	File kasjer1 = new File("kasjer1.png");
	File kasjer2 = new File("kasjer2.png");
	File klient1 = new File("klient1.png");
	File klient2 = new File("klient2.png");
	File klient3 = new File("klient3.png");
	File chair = new File("chair.png");
	File desk = new File("desk.png");
	File gazety = new File("gazety1.png");
	File man = new File("man.png");
	File end = new File("end.png");
	private BufferedImage imageKasjer1 = new BufferedImage(40, 40, 10);
	private BufferedImage imageKasjer2 = new BufferedImage(40, 40, 10);
	private BufferedImage imageKlient1 = new BufferedImage(40, 40, 10);
	private BufferedImage imageKlient2 = new BufferedImage(40, 40, 10);
	private BufferedImage imageKlient3 = new BufferedImage(40, 40, 10);
	private BufferedImage imageChair = new BufferedImage(40, 40, 10);
	private BufferedImage imageDesk = new BufferedImage(40, 40, 10);
	private BufferedImage imageGazety = new BufferedImage(40, 40, 10);
	private BufferedImage imageMan = new BufferedImage(40, 40, 10);
//	private BufferedImage imageEnd = new BufferedImage(40, 40, 10);

	Lock lock = new ReentrantLock(); // zamki dostepu do kolejek
	Lock lock2 = new ReentrantLock();
	int pomoc = 0;
	int pomoc2 = 0;// ustalanie limitu kolejki/konczenia rysowania
	// int razem = 0;
	int ile;
	PostOffice po = new PostOffice();
	private static final long serialVersionUID = 1L;

	public ObslugaPoczty(PostOffice po, int ile) {
		this.ile = ile;
		this.po = po;
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 153, 700, 700);
		try {
			imageKasjer1 = ImageIO.read(kasjer1);
			imageKasjer2 = ImageIO.read(kasjer2);
			imageKlient1 = ImageIO.read(klient1);
			imageKlient2 = ImageIO.read(klient2);
			imageKlient3 = ImageIO.read(klient3);
			imageDesk = ImageIO.read(desk);
			imageChair = ImageIO.read(chair);
			imageGazety = ImageIO.read(gazety);
			imageMan = ImageIO.read(man);
//			imageEnd = ImageIO.read(end);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// czekaj();

		// rysowanie pracowników
		for (int i = 0; i < po.pracownicy.size(); i++) {
			int s = ((getWidth() - 10 * po.pracownicy.size() - 1) / po.pracownicy.size() - 1); // ustalanie
			// szerokoœci
			// okienka
			g.setColor(Color.BLACK);
			// g.drawRect(10 + s * i + 10 * i, 30, s, 40);
			po.pracownicy.get(i).setX(s * i + 10 * i);
			po.pracownicy.get(i).setY(10 + s * i + 10 * i);
			if (po.objWork[i].obowiazek == 0) {
				g.drawImage(imageKasjer1, s * i + 10 * i, 0, this);
			} else if (po.objWork[i].obowiazek == 1) {
				g.drawImage(imageKasjer2, s * i + 10 * i, 0, this);
			}
		}
		//jesli chce sie sprawdzic poprawnosc wprowadzonych wymogów
//		g.drawString("Liczba pracownikow" + po.workNum, 20, 10);
//		g.drawString("Liczba pojemnosc" + po.pojemnosc, 400, 10);
		// rysowanie krzese³ i stolików
		for (int n = 0; n < 5; n++) {
			g.drawImage(imageDesk, 50, 300 + n * 50, this);
			g.drawImage(imageChair, 0, 300 + n * 50, this);
		}

		// rysowanie mê¿czyzn i stojaka na gazety
		g.drawImage(imageGazety, 580, 300, this);
		g.drawImage(imageMan, 620, 380, this);
		g.drawImage(imageMan, 580, 440, this);
		g.drawImage(imageMan, 500, 380, this);

		// rysowanie pierwszej kolejki
		repaint();
		pierwsza(g);
		druga(g);
		for (int i = 0; i < ile; i++) {
			if (po.objWork[i].klient[0] != -1) {
				if (po.objCust[po.objWork[i].klient[0]].task == 0) {
					g.drawImage(imageKlient2, po.objWork[i].getX() + 10, 100, this);
				} else if (po.objCust[po.objWork[i].klient[0]].task == 1) {
					g.drawImage(imageKlient1, po.objWork[i].getX() + 10, 100, this);
				} else if (po.objCust[po.objWork[i].klient[0]].task == 2) {
					g.drawImage(imageKlient3, po.objWork[i].getX() + 10, 100, this);
				}
//				 g.drawString(Integer.toString(po.objWork[i].klient[0]), po.objWork[i].getX() , 200);	//zeby sprawdzic czy zgadzaja sie numery klientow w kolejce i tych którzy s¹ aktualnie obslugiwani
			}

		}
	}

	public void czekaj() {
		for (int i = 0; i < 300; i++) {
			for (int j = 0; j < 400; j++) {

			}

		}
	}

	public synchronized void pierwsza(Graphics g) {
		if (!po.queue1.isEmpty()) {
			try {
				for (Integer i1 : po.queue1) {

					lock.lock();

					if (po.objCust[i1].task == 0) {
						g.drawImage(imageKlient2, 200, 40 * pomoc2 + 250 + pomoc2 * 1, this);
					} else if (po.objCust[i1].task == 2) {
						g.drawImage(imageKlient3, 200, 40 * pomoc2 + 250 + pomoc2 * 1, this);
					}
//					 g.drawString(Integer.toString(i1), 190, 40 * pomoc2 + 250
//					 + pomoc2 * 1);  //zeby sprawdzic czy zgadzaja sie numery klientow w kolejce i tych którzy s¹ aktualnie obslugiwani
					pomoc2 += 1;
					lock.unlock();
					if (pomoc2 == po.queue1.size() || pomoc2 == po.pojemnosc) {
						pomoc2 = 0;
						repaint();
						break;
					}
				}
			} catch (ConcurrentModificationException e) {
			}
		}

	}

	// rysowanie drugiej kolejki
	public synchronized void druga(Graphics g) {
		if (!po.queue2.isEmpty()) {
			try {
				for (Integer i11 : po.queue2) {
					lock2.lock();
					if (po.objCust[i11].task == 1) {
						g.drawImage(imageKlient1, 400, 40 * pomoc + 250 + pomoc * 1, this);
					} else if (po.objCust[i11].task == 2) {
						g.drawImage(imageKlient3, 400, 40 * pomoc + 250 + pomoc * 1, this);
					}
					pomoc += 1;
//					 g.drawString(Integer.toString(i11), 390, 40 * pomoc + 250
//							 + pomoc * 1);
					if (pomoc == po.queue2.size() || pomoc == po.pojemnosc) {
						pomoc = 0;
						repaint();
						break;
					}
				}
				lock2.unlock();

			} catch (ConcurrentModificationException e) {

			}
		}

	}

}
