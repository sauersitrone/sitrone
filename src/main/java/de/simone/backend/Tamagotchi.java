package de.simone.backend;

import java.time.*;
import java.util.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Tamagotchies")
public class Tamagotchi extends TAEntity {

    public static final String ASLEEP = "😴";
    public static final String ANGRY = "😠";
    public static final String HAPPY = "😄";
    public static final String PEACEFUL = "😇";
    public static final String FRUSTRATED = "😖";
    public static final String SMILE = "🙂";
    public static final String TIRED = "😪";
    public static final String PLAYFUL = "😉";
    public static final String CELEBRATION = "🥳";
    public static final String SAD = "😥";

    private static final String[] EMOTIONS = {
            "😍Love, extreme joy",
            "😊Happy, content",
            "🙂Mild happiness",
            "😏Smug, playful sarcasm",
            "😐Neutral, unimpressed",
            "😒Annoyed, displeased",
            "😕Confused, uneasy",
            "😔Sad, regretful",
            "😟Worried, anxious",
            "😠Angry, frustrated",
            "😢Crying, sorrow",
            "😭Deep grief, despair",
            "😨Fear, shock",
            "😱Terror, panic",
            "💀Death, hopelessness"
    };

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_NAMES)
    public String name;

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_NAMES)
    public String personality;

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_NAMES)
    public String strengths;

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_NAMES)
    public String weaknesses;

    // @NotNull
    public String avatar;

    public int hunger = 10; // 0-100. 0 no hunger. 100 max hunger
    public int cleanliness = 100; // 0-100. 0 total dirty,
    public int weight = 50; // 0-inf. // in kg
    public int energy = 90; // 0-100. 0 = no energie
    public int health = 90; // 0-100. 0 = death
    public int level = 1; // 0-inf
    public int experience = 10; // counter. 100 experience -> level up
    public int age = 1; // NOT USED
    public int happiness = 50; // 0-100 0= no hapines
    public LocalTime bedTime = LocalTime.of(20, 0);
    public boolean sleeping = false;
    public boolean alive = true;

    @Transient
    public long lastMillis;
    @Transient
    public long lastAction;

    // the emotion after an action
    public String currentEmotion = SMILE;

    // index of the variable EMOTIONS
    public int generalEmotion = 2; // mild happy.

    private static final Random random = new Random();

    // time to set this tama to inactive mode.
    private static final String INACTIVE = "PT5M";

    /**
     * Making a character eat some food If the character overeats making it sadder
     * If character was
     * hungry eating makes it happier There is also a 50/50 chance that a character
     * gets a little
     * dirty while eating
     */
    public String eat() {
        currentEmotion = HAPPY;
        boolean gettingDirty = random.nextBoolean();
        if (gettingDirty)
            this.cleanliness = Math.max(0, this.cleanliness - 10);

        if (hunger + 25 > 100) {
            this.weight++;
            this.happiness = Math.max(happiness - 10, 0);
            this.health = Math.max(health - 5, 0);
            this.hunger = 100;
            currentEmotion = ANGRY;
            System.out.println("Character overfed");
        } else {
            this.hunger = Math.min(100, this.hunger + 25);
            this.happiness = Math.min(100, this.happiness + 5);
            this.experience = this.experience + 4;
            System.out.println("Character is happy to eat");
        }
        lastAction = System.currentTimeMillis();
        return currentEmotion;
    }

    /**
     * Getting character cleaner If the character is already clean enough then it
     * maks the character
     * sad and drains energy If the character needs a little shower then adding an
     * appropriate value
     * to a cleanliness value
     */
    public String clean() {
        currentEmotion = PEACEFUL;
        if (cleanliness + 30 > 100) {
            this.happiness = Math.max(this.happiness - 10, 0);
            this.energy = Math.max(0, this.energy - 10);
            this.cleanliness = 100;
            currentEmotion = FRUSTRATED;
            System.out.println("There is no need to clean the character");
        } else {
            this.cleanliness = Math.min(100, this.cleanliness + 30);
            this.happiness = Math.min(this.happiness + 10, 100);
            this.experience = this.experience + 5;
            System.out.println("Character is cleaner");
        }
        lastAction = System.currentTimeMillis();
        return currentEmotion;
    }

    /**
     * Getting a character to sleep. Setting sleeping field to false and saving a
     * time when a
     * character went to sleep
     */
    public String sleep() {
        currentEmotion = ASLEEP;
        this.sleeping = true;
        this.bedTime = LocalTime.now();
        System.out.println("Character is going to sleep");
        lastAction = System.currentTimeMillis();
        return currentEmotion;
    }

    /**
     * Waking up a character from a sleep, and making character's go up according to
     * the amount of
     * minutes that elapsed from character going to sleep. Setting a sleeping field
     * to false
     */
    public String wakeUp() {
        currentEmotion = SMILE;
        LocalTime now = LocalTime.now();
        Duration timeElapsed = Duration.between(bedTime, now);
        long sleepingTime = timeElapsed.toMinutes();
        if (this.energy + (int) sleepingTime > 100)
            this.energy = Math.min(100, this.energy + (int) sleepingTime);
        else
            this.energy = this.energy + (int) sleepingTime;
        this.sleeping = false;
        this.experience = this.experience + 2;
        System.out.println("Character woke up");
        lastAction = System.currentTimeMillis();
        return currentEmotion;
    }

    /**
     * Playing with a character which makes it happier, hungrier, dirtier, healthier
     * and lighter. WHAT
     * A COMBO! When the character doesn't have enough energy for that activity it
     * loses some health
     * and is getting sadder because of it
     *
     * @param funAmount the amount of fun the game provides
     */
    public String play(int funAmount) {
        currentEmotion = SMILE;
        if (energy <= 10) {
            this.happiness = Math.max(0, this.happiness - 20 + funAmount);
            this.health = Math.max(0, this.health - 10);
            this.weight = Math.max(0, weight - 1);
            currentEmotion = TIRED;
            System.out.println("Character is too tired");
            System.out.println("Don't force it to play");
        } else {
            this.happiness = Math.min(100, this.happiness + funAmount);
            this.health = Math.min(100, this.health + 1);
            this.experience = this.experience + 10;
            System.out.println("Character played a game");
            currentEmotion = PLAYFUL;
        }

        this.energy = Math.max(this.energy - 10, 0);
        this.hunger = Math.max(0, this.hunger - 5);
        this.cleanliness = Math.max(0, this.cleanliness - 10);
        lastAction = System.currentTimeMillis();
        return currentEmotion;
    }

    /**
     * Character level is going up. Only when the experience level is above 100
     * Otherwise we do
     * nothing
     */
    public String levelUp() {
        currentEmotion = SMILE;
        if (experience >= 100) {
            this.level++;
            this.experience = this.experience - 100;
            System.out.println("LVL UP!");
            currentEmotion = CELEBRATION;
        } else {
            // System.out.println("Not enough experience to level up");
        }
        return currentEmotion;
    }

    public boolean isActive() {
        long secs = Duration.parse(INACTIVE).getSeconds() * 1000;
        return System.currentTimeMillis() - lastAction < secs;
    }

    public void timePassed() {
        if (System.currentTimeMillis() - lastMillis < 60 * 1000)
            return;

        lastMillis = System.currentTimeMillis();
        this.cleanliness -= 1;
        this.hunger -= 1;
        this.experience += 4;
        this.happiness -= 1;
        this.energy -= 1;
        levelUp();
        if (this.health <= 0) {
            this.health = 0;
            // this.alive = false;
            // System.out.println("Character died :/");
            currentEmotion = SAD;
        }
        // check inactivitiy
        long secs = Duration.parse(INACTIVE).getSeconds() * 1000;
        if (System.currentTimeMillis() - lastAction > secs)
            Tamagotchi.getEntityManager().merge(this);
    }

    public String toHtmlString() {
        return "Hunger = "
                + hunger
                + "<br>"
                + "Cleanliness = "
                + cleanliness
                + "<br>"
                + "Weight = "
                + weight
                + "<br>"
                + "Energy = "
                + energy
                + "<br>"
                + "Health = "
                + health
                + "<br>"
                + "Level = "
                + level
                + "<br>"
                + "Experience = "
                + experience
                + "<br>"
                + "Age = "
                + age
                + "<br>"
                + "Happiness = "
                + happiness
                + "<br>"
                + "Sleeping = "
                + sleeping
                + "<br>"
                + "BedTime = "
                + bedTime;
    }

    @Override
    public String toString() {

        return "Character Data\n"
                + "---------------------------\n"
                + "Hunger   =   "
                + hunger
                + "\n---------------------------\n"
                + "Cleanliness   =   "
                + cleanliness
                + "\n---------------------------\n"
                + "Weight   =   "
                + weight
                + "\n---------------------------\n"
                + "Energy   =   "
                + energy
                + "\n---------------------------\n"
                + "Health   =   "
                + health
                + "\n---------------------------\n"
                + "Level   =   "
                + level
                + "\n---------------------------\n"
                + "Experience   =   "
                + experience
                + "\n---------------------------\n"
                + "Age   =   "
                + age
                + "\n---------------------------\n"
                + "Happiness   =   "
                + happiness
                + "\n---------------------------\n"
                + "Sleeping   =   "
                + sleeping
                + "\n---------------------------\n"
                + "BedTime   =   "
                + bedTime;
    }
}
