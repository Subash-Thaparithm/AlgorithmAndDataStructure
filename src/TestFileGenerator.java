import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestFileGenerator {
    public static void main(String[] args) throws Exception {
        String fileToCopy = args[0];
        String targetDirectory = args[1];
        boolean appendTimeStamp = Boolean.parseBoolean(args[2]);
        int delaySeconds = Integer.parseInt(args[3]);
        int iterations = Integer.parseInt(args[4]);
        System.out.println("Utility called with fileToCopy = " + fileToCopy + ", targetDirectory = " + targetDirectory + ", appendTimeStamp = " + appendTimeStamp + ", delaySeconds = " + delaySeconds + ", iterations = " + iterations);

        for(int i = 0; i < iterations; ++i) {
            String targetFileName = (new File(fileToCopy)).getName();
            if (appendTimeStamp) {
                targetFileName = targetFileName + "." + System.nanoTime();
            }

            System.out.println("Copying file to " + targetFileName + ", iteration " + i + " of " + iterations);
            Files.copy(Paths.get(fileToCopy), Paths.get(targetDirectory + File.separator + targetFileName));
            Thread.sleep((long)(delaySeconds * 1000));
        }

    }
}

