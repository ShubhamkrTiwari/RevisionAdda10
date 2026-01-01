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

Key Classes & Responsibilities
- App.kt — initialize Hilt, WorkManager, analytics (agar use kar rahe hain)
- MainActivity.kt — setContent, Theme, NavGraph
- ViewModel (per screen) — UI state (StateFlow) aur events
- UseCases — ek kaam per class business logic
- Repository — data source abstraction (local + remote)
- DAOs (Room) — database operations
- ApiService (Retrofit) — remote endpoints

Data Flow Example
UI (Compose) -> ViewModel -> UseCase -> Repository -> DAO / ApiService -> Result/Flow -> ViewModel -> UI

State & Events
- UI State: immutable data class exposed as StateFlow
- UI Events: Channel / SharedFlow for one-time events (navigation, toast)
- Error handling: Result/Resource wrapper with Success/Loading/Error

Recommended Dependencies (examples)
- Kotlin: org.jetbrains.kotlin:kotlin-stdlib:1.9.x
- Coroutines: kotlinx-coroutines-android:1.7.x
- Compose BOM + androidx.compose: ui/material
- Navigation: androidx.navigation:navigation-compose:2.7.x
- Lifecycle/ViewModel: androidx.lifecycle:lifecycle-viewmodel-compose
- Hilt: com.google.dagger:hilt-android:2.48 + kapt
- Room: androidx.room:room-runtime, room-ktx + kapt room-compiler
- Retrofit + OkHttp + Moshi/kotlinx.serialization
- DataStore: androidx.datastore:datastore-preferences
- Coil: io.coil-kt:coil-compose
- WorkManager: androidx.work:work-runtime-ktx
- Testing: junit, mockk, turbine, androidx.compose.ui:ui-test-junit4

Build, CI & Quality
- Gradle Kotlin DSL (build.gradle.kts)
- CI: GitHub Actions (build, tests, lint)
- Linters: ktlint, detekt
- Pre-commit: ktlint format, detekt checks
- PR policy: require CI pass before merge

Testing Strategy
- Unit tests: ViewModels, UseCases (MockK for repositories)
- DAO tests: in-memory Room DB
- Flow tests: Turbine
- UI tests: Compose UI tests with testTags
- Optional: Instrumentation tests / Firebase Test Lab

Local Setup & Run
1. Install Android Studio + SDK 34
2. Ensure JDK 17
3. Clone repo: git clone git@github.com:ShubhamkrTiwari/RevisionAdda10.git
4. Create a branch: git checkout -b feat/initial-setup
5. Open project in Android Studio -> Gradle sync
6. Run on emulator/device

Useful Gradle commands
- ./gradlew :app:assembleDebug
- ./gradlew test
- ./gradlew ktlintFormat

Short Roadmap (first 2 weeks)
1. Scaffold app module + Hilt + Navigation + Theme (seeded)
2. Implement Notes feature (end-to-end)
3. Add Quizzes listing + attempt flow
4. Implement WorkManager reminders & local notifications
5. Add CI linters and branch protection
6. Phase 2: Optional cloud sync (Firebase)

Security & Privacy
- Do not hardcode API keys; use local.properties or secret manager
- Avoid storing sensitive info in plain text
- Follow Play Store privacy requirements

What I did now
- Prepared this README content containing detailed project plan and instructions in Hindi/English mix.

Next steps (choose one):
- I can push this README.md to branch seed/readme now. (I have created this branch and will add the file.)
- Or I can push directly to main if you prefer (tell me to push to main).
- Or I can implement the Notes feature next on a new branch.

Please confirm if you want me to push README.md to branch seed/readme now.
