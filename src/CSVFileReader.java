import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVFileReader {

    public static ResonantModel read(String path) {

    	ResonantModel model = new ResonantModel();

        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] str;
                str = line.split(",");
                double x = Double.parseDouble(str[0]);
                double y = Double.parseDouble(str[str.length - 1]);
                model.addX(x);
                model.addY(y);

            }
            br.close();

            return model;

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return model;
    }
}