public class Filename {
    private String adress;
    private String adressSeparator;
    private String extensionSeparator;

    public Filename(String str, String sep, String ext) {
        adress = str;
        adressSeparator = sep;
        extensionSeparator = ext;
    }

    public String extension() {
        int dot = adress.lastIndexOf(extensionSeparator);
        return adress.substring(dot + 1);
    }

    // gets filename without extension
    public String filename() {
        if (adress.startsWith("http://www.")){
            adress = adress.replaceFirst("http://www.", "");
        }
        if (adress.startsWith("https://www.")){
            adress = adress.replaceFirst("https://www.", "");
        }
        int dot = adress.indexOf(extensionSeparator);
        int sep = adress.lastIndexOf(adressSeparator);
        return adress.substring(sep + 1, dot) + ".txt";
    }
}