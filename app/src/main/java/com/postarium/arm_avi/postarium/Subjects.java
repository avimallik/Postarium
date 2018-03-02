package com.postarium.arm_avi.postarium;

/**
 * Created by Arm_AVI on 2/28/2018.
 */

public class Subjects {

    String SubName = null;
    String SubFullForm = null;
    String SubPhone = null;

    public Subjects(String Sname, String SFullForm, String SPhoneNo) {

        super();

        this.SubName = Sname;

        this.SubFullForm = SFullForm;

        this.SubPhone = SPhoneNo;

    }

    public String getSubName() {

        return SubName;

    }
    public void setSubName(String code) {

        this.SubName = code;

    }
    public String getSubFullForm() {

        return SubFullForm;

    }
    public void setSubFullForm(String name) {

        this.SubFullForm = name;

    }

    public String getSubPhone(){
        return SubPhone;
    }

    public void setSubPhone(String phoneNo){
        this.SubPhone = phoneNo;
    }

    @Override
    public String toString() {

        return  SubName + " " + SubFullForm + " " + SubPhone;

    }
}
