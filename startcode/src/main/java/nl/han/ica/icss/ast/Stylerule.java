package nl.han.ica.icss.ast;

import java.util.ArrayList;
import java.util.Objects;

public class Stylerule extends ASTNode {

    public ArrayList<Selector> selectors = new ArrayList<>();
    public ArrayList<ASTNode> body = new ArrayList<>();

    public Stylerule() {
    }

    public Stylerule(ArrayList<Selector> selectors, ArrayList<ASTNode> body) {

        this.selectors = selectors;
        this.body = body;
    }

    @Override
    public String getNodeLabel() {
        return "Stylerule";
    }

    @Override
    public ArrayList<ASTNode> getChildren() {
        ArrayList<ASTNode> children = new ArrayList<>();
        children.addAll(selectors);
        children.addAll(body);

        return children;
    }

    @Override
    public ASTNode addChild(ASTNode child) {
        if (child instanceof Selector)
            selectors.add((Selector) child);
        else
            body.add(child);

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Stylerule stylerule = (Stylerule) o;
        return Objects.equals(selectors, stylerule.selectors) &&
                Objects.equals(body, stylerule.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(selectors, body);
    }

    // Added method
    @Override
    public ASTNode removeChild(ASTNode child) {
        if (child instanceof Selector) {
            selectors.remove(child);
        } else {
            body.remove(child);
        }
        return this;
    }

    @Override
    public String getCSSString() {
        StringBuilder stringBuilder = new StringBuilder();

        addSelectorsCSSString(stringBuilder);

        stringBuilder.append(" {").append(System.lineSeparator()).append("\t");
        addBodyCSSString(stringBuilder);
        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    private void addBodyCSSString(StringBuilder stringBuilder) {
        for (int i = 0; i < body.size(); i++) {
            stringBuilder.append(body.get(i).getCSSString()).append(System.lineSeparator());
            if (i < body.size() - 1)
                stringBuilder.append("\t");
        }
    }

    private void addSelectorsCSSString(StringBuilder stringBuilder) {
        for (int i = 0; i < selectors.size(); i++) {
            stringBuilder.append(selectors.get(i).getCSSString());
            if (i < selectors.size() - 1)
                stringBuilder.append(", ");
        }
    }
}
