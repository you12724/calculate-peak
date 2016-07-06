import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ViewMaker extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ResonantModel inputModel;
	JTextField textField;
	JLabel feedBackLabel;
	
	private ViewMaker() {
		
		JButton selectButton = new JButton("select csv file...");
		selectButton.setActionCommand("select");
		selectButton.addActionListener(this);
		
		textField = new JTextField("output", 10);
		JLabel label = new JLabel("output file's name");
		
		JButton completeButton = new JButton("complete");
		completeButton.setActionCommand("complete");
		completeButton.addActionListener(this);
		
		feedBackLabel = new JLabel("Select csv file.");
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				
		panel.add(selectButton);
		panel.add(label);
		panel.add(textField);
		panel.add(completeButton);
		panel.add(feedBackLabel);
		getContentPane().add(panel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(10, 300, 300, 150);
		this.setTitle("CSVの読み込み");
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonName = e.getActionCommand();
		
		if (buttonName.equals("select")) {
			setSelectFile();
		} else if (buttonName.equals("complete")) {
			completeProcess();
		}
	}
	
	private void setSelectFile(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		int selected = fileChooser.showOpenDialog(this);
		if (selected == JFileChooser.APPROVE_OPTION){
	        File file = fileChooser.getSelectedFile();
	        this.inputModel = CSVFileReader.read(file.getAbsolutePath());
	        ChartMaker.showChart(this.inputModel);
	      }
	}
	
	private void completeProcess() {
		if (this.inputModel != null) {
			PeakSearcher seacher = new PeakSearcher(this.inputModel);
			ResonantModel[] models = seacher.searchPeaks();
			CSVFileWriter writer = new CSVFileWriter();
			String inputText = textField.getText();
			for (int i = 0; i < models.length; i++) {
				writer.write(models[i], inputText + "-" + String.valueOf(i));
			}
			feedBackLabel.setText("Complete!!");
		} else {
			feedBackLabel.setText("Error: You must select csv file.");
		}
	}
	
	static public void showView() {
		new ViewMaker();
	}
}
