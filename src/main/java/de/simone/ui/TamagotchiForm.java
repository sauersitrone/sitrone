package de.simone.ui;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.*;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.router.Route;
import de.simone.MainLayout;
import de.simone.UIUtils;
import de.simone.backend.Tamagotchi;
import de.simone.ui.components.FileLoaderSimple;
import jakarta.annotation.security.RolesAllowed;

@RolesAllowed({ "Sitrone.master", "Tamagotchi.edit" })
@Route(value = "Tamagotchi", layout = MainLayout.class)
public class TamagotchiForm extends TAForm<Tamagotchi> {

    private TextField name;
    private FileLoaderSimple avatar;
    private IntegerField hunger;
    private IntegerField cleanliness;
    private IntegerField weight;
    private IntegerField energy;
    private IntegerField health;
    private IntegerField level;
    private IntegerField experience;
    private IntegerField age;
    private Checkbox sleeping;
    private Checkbox alive;
    private MultiSelectComboBox<String> personality;
    private MultiSelectComboBox<String> strengths;
    private MultiSelectComboBox<String> weaknesses;

    public TamagotchiForm() {
        name = UIUtils.getTextField("Tamagotchi.name");
        avatar = new FileLoaderSimple("Tamagotchi.avatar");
        hunger = UIUtils.getIntegerField("Tamagotchi.hunger");
        cleanliness = UIUtils.getIntegerField("Tamagotchi.cleanliness");
        weight = UIUtils.getIntegerField("Tamagotchi.weight");
        energy = UIUtils.getIntegerField("Tamagotchi.energy");
        health = UIUtils.getIntegerField("Tamagotchi.health");
        level = UIUtils.getIntegerField("Tamagotchi.level");
        experience = UIUtils.getIntegerField("Tamagotchi.experience");
        age = UIUtils.getIntegerField("Tamagotchi.age");
        sleeping = UIUtils.getCheckbox("Tamagotchi.sleeping");
        alive = UIUtils.getCheckbox("Tamagotchi.alive");
        personality = UIUtils.getMultiSelectComboBox("tamagotchi.personality", "Tamagotchi.personality", false);
        strengths = UIUtils.getMultiSelectComboBox("tamagotchi.strengths", "Tamagotchi.strengths", false);
        weaknesses = UIUtils.getMultiSelectComboBox("tamagotchi.weaknesses", "Tamagotchi.weaknesses", false);

        addBodyComponets(
                name, UIUtils.getHorizontalLayout2(hunger, cleanliness),
                UIUtils.getHorizontalLayout2(weight, energy),
                UIUtils.getHorizontalLayout2(health, level),
                UIUtils.getHorizontalLayout2(experience, age),
                sleeping, alive, avatar);

        addBodyComponets("personality.separator", false, personality, strengths, weaknesses);

    }
}
