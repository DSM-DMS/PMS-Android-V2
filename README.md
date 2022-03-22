# PMS_Android_V2

***

> PMS(Parents' Management System - 대덕소프트마이스터고 학부모님들의 자녀관리시스템)

* 대덕소프트웨어마이스터고는 전교생이 기숙사생활을 의무적으로 해야하는 학교입니다. 따라서 많은 학부모님들 께서 자녀의 생활을 궁금해하셨는데, 이를 확인하는데에 여러 번거움이 있었습니다.
__PMS__ 는 이러한 불편함을 해소하기위해 만들어졌습니다.
## 주요기능
1. 학사일정 확인

    <img src="https://user-images.githubusercontent.com/67100819/132100410-adf35f5a-cf09-476c-97e8-058c472b7f2f.jpg" width="120" height="250">
2. 급식확인

    <img src="https://user-images.githubusercontent.com/67100819/132100438-8fa9185b-6437-4ead-9e9f-30e1b8ef77c9.jpg" width="120" height="250">
    <img src="https://user-images.githubusercontent.com/67100819/132100442-ce7c4b7a-77b1-4e26-a407-6fbd37e2cb9d.jpg" width="120" height="250">
3. 학교소식확인

    * 포토엘범
    
       <img src="https://user-images.githubusercontent.com/67100819/132100564-3adb5842-ee5d-4498-b8b9-65333bdbed34.jpg" width="120" height="250">
       <img src="https://user-images.githubusercontent.com/67100819/132990816-07ce1331-023c-49b9-8874-73b945e8bf7c.jpg" width="120" height="250">
    
    * 공지사항

       <img src="https://user-images.githubusercontent.com/67100819/132100590-de4250a7-15e3-4517-8e09-1818710bad4d.jpg" width="120" height="250">
       <img src="https://user-images.githubusercontent.com/67100819/132990821-7b8bcb83-6bbe-4d6a-990a-cd8db0eee84c.jpg" width="120" height="250">

    * 가정통신문   
       <img src="https://user-images.githubusercontent.com/67100819/132100595-3eb5befb-935a-4c1b-815f-c70893ea6c3f.jpg" width="120" height="250">
       <img src="https://user-images.githubusercontent.com/67100819/132990823-92df64c3-aa97-4e8a-82e7-e233a85872c7.jpg" width="120" height="250">
    
    

4. 학교소개

    <img src="https://user-images.githubusercontent.com/67100819/132100618-5037eeaf-eae0-454c-a297-cd47f0bf5e14.jpg" width="120" height="250">
5. 학생정보확인

    <img src="https://user-images.githubusercontent.com/67100819/132100629-50257e24-dba7-4e1d-90de-1ed8ebeedf84.jpg" width="120" height="250">
    <img src="https://user-images.githubusercontent.com/67100819/132100643-e55d846a-0069-494c-91b4-8a6c1d108ee3.jpg" width="120" height="250">
    <img src="https://user-images.githubusercontent.com/67100819/132100653-9cc7efcf-ff58-4e3b-b2fc-54856bc39218.jpg" width="120" height="250">
## Tech
1. MVVM
2. Databinding
3. RxJava
4. Koin
5. OkHttp
6. Retrofit2
7. Room
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

