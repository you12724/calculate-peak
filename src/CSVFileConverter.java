
public class CSVFileConverter {

	static double GAP_THRESHOLD = 1;
	static double THRESHOLD = -12;

	static ResonantModel searchPeaks(ResonantModel model, boolean isDip) {

		ResonantModel avarageModel = makeAverageModel(model, 2);
		ResonantModel convertModel = new ResonantModel();
		for (int i = 1; i < avarageModel.size() - 1; i++) {
			double before = avarageModel.getY(i - 1);
			double now = avarageModel.getY(i);
			double after = avarageModel.getY(i + 1);
			boolean result;
			if (isDip) {
				result = before >= now && now <= after;
			} else {
				result = before <= now && now >= after;
			}
			if (result){
				if (isPeak(avarageModel, i)) {
					convertModel.addX(model.getX(i));
					convertModel.addY(model.getY(i));
				}
			}
		}
		return convertModel;
	}

	static ResonantModel makeAverageModel(ResonantModel model, int times) {

		ResonantModel averageModel = ResonantModel.copy(model);

		for (int j = 0; j < times; j++) {

			for (int i = 0; i < averageModel.size(); i++) {
				if (i == 0 || i == averageModel.size() - 1) {
					averageModel.setX(i, model.getX(i));
					averageModel.setY(i, averageModel.getY(i));
					continue;
				}

				averageModel.setX(i, model.getX(i));
				double average = (averageModel.getY(i - 1) + averageModel.getY(i) * 10 + averageModel.getY(i + 1)) / 12;
				averageModel.setY(i, average);
			}
		}
		return averageModel;
	}


	private static boolean isPeak(ResonantModel model, int i) {

		int plusPoint = searchChangePoint(model, i, true);
		int minusPoint = searchChangePoint(model, i, false);
		double plusGap = Math.abs(model.getY(i) - model.getY(plusPoint));
		double minusGap = Math.abs(model.getY(i) - model.getY(minusPoint));
		double gap = Math.min(plusGap, minusGap);
		boolean isPeak = gap >= CSVFileConverter.GAP_THRESHOLD && model.getY(i) < CSVFileConverter.THRESHOLD;

		return isPeak;
	}

	private static int searchChangePoint(ResonantModel model, int i, boolean isPlus) {

		int result = i;

		if (isPlus) {
			while(true) {

				if (i == model.size() - 2) {
					result = i;
					break;
				}

				i++;
				double before = model.getY(i - 1);
				double now = model.getY(i);
				double after = model.getY(i + 1);

				if (before <= now && now >= after) {
					result = i;
					break;
				}

			}
		} else {
			while(true) {

				if (i == 1) {
					result = i;
					break;
				}

				i--;
				double before = model.getY(i - 1);
				double now = model.getY(i);
				double after = model.getY(i + 1);

				if (before <= now && now >= after) {
					result = i;
					break;
				}

			}

		}

		return result;
	}


}
