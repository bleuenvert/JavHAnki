//not implemented :(
package application;

public class Card {
	
	private String front = "";
	private String back = "";
	
	public Card(String question, String answer) {
		front = question;
		back = answer;
	}
	
	public Card(Card cardToCopy) {
		front = cardToCopy.getFront();
		back = cardToCopy.getBack();
	}

	public String getFront() {
		return front;
	}

	public void setFront(String front) {
		this.front = front;
	}

	public String getBack() {
		return back;
	}

	public void setBack(String back) {
		this.back = back;
	}


}
