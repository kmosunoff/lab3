import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Pattern;

public class Processing {
    private List<Section> tree;

    private String getVarName(String inputLine) {
        String line = getLineWithoutComments(inputLine);
        return line.substring(0, line.indexOf("=") - 1);
    }

    private String getVarValue(String inputLine) {
        String line = getLineWithoutComments(inputLine);
        return line.substring(line.indexOf("=") + 2);
    }

    private String getLineWithoutComments(String inputLine) {
        String line;
        if (inputLine.contains(";")) {
            int lastPosition = inputLine.indexOf(';') - 1;
            while (lastPosition > 0 && inputLine.charAt(lastPosition) == ' ') {
                lastPosition--;
            }
            line = inputLine.substring(0, lastPosition + 1);
        }
        else line = inputLine;
        return line;
    }

    private String getSectionName(String inputLine) {
        String line = getLineWithoutComments(inputLine);
        return line.substring(1, line.length() - 1);
    }

    private boolean isSection(String inputLine) {
        String line = getLineWithoutComments(inputLine);
        Pattern section = Pattern.compile("^\\[[a-zA-Z0-9_]+\\]$");
        return section.matcher(line).matches();
    }

    private boolean isAssignment(String inputLine) {
        String line = getLineWithoutComments(inputLine);
        Pattern assignment = Pattern.compile("[a-zA-Z0-9]+ = [.a-zA-Z0-9/_]+");
        return assignment.matcher(line).matches();
    }

    private boolean isComment(String inputLine) {
        Pattern comment = Pattern.compile(";.+");
        return comment.matcher(inputLine).matches();
    }

    private boolean isCorrect(String inputLine) {
        String line = getLineWithoutComments(inputLine);
        return isSection(line) || isAssignment(line);
    }

    Processing(String filename) throws FileSystemException,
                                        FileFormatException,
                                        IOException
                                        {
        this.tree = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            Section newSection = new Section("");
            while ((line = reader.readLine()) != null) {
                if (getLineWithoutComments(line).equals("")) {
                    continue;
                }
                if (isSection(line)) {
                    newSection = new Section(getSectionName(line));
                    tree.add(newSection);
                }
                else if (isAssignment(line)) {
                    if (!newSection.getName().equals("")) {
                        newSection.put(getVarName(line), getVarValue(line));
                    }
                    else {
                        throw new FileFormatException(line);
                    }
                }
                else {
                    throw new FileFormatException(line);
                }
            }
            reader.close();

            /*for (Section section : tree) {
                System.out.println(section.getName());
                for (String value : section.keySet()) {
                    System.out.println(value + " " + section.getValue(value));
                }
            }*/
    }
    /*Class<T> clazz*/
    public <T> Response<?> getValue(InputType inputType, String sectionName, String valueName) throws IncorrectInputTypeException,
                                                                                                    FieldNotFoundException,
                                                                                                    NumberFormatException{
            int position = -1;
            for (Section node : tree) {
                if (node.getName().equals(sectionName)) {
                    position = tree.indexOf(node);
                    break;
                }
            }
            if (position != -1) {
                if (tree.get(position).containsKey(valueName)) {
                    String value = tree.get(position).getValue(valueName);
                    switch (inputType) {
                        case Integer: {
                            return new Response<>(Integer.parseInt(value));
                        }
                        case Double: {
                            return new Response<>(Double.parseDouble(value));
                        }
                        case String: {
                            return new Response<>(value);
                        }
                        default: {
                            throw new IncorrectInputTypeException();
                        }
                    }
                }
                else {
                    throw new FieldNotFoundException("Variable " + valueName);
                }
            }
            else {
                throw new FieldNotFoundException("Section " + sectionName);
            }
    }

}
