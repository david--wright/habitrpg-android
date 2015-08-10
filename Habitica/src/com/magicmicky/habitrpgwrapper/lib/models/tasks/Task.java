package com.magicmicky.habitrpgwrapper.lib.models.tasks;

import com.habitrpg.android.habitica.HabitDatabase;
import com.habitrpg.android.habitica.R;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

/**
 * Created by viirus on 10/08/15.
 */
@Table(databaseName = HabitDatabase.NAME)
public class Task extends BaseModel {

    @Column
    @PrimaryKey
    String id;
    @Column
    public Float priority;

    @Column
    public String text, notes, attribute, type;

    @Column
    public Double value;


    @Column
    public Boolean up, down;

    @Column
    public Boolean completed;

    @Column
    public String frequency;

    @Column
    public Integer everyX, streak;

    @Column
    @ForeignKey(references = {@ForeignKeyReference(columnName = "days_id",
            columnType = Long.class,
            foreignColumnName = "id")})
    public Days repeat;
    //TODO: private String lastCompleted;

    @Column
    public String date;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }
    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }
    /**
     * @return the priority
     */
    public Float getPriority() {
        return priority;
    }
    /**
     * @param i the priority to set
     */
    public void setPriority(Float i) {
        this.priority = i;
    }
    /**
     * @return the text
     */
    public String getText() {
        return text;
    }
    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }
    /**
     * @return the value
     */
    public double getValue() {
        return value;
    }
    /**
     * @param value the value to set
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * To be allowed to set int value without problems
     * @param value the value to set
     */
    public void setValue(double value) {
        this.setValue(Double.valueOf(value));
    }


    /**
     * Returns a string of the type of the Task
     * @return the string of the Item type
     */
    public String getType() {return this.type;}

    public void setType(String type) {this.type = type;}

    /**
     * @return whether or not the habit can be "upped"
     */
    public boolean getUp() {
        return up;
    }
    /**
     * Set the Up value
     * @param up
     */
    public void setUp(Boolean up) {
        this.up = up;
    }
    /**
     * @return whether or not the habit can be "down"
     */
    public boolean getDown() {
        return down;
    }
    /**
     * Set the Down value
     * @param down
     */
    public void setDown(Boolean down) {
        this.down = down;
    }


    public boolean getCompleted() {
        return completed;
    }
    /**
     *  Set whether or not the daily is completed
     * @param completed
     */
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }

    public Integer getEveryX() { return everyX; }
    public void setEveryX(Integer everyX) { this.everyX = everyX; }

    /**
     * @return the repeat array.<br/>
     * This array contains 7 values, one for each days, starting from monday.
     */
    public Days getRepeat() {
        return repeat;
    }
    /**
     * @param repeat the repeat array to set
     */
    public void setRepeat(Days repeat) {
        this.repeat = repeat;
    }
    /**
     * Formated:
     * SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
     * @return the lastCompleted
     */
/*	public String getLastCompleted() {
		return lastCompleted;
	}
	/**
	 * @param lastCompleted the lastCompleted to set
	 */
/*	public void setLastCompleted(String lastCompleted) {
		this.lastCompleted = lastCompleted;
	}
	/**
	 * @return the streak
	 */
    public int getStreak() {
        return streak;
    }
    /**
     * @param streak the streak to set
     */
    public void setStreak(Integer streak) {
        this.streak = streak;
    }


    /**
     * @return the due date
     */
    public String getDate() {
        return date;
    }

    /**
     * Set the due date
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the attribute
     */
    public String getAttribute() {
        return attribute;
    }
    /**
     * @param attribute the attribute to set
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public int getLightTaskColor()
    {
        if (this.value < -20)
            return R.color.worst;
        if (this.value < -10)
            return R.color.worse;
        if (this.value < -1)
            return R.color.bad;
        if (this.value < 5)
            return R.color.neutral;
        if (this.value < 10)
            return R.color.better;
        return R.color.best;
    }

    /**
     * Get the button color resources depending on a certain score
     *
     * @return the color resource id
     */
    public int getDarkTaskColor()
    {
        if (this.value < -20)
            return R.color.worst_btn;
        if (this.value < -10)
            return R.color.worse_btn;
        if (this.value < -1)
            return R.color.bad_btn;
        if (this.value < 5)
            return R.color.neutral_btn;
        if (this.value < 10)
            return R.color.better_btn;

        return R.color.best_btn;
    }
}