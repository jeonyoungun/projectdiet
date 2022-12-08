package com.course.project;

public class monthfoods {
    public static class food {
        String food_name;
        int food_num;
        int Caloly;
        int year;
        int month;
        int day;
        String eattime;
        public food( String food_name, int food_num, int caloly, int year, int month, int day) {
            this.food_name = food_name;
            this.food_num = food_num;
            this.Caloly = caloly;
            this.year = year;
            this.month = month;
            this.day = day;
            String syear = String.valueOf(year);
            String smonth = String.valueOf(month);
            String sday = String.valueOf(day);
            eattime = syear+"."+ smonth+"."+ sday;
        }

        public String getFoodName() {
            return food_name;
        }
        public int getCaloly() {
            return Caloly;
        }
        public int getFoodNum() {
            return food_num;
        }
        public String getEattime() {
            return eattime;
        }

    }
}
