public class ExtendsTest {
    //a test about extend
    public static void main(String[] args) {
        //实例化三个对象
        Animal animal=new Animal();
        Dog dog = new Dog();
        Bird bird =new Bird();
        animal.move();//父类的方法
        dog.move();//继承父类的方法
        bird.move();//重写父类的方法
        dog.sleep();//子类特有的方法

    }
}

class Animal {
    public void move() {
        System.out.println("动物在移动");
    }
}

class Dog extends Animal {
    public void sleep() {
        System.out.println("狗在睡觉");
    }
}

class Bird extends Animal {
    public void move() {
        System.out.println("鸟在飞翔");
    }
}