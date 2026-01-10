public class LogLevels {
    
    public static String message(String logLine) {
        int twoDotOccurence = logLine.indexOf(":");
        return logLine.replaceAll("^\\[.*?\\]:\\s*", "").trim();
    }

    public static String logLevel(String logLine) {
        return logLine.replaceAll("^\\[(.*?)\\]:.*$", "$1").toLowerCase();
    }

    public static String reformat(String logLine) {
    String level = logLine.replaceAll("^\\[(.*?)\\]:.*$", "$1").toLowerCase().trim();
    String message = logLine.replaceAll("^\\[.*?\\]:\\s*", "").trim();
    return message.trim() + " (" + level + ")";
}
}
