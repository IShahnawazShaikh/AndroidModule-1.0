package com.shahnawazshaikh.classcontentdetailmodule.bean;

public class Course {
    private String vid,vname,vofclass,vdesp,vpath,vdate,vsub;
     public Course(){}

    public Course(String vid, String vname, String vofclass, String vdesp, String vdate, String vsub, String vpath) {

        this.vid=vid;
        this.vname=vname;
        this.vofclass=vofclass;
        this.vdesp=vdesp;
        this.vdate=vdate;
        this.vsub=vsub;
        this.vpath=vpath;

    }


    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vnane) {
        this.vname = vnane;
    }

    public String getVofclass() {
        return vofclass;
    }

    public void setVofclass(String vofclass) {
        this.vofclass = vofclass;
    }

    public String getVdesp() {
        return vdesp;
    }

    public void setVdesp(String vdesp) {
        this.vdesp = vdesp;
    }

    public String getVpath() {
        return vpath;
    }

    public void setVpath(String vpath) {
        this.vpath = vpath;
    }

    public String getVdate() {
        return vdate;
    }

    public void setVdate(String vdate) {
        this.vdate = vdate;
    }

    public String getVsub() {
        return vsub;
    }

    public void setVsub(String vsub) {
        this.vsub = vsub;
    }
}
