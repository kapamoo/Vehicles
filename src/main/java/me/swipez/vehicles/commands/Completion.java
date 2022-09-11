package me.swipez.vehicles.commands;

import java.util.ArrayList;
import java.util.List;

public class Completion {

    private List<String> options = new ArrayList<>();

    public void add(String string){
        options.add(string);
    }

    public List<String> getAvailable(String starting){
        List<String> available = new ArrayList<>(options);
        for (String option : options){
            if (!option.startsWith(starting)){
                available.remove(option);
            }
        }
        return available;
    }
}
