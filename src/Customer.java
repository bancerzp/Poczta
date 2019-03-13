
public class Customer implements Runnable {
	/* Customer class structure */

	public int custNum;
	public int task; // zadanie
	public int worker_assigned;
	int x;
	int y;
	PostOffice po;

	Customer(int custNum, int task, PostOffice po) {
		this.custNum = custNum;
		this.task = task;
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
	}

}