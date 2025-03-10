# thymeleaf-webservice

## thymelaef template 활용 절차

> https://lifere.tistory.com/entry/Spring-Boot-Thymeleaf-%ED%83%80%EC%9E%84%EB%A6%AC%ED%94%84-%EC%82%AC%EC%9A%A9%EB%B0%A9%EB%B2%95-%EB%B0%8F-%EC%8B%9C%EC%9E%91%ED%95%98%EA%B8%B0

## HashId 생성 시 제외할 문자열

문자 혼동을 방지하기 위해 제외해야 할 영문자와 숫자는 다음과 같습니다.

### 1. 혼동될 가능성이 높은 문자와 숫자

- 숫자 0 (zero) ↔ 대문자 O (Oscar)
- 숫자 1 (one) ↔ 대문자 I (India) ↔ 소문자 l (Lima)
- 숫자 5 (five) ↔ 대문자 S (Sierra)
- 숫자 6 (six) ↔ 대문자 G (Golf)
- 숫자 8 (eight) ↔ 대문자 B (Bravo)

### 2. 추천 제외 문자

위 조합을 고려했을 때, 다음 문자들은 혼동을 줄이기 위해 문자열 생성에서 제외하는 것이 좋습니다.

- 숫자: 0, 1, 5, 6, 8
- 대문자: O, I, S, G, B
- 소문자: l

### 3. 대체 가능한 문자 (선택 사항)

- 0 대신 Q 또는 D를 사용할 수 있음
- 1 대신 L 또는 T를 사용할 수 있음
- 5 대신 Z를 사용할 수 있음
- 6 대신 C를 사용할 수 있음
- 8 대신 X를 사용할 수 있음

이렇게 하면 사람이 읽을 때도 직관적이고 오류가 줄어드는 유니크한 문자열을 만들 수 있습니다. 😊

## Shorten URL

### Spring boot를 이용한 다양한 리다이렉트 방안

> https://jmlim.github.io/spring/2019/09/30/spring-redirect-to-an-external-url/
