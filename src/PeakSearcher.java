
public class PeakSearcher {
	
	ResonantModel model;
	
	public PeakSearcher(ResonantModel model) {
		this.model = model;
	}

	public ResonantModel[] searchPeaks() {
		ResonantModel[] searchModels = {CSVFileConverter.searchPeaks(model, true), CSVFileConverter.searchPeaks(model, true)};
		return searchModels;
	}
}
