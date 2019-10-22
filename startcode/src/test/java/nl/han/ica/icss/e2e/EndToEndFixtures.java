package nl.han.ica.icss.e2e;

public class EndToEndFixtures {

    public static String level0() {
        return "p {\n" +
                "\tbackground-color: #ffffff;\n" +
                "\twidth: 500px;\n" +
                "}\n" +
                "\n" +
                "a {\n" +
                "\tcolor: #ff0000;\n" +
                "}\n" +
                "\n" +
                "#menu {\n" +
                "\twidth: 520px;\n" +
                "}\n" +
                "\n" +
                ".menu {\n" +
                "\tcolor: #000000;\n" +
                "}\n\n";
    }

    public static String level1() {
        return "p {\n" +
                "\tbackground-color: #ffffff;\n" +
                "\twidth: 500px;\n" +
                "}\n" +
                "\n" +
                "a {\n" +
                "\tcolor: #ff0000;\n" +
                "}\n" +
                "\n" +
                "#menu {\n" +
                "\twidth: 520px;\n" +
                "}\n" +
                "\n" +
                ".menu {\n" +
                "\tcolor: #000000;\n" +
                "}\n\n";
    }

    public static String level2() {
        return "p {\n" +
                "\tbackground-color: #ffffff;\n" +
                "\twidth: 500px;\n" +
                "}\n" +
                "\n" +
                "a {\n" +
                "\tcolor: #ff0000;\n" +
                "}\n" +
                "\n" +
                "#menu {\n" +
                "\twidth: 520px;\n" +
                "}\n" +
                "\n" +
                ".menu {\n" +
                "\tcolor: #000000;\n" +
                "}\n\n";
    }

    public static String level3() {
        return "p {\n" +
                "\tbackground-color: #ffffff;\n" +
                "\twidth: 500px;\n" +
                "\tcolor: #124532;\n" +
                "}\n" +
                "\n" +
                "a {\n" +
                "\tcolor: #ff0000;\n" +
                "}\n" +
                "\n" +
                "#menu {\n" +
                "\twidth: 520px;\n" +
                "}\n" +
                "\n" +
                ".menu {\n" +
                "\tcolor: #000000;\n" +
                "\tbackground-color: #ff0000;\n" +
                "}\n\n";
    }

    public static String multiSelector() {
        return "p, html, #id, .class, a, #anotherid {\n" +
                "\tbackground-color: #ffffff;\n" +
                "\twidth: 500px;\n" +
                "}\n" +
                "\n" +
                "a {\n" +
                "\tcolor: #ff0000;\n" +
                "}\n" +
                "\n";
    }

    public static String compositeSelector() {
        return "div ~ p > a, html + #id, .class:active, p::first-line {\n" +
                "\tbackground-color: #ffffff;\n" +
                "\twidth: 500px;\n" +
                "}\n" +
                "\n" +
                "a ~ p {\n" +
                "\tcolor: #ff0000;\n" +
                "}\n" +
                "\n";
    }

    public static String ifElse() {
        return "p {\n" +
                "\tcolor: #ffffff;\n" +
                "\tbackground-color: #222222;\n" +
                "}\n" +
                "\n" +
                "a {\n" +
                "\tcolor: #222222;\n" +
                "}\n\n";
    }

    public static String directIdChild() {
        return "#id {\n" +
                "\tcolor: #123456;\n" +
                "}\n" +
                "\n" +
                "html > .class {\n" +
                "\tcolor: #123456;\n" +
                "}\n" +
                "\n";
    }
}
