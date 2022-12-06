package algorithms.blind_signature;

public class Bulletin {
    private final String[] possibleAnswer;
    private final String fullQuestion;

    public Bulletin(String fullQuestion) {
        possibleAnswer = new String[]{"Yes", "No", "Refrain"};
        this.fullQuestion = fullQuestion;
    }

    public String[] getPossibleAnswer() {
        return possibleAnswer;
    }

    public String getFullQuestion() {
        return fullQuestion;
    }
}
