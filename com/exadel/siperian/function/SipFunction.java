

package com.exadel.siperian.function;


/**
 * Sip component functions.
 * 
 * @author Eugene Stherbin
 */
public class SipFunction {

    /** The Constant BR_TAG. */
    private static final String BR_TAG = "<br/>";
    private static final String E_BR_TAG = "&lt;br/&gt;";

    /**
     * Nl2br.
     * 
     * @param text the text
     * 
     * @return the string
     */
    public static final String nl2br(java.lang.String text) {
        String rst = null;
        
        if (text != null) {
            rst = text.replace("\r\n", BR_TAG);
            rst = text.replace("\n", BR_TAG);
        }

        return rst;
    }
    public static final String enl2br(java.lang.String text) {
        String rst = null;
        
        if (text != null) {
            rst = text.replace("\r\n", E_BR_TAG);
            rst = text.replace("\n", E_BR_TAG);
        }

        return rst;
    }
}