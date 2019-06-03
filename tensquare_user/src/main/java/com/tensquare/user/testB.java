package com.tensquare.user;


public class testB  extends testA{

    @Override
    public Integer fun(Person person) {
        System.out.println("返回的是100的");
        return 10 * 10 ;
    }

    @Override
    public Integer taxCalCluate(Person person) {
        isNull = true;
         return  super.taxCalCluate(person);

    }
    @Override
    public Integer over(){
       return new Person(13,13,13).getAge();
    }

    public static void main(String[] args) {
        Person person=new Person(100,200,300);
        System.out.println(new testB().taxCalCluate(person));
    }
}
