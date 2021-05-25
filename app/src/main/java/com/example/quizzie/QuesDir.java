package com.example.quizzie;

public class QuesDir {

    //list of all questions
    public String listQues [] = {
            "How to pass data between activities in Android?",
            "What is APK?",
            "What are JSON elements in Android?",
            "Images are kept in _____?",
            "styles.xml was replaced with _____?"
    };

    //list of options corresponding to each question
    private String listOpt [][] = {
            {"Intent", "Content Provider", "Broadcast Receiver", "None Of the above"},
            {"Android Packages", "Android pack", "Android Packaging Kit", "None of the above"},
            {"int", "bool", "null", "All of the above"},
            {"Manifests", "Gradle", "Drawable", "None of the above"},
            {"themes.xml", "colors.xml", "fonts.xml", "None of the above"}
    };

    //list of answer keys
    private String correctOpt [] = {"Intent", "Android Packaging Kit", "All of the above", "Drawable", "themes.xml"};

    //fun() to get question
    public String getQues(int a){
        return listQues[a];
    }

    //fun() to get 1st option
    public String getOpt1(int a){
        return listOpt[a][0];
    }

    //fun() to get 2nd option
    public String getOpt2(int a){
        return listOpt[a][1];
    }

    //fun() to get 3rd option
    public String getOpt3(int a){
        return listOpt[a][2];
    }

    //fun() to get 4th option
    public String getOpt4(int a){
        return listOpt[a][3];
    }

    //fun() to get the correct answer
    public String getCorrectOpt(int a){
        return correctOpt[a];
    }

}
