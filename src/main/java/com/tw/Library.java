package com.tw;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.*;
import java.util.stream.Collectors;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class Library {
    Scanner scanner = new Scanner(System.in);
    List<Student> students = new LinkedList<>();
    public boolean someLibraryMethod() {

        show1();
        return true;

    }

    public void showMain(){
        System.out.print("1. 添加学生\n 2. 生成成绩单\n 3. 退出请输入你的选择（1～3）：");
        int num = scanner.nextInt();
        if (num == 1) show1();
        else if (num == 2) show2();
        else System.exit(0);
    }

    public void show1(){
        System.out.print("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：\n");
        try{
            String information = scanner.next();
            String[] array = information.split("，");
            Student student = new Student((array[0]).trim(), array[1].trim());
            int length = array.length;
            for (int i = 2;i < length;i++){
                String[] score = array[i].split(":");
                student.addScore(score[0].trim(),Double.valueOf(score[1].trim()));
            }

            students.add(student);
            System.out.print(String.format("学生%s的成绩被添加\n", student.getName()));

        }catch (Exception e){
            System.out.print("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：\n");
            show1();
        }

    }

    public void show2(){
        System.out.print("请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：\n");
        try{
            String information = scanner.next();
            String[] array = information.split("，");
            System.out.print("成绩单\n姓名|数学|语文|英语|编程|平均分|总分\n========================\n");
            Arrays.stream(array).map(s -> s.trim()).map(id -> getStudentById(id)).filter(student -> student != null).forEach(student -> {
                Map<String, Double> map = student.getScores();
                System.out.print("|" + map.get("数学"));
                System.out.print("|" + map.get("语文"));
                System.out.print("|" + map.get("英语"));
                System.out.print("|" + map.get("编程"));
                double sum = map.values().stream().mapToDouble(d -> d).sum();
                double average = sum / map.size();
                System.out.print("|" + sum + "|" + average);
            });

            System.out.print(String.format("========================\n全班总分平均数：%.2f\n全班总分中位数：%.2f", getAllAverage(), getAllMedian()));

        }catch (Exception e){
            System.out.print("请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：\n");
            show2();
        }
    }

    public double getAllAverage(){
        return students.stream().mapToDouble(student -> student.getScores().values().stream().mapToDouble(value -> value).sum()).summaryStatistics().getAverage();
    }

    public double getAllMedian(){

        List<Double> allScore = students.stream().flatMap(student -> student.getScores().values().stream().map(value -> value)).sorted().collect(Collectors.toList());
        int size = allScore.size();
        if(size % 2 == 0){
            return (allScore.get(size / 2) + allScore.get(size / 2 - 1)) / 2;
        }
        else {
            return allScore.get(size / 2);
        }

    }

    public Student getStudentById(String id){
        for (Student student : students){
            if (student.getId().equals(id)) return student;
        }
        return null;
    }
}
