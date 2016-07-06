import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JFrame;
 
import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.Range;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
 
public class ChartMaker extends JFrame implements ActionListener{
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// XYSeriesCollectionクラス
    XYSeriesCollection data = null;
    // グラフのデータ
    XYSeries series = null;
     
    /**
     * @param args
     */
     
    private ChartMaker(ResonantModel model) {
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
        xAxis.setAutoRange(true);
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
         
        ChartPanel cpanel = new ChartPanel(Chart);
        getContentPane().add(cpanel, BorderLayout.CENTER);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(320, 300, 800, 500);
        this.setTitle("input csv file");
        this.setVisible(true);
        
        readData(model);
          
    }
     
    // 折れ線グラフのデータを設定
    private XYSeriesCollection dataset() {
         
        data = new XYSeriesCollection();
        // 系列を作成
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
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
	static public void showChart(ResonantModel model){
		new ChartMaker(model);
	}
 
}