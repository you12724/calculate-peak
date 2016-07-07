import java.awt.event.MouseEvent;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

class MyChartPanel extends ChartPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3919565198073272647L;
	
	double firstX = 0;
	double firstY = 0;
	double lastX = 0;
	double lastY = 0;
	ResonantModel model;
	ResonantModel choosePeakModel;
	boolean isPeak = true;
	
	public MyChartPanel(JFreeChart chart, ResonantModel model) {
		super(chart);
		this.model = model;
		this.choosePeakModel = new ResonantModel();
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// 何もしない
	}
	
    @Override
    public void mouseDragged(MouseEvent e) {
    	if (firstX == 0) {
    		firstX = e.getX() - getScreenDataArea().getX();
    	}
    	if (firstY == 0) {
    		firstY = e.getY();
    	}
    	lastX = e.getX() - getScreenDataArea().getX();
    	lastY = e.getY();
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    	if (firstX == 0 && lastX == 0) {
    		return;
    	}
    	System.out.println("first: " + this.firstX + "," + this.firstY);
    	System.out.println("last: " + this.lastX + "," + this.lastY);
    	
    	int lastXPoint = (int) (lastX / getScreenDataArea().getWidth() * model.size());
    	if (lastXPoint < 0) {
    		lastXPoint = 0;
    	}
    	if (lastXPoint > model.size()) {
    		lastXPoint = model.size();
    	}
    	int firstXPoint = (int) (firstX / getScreenDataArea().getWidth() * model.size());
    	if (firstXPoint < 0) {
    		firstXPoint = 0;
    	}
    	if (firstXPoint > model.size()) {
    		firstXPoint = model.size();
    	}
    	int yIndex;
    	if (isPeak) {
    		yIndex = model.searchIndexMaxYInRange(firstXPoint, lastXPoint);
    	} else {
    		yIndex = model.searchIndexMinYInRange(firstXPoint, lastXPoint);
    	}
    	System.out.println("minY y: " + model.getY(yIndex) + " x: " + model.getX(yIndex));
    	this.choosePeakModel.addX(model.getX(yIndex));
    	this.choosePeakModel.addY(model.getY(yIndex));
    	DataViewMaker.sharedInstance().reloadModel(choosePeakModel);
    	firstX = 0;
    	firstY = 0;
    	lastX = 0;
    	lastY = 0;
    }
    
}
