
public class PeakSearcher {
	
	String path;
	
	public PeakSearcher(String path) {
		this.path = path;
	}

	public ResonantModel[] searchAverages() {
		ResonantModel model = CSVFileReader.read(path);
		ResonantModel averageModel1 = CSVFileConverter.makeAverageModel(model, 1);
		ResonantModel averageModel2 = CSVFileConverter.makeAverageModel(model, 2);
		ResonantModel averageModel3 = CSVFileConverter.makeAverageModel(model, 3);
		ResonantModel averageModel4 = CSVFileConverter.makeAverageModel(model, 4);
		ResonantModel averageModel5 = CSVFileConverter.makeAverageModel(model, 5);
		ResonantModel averageModel6 = CSVFileConverter.makeAverageModel(model, 6);

		ResonantModel[] averageModels = {model ,averageModel1, averageModel2, averageModel3, averageModel4, averageModel5, averageModel6};
		return averageModels;
	}

	public ResonantModel[] searchPeaks() {
		ResonantModel model = CSVFileReader.read(path);
		ResonantModel[] searchModels = {CSVFileConverter.searchPeaks(model, true), CSVFileConverter.searchPeaks(model, true)};
		return searchModels;
	}
}
