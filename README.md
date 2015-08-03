# KSUG 2015 Modern Data Access for Enterprise Java - JPA

[행사 홈페이지](http://www.ksug.org/seminar/20150801/)

## 슬라이드 쉐어
- [JPA1 - JPA 소개](http://www.slideshare.net/zipkyh/ksug2015-jpa1-jpa-51213397)
- [JPA2 - JPA 기초와 매핑](http://www.slideshare.net/zipkyh/ksug2015-jpa2-jpa?related=1)
- [JPA3 - JPA 내부 구조](http://www.slideshare.net/zipkyh/ksug2015-jpa3-jpa?related=2)
- [JPA4 - JPA 객체 지향 쿼리 JPQL](http://www.slideshare.net/zipkyh/ksug2015-jpa4?related=3)
- [JPA5 - JPA 스프링과 JPA](http://www.slideshare.net/zipkyh/ksug2015-jpa5-jpa?related=4)

## 발표 자료 소개

자바 ORM 표준 JPA 프로그래밍 / 김영한
자바 서버 개발자 대부분은 객체를 관계형 데이터베이스에 저장합니다. 그래서 무수한 SQL과 매핑 코드를 작성하는데 많은 시간을 소비합니다. 마이바티스(MyBatis)나 스프링의 JdbcTemplate 같은 SQL 매퍼를 사용하면 매핑 코드를 어느정도 줄일 수 있습니다. 하지만 여전히 등록, 수정, 삭제, 조회(CRUD)용 SQL은 반복해서 작성해야 합니다.
더 큰 문제는 행동 중심인 객체와 데이터 중심인 관계형 데이터베이스 사이에는 큰 간격이 있다는 점입니다. 객체 지향 프로그래밍은 추상화, 캡슐화, 정보은닉, 상속, 다형성 등 시스템의 복잡성을 제어할 수 있는 다양한 장치들을 제공합니다. 하지만 관계형 데이터베이스에 객체를 저장하려고 SQL을 작성하는 순간 이런 이점들은 대부분 포기하게 됩니다. 자바 진영에서는 이런 숙제를 풀기 위해 오랜 기간 많은 노력을 기울여 왔습니다. 그 결과 JPA라는 기술 표준이 탄생했습니다. JPA는 SQL 작성 없이 객체를 데이터베이스에 직접 저장할 수 있게 도와주고, 객체와 관계형 데이터베이스의 차이도 중간에서 해결해줍니다. 그래서 JPA는 전 세계 스프링 개발자들이 가장 많이 사용하는 데이터 처리 기술이 되었습니다.

이 세션은 JPA 자체에 초점을 맞춥니다. 참고로 QueryDSL, 스프링 데이터 JPA도 설명하지만 이런 최신 자바 데이터 저장 기술들은 대부분 JPA를 활용하는 기술입니다. 따라서 JPA 자체를 이해하는 것이 가장 중요합니다.
세션에 라이브 코딩이 포함되어 있습니다. 노트북을 지참하셔서 함께 따라하시면 더 유익한 시간을 보낼 수 있습니다. :)