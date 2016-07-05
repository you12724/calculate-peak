
public class PeakSearcher {
	
	String path;
	
	public PeakSearcher(String path) {
		this.path = path;
	}

	public void makeAverageCSV() {
		ResonantModel model = CSVFileReader.read(path);
		ResonantModel averageModel1 = CSVFileConverter.makeAverageModel(model, 1);
		ResonantModel averageModel2 = CSVFileConverter.makeAverageModel(model, 2);
		ResonantModel averageModel3 = CSVFileConverter.makeAverageModel(model, 3);
		ResonantModel averageModel4 = CSVFileConverter.makeAverageModel(model, 4);
		ResonantModel averageModel5 = CSVFileConverter.makeAverageModel(model, 5);
		ResonantModel averageModel6 = CSVFileConverter.makeAverageModel(model, 6);

		ResonantModel[] averageModels = {model ,averageModel1, averageModel2, averageModel3, averageModel4, averageModel5, averageModel6};
		CSVFileWriter writer = new CSVFileWriter();
		writer.write(averageModels, "output2");

		System.out.println("完了！！！");
	}

	public void makePeakCSV() {
		ResonantModel model = CSVFileReader.read(path);
		CSVFileWriter writer = new CSVFileWriter();
		writer.write(CSVFileConverter.searchPeaks(model, true), "Arai_165");
		writer.writeWithOriginal(model, CSVFileConverter.searchPeaks(model, true), "Arai_165-2");
		System.out.println("完了！！！");

	}

	public void makeAverage() {
		ResonantModel model = CSVFileReader.read(path);
		ResonantModel averageModel = CSVFileConverter.makeAverageModel(model, 2);

		ResonantModel[] averageModels = {model ,averageModel};
		CSVFileWriter writer = new CSVFileWriter();
		writer.write(averageModels, "output");

		System.out.println("完了！！！");
	}


}
