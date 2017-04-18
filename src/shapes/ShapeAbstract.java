package shapes;

public abstract class ShapeAbstract implements Shape {
	private int amount = 0; 
	
	public boolean fill(){
		double choice = Math.random();
		if(choice >= 0.5){
			return true;
		}
		return false;
	}

	public int getAmount(){
		return amount;
	}
	
	public void setAmount(int amount){
		this.amount = amount;
	}
	
}
