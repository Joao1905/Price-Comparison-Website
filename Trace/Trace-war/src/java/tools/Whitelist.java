package tools;

public class Whitelist {
    private String whitelist;

    public boolean Apply (String dirty_text) {
        for (int i=0; i < dirty_text.length(); i++) {
            char c_character = dirty_text.charAt(i);
            String character = String.valueOf(c_character);
            
            if (!getWhitelist().contains(character)){
                return false;
            }
        }
        return true;
    }

    public String getWhitelist() {
        return whitelist;
    }

    public void setWhitelist (String whitelist) {
        this.whitelist = whitelist;
    }
    
}
