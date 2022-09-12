package sgz.wizjson;

class User {

    private String name ;
    private Integer age ;
    private String gender;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String toString(){
        return name + " 是个 " + gender +  "  今年 " + age + "岁" + "。 " ;
    }


}