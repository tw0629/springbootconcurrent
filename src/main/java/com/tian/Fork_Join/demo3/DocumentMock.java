package com.tian.Fork_Join.demo3;

import java.util.Random;

/**
 * @author David Tian
 * @desc
 * @since 2020-06-23 11:30
 */
public class DocumentMock {
    private String words[] = {"the", "hello", "goodbye", "packet",
            "java", "thread", "pool", "random","class","main"};
    /**
     *
     * @param numLines 行数
     * @param numWords 每行单词个数
     * @param word 要查找的单词
     * @return
     */
    public String[][] generateDocument(int numLines, int numWords, String word){
        //统计要查找单词出现次数，以便和程序并行运算合并结果做对比
        int counter = 0;
        String document[][] = new String[numLines][numWords];
        Random random = new Random();
        for(int i=0;i<numLines;i++){
            for(int j=0;j<numWords;j++){
                int index = random.nextInt(words.length);
                document[i][j] = words[index];
                if(document[i][j].equals(word)){
                    counter++;
                }
            }
        }
        System.out.println("DocumentMock: The word appears "+counter+" times in the document");
        return document;
    }
}
