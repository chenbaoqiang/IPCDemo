package cn.com.fetion.one;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/11/8.
 */

public class Person implements Parcelable {
     private String name;
    public Person() {
    }
    protected Person(Parcel source) {
        readFromParcel(source);

    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }
    public void readFromParcel(Parcel source) {
        name = source.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
