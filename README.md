# Tugas 13 — Android UI Design (Material Design)

## Identitas
- **Nama** : Akhmad Amin Aziza
- **NIM**  : 452024611055
- **Kelas** : A1

---

## Deskripsi Proyek
Aplikasi ini merupakan implementasi tugas Lesson 13 — *App UI Design* — yang menerapkan:
1. **Styling & Themes** — palet warna kustom di `res/values/themes.xml` dengan dukungan Dark Theme melalui `res/values-night/themes.xml`.
2. **Typography (Type Scale)** — seluruh teks menggunakan `TextAppearance.MaterialComponents` turunan dengan satuan `sp`.
3. **Material Components** — `TextInputLayout` + `TextInputEditText`, `FloatingActionButton` di dalam `CoordinatorLayout`, `MaterialCardView`, `BottomNavigationView`, dan `Snackbar`.
4. **Localization & RTL** — string diekstrak ke `strings.xml`, dengan bahasa default **English** dan bahasa alternatif **Bahasa Indonesia** (`values-b+id`). Seluruh atribut layout menggunakan `start/end` (bukan `left/right`) sehingga siap untuk tata letak RTL.

---


## Precedence: Theme vs Style vs View Attribute

Dalam Android Styling System, ketiga level ini bisa sama-sama mengatur atribut (misalnya warna atau ukuran) untuk satu komponen View yang sama, dan Android mengikuti urutan prioritas dari yang **paling lemah ke paling kuat**: **Theme → Style → View Attribute (atribut langsung di tag XML)**. Sebuah *Theme* berlaku secara global ke seluruh aplikasi/activity dan menjadi nilai default paling dasar (misalnya `colorSecondary` di `themes.xml`); sebuah *Style* yang diterapkan lewat atribut `style="..."` pada suatu View akan menimpa nilai dari Theme untuk View tersebut (misalnya `Widget.App.Button` yang mengubah `cornerRadius` dan `textSize`); dan atribut yang ditulis **langsung** pada tag View di file layout XML (misalnya `app:backgroundTint="?attr/colorSecondary"` yang ditulis manual pada `<MaterialButton>`) memiliki prioritas **tertinggi** dan akan selalu memenangkan konflik terhadap nilai yang sama dari Style maupun Theme. Praktik ini diterapkan pada `btn_save` dalam proyek ini: meskipun `Widget.App.Button` (Style) dan Theme sudah mendefinisikan tampilan tombol, atribut `app:backgroundTint` yang ditulis langsung di layout tetap menjadi penentu akhir warna tombol tersebut.

---

## Struktur Proyek
```
app/src/main/
├── java/com/example/tugas13uidesign/MainActivity.kt
└── res/
    ├── values/themes.xml          (Light Theme)
    ├── values-night/themes.xml    (Dark Theme)
    ├── values/strings.xml         (Default - English)
    ├── values-b+id/strings.xml    (Bahasa Indonesia)
    ├── values/colors.xml
    ├── layout/activity_main.xml
    ├── menu/bottom_nav_menu.xml
    └── drawable/ (ic_home, ic_profile, ic_settings)
```

## Referensi
- [Google Developer Pathway — Lesson 13: App UI Design](https://developer.android.com/courses/pathways/android-development-with-kotlin-13?hl=id)
- [Material Design 3 Guidelines](https://m3.material.io/)
