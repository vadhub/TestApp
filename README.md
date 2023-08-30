# TestApp
Тестовое задание (Android Developer)

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
- Jetpack Compose + Material Design (Jetpack Сompose обязательно, хорошо если xml знаете)

  ## result

  <table style="padding:10px">
  <tr>
    <td><img src="https://github.com/vadhub/TestApp/blob/master/cameras.png?raw=true"  alt="1" width = 200px></td>
    <td><img src="https://github.com/vadhub/TestApp/blob/master/doors.png?raw=true" align="right" alt="2" width = 200px></td>
    <td><img src="https://github.com/vadhub/TestApp/blob/master/edit_cam.png?raw=true" align="right" alt="2" width = 200></td>
    <td><img src="https://github.com/vadhub/TestApp/blob/master/door_redact.png?raw=true" align="right" alt="2" width = 200></td>
  </tr>
</table>
  
