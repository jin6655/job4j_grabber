package ru.job4.xinst;

import ru.job4.grabber.Post;

import java.util.Properties;

public class Caty {

    public static void main(String[] args) {
        String str = "Описание вакансии\n" +
                "Tomcat/Jetty; навык работы с legacy-кодом; понимание концепции тестирования и опыт работы с JUnit; понимание концепции CI/CD; умение работать с git; навыки работы с реляционными СУБД; навыки работы c Docker достаточные для написания своих образов; английский достаточный для чтения документации. Языки и технологии: Java EE; Maven; JUnit; JaCoCo; PL/SQL Oracle; Docker. Желательно: умение работать в Agile (Scrum идеально); умение разбираться с Oracle кодом; опыт работы с Informatica Ultra Messaging; знакомство с трейдинг системами (Stock, Forex, Crypto); разговорный английский. Будет плюсом: опыт участия в разработке систем с фиксированным времени отклика; опыт работы в Azure; опыт работы с Ansible; опыт работы с Redis/Infinispan/Apache Ignite; опыт работы с Kubernetes.";
        System.out.println(str.replace("/","|"));
    }
}