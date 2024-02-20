# TestApp

Дизайн макет: 
https://www.figma.com/file/zzhf0xOS7FP6jWK2Tqb1Gy/internship?node-id=0%3A1&t=cXpylF8FuatqK0pR-1

API:
- http://cars.cprogroup.ru/api/rubetek/cameras/ - Метод получение камер
- http://cars.cprogroup.ru/api/rubetek/doors/ - Метод получение дверей

Требование: 
Использование Git.

Описание: 
- Сохранение данных в БД; 
- Изменение (имя) производим в БД; 
- При открытии раздела если в базе данных нет, то делаем запрос на сервер, если данные в базе есть, то отображаем их + функция принудительного обновления через PullRefresh; 

Stack:
- Kotlin
- MVVM
- Realm
- Ktor
- Coroutines
- LiveData
- Jetpack Compose + Material Design

  ## result

Tested on devices: 
- 3.7 FWVGA slider API 26
- Galaxy Nexus API 33
- Galaxy A13 API 33
    
  <table style="padding:10px">
  <tr>
    <td><img src="https://github.com/vadhub/TestApp/blob/master/shimmer.png?raw=true" alt="1" width = 500></td>
    <td><img src="https://github.com/vadhub/TestApp/blob/master/cam1.png?raw=true" alt="2" width = 500></td>
    <td><img src="https://github.com/vadhub/TestApp/blob/master/cam2.png?raw=true" alt="3" width = 500></td>
    <td><img src="https://github.com/vadhub/TestApp/blob/master/door1.png?raw=true" alt="4" width = 500></td>
    <td><img src="https://github.com/vadhub/TestApp/blob/master/door2.png?raw=true" alt="5" width = 500></td>
  </tr>
</table>
  
