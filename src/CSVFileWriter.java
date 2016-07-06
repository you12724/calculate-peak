import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CSVFileWriter {
	
	String separator = "/";
	
	public CSVFileWriter() {
		File resultDir = new File(".." + separator + "result");
		if (!resultDir.exists()) {
			resultDir.mkdir();
		}
	}

    public void write(ResonantModel model, String name) {
    	
        try {
            FileWriter fw = new FileWriter(".." + separator + "result" + separator + name + ".csv", false);
            PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

            for (int i = 0; i < model.size(); i++) {
            	pw.print(model.getX(i));
            	pw.print(",");
            	pw.println(model.getY(i));
            }

            pw.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void writeWithOriginal(ResonantModel original, ResonantModel peak, String name) {
        try {
            FileWriter fw = new FileWriter(".." + separator + "result" + separator + name + ".csv", false);
            PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

            for (int i = 0; i < original.size(); i++) {
            	pw.print(original.getX(i));
            	pw.print(",");
            	pw.print(original.getY(i));
            	pw.print(",");
            	int index = peak.contain(original.getX(i));
            	if (index != -1){
            		pw.println(peak.getY(index));
            	} else {
            		pw.println("");
            	}
            }

            pw.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void write(ResonantModel[] models, String name) {

        try {
            FileWriter fw = new FileWriter(".." + separator + "result" + separator + name + ".csv", false);
            PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

            for (int i = 0; i < models[0].size(); i++) {

            	pw.print(models[0].getX(i));
            	pw.print(",");

            	for (int j = 0; j < models.length; j++) {

            		if(i ==1) {
            			System.out.println(models[j].getY(i));
            		}

            		pw.print(models[j].getY(i));
            		pw.print(",");

            	}

            	pw.println("");

            }

            pw.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}