import javax.swing.*;

public class StartOkna {

	public static void main(String[] args) {
		JFrame okno = new JFrame();

		String ile;
		String poczekalnia;
		//	String klienci; // do odczytywania liczby klientow
		int ile_pracownikow = 0;
		int ile_poczekalnia = 0;
		//int ile_klientow=0; // do odczytywania liczby klientow

		while (ile_pracownikow < 2 || ile_pracownikow > 10) {
			ile = JOptionPane.showInputDialog("Podaj liczbę pracowników:(2-10)");
			ile_pracownikow = Integer.parseInt(ile);

			if (ile_pracownikow < 2 || ile_pracownikow > 10) {
				JOptionPane.showMessageDialog(null, "Podano liczbę spoza zakresu!");
			}

		}

		PostOffice po = new PostOffice(ile_pracownikow);
		while (ile_poczekalnia < 2 || ile_poczekalnia > 11) {
			poczekalnia = JOptionPane.showInputDialog("Podaj pojemnosc poczekalni:(2-11)");
			ile_poczekalnia = Integer.parseInt(poczekalnia);
			po.pojemnosc = ile_poczekalnia;
			if (ile_poczekalnia < 2 || ile_poczekalnia > 11) {
				JOptionPane.showMessageDialog(null, "Podano liczbę spoza zakresu!");
			}
		}


		//jesli chce sie okreslic ilosc klientow ktora ma zostac obsluzona
//		while (ile_klientow < 2 ) {
//			klienci = JOptionPane.showInputDialog("Podaj liczb� klient�w:(>2)");
//			ile_klientow = Integer.parseInt(klienci);
//			po.custNum = ile_klientow;
//			if (ile_klientow < 2 ) {
//				JOptionPane.showMessageDialog(null, "Poda�e� liczb� spoza zakresu!");
//			}
//
//		}


		okno.setTitle("Poczta");
		okno.setSize(700, 700);
		okno.setVisible(true);
		okno.setResizable(false);
		okno.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		okno.add(new ObslugaPoczty(po, ile_pracownikow));

		new Main(po);
	}
}
