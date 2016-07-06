import java.util.ArrayList;
import java.util.List;

public class ResonantModel {
	private List<Double> x = new ArrayList<Double>();
	private List<Double> y = new ArrayList<Double>();

	static public ResonantModel copy(ResonantModel model) {
		ResonantModel copyModel = new ResonantModel();
		for (int i = 0; i < model.x.size(); i++) {
			copyModel.addX(model.getX(i));
			copyModel.addY(model.getY(i));
		}
		return copyModel;
	}

	public double getX(int i) {
		return this.x.get(i);
	}

	public double getY(int i) {
		return this.y.get(i);
	}

	public void addX(double num) {
		this.x.add(num);
	}

	public void addY(double num) {
		this.y.add(num);
	}

	public void setX(int index, double num) {
		this.x.set(index, num);
	}

	public void setY(int index, double num) {
		this.y.set(index, num);
	}

	public int contain(double data) {

		int result = -1;

		for (int i = 0; i < this.x.size(); i++) {
			if (this.getX(i) == data) {
				result = i;
			}
		}

		return result;

	}

	public int size() {
		return this.x.size();
	}
	
	public double getMinY(){
		double minY = 100;
		for (double y : this.y) {
			if (minY > y) {
				minY = y;
			}
		}
		return minY;
	}
	
	public double getMaxY(){
		double maxY = -100;
		for (double y : this.y) {
			if (maxY < y) {
				maxY = y;
			}
		}
		return maxY;
	}
}
