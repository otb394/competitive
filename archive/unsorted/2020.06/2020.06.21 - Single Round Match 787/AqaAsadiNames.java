package code;

public class AqaAsadiNames {
    public String getName(String Dad, String Mom, String FirstChild, String Gender) {
        String par = (Gender.equals("Boy") ? Dad : Mom);
        String[] tokens = getTokens(par);
        if (getGender(FirstChild).equals(Gender)) {
            return tokens[0] + " " + getTokens(FirstChild)[1];
        } else {
            return tokens[1] + " " + tokens[0];
        }
    }

    private String[] getTokens(String name) {
        return name.split(" ");
    }

    private String getGender(String name) {
        return (isVowel(name.charAt(0)) ? ("Girl") : ("Boy"));
    }

    private boolean isVowel(char c) {
        switch (c) {
            case 'A':
            case'E':
            case'I':
            case'O':
            case'U':
            case'Y':return true;
            default:return false;
        }
    }
}
