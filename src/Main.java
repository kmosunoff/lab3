import java.io.IOException;
import java.nio.file.FileSystemException;

public class Main {
    public static void main(String[] args) {
        try {
            Processing processing = new Processing("input.ini");

            System.out.println(processing.getValue(InputType.String, "NCMD", "SampleRate"));
        }
        catch (FileSystemException e) {
            System.out.println("FileSystemException");
        }
        catch (FileFormatException e) {
            System.out.println("FileFormatException " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("IOException");
        }
        catch (IncorrectInputTypeException | FieldNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (NumberFormatException e) {
            System.out.println("Customizing error " + e.getMessage());
        }
    }
}
