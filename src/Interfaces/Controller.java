package Interfaces;

public interface Controller {
	public void handle(int id, String msg);
	public void keyEvent();
	public void selectEvent(int id);
	public void disconect();
}
