# OnlineShop

Приложение - онлайн магазинкосметических средств. Приложение состоит из 5 главных экранов и нескольких дополнительных.
Реализована регистрация пользователя с сохранением данных для следующего входа (данные хранятся в базе данных). Осноные экраны - каталог, детали товара и просмотр избранных товаров.<br>
<br>**Стартовый экран и  экран регистрации**
<br>В приложении реализован стартовый экрна, логика которого определяет есть ли сохраненный пользователь в базе данных или у нас новый пользователь. В случае нового пользователя - старнтовый экране откроет регистрации, в противном случае - сразу каталог
. Экран регистрации валидирует вводимые значения и сохраняет данные пользователя в базу данных; при успешном вводе открывает каталог.<br>
<br>**Экран "Каталог"**
<br> На экране каталога отображаются товары. Пользователь может отсортировать товары тремя способами - по популярности, по возрастанию и убыванию цены. На экране также присутствуют теги (chip group) с помощью которых можно отфильтровать
 список отображемых товаров. Сортировка на отфильтрованном списке сохраняется и её можно поменять. На элементе списка товаров есть кнопки добавить и удалить из избранного. Для лучшего визуального восприятия товара реализована карусель изображений.<br>
<br>**Экран "Детали"**
<br>При клике на товар пользователь попадет на экран деталей, где сможет посмотреть подробности, цену, увеличенное изображение товара и т.д. На экране деталей также есть кнопки добавить/удалить из избранного, при изменеии статуса товара в избранном
статус также поменяется и на экране "Каталог".<br>
<br>**Экран "Профиль"**
<br>На экране профиля пользователь видит данные о себе и избранных товарах. Выход из приложения осуществляется по нажатию на кнопку выйти, в этом случае стираются все данные о пользователе и его избранных товарах.<br>
<br>**Экран "Избранное**
<br>Экран реализован в виде двух табов, между которыми можно перемещатся свайпом или нажатием. На экране отображаются избранные товары, есть возможность удалить товар из избранного или перейти в детали товара.<br>

Остальные экраны находятся в разработке.

## Технические детали реализации
+ Приложение создано по принципам чистой архитектуры и SOLID, есть 3 слоя (data, domain, presentation). Связь между слоями осуществляется через интерфейсы.
+ Используется паттерн MVVM в презентейшн слое.
+ В качетсве библиотеки для DI выбрана библиотека Hilt.
+ Приложения реализовано по принципу единой активити с фрагментами, навигация через navigation component и safe args.
+ Получение данных реализовано с помощью Retrofit и корутин.
+ Избранные товары и данные о пользователе хранятся в SQlite базе данные, библиотека Room и корутины.
+ Списки сделаны через recyclerView и ListAdapter + Diffutil.
+ Все по работе с данными лежит в data слое, некоторая часть бизнес логики (фильтрация и сортировка) в domain.
+ На экране каталога и избранного реализована LCE модель управления состоянием экрана (loading, content, error).
+ Валидация вводимого номера телефона реализована с помощью библиотеки Tinkoff decoro. Работа с UI через viewBinding.
+ Карусели избражений и табы в избранном реализованы через viewPager2 и tabLayout.

## Используемый стек

+ Kotlin
+ Clean Architecture
+ Hilt
+ Retrofit
+ Room
+ SOLID
+ MVVM (ViewModel, LiveData)  
+ RecyclerView
+ ViewBinding  
+ Navigation Component  
+ Glide
+ Tinkoff decoro
+ Coroutines

## Запись работы основных экранов <br>  Стартовый и регистрация <br> 

<img src="https://github.com/alexxk2/OnlineShop/assets/96295239/c8029aa9-374f-4bac-b415-f99811508eea" width="340" height="699" />  
<br>
<br> 
<img src="https://github.com/alexxk2/OnlineShop/assets/96295239/9b83ce86-e92a-4c7f-8226-6fe54b64f91d" width="340" height="699" /> 
<br>
<br> 
<img src="https://github.com/alexxk2/OnlineShop/assets/96295239/182e167d-5962-4345-bb2f-59513a5d29f9" width="340" height="699" /> 
<br>
<br> 

## Экран "Каталог" и ошибка NoInternet
<img src="https://github.com/alexxk2/OnlineShop/assets/96295239/f32b77c8-1738-43ea-9ca2-af81c725c7a6" width="340" height="699" />  
<br>
<br> 
<img src="https://github.com/alexxk2/OnlineShop/assets/96295239/96fd0f56-1dac-4298-8895-8788c5ceb4f4" width="340" height="699" />  
<br>
<br> 
<img src="https://github.com/alexxk2/OnlineShop/assets/96295239/17488888-372b-4ffb-b9f0-f9624aa2f017" width="340" height="699" />  
<br>
<br> 
<img src="https://github.com/alexxk2/OnlineShop/assets/96295239/09cc4239-58aa-4a2f-b525-58628ea71f71" width="340" height="699" />  
<br>
<br> 

## Экран деталей
<img src="https://github.com/alexxk2/OnlineShop/assets/96295239/df9fb7d5-2d7d-4e36-a0c5-1a3051d3821b" width="340" height="699" />  
<br>
<br> 
<img src="https://github.com/alexxk2/OnlineShop/assets/96295239/7d9f32c1-4756-424a-a3e3-e12d97896aa3" width="340" height="699" />  
<br>
<br> 
<img src="https://github.com/alexxk2/OnlineShop/assets/96295239/9ea40758-abd6-401f-af01-a1dd8a33eaae" width="340" height="699" />  
<br>
<br> 

## Экран избранного
<img src="https://github.com/alexxk2/OnlineShop/assets/96295239/2877cdd4-85a2-41ef-9413-49f89a3d0a7d" width="340" height="699" />  
<br>
<br> 
<img src="https://github.com/alexxk2/OnlineShop/assets/96295239/2b246ca5-1f4a-45c5-851d-31b412f78ba2" width="340" height="699" />  
<br>
<br> 






