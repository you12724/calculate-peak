import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DataViewMaker extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5231562520018975402L;
	
	private static DataViewMaker viewMaker = new DataViewMaker();
	private JLabel dataLabel;
	private ResonantModel model;
	private JTextField fileNameField;
	
	private DataViewMaker() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(10, 460, 350, 390);
		this.setTitle("選択したピークのデータ");
		this.setVisible(true);
		
		dataLabel = new JLabel();
		JPanel dataLabelPanel = new JPanel();
		dataLabelPanel.add(dataLabel);
		getContentPane().add(dataLabelPanel, BorderLayout.NORTH);
		
		fileNameField = new JTextField("output", 7);
		JButton clearButton = new JButton("clear");
		clearButton.setActionCommand("clear");
		clearButton.addActionListener(this);
		JButton outputButton = new JButton("output");
		outputButton.setActionCommand("output");
		outputButton.addActionListener(this);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(fileNameField);
		buttonPanel.add(clearButton);
		buttonPanel.add(outputButton);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
	}
	
	static public DataViewMaker sharedInstance(){
		return viewMaker;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("clear")) {
			this.model = null;
			this.dataLabel.setText("");
		} else if (e.getActionCommand().equals("output")) {
			CSVFileWriter writer = new CSVFileWriter();
			writer.write(model, fileNameField.getText());
			String text = dataLabel.getText() + "<br>complete!!";
			dataLabel.setText(text);
		}
	}
	
	public void reloadModel(ResonantModel model) {
		this.model = model;
		String text = "<html>";
		for (int i = 0; i < model.size(); i++){
			text += "x: " + model.getX(i) + ", y: " + model.getY(i) + "<br>";
		}
		dataLabel.setText(text);
	}

}
