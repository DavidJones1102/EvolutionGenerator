package agh.ics.oop;

public class OptionsParser {
    public static MoveDirection[] parse(String[] table) throws IllegalArgumentException{
        int length = 0;
        for (String s : table) {
            if (s.equals("f") || s.equals("b") || s.equals("r") || s.equals("l") ||
                    s.equals("forward") || s.equals("backward") ||
                    s.equals("right") || s.equals("left")) {
                length++;
            }
        }
        MoveDirection[] tab_enum = new MoveDirection[length];
        int curr_index = 0;
        for (String el : table) {
            switch (el) {
                case "f":
                case "forward":
                    tab_enum[curr_index] = MoveDirection.FORWARD;
                    curr_index++;
                    break;
                case "b":
                case "backward":
                    tab_enum[curr_index] = MoveDirection.BACKWARD;
                    curr_index++;
                    break;
                case "r":
                case "right":
                    tab_enum[curr_index] = MoveDirection.RIGHT;
                    curr_index++;
                    break;
                case "l":
                case "left":
                    tab_enum[curr_index] = MoveDirection.LEFT;
                    curr_index++;
                    break;
                default:
                    throw new IllegalArgumentException(el + " is not legal move specification");
            }
        }
        return tab_enum;
    }
}