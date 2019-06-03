package com.tensquare.user;

import com.tensquare.user.pojo.User;

public class testA implements interfacetest {
    private User user;
    private Person person = new Person(10, 20, 30);
    boolean isNull= false;
    @Override
    public Integer fun(Person person) {
        System.out.println("执行了吗");
        return 20;
    }

    @Override
    public Integer taxCalCluate(Person person) {
        if(isNull || true){
            System.out.println("true啊");
            System.out.println("获得年龄是12还是13:  "+over());

        }
        return fun(person) * over();
    }

    public Integer go(){
        person.setAge(1000);

        return person.getAge();
    }

    public Integer over(){
       return new Person(12,12,12).getAge();
    }
}

class Person {
    private Integer price;
    private Integer age;
    private Integer num;

    public Person(Integer price, Integer age, Integer num) {
        this.price = price;
        this.age = age;
        this.num = num;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
