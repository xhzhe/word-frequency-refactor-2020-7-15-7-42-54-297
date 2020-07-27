import java.util.*;

public class WordFrequencyGame {

    public static final String SPACE_PATTERN = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String SPACE = " ";

    public String getResult(String inputStr) {


        if (inputStr.split(SPACE_PATTERN).length==1) {
            return inputStr + " 1";
        } else {
            try {
                List<WordInfo> wordInfoList = calculateWordFrequency(inputStr);

                wordInfoList.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());

                return generateWordFrequencyGameResult(wordInfoList);
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
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

    private String generateWordFrequencyGameResult(List<WordInfo> wordInfoList) {
        StringJoiner joiner = new StringJoiner("\n");
        for (WordInfo wordInfo : wordInfoList) {
            String resultLine = wordInfo.getValue() + SPACE +wordInfo.getWordCount();
            joiner.add(resultLine);
        }
        return joiner.toString();
    }


    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList){
            if (!map.containsKey(wordInfo.getValue())) {
                List<WordInfo> arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getValue(), arr);
            }
            else {
                map.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return map;
    }
}
