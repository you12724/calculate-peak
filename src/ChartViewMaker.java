import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.Range;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
 
public class ChartViewMaker extends JFrame implements ActionListener{
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    XYSeriesCollection data = null;
    XYSeries series = null;
    ResonantModel model;
    MyChartPanel cpanel;
    JComboBox<String> selectBox;
     
    /**
     * @param args
     */
     
    private ChartViewMaker(ResonantModel model) {
    	// タイトル名
        String title = "input csv file";
        // X軸Name
        String xAxisLabel = "Wavelength";
        // Y軸Name
        String yAxisLabel = "Transmittance";
         
        // 折れ線グラフを生成       
        JFreeChart Chart = 
                ChartFactory.createXYLineChart(// タイトル
                                             title,
                                             // X軸ラベル
                                             xAxisLabel, 
                                             // Y軸ラベル
                                             yAxisLabel,
                                             // xy - Data
                                             dataset(), 
                                             // グラフの向き
                                             PlotOrientation.VERTICAL,
                                             // 凡例
                                             true,
                                             // ツールチップ
                                             true,
                                             // URL作成
                                             false);
         
        // 背景色を設定
        Chart.setBackgroundPaint(ChartColor.WHITE);
         
        XYPlot plot = (XYPlot) Chart.getPlot();
        // 背景色
        plot.setBackgroundPaint(Color.gray); 
        // 背景色 透明度
        plot.setBackgroundAlpha(0.5f);
        // 前景色 透明度
        plot.setForegroundAlpha(0.5f);
        // 縦線の色
        plot.setDomainGridlinePaint(Color.white);
        // 横線の色
        plot.setRangeGridlinePaint(Color.white); 
        // カーソル位置で横方向の補助線をいれる
        plot.setDomainCrosshairVisible(true);
        // カーソル位置で縦方向の補助線をいれる
        plot.setRangeCrosshairVisible(true);
        // 横軸の設定
        NumberAxis xAxis = (NumberAxis)plot.getDomainAxis();
        Range xRange = new Range(model.getMinX(), model.getMaxX());
        xAxis.setRange(xRange);
        // 縦軸の設定
        NumberAxis yAxis = (NumberAxis)plot.getRangeAxis();
        Range yRange = new Range(model.getMinY() - 2, model.getMaxY() + 2);
        yAxis.setRange(yRange);
         
        // プロットをつける
        XYLineAndShapeRenderer renderer =
            new XYLineAndShapeRenderer(true, true);
        plot.setRenderer(renderer);
        renderer.setBaseShapesVisible(true);
        renderer.setBaseShapesFilled(true);
         
        // プロットのサイズ
        Stroke stroke = new BasicStroke(
            0.2f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND);
        renderer.setBaseOutlineStroke(stroke);
         
        cpanel = new MyChartPanel(Chart, model);
        getContentPane().add(cpanel, BorderLayout.CENTER);
        
        String[] peakOrDip = {"Peak", "Dip"};
        selectBox = new JComboBox<String>(peakOrDip);
        selectBox.addActionListener(this);
        JPanel selectPanel = new JPanel();
        selectPanel.add(selectBox);
        getContentPane().add(selectPanel, BorderLayout.SOUTH);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(360 + Const.VIEW_OFFSET_X, Const.VIEW_OFFSET_Y, 800, 500);
        this.setTitle("input csv file");
        this.setVisible(true);
        
        readData(model);
          
    }
     
    private XYSeriesCollection dataset() {
         
        data = new XYSeriesCollection();
        series = new XYSeries("");
        data.addSeries(series);
        return data;
        
    }
     
    public void readData(ResonantModel model) {
    	for (int i = 0; i < model.size(); i++){
    		series.add(model.getX(i), model.getY(i));
    	}
    }
     
	@Override
	public void actionPerformed(ActionEvent e) {
		if (selectBox.getSelectedIndex() == 0) {
			cpanel.isPeak = true;
		} else {
			cpanel.isPeak = false;
		}
	}
	
	static public void showChart(ResonantModel model){
		new ChartViewMaker(model);
	}
 
}
