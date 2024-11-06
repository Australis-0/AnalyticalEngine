package AnalyticalEngine.Framework;

import AnalyticalEngine.Debugger.console;

import java.util.HashMap;
import java.util.Map;

public class KeyboardHandler {
    //Declare global instance variables
    public int caret_position = 0;
    public String current_string = "";
    public String id = "";
    public String last_key_pressed = "";

    boolean alt_pressed = false;
    boolean caps_lock = false;
    boolean ctrl_pressed = false;
    boolean shift_pressed = false;

    boolean right_alt_pressed = false;
    boolean right_ctrl_pressed = false;
    boolean right_shift_pressed = false;

    Map<Integer, String> keycodes_obj = new HashMap<>();

    String[] control_keycodes_obj = {"Alt", "RightAlt", "Shift", "RightShift", "Tab", "Win", "Enter", "PageUp", "PageDown", "Esc", "Delete", "CapsLock", "Ctrl", "RightCtrl", "Up", "Down", "Left", "Right", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10", "F11", "F12"};

    public KeyboardHandler () {
        //Control characters
        keycodes_obj.put(131, "F1");
        keycodes_obj.put(132, "F2");
        keycodes_obj.put(133, "F3");
        keycodes_obj.put(134, "F4");
        keycodes_obj.put(135, "F5");
        keycodes_obj.put(136, "F6");
        keycodes_obj.put(137, "F7");
        keycodes_obj.put(138, "F8");
        keycodes_obj.put(139, "F9");
        keycodes_obj.put(140, "F10");
        keycodes_obj.put(141, "F11");
        keycodes_obj.put(142, "F12");

        keycodes_obj.put(57, "Alt");
        keycodes_obj.put(58, "RightAlt");
        keycodes_obj.put(59, "Shift");
        keycodes_obj.put(60, "RightShift");
        keycodes_obj.put(61, "Tab");
        keycodes_obj.put(62, " ");
        keycodes_obj.put(63, "Win");
        keycodes_obj.put(66, "Enter");
        keycodes_obj.put(67, "Backspace");
        keycodes_obj.put(92, "PageUp");
        keycodes_obj.put(93, "PageDown");
        keycodes_obj.put(111, "Esc");
        keycodes_obj.put(112, "Delete");
        keycodes_obj.put(115, "CapsLock");
        keycodes_obj.put(129, "Ctrl");
        keycodes_obj.put(129, "RightCtrl");

        keycodes_obj.put(19, "Up");
        keycodes_obj.put(20, "Down");
        keycodes_obj.put(21, "Left");
        keycodes_obj.put(22, "Right");

        //Regular keys - no shift modifier
        keycodes_obj.put(7, "0");
        keycodes_obj.put(8, "1");
        keycodes_obj.put(9, "2");
        keycodes_obj.put(10, "3");
        keycodes_obj.put(11, "4");
        keycodes_obj.put(12, "5");
        keycodes_obj.put(13, "6");
        keycodes_obj.put(14, "7");
        keycodes_obj.put(15, "8");
        keycodes_obj.put(16, "9");

        keycodes_obj.put(29, "a");
        keycodes_obj.put(30, "b");
        keycodes_obj.put(31, "c");
        keycodes_obj.put(32, "d");
        keycodes_obj.put(33, "e");
        keycodes_obj.put(34, "f");
        keycodes_obj.put(35, "g");
        keycodes_obj.put(36, "h");
        keycodes_obj.put(37, "i");
        keycodes_obj.put(38, "j");
        keycodes_obj.put(39, "k");
        keycodes_obj.put(40, "l");
        keycodes_obj.put(41, "m");
        keycodes_obj.put(42, "n");
        keycodes_obj.put(43, "o");
        keycodes_obj.put(44, "p");
        keycodes_obj.put(45, "q");
        keycodes_obj.put(46, "r");
        keycodes_obj.put(47, "s");
        keycodes_obj.put(48, "t");
        keycodes_obj.put(49, "u");
        keycodes_obj.put(50, "v");
        keycodes_obj.put(51, "w");
        keycodes_obj.put(52, "x");
        keycodes_obj.put(53, "y");
        keycodes_obj.put(54, "z");

        keycodes_obj.put(55, ",");
        keycodes_obj.put(56, ".");
        keycodes_obj.put(76, "/");

        keycodes_obj.put(68, "`");
        keycodes_obj.put(69, "-");
        keycodes_obj.put(70, "=");
        keycodes_obj.put(71, "[");
        keycodes_obj.put(72, "]");
        keycodes_obj.put(73, "#");
        keycodes_obj.put(74, ";");
        keycodes_obj.put(75, "'");
    }

    public void clearString () {
        caret_position = 0;
        current_string = "";
        clearStringModifiers();
    }
    public void clearStringModifiers () {
        alt_pressed = false;
        caps_lock = false;
        ctrl_pressed = false;
        shift_pressed = false;

        right_alt_pressed = false;
        right_ctrl_pressed = false;
        right_shift_pressed = false;
    }
    public String getDisplayString () {
        return current_string.substring(0, caret_position) + "|" + current_string.substring(caret_position);
    }
    public String getKey (int arg0_keycode) {
        //Convert from parameters
        int keycode = arg0_keycode;

        //Declare local instance variables
        String current_key = "";
        String raw_key_press = keycodes_obj.get(keycode);
        boolean is_control = false;

        //Make sure to return the correct key
        if (raw_key_press != null) {
            //Control handling
            for (String local_control_keycode : control_keycodes_obj)
                if (local_control_keycode.equals(raw_key_press)) {
                    if (local_control_keycode.equals("Alt")) alt_pressed = true;
                    if (local_control_keycode.equals("CapsLock")) caps_lock = (!caps_lock);
                    if (local_control_keycode.equals("Ctrl")) ctrl_pressed = true;
                    if (local_control_keycode.equals("Shift")) shift_pressed = true;

                    if (local_control_keycode.equals("RightAlt")) right_alt_pressed = true;
                    if (local_control_keycode.equals("RightCtrl")) right_ctrl_pressed = true;
                    if (local_control_keycode.equals("RightShift")) right_shift_pressed = true;

                    //Arrow keys for adjusting caret position
                    if (local_control_keycode.equals("Left"))
                        if (caret_position > 0) caret_position--;
                    if (local_control_keycode.equals("Right"))
                        if (caret_position < current_string.length()) caret_position++;

                    is_control = true;
                }

            //Backspace handling
            if (raw_key_press.equals("Backspace")) current_key = "Backspace";
            if (is_control) { current_key = ""; return current_key; } //Guard-clause for remaining control keys

            //Non-control handling
            boolean uppercase = (shift_pressed || right_shift_pressed || caps_lock);

            if (uppercase) {
                //Number bar
                if (raw_key_press.equals("`")) current_key = "¬";
                if (raw_key_press.equals("1")) current_key = "!";
                if (raw_key_press.equals("2")) current_key = "\"";
                if (raw_key_press.equals("3")) current_key = "£";
                if (raw_key_press.equals("4")) current_key = "$";
                if (raw_key_press.equals("5")) current_key = "%";
                if (raw_key_press.equals("6")) current_key = "^";
                if (raw_key_press.equals("7")) current_key = "&";
                if (raw_key_press.equals("8")) current_key = "*";
                if (raw_key_press.equals("9")) current_key = "(";
                if (raw_key_press.equals("0")) current_key = ")";

                //Right-side punctuation
                if (raw_key_press.equals("-")) current_key = "_";
                if (raw_key_press.equals("=")) current_key = "+";
                if (raw_key_press.equals("[")) current_key = "{";
                if (raw_key_press.equals("]")) current_key = "}";
                if (raw_key_press.equals("#")) current_key = "~";
                if (raw_key_press.equals(";")) current_key = ":";
                if (raw_key_press.equals("'")) current_key = "@";
                if (raw_key_press.equals(",")) current_key = "<";
                if (raw_key_press.equals(".")) current_key = ">";
                if (raw_key_press.equals("/")) current_key = "?";

                //Uppercase letters
                if (raw_key_press.equals("a")) current_key = "A";
                if (raw_key_press.equals("b")) current_key = "B";
                if (raw_key_press.equals("c")) current_key = "C";
                if (raw_key_press.equals("d")) current_key = "D";
                if (raw_key_press.equals("e")) current_key = "E";
                if (raw_key_press.equals("f")) current_key = "F";
                if (raw_key_press.equals("g")) current_key = "G";
                if (raw_key_press.equals("h")) current_key = "H";
                if (raw_key_press.equals("i")) current_key = "I";
                if (raw_key_press.equals("j")) current_key = "J";
                if (raw_key_press.equals("k")) current_key = "K";
                if (raw_key_press.equals("l")) current_key = "L";
                if (raw_key_press.equals("m")) current_key = "M";
                if (raw_key_press.equals("n")) current_key = "N";
                if (raw_key_press.equals("o")) current_key = "O";
                if (raw_key_press.equals("p")) current_key = "P";
                if (raw_key_press.equals("q")) current_key = "Q";
                if (raw_key_press.equals("r")) current_key = "R";
                if (raw_key_press.equals("s")) current_key = "S";
                if (raw_key_press.equals("t")) current_key = "T";
                if (raw_key_press.equals("u")) current_key = "U";
                if (raw_key_press.equals("v")) current_key = "V";
                if (raw_key_press.equals("w")) current_key = "W";
                if (raw_key_press.equals("x")) current_key = "X";
                if (raw_key_press.equals("y")) current_key = "Y";
                if (raw_key_press.equals("z")) current_key = "Z";

                //Reset uppercase
                shift_pressed = false;
                right_shift_pressed = false;
            } else {
                current_key = raw_key_press;
            }
        }

        //Return statement
        return current_key;
    }
    public String getString () {
        return current_string.replaceAll("\\p{C}", "");
    }
    public void loadString (String arg0_new_string) {
        //Convert from parameters
        String new_string = arg0_new_string;

        //Change current state
        current_string = new_string;
        caret_position = new_string.length() - 1;
    }
    public void pressKey (int arg0_keycode) {
        //Convert from parameters
        int keycode = arg0_keycode;

        //Declare local instance variables
        String current_key = getKey(keycode);

        //Handle current_key
        if (current_key.length() > 0)
            if (current_key.equals("Backspace")) {
                if (current_string.length() > 0) {
                    try {
                        current_string = current_string.substring(0, caret_position - 1) + current_string.substring(caret_position);
                    } catch (Exception e) {
                        console.log(e);
                    }
                    //Adjust caret backwards
                    caret_position--;
                }
            } else {
                current_string = current_string.substring(0, caret_position) + current_key + current_string.substring(caret_position);
                //Adjust caret forwards
                caret_position++;
            }
        //Caret checks
        if (caret_position > current_string.length()) caret_position = current_string.length();
        if (caret_position < 0) caret_position = 0;

        last_key_pressed = current_key;
    }
}