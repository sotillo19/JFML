package jfml.operator;

public abstract class LogicalType {

	public LogicalType() {
		
	}

	public abstract String getOperator();
	
	public abstract Object getContent(int i);

	public abstract float operate(float degree1, float degree2);
}
