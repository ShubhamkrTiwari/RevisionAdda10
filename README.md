# RevisionAdda10

RevisionAdda10 ek Android app hai (Kotlin, Jetpack Compose) jo students ke liye notes, quizzes, revision reminders aur progress tracking provide karta hai. Yeh README project ka detailed overview, recommended architecture, package/folder structure, zaroori dependencies, CI/testing instructions, local setup aur agle kadam Hindi mein batata hai.

Project Summary
- Naam: RevisionAdda10
- Platform: Android (Kotlin, Jetpack Compose)
- Goal: Students ke liye lightweight app — notes, quizzes, revision reminders, progress tracking.
- Target users: Students preparing exams; offline-first with optional cloud sync.

MVP (initial features)
- User onboarding / optional local auth
- Create / edit / delete notes
- Create / attempt quizzes (MCQ)
- Schedule revision reminders (WorkManager)
- Track progress / stats per topic
- Search notes & quizzes

High-level Architecture (recommendation)
- Clean Architecture: Presentation -> Domain -> Data
  - Presentation: Jetpack Compose, ViewModels (StateFlow)
  - Domain: UseCases, business rules, models
  - Data: Repositories, Room (local), Retrofit (remote), DTOs & mappers
- DI: Hilt
- Concurrency: Kotlin Coroutines + Flow
- Navigation: Navigation-Compose (single-activity)
- Storage: Room (entities/DAOs) + DataStore for prefs
- Background: WorkManager for reminders & notifications

Recommended Modules
- :app — main Android module (UI + Android glue)
- :core — shared utilities, extensions
- :feature-notes, :feature-quizzes, :feature-auth — optional feature modules
- buildSrc — version management (optional)

Package / Folder Structure (package-by-feature recommended)
- app/src/main/kotlin/com/revisionadda/
  - App.kt (Hilt @HiltAndroidApp)
  - MainActivity.kt
  - di/ (NetworkModule.kt, DatabaseModule.kt, RepositoryModule.kt)
  - ui/
    - theme/ (Color.kt, Theme.kt, Type.kt)
    - components/ (reusable Composables)
    - screens/
      - home/ (HomeScreen.kt, HomeViewModel.kt)
      - notes/ (NotesScreen.kt, NoteEditorScreen.kt, NotesViewModel.kt)
      - quizzes/ (QuizListScreen.kt, QuizScreen.kt, QuizViewModel.kt)
    - navigation/ (NavGraph.kt)
  - domain/
    - models/ (Note.kt, Quiz.kt, Question.kt)
    - repository/ (RevisionRepository.kt interface)
    - usecases/ (GetNotesUseCase.kt, SaveNoteUseCase.kt)
  - data/
    - local/ (db/RevisionDatabase.kt, dao/NoteDao.kt)
    - prefs/ (PrefsDataStore.kt)
    - remote/ (api/ApiService.kt, dto/)
    - repository/ (RevisionRepositoryImpl.kt)
  - util/ (Result.kt, DateUtils.kt, NotificationHelper.kt)

## Screenshots & UI notes

- Splash (screenshot 1): Gradient background with logo and title. Implemented an animated splash (scale + fade) and ensured it is accessible to screen readers via content descriptions.
- Progress (screenshot 2): Card with overall percentage and small stat pills. Implemented an accessible `ProgressCard` composable with animated circular progress and high-contrast stat pills.
- Game / Subjects (screenshot 3): Large study challenge card and subject cards. Improved typography handling to avoid awkward wrapping and added consistent padding/spacing for cards.
- Settings (screenshot 4): Light-mode settings with toggles. Implemented `ThemeDataStore` (DataStore preferences) to persist the Light/Dark toggle and applied dynamic theme switching.

Implemented UI improvements in this branch:
- Theme persistence using DataStore (ThemeDataStore)
- Dynamic theme wrapper (`RevisionAddaTheme`) for light/dark colors
- Animated splash screen (scale + timeout)
- Animated and accessible progress card (circular percent + stat pills)
- README section documenting screenshots & notes

(For code and exact implementation details see the improve/ui-and-theme branch.)
