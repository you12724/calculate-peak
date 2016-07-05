import java.util.ArrayList;
import java.util.List;

public class ResonantModel {
	private List<Double> x = new ArrayList<Double>();
	private List<Double> y = new ArrayList<Double>();

	public ResonantModel(ResonantModel model) {
		for (int i = 0; i < model.x.size(); i++) {
			this.addX(model.getX(i));
			this.addY(model.getY(i));
		}
	}

	public ResonantModel() {

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
}
