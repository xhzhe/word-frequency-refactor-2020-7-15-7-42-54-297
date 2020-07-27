import java.util.*;

public class WordFrequencyGame {

    private static final String SPACE_PATTERN = "\\s+";
    private static final String CALCULATE_ERROR = "Calculate Error";
    private static final String SPACE = " ";
    public static final String NEW_LINE = "\n";

    public String getResult(String sentence) {
        try {
            List<WordInfo> wordCount = calculateWordFrequency(sentence);
            wordCount.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());
            return generateWordFrequencyGameResult(wordCount);
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private List<WordInfo> calculateWordFrequency(String sentence) {
        String[] words = sentence.split(SPACE_PATTERN);
        List<WordInfo> wordCount = new ArrayList<>();
        for (String word : new HashSet<>(Arrays.asList(words))) {
            wordCount.add(new WordInfo(word, (int) Arrays.stream(words).filter(wordInSentence -> wordInSentence.equals(word)).count()));
        }
        return wordCount;
    }

    private String generateWordFrequencyGameResult(List<WordInfo> wordCount) {
        StringJoiner result = new StringJoiner(NEW_LINE);
        for (WordInfo wordInfo : wordCount) {
            String resultLine = wordInfo.getValue() + SPACE +wordInfo.getWordCount();
            result.add(resultLine);
        }
        return result.toString();
    }

}
