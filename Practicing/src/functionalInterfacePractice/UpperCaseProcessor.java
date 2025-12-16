package functionalInterfacePractice;

public class UpperCaseProcessor implements StringProcessor {

    @Override
    public String process(String input){
        return input.toUpperCase();
    }


}
