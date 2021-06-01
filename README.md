# PMS_Android_V2
## Language
1. Kotlin
## Resource Rule
* color와 string은 전부 colors.xml,string.xml에서 관리합니다.
### 네이밍 규칙
0. 변수&클래스
    * 변수이름은 __카멜 케이스__ 를 기본으로 합니다.
    ```kotlin
    val personName = "홍길동"
    ```
    * 상수이름은 모두 대문자인 __스네이크 케이스__ 로 합니다
    ```kotlin
    val LOGIN_REQUEST_CODE = 0
    ```
    * 클래스 이름은 __파스칼 케이스__ 로 합니다.
    ```kotlin
    class Person(){}
    ```

1. drawable
    * __what_description_where__
    * where은 여러곳에 사용되면 쓰지 않습니다.
    * (what):icon -> ic, background -> back
    * ex) ic_list, back_border_black

2. layout
    * what_description
    * ex) activity_main, fragment_chatting

3. id
    * where_description_what
    * textView -> tv, editText -> et, button -> btn, customView -> cv 등 줄여서 사용
    * ex) main_intro_tv, login_submit_btn

4. colors.xml
    * color[what] 으로 작성하여 사용합니다

## Tech
1. MVVM
2. Databinding
3. RxJava
4. Koin
5. OkHttp
6. Retrofit2