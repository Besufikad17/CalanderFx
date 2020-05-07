package sample;

import abushakir.calander.Bahirehasab;
import abushakir.calander.ETC;
import abushakir.calander.etDateTime;
import abushakir.util.Calander_Exceptions;
import abushakir.util.Util;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Label date;
    @FXML
    private Label day1;
    @FXML
    private Label day2;
    @FXML
    private Label day3;
    @FXML
    private Label day4;
    @FXML
    private Label day5;
    @FXML
    private Label day6;
    @FXML
    private Label day7;
    @FXML
    private Label day8;
    @FXML
    private Label day9;
    @FXML
    private Label day10;
    @FXML
    private Label day11;
    @FXML
    private Label day12;
    @FXML
    private Label day13;
    @FXML
    private Label day14;
    @FXML
    private Label day15;
    @FXML
    private Label day16;
    @FXML
    private Label day17;
    @FXML
    private Label day18;
    @FXML
    private Label day19;
    @FXML
    private Label day20;
    @FXML
    private Label day21;
    @FXML
    private Label day22;
    @FXML
    private Label day23;
    @FXML
    private Label day24;
    @FXML
    private Label day25;
    @FXML
    private Label day26;
    @FXML
    private Label day27;
    @FXML
    private Label day28;
    @FXML
    private Label holiday;
    @FXML
    private Button previous;
    @FXML
    private Button next;

    String[] months = {"መስከረም", "ጥቅምት", "ኅዳር", "ታኅሳስ", "ጥር", "የካቲት", "መጋቢት", "ሚያዝያ", "ግንቦት", "ሰኔ", "ኃምሌ", "ነሐሴ", "ጷጉሜን"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Initial setup
        date.setText(getCurrentDate());
       
        String mon = date.getText().substring(0, date.getText().indexOf(" "));
        int year = Integer.parseInt(date.getText().substring(date.getText().lastIndexOf(" ")).replace(" ",""));
        if (getHolidaysMap().get(mon) != null) {
            highlightdays(getHolidaysMap().get(mon),mon,year);
        }
        //When Next Button is Clicked
        next.setOnAction(e -> {
            String newStr = update(true);
            date.setText(newStr);
            String month = date.getText().substring(0, date.getText().indexOf(" "));
            int yr = Integer.parseInt(date.getText().substring(date.getText().lastIndexOf(" ")).replace(" ",""));
            if (getHolidaysMap().get(month) != null) {
                highlightdays(getHolidaysMap().get(month),month,yr);
            }
        });
        //When Previous Button is Clicked
        previous.setOnAction(e -> {
            String newStr = update(false);
            date.setText(newStr);
            String month = date.getText().substring(0, date.getText().indexOf(" "));
            int yr = Integer.parseInt(date.getText().substring(date.getText().lastIndexOf(" ")).replace(" ",""));
            if (getHolidaysMap().get(month) != null) {
                highlightdays(getHolidaysMap().get(month),month,yr);
            }
        });
    }

    public String update(boolean isCase) {
        Label[] days = getDays();
        int puagmesize = 0;
        String month = date.getText().substring(0, date.getText().indexOf(" "));
        int year = Integer.parseInt(date.getText().substring(date.getText().lastIndexOf(" ") + 1));
        int day = Integer.parseInt(date.getText().substring(date.getText().indexOf(" ") + 1, date.getText().lastIndexOf(" ")));
        Bahirehasab bh = new Bahirehasab(year);
        if (bh.getEvange().equals("ዮሐንስ")){
            puagmesize = 6;
        }else{
            puagmesize = 5;
        }
        int month_index = Util.indexOf(month, months);
        if (month_index > months.length) {
            month_index = month_index - 13;
        }
        ETC etc = new ETC(year, month_index + 1, day);
        if (isCase) {
            int index = etc.nextMonth().getMonth();
            if (index >= months.length) {
                index = index - 13;
                year++;
            }
            if (index - 1 < 0) {
                index = index + 13;
            }
            if(index != months.length) {
                for (int i = 0; i < days.length; i++) {
                    if(days[i].getText() != null) {
                        int current_date = Integer.parseInt(days[i].getText());
                        int next_date = current_date + 28;
                        if (next_date > 30) {
                            next_date = next_date - 30;
                            days[i].setText("" + next_date);
                        } else {
                            days[i].setText("" + next_date);
                        }
                    }else{
                        days[i].setText("" + i);
                    }
                }
            }else{
                for (int i = 0; i < puagmesize; i++){
                    int current_date = Integer.parseInt(days[i].getText());
                    int next_date = current_date + 28;
                    if (next_date > 30) {
                        next_date = next_date - 30;
                        days[i].setText("" + next_date);
                    } else {
                        days[i].setText("" + next_date);
                    }
                }
                for (int i = puagmesize; i < days.length; i++){
                    days[i].setText(null);
                }
            }
            return months[index - 1] + " " + day + " " + year;
        } else {
            int index = etc.previousMonth().getMonth();
            if (index >= months.length) {
                index = index - 13;
                year--;
            }
            if (index - 1 < 0) {
                index = index + 13;
            }
            if(index != months.length){
                for (int i = 0; i < days.length; i++) {
                    if(days[i].getText() != null) {
                        int current_date = Integer.parseInt(days[i].getText());
                        int previous_date = current_date - 28;
                        if (previous_date > 30) {
                            previous_date = previous_date - 30;
                            days[i].setText("" + previous_date);
                        } else if (previous_date < 0) {
                            previous_date = previous_date + 30;
                            days[i].setText("" + previous_date);
                        } else {
                            days[i].setText("" + previous_date);
                        }
                    }else {
                        days[i].setText("" + i);
                    }
                }
            }else {
                for (int i = 0; i < puagmesize; i++){
                    int current_date = Integer.parseInt(days[i].getText());
                    int next_date = current_date - 28;
                    if (next_date < 0) {
                        next_date = next_date + 30;
                        days[i].setText("" + next_date);
                    } else {
                        days[i].setText("" + next_date);
                    }
                }
                for (int i = puagmesize; i < days.length; i++){
                    days[i].setText(null);
                }
            }
            return months[index - 1] + " " + day + " " + year;
        }
    }

    public static Multimap<String, Integer> getHolidaysMap() {
        Multimap<String, Integer> holidays = ArrayListMultimap.create();
        String[] keys = {"መስከረም", "መስከረም", "ጥቅምት", "ታኅሳስ", "ጥር", "የካቲት", "ሚያዝያ", "ሚያዝያ", "ግንቦት", "ኃምሌ"};
        Bahirehasab bh = new Bahirehasab(2012);
        try {
            int seklet = Integer.parseInt(bh.getSingleBealOrTsom("ስቅለት").get("date"));
            holidays.put(bh.getSingleBealOrTsom("ስቅለት").get("month"), seklet);
            int tensae = Integer.parseInt(bh.getSingleBealOrTsom("ትንሳኤ").get("date"));
            holidays.put(bh.getSingleBealOrTsom("ስቅለት").get("month"), tensae);
        } catch (Calander_Exceptions.BealNameException e) {
            e.printStackTrace();
        }

        int[] values = {1, 17, 29, 29, 11, 23, 23, 27, 20, 23};
        for (int i = 0; i < keys.length; i++) {
            holidays.put(keys[i], values[i]);
        }
        return holidays;
    }

    private void highlightdays(Collection<Integer> day,String month,int year) {
        Label[] days = getDays();
        etDateTime now = etDateTime().now();
        int current_day = now.getDay();
        for (Label l : days) {
            l.setStyle(null);
        }
        for (Integer d : day) {
            for (int i = 0; i < days.length; i++) {
                if (days[i].getText().equals(String.valueOf(d))) {
                    days[i].setStyle("-fx-background-color: #1E90FF;\n" +
                            "-fx-text-fill: white;\n" +
                            "-fx-font-weight: bold;\n" +
                            "-fx-border-radius:3px;\n" +
                            "-fx-background-radius: 3px;");
                    int finalCount = Integer.parseInt(days[i].getText());
                    days[i].setOnMouseClicked(mouseEvent -> display(finalCount,month,year));
                }else if(days[i].getText().equals(String.valueOf(current_day))){
                    days[i].setStyle("-fx-background-color: white;\n" +
                            "-fx-text-fill: #1E90FF;\n" +
                            "-fx-borde-color:#1E90FF;\n");
                }

            }
        }
    }
    public static HashMap<String,String> getholidays(String month,int year){
        HashMap<String,String> holidays = new HashMap<>();
        Bahirehasab bh = new Bahirehasab(year);
        try {
            String seklet = bh.getSingleBealOrTsom("ስቅለት").get("date");
            holidays.put(month +"-" +  seklet,"ስቅለት");
            String tensae = bh.getSingleBealOrTsom("ትንሳኤ").get("date");
            holidays.put(month + "-" + tensae,"ትንሳኤ");
        } catch (Calander_Exceptions.BealNameException e) {
            e.printStackTrace();
        }
        String[] keys = {"መስከረም-1","መስከረም-17","ጥቅምት-29","ታኅሳስ-29","ጥር-11","የካቲት-23","ሚያዝያ-23","ሚያዝያ-27","ግንቦት-20","ኃምሌ-23"};
        String[] values = {"አዲስ አመት","መስቀል","መውሊድ","ገና","ጥምቀት","አደዋ","የሰራተኞች ቀን","የአርበኞች ቀን","ግንቦት 20","ኢደ አል አደሃ"};
        for (int i = 0;i<keys.length;i++){
            holidays.put(keys[i],values[i]);
        }
        return holidays;
    }
    private Label[] getDays() {
        return new Label[]{day1, day2, day3, day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15, day16, day17,
                day18, day19, day20, day21, day22, day23, day24, day25, day26, day27, day28
        };
    }

    private String getCurrentDate(){
        etDateTime etDateTime = new etDateTime();
        etDateTime = etDateTime.now();
        return months[etDateTime.getMonth()-1] + " " + etDateTime.getDay() + " " + etDateTime.getYear();
    }

    public  void display(int count,String month,int year){
        HashMap<String,String> holidays = getholidays(month,year);
        String key = month + "-" + count;
        holiday.setText(holidays.get(key));
    }


}
