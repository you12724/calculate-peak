import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ViewMaker extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	boolean isWin = true;
	
	File targetFile = null;
	
	public ViewMaker(boolean isWin) {
		this.isWin = isWin;
		
		JButton selectButton = new JButton("select csv file...");
		selectButton.setActionCommand("select");
		selectButton.addActionListener(this);
		
		JButton completeButton = new JButton("complete");
		completeButton.setActionCommand("complete");
		completeButton.addActionListener(this);
		
		JPanel selectPanel = new JPanel();
		selectPanel.add(selectButton);
		getContentPane().add(selectPanel, BorderLayout.CENTER);
		
		JPanel completePanel = new JPanel();
		completePanel.add(completeButton);
		getContentPane().add(completePanel, BorderLayout.PAGE_END);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 400, 300);
		this.setTitle("CSVの読み込み");
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonName = e.getActionCommand();
		
		if (buttonName.equals("select")) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		int selected = fileChooser.showOpenDialog(this);
		if (selected == JFileChooser.APPROVE_OPTION){
	        File file = fileChooser.getSelectedFile();
	        this.targetFile = file;
	      }
		} else if (buttonName.equals("complete")) {
			if (this.targetFile != null) {
				PeakSearcher seacher = new PeakSearcher(this.targetFile.getAbsolutePath());
				ResonantModel[] models = seacher.searchPeaks();
				CSVFileWriter writer = new CSVFileWriter(this.isWin);
				for (int i = 0; i < models.length; i++) {
					writer.write(models[i], "test-" + String.valueOf(i));
				}
				System.out.println("完了！！");
			} else {
				System.out.println("ファイルを選択してください。");
			}
		}
	}
}
