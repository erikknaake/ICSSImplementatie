package nl.han.ica.icss.ast;

public abstract class Literal extends Expression {
    @Override
    public Literal eval() {
        return this;
    }

    public abstract String getValue();
}
