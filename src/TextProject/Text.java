package TextProject;

public class Text {
    public static final String NEW_LINE = System.getProperty("line.separator");
    private int numberOfLines;
    private int numberOfCharacters;
    private String content;

    public Text(String text){
        this.content = text;
        this.numberOfCharacters = text.length();
        String[] lines = text.split(NEW_LINE);
        this.numberOfLines = lines.length;
    }
    public int getNumberOfLines(){
        return this.numberOfLines;
    }
    public int getNumberOfCharacters(){
        return this.numberOfCharacters;
    }
    public String getText(){
        return this.content;
    }

    public String getLine(int lineNumber) {
        if (lineNumber > numberOfLines) return null;

        else {
            String[] lines = this.content.split(NEW_LINE);
            return lines[lineNumber];
        }
    }
    public void setLine(int lineNumber, String text){
        String[] lines = this.content.split(NEW_LINE);
        lines[lineNumber] = text;
        this.content = "";
        for (String line : lines) this.content = this.content + line + "\n";
    }
    public void setLines(int lineFrom, int lineTo, String text){
        String[] lines = text.split(NEW_LINE);
        int i =0;
        for(int line = lineFrom; line <= lineTo; line++){
            setLine(line, lines[i]);
            i++;
        }
    }
}
