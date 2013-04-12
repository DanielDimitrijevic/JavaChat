package Interfaces;
/**
 * INterface um anzugeben was ein Controller Beinhalten muss
 * @author Dominik
 *
 */
public interface Controller {
	public void handle(int id, String msg);
	public void keyEvent();
	public void selectEvent(int id);
	public void disconect();
}
