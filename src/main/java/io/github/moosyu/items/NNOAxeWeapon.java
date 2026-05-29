package io.github.moosyu.items;

public class NNOAxeWeapon extends NNOItem {
    public NNOAxeWeapon(Properties properties) {
        super(applyDefaults(properties), "Axe");
    }

    private static Properties applyDefaults(Properties properties) {return properties.stacksTo(1);}
}