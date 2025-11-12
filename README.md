🎓 Student Manager Android App

Android приложение для управления студентами с использованием современного стека технологий и Clean Architecture.

## 🚀 Особенности

- ✅ **Полный CRUD** - создание, чтение, обновление, удаление студентов
- ✅ **Room Database** - локальное хранение данных с автоматическими обновлениями
- ✅ **Clean Architecture** - разделение на Data, Domain, Presentation слои
- ✅ **Reactive UI** - автоматическое обновление через Flow/StateFlow
- ✅ **Modern UI** - Material Design 3, темная/светлая темы
- ✅ **Swipe to Delete** - жестовое управление с подтверждением

## 🛠 Технологический стек

### **Архитектура**
- Clean Architecture + MVVM
- Repository Pattern
- Use Cases (Interactors)
- Dependency Injection с Factory

### **Android Components**
- Room Database + Flow
- ViewModel + LiveData/StateFlow
- RecyclerView + ViewBinding
- Material Design 3

### **Язык & Инструменты**
- Kotlin + Coroutines
- Gradle Kotlin DSL
- Version Catalogs

## 📱 Скриншоты

| Список студентов | Добавление | Редактирование |
|------------------|------------|----------------|
| ![List]() | ![Add]() | ![Edit]() |

## 🏗 Структура проекта
#### app/
#### ├── data/
#### │ ├── StudentEntity.kt
#### │ ├── StudentDao.kt
#### │ ├── StudentDatabase.kt
#### │ └── StudentRepository.kt
#### ├── domain/
#### │ └── usecases/
#### │ ├── GetStudentsUseCase.kt
#### │ ├── AddStudentUseCase.kt
#### │ ├── UpdateStudentUseCase.kt
#### │ └── RemoveStudentUseCase.kt
#### └── presentation/
#### ├── adapter/
#### │ └── StudentAdapter.kt
#### ├── viewmodel/
#### │ ├── StudentViewModel.kt
#### │ └── StudentViewModelFactory.kt
#### └── MainActivity.kt

## 🚀 Установка и запуск

1. Клонируйте репозиторий:
```bash
git clone https://github.com/yourusername/student-manager-android.git
```
2. Откройте в Android Studio или выше
3. Соберите и запустите приложение

## 📝 Основные Use Cases
Просмотр списка студентов с оценками
Добавление новых студентов через FAB
Редактирование по тапу на студента
Удаление свайпом с подтверждением (Undo)
